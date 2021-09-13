<template>
    <b-container class="write">
        <div class="text-center">
            <h3>
                글
                <template v-if="type == 'create'">작성하기</template>
                <template v-else>수정하기</template>
            </h3>
            <br />
        </div>
        <b-form method="post">
            <table class="tbAdd">
                <colgroup>
                    <col width="15%" />
                    <col width="*" />
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td>
                        <b-form-input id="title" v-model="title" />
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <ckeditor v-model="content" :config="editorConfig"></ckeditor>
                    </td>
                </tr>
            </table>
        </b-form>
        <template v-if="type == 'create'" class="btnWrap">
            <div>
                <b-button @click="checkValue" variant="primary">등록</b-button>&nbsp;&nbsp;&nbsp;
                <b-button @click="moveQnaList" variant="warning">목록</b-button>
            </div>
        </template>
        <template v-else>
            <div>
                <b-button @click="checkValue" variant="primary">수정</b-button>&nbsp;&nbsp;&nbsp;
                <b-button @click="deleteQna" variant="danger">삭제</b-button>
            </div>
        </template>
    </b-container>
</template>

<script>
import axios from "@/util/http-common.js";
import { mapState } from "vuex";
export default {
    name: "WriteForm",
    props: {
        type: { type: String },
    },
    data() {
        return {
            writer: "",
            writerName: "",
            title: "",
            content: "",
            editorConfig: {},
        };
    },
    created() {
        console.log(this.type);
        console.log(this.qnalist.length);
        if (this.type === "modify") {
            console.log(this.qna);
            // axios.get(`/${this.$route.params.isbn}`).then(({ data }) => {
            this.writer = this.qna.writer;
            this.writerName = this.qna.writerName;
            this.title = this.qna.title;
            this.content = this.qna.content;
            //   });
        }
    },
    computed: {
        ...mapState(["qnalist", "qna"]),
    },
    methods: {
        checkValue() {
            // 사용자 입력값 체크하기
            // isbn, 제목, 저자, 가격, 설명이 없을 경우 각 항목에 맞는 메세지를 출력
            let err = true;
            let msg = "";

            !this.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
            err &&
                !this.content &&
                ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

            if (!err) alert(msg);
            // 만약, 내용이 다 입력되어 있다면 registBook 호출
            else this.type == "create" ? this.qnaRegist() : this.qnaModify();
        },

        qnaRegist() {
            console.log(this.qnalist.length);
            this.writer = JSON.parse(sessionStorage.getItem("accessToken")).email;
            this.writerName = JSON.parse(sessionStorage.getItem("accessToken")).name;
            console.log(this.content);
            axios
                .post("/qna", {
                    no: this.qnalist.length + 1,
                    writer: this.writer,
                    title: this.title,
                    writerName: this.writerName,
                    content: this.content,
                })
                .then(({ data }) => {
                    console.log(data);
                    let msg = "등록이 완료되었습니다.";
                    alert(msg);
                    this.moveQnaList();
                })
                .catch(({ data }) => {
                    console.log(data);
                    let msg = "등록 처리시 문제가 발생했습니다.";
                    alert(msg);
                    this.moveQnaList();
                });
        },
        qnaModify() {
            console.log("수정시작!");
            console.log(this.qna.no);
            axios
                .put(`/qna/${this.qna.no}`, {
                    no: this.qna.no,
                    writer: this.writer,
                    title: this.title,
                    writerName: this.writerName,
                    content: this.content,
                })
                .then(({ data }) => {
                    console.log(data);
                    let msg = "수정이 완료되었습니다.";
                    alert(msg);
                    this.$router.push("/happyhouse/qna");
                })
                .catch(() => {
                    let msg = "수정 처리시 문제가 발생했습니다.";
                    alert(msg);
                    this.$router.push("/happyhouse/qna");
                });
        },
        deleteQna() {
            console.log(this.qna.no);
            axios
                .delete("/qna/" + this.qna.no)
                .then((response) => {
                    console.log(response);
                    alert("삭제 성공!");
                })
                .catch(() => {
                    alert("삭제 중 문제가 발생했습니다.");
                });
            this.moveList();
        },
        moveQnaList() {
            this.$router.push("/happyhouse/qna");
        },
    },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans+KR");
template {
    font-family: "Noto Sans KR", sans-serif;
}
h3 {
    font-weight: bold;
}
.write {
    padding-top: 60px;
}
.tbAdd {
    width: 100%;
    border-top: 1px solid #888;
}
.tbAdd th,
.tbAdd td {
    border-bottom: 1px solid #eee;
    padding: 5px 0;
}
.tbAdd td {
    padding: 10px 10px;
    box-sizing: border-box;
}
.tbAdd td input {
    width: 100%;
    min-height: 30px;
    box-sizing: border-box;
    padding: 0 10px;
}
.tbAdd td textarea {
    width: 100%;
    min-height: 300px;
    padding: 10px;
    box-sizing: border-box;
}
.btnWrap {
    text-align: center;
    margin: 20px 0 0 0;
}
.btnWrap b-button {
    margin: 0 10px;
}
</style>
