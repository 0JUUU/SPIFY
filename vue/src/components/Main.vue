<template>
    <div>
        <header id="header" class="header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="image-container">
                            <img
                                class="img-fluid"
                                src="@/assets/header-logo.svg"
                                alt="alternative"
                            />
                            <img
                                class="img-fluid"
                                src="@/assets/happy-logo2.svg"
                                alt="alternative"
                            />
                        </div>
                        <!-- end of image-container -->
                    </div>
                    <!-- end of col -->
                    <div class="col-lg-6">
                        <div class="text-container">
                            <h3>
                                트렌디함은 자기 자신을 아는 것에서부터 나오는 것입니다. 나에게
                                알맞는 매물을 찾고 "SPIFY"가 되어보세요.
                            </h3>
                            <!-- 우리 시스템에는
                                약 30,000명의 회원이 등록한 서울시 아파트 매물 정보가 있습니다. 한번
                                찾아 봅시다. -->
                            <a class="btn-solid-lg page-scroll" href="#details">Search</a>
                        </div>
                        <!-- end of text-container -->
                    </div>
                    <!-- end of col -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->

            <br /><br />
        </header>
        <!-- end of header -->
        <!-- end of header -->

        <!-- Details 1 -->
        <div id="details" class="basic-1">
            <div class="container">
                <div class="text-container2">
                    <div class="dong_search">
                        <h1 class="dong_search_title" style="font-weight: bold; color : white">
                            서울시 아파트 매물 검색하기
                        </h1>
                        <br />
                        <form method="get" action="" id="searchForm">
                            <select class="selects sido" name="si">
                                <option value="서울특별시">서울시</option>
                            </select>
                            &nbsp;
                            <select
                                class="selects gu"
                                name="gu"
                                v-model="selected"
                                @change="categoryChange()"
                            >
                                <option value="all">구</option>
                                <option value="도봉구">도봉구</option>
                                <option value="은평구">은평구</option>
                                <option value="동대문구">동대문구</option>
                                <option value="동작구">동작구</option>
                                <option value="금천구">금천구</option>
                                <option value="구로구">구로구</option>
                                <option value="종로구">종로구</option>
                                <option value="강북구">강북구</option>
                                <option value="중랑구">중랑구</option>
                                <option value="강남구">강남구</option>
                                <option value="강서구">강서구</option>
                                <option value="중구">중구</option>
                                <option value="강동구">강동구</option>
                                <option value="광진구">광진구</option>
                                <option value="마포구">마포구</option>
                                <option value="서초구">서초구</option>
                                <option value="성북구">성북구</option>
                                <option value="노원구">노원구</option>
                                <option value="송파구">송파구</option>
                                <option value="서대문">서대문</option>
                                <option value="양천구">양천구</option>
                                <option value="영등포구">영등포구</option>
                                <option value="관악구">관악구</option>
                                <option value="성동구">성동구</option>
                                <option value="용산구">용산구</option> </select
                            >&nbsp;
                            <select
                                class="selects dong"
                                name="dong"
                                id="dong"
                                v-model="selectedDong"
                            >
                                <option value="all">동</option> </select
                            >&nbsp;
                            <!-- <a href="./search_dong_page.html"><button class="search_dong" type="button">검색하기</button></a> -->
                            <button class="search_dong" type="button" @click="searchDong()">
                                검색하기
                            </button>
                        </form>
                        <!-- <br>
                    <img class="kakaomap" alt="dong_logo" src="@/assets/kakao_map_icon.png" style="height:200px"/> -->
                    </div>
                </div>
            </div>
            <!-- end of container -->
        </div>
        <!-- end of basic-1 -->
        <!-- end of details 1 -->

        <!-- Mission -->
        <div class="basic-4" id="service">
            <div class="container">
                <div>
                    <div class="text-container">
                        <h3 style="text-alignment:center; font-weight: bold">
                            사용자가 원하는 매물을 손 쉽게 얻을 수 있도록<br />
                            정확한 정보를 제공하는 것이 우리의 목표입니다.
                        </h3>
                    </div>
                    <!-- end of text-container -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of basic-4 -->
        <!-- end of mission -->

        <!-- Strengths -->
        <div id="strengths" class="basic-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="list-unstyled li-space-lg">
                            <li class="media">
                                <div class="bullet">1</div>
                                <div class="media-body">
                                    <h4>매물 검색 기능</h4>
                                    <p>
                                        서울특별시 아파트 실거래가를 확인할 수 있습니다. 동 /
                                        아파트명으로 검색을 손쉽게 할 수 있습니다. <br />
                                        검색한 매물의 로드뷰, 가격, 면적 등의 상세한 정보로 다양한
                                        정보를 얻어가세요. 또한, 평 당 가격을 통해 해당 매물의
                                        실질적인 거래가 순위를 알아보세요.
                                    </p>
                                </div>
                            </li>
                            <li class="media">
                                <div class="bullet">2</div>
                                <div class="media-body">
                                    <h4>주변 상권 검색 기능</h4>
                                    <p>
                                        찾고자 하는 매물 주변 맛집, 선별 진료소, 마트 등의
                                        편의시설을 알 수 있습니다. 실시간으로 변하는 데이터를 통해
                                        주변 상황을 파악할 수 있습니다. 매물 정보와 함께 주변 정보도
                                        얻어가세요!
                                    </p>
                                </div>
                            </li>
                            <li class="media">
                                <div class="bullet">3</div>
                                <div class="media-body">
                                    <h4>게시판 기능</h4>
                                    <p>
                                        공지사항 뿐 만 아니라, 실시간 사용자 간의 의견을 들을 수
                                        있는 공간이 존재합니다. 궁금한 것에 대해 묻고 답할 수
                                        있습니다. 지금 당장 궁금한 것에 대해
                                        <a class="red" href="/happyhouse/qna">글을 남겨보세요!</a>
                                        사용자들이 그에 대한 답변을 남겨줍니다.
                                    </p>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <!-- end of col-lg-12 -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of basic-5 -->
        <!-- end of strengths -->

        <!-- About -->
        <div id="about" class="basic-6">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="text-container bg-gray">
                            <h2 style="text-weight: bold">팀원을 소개합니다.</h2>
                            <p>
                                이 프로젝트는 엄청난 개발자들로 구성되어있습니다. 구성원 모두 2020년
                                무지막지한 경쟁률을 뚫고 삼성 청년 소프트웨어 아카데미의 일원으로
                                선정 되었으니 믿고 보셔도 될겁니다.
                            </p>
                            <ul class="list-unstyled li-space-lg">
                                <li class="media">
                                    <i class="fas fa-square"></i>
                                    <div class="media-body">
                                        We love what we do and have a lot of passion
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- end of text-container -->
                        <div class="image-container">
                            <img
                                class="img-fluid"
                                src="@/assets/images/about.jpg"
                                alt="alternative"
                            />
                        </div>
                        <!-- end of image-container -->
                    </div>
                    <!-- end of col -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of basic-6 -->
        <!-- end of about -->

        <!-- Projects -->
        <div id="projects" class="slider-1">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <swiper ref="mySwiper" :options="swiperOptions">
                            <swiper-slide>
                                <!-- Slide -->
                                <div class="swiper-slide">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="image-container">
                                                <img
                                                    class="img-fluid"
                                                    src="@/assets/park.png"
                                                    alt="박회재"
                                                />
                                            </div>
                                            <!-- end of image-container -->
                                        </div>
                                        <!-- end of col -->
                                        <div class="col-lg-6">
                                            <div class="text-container">
                                                <h4 style="font-weight:bold">박회재</h4>
                                                <p>
                                                    17 ~ 20. BJTU (China) SW Engineering -
                                                    Graduate<br />
                                                    21 ~ now. 삼성 청년 소프트웨어 아카데미 -
                                                    Studying<br />
                                                </p>

                                                <p
                                                    class="testimonial-text"
                                                    style="font-weight:bold"
                                                >
                                                    안녕하세요! 중국에서 소프트웨어 공학을 전공한
                                                    프로그래머 지망생 입니다. 주로 자바와 파이썬을
                                                    이용하여 프로젝트를 진행해 왔고, 자연어 처리에
                                                    관심이 있어 학부 연구생으로 자연어 처리를 이용한
                                                    학습 모듈을 개발한 경험이 있습니다.<br />
                                                </p>
                                                <div class="testimonial-author">
                                                    <a href="https://github.com/par3k"
                                                        ><img
                                                            src="@/assets/github-logo.png"
                                                            style="width: 50px"
                                                    /></a>
                                                    &nbsp;
                                                    <a href="https://par3k.tistory.com/"
                                                        ><img
                                                            src="@/assets/blog.png"
                                                            style="width: 40px"
                                                    /></a>
                                                </div>
                                            </div>
                                            <!-- end of text-container -->
                                        </div>
                                        <!-- end of col -->
                                    </div>
                                    <!-- end of row -->
                                </div>
                                <!-- end of swiper-slide -->
                            </swiper-slide>
                            <swiper-slide>
                                <!-- Slide -->
                                <div class="swiper-slide">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="image-container">
                                                <img
                                                    class="img-fluid"
                                                    src="@/assets/lee.png"
                                                    alt="이영주"
                                                />
                                            </div>
                                            <!-- end of image-container -->
                                        </div>
                                        <!-- end of col -->
                                        <div class="col-lg-6">
                                            <div class="text-container">
                                                <h4 style="font-weight:bold">이영주</h4>
                                                <p>
                                                    16 ~ 20. INHA Univ. Computer Engineering -
                                                    Graduate<br />
                                                    21 ~ now. 삼성 청년 소프트웨어 아카데미 -
                                                    Studying<br />
                                                </p>

                                                <p
                                                    class="testimonial-text"
                                                    style="font-weight:bold"
                                                >
                                                    안녕하세요😁 인하대학교 컴퓨터공학과를 졸업한 웹
                                                    백엔드 개발자 지망생 이영주입니다. 자바를 이용한
                                                    안드로이드 애플리케이션과 웹 페이지 개발 경험이
                                                    있습니다. <br />
                                                </p>
                                                <div class="testimonial-author">
                                                    <a href="https://github.com/0JUUU"
                                                        ><img
                                                            src="@/assets/github-logo.png"
                                                            style="width: 50px"
                                                    /></a>
                                                    &nbsp;
                                                    <a href="https://www.instagram.com/0__jxxu/"
                                                        ><img
                                                            src="@/assets/instagram.png"
                                                            style="width: 40px"
                                                    /></a>
                                                </div>
                                            </div>
                                            <!-- end of text-container -->
                                        </div>
                                        <!-- end of col -->
                                    </div>
                                    <!-- end of row -->
                                </div>
                                <!-- end of swiper-slide -->
                                <!-- end of slide -->
                            </swiper-slide>

                            <div class="swiper-pagination" slot="pagination"></div>
                        </swiper>

                        <!-- end of text slider -->
                    </div>
                    <!-- end of col -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of slider-1 -->
        <!-- end of projects -->

        <!-- Footer -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="footer-col first">
                            <h6>About HappyHouse</h6>
                            <!-- 홈페이지 소개 -->
                            <p class="p-small">
                                HappyHouse is a website to provide information about Housing price
                                with Kakao map
                            </p>
                            <img
                                class="img-fluid"
                                style="height:30px"
                                src="@/assets/kakao_map_icon.png"
                                alt="KakaoMap"
                            />
                        </div>
                        <!-- end of footer-col -->
                        <div class="footer-col second">
                            <h6>Links</h6>
                            <ul class="list-unstyled li-space-lg p-small">
                                <!-- 링크 이동 -->
                                <li>&nbsp;</li>
                                <li>
                                    Menu: <a class="page-scroll" href="#header">Home</a>,
                                    <a class="page-scroll" href="#service">Services</a>,
                                    <a class="page-scroll" href="#about">about</a>
                                </li>
                            </ul>
                        </div>
                        <!-- end of footer-col -->
                        <div class="footer-col third">
                            <p class="p-small">
                                We would love to hear from you
                                <a href="mailto:ios_7@kakao.com"
                                    ><strong>ios_7@kakao.com</strong></a
                                >
                                <br />
                                <a href="mailto:clleo97@naver.com"
                                    ><strong>clleo97@naver.com</strong></a
                                >
                            </p>
                        </div>
                        <!-- end of footer-col -->
                    </div>
                    <!-- end of col -->
                </div>
                <!-- end of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of footer -->
        <!-- end of footer -->

        <!-- Copyright -->
        <div class="copyright">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <p class="p-small">Copyright © SSAFY all right reserved.</p>
                    </div>
                    <!-- end of col -->
                </div>
                <!-- enf of row -->
            </div>
            <!-- end of container -->
        </div>
        <!-- end of copyright -->
        <!-- end of copyright -->
    </div>
