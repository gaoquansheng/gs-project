<template>
  <div>
    <div class="baseinfo">
      <el-form label-width="115px" ref="form_report" :model="form_report">
        <el-row>
          <el-col :span="6">
            <el-form-item label="项目名称" prop="projectName">
              <el-input
                v-model="form_report.projectName"
                placeholder="请输入项目名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="结题年度" prop="conclusionYear">
              <el-input
                v-model="form_report.conclusionYear"
                placeholder="请输入结题年度"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="归口管理部门" prop="proxyOrgCode">
              <treeselect
                v-model="form_report.proxyOrgName"
                :options="deptOptions"
                :disable-branch-nodes="true"
                :show-count="true"
                :default-expand-level="Infinity"
                placeholder="请选择归属部门"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="结题日期" prop="conclusionEndDate">
              <el-date-picker
                v-model="form_report.conclusionEndDate"
                type="date"
                placeholder="选择日期"
              ></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="10">
          <el-col :span="6">
            <el-form-item label="实际支出资金" prop="outAppAmount">
              <el-input
                v-model="form_report.outAppAmount"
                placeholder="请输入实际支出资金"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="支出自筹经费" prop="outMyAmount">
              <el-input
                v-model="form_report.outMyAmount"
                placeholder="请输入支出自筹经费"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="总支出" prop="outTotalaAmount">
              <el-input
                v-model="form_report.outTotalaAmount"
                placeholder="请输入总支出"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="结题内容描述" prop="summary">
              <el-input
                type="textarea"
                :rows="4"
                v-model="form_report.summary"
                placeholder="请输入结题内容描述"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>
<script>
import { getToken } from "@/utils/auth";
import { addProjectconclusionreport, getProjectreport } from "@/api/pm/step.js";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Treeselect from "@riophae/vue-treeselect";
import { treeselect } from "@/api/system/dept";
export default {
  components: { Treeselect },
  data() {
    return {
      deptOptions: undefined,
      form_report: {},
      params: this.initParams,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
  },
  mounted() {
    this.getTreeselect();
    this.getForm();
  },
  beforeDestroy() {
    this.submitForm();
  },
  methods: {
    getTreeselect() {
      treeselect().then((response) => {
        this.deptOptions = response.data;
      });
    },
    submitForm() {
      this.form_report.projectId = this.params.projectId;
      this.form_report.categoryCode = this.params.categoryCode;
      addProjectconclusionreport(this.form_report).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("添加成功");
        } else {
          this.$message.error("添加失败");
        }
      });
    },
    getForm() {
      getProjectreport(this.params.projectId).then((response) => {
        this.form_report = response.data;
      });
    },
  },
};
</script>