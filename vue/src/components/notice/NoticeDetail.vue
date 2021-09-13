<template>
    <div class="detail">
        <b-container v-if="notice.no" class="bv-example-row">
            <b-row align-h="between">
                <b-col class="text-left" cols="6">
                    <h4>{{ notice.title }}</h4>
                </b-col>
                <b-col cols="6">
                    <b-row align-h="end">
                        <div v-if="adminLogin">
                            <b-button @click="modifyNotice" variant="success">수정</b-button>
                            &nbsp;&nbsp;
                            <b-button @click="deleteNotice" variant="danger">삭제</b-button>
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
                <b-col cols="1">{{ notice.writer }}</b-col>
                |
                <b-col cols="2">{{ notice.regDate }}</b-col>
            </b-row>
            <hr />
            <b-row class="mb-2 mt-1 text-left">
                <b-col>
                    <span v-html="this.notice.content"></span>
                </b-col>
            </b-row>
            <br />
            <br />
        </b-container>
    </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
export default {
    name: "NoticeDetail",
    computed: {
        ...mapState(["notice"]),
    },
    data() {
        return {
            adminLogin: false,
        };
    },
    mounted() {
        this.adminCheck();
    },
    methods: {
        moveList() {
            this.$router.push("/happyhouse/notice");
        },
        deleteNotice() {
            console.log(this.notice.no);
            axios
                .delete("/notice/" + this.notice.no)
                .then((response) => {
                    console.log(response);
                    alert("삭제 성공!");
                })
                .catch(() => {
                    alert("삭제 중 문제가 발생했습니다.");
                });
            this.moveList();
        },
        modifyNotice() {
            this.$router.push("/happyhouse/notice/modify");
        },
        adminCheck() {
            if (JSON.parse(sessionStorage.getItem("accessToken")).email === "admin") {
                this.adminLogin = true;
            } else {
                this.adminLogin = false;
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
</style>
