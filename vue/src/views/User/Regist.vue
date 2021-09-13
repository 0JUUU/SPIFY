<template>
  <div>
	<div id="contact" class="form-11">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>회원가입</h2>
					<br>
					<br>
                </div> <!-- end of col -->
            </div> <!-- end of row -->
            <div class="row">
                <div class="col-lg-12">
                    
                    <!-- Contact Form -->
                    <form id="contactForm">
						<div class="form-group">
                            <input type="text" class="form-control-input" id="username" ref="username" placeholder="" v-model="username">
                            <label class="label-control">이름</label>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control-input" id="email" ref="email" v-model="email">
                            <label class="label-control">이메일 주소</label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control-input" id="userpwd" ref="userpwd" placeholder="" v-model="userpwd">
                            <label class="label-control">비밀번호</label>
                        </div>
						<div class="form-group">
                            <input type="password" class="form-control-input" ref="pwdcheck" v-model="pwdcheck">
                            <label class="label-control">비밀번호 확인</label>
                        </div>
						<div class="form-group">
                            <input type="text" class="form-control-input" id="phoneno" ref="phoneno" v-model="phoneno">
                            <label class="label-control">전화번호</label>
                        </div>
						<br><br>
                        <div class="form-group">
                            <button type="button" class="form-control-submit-button" @click="checkValidation">회원가입</button>
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

export default {
    data() {
        return {
            username: '',
            email: '',
            userpwd: '',
			pwdcheck: '',
            phoneno: '',
        }
    },
    methods: {
		checkValidation() {
			let err = true;
			let msg = '';

			!this.username && (msg = '이름을 입력해주세요', err = false, this.$refs.username.focus());
			err && !this.email && (msg = '이메일을 입력해주세요', err = false, this.$refs.email.focus());
			err && !this.userpwd && (msg = '비밀번호를 입력해주세요', err = false, this.$refs.userpwd.focus());
			err && !this.pwdcheck && (msg = '비밀번호를 입력해주세요', err = false, this.$refs.pwdcheck.focus());
			err && !this.phoneno && (msg = '휴대전화 번호를 입력해주세요', err = false, this.$refs.phoneno.focus());

			if (!err) alert(msg);
			else this.regist();
		},

        regist() {
            axios.post('/admin/user', {
                username: this.username,
                email: this.email,
                userpwd: this.userpwd,
                phoneno: this.phoneno
            }).then((res) => {
				if (res.data === 'SUCCESS') {
					alert("회원가입에 성공했습니다.")
					this.$router.push('/happyhouse/user/login');
				} else {
					alert("이메일이 중복됩니다. 다시 시도하세요");
					this.$refs.email.focus();
					this.$router.push('/happyhouse/user/regist');
				}
            });
        }
    },
}
</script>

<style>

</style>