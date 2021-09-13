<template>
    <b-container class="houseDetail" v-if="this.apt.no">
        <b-row class="text-left">
            <b-button @click="moveBeforeList">목록</b-button>
            <br />
        </b-row>
        <br />
        <b-row class="text-center" align-h="between">
            <b-col cols="6">
                <h3>[{{ apt.dong }}] {{ apt.aptName }}</h3>
            </b-col>
            <b-col cols="6">
                <h6 class="text-right">주소 : {{ apt.dong }} {{ aptDetails[0].jibun }}번지</h6>
            </b-col>
        </b-row>
        <br />
        <br />
        <b-row class="text-center" align-h="end">
            <b-col cols="6" align-self="end">
                <div style="display:inline-block; margin:10px;">
                    <B>총 {{ totalRows }}개의 매물이 있습니다.</B>
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div style="display:inline;" class="toggle checkcross">
                    <input @click="setAptList" id="checkcross" type="checkbox" />
                    <label class="toggle-item" for="checkcross">
                        <div class="check"></div>
                    </label>
                </div>
            </b-col>
        </b-row>
        <vue-slide-up-down :active="active">
            <b-row>
                <b-col cols="6">
                    <div id="roadview" style="width:100%;height:500px;"></div>
                </b-col>
                <b-col cols="6">
                    <b-table
                        id="apt-table"
                        :items="aptList"
                        :per-page="perPage"
                        :current-page="currentPage"
                    ></b-table>
                    <!-- <b-row class="head" style="font-weight:bold;">
                        <b-col cols="2" class="headItem"></b-col>
                        <b-col class="headItem">가격 (만원)</b-col>
                        <b-col class="headItem">면적 (m<sup>2</sup>) </b-col>
                        <b-col class="headItem">층</b-col>
                        <b-col class="headItem">등록일</b-col>
                    </b-row>
                    <b-row v-for="(detail, index) in this.aptDetails" :key="index">
                        <b-col cols="2" class="listItem">{{ index + 1 }}</b-col>
                        <b-col class="listItem">{{ detail.dealAmount }}</b-col>
                        <b-col class="listItem">{{ detail.area }}</b-col>
                        <b-col class="listItem">{{ detail.floor }}</b-col>
                        <b-col class="listItem">
                            {{ detail.dealYear }}.{{ detail.dealMonth }}.{{ detail.dealDay }}
                        </b-col>
                    </b-row> -->
                </b-col>
            </b-row>
            <b-row id="toggle" class="text-center">
                <b-col cols="6"></b-col>
                <b-col cols="6">
                    <b-pagination
                        v-model="currentPage"
                        :total-rows="totalRows"
                        align="center"
                        aria-controls="apt-table"
                    >
                    </b-pagination>
                </b-col>
            </b-row>
        </vue-slide-up-down>
        <hr />
        <div style="margin-top:40px">
            <span style="font-weight:bold">{{ this.dong.value }}</span
            >에 위치한 <span style="font-weight:bold">{{ this.priceList.length + 1 }}</span
            >개의 아파트 중 <span style="color:red;font-weight:bold">{{ grade }}</span
            >위입니다.
        </div>
        <div class="chart">
            <house-chart
                :chardata="this.priceList"
                v-bind:aptNames="aptNames"
                v-bind:amountPerArea="amountPerArea"
            />
        </div>
    </b-container>
</template>

