package com.stageon.stageonwas.domain.artist.seed;


import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.repository.ArtistRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistInfoDataInitializer {

    private final ArtistRepository repository;

    @PostConstruct
    public void init() {

        // 이미 데이터가 있으면 초기화 스킵
        if (repository.count() > 0) {
            System.out.println("밴드 데이터 초기화 스킵 (이미 존재)");
            return;
        }

        List<Artist> artists = List.of(
                Artist.builder()
                        .bandName("검정치마")
                        .relateUrl("https://i.scdn.co/image/ab676161000051748609536d21beed6769d09d7f")
                        .sessionMem("조휴일 – 보컬·기타·작곡, 황재연 – 기타, 이강희 – 기타, 고경천 – 키보드, 최창우 – 베이스, 김희권 – 드럼, 윤원중 - 색소폰")
                        .introBand("검정치마(The Black Skirts)는 싱어송라이터 조휴일이 이끄는 1인 프로젝트 밴드로, 2008년 데뷔 앨범 『201』을 통해 한국 인디 록 씬의 대표적인 뮤지션으로 떠올랐습니다. 감성적인 멜로디와 솔직한 가사, 영어와 한국어를 넘나드는 곡들로 사랑받으며 『Team Baby』, 『Thirsty』, 『Teen Troubles』 등 앨범을 발표하며 꾸준히 활동하고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("혁오")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174a83b7860635940ed829ee09b")
                        .sessionMem("오혁 – 보컬·기타, 임현제 – 기타, 임동건 – 베이스, 이인우 – 드럼")
                        .introBand("혁오(HYUKOH)는 2014년 결성된 4인조 인디 록 밴드로, 〈위잉위잉〉, 〈와리가리〉, 〈TOMBOY〉 등의 곡을 통해 대중성과 음악성을 동시에 인정받았습니다. 다채로운 장르 실험과 세련된 사운드로 한국 인디 씬의 대표 밴드 중 하나로 자리했습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("새소년")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517427781ac76c7bb43ec6c7d4b2")
                        .sessionMem("황소윤 – 보컬·기타")
                        .introBand("새소년(SE SO NEON)은 황소윤을 중심으로 활동하는 인디 록 프로젝트로, 〈긴 꿈〉과 EP 『여름깃』을 통해 독창적인 사운드로 주목받았습니다. 록, 블루스, 신스팝을 아우르는 음악성을 바탕으로 한국대중음악상 수상 등 높은 평가를 받고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("실리카겔")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174017f7a68d770a2f115264068")
                        .sessionMem("김한주 – 건반·보컬, 김춘추 – 기타·보컬, 김건재 – 드럼, 최웅희 – 베이스")
                        .introBand("실리카겔(Silica Gel)은 사이키델릭 록을 기반으로 다양한 실험적인 사운드를 선보이는 4인조 밴드입니다. 독창적인 편곡과 폭발적인 라이브 퍼포먼스로 인디 씬에서 두드러진 존재감을 보여주고 있으며, 정규 2집 『POWER ANDRE 99』 등으로 음악적 스펙트럼을 넓히고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("잔나비")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174776565cc2d97c46f4d000134")
                        .sessionMem("최정훈 – 보컬·기타, 김도형 – 기타")
                        .introBand("잔나비(JANNABI)는 감성적인 멜로디와 빈티지한 밴드 사운드로 사랑받는 인디 록 밴드입니다. 현재는 최정훈과 김도형 2인 체제로 활동하고 있으며, 〈주저하는 연인들을 위해〉, 〈꿈과 책과 힘과 벽〉 등의 곡으로 대중적인 인기를 얻었습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("데이브레이크")
                        .relateUrl("https://i.scdn.co/image/ab676161000051746822d68461a20f71e3db2c30")
                        .sessionMem("이원석 – 보컬, 정유종 – 기타, 김선일 – 베이스, 김장원 – 키보드")
                        .introBand("데이브레이크(Daybreak)는 2006년 결성된 대한민국 인디 록 밴드로, 팝·락·재즈가 뒤섞인 ‘컴팩트 퓨전’ 사운드를 들려주며 2009년 ‘좋다’ 등으로 본격 대중에 이름을 알렸습니다. 멤버 전원이 세션 경력을 지닌 연주자라는 점이 특징입니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("페퍼톤스")
                        .relateUrl("https://i.scdn.co/image/ab676161000051741a8335eb603a0276ec9c9698")
                        .sessionMem("신재평 – 기타·프로그래밍, 이장원 – 베이스")
                        .introBand("페퍼톤스(Peppertones)는 2003년 대학 동문 두 명(신재평, 이장원)이 결성한 2인조 밴드로, 청춘의 낭만과 에너지를 경쾌한 사운드로 풀어내며 ‘Colorful Express’ 등으로 주목받았고, 이후 다채로운 앨범 활동을 통해 꾸준히 활동해 왔습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("쏜애플")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517437c8e9733b4aeef1ff7a3037")
                        .sessionMem("윤성현 – 보컬·기타, 방요셉 – 드럼, 홍동균 – 기타")
                        .introBand("쏜애플(Thornapple)은 2009년 결성된 대한민국 록 밴드로, 사이키델릭 록을 기반으로 몽환적이고 실험적인 사운드를 들려줍니다. 대표곡으로 〈2월〉 등이 있으며, 독자적인 음악세계로 인디 씬에서 주목받아 왔습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("3호선 버터플라이")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174aba03eccf4f7f5150d5e5f98")
                        .sessionMem("성기완 – 보컬·기타, 남상아 – 베이스·보컬, 김남윤 – 드럼")
                        .introBand("3호선 버터플라이(3rd Line Butterfly)는 1999년 홍대 인디 신에서 탄생한 대한민국의 록 밴드로, 시간이 흐르며 멤버 변동이 있었지만 현재도 활동하며 깊고 풍부한 사운드를 들려줍니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("글렌체크")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517452bf23dd85fe7d4cd09ee31b")
                        .sessionMem("김준원 – 기타·보컬, 제이보 – 기타·신시사이저")
                        .introBand("글렌체크(Glen Check)는 2011년 데뷔한 대한민국 일렉트로니카·신스팝 밴드로, 김준원과 제이보 두 명으로 구성되어 있으며, ‘Haute Couture’ 등 앨범을 통해 독특한 전자음악 사운드를 국내외에 선보여 왔습니다.")
                        .typeofartist(1)
                        .build(),


                Artist.builder()
                        .bandName("The Volunteers")
                        .relateUrl("https://i.scdn.co/image/ab676161000051749405d31654d5b4afcc3dba93")
                        .sessionMem("백예린 – 보컬·기타, 고상지 – 베이스, 윤석 – 드럼, 존오 – 기타")
                        .introBand("The Volunteers는 백예린이 주축이 되어 결성된 록 밴드로, 강렬한 기타 톤과 90년대 얼터너티브 록 감성을 현대적으로 풀어낸 사운드로 큰 주목을 받았습니다. 정식 데뷔 앨범 『The Volunteers』를 통해 밴드다운 완성도 높은 편곡과 탄탄한 연주력을 보여주며 한국 록 씬에서 확고한 위치를 잡았습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("웨이브투어스")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517449799010fa77f1f862ab207e")
                        .sessionMem("이승민 – 보컬·기타, 김도현 – 기타, 송정민 – 베이스, 최민우 – 드럼")
                        .introBand("웨이브투어스(Wave to Earth)는 따뜻하고 몽환적인 밴드 사운드로 인디 씬에서 빠르게 성장한 4인조 밴드입니다. 재즈 감성의 드럼, 담백한 보컬, 부드러운 기타 톤을 바탕으로 'soft surf' 스타일을 확립했으며, 글로벌 팬층을 확보하며 활발히 활동 중입니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("카더가든")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517460207000aebdb42a66f6880e")
                        .sessionMem("카더가든 – 보컬(싱어송라이터 단독 프로젝트)")
                        .introBand("카더가든(Car, the garden)은 독특한 음색과 감성적인 곡 구성으로 사랑받는 싱어송라이터입니다. 〈홈보이〉, 〈나무〉, 〈불면증〉 등 감정선이 짙은 곡들로 많은 팬층을 확보했으며, 다양한 음악 협업과 방송 활동을 통해 폭넓게 활동하고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("정우")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174e156408abd916fe880bb7564")
                        .sessionMem("정우 – 보컬·기타(싱어송라이터)")
                        .introBand("정우(Jungwoo)는 따뜻하고 서정적인 포크 기반의 싱어송라이터로, 담백한 목소리와 깊이 있는 가사로 많은 인디 리스너들에게 사랑받고 있습니다. 작은 사운드 구성 속에서 정서적인 여운을 남기는 음악이 특징입니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("너드커넥션")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174f37dafb5dbbefd1fb1eb113e")
                        .sessionMem("전희안 – 보컬, 정호연 – 기타, 하승우 – 베이스, 장원영 – 드럼")
                        .introBand("너드커넥션(Nerd Connection)은 2010년대 후반부터 인디 록 씬에서 주목받기 시작한 4인조 밴드로, 깊이 있는 사운드와 강렬한 라이브 퍼포먼스로 인기를 얻고 있습니다. 감성적인 멜로디와 드라마틱한 전개가 특징이며 다양한 음악 작업을 통해 활동 영역을 넓히고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("터치드")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174701b8daf9bcb567476a1a81f")
                        .sessionMem("김현우 – 보컬, 김준영 – 기타, 정유빈 – 베이스, 전보일 – 드럼")
                        .introBand("터치드(Touched)는 2018년 데뷔한 한국 인디 록 밴드로, 데뷔 직후 강력한 연주력과 세련된 사운드로 빠르게 주목받았습니다. ‘유스(YOUTH)’ 등을 통해 서정성과 에너지를 모두 갖춘 음악으로 사랑받으며 여러 페스티벌에서 활발히 활동하고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("김뜻돌")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174aefde5f14dfe746c21873415")
                        .sessionMem("김뜻돌 – 보컬·프로듀싱(솔로 프로젝트)")
                        .introBand("김뜻돌은 몽환적이고 실험적인 사운드를 기반으로 한 싱어송라이터이자 프로듀서로, R&B·일렉트로닉·인디 팝을 넘나드는 독창적인 음악으로 주목받고 있습니다. 『자각몽』 등 일련의 작품을 통해 개성 있는 세계관을 구축했습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("극동아시아타이거즈")
                        .relateUrl("https://i.scdn.co/image/ab676161000051748140f321aec80566760d46c6")
                        .sessionMem("손애 – 보컬, 추성훈 – 기타, 박은찬 – 베이스, 온훈 – 드럼")
                        .introBand("극동아시아타이거즈는 거칠고 강렬한 록 사운드로 유명한 4인조 밴드로, 펑크와 개러지 록 기반의 에너제틱한 음악을 들려줍니다. 날 선 가사와 강렬한 라이브 퍼포먼스로 인디 씬에서 강한 존재감을 보여주고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("크라잉넛")
                        .relateUrl("https://i.scdn.co/image/ab676161000051748ff484a7552bcf7b34f1acbf")
                        .sessionMem("박윤식 – 보컬·기타, 이상면 – 기타, 이상혁 – 드럼, 한경록 – 베이스, 김인수 – 키보드·아코디언")
                        .introBand("크라잉넛(Crying Nut)은 1993년 결성되어 한국 인디 펑크 록 씬을 대표해온 밴드로, 1998년 ‘말달리자’로 본격적으로 대중인기를 얻었으며 결성 이후 멤버 교체 없이 30년 넘게 활동해왔습니다. 펑크의 직진성과 한국적 정서를 결합한 사운드로 인디음악 역사에 굵은 한 획을 남겼습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("갤럭시 익스프레스")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174e2f24a69c45dca26bf77f2df")
                        .sessionMem("박종현 – 보컬·기타, 이주현 – 베이스·보컬, 전용현 – 드럼")
                        .introBand("갤럭시 익스프레스(Galaxy Express)는 2006년 결성된 한국 펑크·개러지 록 밴드로, 빠른 템포와 강렬한 라이브 사운드로 인디 록 씬에서 큰 존재감을 보여주었습니다. EP 『To The Galaxy』와 정규 『Noise On Fire』 등을 통해 본격 활동을 시작했고, 2011년 한국대중음악상 ‘올해의 음악인’상을 수상하며 인정받았습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("바이 바이 배드맨")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174b042a09ef79419c1ad6c8ed1")
                        .sessionMem("정봉길 – 보컬·기타, 이루리 – 베이스, 고형석 – 키보드")
                        .introBand("바이 바이 배드맨(Bye Bye Badman)은 2009년 결성된 한국 록 밴드로, 90년대 영국 브릿팝과 매드체스터 사운드의 영향을 받은 기타·건반 중심의 감성적 록을 선보이고 있습니다. 데뷔 EP 이후 꾸준히 활동하며 인디 팬층을 확보해왔습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("단편선 순간들")
                        .relateUrl("https://i.scdn.co/image/ab676161000051749254e46ccc745637ff9caa89")
                        .sessionMem("단편선 – 보컬·기타, 이보람 – 피아노·건반, 송현우 – 베이스, 박재준 – 드럼, 박장미 – 기타")
                        .introBand("단편선 순간들(Danpyunsun & The Moments Ensemble)은 2024년 결성된 한국의 실험 록/아트록 밴드로, 싱어송라이터 단편선을 중심으로 피아노·베이스·드럼·기타가 결합된 독창적 사운드를 추구합니다. 데뷔 정규 앨범 『음악만세』로 제22회 한국대중음악상 ‘올해의 음반’을 수상했습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("넬")
                        .relateUrl("https://i.scdn.co/image/ab676161000051747ef39e8e41cfceaa7bea3153")
                        .sessionMem("김종완 – 보컬·기타, 이재경 – 기타, 이정훈 – 베이스")
                        .introBand("넬(NELL)은 1999년 결성되어 한국 모던록 신에서 독보적인 위치를 차지한 3인조 밴드로, ‘기억을 걷는 시간’, ‘그리고 남겨진 것들’ 등의 대표곡을 통해 감성적인 사운드와 서정적 가사로 많은 인기를 얻었습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("드래곤 포니")
                        .relateUrl("https://i.scdn.co/image/ab676161000051742d76a55a284b56844eb5c055")
                        .sessionMem("안태규 – 보컬, 편성현 – 베이스, 권세혁 – 기타, 고강훈 – 드럼")
                        .introBand("드래곤 포니(Dragon Pony)는 2024년 데뷔한 한국 록 밴드로, 소속사 안테나의 첫 보이밴드 형태이며, 강렬한 연주력과 에너제틱한 퍼포먼스로 주목받고 있습니다. 멤버들은 모두 작사·작곡·프로듀싱에 참여하며 자기 색을 담은 음악을 선보이고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("서울전자음악단")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517426158530645531be8d86fb44")
                        .sessionMem("김윤하 – 보컬·기타, 김대일 – 베이스, 신동재 – 키보드·기타, 이윤하 – 드럼")
                        .introBand("서울전자음악단(Seoul Electronic Music Ensemble)은 한국의 실험 록 및 일렉트로닉 밴드로, 무대 퍼포먼스와 연출 요소를 적극 활용한 사운드로 인디 신에서 독특한 위치를 갖고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("자우림")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174a2a96bc3eb2a3ba23e4a6c92")
                        .sessionMem("김윤아 – 보컬·기타, 김선준 – 베이스, 이선규 – 드럼")
                        .introBand("자우림(Jaurim)은 1997년 결성된 한국의 록 밴드로, ‘매직카펫라이드’, ‘스물다섯, 스물하나’ 등 다수의 대표곡을 통해 대중과 평단의 사랑을 받아왔으며, 록 밴드로서 오랜 기간 활동해 온 저력 있는 팀입니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("surl")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517408071cad920ea8bc03969255")
                        .sessionMem("김연우 – 보컬·기타, 전윤우 – 베이스, 박별 – 기타, 최성훈 – 드럼")
                        .introBand("surl(서얼)은 2010년대 중반 이후 활동을 시작한 한국 인디 록 밴드로, 몽환적이고 드림팝적인 사운드를 기반으로 감성적인 곡들을 발표하며 인디 팬들 사이에서 주목받고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("라쿠나")
                        .relateUrl("https://i.scdn.co/image/ab676161000051741bf249d83f3042b384e4f9f1")
                        .sessionMem("이현우 – 보컬·기타, 장호철 – 기타, 최진혁 – 베이스, 박우진 – 드럼")
                        .introBand("라쿠나(Lacuna)는 최근 한국 인디 씬에서 활약 중인 밴드로, 곡마다 빛나는 멜로디와 강한 록 론을 결합한 음악으로 청중에게 깊은 인상을 남기고 있습니다.")
                        .typeofartist(1)
                        .build(),


                Artist.builder()
                        .bandName("한로로")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174545506e7b3307eef9ba47cfa")
                        .sessionMem("한로로(본명 한지수) – 보컬·기타·작곡(싱어송라이터)")
                        .introBand("한로로(HANRORO)는 2000년생 싱어송라이터로 2022년 싱글 『입춘』으로 데뷔했으며, 자연스러운 언어와 청춘의 불안을 담은 노랫말로 Z세대 리스너에게 주목받고 있습니다. 2023년 EP 『이상비행』, 2024년 EP 『집』 등을 통해 모던록 기반의 감성 음악을 선보이고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("윤마치")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174ba03c2512927b6ffea90246e")
                        .sessionMem("윤마치 – 보컬·작곡(싱어송라이터)")
                        .introBand("윤마치는 인디 팝/록 장르를 넘나들며 활동하는 싱어송라이터로, 감성적이면서도 강한 표현이 돋보이는 음악 세계를 구축해 왔습니다. 최근 작업에서는 자기 반영적 가사와 미니멀한 편곡을 통해 팬들 사이에서 깊은 호응을 얻고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("이승윤")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174d85e6231924d1e0df9a6e9a9")
                        .sessionMem("이승윤 – 보컬·기타·작곡(싱어송라이터)")
                        .introBand("이승윤은 1989년생 대한민국 싱어송라이터로, 2020년 오디션 프로그램   《싱어게인》  우승을 통해 대중적 인지도를 얻었습니다. 정규 1집 『폐허가 된다 해도』(2021) 이후 솔로 앨범과 전국 투어를 진행하며 감각적인 사운드와 가사로 주목받고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("유다빈밴드")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174e418c02a8003826b64fc513a")
                        .sessionMem("유다빈 – 보컬·기타·작곡, (기타 세션 및 베이스·드럼 멤버 – 비상시 변동)")
                        .introBand("유다빈밴드는 싱어송라이터 유다빈이 리드하는 밴드 프로젝트로, 포크·록·모던락 요소를 아우르며 서정적이면서도 힘 있는 밴드 사운드를 들려줍니다. 최근 라이브 활동과 함께 신곡 발표를 통해 활동 반경을 넓히고 있습니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("지소쿠리클럽")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517410200abebba8b27da8cb5e2c")
                        .sessionMem("jisokury Club – 보컬·기타·작곡(밴드 프로젝트)")
                        .introBand("jisokury Club(지소쿠리클럽)은 싱어송라이터 jisokury가 주축이 되어 결성된 밴드 형식의 프로젝트로, 인디 록·포스트펑크 기반 사운드와 감성적인 가사로 활동하며, 라이브 중심의 음악 팬층을 가진 팀입니다.")
                        .typeofartist(1)
                        .build(),

                Artist.builder()
                        .bandName("백예린")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517436fb1d2c40997cd05363de1f")
                        .sessionMem("백예린 – 보컬·작곡·프로듀싱(싱어송라이터)")
                        .introBand("백예린은 '비원에이포(B1A4)' 출신 보컬리스트로 솔로 전향 후 R&B·팝·인디록을 넘나드는 다채로운 음악 색채를 보여주며, 독립 레이블을 설립해 자신만의 음악세계를 구축해 왔습니다. 대표곡으로 〈Square (2017)〉, 〈Yolk〉 등이 있습니다.")
                        .typeofartist(1)
                        .build(),


                Artist.builder()
                        .bandName("Oasis")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174b4ddbc39706ef0f2ae0f7c9b")
                        .sessionMem("Liam Gallagher – 보컬, Noel Gallagher – 기타·보컬(리더), Gem Archer – 기타, Andy Bell – 베이스, Chris Sharrock – 드럼")
                        .introBand("Oasis는 1990년대 브릿팝 붐을 대표한 영국 록 밴드로, 『(What’s the Story) Morning Glory?』 등 다수의 명반을 남기며 세계적인 인기를 얻었습니다. 형제인 리암과 노엘 갤러거의 카리스마와 강렬한 사운드가 특징입니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("Coldplay")
                        .relateUrl("https://i.scdn.co/image/ab676161000051741ba8fc5f5c73e7e9313cc6eb")
                        .sessionMem("Chris Martin – 보컬·피아노, Jonny Buckland – 기타, Guy Berryman – 베이스, Will Champion – 드럼")
                        .introBand("Coldplay는 1996년 결성된 영국 록 밴드로, 『Parachutes』, 『A Rush of Blood to the Head』 등 감성적인 록 사운드로 세계적인 밴드로 성장했습니다. 이후 팝·일렉트로닉 요소를 결합하며 넓은 대중성과 실험성을 보여주고 있습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("LAUV")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174b173d69f77530d77a991984f")
                        .sessionMem("Lauv(아리 레프) – 보컬·작곡·프로듀싱")
                        .introBand("LAUV는 감정적인 팝·R&B 스타일의 곡으로 유명한 미국 싱어송라이터입니다. 〈I Like Me Better〉, 〈Paris in the Rain〉 등으로 글로벌 팬층을 확보했습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("Vaundy")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174f6be169899c276073de46a1b")
                        .sessionMem("Vaundy – 보컬·작곡·프로듀싱")
                        .introBand("Vaundy는 일본의 싱어송라이터 겸 프로듀서로, 장르를 넘나드는 세련된 사운드와 독창적인 보컬 톤으로 큰 인기를 얻고 있습니다. 〈怪獣の花唄〉 등 히트곡을 다수 보유하고 있습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("녹황색사회")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174d9a48d00ce7cf4d66abc76b6")
                        .sessionMem("長屋晴子 – 보컬·기타, 小林壱誓 – 기타·보컬, peppe – 키보드, 穴見真吾 – 베이스")
                        .introBand("녹황색사회(リーガルリリー, Ryokuoushoku Shakai)는 일본의 4인조 밴드로 감성적인 멜로디와 섬세한 편곡으로 많은 사랑을 받고 있으며, 애니메이션 OST 참여 등 활동 범위를 넓혀가고 있습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("ONE OK ROCK")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517465f3ecf43652596ef75f3293")
                        .sessionMem("Taka – 보컬, Toru – 기타, Ryota – 베이스, Tomoya – 드럼")
                        .introBand("ONE OK ROCK은 일본을 대표하는 록 밴드로, 강렬한 보컬과 에너지 넘치는 사운드로 글로벌한 인기를 얻고 있습니다. 영어 앨범을 발표하며 해외 활동에도 적극적입니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("King Gnu")
                        .relateUrl("https://i.scdn.co/image/ab676161000051742bdd859253b840c93d36963e")
                        .sessionMem("츠네타 다이키 – 보컬·기타, 이구치 사토루 – 보컬·키보드, 아라이 카즈키 – 베이스, 세키 유 – 드럼")
                        .introBand("King Gnu는 일본의 얼터너티브 록·팝 밴드로 독창적 사운드와 높은 음악성으로 주목받고 있습니다. 〈白日〉을 통해 대중적 인지도도 크게 높였습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("Muse")
                        .relateUrl("https://i.scdn.co/image/ab67616100005174a16a11aa5842607c88a8d8a9")
                        .sessionMem("Matthew Bellamy – 보컬·기타·피아노, Chris Wolstenholme – 베이스, Dominic Howard – 드럼")
                        .introBand("Muse는 영국의 3인조 록 밴드로, 클래식·일렉트로닉·하드록을 결합한 독창적 사운드로 세계적인 팬덤을 구축했습니다. 강렬한 라이브 퍼포먼스로도 유명합니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("아이묭")
                        .relateUrl("https://i.scdn.co/image/ab676161000051748493420e21375f9603f49b89")
                        .sessionMem("아이묭 – 보컬·작곡·기타")
                        .introBand("아이묭(Aimyon)은 일본의 싱어송라이터로, 담백하고 감성적인 멜로디와 진솔한 가사로 폭넓은 세대에게 사랑받고 있습니다. 대표곡으로 〈マリーゴールド〉 등이 있습니다.")
                        .typeofartist(2)
                        .build(),

                Artist.builder()
                        .bandName("SPYAIR")
                        .relateUrl("https://i.scdn.co/image/ab6761610000517440b4d9382a8a8182b183d57a")
                        .sessionMem("YOSUKE – 보컬, UZ – 기타·프로그래밍, MOMIKEN – 베이스, KENTA – 드럼")
                        .introBand("SPYAIR는 일본의 록 밴드로, 애니메이션 OST를 통해 글로벌 팬층을 많이 확보한 팀입니다. 파워풀한 밴드 사운드와 감성적인 멜로디가 특징입니다.")
                        .typeofartist(2)
                        .build()
        );

        repository.saveAll(artists);

        System.out.println("초기 밴드 데이터 " + artists.size() + "건 저장 완료!");
    }
}
