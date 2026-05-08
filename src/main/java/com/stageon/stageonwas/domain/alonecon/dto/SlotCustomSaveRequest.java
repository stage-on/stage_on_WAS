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


    private String mt20id;

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
