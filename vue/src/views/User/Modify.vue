<template>
<div>
<br><br>    <br>
    <br>
    <h2 style="text-align: center">회원정보 수정/탈퇴하기</h2>
    <div class="container" align="center">
        <div class="col-lg-6" align="center">
            <form id="memberform">
                <div class="form-group" align="left">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="username" ref="username" v-model="user.username" readonly>
                </div>
                <div class="form-group" align="left">
                    <label for="email">아이디</label><br>
                    <input type="text" class="form-control" id="email" ref="email" v-model="user.email" readonly>
                </div>
                <div class="form-group" align="left">
                    <label for="">비밀번호</label>
                    <input type="password" class="form-control" id="userpwd" ref="userpwd" v-model="user.userpwd" placeholder="">
                </div>
                <div class="form-group" align="left">
                    <label for="">비밀번호 재입력</label>
                    <input type="password" class="form-control" id="pwdcheck" ref="pwdcheck" v-model="user.pwdcheck" placeholder="">
                </div>
                <div class="form-group" align="left">
                    <label for="phoneno">전화번호</label>
                    <input type="text" class="form-control" id="phoneno" ref="phoneno" v-model="user.phoneno">
                </div>
               <br><br>
            </form>
            <div class="form-group" align="center">
                <button type="button" class="btn btn-primary" @click="checkValidation">정보수정</button>
                <button class="btn" style="background-color: red; color: white" @click="deleteUser">회원 탈퇴</button>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from "@/util/http-common.js";
export default {
    data() {
        return {
            user: {
                username: JSON.parse(sessionStorage.getItem("accessToken")).name,
                email: JSON.parse(sessionStorage.getItem("accessToken")).email,
                userpwd: '',
                pwdcheck: '',
                phoneno: JSON.parse(sessionStorage.getItem("accessToken")).phoneno
            }
        }
    },
    methods: {
		checkValidation() {
			let err = true;
			let msg = '';

			!this.user.username && (msg = '비밀번호를 입력해주세요', err = false, this.$refs.userpwd.focus());
			err && !this.user.pwdcheck && (msg = '비밀번호를 입력해주세요', err = false, this.$refs.pwdcheck.focus());
			err && !this.user.phoneno && (msg = '휴대전화 번호를 입력해주세요', err = false, this.$refs.phoneno.focus());

			if (!err) alert(msg);
			else this.modify();
		},
        modify() {
            axios.put('/admin/user', {
                username: this.user.username,
                email: this.user.email,
                userpwd: this.user.userpwd,
                phoneno: this.user.phoneno
            }).then((res) => {
				if (res.data === 'SUCCESS') {
					alert("정보수정에 성공했습니다.")
					this.$router.push('/happyhouse/user/modify');
                    this.makeNewToken();
				}
            });
        },
        makeNewToken() {
			axios.post('/user/login', {
				email: this.user.email, 
				userpwd: this.user.userpwd 
			}).then((res) => {

				let token = res.data.token 
				sessionStorage.setItem("access_token", token) 
				this.saveNewToken();
			})
		},
		saveNewToken() {
			let token = sessionStorage.getItem("access_token") 
			let config = {
				headers: {
					"access_token" : token
				}
			}
			axios.get('/user/login', config) 
				.then(res => { 

					let userInfo = { 
						email : res.data.user.email,
						name : res.data.user.username,
						phoneno : res.data.user.phoneno,
						joindate : res.data.user.joindate
					}
					sessionStorage.setItem("accessToken", JSON.stringify(userInfo));
					location.href = "/happyhouse/user/modify";
			});
		},
        deleteUser() {
            axios
            .delete('/admin/user/'+this.user.email)
            .then(res => {
                console.log(res);
                alert("회원 탈퇴 성공!");
                location.href = "/happyhouse/";
                sessionStorage.clear();
            }).catch(() => {
                alert("삭제 중 문제가 발생했습니다.");
            });
        },
    },
}
</script>

<style>

</style>