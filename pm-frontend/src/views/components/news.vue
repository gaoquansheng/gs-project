<template>
  <div class="newsMain">
      <div class="title">
          <div>{{info.title}}</div>
          <div>
              <router-link  :to="info.url" target="_blank">more>></router-link>
          </div>
      </div>
      <div class="news">
          <ul>
              <li v-for="item in newsList" :key="item.noticeId">
                  <router-link target="_blank" :to="'/newsDetail/'+item.noticeId">
                    <span> {{item.noticeTitle}} </span>
                    <span> {{item.createTime}} </span>
                    </router-link>
              </li>
          </ul>
      </div>
  </div>
</template>

<script>


export default {
  props: ['initNewsList','initInfo'],
  data() {
    return {
      // 公告表格数据
      // newsList: this.initNewsList
      // title: this.initTitle
    };
  },

  computed: {
    newsList(){
      return this.initNewsList;
    },
    info(){
      return this.initInfo;
    }
  }
};
</script>

<style scoped>
.newsMain {
  margin: 20px;

}
.newsMain .title {
  display: flex;
  font-size: 16px;
  justify-content: space-between;
  border-bottom: 1px solid #383a4c;
  color: #0178a4;
  padding-bottom: 5px;
}
.newsMain .news ul {
  font-size: 14px;
  padding-left: 10px;
}
.newsMain .news ul li{
  border-bottom: 1px dotted #313131;
  line-height: 30px;
  list-style: disc;
  margin-left: 0;

}
.newsMain .news ul li a {
  display: flex;
  justify-content: space-between;
  white-space:nowrap;
  overflow: hidden;
  text-overflow:ellipsis;
}
.newsMain .news ul li a :first-child{
  width: 400px;
  white-space:nowrap;
  overflow: hidden;
  text-overflow:ellipsis;
}
</style>