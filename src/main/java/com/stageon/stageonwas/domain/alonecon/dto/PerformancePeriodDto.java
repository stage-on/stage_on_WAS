package com.stageon.stageonwas.domain.alonecon.dto;


import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformancePeriodDto {


    private String mt20id;


    private String prfnm;

    private LocalDate prfpdfrom;
    private LocalDate prfpdto;
    private String poster;
    private boolean newstate;
    private String fcltynm;

    public PerformancePeriodDto (PerformanceDetail p) {
        this.mt20id = p.getMt20id();
        this.prfnm = p.getPrfnm();
        this.poster = p.getPoster();
        this.prfpdfrom = p.getPrfpdfrom();
        this.prfpdto = p.getPrfpdto();
        this.newstate = p.isNewstate();
        this.fcltynm = p.getFcltynm();
    }
}
