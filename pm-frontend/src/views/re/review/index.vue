<template>
  <div class="main">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="查看相关材料" name="first">
      </el-tab-pane>
      <el-tab-pane label="业务评审意见" name="second">
      </el-tab-pane>
      <el-tab-pane label="打分" name="third">
      </el-tab-pane>
      <el-tab-pane label="投票" name="fourth">
      </el-tab-pane>
    </el-tabs>
  </div>
</template>




<script>
export default {
  name: "Review",
  data() {
    return {
      activeName: "first",
    };
  },
  mounted() {
    this.getCategorys();
  },
  methods: {

    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>
</style>