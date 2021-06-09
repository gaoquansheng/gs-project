<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申报年度" prop="projectYear">
        <el-input
          v-model="queryParams.projectYear"
          placeholder="请输入申报年度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select  v-model="queryParams.categoryCode" clearable placeholder="请选择项目类别"  size="small">
          <el-option v-for="category in categoryList" 
            :key="category.categoryCode"
            :label="category.categoryCode+'-'+category.categoryName"
            :value="category.categoryCode"
            >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

  

    <el-table v-loading="loading" :data="myProjectList" >
      <!-- <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="projectId" /> -->
      <el-table-column label="类别代号" align="center" prop="categoryCode" />
      <!-- <el-table-column label="项目编号" align="center" prop="projectCode" /> -->
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="申报年度" align="center" prop="projectYear" />
      <!-- <el-table-column label="申报人Code" align="center" prop="userCode" /> -->
      <!-- <el-table-column label="申报人" align="center" prop="userName" /> -->
      <!-- <el-table-column label="归口管理部门Code" align="center" prop="proxyOrgCode" /> -->
      <!-- <el-table-column label="归口管理部门" align="center" prop="proxyOrgName" /> -->
      <!-- <el-table-column label="所在部门Code" align="center" prop="myOrgCode" /> -->
      <el-table-column label="归属部门" align="center" prop="proxyOrgName" />
      <el-table-column label="所属领域" align="center" prop="projectArear" :formatter="projectArearFormat"/>
      <el-table-column label="投资来源" align="center" prop="moneyFrom" :formatter="moneyFromFormat"/>
      <el-table-column label="优先级" align="center" prop="projectPriority" :formatter="projectPriorityFormat"/>
      <el-table-column label="开始时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" align="center" prop="appTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.appTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请经费" align="center" prop="appAmount" />
      <el-table-column label="自筹经费" align="center" prop="myAmount" />
      <el-table-column label="总金额" align="center" prop="totalAmount" />
      <el-table-column label="项目状态" align="center" prop="projectStatus" :formatter="projectStatusFormat"/>
      <!-- <el-table-column label="建设内容描述" align="center" prop="projectSummary" /> -->
      <el-table-column label="关键字" align="center" prop="keyWords" />
      <el-table-column label="电子文档" align="center" prop="pdfPath" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-paperclip"
            @click="handleJump(scope.row)"
          >查看</el-button>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pm:myProject:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pm:myProject:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listMyProject, delMyProject, addMyProject, updateMyProject, exportMyProject } from "@/api/pm/myProject";
import {listProjectcategory} from "@/api/pc/projectcategory"

export default {
  // name: "MyProject",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 我的项目表格数据
      myProjectList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: undefined,
        projectYear: undefined,
        categoryCode: undefined,
      },
      categoryList: [],
      projectStatusOptions: [],
      projectTaskStatusOptions: [],
      projectConclusionReportStatusOptions: [],
      projectPriorityOptions: [],
      projectArearOptions: [],
      moneyFromOptions: []
    };
  },
  created() {
    this.getList();
    this.getCategoryList();
    this.getDicts("sys_project_status").then(response => {
      this.projectStatusOptions = response.data;
    });
    this.getDicts("sys_projecttask_status").then(response => {
      this.projectTaskStatusOptions = response.data;
    });
    this.getDicts("sys_projectconclusionreport_status").then(response => {
      this.projectConclusionReportStatusOptions = response.data;
    });
    this.getDicts("project_priority").then(response => {
      this.projectPriorityOptions = response.data;
    });
    this.getDicts("project_arear").then(response => {
      this.projectArearOptions = response.data;
    });
    this.getDicts("money_from").then(response => {
      this.moneyFromOptions = response.data;
    })
  },
  methods: {
    /** 查询我的项目列表 */
    getList() {
      this.loading = true;
      listMyProject(this.queryParams).then(response => {
        this.myProjectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getCategoryList(){
      listProjectcategory().then(res =>{
        this.categoryList = res.rows;
      })
    },
    projectStatusFormat(row, column) {
      return this.selectDictLabel(this.projectStatusOptions, row.projectStatus);
    },
    projectTaskStatusFormat(row, column) {
      return this.selectDictLabel(this.projectTaskStatusOptions, row.projectTaskStatus);
    },
    projectConclusionReportStatusFormat(row, column) {
      return this.selectDictLabel(this.projectConclusionReportStatusOptions, row.projectConclusionReportStatus);
    },
    projectPriorityFormat(row, column) {
      return this.selectDictLabel(this.projectPriorityOptions, row.projectPriority);
    },
    projectArearFormat(row, column) {
      return this.selectDictLabel(this.projectArearOptions, row.projectArear);
    },
    moneyFromFormat(row, colume) {
      return this.selectDictLabel(this.moneyFromOptions, row.moneyFrom);
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleJump(row){
      this.$router.push({name: "annex", params: {projectId: row.projectId}})
    }
  }
};
</script>