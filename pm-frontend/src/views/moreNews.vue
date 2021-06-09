<template>
  <div class="page">
    <div class="header"   >
      <el-image
        :src="img" 
        v-for="img in imgUrls"
        :key="img"
        ></el-image>
    </div>

    <div class="main">
      <el-row>
        <el-col>
          <el-table v-loading="loading" :data="noticeList" stripe>     
            <el-table-column label="标题" align="left"  prop="noticeTitle">
              <template slot-scope="scope">
                <router-link 
                :to="'/newsDetail/'+scope.row.noticeId"
                target="_blank"
                >
                  <el-link type="primary">{{scope.row.noticeTitle}}</el-link>
                </router-link>
              </template>
            </el-table-column>
            <el-table-column label="创建者" align="center" prop="createBy" width="100" />
            <el-table-column sortable label="创建时间" align="center" prop="createTime" width="100">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getAllNews"
          />
        </el-col>
      </el-row>
    </div>


    <div class="footer">
      <span>Copyright © 2020 防灾科技学院 应急管理学院</span>
    </div>
  </div>
</template>

<script>
import { listAllNews } from "@/api/system/notice";


export default {
  name: "Notice",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 公告表格数据
      noticeList: [],

      // 查询参数
      queryParams: {
        noticeType: "",
        pageNum: 1,
        pageSize: 10
      },
      imgUrls: [require("@/assets/image/dzg_logo.png"), require("@/assets/image/title.png")]
    };
  },
  created() {
    this.queryParams.noticeType = this.$route.query.type;
    this.getAllNews();
  },
  methods: {
    /** 查询公告列表 */
    getAllNews() {
      this.loading = true;
      console.log(this.queryParams);
      listAllNews(this.queryParams).then(response => {
        console.log(response);
        this.noticeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
  }
};
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