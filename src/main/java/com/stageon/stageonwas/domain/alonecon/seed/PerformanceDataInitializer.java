package com.stageon.stageonwas.domain.alonecon.seed;


import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail.*;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class PerformanceDataInitializer {

    private final PerformanceDetailRepository repository;
    private final JdbcTemplate jdbcTemplate;

    // ★ ApplicationReadyEvent → PostConstruct 로 변경
    @PostConstruct
    public void init() {

        // ★ 이미 데이터가 있으면 초기화 스킵 (중복 / 에러 방지)
        long count = repository.count();
        if (count > 0) {
            System.out.println("공연 더미데이터가 이미 존재합니다. (총 " + count + "건) 초기화 스킵");
            return;
        }

        // ★ 더 이상 deleteAll / AUTO_INCREMENT reset 하지 않음
        // repository.deleteAll();
        // jdbcTemplate.execute("ALTER TABLE performance_detail AUTO_INCREMENT = 1");

        PerformanceDetail fes = PerformanceDetail.builder()
                .mt20id("PF263558")
                .prfnm("인천펜타포트 락 페스티벌 2025")
                .prfpdfrom(LocalDate.of(2025, 8, 1))
                .prfpdto(LocalDate.of(2025, 8, 3))
                .fcltynm("송도달빛축제공원")
                .prfruntime("33시간")
                .prfage("전체 관람가")
                .pcseguidance("3DAYS 240,000원 / 2DAYS 180,000원 / 1DAY 120,000원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263558_250421_151305.gif")
                .prfstate("공연완료")
                .dtguidance("금요일 ~ 일요일(11:30 ~ 23:30)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();


        /* ============ days ============ */
        fes.setDays(List.of(
                new DayInfo(LocalDate.of(2025, 8, 1), LocalTime.of(10,0), LocalTime.of(23,30)),
                new DayInfo(LocalDate.of(2025, 8, 2), LocalTime.of(10,0), LocalTime.of(23,30)),
                new DayInfo(LocalDate.of(2025, 8, 3), LocalTime.of(10,0), LocalTime.of(23,30))
        ));

        /* ============ slots============ */
        List<Slot> all = new ArrayList<>();

        /* (전체 slot 코드 — 당신이 올린 그대로) */
        /* ---------- 1일차 ---------- */
        LocalDate D1 = LocalDate.of(2025, 8, 1);
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"김뜻돌",
                LocalTime.of(12,0), LocalTime.of(12,40),40,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"리도어",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"너드커넥션",
                LocalTime.of(14,20), LocalTime.of(15,0),40,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"터치드",
                LocalTime.of(15,40), LocalTime.of(16,30),50,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"LITTLE SIMZ",
                LocalTime.of(17,10), LocalTime.of(18,10),60,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"장기하",
                LocalTime.of(19,0), LocalTime.of(20,0),60,null,null));
        all.add(new Slot(D1,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"ASIAN KUNG-FU GENERATION",
                LocalTime.of(21,10), LocalTime.of(22,30),80,null,null));

        // INCHEON
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"드래곤포니",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"더 보울스",
                LocalTime.of(12,40), LocalTime.of(13,10),30,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"QWER",
                LocalTime.of(13,50), LocalTime.of(14,20),30,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"當代電影大師 (Modern Cinema Master)",
                LocalTime.of(15,0), LocalTime.of(15,40),40,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"봉제인간",
                LocalTime.of(16,30), LocalTime.of(17,10),40,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"Tempalay",
                LocalTime.of(18,10), LocalTime.of(19,0),50,null,null));
        all.add(new Slot(D1,"INCHEON","INCHEON STAGE",2,"크라잉넛",
                LocalTime.of(20,0), LocalTime.of(21,0),60,null,null));

        // AIRPORT
        all.add(new Slot(D1,"AIRPORT","INCHEON AIRPORT STAGE",3,"김승주",
                LocalTime.of(12,0), LocalTime.of(12,30),30,null,null));
        all.add(new Slot(D1,"AIRPORT","INCHEON AIRPORT STAGE",3,"HYANG",
                LocalTime.of(13,0), LocalTime.of(13,30),30,null,null));
        all.add(new Slot(D1,"AIRPORT","INCHEON AIRPORT STAGE",3,"캐치더영",
                LocalTime.of(14,0), LocalTime.of(14,30),30,null,null));
        all.add(new Slot(D1,"AIRPORT","INCHEON AIRPORT STAGE",3,"데이네버체인지",
                LocalTime.of(15,10), LocalTime.of(15,40),30,null,null));
        all.add(new Slot(D1,"AIRPORT","INCHEON AIRPORT STAGE",3,"롤링퀴즈",
                LocalTime.of(16,10), LocalTime.of(16,40),30,null,null));

        /* ---------- 2일차 전체 코드 (생략 없이 그대로) ---------- */
        LocalDate D2 = LocalDate.of(2025, 8, 2);

        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"KARDI",
                LocalTime.of(12,0), LocalTime.of(12,40),40,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"갤럭시 익스프레스",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"Omoinotake",
                LocalTime.of(14,30), LocalTime.of(15,10),40,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"ADOY",
                LocalTime.of(15,50), LocalTime.of(16,30),40,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"글렌체크",
                LocalTime.of(17,10), LocalTime.of(18,0),50,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"HYUKOH & SUNSET ROLLERCOASTER",
                LocalTime.of(18,50), LocalTime.of(20,0),70,null,null));
        all.add(new Slot(D2,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"Pulp",
                LocalTime.of(21,0), LocalTime.of(22,30),90,null,null));

        // INCHEON (2일차)
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"다양성",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"BABO",
                LocalTime.of(12,40), LocalTime.of(13,10),30,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"소음발광",
                LocalTime.of(13,50), LocalTime.of(14,30),40,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"단편선 순간들",
                LocalTime.of(15,10), LocalTime.of(15,50),40,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"바이 바이 배드맨",
                LocalTime.of(16,30), LocalTime.of(17,10),40,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"kanekoayano",
                LocalTime.of(18,0), LocalTime.of(18,50),50,null,null));
        all.add(new Slot(D2,"INCHEON","INCHEON STAGE",2,"메써드",
                LocalTime.of(20,0), LocalTime.of(21,0),60,null,null));

        // AIRPORT (2일차)
        all.add(new Slot(D2,"AIRPORT","INCHEON AIRPORT STAGE",3,"creespy",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        all.add(new Slot(D2,"AIRPORT","INCHEON AIRPORT STAGE",3,"비공정",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        all.add(new Slot(D2,"AIRPORT","INCHEON AIRPORT STAGE",3,"오마르와 동방전력",
                LocalTime.of(13,30), LocalTime.of(14,10),40,null,null));
        all.add(new Slot(D2,"AIRPORT","INCHEON AIRPORT STAGE",3,"서울전자음악단",
                LocalTime.of(14,50), LocalTime.of(15,30),40,null,null));
        all.add(new Slot(D2,"AIRPORT","INCHEON AIRPORT STAGE",3,"로단 30",
                LocalTime.of(16,10), LocalTime.of(16,50),40,null,null));


        /* ---------- 3일차 전체 코드 그대로 ---------- */
        LocalDate D3 = LocalDate.of(2025, 8, 3);

        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"윤마치",
                LocalTime.of(12,0), LocalTime.of(12,40),40,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"한로로",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"AUDREY NUNA",
                LocalTime.of(14,30), LocalTime.of(15,10),40,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"Balming Tiger (Band Set)",
                LocalTime.of(15,50), LocalTime.of(16,40),50,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"이승윤",
                LocalTime.of(17,20), LocalTime.of(18,10),50,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"자우림",
                LocalTime.of(19,0), LocalTime.of(20,0),60,null,null));
        all.add(new Slot(D3,"KB","KB KOOKMIN CARD STARSHOP STAGE",1,"BECK",
                LocalTime.of(21,0), LocalTime.of(22,20),80,null,null));

        // INCHEON
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"극동아시아타이거즈",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"송소희",
                LocalTime.of(12,40), LocalTime.of(13,10),30,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"Brandy Senki",
                LocalTime.of(13,50), LocalTime.of(14,30),40,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"LUCY",
                LocalTime.of(15,10), LocalTime.of(15,50),40,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"Touché Amoré",
                LocalTime.of(16,40), LocalTime.of(17,20),40,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"김민규 (델리스파이스, 스위퍼)",
                LocalTime.of(18,10), LocalTime.of(19,0),50,null,null));
        all.add(new Slot(D3,"INCHEON","INCHEON STAGE",2,"3호선 버터플라이",
                LocalTime.of(20,0), LocalTime.of(21,0),60,null,null));

        // AIRPORT
        all.add(new Slot(D3,"AIRPORT","INCHEON AIRPORT STAGE",3,"심아일랜드",
                LocalTime.of(12,0), LocalTime.of(12,30),30,null,null));
        all.add(new Slot(D3,"AIRPORT","INCHEON AIRPORT STAGE",3,"컴파인드 화이트",
                LocalTime.of(13,0), LocalTime.of(13,30),30,null,null));
        all.add(new Slot(D3,"AIRPORT","INCHEON AIRPORT STAGE",3,"데카당",
                LocalTime.of(14,0), LocalTime.of(14,30),30,null,null));
        all.add(new Slot(D3,"AIRPORT","INCHEON AIRPORT STAGE",3,"Milledenials",
                LocalTime.of(15,0), LocalTime.of(15,40),40,null,null));
        all.add(new Slot(D3,"AIRPORT","INCHEON AIRPORT STAGE",3,"밀레나",
                LocalTime.of(16,10), LocalTime.of(16,40),30,null,null));


        fes.setSlots(all);


        /* ============ artistPics ============ */
        fes.setArtistPics(List.of(
                // 1일차
                new ArtistPic(D1,"김뜻돌",""),
                new ArtistPic(D1,"리도어",""),
                new ArtistPic(D1,"너드커넥션",""),
                new ArtistPic(D1,"터치드",""),
                new ArtistPic(D1,"Little Simz",""),
                new ArtistPic(D1,"장기하",""),
                new ArtistPic(D1,"ASIAN KUNG-FU GENERATION",""),

                new ArtistPic(D1,"드래곤 포니",""),
                new ArtistPic(D1,"더 보울스",""),
                new ArtistPic(D1,"QWER",""),
                new ArtistPic(D1,"Modern Cinema Master",""),
                new ArtistPic(D1,"봉제인간",""),
                new ArtistPic(D1,"Tempalay",""),
                new ArtistPic(D1,"크라잉넛",""),

                new ArtistPic(D1,"김승주",""),
                new ArtistPic(D1,"HYANG",""),
                new ArtistPic(D1,"캐치더영",""),
                new ArtistPic(D1,"데이네버체인지",""),
                new ArtistPic(D1,"롤링쿼츠",""),

                // 2일차
                new ArtistPic(D2,"KARDI",""),
                new ArtistPic(D2,"갤럭시 익스프레스",""),
                new ArtistPic(D2,"Omoinotake",""),
                new ArtistPic(D2,"ADOY",""),
                new ArtistPic(D2,"글렌체크",""),
                new ArtistPic(D2,"HYUKOH & SUNSET ROLLERCOASTER",""),
                new ArtistPic(D2,"Pulp",""),

                new ArtistPic(D2,"다양성",""),
                new ArtistPic(D2,"BABO",""),
                new ArtistPic(D2,"소음발광",""),
                new ArtistPic(D2,"단편선 순간들",""),
                new ArtistPic(D2,"바이 바이 배드맨",""),
                new ArtistPic(D2,"kanekoayano",""),
                new ArtistPic(D2,"메써드",""),

                new ArtistPic(D2,"creespy",""),
                new ArtistPic(D2,"비공정",""),
                new ArtistPic(D2,"오마르와 동방전력",""),
                new ArtistPic(D2,"서울전자음악단",""),
                new ArtistPic(D2,"로다운30",""),

                // 3일차
                new ArtistPic(D3,"윤마치",""),
                new ArtistPic(D3,"한로로",""),
                new ArtistPic(D3,"AUDREY NUNA",""),
                new ArtistPic(D3,"Balming Tiger (Band Set)",""),
                new ArtistPic(D3,"이승윤",""),
                new ArtistPic(D3,"자우림",""),
                new ArtistPic(D3,"Beck",""),

                new ArtistPic(D3,"극동아시아타이거즈",""),
                new ArtistPic(D3,"송소희",""),
                new ArtistPic(D3,"Brandy Senki",""),
                new ArtistPic(D3,"LUCY",""),
                new ArtistPic(D3,"Touché Amoré(투셰 아모레)",""),
                new ArtistPic(D3,"김민규(델리스파이스, 스위트피)",""),
                new ArtistPic(D3,"3호선 버터플라이",""),

                new ArtistPic(D3,"심아일랜드",""),
                new ArtistPic(D3,"컨파인드 화이트",""),
                new ArtistPic(D3,"데카당",""),
                new ArtistPic(D3,"Milledenials",""),
                new ArtistPic(D3,"밀레나","")
        ));


        /* ============ 링크 ============ */
        fes.setFesLinks(List.of(
                new Relate("NHN티켓링크","https://www.ticketlink.co.kr/product/56122"),
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1410996"),
                new Relate("엔티켓","https://www.enticket.com:469/pfm/sub01_view.html?pfmIng=1&p_idx=3777"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004977")
        ));

        /* ===== 저장 ===== */
        repository.save(fes);
// ==================== Beautiful Mint Life 2025 ====================
        PerformanceDetail bml = PerformanceDetail.builder()
                .mt20id("PF260433")
                .prfnm("뷰티풀 민트 라이프 BML 2025")
                .prfpdfrom(LocalDate.of(2025, 6, 13))
                .prfpdto(LocalDate.of(2025, 6, 15))
                .fcltynm("올림픽공원 일대")
                .prfruntime("3DAYS")
                .prfage("전체 관람가")
                .pcseguidance("1일권 130,000원, 우선입장권 22,000원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF260433_250306_132204.gif")
                .prfstate("공연완료")
                .dtguidance("금요일 ~ 일요일(12:00 ~ 22:30)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();


        /* ============ days ============ */
        LocalDate bmlD1 = LocalDate.of(2025, 6, 13);
        LocalDate bmlD2 = LocalDate.of(2025, 6, 14);
        LocalDate bmlD3 = LocalDate.of(2025, 6, 15);

        bml.setDays(List.of(
                new DayInfo(bmlD1, LocalTime.of(13,0), LocalTime.of(22,30)),
                new DayInfo(bmlD2, LocalTime.of(12,0), LocalTime.of(22,30)),
                new DayInfo(bmlD3, LocalTime.of(12,0), LocalTime.of(22,30))
        ));

        /* ============ slots ============ */
        List<Slot> bmlall = new ArrayList<>();

        /* ---------- 6/13 (금) ---------- */

// Mint Breeze Stage (88잔디마당)
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"Hi-Fi Un!corn",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"유주",
                LocalTime.of(14,50), LocalTime.of(15,40),50,null,null));
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"오월오일",
                LocalTime.of(16,10), LocalTime.of(17,0),50,null,null));
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"SAM KIM",
                LocalTime.of(17,30), LocalTime.of(18,20),50,null,null));
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"선우정아",
                LocalTime.of(18,50), LocalTime.of(19,50),60,null,null));
        bmlall.add(new Slot(bmlD1,"MINT","Mint Breeze Stage",1,"터치드",
                LocalTime.of(20,30), LocalTime.of(21,30),60,null,null));

