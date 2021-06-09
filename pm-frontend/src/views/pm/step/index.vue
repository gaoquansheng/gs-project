<template>
  <div class="main">
  <!-- <el-row>
    <el-col :span="22" :offset="1"> -->
    <Navbar :initParams="params" :stepId.sync="stepId"  :allSteps.sync="allSteps"/>
    <BaseInfo :initParams="params" v-if="stepId == 'baseInfo' && (params.projectStatus == null || params.projectStatus.indexOf('1') === 0)"/>
    <!-- <Task :initParams="params" v-if="stepId == 'baseInfo' && params.projectStatus.indexOf('2') === 0"/> -->
    <!-- <Report :initParams="params" v-if="stepId == 'baseInfo' && params.projectStatus.indexOf('4') === 0"/> -->
    <!-- <BudgetPlan :initParams="params" v-if="stepId == 'budgetPlan'" /> -->
    <BudgetYearPlan :initParams="params" v-if="stepId == 'budgetYearPlan'" />
    <!-- 项目负责人 -->
    <HasMembers :initParams="params" v-if="stepId == 'hasMembers'" />
    <!-- <HasOrgs :initParams="params" v-if="stepId == 'hasOrgs'" /> -->
    <!-- <Texts :initParams="params" :allSteps.sync="allSteps" v-if="stepId == 'text'"/> -->
    <!-- <Word :initParams="params" :allSteps.sync="allSteps" v-if="stepId == 'word'" /> -->
    <Appplication :initParams="params" v-if="stepId == 'application'" />
    <!-- </el-col> -->

  <!-- </el-row> -->
  </div>
</template>




<script>
import BaseInfo from "@/views/pm/step/components/baseInfo";
import BudgetPlan from "@/views/pm/step/components/budgetPlan";
import BudgetYearPlan from "@/views/pm/step/components/budgetYearPlan";
import HasMembers from "@/views/pm/step/components/hasMembers";
import HasOrgs from "@/views/pm/step/components/hasOrgs";
import Navbar from "@/views/pm/step/components/navbar";
import Texts from "@/views/pm/step/components/text";
import Word from "@/views/pm/step/components/word";
import Appplication from '@/views/pm/step/components/appplication'
import Report from '@/views/pm/step/components/report'
import Task from '@/views/pm/step/components/task'
export default {
  // name: "Step",
  components: {
    BaseInfo,
    BudgetPlan,
    BudgetYearPlan,
    HasMembers,
    HasOrgs,
    Navbar,
    Texts,
    Word,
    Appplication,
    Report,
    Task
  },
  data() {
    return {
      params: "",
      stepId: "baseInfo",
      allSteps:{},
    };
  },

  created() {
    this.params = this.$route.query;
  },


  methods: {},
};
</script>
<style scoped>
.main {
  padding: 20px;
}
</style>