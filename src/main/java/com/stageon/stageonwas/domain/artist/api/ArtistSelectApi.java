package com.stageon.stageonwas.domain.artist.api;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/api/v1/first")
@Tag(name = "Onboarding API", description = "회원가입 초기 온보딩 관련 API")
public interface ArtistSelectApi {

    @Operation(
            summary = "온보딩 - 관심 아티스트 선택",
            description = "유저가 선택한 관심 아티스트(밴드) 목록을 저장하고 정회원(USER)으로 등급을 올립니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "온보딩 성공",
                    content = @Content(
                            schema = @Schema(implementation = ArtistSelectReqDto.class),
                            examples = @ExampleObject(value = "아티스트 선택 및 회원가입이 완료되었습니다.")
                    )
            ),
            @ApiResponse(responseCode = "400", description = "선택 개수 위반",
                    content = @Content(examples = @ExampleObject(value = """
                            { "status": 400, "message": "관심 아티스트는 최소 2개 이상, 최대 10개 이하로 선택해야 합니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "인증 실패",
                    content = @Content(examples = @ExampleObject(value = """
                            { "status": 404, "message": "존재하지 않는 회원입니다." }
                            """))
            )
    })
    @PostMapping("/select")
    ResponseEntity<String> completeOnboarding(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ArtistSelectReqDto requestDto
    );
}
