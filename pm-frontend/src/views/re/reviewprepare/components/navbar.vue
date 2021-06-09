<template>
  <div class="app-container">
    <el-steps :active="active" finish-status="success">
      <el-step title="选择待评审项目"></el-step>
      <el-step title="设置组别"></el-step>
      <el-step title="分配专家"></el-step>
      <el-step title="分配项目"></el-step>
      <el-step title="确认分组信息"></el-step>
      <el-step title="微调专家"></el-step>
      <el-step title="替换专家"></el-step>
    </el-steps>

    <el-button style="margin-top: 12px" @click="next">下一步</el-button>
    <el-button style="margin-top: 12px" @click="pre">上一步</el-button>
  </div>
</template>

<script>
export default {
  name: "Reviewprepare",
  data() {
    return {
      active: 0
    }
  },
  created() {},
  methods: {
    next() {
      ++this.active;
      this.$emit("update:active", this.active);
    },
    pre() {
      --this.active;
      this.$emit("update:active", this.active);
    },
  },
};
</script>