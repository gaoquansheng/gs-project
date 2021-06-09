<template>
  <div>
    <el-form label-width="115px" ref="form" :model="form" :rules="rules">
      <el-row>
        <el-col :span="10">
          <el-form-item label="项目名称" prop="projectName">
            <el-input
              v-model="form.projectName"
              placeholder="请输入项目名称"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="归口管理部门" prop="proxyOrgCode">
            <treeselect
              v-model="form.proxyOrgName"
              :options="deptOptions"
              :disable-branch-nodes="true"
              :show-count="true"
              :default-expand-level="Infinity"
              placeholder="请选择归属部门"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="所属领域" prop="projectArear">
            <el-select
              v-model="form.projectArear"
              placeholder="请选择项目优先级"
            >
              <el-option
                v-for="dict in projectarearOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="投资来源" prop="moneyFrom">
            <el-select v-model="form.moneyFrom" placeholder="请选择项目优先级">
              <el-option
                v-for="dict in moneyfromOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="项目优先级" prop="projectPriority">
            <el-select
              v-model="form.projectPriority"
              placeholder="请选择项目优先级"
            >
              <el-option
                v-for="dict in projectpriorityOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="10">
          <el-form-item label="开始时间" prop="startDate">
            <el-select v-model="form.startDate" placeholder="请选择">
              <el-option
                v-for="item in optionsStart"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="结束时间" prop="endDate">
            <el-select v-model="form.endDate" placeholder="请选择">
              <el-option
                v-for="item in optionsEnd"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="关键字" prop="keyWords">
            <el-input
              type="textarea"
              :rows="3"
              v-model="form.keyWords"
              placeholder="请输入关键字"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="20">
          <el-form-item label="建设内容描述" prop="projectSummary">
            <el-input
              type="textarea"
              :rows="4"
              v-model="form.projectSummary"
              placeholder="请输入建设内容描述"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import { treeselect } from "@/api/system/dept";
import {getProjecttask ,addProjecttask } from "@/api/pm/step.js";
import { getProjectcategory } from "@/api/pc/projectcategory";
export default {
  data() {
    return {
      optionsStart: [],
      optionsEnd: [],
      form: {},
      rules: {
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" },
        ],
        proxyOrgName: [
          { required: true, message: "归口管理部门不能为空", trigger: "blur" },
        ],
        myOrgName: [
          {
            required: true,
            message: "申请人所属部门不能为空",
            trigger: "blur",
          },
        ],
        projectArear: [
          { required: true, message: "所属领域不能为空", trigger: "blur" },
        ],
        moneyFrom: [
          { required: true, message: "投资来源不能为空", trigger: "blur" },
        ],
        projectPriority: [
          { required: true, message: "项目优先级不能为空", trigger: "blur" },
        ],
        startDate: [
          { required: true, message: "开始日期不能为空", trigger: "blur" },
        ],
        endDate: [
          { required: true, message: "结束日期不能为空", trigger: "blur" },
        ],
        projectSummary: [
          { required: true, message: "建设内容描述不能为空", trigger: "blur" },
        ],
        keyWords: [
          { required: true, message: "关键字不能为空", trigger: "blur" },
        ],
      },
      deptOptions: undefined,
      projectarearOptions: [],
      moneyfromOptions: [],
      projectpriorityOptions: [],
      params: this.initParams,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
  },
  components: { Treeselect },
  mounted() {
    this.selectProjectcategory();
    this.getForm();
    this.getTreeselect();
    this.getDicts("project_arear").then((response) => {
      this.projectarearOptions = response.data;
    });
    this.getDicts("money_from").then((response) => {
      this.moneyfromOptions = response.data;
    });
    this.getDicts("project_priority").then((response) => {
      this.projectpriorityOptions = response.data;
    });
  },
  beforeDestroy() {
    this.submitForm();
  },
  methods: {
    submitForm() {
      this.form.categoryCode = this.params.categoryCode;
      this.form.projectYear = this.params.projectYear;
      this.form.projectStatus = "2001";
      if (this.params.projectId) {
        addProjecttask(this.form).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("添加成功");
          } else {
            this.msgError("添加失败");
          }
        });
      }
    },
    getForm() {
      // console.log(this.project);
      if (this.params.projectId) {
        getProjecttask(this.params.projectId).then((response) => {
          this.form = response.data;
        });
      }
    },
    getTreeselect() {
      treeselect().then((response) => {
        this.deptOptions = response.data;
      });
    },
    reset_form() {
      this.form = {
        projectId: this.params.projectId,
        categoryCode: undefined,
        projectCode: undefined,
        projectName: undefined,
        projectYear: undefined,
        userCode: undefined,
        userName: undefined,
        proxyOrgName: 201,
        proxyOrgCode: 201,
        myOrgName: undefined,
        myorgCode: undefined,
        projectArear: 0,
        projectPriority: 0,
        moneyFrom: 0,
        startDate: undefined,
        endDate: undefined,
        appTime: undefined,
        appAmount: undefined,
        myAmount: undefined,
        totalAmount: undefined,
        projectSummary: undefined,
        keyWords: undefined,
      };
      this.resetForm("form");
    },
    // 根据categoryCode查找项目开始和结束月日信息，以便拼接成项目开始时间和结束时间
    selectProjectcategory() {
      getProjectcategory(this.params.categoryCode).then((response) => {
        let pYear = this.params.projectYear;
        // console.log(response.data);
        this.projectCategory = response.data;
        for (let i = 0; i < 5; i++) {
          let yearStart = {
            value:
              pYear +
              "-" +
              this.projectCategory.startMonth +
              "-" +
              this.projectCategory.startDay,
            lable:
              pYear +
              "-" +
              this.projectCategory.startMonth +
              "-" +
              this.projectCategory.startDay,
          };
          let yearEnd = {
            value:
              pYear +
              "-" +
              this.projectCategory.endMonth +
              "-" +
              this.projectCategory.endDay,
            lable:
              pYear +
              "-" +
              this.projectCategory.startMonth +
              "-" +
              this.projectCategory.startDay,
          };
          this.optionsStart.push(yearStart);
          this.optionsEnd.push(yearEnd);
          pYear++;
        }
      });
    },
  },
};
</script>