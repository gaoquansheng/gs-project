<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select @change="getprojectCategoryphases" v-model="queryParams.categoryCode" placeholder="请选择项目类别"  size="small">
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
          v-hasPermi="['pc:projectcategoryphasewordbookmark:add']"
          :disabled="!queryParams.categoryCode || !queryParams.phaseCode"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pc:projectcategoryphasewordbookmark:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pc:projectcategoryphasewordbookmark:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pc:projectcategoryphasewordbookmark:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="projectcategoryphasewordbookmarkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类别流程" align="center" prop="phaseName" />
      <el-table-column label="书签英文" align="center" prop="bookMarkCode" />
      <el-table-column label="书签中文" align="center" prop="bookMarkName" />
      <el-table-column label="书签填报说明" align="center" prop="bookMarkDesc" />
      <el-table-column label="内容类型" align="center" prop="contentType" :formatter="contentTypeFormat" />
      <el-table-column label="是否有效" align="center" prop="isEnable" :formatter="isEnableFormat" />
      <!-- <el-table-column label="排列顺序" align="center" prop="orderSeq" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pc:projectcategoryphasewordbookmark:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pc:projectcategoryphasewordbookmark:remove']"
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

    <!-- 添加或修改Word模板设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
         <el-form-item label="书签英文" prop="bookMarkCode">
          <el-input :disabled="title=='修改Word模板设置'" v-model="form.bookMarkCode" placeholder="请输入书签英文" />
        </el-form-item>
        <el-form-item label="书签中文" prop="bookMarkName">
          <el-input v-model="form.bookMarkName" placeholder="请输入书签中文" />
        </el-form-item>
        <el-form-item label="填报说明" prop="bookMarkDesc">
          <el-input v-model="form.bookMarkDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="内容类型" prop="contentType">
          <el-select v-model="form.contentType" placeholder="请选择内容类型">
            <el-option
              v-for="dict in contentTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有效" prop="isEnable">
          <el-select v-model="form.isEnable" placeholder="请选择是否有效">
            <el-option
              v-for="dict in isEnableOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排列顺序" prop="orderSeq">
          <el-input v-model="form.orderSeq" placeholder="请输入排列顺序" />
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
import { listProjectcategoryphasewordbookmark, getProjectcategoryphasewordbookmark, delProjectcategoryphasewordbookmark, addProjectcategoryphasewordbookmark, updateProjectcategoryphasewordbookmark, exportProjectcategoryphasewordbookmark } from "@/api/pc/projectcategoryphasewordbookmark";
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { listProjectcategoryphase } from "@/api/pc/projectcategoryphase";
export default {
  // name: "Projectcategoryphasewordbookmark",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      selectedProject: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // Word模板设置表格数据
      projectcategoryphasewordbookmarkList: [],
      categorys: [],
      categoryphases: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 内容类型字典
      contentTypeOptions: [],
      // 是否有效字典
      isEnableOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryCode: undefined,
        phaseCode: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookMarkCode: [
          { required: true, message: "书签英文不能为空", trigger: "blur" }
        ],
        bookMarkName: [
          { required: true, message: "书签中文不能为空", trigger: "blur" }
        ],
        bookMarkDesc: [
          { required: true, message: "书签填报说明不能为空", trigger: "blur" }
        ],
        contentType: [
          { required: true, message: "内容类型不能为空", trigger: "blur" }
        ],
        isEnable: [
          { required: true, message: "是否有效不能为空", trigger: "blur" }
        ],
        orderSeq: [
          { required: true, message: "排列顺序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getCategorys();
    this.getDicts("sys_bookmark_type").then(response => {
      this.contentTypeOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.isEnableOptions = response.data;
    });
  },
  methods: {
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
      })
    },
    //得到项目流程名称
    getprojectCategoryphases() {
      this.categoryphases = [];
      let tmp = {
        categoryCode: this.queryParams.categoryCode,
        enabled: 1,
        needWord: 1
      };
      listProjectcategoryphase(tmp).then(res => {
        if(res.total >0){
          this.categoryphases = res.rows;
          this.queryParams.phaseCode = res.rows[0].phaseCode;
        }else {
          this.queryParams.phaseCode = undefined
        }
        this.handleQuery();
      })
    },
    /** 查询Word模板设置列表 */
    getList() {
      this.loading = true;
      listProjectcategoryphasewordbookmark(this.queryParams).then(response => {
        this.projectcategoryphasewordbookmarkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 内容类型字典翻译
    contentTypeFormat(row, column) {
      return this.selectDictLabel(this.contentTypeOptions, row.contentType);
    },
    // 是否有效字典翻译
    isEnableFormat(row, column) {
      return this.selectDictLabel(this.isEnableOptions, row.isEnable);
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
        phaseCode: this.queryParams.phaseCode,
        bookMarkCode: undefined,
        bookMarkName: undefined,
        bookMarkDesc: undefined,
        contentType: undefined,
        isEnable: undefined,
        orderSeq: undefined,
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
      // console.log(selection);
      this.selectedProject = selection;
      // this.ids = selection.map(item => item.categoryCode+","+item.phaseCode+","+item.bookmarkCode)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加Word模板设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      let categoryCode,phaseCode,bookMarkCode;
      if(this.selectedProject.length>0){
        categoryCode = this.selectedProject[0].categoryCode;
        phaseCode = this.selectedProject[0].phaseCode;
        bookMarkCode = this.selectedProject[0].bookMarkCode;
      }else {
        categoryCode = row.categoryCode;
        phaseCode = row.phaseCode;
        bookMarkCode = row.bookMarkCode;
      } 
      getProjectcategoryphasewordbookmark(categoryCode,phaseCode,bookMarkCode).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改Word模板设置";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.title == "修改Word模板设置") {
            updateProjectcategoryphasewordbookmark(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addProjectcategoryphasewordbookmark(this.form).then(response => {
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
      let data = row.categoryCode != undefined? [row]:this.selectedProject;
      // const data = row || this.selectedProject;
      this.$confirm('是否确认删除Word模板设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          console.log("成功");
          console.log(this.selectedProject);
         return delProjectcategoryphasewordbookmark(data);
        }).then(() => {
          console.log("失败");
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有Word模板设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProjectcategoryphasewordbookmark(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>