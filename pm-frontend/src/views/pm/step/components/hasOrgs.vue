<template>
  <div class="baseinfo">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDept">添加内部单位</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDeptEx">添加外部单位</el-button>
      </el-col>
    </el-row>

    <el-table :data="projectorgList">
      <el-table-column label="单位编码" align="center" prop="orgCode" />
      <el-table-column label="单位名称" align="center" prop="orgName" />
      <el-table-column label="单位联系人" align="center" prop="orgAdminname" />
      <el-table-column label="单位电话" align="center" prop="orgTel" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateDept(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDeleteDept(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加外部合作单位对话框 -->
   <el-dialog
      :title="title"
      :visible.sync="openDeptEx"
      width="800px"
      height="1000px"
      append-to-body
    >
      <el-form ref="form_dept" :model="form_dept" label-width="80px">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="名称" prop="orgName">
              <el-input
                v-model="form_dept.orgName"
                placeholder="请输入单位名"
                :disabled="form_dept.orgCode != null"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="联系人" prop="orgAdminUser">
              <el-input
                v-model="form_dept.orgAdminname"
                placeholder="请输入单位联系人"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="orgTel">
              <el-input
                v-model="form_dept.orgTel"
                placeholder="请输入单位联系电话"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm_dept">确 定</el-button>
        <el-button @click="cancelDept">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加合作单位对话框 -->
    <el-dialog :title="title" :visible.sync="openDept" width="800px" height="1000px" append-to-body>
      <div class="app-container">
        <el-form :inline="true">
          <el-form-item label="部门名称">
            <el-input
              v-model="queryParams.deptName"
              placeholder="请输入部门名称"
              clearable
              size="small"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              class="filter-item"
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
            >搜索</el-button>
          </el-form-item>
        </el-form>

        <el-table
          :data="deptList"
          row-key="deptId"
          default-expand-all
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
          @selection-change="handleSelectionChangeOrg"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column prop="deptName" label="部门名称" width="260"></el-table-column>
          <el-table-column prop="orderNum" label="排序" width="200"></el-table-column>
          <el-table-column prop="status" label="状态" :formatter="statusFormat" width="100"></el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" width="200">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
        </el-table>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitDeptIn">确 定</el-button>
          <el-button @click="cancelDeptIn">取 消</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  listProjectorg,
  updateProjectorg,
  addProjectorg,
  getProjectorg,
  delProjectorg
} from "@/api/pm/step.js";
import { listDept } from "@/api/system/dept";

export default {
  data() {
    return {
      openDept: false,
      openDeptEx: false,
      projectorgList: [],
      form_dept: {},
      deptList: [],
      ids_dept: [],
      params: this.initParams,
      title: "",
      loading: false,
      statusOptions: [],
      //查询参数
      queryParams: {
        deptName: undefined,
        status: undefined,
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      }
    };
  },
  props: {
    initParams: {
      type: Object
    }
  },
  mounted() {
    this.getProjectOrg();
    this.getDicts("sys_normal_disable").then((response) => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    getProjectOrg() {
      listProjectorg(this.params.projectId,this.params.projectStatus).then(response => {
        this.projectorgList = response.rows;
      });
    },
    handleAddDept() {
      this.getDept();
      this.openDept = true;
      this.title = "添加合作单位";
    },
    handleUpdateDept(row) {
      this.resetForm_dept();
      const rid = row.rid;
      getProjectorg(rid).then(response => {
        this.form_dept = response.data;
        this.openDeptEx = true;
        this.title = "修改合作单位";
      });
    },
    /** 删除按钮操作 */
    handleDeleteDept(row) {
      const rids = row.rid;
      this.$confirm(
        '是否确认删除添加合作单位编号为"' + rids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delProjectorg(rids);
        })
        .then(() => {
          this.getProjectOrg();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    handleAddDeptEx() {
      this.resetForm_dept();
      this.openDeptEx = true;
      this.title = "添加外部单位";
    },
    resetForm_dept() {
      this.form_dept = {
        rid: undefined,
        projectId: this.params.projectId,
        categoryCode: this.params.categoryCode,
        phaseCode: this.params.projectStatus,
        orgCode: undefined,
        orgName: undefined,
        orgAdminname: undefined,
        orgTel: undefined
      };
      this.resetForm("form_dept");
    },
    submitForm_dept: function() {
      if (this.title == "修改合作单位") {
        updateProjectorg(this.form_dept).then(response => {
          if (response.code === 200) {
            this.msgSuccess("修改成功");
            this.openDeptEx = false;
            this.getProjectOrg();
          }
        });
      } else {
        addProjectorg(this.form_dept).then(response => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.openDeptEx = false;
            this.getProjectOrg();
          }
        });
      }
    },
    cancelDept() {
      this.openDeptEx = false;
      this.resetForm_dept();
    },
    handleSelectionChangeOrg(selection) {
      this.ids_dept = selection;
    },
    cancelDeptIn() {
      this.openDept = false;
    },
    submitDeptIn() {
      const deptIds = this.ids_dept;
      for (let i = 0; i < deptIds.length; i++) {
        this.form_dept = {
          rid: undefined,
          projectId: this.params.projectId,
          categoryCode: this.params.categoryCode,
          phaseCode: this.params.projectStatus,
          orgCode: deptIds[i].deptId,
          orgName: deptIds[i].deptName,
          orgAdminname: undefined,
          orgTel: undefined
        };
        addProjectorg(this.form_dept).then(response => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.getProjectOrg();
          }
        });
        this.resetForm_dept();
        this.getProjectOrg();
      }
      this.openDept = false;
      this.getProjectOrg();
    },
    /** 查询部门列表 */
    getDept() {
      listDept().then(response => {
        console.log(response);
        this.deptList = this.handleTree(response.data, "deptId");
        console.log(this.deptList);
      });
    },
    handleQuery() {
      this.getDept();
    },
    // 字典状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    }
  }
};
</script>