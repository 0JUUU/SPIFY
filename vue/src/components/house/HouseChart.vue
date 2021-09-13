<script>
import { Bar } from "vue-chartjs";

export default {
    extends: Bar,
    props: {
        chartdata: {
            type: Array,
            default: null,
        },
        aptNames: {
            type: Array,
        },
        amountPerArea: {
            type: Array,
        },
    },
    data() {
        return {
            datacollection: {
                labels: this.aptNames, // 가로축 데이터
                datasets: [
                    {
                        label: "평당 가격 (원)", // 차트 상단에 차트 이름
                        data: this.amountPerArea, // 세로축 데이터
                        backgroundColor: "#f08080",
                        borderColor: "#f87979",
                    },
                ],
            }, // chart데이터 끝

            options: {
                responsive: true,
                maintainAspectRatio: false, // 차트 width, height 크기조절
            }, //옵션 끝
        }; // 모든 data 끝
    },
    mounted() {
        console.log("차트 그리기");
        console.log("chartdata : " + this.datacollection);
        console.log(this.aptNames);
        console.log(this.amountPerArea);
        console.log("options : " + this.options);
        this.renderChart(this.datacollection, this.options);
    },
};
</script>

<style></style>
