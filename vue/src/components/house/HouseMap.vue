<template>
    <div id="app">
        <div class="map_wrap">
            <div id="map" style="width:100%;height:150%;position:relative;overflow:hidden;"></div>
            <div title="매물리스트" id="aptviewControl" @click="setAptviewRoad()"></div>
            <div title="맛집" id="matviewControl" @click="setMatviewRoad()"></div>
            <div title="선별진료소" id="coronaviewControl" @click="setCoronaviewRoad()"></div>
            <div title="마트" id="shopviewControl" @click="setShopviewRoad()"></div>
            <div v-if="this.searchKeyword.value == 'aptList'">
                <div id="menu_wrap" class="bg_white">
                    <div class="option">
                        <div>
                            <B>{{ dong.value }} 아파트 매물 리스트</B>
                        </div>
                    </div>
                    <hr />
                    <house-list />
                </div>
            </div>

            <div v-else-if="this.searchKeyword.value == 'restaurant'">
                <div id="menu_wrap" class="bg_white">
                    <div class="option">
                        <div>
                            <B>{{ this.dong.value }} 맛집 리스트</B>
                        </div>
                    </div>
                    <hr />
                    <ul id="placesList"></ul>
                    <div id="pagination"></div>
                </div>
            </div>
            <div v-else-if="this.searchKeyword.value == 'corona'">
                <div id="menu_wrap" class="bg_white">
                    <div class="option">
                        <div>
                            <B>{{ this.dong.value }} 선별진료소</B>
                        </div>
                    </div>
                    <hr />
                    <ul id="placesList"></ul>
                    <div id="pagination"></div>
                </div>
            </div>
            <div v-else-if="this.searchKeyword.value == 'shop'">
                <div id="menu_wrap" class="bg_white">
                    <div class="option">
                        <div>
                            <B>{{ this.dong.value }} 마트 리스트</B>
                        </div>
                    </div>
                    <hr />
                    <ul id="placesList"></ul>
                    <div id="pagination"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import "@/css/kakaomap.css";
