<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select @change="getProjectPhases"
          v-model="queryParams.categoryCode"
          placeholder="请选择项目类别"
          size="small">
          <el-option
            v-for="projectCategory in projectCategoryList"
            :key="projectCategory.categoryCode"
            :label="projectCategory.categoryCode + '-' + projectCategory.categoryName"
            :value="projectCategory.categoryCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="项目状态" prop="projectStatus">
        <el-select  v-model="queryParams.projectStatus" placeholder="请选择项目状态"  size="small">
          <el-option v-for="status in projectStatusOptions" :key="status.value" :label="status.label" :value="status.value" ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handlePass"
          v-hasPermi="['pm:unitreview:edit']"
        >通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleReject"
          v-hasPermi="['pm:unitreview:remove']"
        >拒绝</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          :disabled="multiple"
          @click="handleBack"
          v-hasPermi="['pm:unitreview:add']"
        >退回</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pm:unitreview:export']"
        >导出</el-button>
      </el-col> -->
    </el-row>

    <el-table v-loading="loading" :data="unitreviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="自增1" align="center" prop="projectId" /> -->
      <el-table-column label="类别" align="center" prop="categoryCode" />
      <!-- <el-table-column label="项目编号" align="center" prop="projectCode" /> -->
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="申报年度" align="center" prop="projectYear" />
      <!-- <el-table-column label="申报人" align="center" prop="userCode" /> -->
      <el-table-column label="申报人" align="center" prop="userName" />
      <!-- <el-table-column label="归口管理部门" align="center" prop="proxyOrgCode" /> -->
      <el-table-column label="归口管理部门" align="center" prop="proxyOrgName" />
      <!-- <el-table-column label="申报人所在部门" align="center" prop="myOrgCode" /> -->
      <el-table-column label="申报人所在部门" align="center" prop="myOrgName" />
      <el-table-column label="所属领域" align="center" prop="projectArear" />
      <el-table-column label="投资来源" align="center" prop="moneyFrom" />
      <el-table-column label="项目优先级" align="center" prop="projectPriority" />
      <el-table-column label="项目开始时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目结束时间" align="center" prop="endDate" width="180">
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
      <!-- <el-table-column label="关键字" align="center" prop="keywords" /> -->
      <!-- <el-table-column label="电子文档路径" align="center" prop="pdfPath" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-success"
            @click="handlePass(scope.row)"
            v-hasPermi="['pm:unitreview:edit']"
          >通过</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-error"
            @click="handleReject(scope.row)"
            v-hasPermi="['pm:unitreview:remove']"
          >拒绝</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-refresh-left"
            @click="handleBack(scope.row)"
            v-hasPermi="['pm:unitreview:edit']"
          >退回</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['pm:unitreview:remove']"
          >查看</el-button>
        </template>
      </el-table-column>
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
import { listUnitreview, getUnitreview, delUnitreview, addUnitreview, updateUnitreview, exportUnitreview,getUnitProjectStatus } from "@/api/pm/unitreview";
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import {listProjectcategoryphase} from "@/api/pc/projectcategoryphase"

export default {
  // name: "Unitreview",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 单位项目审核表格数据
      unitreviewList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectStatus: undefined,
        categoryCode: undefined
      },
      projectStatusOptions: [],
      projectCategoryList: [],
      //项目类别下面的阶段数组，用来改变projectStatus
      projectPhases: [],
      //项目状态字典
      statusOptions: []
    };
  },
  created() {
    this.getCategoryList();
    this.getProjectPhases();
    getUnitProjectStatus().then(res => {
      this.queryParams.projectStatus = res.rows[0].value;
      this.projectStatusOptions = res.rows;
    });
    this.getDicts("sys_project_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    getCategoryList() {
      this.loading = true;
      listByAdminUserName().then(res => {
        this.loading = false;
        this.projectCategoryList = res.rows;
        if(this.projectCategoryList.length){
          this.queryParams.categoryCode = res.rows[0].categoryCode; 
          this.getList();
        }

      });
    },
    //用来改变项目状态的列表
    getProjectPhases(){
      let tmp = {
        categoryCode: this.queryParams.categoryCode
      }
      listProjectcategoryphase(tmp).then(res =>{
        this.projectPhases = res.rows;
      })
    },
    /** 查询单位项目审核列表 */
    getList() {
      this.loading = true;
      listUnitreview(this.queryParams).then(response => {
        this.unitreviewList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },   
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    handlePass(row){
      let tmp = {};
      tmp.projectId = row.projectId;
      if(row.projectStatus != "4004"){
        //按照phaseCode从小到大排序
        this.projectPhases.sort((a,b) => {
          return a.phaseCode - b.phaseCode;
        });
        let flag = 0;
        //找到要赋予的那个projectStatus的值
        for(let item of this.projectPhases){
          if(item.phaseCode == row.projectStatus){
            flag = 1;
            continue;
          }
          if(flag == 1 && item.enabled == 1){
            tmp.projectStatus = item.phaseCode;
            break;
          }
        }
      }else {
        tmp.projectStatus = "1";
      }
      updateUnitreview(tmp).then(res => {
        if(res.code == 200){
          this.msgSuccess(res.msg)
          this.getList();
        }
      })
    },
    handleReject(row){
      let tmp = {};
      tmp.projectId = row.projectId;
      tmp.projectStatus = - this.queryParams.projectStatus;
      updateUnitreview(tmp).then(res => {
        if(res.code == 200){
          this.msgSuccess(res.msg)
          this.getList();
        }
      })
    },
    handleBack(row){
      //按照phaseCode从大到小排序
      this.projectPhases.sort((a,b) => {
        return b.phaseCode - a.phaseCode;
      });
      let flag = 0;
      let tmp = {};
      tmp.projectId = row.projectId;
      //找到要赋予的那个projectStatus的值
      for(let item of this.projectPhases){
        if(item.phaseCode == row.projectStatus){
          flag = 1;
          continue;
        }
        if(flag == 1 && item.enabled == 1){
          tmp.projectStatus = item.phaseCode;
          break;
        }
      }
      updateUnitreview(tmp).then(res => {
        if(res.code == 200){
          this.msgSuccess(res.msg)
          this.getList();
        }
      })
    },
    handleView(row){
      window.open(row.pdfPath);
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    projectStatusFormat(row, column){
      return this.selectDictLabel(this.statusOptions,row.projectStatus)
    }
  }
};
</script>