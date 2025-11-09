package com.stageon.stageonwas.domain.alonecon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "performance_detail")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PerformanceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK 타깃이 될 컬럼 → 반드시 Unique + NotNull
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
    private Integer typeofcon; //페스티벌, 공연 분류 코드


    /* ===== 소개 이미지: Embeddable 리스트 ===== */
    @ElementCollection
    @CollectionTable(
            name = "performance_styurls",
            joinColumns = @JoinColumn(
                    name = "performance_mt20id",
                    referencedColumnName = "mt20id"
            )
    )
    @AttributeOverrides({
            @AttributeOverride(name = "relatenm",  column = @Column(name = "relatenm")),
            @AttributeOverride(name = "relateurl", column = @Column(name = "relateurl"))
    })
    private List<Artpic> styurls;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Artpic {
        private String relatenm;
        private String relateurl;
    }

    /* ===== 관련 링크: Embeddable 리스트 ===== */
    @ElementCollection
    @CollectionTable(
            name = "performance_relates",
            joinColumns = @JoinColumn(
                    name = "performance_mt20id",
                    referencedColumnName = "mt20id"
            )
    )
    @AttributeOverrides({
            @AttributeOverride(name = "relatenm",  column = @Column(name = "relatenm")),
            @AttributeOverride(name = "relateurl", column = @Column(name = "relateurl"))
    })
    private List<Relate> relates;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Relate {
        private String relatenm;
        private String relateurl;
    }
}