import HouseList from "@/components/house/HouseList.vue";
import { mapState, mapActions } from "vuex";
export default {
    components: {
        HouseList,
    },

    computed: {
        ...mapState(["dong", "apts", "aptName", "clickApt", "apt", "searchKeyword", "aptDetails"]),
        ...mapActions(["getAptDetails", "getPriceList"]),
    },

    data() {
        return {
            buttonStat: "Apt",
            positions: [],
            map: null,
            ps: null,
            markers: [],
            geocoder: null,
        };
    },

    mounted() {
        if (window.kakao && window.kakao.maps) {
            console.log("initMap");
            this.initMap();
        } else {
            const script = document.createElement("script");
            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
                "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d13cfc3fc2ba17e1abac62d6ed84e591&libraries=services";
            document.head.appendChild(script);
        }
        this.getPriceList();
    },

    updated() {
        console.log("update 됨");
        this.addMarker();
    },

    methods: {
        initMap() {
            var container = document.getElementById("map");
            var options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 4,
            };

            this.map = new kakao.maps.Map(container, options);
            var aptMap = this.map;
            // 아파트 매물 검색
            console.log("검색 키워드 : " + this.searchKeyword.value);

            if (this.searchKeyword.value == "aptList") {
                console.log("아파트 리스트를 찾아보자.");
                // 마커를 표시할 위치와 title 객체 배열입니다
                this.positions = [];
                for (let apt of this.apts) {
                    // console.log(apt);
                    let temp = {
                        title: apt.aptName,
                        latlng: new kakao.maps.LatLng(apt.lat, apt.lng),
                    };
                    this.positions.push(temp);
                }

                console.log(this.positions);

                // 마커 이미지의 이미지 주소입니다
                var imageSrc =
                    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
                for (var i = 0; i < this.positions.length; i++) {
                    // 마커 이미지의 이미지 크기 입니다
                    var imageSize = new kakao.maps.Size(24, 35);

                    // 마커 이미지를 생성합니다
                    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                    // 마커를 생성합니다
                    this.marker = new kakao.maps.Marker({
                        map: aptMap, // 마커를 표시할 지도
                        position: this.positions[i].latlng, // 마커를 표시할 위치
                        title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image: markerImage, // 마커 이미지
                    });
                }

                this.marker.setMap(aptMap);

                // 주소-좌표 변환 객체를 생성합니다
                this.geocoder = new kakao.maps.services.Geocoder(); // 주소로 좌표를 검색합니다

                console.log("clickApt : " + this.clickApt.flag);
                // 하나의 아파트 선택했을 경우
                if (this.clickApt.flag) {
                    var coords = new kakao.maps.LatLng(this.apt.lat, this.apt.lng);
                    aptMap.setCenter(coords);
                    aptMap.setLevel(3);
                    this.marker = new kakao.maps.Marker({
                        map: aptMap,
                        position: new kakao.maps.LatLng(this.apt.lat, this.apt.lng),
                    });

                    var content = `
                     <div class="wrap">
                        <div class="info">
                            <div class="title"> ${this.apt.aptName}
                                <div class="close" id="close" title="닫기"></div>
                            </div>
                            <div class="body">
                                <div class="img">
                                    <img src="https://image.flaticon.com/icons/png/512/1923/1923765.png" width="73" height="73">
                                </div>
                                <div class="desc">
                                <div class="jibun ellipsis">거래번호 - ${this.apt.no}</div>
                                <div class="ellipsis">${this.apt.dong}</div>
                                <div class="jibun ellipsis">건축년도 : ${this.apt.buildYear}년</div>
                                <div><a id="showAptDetail" class="link">매물정보보기</a></div>
                                </div>
                            </div>
                        </div>
                    </div>`;

                    var overlay = new kakao.maps.CustomOverlay({
                        content: content,
                        map: aptMap,
                        position: this.marker.getPosition(),
                    });

                    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
                    kakao.maps.event.addListener(this.marker, "click", function() {
                        overlay.setMap(aptMap);
                    });

                    // 커스텀 오버레이를 끕니다.
                    var closeBtn = document.getElementById("close");
                    closeBtn.onclick = function() {
                        overlay.setMap(null);
                    };

                    var showAptA = document.getElementById("showAptDetail");
                    showAptA.onclick = function() {
                        console.log("showAptA 클릭");

                        setTimeout(function() {
                            location.href = "/happyhouse/house/detail";
                        }, 30);
                    };
                } else {
                    // 동이름으로 검색
                    var addrFullName = this.dong.value;
                    console.log("동 이름 : " + this.dong.value);
                    this.geocoder.addressSearch(addrFullName, function(result, status) {
                        // 정상적으로 검색이 완료됐으면
                        if (status === kakao.maps.services.Status.OK) {
                            console.log("성공");
                            console.log(result);
                            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                            // 해당 좌표로 지도 이동
                            aptMap.setCenter(coords);
                        }
                    });
                }
            } else if (this.searchKeyword.value == "restaurant") {
                console.log(this.dong.value + "맛집 찾기");
                // 장소 검색 객체를 생성합니다
                this.ps = new kakao.maps.services.Places();
                let keyword = this.dong.value + " 맛집";
                // 키워드로 장소를 검색합니다
                this.searchPlaces(keyword);
            } else if (this.searchKeyword.value == "corona") {
                console.log(this.dong.value + "선별진료소 찾기");
                // 장소 검색 객체를 생성합니다
                this.ps = new kakao.maps.services.Places();

                let keyword = this.dong.value + " 선별진료소";
                // 키워드로 장소를 검색합니다
                this.searchPlaces(keyword);
            } else if (this.searchKeyword.value == "shop") {
                console.log(this.dong.value + "마트 찾기");
                // 장소 검색 객체를 생성합니다
                this.ps = new kakao.maps.services.Places();

                let keyword = this.dong.value + " 마트";
                // 키워드로 장소를 검색합니다
                this.searchPlaces(keyword);
            }
            this.clickApt.flag = false;
        },
        // 지도 위 선별진료소 버튼을 눌렀을 때 호출되는 함수
        setCoronaviewRoad() {
            this.searchKeyword.value = "corona";
            alert("선별진료소를 찾겠습니다🧐");
            this.initMap();
        },
        // 지도 위 마트 버튼을 눌렀을 때 호출되는 함수
        setShopviewRoad() {
            this.searchKeyword.value = "shop";
            alert("대형마트를 찾겠습니다🧐");
            this.initMap();
        },
        // 지도 위의 맛집 버튼을 눌렀을 때 호출되는 함수입니다
        setMatviewRoad() {
            this.searchKeyword.value = "restaurant";
            alert("맛집을 찾아 볼까요?");
            this.initMap();
            this.buttonStat = "Mat";
        },

        // 지도 위의 맛집 버튼을 눌렀을 때 호출되는 함수입니다
        setAptviewRoad() {
            this.searchKeyword.value = "aptList";
            alert("아파트 매물을 갖고 오겠습니다.");
            this.initMap();
            this.buttonStat = "Apt";
        },
        // 키워드 검색을 요청하는 함수입니다
        searchPlaces(keyword) {
            // let keyword = this.dong.value + " 맛집";

            if (!keyword.replace(/^\s+|\s+$/g, "")) {
                alert("키워드를 입력해주세요!");
                return false;
            }
            console.log("키워드 : " + keyword);
            // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
            this.ps.keywordSearch(keyword, this.placesSearchCB);
        },
        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        placesSearchCB(data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
                // 정상적으로 검색이 완료됐으면
                // 검색 목록과 마커를 표출합니다
                this.displayPlaces(data);

                // 페이지 번호를 표출합니다
                this.displayPagination(pagination);
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                alert("검색 결과가 존재하지 않습니다.");
                return;
            } else if (status === kakao.maps.services.Status.ERROR) {
                alert("검색 결과 중 오류가 발생했습니다.");
                return;
            }
        },
        // 검색 결과 목록과 마커를 표출하는 함수입니다
        displayPlaces(places) {
            var listEl = document.getElementById("placesList"),
                menuEl = document.getElementById("menu_wrap"),
                fragment = document.createDocumentFragment(),
                bounds = new kakao.maps.LatLngBounds();

            // 검색 결과 목록에 추가된 항목들을 제거합니다
            this.removeAllChildNods(listEl);

            // 지도에 표시되고 있는 마커를 제거합니다
            this.removeMarker();

            for (var i = 0; i < places.length; i++) {
                // 마커를 생성하고 지도에 표시합니다
                var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                    marker = this.addMarker(placePosition, i),
                    itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);

                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                ((marker) => {
                    kakao.maps.event.addListener(marker, "mouseover", function() {});

                    kakao.maps.event.addListener(marker, "mouseout", function() {});

                    itemEl.onmouseover = function() {};

                    itemEl.onmouseout = function() {};
                })(marker, places[i].place_name);

                fragment.appendChild(itemEl);
            }

            // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
            listEl.appendChild(fragment);
            menuEl.scrollTop = 0;

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            this.map.setBounds(bounds);
        },

        // 검색결과 항목을 Element로 반환하는 함수입니다
        getListItem(index, places) {
            var el = document.createElement("li"),
                itemStr =
                    '<span class="markerbg marker_' +
                    (index + 1) +
                    '"></span>' +
                    '<div class="info">' +
                    "   <h5>" +
                    places.place_name +
                    "</h5>";

            if (places.road_address_name) {
                itemStr +=
                    "    <span>" +
                    places.road_address_name +
                    "</span>" +
                    '   <span class="jibun gray">' +
                    places.address_name +
                    "</span>";
            } else {
                itemStr += "    <span>" + places.address_name + "</span>";
            }

            itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

            el.innerHTML = itemStr;
            el.className = "item";

            return el;
        },

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        addMarker(position, idx) {
            var imageSrc =
                    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
                imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
                imgOptions = {
                    spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                    spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                    offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                    position: position, // 마커의 위치
                    image: markerImage,
                });

            marker.setMap(this.map); // 지도 위에 마커를 표출합니다
            this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

            return marker;
        },
        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        removeMarker() {
            for (var i = 0; i < this.markers.length; i++) {
                this.markers[i].setMap(null);
            }
            this.markers = [];
        },

        // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
        displayPagination(pagination) {
            var paginationEl = document.getElementById("pagination"),
                fragment = document.createDocumentFragment(),
                i;

            // 기존에 추가된 페이지번호를 삭제합니다
            while (paginationEl.hasChildNodes()) {
                paginationEl.removeChild(paginationEl.lastChild);
            }

            for (i = 1; i <= pagination.last; i++) {
                var el = document.createElement("a");
                el.href = "#";
                el.innerHTML = i;

                if (i === pagination.current) {
                    el.className = "on";
                } else {
                    el.onclick = (function(i) {
                        return function() {
                            pagination.gotoPage(i);
                        };
                    })(i);
                }

                fragment.appendChild(el);
            }
            paginationEl.appendChild(fragment);
        },
        // 검색결과 목록의 자식 Element를 제거하는 함수입니다
        removeAllChildNods(el) {
            while (el.hasChildNodes()) {
                el.removeChild(el.lastChild);
            }
        },
    },
};
</script>

<style></style>
