<template>
    <b-container align-v="center">
        <h3>공지사항</h3>
        <b-row align-h="between">
            <div v-if="adminLogin">
                <b-button @click="writeNotice">등록</b-button>
                &nbsp;&nbsp;
                <b-button @click="moveNotice" variant="warning">목록</b-button>
            </div>
            <div v-else>
                <b-button @click="moveNotice" variant="warning">목록</b-button>
            </div>
            <b-form inline>
                <b-form-select v-model="selectedSearch" :options="optionsSearch"></b-form-select>
                <b-form-input v-model="word" placeholder="내용 입력" />
                <b-button variant="outline-primary" @click="searchNotice">검색</b-button>
            </b-form>
        </b-row>
        <br />
        <div v-if="noticelist.length" class="list">
            <b-row class="m-2 tableHeader">
                <b-col cols="2" class="text-center head tableCol"><strong>번호</strong></b-col>
                <b-col cols="6" class="text-center head tableCol"><strong>제목</strong></b-col>
                <b-col cols="4" class="text-center head tableCol"><strong>날짜</strong></b-col>
            </b-row>
            <notice-list-item v-for="(notice, index) in noticelist" :key="index" :notice="notice" />
        </div>
        <div v-else class="text-center">
            등록된 게시글이 없습니다.
        </div>
    </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import NoticeListItem from "@/components/notice/NoticeListItem.vue";

export default {
    name: "NoticeList",
    components: {
        NoticeListItem,
    },
    data() {
        return {
            selectedSearch: "",
            optionsSearch: [
                { value: "title", text: "글제목" },
                { value: "content", text: "글내용" },
            ],
            word: "",
            adminLogin: false,
        };
    },
    created() {
        console.log("NoticeList.vue 호출");
        console.log(this.$store);
        this.$store.dispatch("getNoticelist");
    },
    computed: {
        ...mapState(["noticelist"]),
        ...mapActions(["getNoticeByTitle", "getNoticeByContent"]),
    },
    mounted() {
        this.adminCheck();
    },
    updated() {},
    methods: {
        searchNotice(e) {
            e.preventDefault();
            console.log("옵션 : " + this.selectedSearch + ", 검색어 : " + this.word);
            if (this.selectedSearch == "title") {
                this.$store.dispatch("getNoticeByTitle", this.word);
            } else {
                this.getNoticeByContent("word");
            }
            this.word = "";
        },
        writeNotice() {
            this.$router.push("/happyhouse/notice/regist");
        },
        adminCheck() {
            if (JSON.parse(sessionStorage.getItem("accessToken")).email === "admin") {
                this.adminLogin = true;
            } else {
                this.adminLogin = false;
            }
        },
        moveNotice() {
            setTimeout(function() {
                location.reload(true);
            }, 30);
        },
    },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR");
template {
    font-family: "Noto Sans KR", sans-serif;
}
h3 {
    font-weight: bold;
}
.list {
    border-style: solid;
    border-color: whitesmoke;
}
.tableHeader {
    background-color: whitesmoke;
    border-top-style: solid;
    border-top-color: rgb(175, 175, 175);
    height: 45px;
}
.tableCol {
    padding-top: 10px;
}
</style>
