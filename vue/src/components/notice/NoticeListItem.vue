<template>
    <b-row
        class="m-2 line"
        @click="chooseNotice"
        @mouseover="colorChange(true)"
        @mouseout="colorChange(false)"
        :class="{ 'mouse-over-bgcolor': isColor }"
    >
        <b-col cols="2" class="text-center lineItem">{{ this.notice.no }}</b-col>
        <b-col cols="6" class="text-left lineItem">{{ this.notice.title }}</b-col>
        <b-col cols="4" class="text-center lineItem">{{ this.notice.regDate }}</b-col>
    </b-row>
</template>

<script>
import { mapActions } from "vuex";

export default {
    name: "NoticeListItem",
    data() {
        return {
            isColor: false,
        };
    },
    props: {
        notice: Object,
    },
    methods: {
        ...mapActions(["getNotice"]),
        chooseNotice() {
            console.log(this.notice);
            this.getNotice(this.notice);
            if (JSON.parse(sessionStorage.getItem("accessToken"))) {
                this.$router.push("/happyhouse/notice/detail");
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

<style>
.mouse-over-bgcolor {
    background-color: lightblue;
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
