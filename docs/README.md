# STAGE-ON 백엔드 문서

코드를 기준으로 작성한 문서입니다. API 명세의 기준은 Swagger(`/swagger-ui/index.html`)이고, 여기에는 코드만 봐서는 알기 어려운 **구조 · 정책 · 결정 배경 · 문제 해결 과정**을 정리했습니다.

| 문서 | 내용 |
| --- | --- |
| [architecture.md](architecture.md) | 전체 구조, 패키지 구성, CI/CD, 환경 변수, 주요 설정값, 현재 구조의 한계 |
| [domain-model.md](domain-model.md) | ERD, 엔티티, 복합 키 설계, 공연 데이터(`@ElementCollection`) 구조 |
| [auth.md](auth.md) | Google·Kakao OAuth2 로그인, JWT 발급·검증, GUEST → USER 온보딩, 인증 실패 응답 |
| [like-policy.md](like-policy.md) | 아티스트·공연 좋아요 정책(최소 2개·최대 50개), 다건 삭제, 온보딩 트랜잭션 |
| [search.md](search.md) | 통합 검색, 좋아요 여부 표시, 최근 검색어(중복 제거·5개 유지), 추천 검색어 |
| [ticketing-alert.md](ticketing-alert.md) | 좋아요한 공연의 예매일 D-3 메일 알림 배치(`@Scheduled` + `@Async`) |
| [design-decisions.md](design-decisions.md) | 설계 결정(ADR) — 외부 데이터 소스(Spotify 검토), 토큰 전략, 좋아요 검증, 에러 계약 등 *왜 그렇게 만들었는지* |
| [troubleshooting.md](troubleshooting.md) | 버그·장애 대응 기록 (증상 → 원인 규명 → 해결과 선택 근거 → 배운 점) |
| [operations.md](operations.md) | 배포·로그·시드 데이터 운영, 확인된 설정 불일치와 점검 필요 항목 |
| [contribution.md](contribution.md) | 곽동욱 기여 상세 — 담당 범위, 개발 타임라인, 커밋 근거 |

## 읽는 순서

- 서비스 구조가 궁금하다 → `architecture` → `domain-model`
- 설계 의도와 문제 해결 과정이 궁금하다 → `design-decisions`, `troubleshooting`
- 인증/권한을 건드린다 → `auth`
- 좋아요·검색·알림 로직을 건드린다 → `like-policy`, `search`, `ticketing-alert`
- 배포·운영 / 장애 대응 → `operations`, `troubleshooting`
- 누가 무엇을 했는지 → `contribution`

## 문서 작성 원칙

- 코드가 사실의 기준입니다. 정책이나 수치(좋아요 개수, 토큰 만료 시간 등)를 바꾸면 해당 문서도 같이 고칩니다.
- 수치·상태명·경로는 코드에서 확인한 값만 적고, 코드 위치를 링크로 남깁니다.
- 문제 해결 기록에는 관련 커밋 해시를 남겨 `git show <hash>`로 근거를 바로 볼 수 있게 합니다.
- 근거의 종류를 구분합니다.
  - `[기록]` 코드·주석·커밋 메시지에 당시 남아 있는 사실
  - `[분석]` 지금 코드와 커밋 diff를 보고 사후에 정리한 판단
  - `[진술]` 저장소에 흔적이 없고 담당자 기억에 근거한 내용