</template>

<script>
// require styles
import "../css/swiper.css";
import "../css/styles.css";
import "../css/main_page.css";

import { mapState } from "vuex";
export default {
    name: "Main",
    data() {
        return {
            selected: "",
            donglist: [],
            selectedDong: "",
        };
    },
    computed: {
        ...mapState(["dong", "aptName", "searchKeyword"]),
    },
    methods: {
        categoryChange() {
            console.log(this.selected);
            let DOBONG = ["쌍문동", "방학동", "창동", "도봉동"];
            let JONGRO = [
                "청운동",
                "신교동",
                "궁정동",
                "효자동",
                "창성동",
                "통의동",
                "적선동",
                "통인동",
                "누상동",
                "누하동",
                "옥인동",
                "체부동",
                "필운동",
                "내자동",
                "사직동",
                "도렴동",
                "당주동",
                "내수동",
                "세종로",
                "신문로1가",
                "신문로2가",
                "청진동",
                "서린동",
                "수송동",
                "중학동",
                "종로1가",
                "공평동",
                "관훈동",
                "견지동",
                "와룡동",
                "권농동",
                "운니동",
                "익선동",
                "경운동",
                "관철동",
                "인사동",
                "낙원동",
                "종로2가",
                "팔판동",
                "삼청동",
                "안국동",
                "소격동",
                "화동",
                "사간동",
                "송현동",
                "가회동",
                "재동",
                "계동",
                "원서동",
                "훈정동",
                "묘동",
                "봉익동",
                "돈의동",
                "장사동",
                "관수동",
                "종로3가",
                "인의동",
                "예지동",
                "원남동",
                "연지동",
                "종로4가",
                "효제동",
                "종로5가",
                "종로6가",
                "이화동",
                "연건동",
                "충신동",
                "동숭동",
                "혜화동",
                "명륜1가",
                "명륜2가",
                "명륜4가",
                "명륜3가",
                "창신동",
                "숭인동",
                "교남동",
                "평동",
                "송월동",
                "홍파동",
                "교북동",
                "행촌동",
                "구기동",
                "평창동",
                "부암동",
                "홍지동",
                "신영동",
                "무악동",
            ];
            let EUNPYEONG = [
                "수색동",
                "녹번동",
                "불광동",
                "갈현동",
                "구산동",
                "대조동",
                "응암동",
                "역촌동",
                "신사동",
                "증산동",
                "진관동",
            ];
            let DONGDAEMOON = [
                "신설동",
                "용두동",
                "제기동",
                "전농동",
                "답십리동",
                "장안동",
                "청량리동",
                "회기동",
                "휘경동",
                "이문동",
            ];
            let DONGJACK = [
                "노량진동",
                "상도동",
                "상도1동",
                "본동",
                "흑석동",
                "동작동",
                "사당동",
                "대방동",
                "신대방동",
            ];
            let GEUMCHEON = ["가산동", "독산동", "시흥동"];
            let GURO = [
                "신도림동",
                "구로동",
                "가리봉동",
                "고척동",
                "개봉동",
                "오류동",
                "궁동",
                "온수동",
                "천왕동",
                "항동",
            ];
            let GANGBOOK = ["미아동", "번동", "수유동", "우이동"];
            let JOONGRANG = ["면목동", "상봉동", "중화동", "묵동", "망우동", "신내동"];
            let GANGNAM = [
                "역삼동",
                "개포동",
                "청담동",
                "삼성동",
                "대치동",
                "신사동",
                "논현동",
                "압구정동",
                "세곡동",
                "자곡동",
                "율현동",
                "일원동",
                "수서동",
                "도곡동",
            ];
            let GANGSEO = [
                "염창동",
                "등촌동",
                "화곡동",
                "가양동",
                "마곡동",
                "내발산동",
                "외발산동",
                "공항동",
                "방화동",
                "개화동",
                "과해동",
                "오곡동",
                "오쇠동",
            ];
            let JOONG = [
                "무교동",
                "다동",
                "태평로1가",
                "을지로1가",
                "을지로2가",
                "남대문로1가",
                "삼각동",
                "수하동",
                "장교동",
                "수표동",
                "소공동",
                "남창동",
                "북창동",
                "태평로2가",
                "남대문로2가",
                "남대문로3가",
                "남대문로4가",
                "남대문로5가",
                "봉래동1가",
                "봉래동2가",
                "회현동1가",
                "회현동2가",
                "회현동3가",
                "충무로1가",
                "충무로2가",
                "명동1가",
                "명동2가",
                "남산동1가",
                "남산동2가",
                "남산동3가",
                "저동1가",
                "충무로4가",
                "충무로5가",
                "인현동2가",
                "예관동",
                "묵정동",
                "필동1가",
                "필동2가",
                "필동3가",
                "남학동",
                "주자동",
                "예장동",
                "장충동1가",
                "장충동2가",
                "광희동1가",
                "광희동2가",
                "쌍림동",
                "을지로6가",
                "을지로7가",
                "을지로4가",
                "을지로5가",
                "주교동",
                "방산동",
                "오장동",
                "을지로3가",
                "입정동",
                "산림동",
                "충무로3가",
                "초동",
                "인현동1가",
                "저동2가",
                "신당동",
                "흥인동",
                "무학동",
                "황학동",
                "서소문동",
                "정동",
                "순화동",
                "의주로1가",
                "충정로1가",
                "중림동",
                "의주로2가",
                "만리동1가",
                "만리동2가",
            ];
            let GANGDONG = [
                "명일동",
                "고덕동",
                "상일동",
                "길동",
                "둔촌동",
                "암사동",
                "성내동",
                "천호동",
                "강일동",
            ];
            let GUANGJIN = ["중곡동", "능동", "구의동", "광장동", "자양동", "화양동", "군자동"];
            let MAPO = [
                "아현동",
                "공덕동",
                "신공덕동",
                "도화동",
                "용강동",
                "토정동",
                "마포동",
                "대흥동",
                "염리동",
                "노고산동",
                "신수동",
                "현석동",
                "구수동",
                "창전동",
                "상수동",
                "하중동",
                "신정동",
                "당인동",
                "서교동",
                "동교동",
                "합정동",
                "망원동",
                "연남동",
                "성산동",
                "중동",
                "상암동",
            ];
            let SEOCHO = [
                "방배동",
                "양재동",
                "우면동",
                "원지동",
                "잠원동",
                "반포동",
                "서초동",
                "내곡동",
                "염곡동",
                "신원동",
            ];
            let SEONGBOOK = [
                "성북동",
                "성북동1가",
                "돈암동",
                "동소문동1가",
                "동소문동2가",
                "동소문동3가",
                "동소문동4가",
                "동소문동5가",
                "동소문동6가",
                "동소문동7가",
                "삼선동1가",
                "삼선동2가",
                "삼선동3가",
                "삼선동4가",
                "삼선동5가",
                "동선동1가",
                "동선동2가",
                "동선동3가",
                "동선동4가",
                "동선동5가",
                "안암동1가",
                "안암동2가",
                "안암동3가",
                "안암동4가",
                "안암동5가",
                "보문동4가",
                "보문동5가",
                "보문동6가",
                "보문동7가",
                "보문동1가",
                "보문동2가",
                "보문동3가",
                "정릉동",
                "길음동",
                "종암동",
                "하월곡동",
                "상월곡동",
                "장위동",
                "석관동",
            ];
            let NOWON = ["월계동", "공릉동", "하계동", "상계동", "중계동"];
            let SONGPA = [
                "잠실동",
                "신천동",
                "풍납동",
                "송파동",
                "석촌동",
                "삼전동",
                "가락동",
                "문정동",
                "장지동",
                "방이동",
                "오금동",
                "거여동",
                "마천동",
            ];
            let SEODAEMOON = [
                "충정로2가",
                "충정로3가",
                "합동",
                "미근동",
                "냉천동",
                "천연동",
                "옥천동",
                "영천동",
                "현저동",
                "북아현동",
                "홍제동",
                "대현동",
                "대신동",
                "신촌동",
                "봉원동",
                "창천동",
                "연희동",
                "홍은동",
                "북가좌동",
                "남가좌동",
            ];
            let YANGCHEON = ["신정동", "목동", "신월동"];
            let YEONGDEUNGPO = [
                "영등포동",
                "영등포동1가",
                "영등포동2가",
                "영등포동3가",
                "영등포동4가",
                "영등포동5가",
                "영등포동6가",
                "영등포동7가",
                "영등포동8가",
                "여의도동",
                "당산동1가",
                "당산동2가",
                "당산동3가",
                "당산동4가",
                "당산동5가",
                "당산동6가",
                "당산동",
                "도림동",
                "문래동1가",
                "문래동2가",
                "문래동3가",
                "문래동4가",
                "문래동5가",
                "문래동6가",
                "양평동1가",
                "양평동2가",
                "양평동3가",
                "양평동4가",
                "양평동5가",
                "양평동6가",
                "양화동",
                "신길동",
                "대림동",
                "양평동",
            ];
            let GUANAK = ["봉천동", "신림동", "남현동"];
            let SEONGDONG = [
                "상왕십리동",
                "하왕십리동",
                "홍익동",
                "도선동",
                "마장동",
                "사근동",
                "행당동",
                "응봉동",
                "금호동1가",
                "금호동2가",
                "금호동3가",
                "금호동4가",
                "옥수동",
                "성수동1가",
                "성수동2가",
                "송정동",
                "용답동",
            ];
            let YONGSAN = [
                "후암동",
                "용산동2가",
                "용산동4가",
                "갈월동",
                "남영동",
                "용산동1가",
                "동자동",
                "서계동",
                "청파동1가",
                "청파동2가",
                "청파동3가",
                "원효로1가",
                "원효로2가",
                "신창동",
                "산천동",
                "청암동",
                "원효로3가",
                "원효로4가",
                "효창동",
                "도원동",
                "용문동",
                "문배동",
                "신계동",
                "한강로1가",
                "한강로2가",
                "용산동3가",
                "용산동5가",
                "한강로3가",
                "이촌동",
                "이태원동",
                "한남동",
                "동빙고동",
                "서빙고동",
                "주성동",
                "용산동6가",
                "보광동",
            ];
            let target = document.getElementById("dong");

            if (this.selected == "도봉구") this.donglist = DOBONG;
            else if (this.selected == "은평구") this.donglist = EUNPYEONG;
            else if (this.selected == "동대문구") this.donglist = DONGDAEMOON;
            else if (this.selected == "동작구") this.donglist = DONGJACK;
            else if (this.selected == "금천구") this.donglist = GEUMCHEON;
            else if (this.selected == "구로구") this.donglist = GURO;
            else if (this.selected == "종로구") this.donglist = JONGRO;
            else if (this.selected == "강북구") this.donglist = GANGBOOK;
            else if (this.selected == "중랑구") this.donglist = JOONGRANG;
            else if (this.selected == "강남구") this.donglist = GANGNAM;
            else if (this.selected == "강서구") this.donglist = GANGSEO;
            else if (this.selected == "중구") this.donglist = JOONG;
            else if (this.selected == "강동구") this.donglist = GANGDONG;
            else if (this.selected == "광진구") this.donglist = GUANGJIN;
            else if (this.selected == "마포구") this.donglist = MAPO;
            else if (this.selected == "서초구") this.donglist = SEOCHO;
            else if (this.selected == "성북구") this.donglist = SEONGBOOK;
            else if (this.selected == "노원구") this.donglist = NOWON;
            else if (this.selected == "송파구") this.donglist = SONGPA;
            else if (this.selected == "서대문") this.donglist = SEODAEMOON;
            else if (this.selected == "양천구") this.donglist = YANGCHEON;
            else if (this.selected == "영등포구") this.donglist = YEONGDEUNGPO;
            else if (this.selected == "관악구") this.donglist = GUANAK;
            else if (this.selected == "성동구") this.donglist = SEONGDONG;
            else if (this.selected == "용산구") this.donglist = YONGSAN;

            target.options.length = 0;

            // 구 선택 시 동 리스트에 넣기
            for (let x in this.donglist) {
                let opt = document.createElement("option");
                opt.value = this.donglist[x];
                opt.innerHTML = this.donglist[x];
                target.appendChild(opt);
            }
        },
        searchDong() {
            // 동 선택을 안 했을 때
            if (this.selectedDong == "all") {
                alert("지역을 선택해주세요");
                return;
            } else {
                this.searchKeyword.value = "aptList";
                console.log("지역 : " + this.selectedDong);
                this.dong.value = this.selectedDong;
                this.aptName.name = "";
                //document.getElementById("searchForm").submit();

                setTimeout(function() {
                    location.reload(true);
                }, 100);
                this.$router.push("/happyhouse/house");
            }
        },
    },
};
</script>

<style></style>
