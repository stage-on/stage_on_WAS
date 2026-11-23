# 좋아요 정책

**담당** 곽동욱 · 설계 배경 [ADR 5](design-decisions.md#5-좋아요-개수-검증--작업-후-상태를-기준으로-한-곳에서) · 버그 기록 [troubleshooting.md#2](troubleshooting.md#2-좋아요-개수-경계값이-계속-어긋난-문제--작업-전-상태-vs-작업-후-상태)

좋아요는 STAGE-ON 개인화의 입력값입니다. "내 밴드(MY BANDS)", "내 공연(MY CONCERTS)", 좋아요한 아티스트 기준 공연 추천, 예매일 알림 메일이 모두 좋아요 데이터에서 출발합니다.

## 규칙

| 규칙 | 값 | 이유 |
| --- | --- | --- |
| 합산 대상 | 아티스트 좋아요 + 공연 좋아요 | 개인화 입력을 하나의 "관심 목록"으로 봄 |
| 최소 | **2개** 유지 | 추천·홈 화면이 비지 않도록. 온보딩에서 2개 이상 선택을 강제 |
| 최대 | **50개** | 무제한 저장 방지 (5 → 10 → 50으로 조정됨) |
| 중복 | 같은 대상 1회 | 복합 PK + `ALREADY_LIKED`(409) |

검증은 [LikeValidationService](../src/main/java/com/stageon/stageonwas/domain/common/service/LikeValidationService.java) 한 곳에 모았습니다.

```java
public void checkMaxLikes(Long userId)                     // 추가 "전" 호출: total >= 50 이면 거부
public void checkMinLikes(Long userId, int countToDelete)  // 작업 "후" 상태: total - countToDelete < 2 이면 거부
```

`checkMinLikes`가 "지울 개수"를 인자로 받는 이유는 **검증을 작업 후 결과 상태 기준으로 통일**하기 위해서입니다. 이 형태가 되기까지의 과정이 [트러블슈팅 2](troubleshooting.md#2-좋아요-개수-경계값이-계속-어긋난-문제--작업-전-상태-vs-작업-후-상태)입니다.

| 호출 위치 | 호출 | 의미 |
| --- | --- | --- |
| 아티스트/공연 좋아요 추가 | `checkMaxLikes(userId)` | 추가 전 개수가 이미 50이면 거부 |
| 공연 좋아요 취소(1건) | `checkMinLikes(userId, 1)` | 1개 지운 뒤 2개 미만이면 거부 |
| 아티스트 좋아요 다건 취소 | `checkMinLikes(userId, n)` | n개 지운 뒤 2개 미만이면 거부 |
| 온보딩 선택 완료 | `checkMinLikes(userId, 0)` | 저장을 마친 현재 상태가 2개 미만이면 거부 |

## API

| Method | 경로 | 설명 |
| --- | --- | --- |
| `POST` | `/api/v1/likes/artists/{artistId}` | 아티스트 좋아요 |
| `POST` | `/api/v1/likes/artists/delete` | 아티스트 좋아요 **다건** 취소 (body: `{ "artistIds": [..] }`) |
| `GET` | `/api/v1/likes/my/bands` | 내가 좋아요한 아티스트 |
| `POST` | `/api/v1/likes/performances/{performanceId}` | 공연 좋아요 |
| `DELETE` | `/api/v1/likes/performances/{performanceId}` | 공연 좋아요 취소 |
| `GET` | `/api/v1/likes/my/concerts` | 내가 좋아요한 공연 |
| `GET` | `/api/v1/likes/my/festivals` | 좋아요한 공연 중 페스티벌(`typeofcon = 2`)만 (손재희) |
| `POST` | `/api/v1/first/select` | 온보딩: 아티스트 여러 개 선택 + GUEST → USER |

## 아티스트 다건 취소 — 전부 아니면 전무

**커밋** `f680e5c`(2025-11-28)

MY BANDS 편집 화면에서 여러 아티스트를 한 번에 해제하는 UX 요구로 `DELETE /artists/{id}` 단건 API를 `POST /artists/delete` + ID 목록으로 바꿨습니다.

```java
int requestSize = artistIds.stream().distinct().toList().size();
likeValidationService.checkMinLikes(userId, requestSize);
long deletedCount = repo.deleteAllByUserUserIdAndArtistIdIn(userId, artistIds);
if (deletedCount != requestSize) throw new CustomException(LIKE_NOT_FOUND);
```

- 요청에 좋아요하지 않은 ID가 섞여 있으면 **실제 삭제 수 ≠ 요청 수**가 됩니다. 이때 예외를 던지면 `@Transactional`이 이미 지운 행까지 롤백하므로, 일부만 지워지는 상태가 생기지 않습니다.
- 중복 ID는 `distinct()`로 한 번만 셉니다. 세지 않으면 `[1, 1]` 요청이 "2개 삭제"로 계산돼 최소 개수 검증과 삭제 수 비교가 모두 틀어집니다.
- **HTTP 메서드 선택**: `DELETE`에 body를 싣는 것은 스펙상 의미가 정의되지 않아 일부 클라이언트·프록시가 body를 버립니다. 그래서 동작을 경로(`/delete`)로 드러내고 `POST`를 썼습니다. REST 관점에서는 어색하다는 트레이드오프가 있습니다.

## 온보딩 선택 — 하나의 트랜잭션

[ArtistSelectService](../src/main/java/com/stageon/stageonwas/domain/artist/service/ArtistSelectService.java)

```java
@Transactional
public void selectArtists(Long userId, List<Long> artistIds) {
    for (Long artistId : artistIds) artistLikeService.likeArtist(userId, artistId);  // 개별 검증 포함
    likeValidationService.checkMinLikes(userId, 0);                                  // 최소 2개
    user.upgradeRoleToUser();                                                        // GUEST → USER
}
```

`likeArtist`도 `@Transactional`이지만 기본 전파(`REQUIRED`)라 바깥 트랜잭션에 참여합니다. 어느 단계에서 예외가 나도 좋아요 저장과 role 승격이 함께 롤백됩니다.

## 응답에서의 좋아요 여부

- 검색 결과의 각 공연·아티스트에 `isLiked`를 붙입니다(`ff5bde1`). 프론트가 하트 상태를 그리려고 별도 API를 부르지 않아도 됩니다 → [search.md](search.md#좋아요-여부-표시)
- MY CONCERTS 응답은 정의상 모두 좋아요한 공연이라 `liked = true`로 고정합니다.

## 알려진 한계

1. **개수 검증과 저장 사이의 경쟁 조건** `[분석]`: `COUNT → INSERT`가 원자적이지 않아, 같은 사용자의 동시 요청 두 개가 모두 49개 상태에서 검사를 통과하면 51개가 될 수 있습니다. 최소 개수도 동시 삭제로 같은 방식으로 깨질 수 있습니다. 사용자 본인 조작이라 빈도가 낮아 현재는 허용하고 있으며, 엄격히 막으려면 사용자 행 비관적 락(`SELECT ... FOR UPDATE`)으로 같은 사용자 요청을 직렬화합니다.
2. **에러 메시지와 실제 정책 불일치**: `LIKE_LIMIT_EXCEEDED` 메시지는 "최대 10개"인데 코드는 50개입니다(`6e65ef8`에서 숫자만 변경). 숫자를 상수 하나로 두고 메시지도 그 상수로 만들어야 재발하지 않습니다.
3. **파생 삭제 쿼리** `[분석]`: `deleteAllBy...In`은 Spring Data가 대상을 SELECT한 뒤 한 건씩 DELETE합니다. 최대 50건이라 문제는 없지만, 대량 삭제라면 `@Modifying` JPQL 벌크 삭제가 낫습니다.
4. **집계 쿼리 2회**: 매 검증마다 아티스트·공연 `COUNT`를 각각 실행합니다. 사용자당 수십 건이라 비용은 작습니다.
