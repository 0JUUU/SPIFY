<template>
  <b-row
    class="m-2"
    @click="chooseApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >

    <b-col cols="30" style = " text-align: left">{{ this.apt.aptName }} [{{ this.apt.buildYear }}]</b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  name: 'HouseListItem',
  data() {
    return {
      isColor: false,
    };
  },
  computed:{
        ...mapState(['clickApt']),
  },
  props: {
    apt: Object,
  },
  methods: {
    // chooseApt() {
    //   // this.$emit('select-apt', this.apt);
    //   this.$store.dispatch('selectApt', this.apt);
    // },
    ...mapActions(['selectApt','setClickApt']),

    chooseApt() {
      this.clickApt.flag = true;
      console.log("selectApt");
      this.selectApt(this.apt);
      console.log(this.clickApt.flag);
      
      setTimeout(function() {
        location.reload(true);
      }, 1);
      //this.$router.push('/happyhouse/house/detail');

    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.img-list {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
