<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="类别名称" prop="categoryName">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入类别名称"
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
          v-hasPermi="['pc:projectcategory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pc:projectcategory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pc:projectcategory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pc:projectcategory:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectcategoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类别代号" align="center" prop="categoryCode" />
      <el-table-column label="类别名称" align="center" prop="categoryName" />
      <el-table-column label="是否启用" align="center" prop="enabled" :formatter="enabledFormat" />
      <el-table-column label="开始月份" align="center" prop="startMonth" />
      <el-table-column label="开始日" align="center" prop="startDay" />
      <el-table-column label="结束月份" align="center" prop="endMonth" />
      <el-table-column label="结束日" align="center" prop="endDay" />
      <el-table-column label="经费预算" align="center" prop="hasBudgetPlan" :formatter="hasBudgetPlanFormat" />
      <el-table-column label="预算支出" align="center" prop="hasBudgetYearplan" :formatter="hasBudgetYearplanFormat" />
      <el-table-column label="预算说明" align="center" prop="hasBudgetDesc" :formatter="hasBudgetDescFormat" />
      <el-table-column label="自筹经费" align="center" prop="hasSelfFinancing" :formatter="hasSelfFinancingFormat" />
      <el-table-column label="合作单位" align="center" prop="hasOrgs" :formatter="hasOrgsFormat" />
      <el-table-column label="项目成员" align="center" prop="hasMembers" :formatter="hasMembersFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pc:projectcategory:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pc:projectcategory:remove']"
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

    <!-- 添加或修改项目类别对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别代号" prop="categoryCode">
          <el-input :disabled="title == '修改项目类别'" v-model="form.categoryCode" placeholder="请输入类别代号" />
        </el-form-item>
        <el-form-item label="类别名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-select v-model="form.enabled" placeholder="请选择是否启用">
            <el-option
              v-for="dict in enabledOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始月份" prop="startMonth">
          <el-input type="number"  v-model.number="form.startMonth" placeholder="请输入开始月份" />
        </el-form-item>
        <el-form-item label="开始日" prop="startDay">
          <el-input type="number" v-model.number="form.startDay" placeholder="请输入开始日" />
        </el-form-item>
        <el-form-item label="结束月份" prop="endMonth">
          <el-input type="number" v-model.number="form.endMonth" placeholder="请输入结束月份" />
        </el-form-item>
        <el-form-item label="结束日" prop="endDay">
          <el-input type="number" v-model.number="form.endDay" placeholder="请输入结束日" />
        </el-form-item>
        <el-form-item label="经费预算">
          <el-select v-model="form.hasBudgetPlan" placeholder="请选择经费预算">
            <el-option
              v-for="dict in hasBudgetPlanOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预算支出">
          <el-select v-model="form.hasBudgetYearplan" placeholder="请选择年度预算支出计划">
            <el-option
              v-for="dict in hasBudgetYearplanOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预算说明">
          <el-select v-model="form.hasBudgetDesc" placeholder="请选择预算编制说明">
            <el-option
              v-for="dict in hasBudgetDescOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="自筹经费">
          <el-select v-model="form.hasSelfFinancing" placeholder="请选择自筹经费">
            <el-option
              v-for="dict in hasSelfFinancingOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合作单位">
          <el-select v-model="form.hasOrgs" placeholder="请选择合作单位">
            <el-option
              v-for="dict in hasOrgsOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目成员">
          <el-select v-model="form.hasMembers" placeholder="请选择项目成员">
            <el-option
              v-for="dict in hasMembersOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
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
import { listProjectcategory, getProjectcategory, delProjectcategory, addProjectcategory, updateProjectcategory, exportProjectcategory } from "@/api/pc/projectcategory";

