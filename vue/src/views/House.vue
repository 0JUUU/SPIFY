<template>
    <b-container class="bv-example-row">
        <br /><br />
        <house-search-bar />
        <house-map />
        <!-- <mat-map/> -->
        <!-- <house-list/> -->
    </b-container>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseMap from "@/components/house/HouseMap.vue";
// import HouseList from "@/components/house/HouseList.vue";
//import MatMap from "@/components/house/MatMap.vue";
export default {
    name: "House",
    components: {
        HouseSearchBar,
        HouseMap,
    },
    data() {
        return {};
    },
    methods: {},
};
</script>

<style>
@charset "UTF-8";

a {
    color: #444;
    text-decoration: none;
}

.article {
    background-color: #f8f8f7;
    overflow: auto;
}

.article .left-box {
    margin-top: 20px;
    margin-left: 5px;
    padding-top: 20px;
    padding-left: 40px;
    padding-bottom: 80px;
    width: 50%;
    float: left;
    box-sizing: border-box;
    text-align: center;
    background-color: #dbf9c8;
}

.sido,
.gu,
.dong {
    width: 15%;
    padding-top: 7px;
    padding-bottom: 7px;
    font-size: 15px;
}

.left-box input[type="text"] {
    line-height: 30px;
}

.searchBtn {
    width: 15%;
    font-size: 15px;
    padding-top: 7px;
    padding-bottom: 7px;
}
.article .left-box img {
    width: 40%;
}

.table-title {
    padding-left: 30px;
}

.table-wrap {
    margin-left: 1%;
    padding-left: 1%;
    padding-bottom: 1%;
    float: left;
    width: 48%;
    height: 550px;
    box-sizing: border-box;
    text-align: left;
    overflow: auto;
    background-color: white;
}

.table-wrap .aptNameText {
    display: inline-block;
}

img.wish {
    float: right;
    margin-right: 50px;
    margin-top: 20px;
    width: 20px;
}

.table-wrap hr {
    border-top: 1px dotted #bbb;
}
#map {
    width: 90%;
    height: 400px;
    margin-top: 20px;
    padding-right: 20px;
}
</style>
