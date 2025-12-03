package com.stageon.stageonwas.domain.alonecon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performance_detail")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PerformanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // === 공통 기본 정보 ===
    @Column(nullable = false, unique = true)
    private String mt20id;

    private String prfnm;
    private LocalDate prfpdfrom;
    private LocalDate prfpdto;
    private String fcltynm;
    private String prfruntime;
    private String prfage;

    @Lob
    private String pcseguidance;

    private String poster;
    private String prfstate;
    private String dtguidance;

    private LocalDate tkstdate;
    private LocalTime tksttime;
    private Integer typeofcon;
    private boolean newstate;
    @Column(columnDefinition = "TEXT")
    private String locationUrl;


    // ===================================================================
    // 🎫 공연 관련 (일반 Relate / ArtPic)
    // ===================================================================
    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "performance_styurls",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    private List<ArtPic> styurls = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "performance_relates",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    private List<Relate> relates = new ArrayList<>();

    // ===================================================================
    // 🎤 페스티벌 관련
    // ===================================================================

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "fes_days",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    private List<DayInfo> days = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "fes_slots",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    @OrderBy("date ASC, stageOrder ASC, start ASC")
    private List<Slot> slots = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "fes_links",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    private List<Relate> fesLinks = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "fes_artist_pics",
            joinColumns = @JoinColumn(name = "performance_id")
    )
    private List<ArtistPic> artistPics = new ArrayList<>();


    // ===================================================================
    // Embeddable 클래스 (값 타입)
    // ===================================================================

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Relate {
        private String relatenm;
        private String relateurl;
    }

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ArtPic {
        private String relatenm;
        private String relateurl;
    }

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DayInfo {
        @Column(nullable = false)
        private LocalDate date;
        private LocalTime open;
        private LocalTime close;
    }

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Slot {
        @Column(nullable = false)
        private LocalDate date;

        @Column(nullable = false)
        private String stageId;

        @Column(nullable = false)
        private String stageName;

        private Integer stageOrder;

        @Column(nullable = false)
        private String artist;

        @Column(nullable = false)
        private LocalTime start;

        @Column(nullable = false)
        private LocalTime end;

        @Column(nullable = false)
        private Integer minutes;

        private String img;
        private String note;
    }

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ArtistPic {
        private LocalDate date;
        private String relatenm;
        private String url;
    }
}
