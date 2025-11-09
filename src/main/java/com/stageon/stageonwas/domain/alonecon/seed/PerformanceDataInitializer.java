package com.stageon.stageonwas.domain.alonecon.seed;


import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PerformanceDataInitializer {

    private final PerformanceDetailRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void run() {
        repository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE performance_detail AUTO_INCREMENT = 1");

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
                                new PerformanceDetail.Artpic( "검정치마", "http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF262418_250404_1015200.jpg")
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
                                new PerformanceDetail.Artpic( "검정치마", "")
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
                        .prfstate("공연예정")
                        .dtguidance("수요일(20:00)")
                        .tkstdate(LocalDate.of(2025,10,17))
                        .tksttime(LocalTime.of(19,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("새소년","")
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
                                new PerformanceDetail.Artpic("실리카겔","")
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25010454"                        )
                        ))//실리카겔
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF269185")
                        .prfnm("그룹사운드 잔나비 앵콜 콘서트: 모든소년소녀들 2125 [서울 (앵콜) ]")
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
                                new PerformanceDetail.Artpic("잔나비","" )
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
                                new PerformanceDetail.Artpic("잔나비","" )
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
                                new PerformanceDetail.Artpic("잔나비","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/56440")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF272305")
                        .prfnm("The Moment: Live on Melon, 10CM & 소란 & 데이브레이크")
                        .prfpdfrom(LocalDate.of(2025,10,10))
                        .prfpdto(LocalDate.of(2025,10,12))
                        .fcltynm("충무아트센터 (대극장)")
                        .prfruntime("1시간 30분")
                        .prfage("만 6세 이상")
                        .pcseguidance("R석 99,000원, S석 88,000원, A석 77,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF272305_250901_143112.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일 ~ 일요일(16:00), 금요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("데이브레이크","" ),
                                new PerformanceDetail.Artpic("10cm","" ),
                                new PerformanceDetail.Artpic("소란","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211798"),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25011999")
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
                                new PerformanceDetail.Artpic("데이브레이크","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/54010")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF257770")
                        .prfnm("라이브 온 서울: 루시, 데이브레이크")
                        .prfpdfrom(LocalDate.of(2025,2,23))
                        .prfpdto(LocalDate.of(2025,2,23))
                        .fcltynm("KBS스포츠월드(아레나) (KBS아레나)")
                        .prfruntime("2시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 121,000원, S석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF257770_250123_104934.jpg")
                        .prfstate("공연완료")
                        .dtguidance("일요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("데이브레이크","" ),
                                new PerformanceDetail.Artpic("LUCY","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1330577"),
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/52357")
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
                                new PerformanceDetail.Artpic("쏜애플","" )
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
                                new PerformanceDetail.Artpic("쏜애플","" )
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
                                new PerformanceDetail.Artpic("쏜애플","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25001049")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF272320")
                        .prfnm("The Moment: Live on Melon with MOAH, 3호선 버터플라이 & 봉제인간 & ANDOR")
                        .prfpdfrom(LocalDate.of(2025,10,19))
                        .prfpdto(LocalDate.of(2025,10,19))
                        .fcltynm("충무아트센터 (대극장)")
                        .prfruntime("1시간 40분")
                        .prfage("만 6세 이상")
                        .pcseguidance("R석 66,000원, S석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF272320_250821_142037.png")
                        .prfstate("공연완료")
                        .dtguidance("일요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("3호선 버터플라이","" ),
                                new PerformanceDetail.Artpic("봉제인간","" ),
                                new PerformanceDetail.Artpic("ANDOR","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211791"),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25011732")


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
                                new PerformanceDetail.Artpic("3호선 버터플라이","" )
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
                                new PerformanceDetail.Artpic("3호선 버터플라이","" )
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
                                new PerformanceDetail.Artpic("웨이브투어스","" )
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
                                new PerformanceDetail.Artpic("정우","" )
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
                                new PerformanceDetail.Artpic("정우","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211590")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF277643")
                        .prfnm("아리랑센터 기획공연 수능특집 가을콘서트: 너드 커넥션 & 옥상달빛 그리고 서커스 오브 락")
                        .prfpdfrom(LocalDate.of(2025,11,19))
                        .prfpdto(LocalDate.of(2025,11,19))
                        .fcltynm("정선아리랑센터 (아리랑홀)")
                        .prfruntime("1시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석무료")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF277643_251029_124539.png")
                        .prfstate("공연예정")
                        .dtguidance("수요일(19:00)")
                        .tkstdate(LocalDate.of(2025,10,10))
                        .tksttime(LocalTime.of(18,0))
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("너드 커넥션","" ),
                                new PerformanceDetail.Artpic("옥상달빛","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("정선아리랑문화재단",
                                        "https://www.jacf.or.kr/jacf/pageview.php?url=sub01a&keyvalue=sub01")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF274901")
                        .prfnm("Hindie LIVE Vol.2: 너드커넥션 X 루아멜 [하남]")
                        .prfpdfrom(LocalDate.of(2025,11,1))
                        .prfpdto(LocalDate.of(2025,11,1))
                        .fcltynm("하남문화예술회관 (검단홀(대극장))")
                        .prfruntime("1시간 50분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 66,000원, S석 55,000원, A석 44,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF274901_250924_142325.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("너드커넥션","" ),
                                new PerformanceDetail.Artpic("루아멜","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25013879")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF274349")
                        .prfnm("어텐션 라이브, 너드커넥션 + 밴드 히미츠: 뜻밖의 만남 [용인]")
                        .prfpdfrom(LocalDate.of(2025,11,8))
                        .prfpdto(LocalDate.of(2025,11,8))
                        .fcltynm("용인시문화예술회관 (처인홀)")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF274349_250917_141752.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("너드커넥션","" ),
                                new PerformanceDetail.Artpic("히미츠","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25012703")
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
                                new PerformanceDetail.Artpic("너드커넥션","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25008903")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF267487")
                        .prfnm("오늘도 무사히 VOL. 1: 너드커넥션x캔트비블루x에브리데이 먼데이")
                        .prfpdfrom(LocalDate.of(2025,7,25))
                        .prfpdto(LocalDate.of(2025,7,25))
                        .fcltynm("부평아트센터 (해누리극장)")
                        .prfruntime("1시간 40분")
                        .prfage("만 7세 이상")
                        .pcseguidance("1층석 50,000원, 2층석 40,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF267487_250618_113523.gif")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("너드커넥션","" ),
                                new PerformanceDetail.Artpic("캔트비블루","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25007865")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF258939")
                        .prfnm("WEE SHOW #1: 너드커넥션 X ONEWE")
                        .prfpdfrom(LocalDate.of(2025,3,8))
                        .prfpdto(LocalDate.of(2025,3,8))
                        .fcltynm("성남아트센터 (오페라하우스)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 99,000원, S석 88,000원, A석 77,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF258939_250213_103544.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("너드커넥션","" ),
                                new PerformanceDetail.Artpic("ONEWE","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211023"),
                                new PerformanceDetail.Relate("인터파크",
                                        "https://tickets.interpark.com/goods/25001838")

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
                                new PerformanceDetail.Artpic("터치드","" )
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
                                new PerformanceDetail.Artpic("터치드","" )
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
                                new PerformanceDetail.Artpic("터치드","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=24016171")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF275307")
                        .prfnm("29CM STAGE, 심아일랜드 & 극동아시아타이거즈")
                        .prfpdfrom(LocalDate.of(2025,11,23))
                        .prfpdto(LocalDate.of(2025,11,23))
                        .fcltynm("무신사 개러지 (구. 왓챠홀) (무신사 개러지 (구. 왓챠홀) )")
                        .prfruntime("1시간 30분")
                        .prfage("만 11세 이상")
                        .pcseguidance("전석 29,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF275307_250930_102110.jpg")
                        .prfstate("공연예정")
                        .dtguidance("일요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("심아일랜드","" ),
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("29CM",
                                        "https://ticket.29cm.co.kr/catalog/3541437")
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
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
                                new PerformanceDetail.Artpic("극동아시아타이거즈","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=210796")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF270971")
                        .prfnm("문화정원아트홀 10주년 기념 공연 시리즈, CULTURE STAGE: #3 해리빅버튼 x 갤럭시익스프레스")
                        .prfpdfrom(LocalDate.of(2025,8,29))
                        .prfpdto(LocalDate.of(2025,8,29))
                        .fcltynm("문화정원 아트홀 (문화정원 아트홀)")
                        .prfruntime("1시간 40분")
                        .prfage("만 14세 이상")
                        .pcseguidance("스탠딩 35,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF270971_250805_095318.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(20:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("갤럭시 익스프레스","" ),
                                new PerformanceDetail.Artpic("해리빅버튼","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=211710")
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
                                new PerformanceDetail.Artpic("단편선 순간들","" )
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
                                new PerformanceDetail.Artpic("단편선 순간들","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004415")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF260696")
                        .prfnm("살롱콘서트 휴, 단편선 순간들 X 김학선")
                        .prfpdfrom(LocalDate.of(2025,4,24))
                        .prfpdto(LocalDate.of(2025,4,24))
                        .fcltynm("인천문화예술회관 (복합문화공간 )")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 10,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF260696_250310_163257.gif")
                        .prfstate("공연완료")
                        .dtguidance("목요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("단편선 순간들","" ),
                                new PerformanceDetail.Artpic("김학선","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("앤티켓",
                                        "https://www.enticket.com:469/pfm/sub01_view.html?p_idx=3726"),
                                new PerformanceDetail.Relate("인천문화예술회관",
                                        "http://incheon9.moonhwain.net/rsvc/rsv_pm.html?b_id=incheon&p_idx=862&p_kind="),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25003179")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF266207")
                        .prfnm("라이브온 부산: 원위, 드래곤 포니")
                        .prfpdfrom(LocalDate.of(2025,6,29))
                        .prfpdto(LocalDate.of(2025,6,29))
                        .fcltynm("벡스코 (BEXCO) (오디토리움)")
                        .prfruntime("2시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 121,000원, S석 110,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF266207_250530_120318.jpg")
                        .prfstate("공연완료")
                        .dtguidance("일요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("드래곤 포니","" ),
                                new PerformanceDetail.Artpic("원위","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1424250"),
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/54097")
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
                                new PerformanceDetail.Artpic("드래곤 포니","" )
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
                                new PerformanceDetail.Artpic("서울전자음악단","" )
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
                                new PerformanceDetail.Artpic("서울전자음악단","" )
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
                                new PerformanceDetail.Artpic("서울전자음악단","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=210992")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF266085")
                        .prfnm("논산아트센터 소극장 페스타 Ⅲ 밴드 콘서트, 자우림 X 로맨틱펀치")
                        .prfpdfrom(LocalDate.of(2025,6,27))
                        .prfpdto(LocalDate.of(2025,6,27))
                        .fcltynm("논산아트센터(구. 논산문화예술회관) (대공연장)")
                        .prfruntime("2시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 20,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF266085_250529_105832.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("자우림","" ),
                                new PerformanceDetail.Artpic("로맨틱펀치","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("논산시문화예술회관(논산아트센터)",
                                        "http://nonsan.moonhwain.net/rsvc/rsv_pm.html?b_id=nonsan&p_idx=68")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF263991")
                        .prfnm("자우림 X 해음 X 최예림 콘서트: 결 (結) [평택]")
                        .prfpdfrom(LocalDate.of(2025,6,7))
                        .prfpdto(LocalDate.of(2025,6,7))
                        .fcltynm("한국소리터 (지영희홀)")
                        .prfruntime("1시간")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 10,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF263991_250428_135519.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("자우림","" ),
                                new PerformanceDetail.Artpic("해음","" ),
                                new PerformanceDetail.Artpic("최예림","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25005714")
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
                                new PerformanceDetail.Artpic("자우림","" )
                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25004634")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF258248")
                        .prfnm("자우림 & 플라워 콘서트 [강릉]")
                        .prfpdfrom(LocalDate.of(2025,2,28))
                        .prfpdto(LocalDate.of(2025,2,28))
                        .fcltynm("강릉아트센터 (구. 강릉문화예술관) (사임당홀 (대공연장) )")
                        .prfruntime("2시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 50,000원, S석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF258248_250204_115456.jpg")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("자우림","" ),
                                new PerformanceDetail.Artpic("플라워","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("강릉아트센터",
                                        "http://gn.moonhwain.net/rsvc/rsv_pm.html?b_id=gn&p_idx=1170")
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
                        .prfstate("공연예정")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("한로로","" )

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
                                new PerformanceDetail.Artpic("힌로로","" ),
                                new PerformanceDetail.Artpic("유다빈밴드","" )

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
                                new PerformanceDetail.Artpic("윤마치","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/58944")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF258691")
                        .prfnm("어썸스테이지, Xdinary Heroes Ⅹ HANRORO(한로로)")
                        .prfpdfrom(LocalDate.of(2025,2,28))
                        .prfpdto(LocalDate.of(2025,2,28))
                        .fcltynm("벡스코 (BEXCO) (오디토리움)")
                        .prfruntime("2시간 50분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 121,000원, S석 110,000원, A석 99,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF258691_250210_161012.png")
                        .prfstate("공연완료")
                        .dtguidance("금요일(19:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("한로로","" ),
                                new PerformanceDetail.Artpic("Xdinary Heroes","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/54457")
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
                                new PerformanceDetail.Artpic("한로로","" )

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
                        .prfstate("공연예정")
                        .dtguidance("토요일(18:00), 일요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("유다빈밴드","" )

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
                                new PerformanceDetail.Artpic("유다빈밴드","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/56802"),
                                new PerformanceDetail.Relate("네이버N예약",
                                        "https://booking.naver.com/booking/12/bizes/1430538")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF265102")
                        .prfnm("숲속열린음악회 with 이현공원: 노을, 로이킴 & 유다빈밴드")
                        .prfpdfrom(LocalDate.of(2025,5,17))
                        .prfpdto(LocalDate.of(2025,5,17))
                        .fcltynm("이현공원 [대구] (잔디광장)")
                        .prfruntime("3시간")
                        .prfage("만 13세 이상")
                        .pcseguidance("전석무료")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF265102_250516_153032.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("유다빈밴드","" ),
                                new PerformanceDetail.Artpic("로이킴","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("NHN티켓링크",
                                        "http://www.ticketlink.co.kr/product/55979")
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
                                new PerformanceDetail.Artpic("유다빈밴드","" )

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
                                new PerformanceDetail.Artpic("유다빈밴드","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/53075"),
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25003325")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF259778")
                        .prfnm("데이 데이 콘서트 Ⅰ. 유다빈밴드 X 범진 [안양]")
                        .prfpdfrom(LocalDate.of(2025,3,15))
                        .prfpdto(LocalDate.of(2025,3,15))
                        .fcltynm("평촌아트홀 (평촌아트홀)")
                        .prfruntime("1시간 50분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 55,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF259778_250225_113737.gif")
                        .prfstate("공연완료")
                        .dtguidance("토요일(16:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("유다빈밴드","" ),
                                new PerformanceDetail.Artpic("범진","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "http://ticket.yes24.com/Perf/53075"),
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25002416")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF258383")
                        .prfnm("반석산 피크닉: 유다빈밴드X더픽스X블루디 [화성]")
                        .prfpdfrom(LocalDate.of(2025,5,31))
                        .prfpdto(LocalDate.of(2025,5,31))
                        .fcltynm("반석아트홀 (동탄복합문화센터) (야외공연장)")
                        .prfruntime("1시간 40분")
                        .prfage("전체 관람가")
                        .pcseguidance("R석 50,000원, S석 40,000원, A석 30,000원, 잔디석(비지정석) 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF258383_250205_144046.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(17:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("유다빈밴드","" ),
                                new PerformanceDetail.Artpic("더픽스","" ),
                                new PerformanceDetail.Artpic("블루디","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25001244")
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
                                new PerformanceDetail.Artpic("유다빈밴드","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("인터파크",
                                        "http://ticket.interpark.com/Ticket/Goods/GoodsInfo.asp?GoodsCode=25000842")
                        ))
                        .build(),

                PerformanceDetail.builder()
                        .mt20id("PF256816")
                        .prfnm("High-Five! 25: 유다빈밴드 X 원위 (ONEWE)")
                        .prfpdfrom(LocalDate.of(2025,1,25))
                        .prfpdto(LocalDate.of(2025,1,25))
                        .fcltynm("은평문화예술회관 (공연장)")
                        .prfruntime("1시간 30분")
                        .prfage("만 7세 이상")
                        .pcseguidance("R석 88,000원, S석 77,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF256816_250109_100110.jpg")
                        .prfstate("공연완료")
                        .dtguidance("토요일(18:00)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("유다빈밴드","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("멜론티켓",
                                        "https://ticket.melon.com/performance/index.htm?prodId=210850")
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
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

                        ))

                        .relates(List.of(
                                new PerformanceDetail.Relate("예스24",
                                        "https://ticket.yes24.com/Perf/55818")
                        ))
                        .build(),
                PerformanceDetail.builder()
                        .mt20id("PF276160")
                        .prfnm("울산중구문화의전당 (함월홀 (2층) )")
                        .prfpdfrom(LocalDate.of(2025,11,28))
                        .prfpdto(LocalDate.of(2025,11,28))
                        .fcltynm("울산중구문화의전당 (함월홀 (2층) )")
                        .prfruntime("1시간 20분")
                        .prfage("만 7세 이상")
                        .pcseguidance("전석 30,000원")
                        .poster("http://www.kopis.or.kr/upload/pfmPoster/PF_PF276160_251015_110100.png")
                        .prfstate("공연예정")
                        .dtguidance("금요일(19:30)")
                        .tkstdate(null)
                        .tksttime(null)
                        .typeofcon(1)

                        .styurls(List.of(
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
                                new PerformanceDetail.Artpic("지소쿠리클럽","" )

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
//  .tkstdate(LocalDate.of(2025,10,10))
//  .tksttime(LocalTime.of(18,0))
// PerformanceDetail.builder()
//                        .mt20id("")
//                        .prfnm("")
//                        .prfpdfrom("")
//                        .prfpdto("")
//                        .fcltynm("")
//                        .prfruntime("")
//                        .prfage("")
//                        .pcseguidance("")
//                        .poster("")
//                        .prfstate("공연완료")
//                        .dtguidance("")
//                        .tkstdate("")
//                        .typeofcon(1)
//// 여러 이미지 출연진 URL (객체 리스트)
//                        .styurls(List.of(
//        new PerformanceDetail.Artpic( "", ""),
//                                new PerformanceDetail.Artpic("", "")
//                        ))
//                                // 여러 관련 링크
//                                .relates(List.of(
//                                                 new PerformanceDetail.Relate( "",
//                                        ""),
//                                new PerformanceDetail.Relate( "",
//                                                                      "")
//                        ))
//                                .build(),