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
    private String mt20id; //kopis 공연 구별 ID

    private String prfnm; //공연 제목
    private LocalDate prfpdfrom; //공연 시작일
    private LocalDate prfpdto; //공연 종료일
    private String fcltynm; //공연 장소
    private String prfruntime; //공연 플레이 타임
    private String prfage; //공연 관람 가능 나이

    @Lob
    private String pcseguidance; //공연 좌석별 가격표

    private String poster; //공연 포스터
    private String prfstate; //공연 완료, 예정 상태표
    private String dtguidance; //공연 시작 시간
    private LocalDate tkstdate; //티켓팅 시작일
    private LocalTime tksttime; //티켓팅 종료일
    private Integer typeofcon; //페스티벌, 공연 분류 코드 1이면 단독공연 및 콜라보 공연, 2면 페스티벌


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
    private List<Artpic> styurls; //출연 아티스트

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Artpic { //출연 아티스트 이름 및 사진
        private String relatenm; //이름
        private String relateurl; //사진 URL
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
    private List<Relate> relates; //예매처

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Relate {
        private String relatenm; //예매처 이름
        private String relateurl; //예매처 URL
    }
}
