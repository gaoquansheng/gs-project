<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select @change="handleQuery" v-model="queryParams.categoryCode" placeholder="请选择项目类别"  size="small">
          <el-option v-for="category in categoryList" 
            :key="category.categoryCode"
            :label="category.categoryCode+'-'+category.categoryName"
            :value="category.categoryCode"
            >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <!-- <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索</el-button> -->
        <el-button
          class="filter-item"
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pc:projectcategorybudgetsubject:add']"
        >新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="projectcategorybudgetsubjectList"
      row-key="subjectId"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="科目名称" align="center" prop="subjectName" />
      
      <el-table-column label="是否启用" align="center" prop="enabled" :formatter="enabledFormat" />
      <!-- <el-table-column label="父科目类别" align="center" prop="parentSubjectId" /> -->
      <el-table-column label="类别填报说明" align="center" prop="subjectDesc" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pc:projectcategorybudgetsubject:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pc:projectcategorybudgetsubject:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
      <el-table-column label="类别代号" align="center" prop="categoryCode" />
    </el-table>

    <!-- 添加或修改项目类别预算对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="科目名称" prop="subjectName">
          <el-input v-model="form.subjectName" placeholder="请输入科目名称" />
        </el-form-item>
        <!-- <el-form-item label="项目类别">
          <el-select v-model="form.categoryCode" placeholder="请选择项目类别">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item> -->
        <el-form-item label="是否启用" prop="enabled">
          <el-select v-model="form.enabled" placeholder="请选择是否启用">
            <el-option
              v-for="dict in enabledOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="父科目类别" prop="parentSubjectId">
          <treeselect  v-model="form.parentSubjectId" :normalizer="normalizer" :options="projectcategorybudgetsubjectOptions" :default-expand-level="Infinity"/>
        </el-form-item>
        <el-form-item label="类别填报说明" prop="subjectDesc">
          <el-input v-model="form.subjectDesc" type="textarea" placeholder="请输入内容" />
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
import { listProjectcategorybudgetsubject, getProjectcategorybudgetsubject, delProjectcategorybudgetsubject, addProjectcategorybudgetsubject, updateProjectcategorybudgetsubject, exportProjectcategorybudgetsubject } from "@/api/pc/projectcategorybudgetsubject";
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { getUserProfile } from "@/api/system/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  // name: "Projectcategorybudgetsubject",
  components: { Treeselect },
  data() {
    return {
      categoryList: [],
      // 遮罩层
      loading: true,
      // 项目类别预算表格数据
      projectcategorybudgetsubjectList: [],
      // 项目类别预算树选项
      projectcategorybudgetsubjectOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否启用字典
      enabledOptions: [],
      // 查询参数
      queryParams: {
        categoryCode: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        subjectName: [
          { required: true, message: "科目名称不能为空", trigger: "blur" }
        ],
        categoryCode: [
          { required: true, message: "项目类别不能为空", trigger: "blur" }
        ],
        enabled: [
          { required: true, message: "是否启用不能为空", trigger: "blur" }
        ],
        parentSubjectId: [
          { required: true, message: "父科目类别不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getCategoryList();
    this.getDicts("sys_yes_no").then(response => {
      this.enabledOptions = response.data;
    });
  },
  methods: {
    getCategoryList(){
      //这里首先封装一下用户名把,使用vuex中的数据
      this.loading = true;
      listByAdminUserName().then(res => {  
        this.loading = false;   
        this.categoryList = res.rows;
        //加载项目类别的时候默认选中第一个
        if(this.categoryList.length){
          this.queryParams.categoryCode = this.categoryList[0].categoryCode;
          this.getList();
        }
      })


    },
    /** 查询项目类别预算列表 */
    getList() {
      this.loading = true;
      listProjectcategorybudgetsubject(this.queryParams).then(response => {
        this.projectcategorybudgetsubjectList = this.handleTree(response.data, "subjectId", "parentSubjectId","children",-1);
        this.loading = false;
      });
    },
    /** 转换项目类别预算数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.subjectId,
        label: node.subjectName,
        children: node.children
      };
    },
	/** 查询部门下拉树结构 */
    getTreeselect(sId) {
      //这里是新增时候的下拉框
      if(sId == undefined){
              //这里我想通过这个subjectId来控制不显示本身和本身的子类
        listProjectcategorybudgetsubject(this.queryParams).then(response => {
        this.projectcategorybudgetsubjectOptions = [];
        const data = { subjectId: -1, subjectName: '顶级节点', children: [] };   
        data.children = this.handleTree(response.data, "subjectId", "parentSubjectId","children",-1);;
        this.projectcategorybudgetsubjectOptions.push(data);
      });
      }else{
        //这里是修改选项的下拉框，通过给定的subjectId来取消显示其子节点
        listProjectcategorybudgetsubject(this.queryParams).then(response => {
        this.projectcategorybudgetsubjectOptions = [];
        const data = { subjectId: -1, subjectName: '顶级节点', children: [] };  
        let res = response.data.filter(item => {
          return item.subjectId != sId;
        }) 
        //判断返回是否有树形结构
        data.children = this.handleTree(res, "subjectId", "parentSubjectId","children",-1) == res? "":this.handleTree(res, "subjectId", "parentSubjectId","children",-1);
        this.projectcategorybudgetsubjectOptions.push(data);
        });
      }
    },
    // 是否启用字典翻译
    enabledFormat(row, column) {
      return this.selectDictLabel(this.enabledOptions, row.enabled);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        // subjectId: undefined,
        subjectName: undefined,
        categoryCode: this.queryParams.categoryCode,
        enabled: 1,
        parentSubjectId: undefined,
        subjectDesc: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect(); 
      this.open = true;
      this.title = "添加项目类别预算";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
	    this.getTreeselect(row.subjectId);
      if (row != undefined) {
        this.form.parentsubjectid = row.subjectid;
      }
      getProjectcategorybudgetsubject(row.subjectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目类别预算";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.subjectId != undefined) {
            //这里是否可以给parentSubjectLst赋值
            updateProjectcategorybudgetsubject(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addProjectcategorybudgetsubject(this.form).then(response => {
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
      this.$confirm('是否确认删除项目类别预算编号为"' + row.subjectId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProjectcategorybudgetsubject(row.subjectId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>