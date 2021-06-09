<template>
  <div class="app-container">
 <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode" >
          <el-select v-model="queryParams.categoryCode"  placeholder="请选择项目类别"  size="small">
            <el-option v-for="category in categoryList" 
              :key="category.categoryCode"
              :label="category.categoryCode +'-'+category.categoryName"
              :value="category.categoryCode"
              >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类别流程" prop="phaseCode">
          <el-select v-model="queryParams.phaseCode" placeholder="请选择类别流程"  size="small">
            <el-option v-for="projectPhase in projectPhaseList" 
              :key="projectPhase.phaseCode"
              :label="projectPhase.phaseName"
              :value="projectPhase.phaseCode"
              >
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="审核状态" prop="passed">
          <el-select v-model="queryParams.passed" placeholder="请选择审核状态"  size="small">
            <el-option
              v-for="dict in verifyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="projectdocreviewList" >
      <el-table-column label="文档标题" align="center" prop="docTitle" />
      <el-table-column label="项目类别" align="center" prop="categoryCode" />
      <el-table-column label="项目阶段" align="center" prop="phaseName" />
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传人" align="center" prop="uploadBy" />
      <el-table-column label="是否审核通过" align="center" prop="passed" :formatter="passedFormat" />
      <el-table-column label="评审意见" align="center" prop="review" />
      <el-table-column label="审核人" align="center" prop="reviewBy" />
      <el-table-column label="审核时间" align="center" prop="reviewTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="!scope.row.passed"
            @click="handleUpdate(scope.row)"
          >审核</el-button>
           <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
          >预览</el-button>
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

    <!-- 添加或审核项目附件审核对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form"  label-width="100px">
        <el-form-item label="是否审核通过">
          <el-select v-model="form.passed" placeholder="请选择是否审核通过">
            <el-option
              v-for="dict in verifyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评审意见" prop="review">
          <el-input v-model="form.review" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProjectdocreview, getProjectdocreview, delProjectdocreview, addProjectdocreview, updateProjectdocreview, exportProjectdocreview } from "@/api/pm/projectdocreview";
import { myProject } from "@/api/pm/myProject";
import { listWorkflowphase } from "@/api/pc/workflowphase";
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { getToken } from "@/utils/auth";
export default {
  // name: "Projectdocreview",
  data() {
    return {
      index: 0,
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 项目附件审核表格数据
      projectdocreviewList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      verifyOptions: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryCode: undefined,
        phaseCode: undefined,
        passed:0,
      },
      // 表单参数
      form: {},
      projectPhaseList: "",
      categoryList: ""
    };
  },
 created() {
    this.getCategoryList();    
    this.getDicts("sys_verify_status").then(response => {
      this.verifyOptions = response.data;
    });
  },
  methods: {
    getCategoryList(){
      this.loading = true;
      listByAdminUserName().then(res =>{
        this.loading = false
        this.categoryList = res.rows;
        if(this.categoryList.length){
          this.queryParams.categoryCode = res.rows[0].categoryCode;
          this.getPhaseList();
        }
      })
    },
    getPhaseList(){
       listWorkflowphase().then(res => {
        this.projectPhaseList = res.data;
        if(this.projectPhaseList.length){
          this.queryParams.phaseCode = res.data[0].phaseCode;
          this.getList();
        }
      })
    },
    /** 查询项目附件审核列表 */
    getList() {
      this.loading = true;
      listProjectdocreview(this.queryParams).then(response => {
        this.projectdocreviewList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否审核通过字典翻译
    passedFormat(row, column) {
      return this.selectDictLabel(this.verifyOptions, row.passed);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        docTitle: undefined,
        passed: undefined,
        review: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      console.log(this.queryParams);
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 审核按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.open = true;
      this.title = "项目附件审核";
      this.form = row;
    },
    handleView(row){
        window.open(row.docPath);
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            updateProjectdocreview(this.form).then(response => {
              if (this.form.passed != undefined) {
                this.msgSuccess("审核成功");
                this.open = false;
                this.getList();
              }
            });
        }
      });
    },
  } 
};
</script>