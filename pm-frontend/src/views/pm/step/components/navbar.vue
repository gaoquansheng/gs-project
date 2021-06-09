<template>
  <div>
    <h2>填报导航</h2>
    <el-steps :active="active" finish-status="success">
      <el-step
        v-for="(step, index) in steps"
        :title="step.stepName"
        :key="index"
      ></el-step>
    </el-steps>

    <el-row class="step">
      <el-col :offset="18">
        <el-button-group>
          <el-button
            type="primary"
            icon="el-icon-arrow-left"
            @click="prev"
            :disabled="active == 0"
            >上一步</el-button
          >
          <el-button
            type="primary"
            @click="next"
            v-show="active < steps.length - 1"
          >
            下一步
            <i class="el-icon-arrow-right el-icon--right"></i>
          </el-button>
          <el-button
            type="primary"
            v-show="active === steps.length - 1"
            @click="update"
          >
            保存并提交
            <i class="el-icon-upload el-icon--right"></i>
          </el-button>
        </el-button-group>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {
  listSteps,
  getProjectList,
  updateProject,
  addProjecttask,
  addProjectconclusionreport,
} from "@/api/pm/step.js";
import { listProjectcategoryphase } from "@/api/pc/projectcategoryphase";
export default {
  data() {
    return {
      phase: [],
      project: {},
      steps: [],
      active: 0,
      params: this.initParams,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
    stepId: {
      type: String,
    },
    allSteps: {
      type: Object,
    },
  },
  mounted() {
    listSteps(this.params).then((response) => {
      this.steps = response;
      console.log(this.steps);
    });
    this.getPhase();
  },
  methods: {
    next() {
      ++this.active;
      this.$emit("update:stepId", this.steps[this.active].stepid);
      this.$emit("update:allSteps", this.steps[this.active]);
    },
    //上一步
    prev() {
      --this.active;
      this.$emit("update:stepId", this.steps[this.active].stepid);
      this.$emit("update:allSteps", this.steps[this.active]);
    },
    update() {
      if (
        this.params.projectStatus == "1001" ||
        this.params.projectStatus == null
      ) {
        this.phase.forEach((item, index) => {
          if (item.phaseCode == this.params.projectStatus) {
            this.project.projectStatus = this.phase[index + 1].phaseCode;
          }
        });
        this.project.projectId = this.params.projectId;
        updateProject(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目状态为"+this.project.projectStatus);
            this.closePage();
          }
        });
      }
      if (this.params.projectStatus == "2001") {
        this.phase.forEach((item, index) => {
          if (item.phaseCode == this.params.projectStatus) {
            this.project.projectStatus = this.phase[index + 1].phaseCode;
          }
        });
        this.project.projectId = this.params.projectId;
        console.log(this.project);
        addProjecttask(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目申请状态为"+this.project.projectStatus);
            this.closePage();
          } else {
            this.msgError("添加失败");
          }
        });
        updateProject(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目任务状态为"+this.project.projectStatus);
            this.closePage();
          }
        });
      }
      if (this.params.projectStatus == "4001") {
        this.phase.forEach((item, index) => {
          if (item.phaseCode == this.params.projectStatus) {
            this.project.projectStatus = this.phase[index + 1].phaseCode;
          }
        });
        this.project.projectId = this.params.projectId;
        addProjectconclusionreport(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目申请状态为"+this.project.projectStatus);
            this.closePage();
          } else {
            this.$message.error("添加失败");
          }
        });
        addProjecttask(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目任务状态为"+this.project.projectStatus);
            this.closePage();
          } else {
            this.msgError("添加失败");
          }
        });
        updateProject(this.project).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("保存成功，更新项目结题状态为"+this.project.projectStatus);
            this.closePage();
          }
        });
      }
    },
    closePage() {
      this.$store.state.tagsView.visitedViews.splice(
        this.$store.state.tagsView.visitedViews.findIndex(
          (item) => item.path === this.$route.path
        ),
        1
      );
      this.$router.push(
        this.$store.state.tagsView.visitedViews[
          this.$store.state.tagsView.visitedViews.length - 1
        ].path
      );
    },
    getPhase() {
      let tmp = {
        categoryCode: this.params.categoryCode,
      };
      listProjectcategoryphase(tmp).then((response) => {
        this.phase = response.rows.filter((item) => item.enabled == 1);
        // console.log(a);
      });
    },
  },
};
</script>
<style scoped>
.step {
  margin: 50px 0;
}
</style>