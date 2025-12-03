package com.stageon.stageonwas.domain.alonecon.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotCustomSaveRequest {

    // 선택: pathVariable로도 받으니까 굳이 안 써도 됨
    private String mt20id;

    // 반전 정보들 전체
    private List<SlotCustomItem> invertedSlots;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SlotCustomItem {
        private LocalDate date;
        private Integer stageOrder;
        private String artist;
        private boolean inverted;
    }
}
