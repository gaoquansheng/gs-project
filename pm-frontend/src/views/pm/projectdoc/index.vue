<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目名称" >
          <el-select v-model="index" @change="handleProjectChange"  placeholder="请选择项目"  size="small">
            <el-option v-for="(myProject,index) in myProjectList" 
              :key="myProject.projectId"
              :label="myProject.categoryCode +'-'+myProject.projectName"
              :value="index"
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectdocList" >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="文档id" align="center" prop="docId" /> -->
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
      <el-table-column label="审核意见" align="center" prop="review" />
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
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-show="scope.row.passed != 1"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-show="scope.row.passed != 1"
          >删除</el-button>
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

    <!-- 添加或修改上传项目附件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文档标题" prop="docTitle">
          <el-input v-model="form.docTitle" placeholder="请输入文档标题" />
        </el-form-item>
        <el-form-item label="上传文件" prop="docPath">
          <el-upload
            ref="upload"
            class="upload-demo"
            :action="uploadUrl"
            :headers="headers"
            :on-change="handleDocChange"
            :on-success="docFileUploadSuccess"
            :file-list="docFileList"
          >
            <el-button
              slot="trigger"
              size="small"
              type="success"
            >点击上传</el-button>
          </el-upload>
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
import { listProjectdoc, getProjectdoc, delProjectdoc, addProjectdoc, updateProjectdoc, exportProjectdoc } from "@/api/pm/projectdoc";
import { myProject } from "@/api/pm/myProject";
import { listWorkflowphase } from "@/api/pc/workflowphase";
import { getToken } from "@/utils/auth";

export default {
  // name: "Projectdoc",
  data() {
    return {
      index: 0,
      selectProjectId: "",
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
      // 上传项目附件表格数据
      projectdocList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: this.selectProjectId,
        phaseCode: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        docTitle: [
          { required: true, message: "文档标题不能为空", trigger: "blur" }
        ],
        docPath: [
          { required: true, message: "上传文件路径不能为空", trigger: "blur" }
        ],
      },
      myProjectList: [],
      projectPhaseList: [],
      headers: {
        Authorization: "Bearer " + getToken()
      },
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      docFileList: [],
      verifyOptions: ""
    };
  },
  created() {
    this.getDicts("sys_verify_status").then(response => {
      this.verifyOptions = response.data;
    });
    this.getProjectList();
  },
  methods: {
    //查询自己未结题的项目
    getProjectList(){
      this.loading = true;
      myProject().then(res => {
        this.loading = false;
        this.myProjectList = res.rows;
        if(this.myProjectList.length){
          this.selectProjectId = res.rows[0].projectId;
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
    /** 查询上传项目附件列表 */
    getList() {
      this.loading = true;
      console.log(this.queryParams);
      listProjectdoc(this.queryParams).then(response => {
        this.projectdocList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        docId: undefined,
        docTitle: undefined,
        docPath: undefined,
        projectId: this.selectProjectId,
        categoryCode: this.queryParams.categoryCode,
        phaseCode: this.queryParams.phaseCode,
        uploadTime: undefined,
        uploadBy: undefined,
        passed: -1,
        review: undefined,
        reviewBy: undefined,
        reviewTime: undefined
      };
      this.docFileList = [];
      this.resetForm("form");
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.docId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加上传项目附件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const docId = row.docId || this.ids
      getProjectdoc(docId).then(response => {
        this.form = response.data;
        this.docFileList = [{name:response.data.docPath}];
        this.open = true;
        this.title = "修改上传项目附件";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log(this.form);
          
          if (this.form.docId != undefined) {
            updateProjectdoc(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addProjectdoc(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const docIds = row.docId || this.ids;
      this.$confirm('是否确认删除上传项目附件编号为"' + docIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProjectdoc(docIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有上传项目附件数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectdoc(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    handleDocChange(files, fileList) {
      if (fileList.length > 0) {
        this.docFileList = [fileList[fileList.length - 1]]; // 这一步，是 展示最后一次选择的csv文件
      }
    },
    docFileUploadSuccess(res, file) {
      console.log(res);
      this.form.docPath = res.url;
    },
    handleProjectChange(index){
      this.queryParams.categoryCode = this.myProjectList[index].categoryCode;
      this.selectProjectId = this.myProjectList[index].projectId;
    },
        // 是否审核通过字典翻译
    passedFormat(row, column) {
      return this.selectDictLabel(this.verifyOptions, row.passed);
    },
  }
};
</script>