// Cafe Blossom House (KSPO DOME)
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"AxMxP",
                LocalTime.of(14,30), LocalTime.of(15,10),40,null,null));
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"글렌체크",
                LocalTime.of(15,40), LocalTime.of(16,30),50,null,null));
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"THE SOLUTIONS",
                LocalTime.of(17,0), LocalTime.of(17,50),50,null,null));
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"QWER",
                LocalTime.of(18,20), LocalTime.of(19,10),50,null,null));
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"이승윤",
                LocalTime.of(19,40), LocalTime.of(20,40),60,null,null));
        bmlall.add(new Slot(bmlD1,"CAFE","Cafe Blossom House",2,"YB",
                LocalTime.of(21,20), LocalTime.of(22,20),60,null,null));

// Loving Forest Garden (88숲속무대)
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"orange flavored cigarettes",
                LocalTime.of(14,10), LocalTime.of(14,50),40,null,null));
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"김승주",
                LocalTime.of(15,20), LocalTime.of(16,0),40,null,null));
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"DASUTT",
                LocalTime.of(16,30), LocalTime.of(17,20),50,null,null));
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"황가람",
                LocalTime.of(17,50), LocalTime.of(18,40),50,null,null));
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"옥상달빛",
                LocalTime.of(19,10), LocalTime.of(20,10),60,null,null));
        bmlall.add(new Slot(bmlD1,"FOREST","Loving Forest Garden",3,"하동균",
                LocalTime.of(20,40), LocalTime.of(21,40),60,null,null));

        /* ---------- 6/14 (토) ---------- */

// Mint Breeze
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"드래곤 포니",
                LocalTime.of(13,0), LocalTime.of(13,40),40,null,null));
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"방예담",
                LocalTime.of(14,10), LocalTime.of(15,0),50,null,null));
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"소수빈",
                LocalTime.of(15,40), LocalTime.of(16,30),50,null,null));
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"소란",
                LocalTime.of(17,0), LocalTime.of(18,0),60,null,null));
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"하현상",
                LocalTime.of(18,40), LocalTime.of(19,40),60,null,null));
        bmlall.add(new Slot(bmlD2,"MINT","Mint Breeze Stage",1,"정승환",
                LocalTime.of(20,20), LocalTime.of(21,20),60,null,null));

// Cafe Blossom
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"O.O.O",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"리도어",
                LocalTime.of(15,0), LocalTime.of(15,50),50,null,null));
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"너드커넥션",
                LocalTime.of(16,30), LocalTime.of(17,20),50,null,null));
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"페퍼톤스",
                LocalTime.of(18,0), LocalTime.of(18,50),50,null,null));
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"N.Flying",
                LocalTime.of(19,30), LocalTime.of(20,30),60,null,null));
        bmlall.add(new Slot(bmlD2,"CAFE","Cafe Blossom House",2,"실리카겔",
                LocalTime.of(21,10), LocalTime.of(22,10),60,null,null));

// Forest
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"OWAVE",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"이강승",
                LocalTime.of(14,50), LocalTime.of(15,30),40,null,null));
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"까치산",
                LocalTime.of(16,0), LocalTime.of(16,50),50,null,null));
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"dori",
                LocalTime.of(17,30), LocalTime.of(18,20),50,null,null));
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"권순관",
                LocalTime.of(19,0), LocalTime.of(20,0),60,null,null));
        bmlall.add(new Slot(bmlD2,"FOREST","Loving Forest Garden",3,"george",
                LocalTime.of(20,40), LocalTime.of(21,40),60,null,null));

        /* ---------- 6/15 (일) ---------- */

// Mint Breeze
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"우석",
                LocalTime.of(13,0), LocalTime.of(13,40),40,null,null));
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"한로로",
                LocalTime.of(14,10), LocalTime.of(15,0),50,null,null));
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"유다빈밴드",
                LocalTime.of(15,40), LocalTime.of(16,30),50,null,null));
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"로이킴",
                LocalTime.of(17,10), LocalTime.of(18,0),50,null,null));
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"김성규",
                LocalTime.of(18,40), LocalTime.of(19,40),60,null,null));
        bmlall.add(new Slot(bmlD3,"MINT","Mint Breeze Stage",1,"윤하",
                LocalTime.of(20,20), LocalTime.of(21,20),60,null,null));

// Cafe Blossom
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"구원찬",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"Colde",
                LocalTime.of(15,0), LocalTime.of(15,50),50,null,null));
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"적재",
                LocalTime.of(16,30), LocalTime.of(17,20),50,null,null));
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"이석훈",
                LocalTime.of(18,0), LocalTime.of(18,50),50,null,null));
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"10CM",
                LocalTime.of(19,30), LocalTime.of(20,30),60,null,null));
        bmlall.add(new Slot(bmlD3,"CAFE","Cafe Blossom House",2,"다비치",
                LocalTime.of(21,10), LocalTime.of(22,10),60,null,null));

// Forest
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"Dept",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"연정",
                LocalTime.of(14,50), LocalTime.of(15,30),40,null,null));
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"안다영",
                LocalTime.of(16,0), LocalTime.of(16,50),50,null,null));
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"오존",
                LocalTime.of(17,30), LocalTime.of(18,20),50,null,null));
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"브로콜리너마저",
                LocalTime.of(19,0), LocalTime.of(20,0),60,null,null));
        bmlall.add(new Slot(bmlD3,"FOREST","Loving Forest Garden",3,"데이먼스 이어",
                LocalTime.of(20,40), LocalTime.of(21,40),60,null,null));

        bml.setSlots(bmlall);

        /* ============ artistPics ============ */
        bml.setArtistPics(List.of(

                /* ========= 6/13 (금) ========= */
                new ArtistPic(bmlD1, "Hi-Fi Un!corn", ""),
                new ArtistPic(bmlD1, "유주", ""),
                new ArtistPic(bmlD1, "오월오일", ""),
                new ArtistPic(bmlD1, "SAM KIM", ""),
                new ArtistPic(bmlD1, "선우정아", ""),
                new ArtistPic(bmlD1, "터치드", ""),


                new ArtistPic(bmlD1, "AxMxP(with 이승협, 서상욱 of N.Flying)", ""),
                new ArtistPic(bmlD1, "Glen Check", ""),
                new ArtistPic(bmlD1, "THE SOLUTIONS", ""),
                new ArtistPic(bmlD1, "QWER", ""),
                new ArtistPic(bmlD1, "이승윤", ""),
                new ArtistPic(bmlD1, "YB", ""),

                new ArtistPic(bmlD1, "orange flavored cigarettes", ""),
                new ArtistPic(bmlD1, "김승주", ""),
                new ArtistPic(bmlD1, "DASUTT", ""),
                new ArtistPic(bmlD1, "황가람", ""),
                new ArtistPic(bmlD1, "옥상달빛", ""),
                new ArtistPic(bmlD1, "하동균", ""),


                /* ========= 6/14 (토) ========= */
                new ArtistPic(bmlD2, "드래곤 포니", ""),
                new ArtistPic(bmlD2, "방예담", ""),
                new ArtistPic(bmlD2, "소수빈", ""),
                new ArtistPic(bmlD2, "소란", ""),
                new ArtistPic(bmlD2, "하현상", ""),
                new ArtistPic(bmlD2, "정승환", ""),

                new ArtistPic(bmlD2, "O.O.O", ""),
                new ArtistPic(bmlD2, "리도어", ""),
                new ArtistPic(bmlD2, "너드커넥션", ""),
                new ArtistPic(bmlD2, "페퍼톤스", ""),
                new ArtistPic(bmlD2, "N.Flying", ""),
                new ArtistPic(bmlD2, "실리카겔", ""),

                new ArtistPic(bmlD2, "OWAVE", ""),
                new ArtistPic(bmlD2, "이강승", ""),
                new ArtistPic(bmlD2, "까치산", ""),
                new ArtistPic(bmlD2, "dori", ""),
                new ArtistPic(bmlD2, "권순관", ""),
                new ArtistPic(bmlD2, "george", ""),


                /* ========= 6/15 (일) ========= */
                new ArtistPic(bmlD3, "우석", ""),
                new ArtistPic(bmlD3, "한로로", ""),
                new ArtistPic(bmlD3, "유다빈밴드", ""),
                new ArtistPic(bmlD3, "로이킴", ""),
                new ArtistPic(bmlD3, "김성규", ""),
                new ArtistPic(bmlD3, "윤하", ""),

                new ArtistPic(bmlD3, "구원찬", ""),
                new ArtistPic(bmlD3, "Colde", ""),
                new ArtistPic(bmlD3, "적재", ""),
                new ArtistPic(bmlD3, "이석훈", ""),
                new ArtistPic(bmlD3, "10CM", ""),
                new ArtistPic(bmlD3, "다비치", ""),

                new ArtistPic(bmlD3, "Dept", ""),
                new ArtistPic(bmlD3, "연정", ""),
                new ArtistPic(bmlD3, "안다영", ""),
                new ArtistPic(bmlD3, "오존", ""),
                new ArtistPic(bmlD3, "브로콜리너마저", ""),
                new ArtistPic(bmlD3, "데이먼스 이어", "")
        ));

        /* ============ 링크 ============ */
        bml.setFesLinks(List.of(
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1385883"),
                new Relate("예스24","http://ticket.yes24.com/Special/52978"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002850")
        ));

        /* ===== 저장 ===== */
        repository.save(bml);

        PerformanceDetail dmz = PerformanceDetail.builder()
                .mt20id("PF259515")
                .prfnm("DMZ 피스트레인 뮤직 페스티벌")
                .prfpdfrom(LocalDate.of(2025, 6, 14))   // 페스티벌 기간 자체는 13~15로 유지
                .prfpdto(LocalDate.of(2025, 6, 15))
                .fcltynm("고석정 (고석정)")
                .prfruntime("9시간")
                .prfage("전체 관람가")
                .pcseguidance("블라인드 티켓 전일권 114,400원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF259515_250221_105239.jpg")
                .prfstate("공연완료")
                .dtguidance("금요일(13:00), 토요일 ~ 일요일(13:00)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();


        /* ============ days (6/14 · 6/15만) ============ */
        LocalDate dmzD1 = LocalDate.of(2025, 6, 14); // 토
        LocalDate dmzD2 = LocalDate.of(2025, 6, 15); // 일

// 안내 기준으로 13:00 ~ 22:00 정도로 세팅 (원하면 여기 시간만 나중에 조정해도 됨)
        dmz.setDays(List.of(
                new DayInfo(dmzD1, LocalTime.of(13, 0), LocalTime.of(24, 0)),
                new DayInfo(dmzD2, LocalTime.of(13, 0), LocalTime.of(22, 0))
        ));


        /* ============ slots ============ */
        List<Slot> dmzAll = new ArrayList<>();

        /* ---------- 6/14 (토) ---------- */
// LAND STAGE
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"단편선순간들",
                LocalTime.of(15,0), LocalTime.of(15,40),40,null,null));
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"초록불꽃소년단",
                LocalTime.of(16,30), LocalTime.of(17,10),40,null,null));
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"구남과여라이딩스텔라",
                LocalTime.of(18,0), LocalTime.of(18,40),40,null,null));
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"MINAMI DEUTSGH",
                LocalTime.of(19,30), LocalTime.of(20,10),40,null,null));
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"바보",
                LocalTime.of(21,0), LocalTime.of(21,40),40,null,null));
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"TELEPOPMUSIK (DJ SET)",
                LocalTime.of(22,40), LocalTime.of(23,30),50,null,null));
