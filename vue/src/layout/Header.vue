<template>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark" style="background-color: black">
        <!-- 나중에 투명으로 바꾸면 됨 -->
        <div class="container">
            <!-- Text Logo - Use this if you don't have a graphic logo -->
            <a href="/happyhouse#header" class="navbar-brand logo-text page-scroll">HappyHouse</a>

            <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="/happyhouse#details" class="nav-link page-scroll">매물검색</a>
                    </li>
                    <li class="nav-item">
                        <a href="/happyhouse#service" class="nav-link page-scroll">서비스</a>
                    </li>
                    <li class="nav-item">
                        <a href="/happyhouse#about" class="nav-link page-scroll">팀원소개</a>
                    </li>
                    <li class="nav-item">
                        <router-link to="/happyhouse/notice" class="nav-link page-scroll">공지사항</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/happyhouse/qna" class="nav-link page-scroll">게시판</router-link>
                    </li>

                    <div v-if="!loginok">
                        <li class="nav-item">
                            <router-link to="/happyhouse/user/modify" class="nav-link page-scroll">내 정보</router-link>
                        </li>
                    </div>
                    <div v-if="!loginok">
                        <li class="nav-item">
                            <a @click="logout" class="nav-link page-scroll">로그아웃</a>
                        </li>
                    </div>

                    <div v-if="loginok">
                        <li class="nav-item">
                            <router-link to="/happyhouse/user/login" class="nav-link page-scroll"
                                >로그인</router-link
                            >
                        </li>
                    </div>

                    <div v-if="loginok">
                        <li class="nav-item">
                            <router-link to="/happyhouse/user/regist" class="nav-link page-scroll"
                                >회원가입</router-link
                            >
                        </li>
                    </div>
                </ul>
            </div>
            <!-- end of navbar-collapse -->
        </div>
        <!-- end of container -->
    </nav>
    <!-- end of navbar -->
    <!-- end of navigation -->
</template>
<script>
import "../css/styles.css";
import "../css/main_page.css";
export default {
    name: "Header",
    data() {
        return {
            loginok: false,
            username: "",
            nim: "",
        };
    },
    mounted() {
        this.logincheck();
    },
    methods: {
        logincheck() {
            if (!sessionStorage.getItem("accessToken")) {
                this.loginok = true;
            } else {
                this.loginok = false;
            }
        },
        logout() {
            alert("로그아웃 되었습니다.");
            sessionStorage.clear();
            this.loginok = false;
            location.href = "/";
        },
    },
};
</script>

<style scoped>
* {
    vertical-align: top;
}

html,
body {
    width: 100%;
    height: 95%;
    margin: 0;
}

header a {
    font-size: 15px;
    font-weight: 600;
    line-height: 1.5;
    color: #444;
    font-family: 돋움, Dotum, AppleGothic, Helvetica, sans-serif;
    text-decoration: none;
}

header {
    width: 100%;
    height: 50px;
    margin-top: 20px;
}

img {
    max-width: 100%;
}

.wrapper {
    width: 100%;
    height: 100%;
}

.wrapper .logo {
    display: inline-block;
    width: 10%;
    margin-left: -50%;
}

.wrapper nav {
    display: inline-block;
    height: 100%;
}

.menubar .menu li {
    display: inline-block;
    margin: 0 20px 0 20px;
}

.wrapper .right-container {
    display: inline-block;
    margin-top: 10px;
    margin-right: 30px;
    float: right;
}

.wrapper .search {
    display: inline-block;
}

.wrapper .search .search_text {
    width: 70%;
    line-height: 25px;
}

.wrapper .user_button_group {
    display: inline-block;
    margin-top: 7px;
}
.article {
    background-color: #f8f8f7;
    overflow: auto;
}

.article .dong_search {
    margin-top: 20px;
    margin-left: 5px;
    padding-left: 40px;
    padding-top: 100px;
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
.search_dong {
    width: 15%;
    font-size: 15px;
    padding-top: 7px;
    padding-bottom: 7px;
}
.article .dong_search img {
    width: 40%;
}

.article div h2 {
    margin-top: 30px;
}

.article div ul {
    display: flex;
    flex-wrap: wrap;
    list-style: none;
}

.article div ul li {
    margin: 5px;
    height: 180px;
    flex-grow: 1;
}

.article div ul li img {
    max-height: 100%;
    min-width: 100%;
    object-fit: cover;
    vertical-align: bottom;
}

@media (max-aspect-ratio: 1/1) {
    li {
        height: 30vh;
    }
}

@media (max-height: 480px) {
    li {
        height: 80vh;
    }
}

@media (max-aspect-ratio: 1/1) and (max-width: 480px) {
    ul {
        flex-direction: row;
    }
    li {
        height: auto;
        width: 100%;
    }
    article div ul li img {
        width: 100%;
        max-height: 75vh;
        min-width: 0;
    }
}
</style>
