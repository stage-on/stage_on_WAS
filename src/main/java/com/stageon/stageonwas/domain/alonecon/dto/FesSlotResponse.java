package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class FesSlotResponse {

    private LocalDate date;
    private String stageId;
    private String stageName;
    private Integer stageOrder;
    private String artist;
    private LocalTime start;
    private LocalTime end;
    private Integer minutes;
    private String img;
    private String note;
    private boolean inverted; // 색상 반전 여부

    public static FesSlotResponse from(PerformanceDetail.Slot slot, boolean inverted) {
        return new FesSlotResponse(
                slot.getDate(),
                slot.getStageId(),
                slot.getStageName(),
                slot.getStageOrder(),
                slot.getArtist(),
                slot.getStart(),
                slot.getEnd(),
                slot.getMinutes(),
                slot.getImg(),
                slot.getNote(),
                inverted
        );
    }
}