// 포스터상 24:20–25:10 → 내부적으로 00:20–01:10으로 저장 (D+1 새벽)
        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"HITECH",
                LocalTime.of(0,20), LocalTime.of(1,10),50,null,null));

// PEACE STAGE
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"사뮈",
                LocalTime.of(15,45), LocalTime.of(16,25),40,null,null));
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"THE CHAIRS",
                LocalTime.of(17,15), LocalTime.of(17,55),40,null,null));
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"수민",
                LocalTime.of(18,45), LocalTime.of(19,25),40,null,null));
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"김현철",
                LocalTime.of(20,15), LocalTime.of(20,55),40,null,null));
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"김민규",
                LocalTime.of(21,45), LocalTime.of(22,35),50,null,null));
        dmzAll.add(new Slot(dmzD1,"PEACE","Peace Stage",2,"KO SHIN MOON",
                LocalTime.of(23,55),LocalTime.of(24,15),20,null,null));


        /* ---------- 6/15 (일) ---------- */
// LAND STAGE
        dmzAll.add(new Slot(dmzD2,"LAND","Land Stage",1,"놀이도감",
                LocalTime.of(14,45), LocalTime.of(15,25),40,null,null));
        dmzAll.add(new Slot(dmzD2,"LAND","Land Stage",1,"TENDOUJI",
                LocalTime.of(16,15), LocalTime.of(16,55),40,null,null));
        dmzAll.add(new Slot(dmzD2,"LAND","Land Stage",1,"김뜻돌",
                LocalTime.of(17,45), LocalTime.of(18,25),40,null,null));
        dmzAll.add(new Slot(dmzD2,"LAND","Land Stage",1,"LAMBRINI GIRLS",
                LocalTime.of(19,15), LocalTime.of(19,55),40,null,null));
        dmzAll.add(new Slot(dmzD2,"LAND","Land Stage",1,"JAPANESE BREAKFAST",
                LocalTime.of(20,55), LocalTime.of(21,45),50,null,null));

// PEACE STAGE
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"크리스피",
                LocalTime.of(14,0), LocalTime.of(14,40),40,null,null));
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"주영",
                LocalTime.of(15,30), LocalTime.of(16,10),40,null,null));
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"ALI",
                LocalTime.of(17,0), LocalTime.of(17,40),40,null,null));
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"지소쿠리클럽",
                LocalTime.of(18,30), LocalTime.of(19,10),40,null,null));
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"와와와",
                LocalTime.of(20,0), LocalTime.of(20,50),50,null,null));
        dmzAll.add(new Slot(dmzD2,"PEACE","Peace Stage",2,"사랑과 평화",
                LocalTime.of(21,50), LocalTime.of(22,30),40,null,null));

        dmz.setSlots(dmzAll);


        /* ============ artistPics ============ */
        dmz.setArtistPics(List.of(
                /* ========= 6/14 (토) ========= */
                new ArtistPic(dmzD1, "단편선순간들", ""),
                new ArtistPic(dmzD1, "초록불꽃소년단", ""),
                new ArtistPic(dmzD1, "구남과여라이딩스텔라", ""),
                new ArtistPic(dmzD1, "MINAMI DEUTSGH", ""),
                new ArtistPic(dmzD1, "바보", ""),
                new ArtistPic(dmzD1, "TELEPOPMUSIK (DJ SET)", ""),
                new ArtistPic(dmzD1, "HITECH", ""),

                new ArtistPic(dmzD1, "사뮈", ""),
                new ArtistPic(dmzD1, "THE CHAIRS", ""),
                new ArtistPic(dmzD1, "수민", ""),
                new ArtistPic(dmzD1, "김현철", ""),
                new ArtistPic(dmzD1, "김민규", ""),
                new ArtistPic(dmzD1, "KO SHIN MOON", ""),

                /* ========= 6/15 (일) ========= */
                new ArtistPic(dmzD2, "놀이도감", ""),
                new ArtistPic(dmzD2, "TENDOUJI", ""),
                new ArtistPic(dmzD2, "김뜻돌", ""),
                new ArtistPic(dmzD2, "LAMBRINI GIRLS", ""),
                new ArtistPic(dmzD2, "JAPANESE BREAKFAST", ""),

                new ArtistPic(dmzD2, "크리스피", ""),
                new ArtistPic(dmzD2, "주영", ""),
                new ArtistPic(dmzD2, "ALI", ""),
                new ArtistPic(dmzD2, "지소쿠리클럽", ""),
                new ArtistPic(dmzD2, "와와와", ""),
                new ArtistPic(dmzD2, "사랑과 평화", "")
        ));

        /* ============ 링크 ============ */
        dmz.setFesLinks(List.of(
                new Relate("NHN티켓링크","http://www.ticketlink.co.kr/product/55737"),
                new Relate("멜론티켓","https://ticket.melon.com/performance/index.htm?prodId=211065")
        ));

        /* ===== 저장 ===== */
        repository.save(dmz);
        // ================= ASIAN POP FESTIVAL 2025 =================

        PerformanceDetail apf = PerformanceDetail.builder()
                .mt20id("PF264505")
                .prfnm("아시안 팝 페스티벌 (ASIAN POP FESTIVAL)")
                .prfpdfrom(LocalDate.of(2025, 6, 21))
                .prfpdto(LocalDate.of(2025, 6, 22))
                .fcltynm("파라다이스시티 (컬처파크 (야외))")
                .prfruntime("11시간")
                .prfage("전체 관람가")
                .pcseguidance("양일권 176,000원, 일일권 110,000원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF264505_250509_095705.gif")
                .prfstate("공연완료")
                .dtguidance("토요일 ~ 일요일(12:00)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();

        /* ============ days ============ */
        LocalDate apfD1 = LocalDate.of(2025, 6, 21);
        LocalDate apfD2 = LocalDate.of(2025, 6, 22);

        apf.setDays(List.of(
                new DayInfo(apfD1, LocalTime.of(11, 0), LocalTime.of(23, 0)),
                new DayInfo(apfD2, LocalTime.of(11, 0), LocalTime.of(23, 0))
        ));

        /* ============ slots ============ */
        List<Slot> apfall = new ArrayList<>();

        /* ---------- 6/21 (토) ---------- */
// Paradise Stage
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"킹스턴 루디스카",
                LocalTime.of(12,40), LocalTime.of(13,20),40,null,null));
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"DOUDOU",
                LocalTime.of(14,0), LocalTime.of(14,45),45,null,null));
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"갤럭시 익스프레스",
                LocalTime.of(15,50), LocalTime.of(16,15),25,null,null));
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"MONO NO AWARE",
                LocalTime.of(17,0), LocalTime.of(17,45),45,null,null));
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"The Volunteers",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        apfall.add(new Slot(apfD1,"PARA","Paradise Stage",1,"실리카겔",
                LocalTime.of(20,20), LocalTime.of(21,20),60,null,null));

// City Stage
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"팔칠댄스",
                LocalTime.of(12,0), LocalTime.of(12,40),40,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"GDJYB",
                LocalTime.of(13,20), LocalTime.of(14,0),40,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"할로우잰",
                LocalTime.of(14,45), LocalTime.of(15,30),45,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"LOMBA SIHIR",
                LocalTime.of(16,15), LocalTime.of(17,0),45,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"MEI EHARA'",
                LocalTime.of(17,45), LocalTime.of(18,30),45,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"EGO-WRAPPIN'",
                LocalTime.of(19,20), LocalTime.of(20,20),60,null,null));
        apfall.add(new Slot(apfD1,"CITY","City Stage",2,"새소년",
                LocalTime.of(21,50), LocalTime.of(22,50),60,null,null));

// Rubik Stage
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"김푸름",
                LocalTime.of(12,20), LocalTime.of(13,0),40,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"PHOEBE RINGS",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"ENNO CHENG",
                LocalTime.of(15,0), LocalTime.of(15,45),45,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"신인류",
                LocalTime.of(16,50), LocalTime.of(17,15),25,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"다브다",
                LocalTime.of(18,0), LocalTime.of(18,45),45,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"HAKU.",
                LocalTime.of(19,30), LocalTime.of(20,15),45,null,null));
        apfall.add(new Slot(apfD1,"RUBIK","Rubik Stage",3,"솔루션스",
                LocalTime.of(21,40), LocalTime.of(22,10),30,null,null));

// Chroma Stage
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"팻햄스터 & 캉누",
                LocalTime.of(17,0), LocalTime.of(17,45),45,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"TOFUBEATS",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"키라라",
                LocalTime.of(20,0), LocalTime.of(20,50),50,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"이디오테잎",
                LocalTime.of(21,50), LocalTime.of(22,50),60,null,null));

        /* ---------- 6/22 (일) ---------- */
// Paradise Stage
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"아디오스 오디오",
                LocalTime.of(12,40), LocalTime.of(13,20),40,null,null));
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"SUNKISSED LOLA",
                LocalTime.of(14,0), LocalTime.of(14,45),45,null,null));
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"너드커넥션",
                LocalTime.of(15,30), LocalTime.of(16,15),45,null,null));
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"YOGEE NEW WAVES",
                LocalTime.of(17,0), LocalTime.of(17,45),45,null,null));
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"이승윤",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        apfall.add(new Slot(apfD2,"PARA","Paradise Stage",1,"자우림",
                LocalTime.of(20,20), LocalTime.of(21,20),60,null,null));

