# 운영

## 배포

- `develop` 브랜치에 push하면 GitHub Actions가 빌드 → EC2로 JAR 전송 → `systemctl restart stageon`([architecture.md](architecture.md#cicd-githubworkflowsdeployyml-담당-손재희)).
- 기동 확인: 서버 로그에서 `### DEPLOY CHECK: APPLICATION STARTED v1 ###`.
- 수동 배포가 필요하면 Actions 탭에서 `workflow_dispatch`로 같은 워크플로를 실행합니다.

```bash
sudo systemctl status stageon --no-pager -l
```

```bash
sudo journalctl -u stageon -f
```

## 로그로 확인할 수 있는 것

| 상황 | 로그 | 위치 |
| --- | --- | --- |
| 예상하지 못한 서버 예외 | `INTERNAL_SERVER_ERROR` + 스택트레이스 | GlobalExceptionHandler |
| 알림 배치 시작/종료 | `--- [배치 시작] 예매일 알림 이메일 발송 ---`, `총 {n}명에게 알림 발송 완료` | PerformanceAlertService |
| 메일 발송 실패 | `이메일 발송 실패: User=..., FailedPerformances=..., Error=...` | EmailService |
| SQL | `show-sql: true`, `format_sql: true`로 모든 쿼리 출력 | application.yml |

`show-sql: true`는 운영에서도 켜져 있어 로그량이 많고, 쿼리 파라미터가 아닌 SQL 본문만 표준 출력으로 나갑니다. 운영 프로필에서는 끄고 필요할 때 `org.hibernate.SQL` 로거 레벨로 제어하는 것을 권장합니다.

## 공연 시드 데이터와 재배포

`PerformanceDataInitializer`는 **기동할 때마다** 다음을 실행합니다.

```sql
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE fes_artist_pics; ... TRUNCATE TABLE performance_detail;
SET FOREIGN_KEY_CHECKS = 1;
-- 이후 시드 공연을 다시 INSERT (id는 1부터 다시 매겨짐)
```

배포 = 재기동이므로 배포할 때마다 공연 테이블이 다시 만들어집니다. 사용자 데이터(`user_performance_like.performance_id`, `user_fes_slot_custom.performance_id`)는 공연 **PK**를 가리키므로:

- 시드 목록이 같으면 같은 순서로 같은 id가 매겨져 우연히 맞습니다.
- 시드에 공연을 추가·삭제·재정렬한 버전이 배포되면 **기존 좋아요가 다른 공연을 가리키거나 존재하지 않는 공연을 가리킵니다**(FK 검사를 껐기 때문에 오류도 나지 않음).

**권장 조치**: 시드를 `@Profile("dev")`로 되돌리거나 "이미 있으면 건너뛰기"(`ArtistInfoDataInitializer` 방식)로 바꾸고, 운영 데이터 변경은 `mt20id` 기준 upsert 스크립트로 분리합니다. 사용자 데이터가 대리 키 대신 `mt20id`를 참조하게 바꾸는 것도 방법입니다. 이 문제의 배경은 [troubleshooting.md#3](troubleshooting.md#3-좋아요-기능-추가-후-서버가-기동되지-않던-문제--시드-초기화와-fk)에 있습니다.

## 점검 필요 항목

코드에서 확인된 불일치와 보안·운영 점검 항목입니다. 우선순위는 영향 기준입니다.

| 우선 | 항목 | 내용 | 관련 |
| --- | --- | --- | --- |
| 높음 | 공연 시드 재적재 | 위 섹션. 재배포 시 사용자 좋아요가 다른 공연을 가리킬 수 있음 | PerformanceDataInitializer |
| 높음 | `/api/artists/**` 전체 `permitAll` | 조회뿐 아니라 아티스트 **생성·수정·삭제(POST/PUT/DELETE)도 인증 없이** 가능 | SecurityConfig (`6f51103`) — GET만 허용하고 나머지는 관리자 권한으로 |
| 높음 | 토큰이 URL에 노출 | 로그인 리다이렉트 `?token=`, 필터의 `token` 쿼리 파라미터 지원 | [auth.md](auth.md#알려진-한계) |
| 중간 | 알림 cron 타임존 | `@Scheduled`·`LocalDate.now()`가 JVM 기본 타임존을 따름. 서버가 UTC면 KST 18:00 발송 | [ticketing-alert.md](ticketing-alert.md#알려진-한계와-개선-후보) — 서버 `timedatectl` 확인 또는 `zone = "Asia/Seoul"` |
| 중간 | 같은 이메일 다른 provider | `findByEmail`이 2건을 만나면 예외 (`OAuth2SuccessHandler`, `UserCheckService`) | `(email, provider)` 기준 조회로 통일 + 유니크 제약 |
| 중간 | 좋아요 최대 개수 메시지 불일치 | 코드 50개, 메시지 "최대 10개" | ErrorCode `LIKE_LIMIT_EXCEEDED` |
| 중간 | 테스트 부재 | 컨텍스트 로드 테스트 1개뿐, CI에서도 미실행 | 좋아요 경계값·검색 기록 정책부터 단위 테스트 |
| 중간 | 인증 실패 응답 형식 | 필터 단계 401/403은 `{error, message}`, 그 외는 `{status, message}`. 만료/위조 구분 없음 | SecurityConfig, ErrorResDto |
| 낮음 | 이전 형식 토큰 파싱 | `sub`가 숫자가 아니면 필터에서 `NumberFormatException` → 500 | JwtAuthenticationFilter |
| 낮음 | JWT 필터 이중 등록 `[분석]` | `@Component`인 `OncePerRequestFilter`는 Spring Boot가 서블릿 필터로도 자동 등록함. `web.ignoring()` 경로에서도 실행되는 이유 | `FilterRegistrationBean.setEnabled(false)` 또는 `@Component` 제거 |
| 낮음 | 리다이렉트 주소 하드코딩 | 환경별로 코드 수정 필요 | [troubleshooting.md#8](troubleshooting.md#8-로그인-리다이렉트-주소를-바꾸는-커밋이-반복된-문제) |
| 낮음 | 운영 로그 | `show-sql: true`, 일부 `System.out.println` | 로거로 교체 |
| 낮음 | 외부 이미지 의존 | 아티스트 이미지가 Spotify CDN(`i.scdn.co`) URL | 자체 스토리지로 이전 |
