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
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class PerformanceDataInitializer {

    private final PerformanceDetailRepository repository;
    private final JdbcTemplate jdbcTemplate;


    @Transactional
    @PostConstruct
    public void init() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE fes_artist_pics");
        jdbcTemplate.execute("TRUNCATE TABLE fes_links");
        jdbcTemplate.execute("TRUNCATE TABLE fes_slots");
        jdbcTemplate.execute("TRUNCATE TABLE fes_days");
        jdbcTemplate.execute("TRUNCATE TABLE performance_styurls");
        jdbcTemplate.execute("TRUNCATE TABLE performance_relates");
        jdbcTemplate.execute("TRUNCATE TABLE performance_detail");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");

//        long count = repository.count();
//        if (count > 0) {
//            System.out.println("공연 더미데이터가 이미 존재합니다. (총 " + count + "건) 초기화 스킵");
//            return;
//        }



        PerformanceDetail fes = PerformanceDetail.builder()
                .mt20id("PF263558")
                .prfnm("인천펜타포트 락 페스티벌 2025")
                .prfpdfrom(LocalDate.of(2025, 8, 1))
                .prfpdto(LocalDate.of(2025, 8, 3))
                .fcltynm("송도달빛축제공원")
                .locationUrl("https://www.google.com/maps/place/%EC%86%A1%EB%8F%84%EB%8B%AC%EB%B9%9B%EC%B6%95%EC%A0%9C%EA%B3%B5%EC%9B%90/data=!3m1!4b1!4m6!3m5!1s0x357b779432cbecc7:0x9b38cc650bb7b441!8m2!3d37.4064278!4d126.6338237!16s%2Fg%2F11ddwydcbh?entry=ttu&g_ep=EgoyMDI1MTEyMy4xIKXMDSoASAFQAw%3D%3D")
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



        fes.setDays(List.of(
                new DayInfo(LocalDate.of(2025, 8, 1), LocalTime.of(10,0), LocalTime.of(23,30)),
                new DayInfo(LocalDate.of(2025, 8, 2), LocalTime.of(10,0), LocalTime.of(23,30)),
                new DayInfo(LocalDate.of(2025, 8, 3), LocalTime.of(10,0), LocalTime.of(23,30))
        ));


        List<Slot> all = new ArrayList<>();


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



        fes.setArtistPics(List.of(

                new ArtistPic(D1,"김뜻돌","https://i.scdn.co/image/ab67616100005174cefd8bc952024635511d6b8b"),
                new ArtistPic(D1,"리도어","https://i.scdn.co/image/ab676161000051744274ddd6297e90ca0398925e"),
                new ArtistPic(D1,"너드커넥션","https://i.scdn.co/image/ab67616100005174819f0c631089e5f09d09eec2"),
                new ArtistPic(D1,"터치드","https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f"),
                new ArtistPic(D1,"Little Simz","https://i.scdn.co/image/ab67616100005174a22264dfbad2d96ffc6ee2e0"),
                new ArtistPic(D1,"장기하","https://i.scdn.co/image/ab67616100005174f99ecf9f9a5e0e62d01b10a6"),
                new ArtistPic(D1,"ASIAN KUNG-FU GENERATION","https://i.scdn.co/image/ab6761610000517440c6a6dd0715a0de86c05308"),

                new ArtistPic(D1,"드래곤 포니","https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055"),
                new ArtistPic(D1,"더 보울스","https://i.scdn.co/image/ab676161000051747456f88637e7eb6417fe1d8f"),
                new ArtistPic(D1,"QWER","https://i.scdn.co/image/ab67616100005174eb24d4a27b5a24af1cfcc24b"),
                new ArtistPic(D1,"Modern Cinema Master","https://i.scdn.co/image/ab67616100005174f17a4539a230317f3a0f0942"),
                new ArtistPic(D1,"봉제인간","https://i.scdn.co/image/ab6761610000517483ba004ed128d489679450c8"),
                new ArtistPic(D1,"Tempalay","https://i.scdn.co/image/ab67616100005174dae7558333b6cc3de91fa70d"),
                new ArtistPic(D1,"크라잉넛","https://i.scdn.co/image/ab676161000051748ff484a7552bcf7b34f1acbf"),

                new ArtistPic(D1,"김승주","https://i.scdn.co/image/ab67616100005174d4f8bee84f26ba38f279d1c3"),
                new ArtistPic(D1,"HYANG","https://i.scdn.co/image/ab67616100005174379dee67b57754094171bd9a"),
                new ArtistPic(D1,"캐치더영","https://i.scdn.co/image/ab67616100005174a89c05a9ca95390ff3ceaf7b"),
                new ArtistPic(D1,"데이네버체인지","https://i.scdn.co/image/ab676161000051749a1f36749d4e442734511e24"),
                new ArtistPic(D1,"롤링쿼츠","https://i.scdn.co/image/ab676161000051741f65067c901a9421b2f1b0b0"),


                new ArtistPic(D2,"KARDI","https://i.scdn.co/image/ab676161000051743c74dd210ce013521c0e6fc6"),
                new ArtistPic(D2,"갤럭시 익스프레스","https://i.scdn.co/image/ab67616100005174e2f24a69c45dca26bf77f2df"),
                new ArtistPic(D2,"Omoinotake","https://i.scdn.co/image/ab676161000051741385dc0ff7498664c1782091"),
                new ArtistPic(D2,"ADOY","https://i.scdn.co/image/ab67616100005174a7a75e22ac51af6b397cdfe7"),
                new ArtistPic(D2,"글렌체크","https://i.scdn.co/image/ab6761610000517452bf23dd85fe7d4cd09ee31b"),
                new ArtistPic(D2,"HYUKOH & SUNSET ROLLERCOASTER","https://i.scdn.co/image/ab67616100005174a83b7860635940ed829ee09b"),
                new ArtistPic(D2,"Pulp","https://i.scdn.co/image/ab676161000051742a7e3dfda16364548dfdc915"),

                new ArtistPic(D2,"다양성","https://i.scdn.co/image/ab6761610000517478e658433e710d053e064bac"),
                new ArtistPic(D2,"BABO","https://i.scdn.co/image/ab6761610000517427c199b958fe9fe72878044e"),
                new ArtistPic(D2,"소음발광","https://i.scdn.co/image/ab67616100005174a12effd7840f3b0ad798c11f"),
                new ArtistPic(D2,"단편선 순간들","https://i.scdn.co/image/ab676161000051749254e46ccc745637ff9caa89"),
                new ArtistPic(D2,"바이 바이 배드맨","https://i.scdn.co/image/ab67616100005174b042a09ef79419c1ad6c8ed1"),
                new ArtistPic(D2,"kanekoayano","https://i.scdn.co/image/ab676161000051740473ccde22668585e5a291b0"),
                new ArtistPic(D2,"메써드","https://i.scdn.co/image/ab67616d00001e0288276db331d8fe8bb6b83df1"),

                new ArtistPic(D2,"creespy","https://i.scdn.co/image/ab67616100005174b4c44e22e0c3d6a80112a0b1"),
                new ArtistPic(D2,"비공정","https://i.scdn.co/image/ab67616100005174b72080930d557c284e7d4fe1"),
                new ArtistPic(D2,"오마르와 동방전력","https://i.scdn.co/image/ab676161000051740f6cc03960ac70a4c4331037"),
                new ArtistPic(D2,"서울전자음악단","https://i.scdn.co/image/ab6761610000517426158530645531be8d86fb44"),
                new ArtistPic(D2,"로다운30","https://i.scdn.co/image/ab67616d00001e0253f722c1eb02b7958fc44a45"),


                new ArtistPic(D3,"윤마치","https://i.scdn.co/image/ab67616100005174ba03c2512927b6ffea90246e"),
                new ArtistPic(D3,"한로로","https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa"),
                new ArtistPic(D3,"AUDREY NUNA","https://i.scdn.co/image/ab676161000051742e9ac1a1188624174e9c86a3"),
                new ArtistPic(D3,"Balming Tiger (Band Set)","https://i.scdn.co/image/ab676161000051740d572a9f305a947823d4e0d6"),
                new ArtistPic(D3,"이승윤","https://i.scdn.co/image/ab67616100005174d85e6231924d1e0df9a6e9a9"),
                new ArtistPic(D3,"자우림","https://i.scdn.co/image/ab67616100005174aa6728579a1ee2dbb423e89d"),
                new ArtistPic(D3,"Beck","https://i.scdn.co/image/ab676161000051741b7aaeedafb29af4c513ebd3"),

                new ArtistPic(D3,"극동아시아타이거즈","https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6"),
                new ArtistPic(D3,"송소희","https://i.scdn.co/image/ab67616100005174c273b094c74f1151f1ef79ca"),
                new ArtistPic(D3,"Brandy Senki","https://i.scdn.co/image/ab67616100005174f81d8fba5afff369112b9fa9"),
                new ArtistPic(D3,"LUCY","https://i.scdn.co/image/ab676161000051741beb58b4d3c7a6f60897c99b"),
                new ArtistPic(D3,"Touché Amoré(투셰 아모레)","https://i.scdn.co/image/ab67616100005174daa3d788f131222ce2d07e43"),
                new ArtistPic(D3,"김민규(델리스파이스, 스위트피)","https://i.scdn.co/image/ab67616d00001e029ee364d7aacc7ae61af96b2b"),
                new ArtistPic(D3,"3호선 버터플라이","https://i.scdn.co/image/ab67616100005174aba03eccf4f7f5150d5e5f98"),

                new ArtistPic(D3,"심아일랜드","https://i.scdn.co/image/ab67616100005174dfbfe9f0ef235029b439f127"),
                new ArtistPic(D3,"컨파인드 화이트","https://i.scdn.co/image/ab676161000051745d0fa877d472f35ca52060c9"),
                new ArtistPic(D3,"데카당","https://i.scdn.co/image/ab67616d00001e020d06cf9a79f6dfc263fd6e07"),
                new ArtistPic(D3,"Milledenials","https://i.scdn.co/image/ab67616100005174e455959379957f8ee9aeea7d"),
                new ArtistPic(D3,"밀레나","https://i.scdn.co/image/ab67616100005174f7a24a6e14e58a3106344603")
        ));



        fes.setFesLinks(List.of(
                new Relate("NHN티켓링크","https://www.ticketlink.co.kr/product/56122"),
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1410996"),
                new Relate("엔티켓","https://www.enticket.com:469/pfm/sub01_view.html?pfmIng=1&p_idx=3777"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004977")
        ));


        repository.save(fes);

        PerformanceDetail bml = PerformanceDetail.builder()
                .mt20id("PF260433")
                .prfnm("뷰티풀 민트 라이프 BML 2025")
                .prfpdfrom(LocalDate.of(2025, 6, 13))
                .prfpdto(LocalDate.of(2025, 6, 15))
                .fcltynm("올림픽공원 일대")
                .locationUrl("https://www.google.com/maps/place/%EC%98%AC%EB%A6%BC%ED%94%BD%EA%B3%B5%EC%9B%90/data=!4m10!1m2!2m1!1z7Jis66a87ZS96rO17JuQIOydvOuMgA!3m6!1s0x357ca5610ed8f483:0x5e38157c0815a26f!8m2!3d37.5206868!4d127.1214941!15sChbsmKzrprztlL3qs7Xsm5Ag7J2864yAWhkiF-yYrOumvO2UvSDqs7Xsm5Ag7J2864yAkgEJY2l0eV9wYXJrmgEjQ2haRFNVaE5NRzluUzBWSlEwRm5TVU5FYVRoZlYxZEJFQUXgAQD6AQQIABAY!16zL20vMDk1OWM5?entry=ttu&g_ep=EgoyMDI1MTEyMy4xIKXMDSoASAFQAw%3D%3D")
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



        LocalDate bmlD1 = LocalDate.of(2025, 6, 13);
        LocalDate bmlD2 = LocalDate.of(2025, 6, 14);
        LocalDate bmlD3 = LocalDate.of(2025, 6, 15);

        bml.setDays(List.of(
                new DayInfo(bmlD1, LocalTime.of(13,0), LocalTime.of(22,30)),
                new DayInfo(bmlD2, LocalTime.of(12,0), LocalTime.of(22,30)),
                new DayInfo(bmlD3, LocalTime.of(12,0), LocalTime.of(22,30))
        ));


        List<Slot> bmlall = new ArrayList<>();




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


        bml.setArtistPics(List.of(


                new ArtistPic(bmlD1, "Hi-Fi Un!corn", "https://i.scdn.co/image/ab676161000051741ba4c4a78404ac6096eb79d7"),
                new ArtistPic(bmlD1, "유주", "https://i.scdn.co/image/ab6761610000517491af7b7bd63249afa27f1664"),
                new ArtistPic(bmlD1, "오월오일", "https://i.scdn.co/image/ab67616100005174b161213aec6cd03e475d325a"),
                new ArtistPic(bmlD1, "SAM KIM", "https://i.scdn.co/image/ab6761610000517418c434d49d7d2f82199fde6d"),
                new ArtistPic(bmlD1, "선우정아", "https://i.scdn.co/image/ab67616100005174de3d93419c1abbc27fd8c57d"),
                new ArtistPic(bmlD1, "터치드", "https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f"),


                new ArtistPic(bmlD1, "AxMxP(with 이승협, 서상욱 of N.Flying)", "https://i.scdn.co/image/ab67616100005174d39e0c76068ce6f4892fd653"),
                new ArtistPic(bmlD1, "글렌체크", "https://i.scdn.co/image/ab6761610000517452bf23dd85fe7d4cd09ee31b"),
                new ArtistPic(bmlD1, "THE SOLUTIONS", "https://i.scdn.co/image/ab6761610000517411f1a57c3971193b1305e018"),
                new ArtistPic(bmlD1, "QWER", "https://i.scdn.co/image/ab67616100005174eb24d4a27b5a24af1cfcc24b"),
                new ArtistPic(bmlD1, "이승윤", "https://i.scdn.co/image/ab67616100005174d85e6231924d1e0df9a6e9a9"),
                new ArtistPic(bmlD1, "YB", "https://i.scdn.co/image/ab676161000051744a10fa9cabfb1c7e0904d336"),

                new ArtistPic(bmlD1, "orange flavored cigarettes", "https://i.scdn.co/image/ab67616100005174119994f3592077601d86410a"),
                new ArtistPic(bmlD1, "김승주", "https://i.scdn.co/image/ab67616100005174d4f8bee84f26ba38f279d1c3"),
                new ArtistPic(bmlD1, "DASUTT", "https://i.scdn.co/image/ab676161000051748fa46d286c0342ecfe0b3bed"),
                new ArtistPic(bmlD1, "황가람", "https://i.scdn.co/image/ab6761610000517492ac15e86b2a58f863795b94"),
                new ArtistPic(bmlD1, "옥상달빛", "https://i.scdn.co/image/ab67616100005174b638de41fb3322811b1f6a59"),
                new ArtistPic(bmlD1, "하동균", "https://i.scdn.co/image/ab67616100005174c9df035b4a4fb5ff110d50fd"),



                new ArtistPic(bmlD2, "드래곤 포니", "https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055"),
                new ArtistPic(bmlD2, "방예담", "https://i.scdn.co/image/ab6761610000517466c3e4fa90021cb3de9f6071"),
                new ArtistPic(bmlD2, "소수빈", "https://i.scdn.co/image/ab6761610000517413a4250d79cc5ca2bf34e00a"),
                new ArtistPic(bmlD2, "소란", "https://i.scdn.co/image/ab67616100005174bdfe5343fd9232ceb36c2a3c"),
                new ArtistPic(bmlD2, "하현상", "https://i.scdn.co/image/ab676161000051743ee9650923619b6121f19976"),
                new ArtistPic(bmlD2, "정승환", "https://i.scdn.co/image/ab6761610000517470e421846bdd71847716d87d"),

                new ArtistPic(bmlD2, "O.O.O", "https://i.scdn.co/image/ab67616d00001e02422177edd214cf05a5f410a9"),
                new ArtistPic(bmlD2, "리도어", "https://i.scdn.co/image/ab676161000051744274ddd6297e90ca0398925e"),
                new ArtistPic(bmlD2, "너드커넥션", "https://i.scdn.co/image/ab67616100005174819f0c631089e5f09d09eec2"),
                new ArtistPic(bmlD2, "페퍼톤스", "https://i.scdn.co/image/ab676161000051741a8335eb603a0276ec9c9698"),
                new ArtistPic(bmlD2, "N.Flying", "https://i.scdn.co/image/ab67616100005174b0c673c1725eecd702b92cf2"),
                new ArtistPic(bmlD2, "실리카겔", "https://i.scdn.co/image/ab67616100005174017f7a68d770a2f115264068"),

                new ArtistPic(bmlD2, "OWAVE", "https://i.scdn.co/image/ab676161000051746de178407eae41ebed633469"),
                new ArtistPic(bmlD2, "이강승", "https://i.scdn.co/image/ab67616100005174c4718023da810700ddb2f673"),
                new ArtistPic(bmlD2, "까치산", "https://i.scdn.co/image/ab676161000051749e194f1c7ae66dd38d01ee26"),
                new ArtistPic(bmlD2, "dori", "https://i.scdn.co/image/ab67616100005174a0e9073506303eaa13d8b510"),
                new ArtistPic(bmlD2, "권순관", "https://i.scdn.co/image/ab67616100005174f49e54dd3c327ed3c248598a"),
                new ArtistPic(bmlD2, "george", "https://i.scdn.co/image/ab67616100005174487b00fa17362b8eab3b16c8"),



                new ArtistPic(bmlD3, "우석", "https://i.scdn.co/image/ab676161000051743d06ce43e41ccc95393ddd3c"),
                new ArtistPic(bmlD3, "한로로", "https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa"),
                new ArtistPic(bmlD3, "유다빈밴드", "https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a"),
                new ArtistPic(bmlD3, "로이킴", "https://i.scdn.co/image/ab676161000051740b9c2ebfa304077c9996d2c9"),
                new ArtistPic(bmlD3, "김성규", "https://i.scdn.co/image/ab67616100005174ed66ec72dc255d33b3a31fe5"),
                new ArtistPic(bmlD3, "윤하", "https://i.scdn.co/image/ab67616100005174f5e315e40a6d4ffd36c10d94"),

                new ArtistPic(bmlD3, "구원찬", "https://i.scdn.co/image/ab676161000051740b228a19f14992d3ab7d9a08"),
                new ArtistPic(bmlD3, "Colde", "https://i.scdn.co/image/ab676161000051740d70006fb7127e182921ecd7"),
                new ArtistPic(bmlD3, "적재", "https://i.scdn.co/image/ab676161000051743739afa8c364e092180d78c1"),
                new ArtistPic(bmlD3, "이석훈", "https://i.scdn.co/image/ab6761610000517478e4f933019aa0c255f280c5"),
                new ArtistPic(bmlD3, "10CM", "https://i.scdn.co/image/ab676161000051742445af13cb1a8132388cd566"),
                new ArtistPic(bmlD3, "다비치", "https://i.scdn.co/image/ab6761610000517440e9bd998a98de7a1c1ef354"),

                new ArtistPic(bmlD3, "Dept", "https://i.scdn.co/image/ab67616100005174e842b352c653ce12f6feeb2c"),
                new ArtistPic(bmlD3, "연정", "https://i.scdn.co/image/ab67616100005174962eef550432f20b9ae44bc7"),
                new ArtistPic(bmlD3, "안다영", "https://i.scdn.co/image/ab676161000051748be75955b0b59d8cbbb0be5f"),
                new ArtistPic(bmlD3, "오존", "https://i.scdn.co/image/ab67616100005174bf92f84cbfdbe0189457d19e"),
                new ArtistPic(bmlD3, "브로콜리너마저", "https://i.scdn.co/image/ab676161000051747c6a48a91cd0013a8bfb7490"),
                new ArtistPic(bmlD3, "데이먼스 이어", "https://i.scdn.co/image/ab67616100005174341c85848c698e505bb967b4")
        ));


        bml.setFesLinks(List.of(
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1385883"),
                new Relate("예스24","http://ticket.yes24.com/Special/52978"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002850")
        ));


        repository.save(bml);

        PerformanceDetail dmz = PerformanceDetail.builder()
                .mt20id("PF259515")
                .prfnm("DMZ 피스트레인 뮤직 페스티벌")
                .prfpdfrom(LocalDate.of(2025, 6, 14))
                .prfpdto(LocalDate.of(2025, 6, 15))
                .fcltynm("고석정 (고석정)")
                .locationUrl("https://www.google.com/maps/place/%EA%B3%A0%EC%84%9D%EC%A0%95/data=!4m10!1m2!2m1!1z6rOg7ISd7KCV!3m6!1s0x357d353025fc9605:0x6133f3c1b400dd78!8m2!3d38.1852381!4d127.2879559!15sCgnqs6DshJ3soJWSARNoaXN0b3JpY2FsX2xhbmRtYXJr4AEA!16s%2Fg%2F1211r9mj?entry=ttu&g_ep=EgoyMDI1MTEyMy4xIKXMDSoASAFQAw%3D%3D")
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



        LocalDate dmzD1 = LocalDate.of(2025, 6, 14);
        LocalDate dmzD2 = LocalDate.of(2025, 6, 15);


        dmz.setDays(List.of(
                new DayInfo(dmzD1, LocalTime.of(13, 0), LocalTime.of(00, 0)),
                new DayInfo(dmzD2, LocalTime.of(13, 0), LocalTime.of(22, 0))
        ));



        List<Slot> dmzAll = new ArrayList<>();


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

        dmzAll.add(new Slot(dmzD1,"LAND","Land Stage",1,"HITECH",
                LocalTime.of(0,20), LocalTime.of(1,10),50,null,null));


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
                LocalTime.of(23,55),LocalTime.of(00,15),20,null,null));



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



        dmz.setArtistPics(List.of(

                new ArtistPic(dmzD1, "단편선순간들", "https://i.scdn.co/image/ab676161000051749254e46ccc745637ff9caa89"),
                new ArtistPic(dmzD1, "초록불꽃소년단", "https://i.scdn.co/image/ab67616100005174b1da352f0fe336747532f929"),
                new ArtistPic(dmzD1, "구남과여라이딩스텔라", "https://i.scdn.co/image/ab67616100005174cf1ef82de88f7c0bce441d24"),
                new ArtistPic(dmzD1, "MINAMI DEUTSGH", "https://i.scdn.co/image/ab67616100005174c811072c80372b2ce71dc0b6"),
                new ArtistPic(dmzD1, "바보", "https://i.scdn.co/image/ab6761610000517427c199b958fe9fe72878044e"),
                new ArtistPic(dmzD1, "TELEPOPMUSIK (DJ SET)", "https://i.scdn.co/image/ab67616100005174d179261028d531db338bc227"),
                new ArtistPic(dmzD1, "HITECH", "https://i.scdn.co/image/ab676161000051741bb067080d7127c0156a5020"),

                new ArtistPic(dmzD1, "사뮈", "https://i.scdn.co/image/ab6761610000517404c9dd6e411a30e5a3fd8437"),
                new ArtistPic(dmzD1, "THE CHAIRS", "https://i.scdn.co/image/ab676161000051747ae6d77815389271766a5551"),
                new ArtistPic(dmzD1, "수민", "https://i.scdn.co/image/ab676161000051744b924be9b846ac20bfecc676"),
                new ArtistPic(dmzD1, "김현철", "https://i.scdn.co/image/ab67616d00001e0201d0508f13c7fd7fbf459701"),
                new ArtistPic(dmzD1, "김민규", "https://i.scdn.co/image/ab67616100005174c53e9ff3b9cc710cdc745fda"),
                new ArtistPic(dmzD1, "KO SHIN MOON", "https://i.scdn.co/image/ab676161000051745eaa1dad6c99f500fdcf6d7e"),


                new ArtistPic(dmzD2, "놀이도감", "https://i.scdn.co/image/ab676161000051740901b760be31d8c75b542a7e"),
                new ArtistPic(dmzD2, "TENDOUJI", "https://i.scdn.co/image/ab676161000051745b9e1e917d482a5c7b039a57"),
                new ArtistPic(dmzD2, "김뜻돌", "https://i.scdn.co/image/ab67616100005174cefd8bc952024635511d6b8b"),
                new ArtistPic(dmzD2, "LAMBRINI GIRLS", "https://i.scdn.co/image/ab67616100005174451fcef9face9f013f488a91"),
                new ArtistPic(dmzD2, "JAPANESE BREAKFAST", "https://i.scdn.co/image/ab67616100005174679cee0110b7cc128a496431"),

                new ArtistPic(dmzD2, "크리스피", "https://i.scdn.co/image/ab67616100005174b4c44e22e0c3d6a80112a0b1"),
                new ArtistPic(dmzD2, "주영", "https://i.scdn.co/image/ab676161000051745eb4b4b9f010b5d097da7f64"),
                new ArtistPic(dmzD2, "ALI", "https://i.scdn.co/image/ab67616100005174375ece7b58b0ece96cfbf8d7"),
                new ArtistPic(dmzD2, "지소쿠리클럽", "https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c"),
                new ArtistPic(dmzD2, "와와와", "https://i.scdn.co/image/ab67616100005174705802c7f0efea8efe367c6c"),
                new ArtistPic(dmzD2, "사랑과 평화", "https://i.scdn.co/image/ab67616d00001e02cbe5fb013449be4788a2113c")
        ));


        dmz.setFesLinks(List.of(
                new Relate("NHN티켓링크","http://www.ticketlink.co.kr/product/55737"),
                new Relate("멜론티켓","https://ticket.melon.com/performance/index.htm?prodId=211065")
        ));


        repository.save(dmz);

        PerformanceDetail apf = PerformanceDetail.builder()
                .mt20id("PF264505")
                .prfnm("아시안 팝 페스티벌 (ASIAN POP FESTIVAL)")
                .prfpdfrom(LocalDate.of(2025, 6, 21))
                .prfpdto(LocalDate.of(2025, 6, 22))
                .fcltynm("파라다이스시티 (컬처파크 (야외))")
                .locationUrl("https://www.google.com/maps/place/%EC%9D%B8%EC%8A%A4%ED%8C%8C%EC%9D%B4%EC%96%B4+%EC%97%94%ED%84%B0%ED%85%8C%EC%9D%B8%EB%A8%BC%ED%8A%B8+%EB%A6%AC%EC%A1%B0%ED%8A%B8/data=!4m12!1m2!2m1!1z7YyM652864uk7J207Iqk7Iuc7Yuw!3m8!1s0x357c9f976f60b0e1:0x35b3cd3163505ca2!5m2!4m1!1i2!8m2!3d37.4656729!4d126.3891461!16s%2Fg%2F11j8v_9yjs?entry=ttu&g_ep=EgoyMDI1MTEyMy4xIKXMDSoASAFQAw%3D%3D")
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


        LocalDate apfD1 = LocalDate.of(2025, 6, 21);
        LocalDate apfD2 = LocalDate.of(2025, 6, 22);

        apf.setDays(List.of(
                new DayInfo(apfD1, LocalTime.of(11, 0), LocalTime.of(23, 0)),
                new DayInfo(apfD2, LocalTime.of(11, 0), LocalTime.of(23, 0))
        ));


        List<Slot> apfall = new ArrayList<>();



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


        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"팻햄스터 & 캉누",
                LocalTime.of(17,0), LocalTime.of(17,45),45,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"TOFUBEATS",
                LocalTime.of(18,30), LocalTime.of(19,20),50,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"키라라",
                LocalTime.of(20,0), LocalTime.of(20,50),50,null,null));
        apfall.add(new Slot(apfD1,"CHROMA","Chroma Stage",4,"이디오테잎",
                LocalTime.of(21,50), LocalTime.of(22,50),60,null,null));


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


        apf.setArtistPics(List.of(

                new ArtistPic(apfD1, "킹스턴 루디스카", "https://i.scdn.co/image/ab67616100005174eeae1288e35f69be560f8310"),
                new ArtistPic(apfD1, "DOUDOU", "https://i.scdn.co/image/ab67616d00001e02e27a323a1c0deb30f78146b5"),
                new ArtistPic(apfD1, "갤럭시 익스프레스", "https://i.scdn.co/image/ab67616100005174e2f24a69c45dca26bf77f2df"),
                new ArtistPic(apfD1, "MONO NO AWARE", "https://i.scdn.co/image/ab6761610000517477044def4be62211510e3c11"),
                new ArtistPic(apfD1, "더 발룬티어스", "https://i.scdn.co/image/ab676161000051749405d31654d5b4afcc3dba93"),
                new ArtistPic(apfD1, "실리카겔", "https://i.scdn.co/image/ab67616100005174017f7a68d770a2f115264068"),

                new ArtistPic(apfD1, "팔칠댄스", "https://i.scdn.co/image/ab676161000051741102807c50ffbe3b733e8b27"),
                new ArtistPic(apfD1, "GDJYB", "https://i.scdn.co/image/ab676161000051746800111793b70e9d8b1b372c"),
                new ArtistPic(apfD1, "할로우잼", "https://i.scdn.co/image/ab67616100005174b645c02cfa05103299775097"),
                new ArtistPic(apfD1, "LOMBA SIHIR", "https://i.scdn.co/image/ab67616100005174c8275e79110bd3649e979975"),
                new ArtistPic(apfD1, "MEI EHARA", "https://i.scdn.co/image/ab67616100005174fb56eb913ef8e432bff03634"),
                new ArtistPic(apfD1, "EGO-WRAPPIN''", "https://i.scdn.co/image/ab676161000051743479cbebe78f125e691689f6"),
                new ArtistPic(apfD1, "새소년", "https://i.scdn.co/image/ab6761610000517427781ac76c7bb43ec6c7d4b2"),

                new ArtistPic(apfD1, "김푸름", "https://i.scdn.co/image/ab67616100005174a35563ab23618e9c3707f9ae"),
                new ArtistPic(apfD1, "PHOEBE RINGS", "https://i.scdn.co/image/ab67616100005174baaa6e10e14ae67840b635bf"),
                new ArtistPic(apfD1, "ENNO CHENG", "https://i.scdn.co/image/ab67616100005174f3483ac01fa623c8fcd76342"),
                new ArtistPic(apfD1, "신인류", "https://i.scdn.co/image/ab676161000051746c49e26d69e5a66a94d3ead8"),
                new ArtistPic(apfD1, "다브다", "https://i.scdn.co/image/ab67616100005174aeee9e7d0fec294473fd5a0e"),
                new ArtistPic(apfD1, "HAKU.", "https://i.scdn.co/image/ab67616d00001e02e7c7224f073cbe3a4624e07a"),
                new ArtistPic(apfD1, "솔루션스", "https://i.scdn.co/image/ab6761610000517411f1a57c3971193b1305e018"),

                new ArtistPic(apfD1, "팻햄스터 & 칸누", "https://i.scdn.co/image/ab676161000051745f24b3c8fd7e66d0433803cd"),
                new ArtistPic(apfD1, "TOFUBEATS", "https://i.scdn.co/image/ab67616100005174b5cbb83cf6327d565af2ea89"),
                new ArtistPic(apfD1, "키라라", "https://i.scdn.co/image/ab67616100005174146aff7e2dd783b32f0421fd"),
                new ArtistPic(apfD1, "이디오테잎", "https://i.scdn.co/image/ab6761610000517494af74a6b2652f01a728494d"),


                new ArtistPic(apfD2, "아디오스 오디오", "https://i.scdn.co/image/ab67616100005174223197ec1dc3dd39035f9cbf"),
                new ArtistPic(apfD2, "SUNKISSED LOLA", "https://i.scdn.co/image/ab676161000051746fea7eb7daf80ad5ad0508d1"),
                new ArtistPic(apfD2, "너드커넥션", "https://i.scdn.co/image/ab67616100005174819f0c631089e5f09d09eec2"),
                new ArtistPic(apfD2, "YOGEE NEW WAVES", "https://i.scdn.co/image/ab67616100005174305a8a1786645cac9cad57f4"),
                new ArtistPic(apfD2, "이승윤", "https://i.scdn.co/image/ab67616100005174d85e6231924d1e0df9a6e9a9"),
                new ArtistPic(apfD2, "자우림", "https://i.scdn.co/image/ab67616100005174aa6728579a1ee2dbb423e89d"),

                new ArtistPic(apfD2, "극동아시아타이거즈", "https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6"),
                new ArtistPic(apfD2, "시라카미 우즈", "https://i.scdn.co/image/ab6761610000517423ef62005cc37785c6bb4bde"),
                new ArtistPic(apfD2, "HIPERSON", "https://i.scdn.co/image/ab67616100005174af1bd35f6d2c6c877aa194ad"),
                new ArtistPic(apfD2, "백현진", "https://i.scdn.co/image/ab67616100005174122d573b9a024bfc1e5bd790"),
                new ArtistPic(apfD2, "BIALYSTOCKS", "https://i.scdn.co/image/ab6761610000517493ca0fd384e5e761ea675597"),
                new ArtistPic(apfD2, "LAMP", "https://i.scdn.co/image/ab67616d00001e024fd645258bc26c0b1b1619a4"),
                new ArtistPic(apfD2, "장기하", "https://i.scdn.co/image/ab67616100005174f99ecf9f9a5e0e62d01b10a6"),

                new ArtistPic(apfD2, "고고학", "https://i.scdn.co/image/ab6761610000517433857eb4372f003638403e15"),
                new ArtistPic(apfD2, "지윤해", "https://i.scdn.co/image/ab67616100005174e6adfa515135ee2f00a9e4f5"),
                new ArtistPic(apfD2, "더 보울스", "https://i.scdn.co/image/ab676161000051747456f88637e7eb6417fe1d8f"),
                new ArtistPic(apfD2, "MINDFREAKKK", "https://i.scdn.co/image/ab676161000051745cb7e5ce9f464941599f0a5a"),
                new ArtistPic(apfD2, "허회경", "https://i.scdn.co/image/ab67616100005174c3f3e653ad5e4f26f0b18d6c"),
                new ArtistPic(apfD2, "원디시티", "https://i.scdn.co/image/ab67616100005174463a54d1e2ee8c178f92ef1a"),
                new ArtistPic(apfD2, "데이먼스 이어", "https://i.scdn.co/image/ab67616100005174341c85848c698e505bb967b4"),

                new ArtistPic(apfD2, "와이2케이92", "https://i.scdn.co/image/ab6761610000517449660015d7968cfbe6fbd1b1"),
                new ArtistPic(apfD2, "힙노시스테라피", "https://i.scdn.co/image/ab676161000051741a6cc6e3939ab528f3b4feea"),
                new ArtistPic(apfD2, "마인드 컴바인드", "https://i.scdn.co/image/ab67616d00001e02032def96150e601d1d5963c3"),
                new ArtistPic(apfD2, "J-TONG", "https://i.scdn.co/image/ab67616d00001e02d451a00b62c38b5c9d85f1eb"),
                new ArtistPic(apfD2, "엠씨 스나이퍼", "https://i.scdn.co/image/ab67616d00001e027e99f442de3bc47432dc64f3"),
                new ArtistPic(apfD2, "가리온", "https://i.scdn.co/image/ab67616d00001e020a94cd874d2c997d218927cb")
        ));


        apf.setFesLinks(List.of(
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002190")
        ));


        repository.save(apf);

        PerformanceDetail gmf = PerformanceDetail.builder()
                .mt20id("PF269741")
                .prfnm("그랜드 민트 페스티벌 2025")
                .prfpdfrom(LocalDate.of(2025, 10, 18))
                .prfpdto(LocalDate.of(2025, 10, 19))
                .fcltynm("올림픽공원 (일대)")
                .locationUrl("google.com/maps/place/올림픽공원/data=!4m6!3m5!1s0x357ca5610ed8f483:0x5e38157c0815a26f!8m2!3d37.5206868!4d127.1214941!16zL20vMDk1OWM5?entry=ttu&g_ep=EgoyMDI1MTEyMy4xIKXMDSoASAFQAw%3D%3D")
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



        LocalDate gmfD1 = LocalDate.of(2025, 10, 18);
        LocalDate gmfD2 = LocalDate.of(2025, 10, 19);

        gmf.setDays(List.of(
                new DayInfo(gmfD1, LocalTime.of(12, 0), LocalTime.of(22, 0)),
                new DayInfo(gmfD2, LocalTime.of(12, 0), LocalTime.of(22, 0))
        ));


        List<Slot> gmfAll = new ArrayList<>();




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



        gmf.setArtistPics(List.of(


                new ArtistPic(gmfD1, "까치산", "https://i.scdn.co/image/ab676161000051749e194f1c7ae66dd38d01ee26"),
                new ArtistPic(gmfD1, "george", "https://i.scdn.co/image/ab67616100005174487b00fa17362b8eab3b16c8"),
                new ArtistPic(gmfD1, "폴킴", "https://i.scdn.co/image/ab676161000051749aeb37f19398d3028ef58563"),
                new ArtistPic(gmfD1, "정승환", "https://i.scdn.co/image/ab6761610000517470e421846bdd71847716d87d"),
                new ArtistPic(gmfD1, "적재", "https://i.scdn.co/image/ab676161000051743739afa8c364e092180d78c1"),
                new ArtistPic(gmfD1, "AKMU", "https://i.scdn.co/image/ab6761610000517400beb181c5f5464f4562f90f"),

                new ArtistPic(gmfD1, "can’t be blue", "https://i.scdn.co/image/ab67616d00001e0266cd86bd4090d6e73e2bb8bb"),
                new ArtistPic(gmfD1, "김뜻돌", "https://i.scdn.co/image/ab67616100005174cefd8bc952024635511d6b8b"),
                new ArtistPic(gmfD1, "유다빈밴드", "https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a"),
                new ArtistPic(gmfD1, "Daybreak", "https://i.scdn.co/image/ab67616100005174e3d33c5c838f348d1491dca5"),
                new ArtistPic(gmfD1, "터치드", "https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f"),
                new ArtistPic(gmfD1, "LUCY", "https://i.scdn.co/image/ab676161000051741beb58b4d3c7a6f60897c99b"),

                new ArtistPic(gmfD1, "KIK", "https://i.scdn.co/image/ab67616d00001e02cf865feac24754b17b12886b"),
                new ArtistPic(gmfD1, "리도어", "https://i.scdn.co/image/ab676161000051744274ddd6297e90ca0398925e"),
                new ArtistPic(gmfD1, "TELEVISION OFF", "https://i.scdn.co/image/ab676161000051743c22671e302fb04bc7564b86"),
                new ArtistPic(gmfD1, "지소쿠리클럽", "https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c"),
                new ArtistPic(gmfD1, "오월오일", "https://i.scdn.co/image/ab67616100005174b161213aec6cd03e475d325a"),
                new ArtistPic(gmfD1, "페퍼톤스", "https://i.scdn.co/image/ab676161000051741a8335eb603a0276ec9c9698"),
                new ArtistPic(gmfD1, "실리카겔", "https://i.scdn.co/image/ab67616100005174017f7a68d770a2f115264068"),

                new ArtistPic(gmfD1, "우예린", "https://i.scdn.co/image/ab676161000051749b537bc120117344d165324d"),
                new ArtistPic(gmfD1, "GEMINI", "https://i.scdn.co/image/ab67616100005174232b2f32c2d0395dadbe2618"),
                new ArtistPic(gmfD1, "김수영", "https://i.scdn.co/image/ab676161000051741e48d0250af615c7c84bb828"),
                new ArtistPic(gmfD1, "PAMUNGKAS", "https://i.scdn.co/image/ab67616100005174372a08067f92c816b121ac26"),
                new ArtistPic(gmfD1, "정세운", "https://i.scdn.co/image/ab6761610000517444fb51a7f1a82c5ad6c1b34b"),
                new ArtistPic(gmfD1, "소수빈", "https://i.scdn.co/image/ab6761610000517413a4250d79cc5ca2bf34e00a"),

                new ArtistPic(gmfD1, "키스누", "https://i.scdn.co/image/ab67616100005174182b6b02dd3dfa631dc26318"),
                new ArtistPic(gmfD1, "evenif", "https://i.scdn.co/image/ab676161000051744fe98030468bcdb8a51f73d1"),
                new ArtistPic(gmfD1, "컨파인드 화이트", "https://i.scdn.co/image/ab676161000051745d0fa877d472f35ca52060c9"),
                new ArtistPic(gmfD1, "민서", "https://i.scdn.co/image/ab676161000051740171ff4344958aefdb440e90"),
                new ArtistPic(gmfD1, "이준형", "https://i.scdn.co/image/ab676161000051749bcad25fa3aaffe443184bb9"),
                new ArtistPic(gmfD1, "Rolling Quartz", "https://i.scdn.co/image/ab676161000051741f65067c901a9421b2f1b0b0"),



                new ArtistPic(gmfD2, "GOGOHAWK", "https://i.scdn.co/image/ab6761610000517433857eb4372f003638403e15"),
                new ArtistPic(gmfD2, "데이먼스 이어", "https://i.scdn.co/image/ab67616100005174341c85848c698e505bb967b4"),
                new ArtistPic(gmfD2, "하동균", "https://i.scdn.co/image/ab67616100005174c9df035b4a4fb5ff110d50fd"),
                new ArtistPic(gmfD2, "멜로망스", "https://i.scdn.co/image/ab67616100005174a0e601a6151cf62e4ff2ced2"),
                new ArtistPic(gmfD2, "10CM", "https://i.scdn.co/image/ab676161000051742445af13cb1a8132388cd566"),
                new ArtistPic(gmfD2, "홍이삭", "https://i.scdn.co/image/ab676161000051746c60a7dfa8d6f9eb288331c7"),

                new ArtistPic(gmfD2, "Hi-Fi Unicorn", "https://i.scdn.co/image/ab676161000051741ba4c4a78404ac6096eb79d7"),
                new ArtistPic(gmfD2, "원위", "https://i.scdn.co/image/ab676161000051741b21a1f68146abd84f55d4c7"),
                new ArtistPic(gmfD2, "카더가든", "https://i.scdn.co/image/ab6761610000517460207000aebdb42a66f6880e"),
                new ArtistPic(gmfD2, "소란", "https://i.scdn.co/image/ab67616100005174bdfe5343fd9232ceb36c2a3c"),
                new ArtistPic(gmfD2, "CNBLUE", "https://i.scdn.co/image/ab67616100005174a52791c5191ef8c055edf551"),
                new ArtistPic(gmfD2, "윤하", "https://i.scdn.co/image/ab67616100005174f5e315e40a6d4ffd36c10d94"),

                new ArtistPic(gmfD2, "LOW HIGH LOW", "https://i.scdn.co/image/ab67616100005174010ffcd3b61bb94c03ba7cfb"),
                new ArtistPic(gmfD2, "SNAKE CHICKEN SOUP", "https://i.scdn.co/image/ab6761610000517444694e01b622299be032135b"),
                new ArtistPic(gmfD2, "Wendy Wander", "https://i.scdn.co/image/ab676161000051748754992366dfa44c6804894a"),
                new ArtistPic(gmfD2, "THE SOLUTIONS", "https://i.scdn.co/image/ab6761610000517411f1a57c3971193b1305e018"),
                new ArtistPic(gmfD2, "드래곤 포니", "https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055"),
                new ArtistPic(gmfD2, "쏜애플", "https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037"),
                new ArtistPic(gmfD2, "N.Flying", "https://i.scdn.co/image/ab67616100005174b0c673c1725eecd702b92cf2"),

                new ArtistPic(gmfD2, "OurR", "https://i.scdn.co/image/ab6761610000517443b5d6f5a82d88f918ec9f45"),
                new ArtistPic(gmfD2, "KEN", "https://i.scdn.co/image/ab676161000051747adf9fc7f58d29c92d81fe0d"),
                new ArtistPic(gmfD2, "범진", "https://i.scdn.co/image/ab676161000051743049269988dba509fb184c99"),
                new ArtistPic(gmfD2, "Michael Kaneko", "https://i.scdn.co/image/ab67616100005174125e74cd113327d0b40e2e71"),
                new ArtistPic(gmfD2, "스텔라장", "https://i.scdn.co/image/ab676161000051747c6984bebfa288d413c664af"),
                new ArtistPic(gmfD2, "너드커넥션", "https://i.scdn.co/image/ab67616100005174819f0c631089e5f09d09eec2"),

                new ArtistPic(gmfD2, "삼월생", "https://i.scdn.co/image/ab67616100005174a4e87353d26862fe38d78957"),
                new ArtistPic(gmfD2, "베리코이버니", "https://i.scdn.co/image/ab676161000051744f51fd9adb14c1757b85258f"),
                new ArtistPic(gmfD2, "blah", "https://i.scdn.co/image/ab676161000051744fbcb8d5ceeb84a0245e7305"),
                new ArtistPic(gmfD2, "공원", "https://i.scdn.co/image/ab676161000051741cec7df237f9cad5d5b99fc5"),
                new ArtistPic(gmfD2, "연정", "https://i.scdn.co/image/ab67616100005174962eef550432f20b9ae44bc7"),
                new ArtistPic(gmfD2, "박소은", "https://i.scdn.co/image/ab676161000051740461cbcc7ed52d8bd3eb66d2")
        ));



        gmf.setFesLinks(List.of(
                new Relate("네이버N예약","https://booking.naver.com/booking/12/bizes/1458221"),
                new Relate("예스24","http://ticket.yes24.com/Perf/54556"),
                new Relate("인터파크","http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010143")
        ));


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



        LocalDate birD1 = LocalDate.of(2025, 9, 26);
        LocalDate birD2 = LocalDate.of(2025, 9, 27);
        LocalDate birD3 = LocalDate.of(2025, 9, 28);

        bir.setDays(List.of(
                new DayInfo(birD1, LocalTime.of(10,0), LocalTime.of(22,0)),
                new DayInfo(birD2, LocalTime.of(10,0), LocalTime.of(22,0)),
                new DayInfo(birD3, LocalTime.of(10,0), LocalTime.of(22,0))
        ));


        List<Slot> birall = new ArrayList<>();




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


        bir.setArtistPics(List.of(


                new ArtistPic(birD1, "신인류", "https://i.scdn.co/image/ab676161000051746c49e26d69e5a66a94d3ead8"),
                new ArtistPic(birD1, "Flesh Juicer", "https://i.scdn.co/image/ab676161000051741fb0010a6c58022cbf339cf4"),
                new ArtistPic(birD1, "go!go!vanillas", "https://i.scdn.co/image/ab67616100005174b3866e9e7a7170317d29441f"),
                new ArtistPic(birD1, "한로로", "https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa"),
                new ArtistPic(birD1, "쏜애플", "https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037"),
                new ArtistPic(birD1, "자우림", "https://i.scdn.co/image/ab67616100005174aa6728579a1ee2dbb423e89d"),
                new ArtistPic(birD1, "SUEDE", "https://i.scdn.co/image/ab67616d00001e022709c8c14b2c9e6f6656120c"),
                new ArtistPic(birD1, "더 보울스", "https://i.scdn.co/image/ab676161000051747456f88637e7eb6417fe1d8f"),
                new ArtistPic(birD1, "아디오스오디오", "https://i.scdn.co/image/ab67616100005174223197ec1dc3dd39035f9cbf"),
                new ArtistPic(birD1, "오칠", "https://i.scdn.co/image/ab67616100005174707415a61581e15b73beb3ed"),
                new ArtistPic(birD1, "PITTA", "https://i.scdn.co/image/ab6761610000517471951da5cb57dca7b723873c"),
                new ArtistPic(birD1, "Xdinary Heroes", "https://i.scdn.co/image/ab67616100005174573af559d98d0c0228422910"),
                new ArtistPic(birD1, "CNBLUE", "https://i.scdn.co/image/ab67616100005174a52791c5191ef8c055edf551"),
                new ArtistPic(birD1, "NELL", "https://i.scdn.co/image/ab676161000051747ef39e8e41cfceaa7bea3153"),
                new ArtistPic(birD1, "Tokai", "https://i.scdn.co/image/ab676161000051746ec673ef72b545d864765551"),
                new ArtistPic(birD1, "W24", "https://i.scdn.co/image/ab676161000051746858260ed2e701eff5defcfb"),
                new ArtistPic(birD1, "정우", "https://i.scdn.co/image/ab67616100005174e156408abd916fe880bb7564"),
                new ArtistPic(birD1, "원위", "https://i.scdn.co/image/ab676161000051741b21a1f68146abd84f55d4c7"),
                new ArtistPic(birD1, "QWER", "https://i.scdn.co/image/ab67616100005174eb24d4a27b5a24af1cfcc24b"),
                new ArtistPic(birD1, "플라워", "https://i.scdn.co/image/ab67616d00001e02117ecdf47e2326e41fc57eac"),
                new ArtistPic(birD1, "ASH ISLAND", "https://i.scdn.co/image/ab67616100005174bfc3ca7030117895f1d13252"),
                new ArtistPic(birD1, "TAHITI 80", "https://i.scdn.co/image/ab676161000051748f33c44e82faf92d1d3ca901"),
                new ArtistPic(birD1, "낙원", "https://i.scdn.co/image/ab676161000051748389aa66c0dc91b666b43dfb"),
                new ArtistPic(birD1, "전범선과 양반들", "https://i.scdn.co/image/ab676161000051747e2ae6c1b4afd9602c001f8d"),
                new ArtistPic(birD1, "Monday Off With Bluesy", "https://i.scdn.co/image/ab67616100005174b8980043203ec91dba725264"),
                new ArtistPic(birD1, "유인원", "https://i.scdn.co/image/ab676161000051745e0ed1f4c3ed8f642020125e"),
                new ArtistPic(birD1, "KYSB", "https://i.scdn.co/image/ab67616100005174b15faca12aeda171bfa5c134"),



                new ArtistPic(birD2, "양치기소년단", "https://i.scdn.co/image/ab676161000051745427ee91088d5d16aa3d670b"),
                new ArtistPic(birD2, "리도어", "https://i.scdn.co/image/ab676161000051744274ddd6297e90ca0398925e"),
                new ArtistPic(birD2, "Sorry Youth", "https://i.scdn.co/image/ab676161000051742f1c8df70a76d4f6922e483e"),
                new ArtistPic(birD2, "바밍타이거", "https://i.scdn.co/image/ab676161000051740d572a9f305a947823d4e0d6"),
                new ArtistPic(birD2, "WANIMA", "https://i.scdn.co/image/ab676161000051743250b704a6dfb2572aa8d476"),
                new ArtistPic(birD2, "윤수일밴드", "https://i.scdn.co/image/ab67616d00001e02e93dc979c8d8b9046439a28b"),
                new ArtistPic(birD2, "THE SMASHING PUMPKINS", "https://i.scdn.co/image/ab67616100005174a7f80939b43d9efe1ca73a22"),
                new ArtistPic(birD2, "키라라", "https://i.scdn.co/image/ab67616100005174146aff7e2dd783b32f0421fd"),
                new ArtistPic(birD2, "LUCKLIFE", "https://i.scdn.co/image/ab67616100005174fbaaf02baeb7b47a06e668a5"),
                new ArtistPic(birD2, "너드커넥션", "https://i.scdn.co/image/ab67616100005174819f0c631089e5f09d09eec2"),
                new ArtistPic(birD2, "클렌체크", "https://i.scdn.co/image/ab6761610000517452bf23dd85fe7d4cd09ee31b"),
                new ArtistPic(birD2, "MIKA", "https://i.scdn.co/image/ab67616100005174303c632c4354bdf8c79fb0f9"),
                new ArtistPic(birD2, "컨파인드 화이트", "https://i.scdn.co/image/ab676161000051745d0fa877d472f35ca52060c9"),
                new ArtistPic(birD2, "Hi-Fi Unicorn", "https://i.scdn.co/image/ab676161000051741ba4c4a78404ac6096eb79d7"),
                new ArtistPic(birD2, "팔칠댄스", "https://i.scdn.co/image/ab676161000051741102807c50ffbe3b733e8b27"),
                new ArtistPic(birD2, "윤마치", "https://i.scdn.co/image/ab67616100005174ba03c2512927b6ffea90246e"),
                new ArtistPic(birD2, "muque", "https://i.scdn.co/image/ab676161000051741a6606935a281ae90f7d6f2b"),
                new ArtistPic(birD2, "위아더나잇", "https://i.scdn.co/image/ab67616100005174b87c62de5ced144370115121"),
                new ArtistPic(birD2, "짙은", "https://i.scdn.co/image/ab67616100005174df3dde724d74da69164518e6"),
                new ArtistPic(birD2, "오촌 x 카더가든", "https://i.scdn.co/image/ab676161000051742445af13cb1a8132388cd566"),
                new ArtistPic(birD2, "오아!", "https://i.scdn.co/image/ab67616100005174b2578dfae894dcb4ecf46c1b"),
                new ArtistPic(birD2, "cheap.n.sweet", "https://i.scdn.co/image/ab676161000051742fc7e4ba533c3524140fe177"),
                new ArtistPic(birD2, "Default.", "https://i.scdn.co/image/ab67616100005174e3ac5eb948e78d9285d1dbdb"),
                new ArtistPic(birD2, "HYANG", "https://i.scdn.co/image/ab67616100005174379dee67b57754094171bd9a"),
                new ArtistPic(birD2, "까치산", "https://i.scdn.co/image/ab676161000051749e194f1c7ae66dd38d01ee26"),


                new ArtistPic(birD3, "심아일랜드", "https://i.scdn.co/image/ab67616100005174dfbfe9f0ef235029b439f127"),
                new ArtistPic(birD3, "Dragon Pony", "https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055"),
                new ArtistPic(birD3, "WOODZ", "https://i.scdn.co/image/ab67616100005174a2c9b8c0aaf95a94a8a23ce1"),
                new ArtistPic(birD3, "TOKYO SKA PARADISE ORCHESTRA", "https://i.scdn.co/image/ab67616100005174396645f80beaa3f17506e478"),
                new ArtistPic(birD3, "터치드", "https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f"),
                new ArtistPic(birD3, "국카스텐", "https://i.scdn.co/image/ab67616100005174839d5151ddf105f92ec0d993"),
                new ArtistPic(birD3, "BABYMETAL", "https://i.scdn.co/image/ab676161000051748bf2dcb4312416001228a30d"),
                new ArtistPic(birD3, "다양성", "https://i.scdn.co/image/ab6761610000517478e658433e710d053e064bac"),
                new ArtistPic(birD3, "ddbb", "https://i.scdn.co/image/ab6761610000517440ee3df96537e42ca0e055a2"),
                new ArtistPic(birD3, "Slot Machine", "https://i.scdn.co/image/ab676161000051745157d7e9a252a04974622d9f"),
                new ArtistPic(birD3, "브로큰 발렌타인", "https://i.scdn.co/image/ab676161000051740e7c064a81a77f680ef335fd"),
                new ArtistPic(birD3, "Y2K", "https://i.scdn.co/image/ab67616d00001e02df8aa515a32fa61a9f49d631"),
                new ArtistPic(birD3, "이승윤", "https://i.scdn.co/image/ab67616100005174d85e6231924d1e0df9a6e9a9"),
                new ArtistPic(birD3, "PORTER ROBINSON", "https://i.scdn.co/image/ab676161000051741ac12dcb2cc4fc7c740c5e0c"),
                new ArtistPic(birD3, "AxMxP", "https://i.scdn.co/image/ab67616100005174d39e0c76068ce6f4892fd653"),
                new ArtistPic(birD3, "BØJEONG", "https://i.scdn.co/image/ab67616100005174b72080930d557c284e7d4fe1"),
                new ArtistPic(birD3, "오월오일", "https://i.scdn.co/image/ab67616100005174b161213aec6cd03e475d325a"),
                new ArtistPic(birD3, "쏠", "https://i.scdn.co/image/ab67616100005174dbe98215f4481d88eab538fc"),
                new ArtistPic(birD3, "LET ME KNOW", "https://i.scdn.co/image/ab676161000051742e28516865378be6af701867"),
                new ArtistPic(birD3, "Eir Aoi", "https://i.scdn.co/image/ab6761610000517476252cf0f56a32dc2b1f8423"),
                new ArtistPic(birD3, "소란", "https://i.scdn.co/image/ab67616100005174bdfe5343fd9232ceb36c2a3c"),
                new ArtistPic(birD3, "10CM", "https://i.scdn.co/image/ab676161000051742445af13cb1a8132388cd566"),
                new ArtistPic(birD3, "프랭클", "https://i.scdn.co/image/ab67616100005174a98d48d99189ffc3ef28f22d"),
                new ArtistPic(birD3, "나타샤", "https://i.scdn.co/image/ab67616100005174c1b4aae75fddae6c048ab338"),
                new ArtistPic(birD3, "야자수", "https://i.scdn.co/image/ab676161000051741c0732f9c3f3e4cc7f14191e"),
                new ArtistPic(birD3, "데이네버체인지", "https://i.scdn.co/image/ab676161000051749a1f36749d4e442734511e24"),
                new ArtistPic(birD3, "다다다", "https://i.scdn.co/image/ab67616100005174d88927e7dee9b6d648467606")
        ));


        bir.setFesLinks(List.of(
                new Relate("예스24","http://ticket.yes24.com/Perf/53880"),
                new Relate("인터파크","https://tickets.interpark.com/goods/25009725")
        ));


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
                        .locationUrl("https://www.google.com/maps/place/%EC%9E%A5%EC%B6%A9%EC%B2%B4%EC%9C%A1%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x357ca31a8bb4cab5:0x27d36acdc0017a8c!8m2!3d37.5581571!4d127.0068191!16s%2Fm%2F03cjlq9?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF262418_250404_101520.gif")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%AC%EB%A6%BC%ED%94%BD%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357caf89b7318f5d:0x4b425bd9440d40f8!8m2!3d37.5147044!4d127.1275107!16s%2Fg%2F11f6158r0s?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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

                PerformanceDetail.builder()
                        .mt20id("PF270921")
                        .prfnm("LOVE IN SEOUL, 새소년")
                        .prfpdfrom(LocalDate.of(2025,11,19))
                        .prfpdto(LocalDate.of(2025,11,19))
                        .fcltynm("블루스퀘어 (SOL트래블홀 (구. Mastercard Hall) )")
                        .locationUrl("https://www.google.com/maps/place/%EC%8B%A0%ED%95%9C%EC%B9%B4%EB%93%9C+SOL%ED%8E%98%EC%9D%B4+%EC%8A%A4%ED%80%98%EC%96%B4/data=!4m10!1m2!2m1!1zU09M7Yq4656Y67iU7ZmA!3m6!1s0x357c98d70ae34eed:0xe1f4597b2c971fe3!8m2!3d37.5511251!4d126.9142918!15sCg9TT0ztirjrnpjruJTtmYBaEyIRc29sIO2KuOuemOu4lCDtmYCSARdwZXJmb3JtaW5nX2FydHNfdGhlYXRlcpoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOMWFXUXlVRGxSUlJBQuABAPoBBAgAECU!16s%2Fg%2F11cncms947?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%ED%82%A8%ED%85%8D%EC%8A%A4+%EC%A0%9C1%EC%A0%84%EC%8B%9C%EC%9E%A5/data=!4m10!1m2!2m1!1z7YKo7YWN7Iqk!3m6!1s0x357c852e29ed355d:0xc052de8a15a7ba40!8m2!3d37.6696535!4d126.7449194!15sCgntgqjthY3siqSSARtleGhpYml0aW9uX2FuZF90cmFkZV9jZW50ZXLgAQA!16s%2Fg%2F11g0gd355r?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%AC%EB%A6%BC%ED%94%BD%EA%B3%B5%EC%9B%90/data=!3m1!4b1!4m6!3m5!1s0x357ca5610ed8f483:0x5e38157c0815a26f!8m2!3d37.5206868!4d127.1214941!16zL20vMDk1OWM5?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%97%91%EC%8A%A4%EC%BD%94+%EC%84%9C%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x3565e1aa3b33a607:0xca08be7329f6a0ca!8m2!3d35.9068477!4d128.613313!16s%2Fg%2F120j3nkm?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EA%B4%91%EC%A3%BC%EC%97%AC%EB%8C%80+%EC%9C%A0%EB%8B%88%EB%B2%84%EC%8B%9C%EC%95%84%EB%93%9C+%EC%B2%B4%EC%9C%A1%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x357189ec4e4f9b71:0xd62071b249d8fab!8m2!3d35.1644973!4d126.7983566!16s%2Fg%2F11gwghf0y_?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%88%EC%8A%A424+%EB%9D%BC%EC%9D%B4%EB%B8%8C%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca54b3dae6bf1:0x33f573dad3345d19!8m2!3d37.5457688!4d127.1078812!16s%2Fm%2F0nbhg85?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EA%B3%A0%EB%A0%A4%EB%8C%80%ED%95%99%EA%B5%90+%ED%99%94%EC%A0%95%EC%B2%B4%EC%9C%A1%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x357cbca30868a5a3:0xa5857bc1cddc59b0!8m2!3d37.5926949!4d127.0246979!16s%2Fg%2F1q64tm__k?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%A7%88%EA%B3%A1+LG%EC%95%84%ED%8A%B8%EC%84%BC%ED%84%B0/data=!3m1!4b1!4m6!3m5!1s0x357ca3ff21208857:0x6bac6122f3fe51ec!8m2!3d37.5650867!4d126.8295934!16s%2Fm%2F055cl_4?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%99%93%EC%B1%A0%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357c998e5a6bc20f:0x7d3b6960e76a2d41!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11t3yvh95c?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%99%93%EC%B1%A0%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357c998e5a6bc20f:0x7d3b6960e76a2d41!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11t3yvh95c?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%84%B8%EC%A2%85%EB%AC%B8%ED%99%94%ED%9A%8C%EA%B4%80+%EC%84%B8%EC%A2%85%ED%99%80/data=!4m10!1m2!2m1!1z7IS47KKFIOydjOyVheywveyekeyGjCDriITrpqzrnb0!3m6!1s0x357ca2eb510f3c45:0xb3c8d1f23ed86d50!8m2!3d37.5725618!4d126.975914!15sCiDshLjsooUg7J2M7JWF7LC97J6R7IaMIOuIhOumrOudvVolIiPshLjsooUg7J2M7JWFIOywveyekSDshowg64iE66asIOudvZIBBm11c2V1bZoBI0NoWkRTVWhOTUc5blMwVkpRMEZuVFVSbk9EVXpUa05CRUFF4AEA-gEECAAQDQ!16s%2Fg%2F1w2yxcsc?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%96%B8%EB%8D%94%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%A7%80+%7C+UNDERSTAGE/data=!3m1!4b1!4m6!3m5!1s0x357ca3b3bbbf88cd:0x9985ec875ca67a04!8m2!3d37.5366084!4d127.0006901!16s%2Fg%2F11btt5j008?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%AC%B4%EC%8B%A0%EC%82%AC+%EA%B0%9C%EB%9F%AC%EC%A7%80+(Musinsa+Garage)/data=!3m1!4b1!4m6!3m5!1s0x357c99e21f281c77:0x37b8cb2577692919!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11kc8f_3jc?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G+%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9+%EB%8C%80%EC%B9%98%EC%95%84%ED%8A%B8%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca43f6699399b:0x483e33ea5c7476a0!8m2!3d37.5063601!4d127.0654008!16s%2Fg%2F11c6tm7_p6?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%AC%B4%EC%8B%A0%EC%82%AC+%EA%B0%9C%EB%9F%AC%EC%A7%80+(Musinsa+Garage)/data=!3m1!4b1!4m6!3m5!1s0x357c99e21f281c77:0x37b8cb2577692919!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11kc8f_3jc?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%ED%82%A8%ED%85%8D%EC%8A%A4+%EC%A0%9C2%EC%A0%84%EC%8B%9C%EC%9E%A5/data=!3m1!4b1!4m6!3m5!1s0x357c85dd4fbe87e1:0x6ebdda407822335b!8m2!3d37.6650251!4d126.7419028!16s%2Fg%2F11t4724h27?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%AC%B4%EC%8B%A0%EC%82%AC+%EA%B0%9C%EB%9F%AC%EC%A7%80+(Musinsa+Garage)/data=!3m1!4b1!4m6!3m5!1s0x357c99e21f281c77:0x37b8cb2577692919!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11kc8f_3jc?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%AC%EB%A6%BC%ED%94%BD%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357caf89b7318f5d:0x4b425bd9440d40f8!8m2!3d37.5147044!4d127.1275107!16s%2Fg%2F11f6158r0s?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%85%B8%EB%93%A4%EC%84%AC+%EB%9D%BC%EC%9D%B4%EB%B8%8C%ED%95%98%EC%9A%B0%EC%8A%A4/data=!3m1!4b1!4m6!3m5!1s0x357ca1f05ebd6d4d:0x74f21daf60e7723c!8m2!3d37.5181707!4d126.9582643!16s%2Fg%2F11rgx348xy?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G+%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9+%EB%8C%80%EC%B9%98%EC%95%84%ED%8A%B8%ED%99%80/data=!4m6!3m5!1s0x357ca43f6699399b:0x483e33ea5c7476a0!8m2!3d37.5063601!4d127.0654008!16s%2Fg%2F11c6tm7_p6?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G+%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9+%EC%B6%98%EC%B2%9C+%EC%95%84%ED%8A%B8%EC%84%BC%ED%84%B0/data=!3m1!4b1!4m6!3m5!1s0x3562e6f354dc227b:0x1d44be4f1d71d09b!8m2!3d37.8741327!4d127.7023584!16s%2Fg%2F1q64f6w61?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9+%EB%B6%80%EC%82%B0/data=!3m1!4b1!4m6!3m5!1s0x3568eb60fe06d5d5:0x26628308c9abcac5!8m2!3d35.1542841!4d129.0573158!16s%2Fg%2F11fnyftyj7?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G+%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9/data=!4m6!3m5!1s0x357c98daba8f7a59:0x369e09805468d16c!8m2!3d37.550912!4d126.921127!16s%2Fg%2F11x1nfg_5?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("google.com/maps/place/롤링홀/data=!4m6!3m5!1s0x357c98d04c6b5451:0xb4eb7364af33a4e!8m2!3d37.5483606!4d126.9200362!16s%2Fg%2F1tcwtns7?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/KT%26G+%EC%83%81%EC%83%81%EB%A7%88%EB%8B%B9+%EB%8C%80%EC%B9%98%EC%95%84%ED%8A%B8%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca43f6699399b:0x483e33ea5c7476a0!8m2!3d37.5063601!4d127.0654008!16s%2Fg%2F11c6tm7_p6?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%B0%95%EC%97%B0%EB%AC%B8%ED%99%94%EA%B4%80/data=!4m10!1m2!2m1!1z7IS47KKFIOydjOyVheywveyekeyGjCDriITrpqzrnb0!3m6!1s0x357acb49363d48af:0x7c89928b28f2d89f!8m2!3d36.4985206!4d127.2638359!15sCiDshLjsooUg7J2M7JWF7LC97J6R7IaMIOuIhOumrOudvVolIiPshLjsooUg7J2M7JWFIOywveyekSDshowg64iE66asIOudvZIBD2N1bHR1cmFsX2NlbnRlcpoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VSWGRWOHllVFpSUlJBQuABAPoBBAgAEA8!16s%2Fg%2F11rb1yrq2q?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%8B%A0%ED%95%9C%EC%B9%B4%EB%93%9C+SOL%ED%8E%98%EC%9D%B4+%EC%8A%A4%ED%80%98%EC%96%B4/data=!3m1!4b1!4m6!3m5!1s0x357c98d70ae34eed:0xe1f4597b2c971fe3!8m2!3d37.5511251!4d126.9142918!16s%2Fg%2F11cncms947?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%ED%86%A4%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4/data=!4m6!3m5!1s0x357c98de617b7b8f:0xc1b5088a364f27f4!8m2!3d37.5593385!4d126.9154517!16s%2Fg%2F11ghrgs1p9?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%ED%81%B4%EB%9F%BD+%EC%98%A8%EC%97%90%EC%96%B4/data=!4m6!3m5!1s0x357c992c32da3081:0xe22e0ecd778c6b37!8m2!3d37.5522853!4d126.9188982!16s%2Fg%2F11q49dcw0p?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%A1%A4%EB%A7%81%ED%99%80/data=!4m6!3m5!1s0x357c98d04c6b5451:0xb4eb7364af33a4e!8m2!3d37.5483606!4d126.9200362!16s%2Fg%2F1tcwtns7?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("google.com/maps/place/신한카드+SOL페이+스퀘어/data=!4m6!3m5!1s0x357c98d70ae34eed:0xe1f4597b2c971fe3!8m2!3d37.5511251!4d126.9142918!16s%2Fg%2F11cncms947?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EA%B3%A0%EB%A0%A4%EB%8C%80%ED%95%99%EA%B5%90+%ED%99%94%EC%A0%95%EC%B2%B4%EC%9C%A1%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x357cbca30868a5a3:0xa5857bc1cddc59b0!8m2!3d37.5926949!4d127.0246979!16s%2Fg%2F1q64tm__k?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/3%C2%B715%EC%95%84%ED%8A%B8%EC%84%BC%ED%84%B0/data=!3m1!4b1!4m6!3m5!1s0x356f321adac299ed:0x7c834560419e0cd5!8m2!3d35.2258781!4d128.5772021!16s%2Fg%2F1q64nzk7r?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%9D%B4%ED%99%94%EC%97%AC%EC%9E%90%EB%8C%80%ED%95%99%EA%B5%90+%EC%82%BC%EC%84%B1%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357c9884f6601cdb:0x1c28aa1a14e5b02b!8m2!3d37.5614238!4d126.9469727!16s%2Fg%2F11kj8__6s2?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%88%EC%8A%A424+%EB%9D%BC%EC%9D%B4%EB%B8%8C%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca54b3dae6bf1:0x33f573dad3345d19!8m2!3d37.5457688!4d127.1078812!16s%2Fm%2F0nbhg85?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%AC%EB%A6%BC%ED%94%BD%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357caf89b7318f5d:0x4b425bd9440d40f8!8m2!3d37.5147044!4d127.1275107!16s%2Fg%2F11f6158r0s?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%ED%96%89%EB%B3%B5%EB%B6%81%EA%B5%AC%EB%AC%B8%ED%99%94%EC%9E%AC%EB%8B%A8+%EC%96%B4%EC%9A%B8%EC%95%84%ED%8A%B8%EC%84%BC%ED%84%B0/data=!3m1!4b1!4m6!3m5!1s0x3565e6e05fbd9f61:0x6dd2ce89762a4f09!8m2!3d35.9347008!4d128.5466137!16s%2Fg%2F1v9gs9zv?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%81%EB%93%B1%ED%8F%AC%EC%95%84%ED%8A%B8%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357c9ee8a8a1822f:0xeff63704940380be!8m2!3d37.525969!4d126.9000964!16s%2Fg%2F1tdx6n4v?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                                new PerformanceDetail.ArtPic("유다빈밴드","https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a" )

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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%88%EC%8A%A424+%EB%9D%BC%EC%9D%B4%EB%B8%8C%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca54b3dae6bf1:0x33f573dad3345d19!8m2!3d37.5457688!4d127.1078812!16s%2Fm%2F0nbhg85?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%AC%B4%EC%8B%A0%EC%82%AC+%EA%B0%9C%EB%9F%AC%EC%A7%80+(Musinsa+Garage)/data=!3m1!4b1!4m6!3m5!1s0x357c99e21f281c77:0x37b8cb2577692919!8m2!3d37.5515437!4d126.9197803!16s%2Fg%2F11kc8f_3jc?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%98%88%EC%8A%A424+%EB%9D%BC%EC%9D%B4%EB%B8%8C%ED%99%80/data=!3m1!4b1!4m6!3m5!1s0x357ca54b3dae6bf1:0x33f573dad3345d19!8m2!3d37.5457688!4d127.1078812!16s%2Fm%2F0nbhg85?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%A4%91%EA%B5%AC%EB%AC%B8%ED%99%94%EC%9D%98%EC%A0%84%EB%8B%B9/data=!3m1!4b1!4m6!3m5!1s0x3567ccd549ad6a05:0xd28c1fe7f20e463d!8m2!3d35.5671961!4d129.3215862!16s%2Fg%2F11byts_dx1?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EA%B0%95%EB%82%A8%EC%94%A8%EC%96%B4%ED%84%B0/data=!4m6!3m5!1s0x357ca1c816bc7d9b:0xb8ff9f07241f214f!8m2!3d37.495188!4d127.0332163!16s%2Fg%2F11p0v_w05x?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EA%B4%91%EC%A3%BC+%EC%98%88%EC%88%A0%EC%9D%98%EC%A0%84%EB%8B%B9/data=!3m1!4b1!4m6!3m5!1s0x35718c3c83f9b623:0x725705981de21dc7!8m2!3d35.1780556!4d126.8813889!16s%2Fg%2F1211wx3l?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EB%8B%AC%EC%84%9C%EC%95%84%ED%8A%B8%EC%84%BC%ED%84%B0/data=!3m1!4b1!4m6!3m5!1s0x3565e528a169fd79:0x596e7242a9ecf062!8m2!3d35.8436423!4d128.5231794!16s%2Fg%2F11bw5v3pz1?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%8B%A0%ED%95%9C%EC%B9%B4%EB%93%9C+SOL%ED%8E%98%EC%9D%B4+%EC%8A%A4%ED%80%98%EC%96%B4/data=!3m1!4b1!4m6!3m5!1s0x357c98d70ae34eed:0xe1f4597b2c971fe3!8m2!3d37.5511251!4d126.9142918!16s%2Fg%2F11cncms947?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .locationUrl("https://www.google.com/maps/place/%EC%9D%B8%EC%8A%A4%ED%8C%8C%EC%9D%B4%EC%96%B4+%EC%97%94%ED%84%B0%ED%85%8C%EC%9D%B8%EB%A8%BC%ED%8A%B8+%EB%A6%AC%EC%A1%B0%ED%8A%B8/data=!4m9!3m8!1s0x357c9f976f60b0e1:0x35b3cd3163505ca2!5m2!4m1!1i2!8m2!3d37.4656729!4d126.3891461!16s%2Fg%2F11j8v_9yjs?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
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
                        .mt20id("PF263895")
                        .prfnm("라우브 내한공연 Lauv's I Love You, Mean It Tour [대구]")
                        .prfpdfrom(LocalDate.of(2025,5,24))
                        .prfpdto(LocalDate.of(2025,5,25))
                        .fcltynm("엑스코(exco) (컨벤션홀 (5층))")
                        .locationUrl("https://www.google.com/maps/place/%EC%97%91%EC%8A%A4%EC%BD%94+%EC%84%9C%EA%B4%80/data=!3m1!4b1!4m6!3m5!1s0x3565e1aa3b33a607:0xca08be7329f6a0ca!8m2!3d35.9068477!4d128.613313!16s%2Fg%2F120j3nkm?entry=ttu&g_ep=EgoyMDI1MTIwMi4wIKXMDSoASAFQAw%3D%3D")
                        .prfruntime("2시간")
                        .prfage("만 11세 이상")
                        .pcseguidance("스탠딩  154,000원, SR  165,000원, R석 154,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263895_250425_135006.gif")
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
        repository.save(fes);
        repository.save(bml);
        repository.save(dmz);
        repository.save(apf);
        repository.save(bml);

        System.out.println(" 공연 더미데이터 초기화 완료 (" + data.size() + "건)");
    }
}