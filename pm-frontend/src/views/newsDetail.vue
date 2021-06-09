<template>
<div class="page">
  <div class="header">
    <el-image
      :src="img" 
      v-for="img in imgUrls"
      :key="img"
    ></el-image>
  </div>
  <div class="main ql-container ql-snow">
    <div class="title">{{news.noticeTitle}}</div>
    <div class="ql-editor" v-html="news.noticeContent"></div>
  </div>
  <div class="footer">
    <span>Copyright © 2020 防灾科技学院 应急管理学院</span> 
  </div>
</div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import {listNewsById} from "@/api/system/notice";//新闻通知api
export default {
  data(){
    return {
      news: {},
      imgUrls: [require("@/assets/image/dzg_logo.png"), require("@/assets/image/title.png")]
    }
  },
  mounted(){
    console.log("hello");
    let noticeId = this.$route.params.noticeId;
    listNewsById(noticeId).then(response => {
      this.news = response.data;
    })
  }
}
</script>

<style scoped>
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
.main {
  flex: 1;
  width: 60%;
  box-sizing: border-box;
  margin-left: 20%;
  background: #fff;
  border: 1px solid #cccccc;
  border-radius: 8px;
  margin-bottom: 10px; 
  margin-top: 10px; 
  overflow:hidden;

}
.main .title {
  line-height: 50px;
  margin-top: 20px;
  font-size: 20px;
  text-align: center;
  font-weight: bolder;
  border-bottom: solid 1px #eee;
}
.footer {
  height: 40px;
  line-height: 40px;
  /* position: fixed; */
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
a:hover {
  color: red;
}
</style>