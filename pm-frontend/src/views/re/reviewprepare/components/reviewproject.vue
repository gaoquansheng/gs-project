<template>
  <div class="main">
    <el-form ref="form" :model="form" label-width="115px">
      <el-row>
        <el-col :span="10"
          ><el-form-item label="项目类别" prop="categoryCode">
            <el-select
              v-model="form.categoryCode"
              placeholder="请选择项目类别"
              @change="getPhase"
            >
              <el-option
                v-for="category in categorys"
                :key="category.categoryCode"
                :label="category.categoryCode + '-' + category.categoryName"
                :value="category.categoryCode"
              ></el-option> </el-select></el-form-item
        ></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="10"
          ><el-form-item label="流程" prop="phaseCode">
            <el-select v-model="form.phaseCode" placeholder="请选择项目类别">
              <el-option
                v-for="phase in phases"
                :key="phase.phaseCode"
                :label="phase.phaseName"
                :value="phase.phaseCode"
              ></el-option>
            </el-select> </el-form-item
        ></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="10"
          ><el-form-item label="时间" prop="time">
            <el-input
              v-model="form.time"
              placeholder="请输入时间"
            ></el-input> </el-form-item
        ></el-col>
      </el-row>
    </el-form>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="候选项目列表" name="first">
        <el-table
          :data="data"
          style="width: 100%">
          <el-table-column
            prop="prop"
            label="label"
            width="width">
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="已选项目列表" name="second">
        <el-table
          :data="data"
          style="width: 100%">
          <el-table-column
            prop="prop"
            label="label"
            width="width">
          </el-table-column>
        </el-table>
      </el-tab-pane>
      
    </el-tabs>
  </div>
</template>




<script>
import { listProjectcategory } from "@/api/pc/projectcategory";
import { listProjectcategoryphase } from "@/api/pc/projectcategoryphase";
export default {
  name: "Step",
  data() {
    return {
      activeName: "first",
      categorys: [],
      phases: [],
      form: {},
    };
  },
  mounted() {
    this.getCategorys();
  },
  methods: {
    //得到所有项目类别
    getCategorys() {
      listProjectcategory().then((res) => {
        this.categorys = res.rows;
      });
    },

    handleClick(tab, event) {
      console.log(tab, event);
    },
    getPhase() {
      let tmp = {
        categoryCode: this.form.categoryCode,
      };
      listProjectcategoryphase(tmp).then((response) => {
        this.phases = response.rows.filter(
          (item) => item.enabled == 1 && item.needReview == 1
        );
        // console.log(a);
      });
    },
  },
};
</script>
</style>