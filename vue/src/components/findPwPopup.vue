<template>
    <div class="container">
        <br>
        <h3 style="text-weight: bold">비밀번호 찾기</h3>
        <hr>
        <br>
        <div>
            <span class = "input-group-addon">
                이메일주소를 입력해 주세요
            </span>
            <br>
            <input type="text" v-model="email" class="form-control col-md-12" placeholder="XXX@XXX.com 형식입니다.">
        </div>
        <br>
        <br>
        <div class="row"> 
            <button class="btn-cancel col-md-6" @click="$emit('close')" type="button">취소</button>
            <button class="btn-submit col-md-6" @click="findPwd" type="button">비밀번호 찾기</button>
        </div>
        
    </div>
</template>
<script>
import axios from "@/util/http-common";
export default {
  data:function(){
      return {
          email:'',
      }
  },props : [
  ],methods : {
      findPwd() {
			axios.post('/admin/findpw/' + this.email, {
				email: this.email, // 로그인 폼에 입력한 이메일
			}).then((res) => {
				console.log(res);
				alert("이메일로 비밀번호를 전송했습니다.");
				
			}).catch(() => {
				alert("전송 실패! 이메일을 확인해주세요");
			});
		}
  }
}
</script>

<style scoped>
.btn-submit {
    display: inline-block;
    padding: 1.625rem 2.625rem 1.625rem 2.625rem;
    border: 1px solid #3abbbb;
    
    background-color: #3abbbb;
    color: #ffffff;
    font-weight: 600;
    font-size: 0.875rem;
    line-height: 0;
    text-decoration: none;
    transition: all 0.2s;
}

.btn-submit:hover {
    background-color: transparent;
    color: #3abbbb; /* needs to stay here because of the color property of a tag */
    text-decoration: none;
}

.btn-cancel {
    display: inline-block;
    padding: 1.625rem 2.625rem 1.625rem 2.625rem;
    border: 1px solid #3abbbb;
    
    background-color: #ffffff;
    color: #3abbbb;
    font-weight: 600;
    font-size: 0.875rem;
    line-height: 0;
    text-decoration: none;
    transition: all 0.2s;
}

.btn-cancel:hover {
    background-color: #3abbbb;
    color: #ffffff; /* needs to stay here because of the color property of a tag */
    text-decoration: none;
}
</style>