<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="68px"
    >
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select
          v-model="queryParams.categoryCode"
          placeholder="请选择项目类别"
          size="small"
          @change="handleQuery"
        >
          <el-option
            v-for="category in categorys"
            :key="category.categoryCode"
            :label="category.categoryCode + '-' + category.categoryName"
            :value="category.categoryCode"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pc:projectcategoryadmins:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pc:projectcategoryadmins:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pc:projectcategoryadmins:export']"
          >导出</el-button
        >
      </el-col>
    </el-row>
    <el-table
      v-loading="loading"
      :data="projectcategoryadminsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="管理员代码" align="center" prop="adminuserCode" /> -->
      <el-table-column label="管理员姓名" align="center" prop="adminuserName" />
      <el-table-column label="类别代号" align="center" prop="categoryCode" />
      <el-table-column label="类别名称" align="center" prop="categoryName" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pc:projectcategoryadmins:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改项目类别管理员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="类别代号" prop="categoryCode">
          <el-select
            v-model="form.categoryCode"
            placeholder="请选择项目类别"
            disabled
          >
            <el-option
              v-for="category in categorys"
              :key="category.categoryCode"
              :label="category.categoryCode + '-' + category.categoryName"
              :value="category.categoryCode"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择管理员" prop="adminuserCode">
          <el-select
            v-model="form.adminuserCode"
            multiple
            filterable
            placeholder="请选择管理员"
          >
            <el-option
              v-for="user in users"
              :key="user.userId"
              :label="user.userName"
              :value="user.userId"
            ></el-option>
          </el-select>
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
import {
  listProjectcategoryadmins,
  getProjectcategoryadmins,
  delProjectcategoryadmins,
  addProjectcategoryadmins,
  updateProjectcategoryadmins,
  exportProjectcategoryadmins,
} from "@/api/pc/projectcategoryadmins";
import { listProjectcategory } from "@/api/pc/projectcategory";
import { listUser, getRootUser } from "@/api/system/user";
export default {
  // name: "Projectcategoryadmins1",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      adcodes: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 项目类别管理员表格数据
      projectcategoryadminsList: [],
      categorys: [],
      users: [],
      usernames: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryCode: undefined,
        adminuserCode: undefined,
        adminuserName: undefined,
      },
      value: "",
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryCode: [
          { required: true, message: "项目类别不能为空", trigger: "blur" },
        ],
        adminuserCode: [
          { required: true, message: "管理员不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getCategorys();
    this.getUsers();
  },
  methods: {
    getCategoryName(code) {
      for (let i = 0; i < this.categorys.length; i++) {
        if (this.queryParams.categoryCode == this.categorys[i].categoryCode) {
          this.form.categoryName = this.categorys[i].categoryName;
        }
      }
    },
    /** 查询项目类别管理员列表 */
    getList() {
      this.loading = true;
      listProjectcategoryadmins(this.queryParams).then((response) => {
        this.projectcategoryadminsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //得到所有项目类别
    getCategorys() {
      let tmp = {
        enabled: 1
      };
      this.loading = true;
      listProjectcategory(tmp).then(res => {
        this.categorys = res.rows;
        if(res.rows.length){
          this.queryParams.categoryCode = this.categorys[0].categoryCode;
          this.getList();
        }   
        this.loading = false;       
      });

    },
    //得到所有用户
    getUsers() {
      listUser().then((res) => {
        console.log(res.rows);
        this.users = res.rows;
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
        categoryCode: this.queryParams.categoryCode,
        adminuserCode: [],
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
      this.ids = selection.map((item) => item.categoryCode);
      this.adcodes = selection.map((item) => item.adminuserCode);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目类别管理员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const categoryCode = row.categoryCode || this.ids;
      getProjectcategoryadmins(categoryCode).then((response) => {
        this.form.categoryCode = response.data.categoryCode;
        this.open = true;
        this.title = "修改项目类别管理员";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.getCategoryName(this.form.categoryCode);
          addProjectcategoryadmins(this.form).then((response) => {
            if (response.code === 200) {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      // console.log(this.ids);
      // console.log(this.adcodes);
      const categoryCodes = this.queryParams.categoryCode;
      const adminUserCodes = row.adminuserCode || this.adcodes;
      this.$confirm("是否确认删除?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delProjectcategoryadmins(categoryCodes, adminUserCodes);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有项目类别管理员数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportProjectcategoryadmins(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  },
};
</script>