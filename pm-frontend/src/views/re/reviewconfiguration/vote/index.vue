<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-input
          v-model="queryParams.categoryCode"
          placeholder="请输入项目类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目流程" prop="phaseCode">
        <el-input
          v-model="queryParams.phaseCode"
          placeholder="请输入项目流程"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['re:projectcategoryreviewrulevoteoption:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['re:projectcategoryreviewrulevoteoption:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['re:projectcategoryreviewrulevoteoption:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['re:projectcategoryreviewrulevoteoption:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectcategoryreviewrulevoteoptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="投票流程" align="center" prop="optionId" /> -->
      <el-table-column label="项目类别" align="center" prop="categoryCode" />
      <el-table-column label="项目流程" align="center" prop="phaseCode" />
      <el-table-column label="投票title" align="center" prop="optionTitle" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['re:projectcategoryreviewrulevoteoption:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['re:projectcategoryreviewrulevoteoption:remove']"
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

    <!-- 添加或修改projectcategoryreviewrulevoteoption对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="项目类别" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入项目类别" />
        </el-form-item>
        <el-form-item label="项目流程" prop="phaseCode">
          <el-input v-model="form.phaseCode" placeholder="请输入项目流程" />
        </el-form-item> -->
        <el-form-item label="投票流程" prop="optionTitle">
          <el-input v-model="form.optionTitle" type="textarea" placeholder="请输入内容" />
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
import { listProjectcategoryreviewrulevoteoption, getProjectcategoryreviewrulevoteoption, delProjectcategoryreviewrulevoteoption, addProjectcategoryreviewrulevoteoption, updateProjectcategoryreviewrulevoteoption, exportProjectcategoryreviewrulevoteoption } from "@/api/re/projectcategoryreviewrulevoteoption";

export default {
  name: "Projectcategoryreviewrulevoteoption",
  data() {
    return {
      params:{},
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
      // projectcategoryreviewrulevoteoption表格数据
      projectcategoryreviewrulevoteoptionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryCode: undefined,
        phaseCode: undefined,
        optionTitle: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryCode: [
          { required: true, message: "项目类别不能为空", trigger: "blur" }
        ],
        phaseCode: [
          { required: true, message: "项目流程不能为空", trigger: "blur" }
        ],
        optionTitle: [
          { required: true, message: "投票流程不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.params = this.$route.query;
    this.getList();
    
    console.log(this.params);
  },
  methods: {
    /** 查询projectcategoryreviewrulevoteoption列表 */
    getList() {
      this.loading = true;
      listProjectcategoryreviewrulevoteoption(this.params.categoryCode,this.params.phaseCode).then(response => {
        this.projectcategoryreviewrulevoteoptionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        optionId: undefined,
        categoryCode: undefined,
        phaseCode: undefined,
        optionTitle: undefined
      };
      this.resetForm("form");
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.optionId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加vote";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const optionId = row.optionId || this.ids
      getProjectcategoryreviewrulevoteoption(optionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改vote";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.optionId != undefined) {
            this.form.categoryCode = this.params.categoryCode
            this.form.phaseCode = this.params.phaseCode
            updateProjectcategoryreviewrulevoteoption(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            this.form.categoryCode = this.params.categoryCode
            this.form.phaseCode = this.params.phaseCode
            addProjectcategoryreviewrulevoteoption(this.form).then(response => {
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
      const optionIds = row.optionId || this.ids;
      this.$confirm('是否确认删除projectcategoryreviewrulevoteoption编号为"' + optionIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProjectcategoryreviewrulevoteoption(optionIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有projectcategoryreviewrulevoteoption数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectcategoryreviewrulevoteoption(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>