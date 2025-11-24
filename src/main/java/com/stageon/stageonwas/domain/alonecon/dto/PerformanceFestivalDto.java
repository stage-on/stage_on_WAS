package com.stageon.stageonwas.domain.alonecon.dto;


import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * typeofcon = 2 (페스티벌) 전용 DTO
 * - 공통 정보 + 공연용 컬렉션 + 페스티벌 상세(days, slots, fesLinks, artistPics)까지 모두 포함
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceFestivalDto {

    // === 공통 기본 정보 ===
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
    // 공연/공통 관련 컬렉션
    private List<PerformanceDetail.ArtPic> styurls;
    private List<PerformanceDetail.Relate> relates;

    // 페스티벌 관련 컬렉션
    private List<PerformanceDetail.DayInfo> days;
    private List<PerformanceDetail.Slot> slots;
    private List<PerformanceDetail.Relate> fesLinks;
    private List<PerformanceDetail.ArtistPic> artistPics;

    // 엔티티 → DTO 변환 생성자
    public PerformanceFestivalDto(PerformanceDetail p) {
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
        this.newstate = p.isNewstate();

        this.styurls = p.getStyurls();
        this.relates = p.getRelates();
        this.days = p.getDays();
        this.slots = p.getSlots();
        this.fesLinks = p.getFesLinks();
        this.artistPics = p.getArtistPics();
    }
}
