<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select
          @change="getprojectCategoryphases"
          v-model="queryParams.categoryCode"
          placeholder="请选择项目类别"
          size="small"
        >
          <el-option
            v-for="category in categorys"
            :key="category.categoryCode"
            :label="category.categoryCode + '-' + category.categoryName"
            :value="category.categoryCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类别流程" prop="phaseCode">
        <el-select v-model="queryParams.phaseCode" placeholder="请选择类别流程">
          <el-option
            v-for="phase in categoryphases"
            :key="phase.phaseCode"
            :label="phase.phaseName"
            :value="phase.phaseCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开放年度" prop="openYear">
        <el-input
          v-model="queryParams.openYear"
          placeholder="请输入开放年度"
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
          :disabled="queryParams.categoryCode == undefined || queryParams.phaseCode == undefined"
          v-hasPermi="['pc:opentime:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pc:opentime:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pc:opentime:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pc:opentime:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="opentimeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="年度" align="center" prop="openYear" />
      <el-table-column label="类别名称" align="center" prop="categoryName" width="180" />
      <el-table-column label="类别流程" align="center" prop="phaseName" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="项目类别" align="center" prop="categoryCode" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pc:opentime:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pc:opentime:remove']"
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

    <!-- 添加或修改项目起止时间对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="开放年度" prop="openYear">
          <el-input :disabled="title=='修改项目起止时间'" v-model="form.openYear" placeholder="请输入开放年度" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.startTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="选择日期时间"
            align="right"
            :picker-options="pickerOptions"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.endTime"
            value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime"
            placeholder="选择日期时间"
            align="right"
            :picker-options="pickerOptions"
          ></el-date-picker>
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
  listOpentime,
  getOpentime,
  delOpentime,
  addOpentime,
  updateOpentime,
  exportOpentime
} from "@/api/pc/opentime";
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { listProjectcategoryphase } from "@/api/pc/projectcategoryphase";
export default {
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
      // 项目起止时间表格数据
      opentimeList: [],
      categorys: [],
      categoryphases: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        openYear: undefined,
        categoryCode: undefined,
        phaseCode: undefined
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        openYear: [
          { required: true, message: "开放年度不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "结束时间不能为空", trigger: "blur" }
        ],
        phaseCode: [
          { required: true, message: "项目阶段不能为空", trigger: "blur" }
        ],
        categoryCode: [
          { required: true, message: "项目类别不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getCategorys();
  },
  methods: {
    /** 查询项目起止时间列表 */
    getList() {
      this.loading = true;
      listOpentime(this.queryParams).then(response => {
        this.opentimeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //得到所有项目类别
    getCategorys() {
      this.loading = true;
      listByAdminUserName().then(res => {
        this.loading = false;
        this.categorys = res.rows;
        if(this.categorys.length){
          this.queryParams.categoryCode = res.rows[0].categoryCode;
          this.getprojectCategoryphases();
        }
      });
    },
    //得到项目流程名称
    getprojectCategoryphases() {
      let tmp = {
        categoryCode: this.queryParams.categoryCode,
        enabled: 1
      };
      listProjectcategoryphase(tmp).then(res => {
        this.categoryphases = res.rows;
        if(this.categoryphases.length){
          this.queryParams.phaseCode = res.rows[0].phaseCode;
          this.getList();
        }
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
        rId: undefined,
        openYear: new Date().getFullYear(),
        startTime: undefined,
        endTime: undefined,
        phaseCode: this.queryParams.phaseCode,
        categoryCode: this.queryParams.categoryCode
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
      this.ids = selection.map(item => item.rId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目起止时间";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rId = row.rId || this.ids;
      getOpentime(rId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目起止时间";
      });
    },
    submitForm: function() {
      var data = new Date();
      var useryear = data.getFullYear();
      if (this.form.openYear > 2099) {
        this.msgError("输入年份错误");
        return;
      } else if (this.form.openYear < useryear) {
        this.msgError("开放年度不能低于" + useryear + "年");
        return;
      }

      if (this.form.startTime > this.form.endTime) {
        this.msgError("结束时间不能比开始时间晚");
        return;
      }

      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.rId != undefined) {
            updateOpentime(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addOpentime(this.form).then(response => {
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
      const rIds = row.rId || this.ids;
      this.$confirm(
        '是否确认删除项目起止时间编号为"' + rIds + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delOpentime(rIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有项目起止时间数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportOpentime(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>

