<template>
  <div>
    <el-table 
      row-key="docId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      v-loading="loading" :data="projectDocList" >
      <el-table-column label="项目类别" align="center" prop="categoryCode" />
      <el-table-column label="阶段流程" align="center" prop="phaseName" />
      <el-table-column label="文档标题" align="center" prop="docTitle" />
      <!-- <el-table-column label="上传文件路径" align="center" prop="docPath" /> -->
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传人" align="center" prop="uploadBy" />
      <el-table-column label="是否审核通过" align="center" prop="passed" :formatter="passedFormat"/>
      <el-table-column label="评审意见" align="center" prop="review" />
      <el-table-column label="审核人" align="center" prop="reviewBy" />
      <el-table-column label="审核时间" align="center" prop="reviewTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            v-show="!scope.row.children"
          >下载</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-show="!scope.row.children"
          >预览</el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>

import {listProjectdoc} from "@/api/pm/projectdoc"
export default {
  data() {
    return {
      loading: true,
      queryParams: {
          projectId: "",
          passed: 1
      },
      projectDocList: [],
      verifyOptions: ""
    }
  },
  created(){
    this.queryParams.projectId = this.$route.params.projectId;
    this.getProjectDoc();
    this.getDicts("sys_verify_status").then(response => {
      this.verifyOptions = response.data;
    });
  },
  methods: {
      getProjectDoc(){
          this.loading = true;
          listProjectdoc(this.queryParams).then(res =>{
              this.projectDocList = this.formatterData(res.rows);
              this.loading = false;
             
          })
      },
      handleDownload(row){
        this.download(row.docPath)
      },
      handleView(row){
        window.open(row.docPath);
      },
      formatterData(projectDocList){
        let ind = []
        let res = []
        for(let i = 0;i<projectDocList.length && ind.indexOf(i) == -1;i++){
          let tmp = JSON.parse(JSON.stringify(projectDocList[i]));
          tmp.children = [];
          if(i == projectDocList.length-1){
            res.push(projectDocList[i])
          }
          for(let j = i+1;j<projectDocList.length;j++){
            if(projectDocList[i].phaseCode == projectDocList[j].phaseCode){
              ind.push(j);
              tmp.children.push(projectDocList[j])
            }
            if(j==projectDocList.length -1 && tmp.children.length>0){
              tmp.children.push(projectDocList[i])
              //渲染数据时必须指定rowkey,这里给个不重复的字段
              let now = new Date();
              tmp.docId = projectDocList[i].docId + now.getTime();
              res.push(tmp)
            }
            if(j==projectDocList.length -1 && tmp.children.length == 0){
              res.push(projectDocList[i])
            }
          }
        }
       return res;
      },
      passedFormat(row, column) {
        return this.selectDictLabel(this.verifyOptions, row.passed);
      },
  }
}
</script>