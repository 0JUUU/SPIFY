import Vue from "vue";
import VueRouter from "vue-router";
import Main from "@/views/Main.vue";
import Login from "@/views/User/Login.vue";
import Regist from "@/views/User/Regist.vue";
import Modify from "@/views/User/Modify.vue";
import Board from "@/views/Board.vue";
import House from "@/views/House.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import QnaDetail from "@/components/board/qna/QnaDetail.vue";
import QnaRegist from "@/components/board/qna/QnaRegist.vue";
import QnaModify from "@/components/board/qna/QnaModify.vue";
import Notice from "@/views/Notice.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeRegist from "@/components/notice/NoticeRegist.vue";
import NoticeModify from "@/components/notice/NoticeModify.vue";
Vue.use(VueRouter);

const requireAuth = () => (to, from, next) => {
    if (sessionStorage.getItem("accessToken")) {
        // 세션스토리지에 엑세스 토큰이 있다면 즉, 로그인 했다면
        return next(); // 다음 페이지로 진행 가능
    } else {
        next("/happyhouse/user/login"); // 로그인도 안했는데 들어가려한다? 로그인부터 하렴
    }
};

const onlyAdmin = () => (to, from, next) => {
    if (JSON.parse(sessionStorage.getItem("accessToken")).email === "admin") {
        // 관리자로 로그인 했을 경우만
        return next(); // 다음 페이지로 진행 가능
    } else {
        alert("관리자만 접근 가능합니다."); // 그외에는 나가리
        next("/");
    }
};

const routes = [
    {
        path: "/",
        name: "Main",
        redirect: "/happyhouse/",
    },
    {
        path: "/happyhouse",
        name: "Main",
        component: Main,
    },

    // 로그인, 회원가입, 회원정보 수정 관련
    {
        path: "/happyhouse/user/login",
        name: "Login",
        component: Login,
    },
    {
        path: "/happyhouse/user/regist",
        name: "Regist",
        component: Regist,
    },
    {
        path: "/happyhouse/user/modify",
        name: "Modify",
        component: Modify,
        beforeEnter: requireAuth(),
    },
    // 주택 매물 관련
    {
        path: "/happyhouse/house",
        name: "House",
        component: House,
    },
    {
        path: "/happyhouse/house/detail",
        name: "HouseDetail",
        component: HouseDetail,
    },
    // Q&A 관련
    {
        path: "/happyhouse/qna",
        name: "Board",
        component: Board,
    },
    {
        path: "/happyhouse/qna/detail",
        name: "QnaDetail",
        component: QnaDetail,
    },
    {
        path: "/happyhouse/qna/regist",
        name: "QnaRegist",
        component: QnaRegist,
        beforeEnter: requireAuth(),
    },
    {
        path: "/happyhouse/qna/modify",
        name: "QnaModify",
        component: QnaModify,
        beforeEnter: requireAuth(),
    },

    // 공지사항 관련
    {
        path: "/happyhouse/notice",
        name: "Notice",
        component: Notice,
    },
    {
        path: "/happyhouse/notice/detail",
        name: "NoticeDetail",
        component: NoticeDetail,
    },
    {
        path: "/happyhouse/notice/regist",
        name: "NoticeRegist",
        component: NoticeRegist,
        beforeEnter: onlyAdmin(),
    },
    {
        path: "/happyhouse/notice/modify",
        name: "NoticeModify",
        component: NoticeModify,
        beforeEnter: onlyAdmin(),
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;
