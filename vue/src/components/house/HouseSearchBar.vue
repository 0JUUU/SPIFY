<template>
    <b-form inline>
        <b-form-input type="text" name="dong" v-model="inputDong" />
        <b-form-input
            type="text"
            name="apt"
            placeholder="아파트 이름"
            v-model.lazy="inputAptName"
        />
        <button @click="searchHouse">검색하기</button>
    </b-form>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
    name: "SearchBar",
    data() {
        return {
            inputAptName: "",
            inputDong: "",
        };
    },
    computed: {
        ...mapState(["dong", "aptName", "searchKeyword"]),
        ...mapActions(["getAptList"]),
    },
    created() {
        this.inputDong = this.dong.value;
    },
    methods: {
        searchHouse(e) {
            e.preventDefault();
            this.aptName.name = this.inputAptName;
            this.searchKeyword.value = "aptList";
            console.log(this.aptName.name);
            this.dong.value = this.inputDong;
            console.log("동 입력 후 : " + this.dong.value);
            if (this.inputAptName == "") {
                this.$store.dispatch("getAptList");
            } else {
                this.$store.dispatch("getAptListByName");
            }
            this.$router.push("/happyhouse/house");
            setTimeout(function() {
                location.reload(true);
            }, 30);
        },
    },
};
</script>

<style></style>
