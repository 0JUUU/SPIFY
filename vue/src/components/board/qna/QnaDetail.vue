<template>
    <div class="detail">
        <b-container v-if="qna.no" class="bv-example-row">
            <b-row align-h="between">
                <b-col class="text-left" cols="6">
                    <h4>{{ qna.title }}</h4>
                </b-col>
                <b-col cols="6">
                    <b-row align-h="end">
                        <div v-if="userLogin">
                            <b-button @click="modifyQna" variant="success">수정</b-button>
                            &nbsp;&nbsp;
                            <b-button @click="deleteQna" variant="danger">삭제</b-button>
                            &nbsp;&nbsp;
                            <b-button @click="moveList" variant="warning">글 목록</b-button>
                        </div>
                        <div v-else>
                            <b-button @click="moveList" variant="warning">글 목록</b-button>
                        </div>
                    </b-row>
                </b-col>
            </b-row>
            <br />
            <b-row align-h="start">
                <b-col cols="1">{{ qna.writerName }}</b-col>
                |
                <b-col cols="2">{{ qna.regDate }}</b-col>
            </b-row>
            <hr />
            <b-row class="mb-2 mt-1 text-left">
                <b-col>
                    <span v-html="this.qna.content"></span>
                </b-col>
            </b-row>
            <br />
            <br />
        </b-container>
        <div class="container" style="padding-right: 15px; padding-left: 15px; margin-right: auto; margin-left: auto;" id="disqus_thread"></div>
    </div>
</template>

<script>
(function() {
    // DON'T EDIT BELOW THIS LINE
    var d = document,
        s = d.createElement("script");
    s.src = "https://happyhouse-2.disqus.com/embed.js";
    s.setAttribute("data-timestamp", +new Date());
    (d.head || d.body).appendChild(s);
})();
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
export default {
    name: "QnaDetail",
    data() {
        return {
            userLogin: false,
            content: "",
        };
    },
    computed: {
        ...mapState(["qna"]),
    },
    created() {
        console.log("QnaDetail : " + this.qna.title);
        this.userCheck();
    },
    updated() {},
    methods: {
        moveList() {
            this.$router.push("/happyhouse/qna");
        },
        deleteQna() {
            console.log(this.qna.no);
            axios
                .delete("/qna/" + this.qna.no)
                .then((response) => {
                    console.log(response);
                    alert("삭제 성공!");
                })
                .catch(() => {
                    alert("삭제 중 문제가 발생했습니다.");
                });
            this.moveList();
        },
        modifyQna() {
            this.$router.push("/happyhouse/qna/modify");
        },
        userCheck() {
            console.log(JSON.parse(sessionStorage.getItem("accessToken")).name);
            console.log(this.qna.writerName);
            if (JSON.parse(sessionStorage.getItem("accessToken")).name === this.qna.writerName) {
                this.userLogin = true;
            } else {
                this.userLogin = false;
            }
        },
    },
};
</script>

<style scoped>
h4 {
    font-weight: bold;
}
.detail {
    padding-top: 70px;
}
#disqus_thread {
    width: 80%;
    padding-left: 200px;
}
</style>