// City Stage
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"극동아시아타이거즈",
                LocalTime.of(12,0), LocalTime.of(12,40),40,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"시라카미 우즈",
                LocalTime.of(13,20), LocalTime.of(14,0),40,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"HIPERSON",
                LocalTime.of(14,45), LocalTime.of(15,30),45,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"백현진",
                LocalTime.of(16,15), LocalTime.of(17,0),45,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"BIALYSTOCKS",
                LocalTime.of(17,45), LocalTime.of(18,30),45,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"LAMP",
                LocalTime.of(19,20), LocalTime.of(20,20),60,null,null));
        apfall.add(new Slot(apfD2,"CITY","City Stage",2,"장기하",
                LocalTime.of(21,30), LocalTime.of(22,50),80,null,null));

// Rubik Stage
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"고고학",
                LocalTime.of(12,20), LocalTime.of(13,0),40,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"지윤해",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"더 보울스",
                LocalTime.of(15,0), LocalTime.of(15,45),45,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"MINDFREAKKK",
                LocalTime.of(16,30), LocalTime.of(17,15),45,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"허회경",
                LocalTime.of(18,0), LocalTime.of(18,45),45,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"원디시티",
                LocalTime.of(19,30), LocalTime.of(20,15),45,null,null));
        apfall.add(new Slot(apfD2,"RUBIK","Rubik Stage",3,"데이먼스 이어",
                LocalTime.of(21,10), LocalTime.of(22,10),60,null,null));

