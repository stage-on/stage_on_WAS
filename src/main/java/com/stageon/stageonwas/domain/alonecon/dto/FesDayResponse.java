package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class FesDayResponse {

    private LocalDate date;
    private LocalTime open;
    private LocalTime close;

    public static FesDayResponse from(PerformanceDetail.DayInfo day) {
        return new FesDayResponse(day.getDate(), day.getOpen(), day.getClose());
    }
}
