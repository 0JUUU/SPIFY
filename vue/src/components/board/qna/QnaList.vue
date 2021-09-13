<template>
    <b-container align-v="center">
        <h3>자유 게시판</h3>
        <b-row align-h="between">
            <div v-if="userLogin">
                <b-button @click="writeQna">글쓰기</b-button> &nbsp;&nbsp;
                <b-button @click="moveQna" variant="warning">목록</b-button>
            </div>
            <div v-else>
                <b-button @click="moveQna" variant="warning">목록</b-button>
            </div>
            <b-form inline>
                <b-form-select v-model="selectedSearch" :options="optionsSearch"></b-form-select>
                <b-form-input v-model="word" placeholder="내용 입력" />
                <b-button variant="outline-primary" @click="searchQna">검색</b-button>
            </b-form>
        </b-row>
        <br />
        <div v-if="qnalist.length" class="list">
            <b-row class="m-2 tableHeader">
                <b-col cols="1" class="text-center head tableCol"><strong>번호</strong></b-col>
                <b-col cols="6" class="text-center head tableCol"><strong>제목</strong></b-col>
                <b-col cols="2" class="text-center head tableCol"><strong>작성자</strong></b-col>
                <b-col cols="3" class="text-center head tableCol"><strong>날짜</strong></b-col>
            </b-row>
            <qna-list-item v-for="(qna, index) in qnalist" :key="index" :qna="qna" />
        </div>
        <div v-else class="text-center">
            등록된 게시글이 없습니다.
        </div>
    </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import QnaListItem from "@/components/board/qna/QnaListItem.vue";

export default {
    name: "QnaList",
    components: {
        QnaListItem,
    },
    data() {
        return {
            selectedSearch: "",
            optionsSearch: [
                { value: "writerName", text: "글작성자" },
                { value: "title", text: "글제목" },
                { value: "content", text: "글내용" },
            ],
            word: "",
            userLogin: false,
        };
    },
    created() {
        console.log("QnaList.vue 호출");
        console.log(this.$store);
        this.$store.dispatch("getQnalist");
    },
    mounted() {
        this.userLoginCheck();
    },
    computed: {
        ...mapState(["qnalist"]),
        ...mapActions(["getQnaByTitle", "getQnaByContent", "getQnaByUserName"]),
    },
    methods: {
        searchQna(e) {
            e.preventDefault();
            console.log("옵션 : " + this.selectedSearch + ", 검색어 : " + this.word);
            if (this.selectedSearch == "title") {
                this.$store.dispatch("getQnaByTitle", this.word);
            } else if (this.selectedSearch == "content") {
                this.getQnaByContent("word");
            } else {
                this.getQnaByUserName("word");
            }
        },
        writeQna() {
            this.$router.push("/happyhouse/qna/regist");
        },
        userLoginCheck() {
            if (JSON.parse(sessionStorage.getItem("accessToken"))) {
                this.userLogin = true;
            } else this.userLogin = false;
        },
        moveQna() {
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
