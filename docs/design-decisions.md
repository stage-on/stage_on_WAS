# 설계 결정 (ADR)

기능이 아니라 **왜 그렇게 만들었는지**를 남기는 문서입니다. 각 결정을 *배경 → 결정 → 근거 → 트레이드오프* 순서로 정리했고, 담당자와 커밋을 함께 적었습니다.

> **근거 표기**: `[기록]` 코드·주석·커밋에 남아 있는 사실 · `[분석]` 지금 코드를 보고 사후에 정리한 판단 · `[진술]` 저장소에 흔적이 없고 담당자 기억에 근거한 내용

| # | 결정 | 핵심 선택 | 담당 |
| --- | --- | --- | --- |
| 1 | [외부 데이터 소스](#1-외부-데이터-소스--spotify-api를-검토하고-kopis--자체-데이터로-정했다) | Spotify Web API 검토 → KOPIS + 자체 아티스트 데이터 | 곽동욱(Spotify 검토) · 손재희(KOPIS·시드) |
| 2 | [토큰 전략](#2-토큰-전략--access-token-단일--subject는-userid) | Access Token 단일(6h), `sub = userId` | 곽동욱 |
| 3 | [세션 → JWT](#3-소셜-로그인은-세션이-아니라-jwt로-마무리한다) | OAuth2 로그인 성공 핸들러에서 JWT 발급 후 SPA로 리다이렉트 | 곽동욱 |
| 4 | [provider 매핑](#4-provider별-응답-매핑--enum-전략) | `OAuthAttributes` enum + 함수 | 곽동욱 |
| 5 | [좋아요 개수 검증](#5-좋아요-개수-검증--작업-후-상태를-기준으로-한-곳에서) | 공용 서비스, "작업 후 상태" 기준 | 곽동욱 |
| 6 | [좋아요 저장 구조](#6-좋아요는-복합-pk-연결-엔티티) | 복합 PK + `@MapsId` | 곽동욱 |
| 7 | [사용자 식별](#7-사용자-식별값은-요청이-아니라-토큰에서-꺼낸다) | `@AuthenticationPrincipal`만 신뢰 | 곽동욱 |
| 8 | [에러 계약](#8-에러-계약--errorcode-enum-한-곳에서-상태와-메시지를-정한다) | `CustomException(ErrorCode)` + 전역 핸들러 | 곽동욱 |
| 9 | [API 문서](#9-swagger-명세를-인터페이스로-분리한다) | Swagger 어노테이션을 `XxxApi` 인터페이스로 분리 | 곽동욱 · 손재희 |
| 10 | [알림 배치](#10-알림-배치--조회는-한-번-발송은-비동기) | fetch join 1회 + 사용자별 묶음 + `@Async` | 곽동욱 |

---

## 1. 외부 데이터 소스 — Spotify API를 검토하고 KOPIS + 자체 데이터로 정했다

**담당** 곽동욱(Spotify Web API 연동 검토) · 손재희(KOPIS 연동, 아티스트·공연 시드)

- **배경**: 서비스의 재료는 "아티스트 정보"와 "공연 정보" 두 가지입니다. 직접 입력하지 않고 외부 API에서 가져올 수 있는지가 첫 번째 결정이었습니다.
- **시도** `[진술]`: 곽동욱은 프로젝트 초기에 아티스트 정보(이름·이미지 등)를 **Spotify Web API**로 가져오는 방안을 먼저 시도했습니다. 연동 코드는 최종 저장소에 커밋되지 않았습니다.
  - 흔적 `[기록]`: 아티스트 시드 데이터 44건의 이미지 URL(`relate_url`)이 **모두 Spotify 이미지 CDN(`i.scdn.co`)** 을 가리킵니다([ArtistInfoDataInitializer](../src/main/java/com/stageon/stageonwas/domain/artist/seed/ArtistInfoDataInitializer.java)). Spotify에서 확인한 아티스트 이미지를 데이터로 옮겨 쓴 결과입니다.
- **결정**
  - 공연 정보: 국내 공연을 공식적으로 모아 둔 **KOPIS(공연예술통합전산망) Open API** (`mt20id` 기준 상세 조회, WebClient, 타임아웃 8초).
  - 아티스트 정보: **자체 데이터(`artist_info`)** 로 관리. 이미지는 Spotify CDN URL 사용.
- **근거** `[분석]` — 서비스 요구와 Spotify Web API가 주는 데이터를 비교한 사후 정리입니다.
  - Spotify Web API는 음원·아티스트 메타데이터(이름, 장르, 이미지, 인기도) 중심이고 **국내 공연 일정·예매일·공연장 정보가 없습니다**. 공연 데이터는 어차피 다른 소스가 필요했습니다.
  - 서비스 화면에 필요한 **밴드 소개글·세션 구성원** 같은 필드는 Spotify 아티스트 객체에 없습니다.
  - Spotify는 2024-11-27부터 신규 앱에 대해 Related Artists·Recommendations 등 일부 엔드포인트를 제한했습니다. "좋아하는 밴드 기반 추천"에 기대할 수 있었던 기능을 신규 프로젝트에서 쓰기 어렵습니다.
  - 대상이 국내 인디 밴드 수십 팀으로 한정적이라, 외부 API에 의존하기보다 직접 큐레이션한 데이터의 품질이 높습니다.
- **트레이드오프**
  - 아티스트 추가·수정이 사람 손을 거칩니다(시드 코드 수정 또는 `/api/artists` CRUD).
  - 이미지가 외부 CDN 주소에 의존합니다. Spotify가 URL을 바꾸면 이미지가 깨지므로, 자체 스토리지(S3)로 옮기는 것이 안전합니다.
- **배운 점**: 외부 API를 고를 때는 "가져올 수 있는가"보다 **서비스 화면이 요구하는 필드와 API가 주는 필드를 먼저 대조**해야 합니다. 그리고 API 정책(엔드포인트 제한, 이용 약관)이 바뀔 수 있다는 점까지 의존성의 비용에 포함됩니다.

## 2. 토큰 전략 — Access Token 단일 · subject는 userId

**담당** 곽동욱 · `51dd8e8`, `6d73572`, `f813eb9`, `de50ec7`, `9e14d8e` · [auth.md](auth.md#jwt)

- **배경**: 프론트(Vercel)와 백엔드(EC2)가 다른 도메인이고, 서버는 한 대, 일정은 짧았습니다.
- **결정**: HS256 Access Token 하나(6시간), Refresh Token 없음. `sub`에는 이메일이 아닌 **userId(PK)**.
- **근거**
  - 요청마다 서명 검증만으로 인증해 세션 저장소가 필요 없습니다 `[분석]`.
  - `sub = userId`인 이유: 사용자 식별이 `(email, provider)`라 이메일만으로는 사람이 유일하지 않고, 로그인 때마다 이메일이 갱신될 수 있으며, 토큰 payload(서명만 되고 암호화되지 않음)에 개인정보를 싣지 않기 위해서입니다 `[분석]`.
  - Refresh 없이 쓰기 위해 만료를 6시간으로 길게 잡았습니다. 공연 정보를 둘러보는 세션 길이를 덮으면서, 탈취 시 노출 시간은 하루 단위보다 짧게 유지하는 절충입니다 `[분석]`.
- **트레이드오프**: 서버가 발급한 토큰을 **회수할 수단이 없습니다**(로그아웃·탈퇴 즉시 무효화 불가). 다음 단계는 Refresh Token + rotation, 또는 Redis 블랙리스트입니다.

## 3. 소셜 로그인은 세션이 아니라 JWT로 마무리한다

**담당** 곽동욱 · `cb64b60`~`96431fa`(2025-11-03 세션 기반 OAuth2 구현) → `7b83cbd`(2025-11-06 JWT 발급·리다이렉트) → `0d0c964`(테스트 페이지 제거)

- **배경** `[기록]`: 처음에는 Spring Security 기본 OAuth2 로그인(세션) + Thymeleaf 테스트 로그인 페이지(`login.html`) + 로그인 정보를 보여주는 `/oauth/loginInfo`로 동작을 확인했습니다.
- **결정**: 로그인 성공 시 `OAuth2SuccessHandler`가 JWT를 발급해 **프론트 콜백 주소로 리다이렉트**하고, 이후 모든 API는 Bearer 토큰으로 인증합니다. 세션은 `STATELESS`.
- **근거** `[분석]`: 프론트가 별도 도메인의 SPA라 서버 세션 쿠키를 쓰려면 `SameSite=None; Secure` 쿠키와 CORS credential 설정을 맞춰야 하고 브라우저별 제약이 많습니다. 토큰을 헤더로 보내면 도메인과 무관하게 같은 방식으로 인증됩니다.
- **트레이드오프**: 리다이렉트 쿼리로 토큰을 넘겨 URL에 토큰이 노출됩니다([auth.md 알려진 한계](auth.md#알려진-한계)). 일회용 코드 → 토큰 교환 API로 바꾸는 것이 개선안입니다.

## 4. provider별 응답 매핑 — enum 전략

**담당** 곽동욱 · `93749cf` · [auth.md](auth.md#provider별-응답-차이-흡수--oauthattributes)

- **결정**: `registrationId → Function<Map, UserProfile>`을 enum 상수로 묶어 provider별 JSON 구조 차이를 흡수.
- **근거**: provider 추가·삭제가 enum 상수 단위로 끝나고(실제로 Naver 제거 시 확인, `48776b6`·`93e7166`), 서비스 코드에는 `extract(registrationId, attributes)` 한 줄만 남습니다. 지원하지 않는 provider는 예외로 즉시 드러납니다.
- **트레이드오프**: 매핑 함수 안에서 `Map` 캐스팅을 하므로 provider 응답 구조가 바뀌면 런타임에야 발견됩니다. 이메일 동의를 하지 않은 Kakao 사용자는 이메일이 `null`이 되는데, 이 경우를 매핑 단계에서 명시적으로 거부하지는 않습니다.

## 5. 좋아요 개수 검증 — "작업 후 상태"를 기준으로, 한 곳에서

**담당** 곽동욱 · `85feba0` → `6e65ef8`, `d51c25c`, `ddc640e`, `17b0ca8` · [like-policy.md](like-policy.md)

- **배경**: 최소·최대 개수가 "아티스트 + 공연 합산" 규칙이라 두 도메인 서비스가 같은 규칙을 지켜야 했습니다.
- **결정**
  - 규칙을 `common`의 `LikeValidationService` 한 곳에 둡니다.
  - 최소 개수는 `checkMinLikes(userId, countToDelete)` — **작업이 끝난 뒤의 개수**(`total - countToDelete`)로 판정합니다. 추가 전 최대 검사는 `total >= MAX`.
- **근거** `[기록 + 분석]`: 처음에는 `checkMinLikes(userId)`가 "현재 개수"만 봤는데, 같은 함수가 **삭제 전**(현재 개수 기준이어야 함)과 **온보딩 저장 후**(결과 개수 기준이어야 함)에 모두 쓰이면서 경계값(`<= 2` vs `< 2`, `>= 10` vs `> 10`)을 고칠 때마다 다른 쪽이 깨졌습니다([트러블슈팅 2](troubleshooting.md#2-좋아요-개수-경계값이-계속-어긋난-문제--작업-전-상태-vs-작업-후-상태)). "작업 후 상태"라는 하나의 기준으로 통일하자 호출부마다 의미가 명확해졌습니다(`0` = 이미 반영됨, `1` = 한 개 지울 예정, `n` = n개 지울 예정).
- **트레이드오프**: `COUNT` 후 저장이라 동시 요청에서 경쟁 조건이 남습니다. 정책 숫자(50)와 에러 메시지(10)가 따로 관리되어 불일치가 생겼습니다.

## 6. 좋아요는 복합 PK 연결 엔티티

**담당** 곽동욱 · `9ffe544`, `4aab85f`, `8e4f70e` · [domain-model.md](domain-model.md#좋아요는-대리-키-없는-연결-엔티티다-곽동욱)

- **결정**: `(user_id, artist_id)` / `(user_id, performance_id)`를 PK로, `@EmbeddedId` + `@MapsId`.
- **근거**: 중복 좋아요를 **DB 제약이 최종 보장**하고(애플리케이션 검사를 동시에 통과해도 두 번째 INSERT 실패), 조회·삭제가 PK 접근이 됩니다.
- **트레이드오프**: "좋아요한 시각"이 없어 최신순 정렬 불가. 복합 키 클래스에 `equals/hashCode`·`Serializable` 구현 부담.

## 7. 사용자 식별값은 요청이 아니라 토큰에서 꺼낸다

**담당** 곽동욱 · `c7130c0`, `bf8099e`, `eb659e2`, `4f76d60` (2025-11-20) · [트러블슈팅 1](troubleshooting.md#1-다른-사용자의-좋아요검색-기록을-조작할-수-있던-구조--idor)

- **결정**: 컨트롤러는 `userId`를 `@RequestParam`으로 받지 않고 `@AuthenticationPrincipal CustomUserDetails`에서만 꺼냅니다. `CustomUserDetails`는 `UserDetails`와 `OAuth2User`를 함께 구현해 필터·OAuth2 양쪽에서 같은 principal 타입을 씁니다.
- **근거**: 클라이언트가 보낸 식별값은 **조작 가능한 입력**입니다. 서버가 서명으로 검증한 토큰의 `sub`만이 "요청자가 누구인가"의 근거가 될 수 있습니다.
- **트레이드오프**: 필터가 요청마다 `User`를 DB에서 1회 조회합니다. 대신 서비스는 사용자 존재를 다시 확인할 필요가 줄고, 탈퇴한 사용자는 토큰이 유효해도 인증되지 않습니다.

## 8. 에러 계약 — `ErrorCode` enum 한 곳에서 상태와 메시지를 정한다

**담당** 곽동욱 · `5c7a1f7`(초기 전역 핸들러) → `33e1046`, `e830c7f`, `35f34f6`, `426a82a` (2025-11-23) · [GlobalExceptionHandler](../src/main/java/com/stageon/stageonwas/exception/GlobalExceptionHandler.java)

- **결정**: 비즈니스 예외는 `CustomException(ErrorCode)` 하나로 던지고, `ErrorCode`가 **HTTP 상태와 메시지**를 함께 가집니다. 전역 핸들러가 예외 종류별로 공통 `ErrorResDto { status, message, validation? }`를 만듭니다.

  | 예외 | 응답 |
  | --- | --- |
  | `CustomException` | `ErrorCode`의 상태·메시지 (404/409/400/403) |
  | `MethodArgumentNotValidException` | 400 + `validation: { 필드: 메시지 }` |
  | `MissingServletRequestParameterException` | 400 · "`{파라미터}` 필수 입력값이 누락되었습니다." |
  | `MethodArgumentTypeMismatchException` | 400 · "`{파라미터}` 입력 타입이 올바르지 않습니다." |
  | 그 외 `Exception` | 500 · 고정 메시지 + 서버 로그에 스택트레이스 |

- **근거**: 초기 핸들러는 모든 `RuntimeException`을 **400 + 예외 메시지 그대로** 응답했습니다. 그래서 "없는 아티스트(404여야 함)", "이미 좋아요함(409)", 서버 버그(500)가 전부 400이 되고, NPE 같은 내부 메시지가 클라이언트에 노출됐습니다 `[기록: 5c7a1f7]`. 상태 코드가 의미를 가져야 프론트가 분기할 수 있습니다([트러블슈팅 6](troubleshooting.md#6-모든-예외가-400으로-응답되던-문제--에러-계약-정립)).
- **트레이드오프**: 응답에 에러를 구분하는 **고유 `code` 필드가 없어** 같은 상태(예: 400 `LIKE_LIMIT_EXCEEDED` vs `LIKE_MIN_REQUIRED`)를 프론트가 메시지 문자열로 구분해야 합니다. `ErrorCode.name()`을 응답에 넣는 것이 다음 단계입니다. 필터 단계 401/403은 형식이 다릅니다([auth.md](auth.md#인증-실패-응답)).

## 9. Swagger 명세를 인터페이스로 분리한다

**담당** 곽동욱(인증·좋아요·검색·추천 API 문서, `6c52aa8`~`1bb8b2b`) · 손재희(KOPIS·타임테이블 API 문서)

- **결정**: `@Tag`, `@Operation`, `@ApiResponses`, 예시 JSON을 `XxxApi` 인터페이스에 두고 컨트롤러가 `implements` 합니다. JWT 인증은 `OpenApiSecurityConfig`의 `bearerAuth` 스키마로 Swagger UI에서 바로 넣을 수 있게 했고, 서버 목록에 운영(HTTPS)·로컬 주소를 명시했습니다(`35f69f0`).
- **근거**: 응답 예시·에러 케이스까지 적으면 어노테이션이 로직보다 길어집니다. 분리하면 컨트롤러는 로직만, 인터페이스는 계약만 보여줍니다. 프론트와 별도 레포로 협업했기 때문에 **Swagger가 유일한 API 계약서**였고, DTO 필드를 바꿀 때마다 문서 커밋을 함께 남겼습니다(`13d303c`, `be63693`, `e7cfc08`, `00fdde6`, `ee47e65`).
- **트레이드오프**: 인터페이스와 컨트롤러의 매핑 어노테이션이 중복되어, 한쪽만 바꾸면 문서와 실제 경로가 어긋날 수 있습니다.

## 10. 알림 배치 — 조회는 한 번, 발송은 비동기

**담당** 곽동욱 · `9450ad0`, `3feb3e5`, `abdb986` · [ticketing-alert.md](ticketing-alert.md)

- **결정**: 대상 조회는 fetch join 1회, 사용자별로 묶어 1통, 발송은 `@Async`, 실패는 사용자 단위로 로그.
- **근거**: N+1 제거, 메일 수 최소화, SMTP 지연이 배치 전체를 막지 않게 분리, 한 사용자의 실패가 전체를 멈추지 않게 격리.
- **트레이드오프**: 재시도·발송 이력·분산 락이 없고, cron 타임존을 지정하지 않았습니다.
