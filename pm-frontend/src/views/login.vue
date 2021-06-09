<template>
  <div class="page">
    <div class="header">
      <el-image
        :src="img" 
        v-for="img in imgUrls"
        :key="img"
        ></el-image>
    </div>
    <div class="main">
      <div class="leftContent">
        <div class="news">
          <self-news :initNewsList="newsList" :initInfo="{title:'新闻',url: '/moreNews?type=2'}" />
                  <!-- <div class="notice"> -->
          <self-news :initNewsList="noticeList" :initInfo="{title:'通知',url: '/moreNews?type=1'}" />
        <!-- </div> -->
        </div>

      </div>
      <!-- <div class="rightContent"> -->
        <div class="login">
          <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
            <h3 class="title">甘肃省地震局项目库管理系统</h3>
            <el-form-item prop="username">
              <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
                <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                type="password"
                auto-complete="off"
                placeholder="密码"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input
                v-model="loginForm.code"
                auto-complete="off"
                placeholder="验证码"
                style="width: 63%"
                @keyup.enter.native="handleLogin"
              >
                <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
              </el-input>
              <div class="login-code">
                <img :src="codeUrl" @click="getCode" class="login-code-img"/>
              </div>
            </el-form-item>
            <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
            <el-form-item style="width:100%;">
              <el-button
                :loading="loading"
                size="medium"
                type="primary"
                style="width:100%;"
                @click.native.prevent="handleLogin"
              >
                <span v-if="!loading">登 录</span>
                <span v-else>登 录 中...</span>
              </el-button>
            </el-form-item>
          </el-form>
      <!--  底部  -->
        </div>
        <!-- <div class="fileDownload">文件下载</div> -->
      <!-- </div> -->
    </div>
    <div class="footer">
      <span>Copyright © 2020 防灾科技学院 应急管理学院</span>
    </div>
  </div>
</template>

<script>
import {
  listNews,
  getNotice,
  delNotice,
  addNotice,
  updateNotice,
  exportNotice
} from "@/api/system/notice";//新闻通知api
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'
import SelfNews from "@/views/components/news.vue"
export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "用户名不能为空" }
        ],
        password: [
          { required: true, trigger: "blur", message: "密码不能为空" }
        ],
        code: [{ required: true, trigger: "change", message: "验证码不能为空" }]
      },
      loading: false,
      redirect: undefined,
      // 查询参数
      newsList: [],
      noticeList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      imgUrls: [require("@/assets/image/dzg_logo.png"), require("@/assets/image/title.png")]
    };
  },
  components: {
    SelfNews
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },

  created() {
    //获取所有的新闻
    this.getNewsList();
    this.getCode();
    this.getCookie();

  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.loginForm.uuid = res.uuid;
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    getNewsList(){
      listNews().then(response => {
        this.newsList = response.rows.filter(item => item.noticeType == 2);        
        this.noticeList = response.rows.filter(item => item.noticeType == 1);       

      });
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
              this.getCode();
            });
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.page {
  height: 100%;
  background-image: url("../assets/image/bg.jpg");
  background-size: cover;
  display: flex;
  flex-direction: column;
}
.header {
  height: 200px;
  margin: 0 auto;
}
.header .el-image {
  margin-top: 40px;
}
// .header :first-child {
//   margin-right: 20px;
// }

.main {
  flex: 1;
  display: flex;
  justify-content: center;
  margin-top: 20px;
  opacity: 0.8;
}
.footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.leftContent {
   display: flex;
}
.news {
  background: #fff;
  border: 1px solid #cccccc;
  border-radius: 8px;
  margin-bottom: 10px;
  // overflow: hidden;
  box-sizing: border-box;
  height: 440px;
  margin-right: 10px;
}

.fileDownload {
  height:40%;
  margin-top: 10px;
  background: #fff;
  border: 1px solid #cccccc;
  border-radius: 8px;
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  height: 440px;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.login-code-img {
  height: 38px;
}
</style>
