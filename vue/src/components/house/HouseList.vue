<template>
  <b-container v-if="apts && apts.length != 0" class="bv-example-row mt-2">
    <house-list-item v-for="(apt, index) in apts" :key="index" :apt="apt" />
  </b-container>
  <b-container v-else class="bv-example-row mt-2">
    <b-row>
      <b-col><b-alert show>아파트 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapState, mapActions} from "vuex";
import HouseListItem from "@/components/house/HouseListItem.vue";
export default {
    name:"HouseList",
    data() {
        return {
        loginok: false,
        }
    },
    components:{
        HouseListItem
    },
    computed:{
        ...mapState(['aptName', 'apts', 'dong']),
        ...mapActions(['getAptList']),
    },
    created(){
        console.log("HouseList 호출 되기 전 >> dong : " + this.dong.value + ", aptName : " + this.aptName.name);
        // aptName에 이름 저장되어 있다면 byname 호출
        // 아니면 그냥 동에 해당하는 리스트 반환
        if(this.aptName.name == "") this.$store.dispatch("getAptList");
        else this.$store.dispatch("getAptListByName");
        console.log(this.apts);
    },
    mounted() {
        this.logincheck(); 
    },
    methods: {
        logincheck() {
            if (!localStorage.getItem("accessToken")) {
            this.loginok = true;
            } else {
            this.loginok = false;
            }
        },
        logout() {
            alert("로그아웃 되었습니다.");
            localStorage.clear();
            this.loginok = false;
            location.href = "/";
        }

    }
}
</script>

<style>

</style>