// Chroma Stage
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"와이2케이92",
                LocalTime.of(16,0), LocalTime.of(16,40),40,null,null));
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"힙노시스테라피",
                LocalTime.of(17,10), LocalTime.of(17,50),40,null,null));
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"마인드 컴바인드",
                LocalTime.of(18,20), LocalTime.of(19,0),40,null,null));
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"J-TONG",
                LocalTime.of(19,30), LocalTime.of(20,10),40,null,null));
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"엠씨 스나이퍼",
                LocalTime.of(20,40), LocalTime.of(21,20),40,null,null));
        apfall.add(new Slot(apfD2,"CHROMA","Chroma Stage",4,"가리온",
                LocalTime.of(21,50), LocalTime.of(22,50),60,null,null));

        apf.setSlots(apfall);

        /* ============ artistPics ============ */
        apf.setArtistPics(List.of(
                // 6/21
                new ArtistPic(apfD1, "킹스턴 루디스카", ""),
                new ArtistPic(apfD1, "DOUDOU", ""),
                new ArtistPic(apfD1, "갤럭시 익스프레스", ""),
                new ArtistPic(apfD1, "MONO NO AWARE", ""),
                new ArtistPic(apfD1, "더 발룬티어스", ""),
                new ArtistPic(apfD1, "실리카겔", ""),

                new ArtistPic(apfD1, "팔칠댄스", ""),
                new ArtistPic(apfD1, "GDJYB", ""),
                new ArtistPic(apfD1, "할로우잼", ""),
                new ArtistPic(apfD1, "LOMBA SIHIR", ""),
                new ArtistPic(apfD1, "MEI EHARA", ""),
                new ArtistPic(apfD1, "EGO-WRAPPIN''", ""),
                new ArtistPic(apfD1, "새소년", ""),

                new ArtistPic(apfD1, "김푸름", ""),
                new ArtistPic(apfD1, "PHOEBE RINGS", ""),
                new ArtistPic(apfD1, "ENNO CHENG", ""),
                new ArtistPic(apfD1, "신인류", ""),
                new ArtistPic(apfD1, "다보다", ""),
                new ArtistPic(apfD1, "HAKU.", ""),
                new ArtistPic(apfD1, "솔루션스", ""),

                new ArtistPic(apfD1, "팻햄스터 & 칸누", ""),
                new ArtistPic(apfD1, "TOFUBEATS", ""),
                new ArtistPic(apfD1, "키라라", ""),
                new ArtistPic(apfD1, "이디오테잎", ""),

                // 6/22
                new ArtistPic(apfD2, "아디오스 오디오", ""),
                new ArtistPic(apfD2, "SUNKISSED LOLA", ""),
                new ArtistPic(apfD2, "너드커넥션", ""),
                new ArtistPic(apfD2, "YOGEE NEW WAVES", ""),
                new ArtistPic(apfD2, "이승윤", ""),
                new ArtistPic(apfD2, "자우림", ""),

                new ArtistPic(apfD2, "극동아시아타이거즈", ""),
                new ArtistPic(apfD2, "시라카미 우즈", ""),
                new ArtistPic(apfD2, "HIPERSON", ""),
                new ArtistPic(apfD2, "백현진", ""),
                new ArtistPic(apfD2, "BIALYSTOCKS", ""),
                new ArtistPic(apfD2, "LAMP", ""),
                new ArtistPic(apfD2, "장기하", ""),

                new ArtistPic(apfD2, "고고학", ""),
                new ArtistPic(apfD2, "지윤해", ""),
                new ArtistPic(apfD2, "더 보울스", ""),
                new ArtistPic(apfD2, "MINDFREAKKK", ""),
                new ArtistPic(apfD2, "허회경", ""),
                new ArtistPic(apfD2, "원디시티", ""),
                new ArtistPic(apfD2, "데이먼스 이어", ""),

                new ArtistPic(apfD2, "와이2케이92", ""),
                new ArtistPic(apfD2, "힙노시스테라피", ""),
                new ArtistPic(apfD2, "마인드 컴바인드", ""),
                new ArtistPic(apfD2, "J-TONG", ""),
                new ArtistPic(apfD2, "엠씨 스나이퍼", ""),
                new ArtistPic(apfD2, "가리온", "")
        ));

        /* ============ 링크 ============ */
        apf.setFesLinks(List.of(
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002190")
        ));

        /* ===== 저장 ===== */
        repository.save(apf);

        PerformanceDetail gmf = PerformanceDetail.builder()
                .mt20id("PF269741")
                .prfnm("그랜드 민트 페스티벌 2025")
                .prfpdfrom(LocalDate.of(2025, 10, 18))
                .prfpdto(LocalDate.of(2025, 10, 19))
                .fcltynm("올림픽공원 (일대)")
                .prfruntime("2DAYS")
                .prfage("전체 관람가")
                .pcseguidance("1일권 140,000원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF269741_250717_141314.gif")
                .prfstate("공연완료")
                .dtguidance("토요일 ~ 일요일(12:00)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();


        /* ============ days ============ */
        LocalDate gmfD1 = LocalDate.of(2025, 10, 18);
        LocalDate gmfD2 = LocalDate.of(2025, 10, 19);

        gmf.setDays(List.of(
                new DayInfo(gmfD1, LocalTime.of(12, 0), LocalTime.of(22, 0)),
                new DayInfo(gmfD2, LocalTime.of(12, 0), LocalTime.of(22, 0))
        ));

        /* ============ slots ============ */
        List<Slot> gmfAll = new ArrayList<>();

        /* ---------- 10/18 (토) ---------- */

        /* Mint Breeze Stage (88잔디마당) */
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"까치산",
                LocalTime.of(12,50), LocalTime.of(13,30),40,null,null));
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"george",
                LocalTime.of(14,0), LocalTime.of(14,50),50,null,null));
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"폴킴",
                LocalTime.of(15,30), LocalTime.of(16,20),50,null,null));
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"정승환",
                LocalTime.of(17,0), LocalTime.of(18,0),60,null,null));
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"적재",
                LocalTime.of(18,40), LocalTime.of(19,40),60,null,null));
        gmfAll.add(new Slot(gmfD1,"MINT","Mint Breeze Stage",1,"AKMU",
                LocalTime.of(20,20), LocalTime.of(21,20),60,null,null)); // 종료시간은 60분 기준 가정

        /* Club Midnight Sunset (KSPO DOME) */
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"can’t be blue",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"김뜻돌",
                LocalTime.of(14,50), LocalTime.of(15,40),50,null,null));
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"유다빈밴드",
                LocalTime.of(16,20), LocalTime.of(17,10),50,null,null));
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"데이브레이크",
                LocalTime.of(17,50), LocalTime.of(18,50),60,null,null));
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"터치드",
                LocalTime.of(19,30), LocalTime.of(20,30),60,null,null));
        gmfAll.add(new Slot(gmfD1,"CLUB","Club Midnight Sunset",2,"LUCY",
                LocalTime.of(21,10), LocalTime.of(22,0),50,null,null)); // 종료시간 50분 가정

        /* Station Stardust by CDF */
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"KIK",
                LocalTime.of(12,30), LocalTime.of(13,10),40,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"리도어",
                LocalTime.of(13,40), LocalTime.of(14,30),50,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"TELEVISION OFF",
                LocalTime.of(15,10), LocalTime.of(16,0),50,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"지소쿠리클럽",
                LocalTime.of(16,30), LocalTime.of(17,20),50,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"오월오일",
                LocalTime.of(17,50), LocalTime.of(18,40),50,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"페퍼톤스",
                LocalTime.of(19,10), LocalTime.of(20,10),60,null,null));
        gmfAll.add(new Slot(gmfD1,"STATION","Station Stardust by CDF",3,"실리카겔",
                LocalTime.of(20,50), LocalTime.of(21,40),50,null,null)); // 종료시간 50분 가정

        /* Loving Forest Garden (88숲 수변무대) */
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"우예린",
                LocalTime.of(13,20), LocalTime.of(14,0),40,null,null));
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"GEMINI",
                LocalTime.of(14,30), LocalTime.of(15,10),40,null,null));
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"김수영",
                LocalTime.of(15,40), LocalTime.of(16,30),50,null,null));
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"PAMUNGKAS",
                LocalTime.of(17,10), LocalTime.of(18,0),50,null,null));
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"정세운",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        gmfAll.add(new Slot(gmfD1,"FOREST","Loving Forest Garden",4,"소수빈",
                LocalTime.of(20,10), LocalTime.of(21,10),60,null,null));
        /* bright Lab. (한얼광장 – 무료 프로그램) */
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"키스누",
                LocalTime.of(14,30), LocalTime.of(15,0),30,null,null));
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"evenif",
                LocalTime.of(15,10), LocalTime.of(16,0),50,null,null));
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"컨파인드 화이트",
                LocalTime.of(16,30), LocalTime.of(17,0),30,null,null));
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"민서",
                LocalTime.of(17,10), LocalTime.of(17,40),30,null,null));
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"이준형",
                LocalTime.of(18,0), LocalTime.of(19,0),60,null,null));
        gmfAll.add(new Slot(gmfD1,"LAB","bright Lab.",5,"Rolling Quartz",
                LocalTime.of(19,10), LocalTime.of(20,0),50,null,null)); // 종료시간 가정


        /* ---------- 10/19 (일) ---------- */

        /* Mint Breeze Stage */
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"GOGOHAWK",
                LocalTime.of(12,50), LocalTime.of(13,30),40,null,null));
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"데이먼스 이어",
                LocalTime.of(14,0), LocalTime.of(14,50),50,null,null));
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"하동균",
                LocalTime.of(15,30), LocalTime.of(16,20),50,null,null));
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"멜로망스",
                LocalTime.of(17,20), LocalTime.of(18,20),60,null,null));
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"10CM",
                LocalTime.of(18,40), LocalTime.of(19,40),60,null,null));
        gmfAll.add(new Slot(gmfD2,"MINT","Mint Breeze Stage",1,"홍이삭",
                LocalTime.of(20,20), LocalTime.of(21,10),50,null,null)); // 종료시간 가정

        /* Club Midnight Sunset */
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"Hi-Fi Unicorn",
                LocalTime.of(12,30), LocalTime.of(13,10),40,null,null)); // 40분 기준 분할 가정
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"원위",
                LocalTime.of(14,30), LocalTime.of(15,20),50,null,null));
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"카더가든",
                LocalTime.of(16,0), LocalTime.of(16,50),50,null,null));
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"소란",
                LocalTime.of(17,30), LocalTime.of(18,30),60,null,null));
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"CNBLUE",
                LocalTime.of(19,10), LocalTime.of(20,10),60,null,null));
        gmfAll.add(new Slot(gmfD2,"CLUB","Club Midnight Sunset",2,"윤하",
                LocalTime.of(20,50), LocalTime.of(21,40),50,null,null)); // 종료시간 50분 가정

        /* Station Stardust by CDF */
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"LOW HIGH LOW",
                LocalTime.of(12,30), LocalTime.of(13,10),40,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"SNAKE CHICKEN SOUP",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"Wendy Wander",
                LocalTime.of(15,10), LocalTime.of(16,0),50,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"THE SOLUTIONS",
                LocalTime.of(16,30), LocalTime.of(17,20),50,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"드래곤 포니",
                LocalTime.of(17,50), LocalTime.of(18,40),50,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"쏜애플",
                LocalTime.of(19,20), LocalTime.of(20,20),60,null,null));
        gmfAll.add(new Slot(gmfD2,"STATION","Station Stardust by CDF",3,"N.Flying",
                LocalTime.of(21,0), LocalTime.of(22,0),60,null,null)); // 마지막 슬롯 60분 가정

        /* Loving Forest Garden */
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"OurR",
                LocalTime.of(12,30), LocalTime.of(13,10),40,null,null));
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"KEN",
                LocalTime.of(13,40), LocalTime.of(14,20),40,null,null));
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"범진",
                LocalTime.of(15,0), LocalTime.of(15,50),50,null,null));
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"Michael Kaneko",
                LocalTime.of(17,30), LocalTime.of(18,10),40,null,null));
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"스텔라장",
                LocalTime.of(18,50), LocalTime.of(19,40),50,null,null));
        gmfAll.add(new Slot(gmfD2,"FOREST","Loving Forest Garden",4,"너드커넥션",
                LocalTime.of(20,20), LocalTime.of(21,10),50,null,null)); // 종료시간 50분 가정

        /* bright Lab. */
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"삼월생",
                LocalTime.of(13,30), LocalTime.of(14,0),30,null,null));
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"베리코이버니",
                LocalTime.of(14,30), LocalTime.of(15,0),30,null,null));
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"blah",
                LocalTime.of(15,30), LocalTime.of(17,0),90,null,null));
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"공원",
                LocalTime.of(17,30), LocalTime.of(19,0),90,null,null));
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"연정",
                LocalTime.of(18,30), LocalTime.of(19,0),30,null,null));
        gmfAll.add(new Slot(gmfD2,"LAB","bright Lab.",5,"박소은",
                LocalTime.of(19,30), LocalTime.of(20,0),30,null,null));

        gmf.setSlots(gmfAll);


        /* ============ artistPics ============ */
        gmf.setArtistPics(List.of(

                /* ========= 10/18 (토) ========= */
                new ArtistPic(gmfD1, "까치산", ""),
                new ArtistPic(gmfD1, "george", ""),
                new ArtistPic(gmfD1, "폴킴", ""),
                new ArtistPic(gmfD1, "정승환", ""),
                new ArtistPic(gmfD1, "적재", ""),
                new ArtistPic(gmfD1, "AKMU", ""),

                new ArtistPic(gmfD1, "can’t be blue", ""),
                new ArtistPic(gmfD1, "김뜻돌", ""),
                new ArtistPic(gmfD1, "유다빈밴드", ""),
                new ArtistPic(gmfD1, "Daybreak", ""),
                new ArtistPic(gmfD1, "타치도", ""),
                new ArtistPic(gmfD1, "LUCY", ""),

                new ArtistPic(gmfD1, "KIK", ""),
                new ArtistPic(gmfD1, "리도어", ""),
                new ArtistPic(gmfD1, "TELEVISION OFF", ""),
                new ArtistPic(gmfD1, "지소쿠리클럽", ""),
                new ArtistPic(gmfD1, "오월오일", ""),
                new ArtistPic(gmfD1, "페퍼톤스", ""),
                new ArtistPic(gmfD1, "실리카겔", ""),

                new ArtistPic(gmfD1, "우예린", ""),
                new ArtistPic(gmfD1, "GEMINI", ""),
                new ArtistPic(gmfD1, "김수영", ""),
                new ArtistPic(gmfD1, "PAMUNGKAS", ""),
                new ArtistPic(gmfD1, "정세운", ""),
                new ArtistPic(gmfD1, "소수빈", ""),

                new ArtistPic(gmfD1, "키스누", ""),
                new ArtistPic(gmfD1, "evenif", ""),
                new ArtistPic(gmfD1, "컨파인드 화이트", ""),
                new ArtistPic(gmfD1, "민서", ""),
                new ArtistPic(gmfD1, "이준형", ""),
                new ArtistPic(gmfD1, "Rolling Quartz", ""),


                /* ========= 10/19 (일) ========= */
                new ArtistPic(gmfD2, "GOGOHAWK", ""),
                new ArtistPic(gmfD2, "데이먼스 이어", ""),
                new ArtistPic(gmfD2, "하동균", ""),
                new ArtistPic(gmfD2, "멜로망스", ""),
                new ArtistPic(gmfD2, "10CM", ""),
                new ArtistPic(gmfD2, "홍이삭", ""),

                new ArtistPic(gmfD2, "Hi-Fi Unicorn", ""),
                new ArtistPic(gmfD2, "원위", ""),
                new ArtistPic(gmfD2, "카더가든", ""),
                new ArtistPic(gmfD2, "소란", ""),
                new ArtistPic(gmfD2, "CNBLUE", ""),
                new ArtistPic(gmfD2, "윤하", ""),

                new ArtistPic(gmfD2, "LOW HIGH LOW", ""),
                new ArtistPic(gmfD2, "SNAKE CHICKEN SOUP", ""),
                new ArtistPic(gmfD2, "Wendy Wander", ""),
                new ArtistPic(gmfD2, "THE SOLUTIONS", ""),
                new ArtistPic(gmfD2, "드래곤 포니", ""),
                new ArtistPic(gmfD2, "쏜애플", ""),
                new ArtistPic(gmfD2, "N.Flying", ""),

                new ArtistPic(gmfD2, "OurR", ""),
                new ArtistPic(gmfD2, "KEN", ""),
                new ArtistPic(gmfD2, "범진", ""),
                new ArtistPic(gmfD2, "Michael Kaneko", ""),
                new ArtistPic(gmfD2, "스텔라장", ""),
                new ArtistPic(gmfD2, "너드커넥션", ""),

                new ArtistPic(gmfD2, "삼월생", ""),
                new ArtistPic(gmfD2, "베리코이버니", ""),
                new ArtistPic(gmfD2, "blah", ""),
                new ArtistPic(gmfD2, "공원", ""),
                new ArtistPic(gmfD2, "연정", ""),
                new ArtistPic(gmfD2, "박소은", "")
        ));


        /* ============ 링크 ============ */
        gmf.setFesLinks(List.of(
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1458221"),
                new Relate("예스24","http://ticket.yes24.com/Perf/54556"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010143")
        ));

        /* ===== 저장 ===== */
        repository.save(gmf);

        PerformanceDetail bir = PerformanceDetail.builder()
                .mt20id("PF264954")
                .prfnm("부산국제록페스티벌")
                .prfpdfrom(LocalDate.of(2025, 9, 26))
                .prfpdto(LocalDate.of(2025, 9, 28))
                .fcltynm("삼락생태공원 (삼락생태공원)")
                .prfruntime("3DAYS")
                .prfage("전체 관람가")
                .pcseguidance("3DAYS 242,000원 / 2DAYS 176,000원 / 1DAY 110,000원")
                .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF264954_250515_095218.jpg")
                .prfstate("공연완료")
                .dtguidance("금요일(12:00), 토요일 ~ 일요일(12:00)")
                .tkstdate(null)
                .tksttime(null)
                .typeofcon(2)
                .build();


        /* ============ days ============ */
        LocalDate birD1 = LocalDate.of(2025, 9, 26);
        LocalDate birD2 = LocalDate.of(2025, 9, 27);
        LocalDate birD3 = LocalDate.of(2025, 9, 28);

        bir.setDays(List.of(
                new DayInfo(birD1, LocalTime.of(10,0), LocalTime.of(22,0)),
                new DayInfo(birD2, LocalTime.of(10,0), LocalTime.of(22,0)),
                new DayInfo(birD3, LocalTime.of(10,0), LocalTime.of(22,0))
        ));

        /* ============ slots ============ */
        List<Slot> birall = new ArrayList<>();

        /* ---------- 9/26 (금) ---------- */

        // Samrock Stage
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"신인류",
                LocalTime.of(12,0), LocalTime.of(12,30),30,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"Flesh Juicer",
                LocalTime.of(13,0), LocalTime.of(13,30),30,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"go!go!vanillas",
                LocalTime.of(14,0), LocalTime.of(14,40),40,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"한로로",
                LocalTime.of(15,20), LocalTime.of(16,0),40,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"쏜애플",
                LocalTime.of(16,50), LocalTime.of(17,40),50,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"자우림",
                LocalTime.of(18,30), LocalTime.of(19,30),60,null,null));
        birall.add(new Slot(birD1,"SAMROCK","Samrock Stage",1,"SUEDE",
                LocalTime.of(20,40), LocalTime.of(22,0),80,null,null));

        // Green Stage
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"더 보울스",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"아디오스오디오",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"오칠",
                LocalTime.of(13,30), LocalTime.of(14,0),30,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"PITTA",
                LocalTime.of(14,40), LocalTime.of(15,20),40,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"Xdinary Heroes",
                LocalTime.of(16,0), LocalTime.of(16,50),50,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"CNBLUE",
                LocalTime.of(17,40), LocalTime.of(18,30),50,null,null));
        birall.add(new Slot(birD1,"GREEN","Green Stage",2,"NELL",
                LocalTime.of(19,40), LocalTime.of(20,40),60,null,null));

        // River Stage
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"Tokai",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"W24",
                LocalTime.of(12,20), LocalTime.of(12,50),30,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"정우",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"원위",
                LocalTime.of(14,10), LocalTime.of(14,50),40,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"QWER",
                LocalTime.of(15,10), LocalTime.of(15,50),40,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"플라워",
                LocalTime.of(16,10), LocalTime.of(16,50),40,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"ASH ISLAND",
                LocalTime.of(17,20), LocalTime.of(18,0),40,null,null));
        birall.add(new Slot(birD1,"RIVER","River Stage",3,"TAHITI 80",
                LocalTime.of(18,40), LocalTime.of(19,30),50,null,null));

        // Hidden Stage
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"낙원",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"전범선과 양반들",
                LocalTime.of(13,20), LocalTime.of(13,50),30,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"Monday Off With Bluesy",
                LocalTime.of(14,10), LocalTime.of(14,40),30,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"유인원",
                LocalTime.of(15,0), LocalTime.of(15,30),30,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"KYSB",
                LocalTime.of(15,50), LocalTime.of(16,20),30,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"삼락_스피릿 챌린지",
                LocalTime.of(16,50), LocalTime.of(17,30),40,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"삼락_게임랜드",
                LocalTime.of(17,40), LocalTime.of(18,20),40,null,null));
        birall.add(new Slot(birD1,"HIDDEN","Hidden Stage",4,"부락_노래자랑",
                LocalTime.of(18,30), LocalTime.of(19,10),40,null,null));


        /* ---------- 9/27 (토) ---------- */

        // Samrock Stage
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"양치기소년단",
                LocalTime.of(12,0), LocalTime.of(12,30),30,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"리도어",
                LocalTime.of(13,0), LocalTime.of(13,30),30,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"Sorry Youth",
                LocalTime.of(14,0), LocalTime.of(14,40),40,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"바밍타이거",
                LocalTime.of(15,20), LocalTime.of(16,0),40,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"WANIMA",
                LocalTime.of(16,50), LocalTime.of(17,40),50,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"윤수일밴드",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        birall.add(new Slot(birD2,"SAMROCK","Samrock Stage",1,"THE SMASHING PUMPKINS",
                LocalTime.of(20,40), LocalTime.of(22,0),80,null,null));

        // Green Stage
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"극동아시아타이거즈",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"단편선 순간들",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"키라라",
                LocalTime.of(13,30), LocalTime.of(14,0),30,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"LUCKLIFE",
                LocalTime.of(14,40), LocalTime.of(15,20),40,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"너드커넥션",
                LocalTime.of(16,0), LocalTime.of(16,50),50,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"클렌체크",
                LocalTime.of(17,40), LocalTime.of(18,30),50,null,null));
        birall.add(new Slot(birD2,"GREEN","Green Stage",2,"MIKA",
                LocalTime.of(19,30), LocalTime.of(20,40),70,null,null));

        // River Stage
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"컨파인드 화이트",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"Hi-Fi Unicorn",
                LocalTime.of(12,20), LocalTime.of(12,50),30,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"팔칠댄스",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"윤마치",
                LocalTime.of(14,10), LocalTime.of(14,50),40,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"muque",
                LocalTime.of(15,10), LocalTime.of(15,50),40,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"위아더나잇",
                LocalTime.of(16,10), LocalTime.of(16,50),40,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"짙은",
                LocalTime.of(17,20), LocalTime.of(18,10),50,null,null));
        birall.add(new Slot(birD2,"RIVER","River Stage",3,"오존 x 카더가든",
                LocalTime.of(18,40), LocalTime.of(19,30),50,null,null));

        // Hidden Stage
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"오아!",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"cheap.n.sweet",
                LocalTime.of(13,20), LocalTime.of(13,50),30,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"Default.",
                LocalTime.of(14,10), LocalTime.of(14,40),30,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"HYANG",
                LocalTime.of(15,0), LocalTime.of(15,30),30,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"까치산",
                LocalTime.of(15,50), LocalTime.of(16,20),30,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"삼락_스피릿 챌린지",
                LocalTime.of(16,50), LocalTime.of(17,30),40,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"삼락_게임랜드",
                LocalTime.of(17,40), LocalTime.of(18,20),40,null,null));
        birall.add(new Slot(birD2,"HIDDEN","Hidden Stage",4,"부락_노래자랑",
                LocalTime.of(18,30), LocalTime.of(19,10),40,null,null));


        /* ---------- 9/28 (일) ---------- */

        // Samrock Stage
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"심야일랜드 SMILE LAND",
                LocalTime.of(12,0), LocalTime.of(12,30),30,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"드래곤 포니",
                LocalTime.of(13,0), LocalTime.of(13,30),30,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"WOODZ",
                LocalTime.of(14,0), LocalTime.of(14,40),40,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"TOKYO SKA PARADISE ORCHESTRA",
                LocalTime.of(15,20), LocalTime.of(16,10),50,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"터치드",
                LocalTime.of(16,50), LocalTime.of(17,40),50,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"국카스텐",
                LocalTime.of(18,40), LocalTime.of(19,40),60,null,null));
        birall.add(new Slot(birD3,"SAMROCK","Samrock Stage",1,"BABYMETAL",
                LocalTime.of(20,40), LocalTime.of(22,0),80,null,null));

        // Green Stage
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"다양성",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"ddbb",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"Slot Machine",
                LocalTime.of(13,30), LocalTime.of(14,0),30,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"브로큰 발렌타인",
                LocalTime.of(14,20), LocalTime.of(15,20),60,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"Y2K",
                LocalTime.of(16,10), LocalTime.of(16,50),40,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"이승윤",
                LocalTime.of(17,40), LocalTime.of(18,40),60,null,null));
        birall.add(new Slot(birD3,"GREEN","Green Stage",2,"PORTER ROBINSON",
                LocalTime.of(19,40), LocalTime.of(20,40),60,null,null));

        // River Stage
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"AxMxP",
                LocalTime.of(11,30), LocalTime.of(12,0),30,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"BØJEONG",
                LocalTime.of(12,20), LocalTime.of(12,50),30,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"오월오일",
                LocalTime.of(13,10), LocalTime.of(13,50),40,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"쏠",
                LocalTime.of(14,10), LocalTime.of(14,50),40,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"LET ME KNOW",
                LocalTime.of(15,10), LocalTime.of(15,50),40,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"Eir Aoi",
                LocalTime.of(16,10), LocalTime.of(16,50),40,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"소란 SORAN",
                LocalTime.of(17,20), LocalTime.of(18,10),50,null,null));
        birall.add(new Slot(birD3,"RIVER","River Stage",3,"10CM",
                LocalTime.of(18,40), LocalTime.of(19,30),50,null,null));

        // Hidden Stage
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"프랭클리",
                LocalTime.of(12,30), LocalTime.of(13,0),30,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"나타샤",
                LocalTime.of(13,20), LocalTime.of(13,50),30,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"야자수",
                LocalTime.of(14,10), LocalTime.of(14,40),30,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"데이네버체인지",
                LocalTime.of(15,0), LocalTime.of(15,30),30,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"다다다",
                LocalTime.of(15,50), LocalTime.of(16,20),30,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"루키즈 온 더 부락 시상식",
                LocalTime.of(16,50), LocalTime.of(17,30),40,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"스쿨오브부락",
                LocalTime.of(17,40), LocalTime.of(18,20),40,null,null));
        birall.add(new Slot(birD3,"HIDDEN","Hidden Stage",4,"부락_노래자랑",
                LocalTime.of(18,30), LocalTime.of(19,10),40,null,null));


        bir.setSlots(birall);

        /* ============ artistPics ============ */
        bir.setArtistPics(List.of(

                /* ========= 9/26 (금) ========= */
                new ArtistPic(birD1, "신인류u", ""),
                new ArtistPic(birD1, "Flesh Juicer", ""),
                new ArtistPic(birD1, "go!go!vanillas", ""),
                new ArtistPic(birD1, "한로로", ""),
                new ArtistPic(birD1, "쏜애플", ""),
                new ArtistPic(birD1, "자우림", ""),
                new ArtistPic(birD1, "SUEDE", ""),
                new ArtistPic(birD1, "더 보울스", ""),
                new ArtistPic(birD1, "아디오스오디오", ""),
                new ArtistPic(birD1, "오칠", ""),
                new ArtistPic(birD1, "PITTA", ""),
                new ArtistPic(birD1, "Xdinary Heroes", ""),
                new ArtistPic(birD1, "CNBLUE", ""),
                new ArtistPic(birD1, "NELL", ""),
                new ArtistPic(birD1, "Tokai", ""),
                new ArtistPic(birD1, "W24", ""),
                new ArtistPic(birD1, "정우", ""),
                new ArtistPic(birD1, "원위", ""),
                new ArtistPic(birD1, "QWER", ""),
                new ArtistPic(birD1, "플라워", ""),
                new ArtistPic(birD1, "ASH ISLAND", ""),
                new ArtistPic(birD1, "TAHITI 80", ""),
                new ArtistPic(birD1, "낙원", ""),
                new ArtistPic(birD1, "전범선과 양반들", ""),
                new ArtistPic(birD1, "Monday Off With Bluesy", ""),
                new ArtistPic(birD1, "유인원", ""),
                new ArtistPic(birD1, "KYSB", ""),


                /* ========= 9/27 (토) ========= */
                new ArtistPic(birD2, "양치기소년단", ""),
                new ArtistPic(birD2, "리도어", ""),
                new ArtistPic(birD2, "Sorry Youth", ""),
                new ArtistPic(birD2, "바밍타이거", ""),
                new ArtistPic(birD2, "WANIMA", ""),
                new ArtistPic(birD2, "윤수일밴드", ""),
                new ArtistPic(birD2, "THE SMASHING PUMPKINS", ""),
                new ArtistPic(birD2, "키라라", ""),
                new ArtistPic(birD2, "LUCKLIFE", ""),
                new ArtistPic(birD2, "너드커넥션", ""),
                new ArtistPic(birD2, "클렌체크", ""),
                new ArtistPic(birD2, "MIKA", ""),
                new ArtistPic(birD2, "컨파인드 화이트", ""),
                new ArtistPic(birD2, "Hi-Fi Unicorn", ""),
                new ArtistPic(birD2, "팔칠댄스", ""),
                new ArtistPic(birD2, "윤마치", ""),
                new ArtistPic(birD2, "muque", ""),
                new ArtistPic(birD2, "위아더나잇", ""),
                new ArtistPic(birD2, "짙은", ""),
                new ArtistPic(birD2, "오촌 x 카더가든", ""),
                new ArtistPic(birD2, "오아!", ""),
                new ArtistPic(birD2, "cheap.n.sweet", ""),
                new ArtistPic(birD2, "Default.", ""),
                new ArtistPic(birD2, "HYANG", ""),
                new ArtistPic(birD2, "까치산", ""),

                /* ========= 9/28 (일) ========= */
                new ArtistPic(birD3, "심야일랜드", ""),
                new ArtistPic(birD3, "Dragon Pony", ""),
                new ArtistPic(birD3, "WOODZ", ""),
                new ArtistPic(birD3, "TOKYO SKA PARADISE ORCHESTRA", ""),
                new ArtistPic(birD3, "터치드", ""),
                new ArtistPic(birD3, "국카스텐", ""),
                new ArtistPic(birD3, "BABYMETAL", ""),
                new ArtistPic(birD3, "다양성", ""),
                new ArtistPic(birD3, "ddbb", ""),
                new ArtistPic(birD3, "Slot Machine", ""),
                new ArtistPic(birD3, "브로큰 발렌타인", ""),
                new ArtistPic(birD3, "Y2K", ""),
                new ArtistPic(birD3, "이승윤", ""),
                new ArtistPic(birD3, "PORTER ROBINSON", ""),
                new ArtistPic(birD3, "AxMxP", ""),
                new ArtistPic(birD3, "BØJEONG", ""),
                new ArtistPic(birD3, "오월오일", ""),
                new ArtistPic(birD3, "쏠", ""),
                new ArtistPic(birD3, "LET ME KNOW", ""),
                new ArtistPic(birD3, "Eir Aoi", ""),
                new ArtistPic(birD3, "소란", ""),
                new ArtistPic(birD3, "10CM", ""),
                new ArtistPic(birD3, "프랭클", ""),
                new ArtistPic(birD3, "나타샤", ""),
                new ArtistPic(birD3, "야자수", ""),
                new ArtistPic(birD3, "데이네버체인지", ""),
                new ArtistPic(birD3, "다다다", "")
        ));

        /* ============ 링크 ============ */
        bir.setFesLinks(List.of(
                new Relate("예스24","http://ticket.yes24.com/Perf/53880"),
                new Relate("인터파크","https://tickets.interpark.com/goods/25009725")
        ));

        /* ===== 저장 ===== */
        repository.save(bir);



        List<PerformanceDetail> data = List.of(
                PerformanceDetail.builder()
                        .mt20id("PF262418")
                        .prfnm("검정치마 단독 공연: LAST CHANCE TO COME BACK HOME")
                        .prfpdfrom(LocalDate.of(2025,5,2))
                        .prfpdto(LocalDate.of(2025,5,11))
                        .fcltynm("장충체육관 (장충체육관)")
                        .prfruntime("2시간")
                        .prfage("만 13세 이상")
                        .pcseguidance("스탠딩 132,000원, 지정석 132,000원, 시야제한석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF262418_250404_101520.gif")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00), 토요일(19:00), 일요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)
                        // 여러 이미지 출연진 URL (객체 리스트)
                        .styurls(List.of(
                                new PerformanceDetail.ArtPic( "검정치마", "https://i.scdn.co/image/ab676161000051748609536d21beed6769d09d7f")
                        ))
                        // 여러 관련 링크
                        .relates(List.of(
                                new PerformanceDetail.Relate( "인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004656"),
                                new PerformanceDetail.Relate( "멜론티켓",
                                        "https://ticket.melon.com/performance/PF262418")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF256780")
                        .prfnm("검정치마 단독공연: SONGS TO BRING YOU HOME")
                        .prfpdfrom(LocalDate.of(2025,2,7))
                        .prfpdto(LocalDate.of(2025,2,9))
                        .fcltynm("올림픽공원 (올림픽홀)")
                        .prfruntime("2시간")
                        .prfage("만 13세 이상")
                        .pcseguidance("스탠딩석 132,000원, 지정석R석 132,000원, 지정석S석 121,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF256780_250108_125004.gif")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00), 토요일(19:00), 일요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic( "검정치마", "https://i.scdn.co/image/ab676161000051748609536d21beed6769d09d7f")
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate( "인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25000084")
                        ))
                        .build(),
                //검정치마
                PerformanceDetail.builder()
                        .mt20id("PF270921")
                        .prfnm("LOVE IN SEOUL, 새소년")
                        .prfpdfrom(LocalDate.of(2025,11,19))
                        .prfpdto(LocalDate.of(2025,11,19))
                        .fcltynm("블루스퀘어 (SOL트래블홀 (구. Mastercard Hall) )")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("스탠딩 132,000원, 지정석 143,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF270921_250804_153848.gif")
                        .prfstate("공연완료")
                        .dtguidance("수요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("새소년","https://i.scdn.co/image/ab6761610000517427781ac76c7bb43ec6c7d4b2")
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크" ,
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010965"        )
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF270285")
                        .prfnm("실리카겔 단독공연: Syn. THE. Size X")
                        .prfpdfrom(LocalDate.of(2025,8,30))
                        .prfpdto(LocalDate.of(2025,8,31))
                        .fcltynm("킨텍스 (제1전시장 1홀)")
                        .prfruntime("1시간 30분")
                        .prfage("만 11세 이상")
                        .pcseguidance("스탠딩 R 132,000원, 스탠딩 S 121,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF270285_250724_145128.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("실리카겔","https://i.scdn.co/image/ab67616100005174017f7a68d770a2f115264068")

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010454"                        )
                        ))//실리카겔
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF269185")
                        .prfnm("그룹사운드 잔나비 앵콜 콘서트: 모든소년소녀들 2125 [서울 (앵콜)]")
                        .prfpdfrom(LocalDate.of(2025,8,2))
                        .prfpdto(LocalDate.of(2025,8,3))
                        .fcltynm("올림픽공원 (KSPO DOME(체조경기장))")
                        .prfruntime("2시간 20분")
                        .prfage("만 6세 이상")
                        .pcseguidance("f'I'oor(현장수령)석  178,000원, M석  178,000원, J석  158,000원, F석  128,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF269185_250710_154753.png")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("잔나비","https://i.scdn.co/image/ab67616100005174776565cc2d97c46f4d000134" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크" ,
                                        "http://www.ticketlink.co.kr/product/57359"        )
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF266253")
                        .prfnm("잔나비 콘서트: 모든소년소녀들 [대구]")
                        .prfpdfrom(LocalDate.of(2025,6,28))
                        .prfpdto(LocalDate.of(2025,6,29))
                        .fcltynm("엑스코(exco) (전시6홀)")
                        .prfruntime("2시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("I석 178,000원, M석 178,000원, J석 158,000원, F석 128,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF266253_250530_155938.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("잔나비","https://i.scdn.co/image/ab67616100005174776565cc2d97c46f4d000134" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/56530")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF265421")
                        .prfnm("잔나비 콘서트: 모든소년소녀들 [광주]")
                        .prfpdfrom(LocalDate.of(2025,6,14))
                        .prfpdto(LocalDate.of(2025,6,15))
                        .fcltynm("광주여자대학교 (유니버시아드 체육관)")
                        .prfruntime("2시간 20분")
                        .prfage("만 6세 이상")
                        .pcseguidance("f'l'oor(현장수령)석 178,000원, f'l'oor석 178,000원, M석 178,000원, J석 158,000원, F석 128,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF265421_250521_134634.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("잔나비","https://i.scdn.co/image/ab67616100005174776565cc2d97c46f4d000134" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/56440")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF265622")
                        .prfnm("데이브레이크, SUMMER MADNESS: BLUE")
                        .prfpdfrom(LocalDate.of(2025,7,5))
                        .prfpdto(LocalDate.of(2025,7,6))
                        .fcltynm("예스24 라이브홀 (구. 악스코리아) (예스24 라이브홀 (구. 악스코리아))")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 132,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF265622_250523_110524.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("데이브레이크","https://i.scdn.co/image/ab676161000051746822d68461a20f71e3db2c30" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/54010")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF276872")
                        .prfnm("쏜애플 콘서트: 바다와 구름과 무대")
                        .prfpdfrom(LocalDate.of(2025,12,20))
                        .prfpdto(LocalDate.of(2025,12,21))
                        .fcltynm("고려대학교 화정체육관 (주경기장)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 132,000원, S석 121,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF276872_251021_111649.gif")
                        .prfstate("공연예정")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(LocalDate.of(2025,10,20))
                        .tksttime(LocalTime.of(19,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("쏜애플","https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=212136"),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25015161")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF265501")
                        .prfnm("쏜애플 콘서트, 불구경")
                        .prfpdfrom(LocalDate.of(2025,6,20))
                        .prfpdto(LocalDate.of(2025,6,29))
                        .fcltynm("LG아트센터 서울 (U+ 스테이지)")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF265501_250521_165449.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00), 토요일(19:00), 일요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("쏜애플","https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("LG아트센터",
                                        "https://www.lgart.com/product/ko/performance/252918"),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=L0000121")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF257819")
                        .prfnm("MPMG WEEK, BLUE LABEL: 쏜애플")
                        .prfpdfrom(LocalDate.of(2025,2,10))
                        .prfpdto(LocalDate.of(2025,2,11))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 10분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF257819_250124_100726.gif")
                        .prfstate("공연완료")
                        .dtguidance("월요일 ~ 화요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("쏜애플","https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25001049")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF271583")
                        .prfnm("3호선 버터플라이, 환희보라바깥 발매 기념 콘서트")
                        .prfpdfrom(LocalDate.of(2025,9,13))
                        .prfpdto(LocalDate.of(2025,9,14))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("현장판매 66,000원, 사전예약 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF271583_250820_125257.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00), 일요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("3호선 버터플라이","https://i.scdn.co/image/ab67616100005174aba03eccf4f7f5150d5e5f98" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25011249")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF270503")
                        .prfnm("디깅라이브세종, EP.17 3호선 버터플라이 [세종]")
                        .prfpdfrom(LocalDate.of(2025,9,27))
                        .prfpdto(LocalDate.of(2025,9,27))
                        .fcltynm("세종 음악창작소 누리락 (공연장)")
                        .prfruntime("1시간 15분")
                        .prfage("만 7세 이상")
                        .pcseguidance("일반석 30,000원, 보컬뒷자리석 20,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF270503_250728_155254.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("3호선 버터플라이","https://i.scdn.co/image/ab67616100005174aba03eccf4f7f5150d5e5f98" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010518")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF277768")
                        .prfnm("웨이브투어스, Curated 01: Milena")
                        .prfpdfrom(LocalDate.of(2025,11,30))
                        .prfpdto(LocalDate.of(2025,11,30))
                        .fcltynm("현대카드 언더스테이지 (스튜디오)")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF277768_251030_125723.jpg")
                        .prfstate("공연예정")
                        .dtguidance("일요일(17:00)")
                        .tkstdate(LocalDate.of(2025,10,28))
                        .tksttime(LocalTime.of(19,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("웨이브투어스","https://i.scdn.co/image/ab6761610000517449799010fa77f1f862ab207e" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=212206")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF270878")
                        .prfnm("정우 단독 공연: 철의 삶")
                        .prfpdfrom(LocalDate.of(2025,8,30))
                        .prfpdto(LocalDate.of(2025,8,31))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 30분")
                        .prfage("만 11세 이상")
                        .pcseguidance("스탠딩  77,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF270878_250804_140100.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("정우","https://i.scdn.co/image/ab67616100005174e156408abd916fe880bb7564" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010967")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF268242")
                        .prfnm("정우의 기습공격")
                        .prfpdfrom(LocalDate.of(2025,7,3))
                        .prfpdto(LocalDate.of(2025,7,3))
                        .fcltynm("KT&G 상상마당 라이브홀 [마포] (KT&G 상상마당 라이브홀 [마포] )")
                        .prfruntime("1시간")
                        .prfage("전체 관람가")
                        .pcseguidance("스탠딩 35,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF268242_250627_114657.png")
                        .prfstate("공연완료")
                        .dtguidance("목요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("정우","https://i.scdn.co/image/ab67616100005174e156408abd916fe880bb7564" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211590")
                        ))
                        .build(),


                PerformanceDetail.builder()
                        .mt20id("PF269773")
                        .prfnm("너드커넥션 SUMMER LIVE: PULSE")
                        .prfpdfrom(LocalDate.of(2025,8,15))
                        .prfpdto(LocalDate.of(2025,8,15))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 50분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF269773_250718_102606.gif")
                        .prfstate("공연완료")
                        .dtguidance("HOL(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("너드커넥션","https://i.scdn.co/image/ab67616100005174f37dafb5dbbefd1fb1eb113e" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25008903")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF268049")
                        .prfnm("터치드 (TOUCHED) 단독 콘서트: ATTRACTION")
                        .prfpdfrom(LocalDate.of(2025,8,23))
                        .prfpdto(LocalDate.of(2025,8,24))
                        .fcltynm("킨텍스 (제2전시장 10홀)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 132,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF268049_250625_130814.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("터치드","https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25008888")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF257903")
                        .prfnm("MPMG WEEK, BLUE LABEL: 터치드")
                        .prfpdfrom(LocalDate.of(2025,2,13))
                        .prfpdto(LocalDate.of(2025,2,13))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 10분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF257903_250124_154852.gif")
                        .prfstate("공연완료")
                        .dtguidance("목요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("터치드","https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25000887")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF253422")
                        .prfnm("터치드 (TOUCHED) 단독 콘서트: HIGHLIGHT Ⅲ")
                        .prfpdfrom(LocalDate.of(2025,1,25))
                        .prfpdto(LocalDate.of(2025,1,26))
                        .fcltynm("올림픽공원 (올림픽홀)")
                        .prfruntime("1시간 50분")
                        .prfage("만 7세 이상")
                        .pcseguidance("VIP석 121,000원, R석 110,000원, R석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF257903_250124_154852.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("터치드","https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=24016171")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF268932")
                        .prfnm("노들인디션 VOL.4 극동아시아타이거즈 단독공연: 호랑이 상담소")
                        .prfpdfrom(LocalDate.of(2025,7,30))
                        .prfpdto(LocalDate.of(2025,7,30))
                        .fcltynm("노들섬 (라이브하우스)")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석무료")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF268932_250708_110750.gif")
                        .prfstate("공연완료")
                        .dtguidance("수요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25009791")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF268843")
                        .prfnm("극동아시아타이거즈 전국투어: 호랑이 소리를 찾아서 [서울 (앵콜) ]")
                        .prfpdfrom(LocalDate.of(2025,8,22))
                        .prfpdto(LocalDate.of(2025,8,22))
                        .fcltynm("KT&G 상상마당 라이브홀 [마포] (KT&G 상상마당 라이브홀 [마포] )")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF268843_250707_124116.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211496")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF263787")
                        .prfnm("극동아시아타이거즈 전국투어: 호랑이 소리를 찾아서 [춘천]")
                        .prfpdfrom(LocalDate.of(2025,6,28))
                        .prfpdto(LocalDate.of(2025,6,28))
                        .fcltynm("KT&G 상상마당 [춘천] (사운드홀)")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263787_250424_105816.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211308")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF263745")
                        .prfnm("극동아시아타이거즈 전국투어: 호랑이 소리를 찾아서 [부산]")
                        .prfpdfrom(LocalDate.of(2025,6,6))
                        .prfpdto(LocalDate.of(2025,6,6))
                        .fcltynm("KT&G 상상마당 라이브홀 [부산] (라이브홀)")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263745_250423_143636.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211305")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF263607")
                        .prfnm("극동아시아타이거즈 전국투어: 호랑이 소리를 찾아서 [서울]")
                        .prfpdfrom(LocalDate.of(2025,5,17))
                        .prfpdto(LocalDate.of(2025,5,17))
                        .fcltynm("KT&G 상상마당 라이브홀 [마포] (KT&G 상상마당 라이브홀 [마포] )")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263607_250422_105537.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211307")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF256003")
                        .prfnm("롤링 30주년 기념 공연, 극동아시아타이거즈 단독 콘서트: 몽유호원")
                        .prfpdfrom(LocalDate.of(2025,1,16))
                        .prfpdto(LocalDate.of(2025,1,16))
                        .fcltynm("롤링홀 (롤링홀)")
                        .prfruntime("1시간 30분")
                        .prfage("전체 관람가")
                        .pcseguidance("스탠딩 44,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF256003_241218_130556.png")
                        .prfstate("공연완료")
                        .dtguidance("목요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=210796")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF274182")
                        .prfnm("단편선 순간들 단독공연: 모두를 위한 음악만세 2025")
                        .prfpdfrom(LocalDate.of(2025,10,31))
                        .prfpdto(LocalDate.of(2025,10,31))
                        .fcltynm("KT&G 상상마당 라이브홀 [마포] (KT&G 상상마당 라이브홀 [마포] )")
                        .prfruntime("1시간 34분")
                        .prfage("만 6세 이상")
                        .pcseguidance("스탠딩 44,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF274182_250916_103906.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("단편선 순간들","https://i.scdn.co/image/ab676161000051749254e46ccc745637ff9caa89" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211946")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF262150")
                        .prfnm("디깅 라이브 세종, EP.15 단편선 순간들 [세종]")
                        .prfpdfrom(LocalDate.of(2025,6,28))
                        .prfpdto(LocalDate.of(2025,6,28))
                        .fcltynm("세종 음악창작소 누리락 (공연장)")
                        .prfruntime("1시간 15분")
                        .prfage("만 7세 이상")
                        .pcseguidance("일반석 30,000원, 보컬뒷자리석 20,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF262150_250331_141946.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("단편선 순간들","https://i.scdn.co/image/ab676161000051749254e46ccc745637ff9caa89" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004415")
                        ))
                        .build(),


                PerformanceDetail.builder()
                        .mt20id("PF260985")
                        .prfnm("Dragon Pony (드래곤포니) 1st Concert: Not Out [서울]")
                        .prfpdfrom(LocalDate.of(2025,5,3))
                        .prfpdto(LocalDate.of(2025,5,4))
                        .fcltynm("신한카드 SOL페이 스퀘어(구.신한pLay 스퀘어) (라이브홀)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF260985_250313_163149.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("드래곤 포니","https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25003223")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF267118")
                        .prfnm("TONE STUDIO LIVE, 서울전자음악단 (Seoul electric band)")
                        .prfpdfrom(LocalDate.of(2025,7,5))
                        .prfpdto(LocalDate.of(2025,7,5))
                        .fcltynm("톤스튜디오 서울 (A ROOM)")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF267118_250613_105456.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("서울전자음악단","https://i.scdn.co/image/ab6761610000517426158530645531be8d86fb44" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211498")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF263784")
                        .prfnm("먼데이프로젝트 시즌8, 서울전자음악단 단독 콘서트: THE ORIGINAL")
                        .prfpdfrom(LocalDate.of(2025,5,12))
                        .prfpdto(LocalDate.of(2025,5,12))
                        .fcltynm("클럽온에어 (클럽온에어)")
                        .prfruntime("1시간 40분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263784_250424_105358.png")
                        .prfstate("공연완료")
                        .dtguidance("월요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("서울전자음악단","https://i.scdn.co/image/ab6761610000517426158530645531be8d86fb44" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211302")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF258740")
                        .prfnm("롤링 30주년 기념 공연, 서울전자음악단 단독 콘서트: 사이키델릭 평행 세계")
                        .prfpdfrom(LocalDate.of(2025,3,7))
                        .prfpdto(LocalDate.of(2025,3,7))
                        .fcltynm("롤링홀 (롤링홀)")
                        .prfruntime("1시간 40분")
                        .prfage("전체 관람가")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF258740_250211_104407.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("서울전자음악단","https://i.scdn.co/image/ab6761610000517426158530645531be8d86fb44" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=210992")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF261999")
                        .prfnm("자우림 팬 콘서트: 러브공작단 시크릿 쥬 주 총회")
                        .prfpdfrom(LocalDate.of(2025,4,26))
                        .prfpdto(LocalDate.of(2025,4,26))
                        .fcltynm("신한카드 SOL페이 스퀘어(구.신한pLay 스퀘어) (라이브홀)")
                        .prfruntime("1시간 40분")
                        .prfage("만 8세 이상")
                        .pcseguidance("전석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF261999_250328_104608.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(14:00,19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("자우림","https://i.scdn.co/image/ab67616100005174a2a96bc3eb2a3ba23e4a6c92" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004634")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF276253")
                        .prfnm("한로로 4TH 단독콘서트: 자몽살구클럽")
                        .prfpdfrom(LocalDate.of(2025,11,22))
                        .prfpdto(LocalDate.of(2025,11,23))
                        .fcltynm("고려대학교 화정체육관 (주경기장)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 121,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF276253_251015_152406.png")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("한로로","https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=212063")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF262961")
                        .prfnm("SUPER! 수요콘서트, 유다빈밴드 Ⅹ 한로로 [창원] (6월)")
                        .prfpdfrom(LocalDate.of(2025,6,25))
                        .prfpdto(LocalDate.of(2025,6,25))
                        .fcltynm("3.15아트센터 (대극장)")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 40,000원, S석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF262961_250411_155345.jpg")
                        .prfstate("공연완료")
                        .dtguidance("수요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("힌로로","https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa" ),
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1387717"),
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/53470")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF275487")
                        .prfnm("윤마치 콘서트: M")
                        .prfpdfrom(LocalDate.of(2025,11,1))
                        .prfpdto(LocalDate.of(2025,11,1))
                        .fcltynm("이화여자대학교 삼성홀 (이화여자대학교 삼성홀)")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF275487_251001_143220.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("윤마치","https://i.scdn.co/image/ab67616100005174ba03c2512927b6ffea90246e" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/58944")
                        ))
                        .build(),


                PerformanceDetail.builder()
                        .mt20id("PF254861")
                        .prfnm("HANRORO 3RD SOLO CONCERT, 발아")
                        .prfpdfrom(LocalDate.of(2025,1,11))
                        .prfpdto(LocalDate.of(2025,1,12))
                        .fcltynm("예스24 라이브홀 (구. 악스코리아) (예스24 라이브홀 (구. 악스코리아))")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF254861_241129_103606.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("한로로","https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/51822")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF275346")
                        .prfnm("유다빈밴드 단독 콘서트: 우리의 밤 - 커튼콜")
                        .prfpdfrom(LocalDate.of(2025,11,15))
                        .prfpdto(LocalDate.of(2025,11,16))
                        .fcltynm("올림픽공원 (올림픽홀)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 121,000원, S석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF275346_250930_125608.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25014163")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF266878")
                        .prfnm("인디神페스티벌, 유다빈밴드 [대구]")
                        .prfpdfrom(LocalDate.of(2025,7,12))
                        .prfpdto(LocalDate.of(2025,7,12))
                        .fcltynm("어울아트센터(구. 대구북구문예회관) (함지홀(대공연장))")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF266878_250610_125749.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/56802"),
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1430538")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF264509")
                        .prfnm("어슬렁 어슬렁 콘서트, #여름: 유다빈밴드")
                        .prfpdfrom(LocalDate.of(2025,7,18))
                        .prfpdto(LocalDate.of(2025,7,18))
                        .fcltynm("영등포아트홀 (영등포아트홀)")
                        .prfruntime("1시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("일반석 40,000원, 투쓰리특별석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF264509_250509_100424.gif")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("유다빈밴드","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25006282")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF260888")
                        .prfnm("유다빈밴드 단독 콘서트: GET LUCKY!")
                        .prfpdfrom(LocalDate.of(2025,4,26))
                        .prfpdto(LocalDate.of(2025,4,27))
                        .fcltynm("예스24 라이브홀 (구. 악스코리아) (예스24 라이브홀 (구. 악스코리아))")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("스탠딩 110,000원, 지정석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF260888_250313_100058.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/53075"),
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25003325")
                        ))
                        .build(),



                PerformanceDetail.builder()
                        .mt20id("PF257902")
                        .prfnm("MPMG WEEK, BLUE LABEL: 유다빈밴드")
                        .prfpdfrom(LocalDate.of(2025,2,12))
                        .prfpdto(LocalDate.of(2025,2,12))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 10분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 66,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF257902_250124_154435.gif")
                        .prfstate("공연완료")
                        .dtguidance("수요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25000842")
                        ))
                        .build(),


                PerformanceDetail.builder()
                        .mt20id("PF277677")
                        .prfnm("지소쿠리클럽 연말 단독 콘서트: Into the Wild")
                        .prfpdfrom(LocalDate.of(2025,12,6))
                        .prfpdto(LocalDate.of(2025,12,7))
                        .fcltynm("예스24 라이브홀 (구. 악스코리아) (예스24 라이브홀 (구. 악스코리아))")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF277677_251029_141123.jpg")
                        .prfstate("공연예정")
                        .dtguidance("토요일(19:00), 일요일(17:00)")
                        .tkstdate(LocalDate.of(2025,11,2))
                        .tksttime(LocalTime.of(19,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "https://ticket.yes24.com/Perf/55818")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF276160")
                        .prfnm("타인의 플레이리스트 #3: 지소쿠리클럽 [울산]")
                        .prfpdfrom(LocalDate.of(2025,11,28))
                        .prfpdto(LocalDate.of(2025,11,28))
                        .fcltynm("울산중구문화의전당 (함월홀 (2층) )")
                        .prfruntime("1시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF276160_251015_110100.png")
                        .prfstate("공연예정")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(LocalDate.of(2025,10,28))
                        .tksttime(LocalTime.of(19,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("울산중구문화의전당",
                                        "https://artscenter.junggu.ulsan.kr/01_Menu/01_view.do?prcValue=P0001740&searchCat1=PFMANC")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF265915")
                        .prfnm("제998회 목요예술무대: 밴드 지소쿠리클럽의 시리즈 파장")
                        .prfpdfrom(LocalDate.of(2025,6,12))
                        .prfpdto(LocalDate.of(2025,6,12))
                        .fcltynm("강남씨어터 (강남씨어터)")
                        .prfruntime("1시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 20,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF265915_250527_140029.gif")
                        .prfstate("공연완료")
                        .dtguidance("목요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25007392")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF261824")
                        .prfnm("G-STAGE Vol.5: 지소쿠리클럽 콘서트 [경기 광주]")
                        .prfpdfrom(LocalDate.of(2025,5,16))
                        .prfpdto(LocalDate.of(2025,5,16))
                        .fcltynm("광주시문화예술의전당 (구. 남한산성아트홀) (맹사성홀)")
                        .prfruntime("1시간 10분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 50,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF261824_250326_102658.gif")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25003470")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF260683")
                        .prfnm("DSAC 시즌 콘서트, 인디 스테이션: 지소쿠리클럽 [대구]")
                        .prfpdfrom(LocalDate.of(2025,4,12))
                        .prfpdto(LocalDate.of(2025,4,12))
                        .fcltynm("달서아트센터 (구. 웃는얼굴아트센터) (청룡홀)")
                        .prfruntime("1시간 10분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 50,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF260683_250310_155912.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/55112"),
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1362079")

                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF259427")
                        .prfnm("지소쿠리클럽 단독공연: 등반가들")
                        .prfpdfrom(LocalDate.of(2025,3,15))
                        .prfpdto(LocalDate.of(2025,3,16))
                        .fcltynm("신한카드 SOL페이 스퀘어(구.신한pLay 스퀘어) (라이브홀)")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 88,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF259427_250220_104808.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002327")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF274608")
                        .prfnm("백예린 단독 콘서트: Flash and Core [인천]")
                        .prfpdfrom(LocalDate.of(2025,10,25))
                        .prfpdto(LocalDate.of(2025,10,26))
                        .fcltynm("인스파이어 엔터테인먼트 리조트 (아레나)")
                        .prfruntime("2시간")
                        .prfage("만 11세 이상")
                        .pcseguidance("스탠딩  154,000원, SR  165,000원, R석 154,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF274608_250922_113849.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517436fb1d2c40997cd05363de1f" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25013780")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF274608")
                        .prfnm("라우브 내한공연 Lauv's I Love You, Mean It Tour [대구]")
                        .prfpdfrom(LocalDate.of(2025,5,24))
                        .prfpdto(LocalDate.of(2025,5,25))
                        .fcltynm("엑스코(exco) (컨벤션홀 (5층))")
                        .prfruntime("2시간")
                        .prfage("만 11세 이상")
                        .pcseguidance("스탠딩  154,000원, SR  165,000원, R석 154,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF274608_250922_113849.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.ArtPic("지소쿠리클럽","https://i.scdn.co/image/ab6761610000517436fb1d2c40997cd05363de1f" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25013780")
                        ))
                        .build()
        );
        repository.saveAll(data);
        System.out.println(" 공연 더미데이터 초기화 완료 (" + data.size() + "건)");
    }
}