export default {
  // name: "Projectcategory",
  data() {
    let month31 = [1,3,5,7,8,10,12];
    let month30 = [4,6,9,11];
    let validateStartMonth = (rule,value,callback) => {
      if(!value){
        return callback(new Error("日期不能为空"))
      }
      if(Math.round(value) != value){
        return callback(new Error("请输入正确格式的日期"));
      }
      if(value >= 1 && value <= 12){
        if(value <= this.form.endMonth){
          return callback();
        }else{
          return callback(new Error("开始月份不能大于结束月份"))
        }
      }else{
        return callback(new Error("请输入正确格式的日期"));
      }
    };
    let validateStartDay = (rule,value,callback) => {
      if(!value){
        return callback(new Error("日期不能为空"))
      }
      if(Math.round(value) != value){
        return callback(new Error("请输入正确格式的日期"));
      }
      if(this.form.startMonth == this.form.endMonth){
        if(value >= this.form.endDay){
          return callback(new Error("开始日期不能大于等于结束日期"))
        }else{
          return callback();
        }
      }
      if(month31.indexOf(this.form.startMonth) != -1){
        if(value <1 || value >31){
          return callback(new Error("请输入1至31的日期"))
        }else{
          return callback();
        }
      }else if(month30.indexOf(this.form.startMonth) != -1){
        if(value <1 || value >30){
          return callback(new Error("请输入1至30的日期"))
        }else{
          return callback();
        }
      }else if(this.form.startMonth == 2){
        if(value <1 || value >28){
          return callback(new Error("请输入1至28的日期"))
        }else{
          return callback();
        }
      }
      callback();
    };
    let validateEndMonth = (rule,value,callback) => {
      if(!value){
        return callback(new Error("日期不能为空"))
      }
      if(Math.round(value) != value){
        return callback(new Error("请输入正确格式的日期"));
      }
      if(value >= 1 && value <= 12){
        return callback();       
      }else{
        return callback(new Error("请输入正确格式的日期"));
      }
    };
    let validateEndDay = (rule,value,callback) => {
      if(!value){
        return callback(new Error("日期不能为空"))
      }
      if(Math.round(value) != value){
        return callback(new Error("请输入正确格式的日期"));
      }
      if(month31.indexOf(this.form.endMonth) != -1){
        if(value <1 || value >31){
          return callback(new Error("请输入1至31的日期"))
        }else{
          return callback();
        }
      }else if(month30.indexOf(this.form.endMonth) != -1){
        if(value <1 || value >30){
          return callback(new Error("请输入1至30的日期"))
        }else{
          return callback();
        }
      }else if(this.form.endMonth == 2){
        if(value <1 || value >28){
          return callback(new Error("请输入1至28的日期"))
        }else{
          return callback();
        }
      }
    }
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
      // 项目类别表格数据
      projectcategoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否启用字典
      enabledOptions: [],
      // 经费预算字典
      hasBudgetPlanOptions: [],
      // 年度预算支出计划字典
      hasBudgetYearplanOptions: [],
      // 预算编制说明字典
      hasBudgetDescOptions: [],
      // 自筹经费字典
      hasSelfFinancingOptions: [],
      // 合作单位字典
      hasOrgsOptions: [],
      // 项目成员字典
      hasMembersOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryName: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryCode: [
          { required: true, message: "类别代号不能为空", trigger: "blur" }
        ],
        categoryName: [
          { required: true, message: "类别名称不能为空", trigger: "blur" }
        ],
        enabled: [
          { required: true, message: "是否启用不能为空", trigger: "blur" }
        ],
        startMonth: [
          { validator: validateStartMonth, trigger: "blur"}
        ],
        startDay: [
          { validator: validateStartDay, trigger: "blur" }
        ],
        endMonth: [
          { validator: validateEndMonth, trigger: "blur"}
        ],
        endDay: [
          { validator: validateEndDay, trigger: "blur"}
        ],
        hasBudgetPlan: [
          { required: true, message: "经费预算不能为空", trigger: "blur" }
        ],
        hasBudgetYearplan: [
          { required: true, message: "年度预算支出计划不能为空", trigger: "blur" }
        ],
        hasBudgetDesc: [
          { required: true, message: "预算编制说明不能为空", trigger: "blur" }
        ],
        hasSelfFinancing: [
          { required: true, message: "自筹经费不能为空", trigger: "blur" }
        ],
        hasOrgs: [
          { required: true, message: "合作单位不能为空", trigger: "blur" }
        ],
        hasMembers: [
          { required: true, message: "项目成员不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.enabledOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasBudgetPlanOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasBudgetYearplanOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasBudgetDescOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasSelfFinancingOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasOrgsOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.hasMembersOptions = response.data;
    });
  },
  methods: {
    /** 查询项目类别列表 */
    getList() {
      this.loading = true;
      listProjectcategory(this.queryParams).then(response => {    
        this.projectcategoryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否启用字典翻译
    enabledFormat(row, column) {
      return this.selectDictLabel(this.enabledOptions, row.enabled);
    },
    // 经费预算字典翻译
    hasBudgetPlanFormat(row, column) {
      return this.selectDictLabel(this.hasBudgetPlanOptions, row.hasBudgetPlan);
    },
    // 年度预算支出计划字典翻译
    hasBudgetYearplanFormat(row, column) {
      return this.selectDictLabel(this.hasBudgetYearplanOptions, row.hasBudgetYearplan);
    },
    // 预算编制说明字典翻译
    hasBudgetDescFormat(row, column) {
      return this.selectDictLabel(this.hasBudgetDescOptions, row.hasBudgetDesc);
    },
    // 自筹经费字典翻译
    hasSelfFinancingFormat(row, column) {
      return this.selectDictLabel(this.hasSelfFinancingOptions, row.hasSelfFinancing);
    },
    // 合作单位字典翻译
    hasOrgsFormat(row, column) {
      return this.selectDictLabel(this.hasOrgsOptions, row.hasOrgs);
    },
    // 项目成员字典翻译
    hasMembersFormat(row, column) {
      return this.selectDictLabel(this.hasMembersOptions, row.hasMembers);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        categoryCode: undefined,
        categoryName: undefined,
        //设置为1为默认选中
        enabled: 1,
        startMonth: 1,
        startDay: 1,
        endMonth: 12,
        endDay: 31,
        hasBudgetPlan: 1,
        hasBudgetYearplan: 1,
        hasBudgetDesc: 1,
        hasSelfFinancing: 1,
        hasOrgs: 1,
        hasMembers: 1
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
      this.ids = selection.map(item => item.categoryCode)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目类别";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const categoryCode = row.categoryCode || this.ids
      getProjectcategory(categoryCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目类别";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          console.log(this.form);
          if(this.title == "修改项目类别"){
            updateProjectcategory(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          }else{
            addProjectcategory(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }else {
                this.$message.error("添加失败");
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const categoryCodes = row.categoryCode || this.ids;
      this.$confirm('是否确认删除项目类别编号为"' + categoryCodes + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProjectcategory(categoryCodes);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有项目类别数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectcategory(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
