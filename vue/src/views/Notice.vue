<template>
  <div>
      <br>
      <br>
      <notice-list/>
  </div>
</template>

<script>
import {mapState} from "vuex";
import NoticeList from "@/components/notice/NoticeList.vue";
export default {
    name:"Notice",
    components:{
        NoticeList,
    },
    computed:{
        ...mapState(['notice']),
    }
}
</script>

<style>

</style>