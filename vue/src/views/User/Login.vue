<template>
<div>
    <div id="contact" class="form-1">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>로그인</h2>
					<br>
					<br>
                </div> <!-- end of col -->
            </div> <!-- end of row -->
			
			<div class="row">
				<modals-container />
			</div>
			
            <div class="row">
                <div class="col-lg-12">
                    <!-- Contact Form -->
                    <form id="contactForm">
                        <div class="form-group">
                            <input type="text" class="form-control-input" id="email" name="email" v-model="email" placeholder="" required>
                            <label class="label-control" for="cname">이메일 주소</label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control-input" id="userpwd" name="userpwd" v-model="userpwd" placeholder="" onkeydown="javascript:if(event.keyCode == 13) {login();}" required>
                            <label class="label-control" for="cemail">비밀번호</label>
                        </div><br><br>
                        <div class="form-group">
                            <button type="button" class="form-control-submit-button" @click="login">로그인</button>
							<!-- <button type="button" id="mvRegisterBtn" class="btn btn-primary">비밀번호 찾기</button> -->
							<br>
							<br>
							<br>
							<!-- Invitation -->
							<div class="basic-77">
								<div class="container">
									<div class="row">
										<div class="col-lg-12" style="color: white">
											비밀번호를 잊어 버리셨나요? &nbsp;
											<a class="btn-solid-lg page-scroll" @click="findpw_modal">비밀번호 찾기</a>
										</div> <!-- end of col -->
									</div> <!-- end of row -->
								</div> <!-- end of container -->
							</div> <!-- end of basic-7 -->
							<!-- end of invitation -->
						</div>
                    </form>
                    <!-- end of contact form -->
                </div> <!-- end of col -->
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of contact -->

</div>
</template>

<script>
import axios from "@/util/http-common";
import findPwPopup from "@/components/findPwPopup.vue";
import "@/css/styles.css";
export default {
	data () {
		return {
			message: '',
			email: '',
			userpwd: '',
		}
	},
	methods: {
		findpw_modal(){
                this.$modal.show(findPwPopup,{
                    hot_table : 'data',
                    modal : this.$modal },{
                        name: 'dynamic-modal',
                        width : '480px',
                        height : '275px',
                        draggable: false,
            })
		},
		login() {
			axios.post('/user/login', {
				email: this.email, // 로그인 폼에 입력한 이메일,
				userpwd: this.userpwd // 비밀번호를 서버로 보냄
			}).then((res) => {
				console.log(res);
				alert("로그인 성공~~!!");

				let token = res.data.token // 로그인을 하고 리턴 받는 값중 토큰 값을 let token으로 저장
				sessionStorage.setItem("access_token", token) // 세션 스토리지에 토큰 값을 저장
				this.getMemberInfo();
				
			}).catch(() => {
				alert("로그인 실패! 아이디와 비밀번호를 확인하세요");
			});
		},
		getMemberInfo() {
			let token = sessionStorage.getItem("access_token") // 세션 스토리지에 있는 토큰 값을 불러서
			let config = {
				headers: {
					"access_token" : token // 헤더값으로 저장
				}
			}
			axios.get('/user/login', config) // 이 헤더 값을 url로 서버에 전송
				.then(res => { // 토큰 값을 보내 서버에서 그 토큰에 맞는 유저 정보 값을 Vue로 보내줌
					// console.log(res);
					// console.log(res.data.user.username); 
					let userInfo = { // userInfo라는 변수에다 email, name, phoneno, joindate를 저장해줌
						email : res.data.user.email,
						name : res.data.user.username,
						phoneno : res.data.user.phoneno,
						joindate : res.data.user.joindate
					}
					sessionStorage.setItem("accessToken", JSON.stringify(userInfo));
					console.log(userInfo);
					location.href = "/";
			});
		},
	},
}
</script>

<style>

</style>