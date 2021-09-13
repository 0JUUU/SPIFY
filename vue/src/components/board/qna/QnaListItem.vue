<template>
    <div>
        <b-row
            class="m-2 line"
            @click="chooseQna"
            @mouseover="colorChange(true)"
            @mouseout="colorChange(false)"
            :class="{ 'mouse-over-bgcolor': isColor }"
        >
            <b-col cols="1" class="text-center lineItem">{{ this.qna.no }}</b-col>
            <b-col cols="6" class="text-left lineItem">{{ this.qna.title }}</b-col>
            <b-col cols="2" class="text-center lineItem">{{ this.qna.writerName }}</b-col>
            <b-col cols="3" class="text-center lineItem">{{ this.qna.regDate }}</b-col>
        </b-row>
    </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
    name: "QnaListItem",
    data() {
        return {
            isColor: false,
        };
    },
    props: {
        qna: Object,
    },
    methods: {
        ...mapActions(["getQna"]),
        chooseQna() {
            console.log("chooseQna : " + this.qna.title);
            this.getQna(this.qna);

            if (JSON.parse(sessionStorage.getItem("accessToken"))) {
                setTimeout(function() {
                    location.href = "/happyhouse/qna/detail";
                    // this.$router.push("/happyhouse/qna/detail");
                }, 10);
            } else {
                this.adminLogin = false;
                alert("로그인한 사용자만 접근 가능합니다.");
            }
        },
        colorChange(flag) {
            this.isColor = flag;
        },
    },
};
</script>

<style scoped>
.mouse-over-bgcolor {
    background-color: powderblue;
}
.line {
    border-bottom-style: solid;
    border-bottom-color: whitesmoke;
    height: 40px;
}
.lineItem {
    padding: 6px;
}
</style>