<script>
import { mapState } from "vuex";
import HouseChart from "./HouseChart.vue";
export default {
    name: "HouseDetail",
    components: {
        HouseChart,
    },
    data() {
        return {
            currentPage: 1,
            perPage: 10,
            aptList: [],
            active: false,
            aptNames: [],
            amountPerArea: [],
            options: {
                responsive: true,
                maintainAspectRatio: false,
            },
            grade: null,
        };
    },
    computed: {
        ...mapState(["apt", "dong", "aptName", "aptDetails", "priceList"]),
        totalRows() {
            console.log(this.aptDetails.length);
            return this.aptDetails.length;
        },
    },
    created() {
        console.log("house detail 진입");
        console.log(this.apt);
        this.$store.dispatch("getAptDetails");
        console.log("aptDetails : " + this.aptDetails);
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            console.log("initMap");
            this.loadRoadView();
        } else {
            const script = document.createElement("script");
            script.onload = () => kakao.maps.load(this.loadRoadView);
            script.src =
                "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d13cfc3fc2ba17e1abac62d6ed84e591&libraries=services";
            document.head.appendChild(script);
        }
        this.getData();
    },
    filters: {
        price(value) {
            if (!value) return value;
            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
    },
    methods: {
        getData() {
            for (let i = 0; i < this.priceList.length; i++) {
                this.aptNames.push(this.priceList[i].aptName);
                this.amountPerArea.push(this.priceList[i].amountPerArea);
                if (this.priceList[i].aptName == this.apt.aptName) {
                    this.grade = i + 1;
                    console.log("순위 : " + this.grade);
                }
            }
        },
        moveBeforeList() {
            console.log("moveBeforeList");
            console.log(this.dong);
            console.log(this.aptName.name);
            this.$router.go(-1);
        },
        loadRoadView() {
            var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
            var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

            var position = new kakao.maps.LatLng(this.apt.lat, this.apt.lng);
            roadviewClient.getNearestPanoId(position, 130, function(panoId) {
                roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            });
        },
        setAptList() {
            console.log("aptList 가져오기");
            console.log(this.aptDetails[0].aptName);
            this.aptList = [];
            for (let i = 0; i < this.aptDetails.length; i++) {
                console.log(i);
                let apt = {
                    " ": i + 1,
                    "가격 (만원)": this.aptDetails[i].dealAmount,
                    "면적 (m2)": this.aptDetails[i].area,
                    층: this.aptDetails[i].floor,
                    등록일: `${this.aptDetails[i].dealYear}.${this.aptDetails[i].dealMonth}.${this.aptDetails[i].dealDay}`,
                };
                this.aptList.push(apt);
            }
            this.toggle();
        },
        toggle() {
            this.active = !this.active;
        },
    },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR");
template {
    font-family: Century Gothic, sans-serif;
}
h3 {
    font-weight: bold;
}
.houseDetail {
    padding: 60px;
}
.head {
    border-top-style: solid;
    border-top-color: darkgray;
}
.headItem {
    height: 60px;
    text-align: center;
    padding: 15px;
    background-color: rgb(239, 243, 255);
}
.listItem {
    height: 50px;
    padding: 15px;
    border-bottom-style: solid;
    border-bottom-color: whitesmoke;
}

body {
    background: #2e394d;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
}

* {
    box-sizing: border-box;
}

input {
    height: 20px;
    left: 0;
    opacity: 0;
    position: absolute;
    top: 0;
    width: 20px;
}

.toggle {
    position: relative;
    display: inline-block;
}

label.toggle-item {
    width: 6em;
    background: #2e394d;
    height: 2em;
    display: inline-block;
    border-radius: 50px;
    position: relative;
    transition: all 0.3s ease;
    transform-origin: 20% center;
    cursor: pointer;
}
label.toggle-item:before {
    display: block;
    transition: all 0.2s ease;
    width: 1.5em;
    height: 1.5em;
    top: 0.25em;
    left: 0.25em;
    border-radius: 2em;
    border: 2px solid #88cf8f;
    transition: 0.3s ease;
}

.checkcross label:before {
    content: none;
}
.checkcross .check {
    border-radius: 50%;
    width: 1.5em;
    height: 1.5em;
    position: absolute;
    background: #8bc34a;
    transition: 0.4s ease;
    top: 4.5px;
    left: 4.5px;
}
.checkcross .check:before,
.checkcross .check:after {
    height: 4px;
    border-radius: 10px;
    background: #fff;
    transition: 0.2s ease;
}
.checkcross .check:before {
    width: 25px;
    transform: rotate(-45deg) translate(-6px, 20px);
}
.checkcross .check:after {
    width: 10px;
    transform: rotate(45deg) translate(20px, 11px);
}

#checkcross:checked + label .check {
    left: 68px;
    transform: rotate(360deg);
    background: #c34a4a;
}
#checkcross:checked + label .check:before {
    width: 27px;
    transform: rotate(-45deg) translate(-8px, 18px);
}
#checkcross:checked + label .check:after {
    width: 27px;
    transform: rotate(45deg) translate(18px, 8px);
}
.chart {
    max-width: 600px;
    margin: 20px auto;
}
</style>
