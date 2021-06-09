<template>
  <!-- 项目组成员 -->
  <div class="baseinfo">
    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >添加已注册成员</el-button
        >
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAddUser"
          >添加项目负责人</el-button
        >
      </el-col>
    </el-row>
    <el-table :data="projectmeberList">
      <el-table-column label="成员代码" align="center" prop="userCode" />
      <el-table-column label="成员姓名" align="center" prop="userName" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column
        label="性别"
        align="center"
        prop="sex"
        :formatter="sexFormat"
      />
      <el-table-column label="学历" align="center" prop="education" />
      <el-table-column label="职称" align="center" prop="title" />
      <el-table-column label="专业" align="center" prop="major" />
      <el-table-column label="工作时间" align="center" prop="workmonth" />
      <el-table-column label="任务分工" align="center" prop="task" />
      <el-table-column label="所在单位代码" align="center" prop="orgCode" />
      <el-table-column label="所在单位名称" align="center" prop="orgName" />
      <el-table-column
        label="是否为项目负责人"
        align="center"
        prop="isLeader"
        :formatter="isLeaderFormat"
      />
      <el-table-column label="人员排序" align="center" prop="orderNum" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateMember(scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDeleteMember(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加用户对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="800px"
      height="2000px"
      append-to-body
    >
      <div class="app-container">
        <el-row :gutter="20">
          <!--用户数据-->
          <el-col :span="30" :xs="24">
            <el-form :inline="true" @submit.native.prevent>
              <el-form-item label="用户名称" prop="userName">
                <el-input
                  v-model="queryParams.userName"
                  placeholder="请输入用户名称"
                  clearable
                  size="small"
                  style="width: 140px"
                  @keyup.enter.native="handleQueryUser"
                />
                <el-input type="hidden" />
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  icon="el-icon-search"
                  size="mini"
                  @click="handleQueryUser"
                  >搜索</el-button
                >
              </el-form-item>
            </el-form>
            <el-table
              :data="userList"
              @selection-change="handleSelectionChangeMember"
            >
              <el-table-column type="selection" width="50" align="center" />
              <el-table-column label="用户编号" align="center" prop="userId" />
              <el-table-column
                label="用户名称"
                align="center"
                prop="userName"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                label="用户昵称"
                align="center"
                prop="nickName"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                label="部门"
                align="center"
                prop="dept.deptName"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                label="手机号码"
                align="center"
                prop="phonenumber"
                width="120"
              />
              <el-table-column
                label="创建时间"
                align="center"
                prop="createTime"
                width="160"
              >
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.createTime) }}</span>
                </template>
              </el-table-column>
            </el-table>
            <div class="dialog-footer">
              <el-button type="primary" @click="submitForm_user"
                >确 定</el-button
              >
              <el-button @click="cancelForm_user">取 消</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-dialog>

    <!-- 添加未注册用户对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openUser"
      width="800px"
      height="1000px"
      append-to-body
    >
      <el-form ref="form_member" :model="form_member" label-width="80px">
        <el-form-item label="成员姓名" prop="userName">
          <el-input
            :disabled="form_member.userCode != null"
            v-model="form_member.userName"
            placeholder="请输入成员姓名"
          />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input
            v-model="form_member.age"
            placeholder="请输入年龄"
            :disabled="form_member.userCode != null"
          />
        </el-form-item>                   
        <el-form-item label="所在单位" prop="orgCode">
          <el-input
            v-model="form_member.orgCode"
            placeholder="所在单位代码"
            :disabled="form_member.userCode != null"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm_member">确 定</el-button>
        <el-button @click="cancelForm_member">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  getProjectmeber,
  delProjectmeber,
  updateProjectmeber,
  addProjectmeber,
  listProjectmeber,
} from "@/api/pm/step.js";
import { listUser } from "@/api/system/user";
export default {
  data() {
    return {
      educationOptions: [
        {
          value: "本科",
          label: "本科",
        },
        {
          value: "研究生",
          label: "研究生",
        },
        {
          value: "博士",
          label: "博士",
        },
        {
          value: "博士后",
          label: "博士后",
        },
      ],
      open: false,
      title: "",
      openUser: false,
      projectmeberList: [],
      params: this.initParams,
      form_member: {},
      queryParams: {
        deptName: undefined,
        status: undefined,
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined,
      },
      //用户列表
      userList: [],
      // 性别字典
      sexOptions: [],
      // 是否为项目负责人字典
      isLeaderOptions: [],
    };
  },
  props: {
    initParams: {
      type: Object,
    },
  },
  mounted() {
    this.getDicts("sys_user_sex").then((response) => {
      this.sexOptions = response.data;
    });
    this.getDicts("sys_yes_no").then((response) => {
      this.isLeaderOptions = response.data;
    });
    this.getProjectMember();
  },
  methods: {
    getProjectMember() {
      listProjectmeber(this.params.projectId, this.params.projectStatus).then(
        (response) => {
          this.projectmeberList = response.rows;
        }
      );
    },
    submitForm_user() {
      const userIds = this.ids_member;
      for (let i = 0; i < userIds.length; i++) {
        this.form_member = {
          rid: undefined,
          projectId: this.params.projectId,
          categoryCode: this.params.categoryCode,
          phaseCode: this.params.projectStatus,
          userCode: userIds[i].userId,
          userName: userIds[i].userName,
          age: undefined,
          sex: userIds[i].sex,
          education: undefined,
          title: undefined,
          major: undefined,
          workmonth: undefined,
          task: undefined,
          orgCode: userIds[i].dept.deptId,
          orgName: userIds[i].dept.deptName,
          isLeader: 0,
          orderNum: undefined,
        };
        addProjectmeber(this.form_member).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.getProjectMember();
          }
        });
        this.open = false;
        this.getProjectMember();
      }
      this.getProjectMember();
    },
    handleAdd() {
      this.open = true;
      this.title = "添加项目组成员";
      this.getUsers();
    },
    handleAddUser() {
      this.resetForm_member();
      this.openUser = true;
      this.title = "添加未注册用户";
    },
    resetForm_member() {
      this.form_member = {
        rid: undefined,
        projectId: this.params.projectId,
        categoryCode: this.params.categoryCode,
        phaseCode: this.params.projectStatus,
        userCode: undefined,
        userName: undefined,
        age: undefined,
        sex: "0",
        education: undefined,
        title: undefined,
        major: undefined,
        workmonth: undefined,
        task: undefined,
        orgCode: undefined,
        orgName: undefined,
        isLeader: 0,
        orderNum: undefined,
      };
      this.resetForm("form_member");
    },
    handleUpdateMember(row) {
      this.resetForm_member();
      const rid = row.rid;
      getProjectmeber(rid).then((response) => {
        this.form_member = response.data;
        this.openUser = true;
        this.title = "修改项目组成员";
      });
    },
    /** 删除按钮操作 */
    handleDeleteMember(row) {
      const rids = row.rid;
      this.$confirm(
        '是否确认删除项目组成员编号为"' + rids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delProjectmeber(rids);
        })
        .then(() => {
          this.getProjectMember();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    // 性别字典翻译
    sexFormat(row, column) {
      return this.selectDictLabel(this.sexOptions, row.sex);
    },
    // 是否为项目负责人字典翻译
    isLeaderFormat(row, column) {
      return this.selectDictLabel(this.isLeaderOptions, row.isLeader);
    },
    /** 搜索按钮操作 */
    handleQueryUser() {
      this.getUsers();
    },
    getUsers() {
      this.loading = true;
      listUser(this.queryParams).then((res) => {
        this.userList = res.rows;
        this.loading = false;
      });
    },
    handleSelectionChangeMember(selection) {
      this.ids_member = selection;
    },
    cancelForm_user() {
      this.open = false;
    },
    submitForm_member: function () {
      if (this.title === "修改项目组成员") {
        updateProjectmeber(this.form_member).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("修改成功");
            this.openUser = false;
            this.getProjectMember();
          }
        });
      } else {
        addProjectmeber(this.form_member).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.openUser = false;
            this.getProjectMember();
          }
        });
      }
    },
    cancelForm_member() {
      this.openUser = false;
      this.resetForm_member();
    },
  },
};
</script>