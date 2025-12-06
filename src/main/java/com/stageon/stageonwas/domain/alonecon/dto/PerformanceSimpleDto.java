package com.stageon.stageonwas.domain.alonecon.dto;


import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceSimpleDto {


    private Long id;
    private String mt20id;
    private String prfnm;
    private LocalDate prfpdfrom;
    private LocalDate prfpdto;
    private String fcltynm;
    private String prfruntime;
    private String prfage;
    private String pcseguidance;
    private String poster;
    private String prfstate;
    private String dtguidance;
    private LocalDate tkstdate;
    private LocalTime tksttime;
    private Integer typeofcon;
    private boolean newstate;
    private String locationUrl;


    private List<PerformanceDetail.ArtPic> styurls;
    private List<PerformanceDetail.Relate> relates;


    public PerformanceSimpleDto(PerformanceDetail p) {
        this.id = p.getId();
        this.mt20id = p.getMt20id();
        this.prfnm = p.getPrfnm();
        this.prfpdfrom = p.getPrfpdfrom();
        this.prfpdto = p.getPrfpdto();
        this.fcltynm = p.getFcltynm();
        this.prfruntime = p.getPrfruntime();
        this.prfage = p.getPrfage();
        this.pcseguidance = p.getPcseguidance();
        this.poster = p.getPoster();
        this.prfstate = p.getPrfstate();
        this.dtguidance = p.getDtguidance();
        this.tkstdate = p.getTkstdate();
        this.tksttime = p.getTksttime();
        this.typeofcon = p.getTypeofcon();
        this.styurls = p.getStyurls();
        this.relates = p.getRelates();
        this.newstate = p.isNewstate();
        this.locationUrl = p.getLocationUrl();
    }
}
