<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="类别代码" prop="categoryCode">
        <el-input
          v-model="queryParams.categoryCode"
          placeholder="请输入类别代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阶段代码" prop="phaseCode">
        <el-input
          v-model="queryParams.phaseCode"
          placeholder="请输入阶段代码"
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
          v-hasPermi="['re:projectcategoryreviewrulescoreoption:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['re:projectcategoryreviewrulescoreoption:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['re:projectcategoryreviewrulescoreoption:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['re:projectcategoryreviewrulescoreoption:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectcategoryreviewrulescoreoptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="阶段代码" align="center" prop="scoreId" /> -->
      <el-table-column label="类别代码" align="center" prop="categoryCode" />
      <el-table-column label="阶段代码" align="center" prop="phaseCode" />
      <el-table-column label="评分项" align="center" prop="scoreTitle" />
      <el-table-column label="最低分" align="center" prop="minScore" />
      <el-table-column label="最高分" align="center" prop="maxScore" />
      <el-table-column label="评分描述" align="center" prop="scroreDesc" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['re:projectcategoryreviewrulescoreoption:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['re:projectcategoryreviewrulescoreoption:remove']"
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

    <!-- 添加或修改评分设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评分项" prop="scoreTitle">
          <el-input v-model="form.scoreTitle" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="最低分" prop="minScore">
          <el-input v-model="form.minScore" placeholder="请输入最低分" />
        </el-form-item>
        <el-form-item label="最高分" prop="maxScore">
          <el-input v-model="form.maxScore" placeholder="请输入最高分" />
        </el-form-item>
        <el-form-item label="评分描述" prop="scroreDesc">
          <el-input v-model="form.scroreDesc" type="textarea" placeholder="请输入内容" />
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
import { listProjectcategoryreviewrulescoreoption, getProjectcategoryreviewrulescoreoption, delProjectcategoryreviewrulescoreoption, addProjectcategoryreviewrulescoreoption, updateProjectcategoryreviewrulescoreoption, exportProjectcategoryreviewrulescoreoption } from "@/api/re/projectcategoryreviewrulescoreoption";

export default {
  name: "Projectcategoryreviewrulescoreoption",
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
      // 评分设置表格数据
      projectcategoryreviewrulescoreoptionList: [],
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        scoreTitle: [
          { required: true, message: "评分项不能为空", trigger: "blur" }
        ],
        minScore: [
          { required: true, message: "最低分不能为空", trigger: "blur" }
        ],
        maxScore: [
          { required: true, message: "最高分不能为空", trigger: "blur" }
        ],
        scroreDesc: [
          { required: true, message: "评分描述不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.params = this.$route.query;
    this.getList();
  },
  methods: {
    /** 查询评分设置列表 */
    getList() {
      this.loading = true;
      listProjectcategoryreviewrulescoreoption(this.params.categoryCode,this.params.phaseCode).then(response => {
        this.projectcategoryreviewrulescoreoptionList = response.rows;
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
        scoreId: undefined,
        categoryCode: undefined,
        phaseCode: undefined,
        scoreTitle: undefined,
        minScore: undefined,
        maxScore: undefined,
        scroreDesc: undefined
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
      this.ids = selection.map(item => item.scoreId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评分设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scoreId = row.scoreId || this.ids
      getProjectcategoryreviewrulescoreoption(scoreId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评分设置";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scoreId != undefined) {
            this.form.categoryCode = this.params.categoryCode
            this.form.phaseCode = this.params.phaseCode
            updateProjectcategoryreviewrulescoreoption(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            this.form.categoryCode = this.params.categoryCode
            this.form.phaseCode = this.params.phaseCode
            addProjectcategoryreviewrulescoreoption(this.form).then(response => {
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
      const scoreIds = row.scoreId || this.ids;
      this.$confirm('是否确认删除评分设置编号为"' + scoreIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProjectcategoryreviewrulescoreoption(scoreIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有评分设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectcategoryreviewrulescoreoption(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>