<template>
  <div class="app-container">
    <div class="workflowphase">
      <vue2-org-tree 
      :data="workflowphaseList" 
      :props="props"
      />  
    </div>
  </div>
</template>

<script>
import { listWorkflowphase, getWorkflowphase, delWorkflowphase, addWorkflowphase, updateWorkflowphase, exportWorkflowphase } from "@/api/pc/workflowphase";
// import Treeselect from "@riophae/vue-treeselect";
// import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import "vue2-org-tree/dist/style.css"

export default {
  name: "Workflowphase",
  data() {
    return {
      props: {
        id: "phaseCode",
        label: "phaseName",
        children: "children"
      },
      // 遮罩层
      loading: true,
      // 管理流程表格数据
      workflowphaseList: {},
      // 表单参数
      form: {},

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询管理流程列表 */
    getList() {
      this.loading = true;
      listWorkflowphase().then(response => {
        this.workflowphaseList= this.handleTree(response.data, "phaseCode", "parentCode","children","-1")[0];
        this.loading = false;
      });
    }
  }
};
</script>
<style>
.workflowphase {
  text-align: center;
}
.clickable-node {
      cursor: pointer;
    }
</style>