<template>
<div>
    <br>
    <br>
    <qna-list/>
</div>
    
</template>

<script>
import QnaList from "@/components/board/qna/QnaList.vue";
import {mapState} from 'vuex';

export default {
    name:"Board",
    components:{
        QnaList,
    },
    computed:{
        ...mapState(['qna']),
    }
}
</script>

<style>

</style>
