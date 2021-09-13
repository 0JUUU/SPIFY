import axios from "@/util/http-common";
import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        searchKeyword: {},
        clickApt: {},
        aptName: {},
        dong: {},
        qnalist: [],
        qna: {},
        noticelist: [],
        notice: {},
        apts: [],
        apt: Object,
        aptDetails: [],
        priceList: [],
    },
    getters: {
        searchKeyword(state) {
            return state.searchKeyword;
        },
        clickApt(state) {
            return state.clickApt;
        },
        aptName(state) {
            return state.aptName;
        },
        dong(state) {
            return state.dong;
        },
        qnalist(state) {
            return state.qnalist;
        },
        qna(state) {
            return state.qna;
        },
        noticelist(state) {
            return state.noticelist;
        },
        notice(state) {
            return state.notice;
        },
        aptDetails(state) {
            return state.aptDetails;
        },
        priceList(state) {
            return state.priceList;
        },
    },
    mutations: {
        GET_APT_LIST(state, apts) {
            // console.log(state, apts);
            state.apts = apts;
        },
        SELECT_APT(state, apt) {
            state.apt = apt;
        },
        SET_QNA_LIST(state, payload) {
            state.qnalist = payload;
        },
        SET_QNA(state, payload) {
            state.qna = payload;
            console.log("SET_QNA : " + state.qna.title);
        },
        SET_NOTICE_LIST(state, payload) {
            state.noticelist = payload;
        },
        SET_NOTICE(state, payload) {
            state.notice = payload;
        },
        SET_CLICK_APT(state, payload) {
            state.clickApt = payload;
        },
        SET_APT_DETALIS_LIST(state, payload) {
            state.aptDetails = payload;
        },
        SET_PRICE_LIST(state, payload) {
            state.priceList = payload;
        },
    },
    actions: {
        // Q&A 관련
        getQnalist(context) {
            axios.get("/qna/list").then(({ data }) => {
                context.commit("SET_QNA_LIST", data);
            });
        },
        getQna(context, payload) {
            console.log("getQna >> " + payload);
            console.dir(payload.no);
            axios.get("/qna/" + payload.no).then(({ data }) => {
                console.log(data);
                context.commit("SET_QNA", data);
            });
        },
        getQnaByTitle(context, payload) {
            console.dir(payload);
            axios.get("/qna/title/" + payload).then(({ data }) => {
                console.log("Q&A 제목으로 찾은 결과 : " + data);
                context.commit("SET_QNA_LIST", data);
            });
        },
        getQnaByContent(context, payload) {
            console.dir(payload);
            axios.get("/qna/content/" + payload.word).then(({ data }) => {
                console.log("Q&A 내용으로 찾은 결과 : " + data);
                context.commit("SET_QNA_LIST", data);
            });
        },
        getQnaByUserName(context, payload) {
            console.dir(payload);
            axios.get("/qna/user/" + payload.word).then(({ data }) => {
                console.log("Q&A 작성자 이름으로 찾은 결과 : " + data);
                context.commit("SET_QNA_LIST", data);
            });
        },

        // 공지사항 관련
        getNoticelist(context) {
            axios.get("/notice/list").then(({ data }) => {
                context.commit("SET_NOTICE_LIST", data);
            });
        },
        getNotice(context, payload) {
            console.dir(payload.no);
            axios.get("/notice/" + payload.no).then(({ data }) => {
                console.log(data);
                context.commit("SET_NOTICE", data);
            });
        },
        getNoticeByTitle(context, payload) {
            console.dir(payload);
            axios.get("/notice/title/" + payload).then(({ data }) => {
                console.log("공지사항 제목으로 찾은 결과 : " + data);
                context.commit("SET_NOTICE_LIST", data);
            });
        },
        getNoticeByContent(context, payload) {
            console.dir(payload);
            axios.get("/notice/content/" + payload.word).then(({ data }) => {
                console.log("공지사항 내용으로 찾은 결과 : " + data);
                context.commit("SET_NOTICE_LIST", data);
            });
        },
        getAptList({ commit }) {
            axios
                .get("/house/searchDong/" + this.state.dong.value)
                .then((response) => {
                    commit("GET_APT_LIST", response.data);
                })
                .catch((error) => {
                    console.dir(error);
                });
        },
        getAptListByName({ commit }) {
            console.log(
                "getAptListByName >> dong " +
                    this.state.dong.value +
                    "/apt : " +
                    this.state.aptName.name
            );
            axios
                .get(`/house/searchApt`, {
                    params: {
                        dong: this.state.dong.value,
                        aptName: this.state.aptName.name,
                    },
                })
                .then((response) => {
                    console.log("이름으로 검색 : " + response.data);
                    commit("GET_APT_LIST", response.data);
                })
                .catch((error) => {
                    console.dir(error);
                });
        },
        selectApt({ commit }, apt) {
            commit("SELECT_APT", apt);
        },
        setClickApt({ commit }, clickApt) {
            commit("SET_CLICK_APT", clickApt);
        },
        getAptDetails({ commit }) {
            console.log(commit);
            console.log("getAptDetails : " + this.state.apt.aptName);
            axios
                .get(`/house/aptList/${this.state.apt.dong}/${this.state.apt.aptName}`)
                .then((response) => {
                    //console.log("아파트 리스트 가지고 왔음 : " + response.data);
                    commit("SET_APT_DETALIS_LIST", response.data);
                })
                .catch((error) => {
                    console.dir(error);
                });
            // axios.get(`/house/`)
        },

        // 평당 가격정보
        getPriceList({ commit }) {
            console.log("평당 가격 정보 불러올게 : " + this.state.dong.value);
            axios
                .get(`/house/priceList/${this.state.dong.value}`)
                .then((response) => {
                    console.log("actions : 평당 가격 정보 가지고 옴 : " + response.data);
                    commit("SET_PRICE_LIST", response.data);
                })
                .catch((error) => {
                    console.dir(error);
                });
        },
    },
    modules: {},
    plugins: [createPersistedState()],
});
