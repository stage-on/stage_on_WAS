package com.stageon.stageonwas.domain.alonecon.api;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceDetailResponse;
import com.stageon.stageonwas.domain.alonecon.dto.SlotCustomSaveRequest;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Festival Timetable", description = "페스티벌 타임테이블 / 커스텀 관련 API")
public interface FestivalTimetableApi {

    // ==================== 1) 내가 커스텀한 페스티벌 목록 ====================
    @Operation(
            summary = "내가 커스텀한 페스티벌 목록 조회",
            description = "유저가 타임테이블을 한 번이라도 커스텀한 페스티벌(performance) 목록을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "커스텀한 페스티벌 목록",
            content = @Content(schema = @Schema(implementation = PerformanceDetail.class))
    )
    ResponseEntity<List<PerformanceDetail>> getMyCustomFestivals(
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // ==================== 2) 로그인 유저 기준 상세 조회 ====================
    @Operation(
            summary = "로그인 유저 기준 페스티벌 상세 + 슬롯 반전 여부 조회",
            description = """
                seed로 저장된 페스티벌 타임테이블(slots)에
                현재 로그인한 유저의 커스텀 정보(색상 반전 여부)가 있으면 합쳐서 반환합니다.
                (커스텀이 없으면 seed 그대로 반환)
                """
    )
    @ApiResponse(
            responseCode = "200",
            description = "상세 정보 + inverted 정보",
            content = @Content(schema = @Schema(implementation = PerformanceDetailResponse.class))
    )
    ResponseEntity<PerformanceDetailResponse> getMyFestivalDetail(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558") String mt20id,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // ==================== 3) 커스텀 저장 ====================
    @Operation(
            summary = "타임테이블 커스텀 저장",
            description = """
                특정 페스티벌(mt20id)에 대해 유저가 선택한 슬롯(색상 반전 여부)을 저장합니다.
                같은 공연에 대해 기존에 저장된 커스텀 데이터는 모두 삭제한 뒤, 새로 저장합니다.
                """
    )
    @ApiResponse(
            responseCode = "200",
            description = "저장 성공 (body 없음)"
    )
    ResponseEntity<Void> saveSlotCustom(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558") String mt20id,
            SlotCustomSaveRequest request,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // ==================== 4) (기존 detail) ====================
    @Operation(
            summary = "페스티벌 상세 + 슬롯 반전 여부 조회",
            description = """
                    seed로 저장된 페스티벌 타임테이블(slots)에  
                    해당 유저의 커스텀 정보(색상 반전 여부)를 합쳐서 반환합니다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "상세 정보 + inverted 정보",
            content = @Content(schema = @Schema(implementation = PerformanceDetailResponse.class))
    )
    ResponseEntity<PerformanceDetailResponse> getFestivalDetail(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558") String mt20id,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );
}
