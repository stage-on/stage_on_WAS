package com.stageon.stageonwas.domain.artist.api;

import com.stageon.stageonwas.domain.artist.dto.ArtistLikeResDto;
import com.stageon.stageonwas.domain.artist.dto.ArtistSelectReqDto;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Artist Like API", description = "아티스트 좋아요 및 My Band 목록 조회 API")
@RequestMapping("/api/v1/likes")
public interface ArtistLikeApi {

    @Operation(
            summary = "아티스트 좋아요 등록 (My Band 추가)",
            description = "사용자가 특정 아티스트에 좋아요를 등록합니다. (최대 5개 제한)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "좋아요 등록 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "400", description = "유효성 및 비즈니스 조건 실패",
                    content = @Content(examples = {
                            @ExampleObject(name = "좋아요 개수 초과", value = """
                                    { "status": 400, "message": "좋아요는 아티스트와 공연을 합쳐 최대 5개까지만 가능합니다." }
                                    """),
                            @ExampleObject(name = "파라미터 타입 불일치", value = """
                                    { "status": 400, "message": "artistId 파라미터 형식이 올바르지 않습니다." }
                                    """)
                    })
            ),
            @ApiResponse(responseCode = "409", description = "이미 좋아요 누른 상태",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 409, "message": "이미 좋아요를 누른 대상입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "리소스 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 아티스트입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/artists/{artistId}")
    ResponseEntity<String> likeArtist(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long artistId);

    @Operation(
            summary = "아티스트 좋아요 취소 (My Band 삭제)",
            description = "사용자가 등록한 아티스트 좋아요를 취소합니다. (최소 2개 유지)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "좋아요 취소 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "400", description = "최소 개수 미달",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 400, "message": "좋아요는 최소 2개를 유지해야 합니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "좋아요 내역 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "좋아요 내역이 존재하지 않습니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/artists/delete")
    ResponseEntity<String> unlikeArtist(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ArtistSelectReqDto artistSelectReqDto);


    @Operation(
            summary = "My Band 목록 조회",
            description = "현재 사용자가 좋아요를 누른 모든 아티스트 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = ArtistLikeResDto.class),
                            examples = @ExampleObject(value = """
                                    [
                                      {
                                        "artistId": 5,
                                        "artistName": "데이식스 (DAY6)",
                                        "artistPictureUrl": "https://image.example.com/day6.jpg"
                                      },
                                      {
                                        "artistId": 8,
                                        "artistName": "검정치마",
                                        "artistPictureUrl": "https://image.example.com/blackskirts.jpg"
                                      }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "사용자 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 회원입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/my/bands")
    ResponseEntity<List<ArtistLikeResDto>> getMyBands(
            @AuthenticationPrincipal CustomUserDetails userDetails);
}