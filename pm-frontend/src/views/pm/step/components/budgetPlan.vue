<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="总投资">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="中央投资">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="地方投资">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="自筹投资">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {
  listProjectbugdet,
  addProjectbudget,
  updateProjectbudget,
  updateProject,
  updateProjecttask,
  getProjectList,
  listProjecttask,
  getProjecttask
} from "@/api/pm/step.js";
export default {
  data() {
    return {
      form: {},
      sum: [],
      budgetInfo: [],
      compressedData: [],
      params: this.initParams,
      loading: false,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
  },
  mounted() {
    //首先获取树形表格
    if (!this.params.projectId) {
    } else {
      this.getBudgetInfo();
      this.listBudget();
    }
  },
  watch: {
    params: {
      deep:true,
      handler(old,newVal) {
        this.getBudgetInfo();
        this.listBudget();
      },
    },
  },
  beforeDestroy() {
    this.submitForm();
  },
  methods: {
    getBudgetInfo() {
      //这里的值没有立即传过来，projectId没有立即更新怎么办呢
      this.loading = true;
      if (!this.params.projectId) {
        setTimeout(() => {
          listProjectbugdet(
            this.params.categoryCode,
            this.params.projectId
          ).then((response) => {
            // listProjectbugdet(this.project.categoryCode, this.project.projectId).then(response => {
            console.log("===============");
            console.log(response);
            this.loading = false;
            this.budgetInfo = this.handleTree(
              response.data,
              "subjectId",
              "parentSubjectId",
              "children",
              -1
            );
          });
        }, 1000);
      } else {
        listProjectbugdet(
          this.params.projectStatus,
          this.params.projectId,
          this.params.categoryCode
        ).then((response) => {
          // listProjectbugdet(this.project.categoryCode, this.project.projectId).then(response => {
          // console.log("===============");
          // console.log(response);
          this.loading = false;
          this.budgetInfo = this.handleTree(
            response.data,
            "subjectId",
            "parentSubjectId",
            "children",
            -1
          );
        });
      }
    },
    submitForm() {
      //如果budgetinfo有RID这个属性就更新，没有就新增
      this.compressedData = [];
      let data = this.treeDataToCompressed(this.budgetInfo);
      data.forEach((item) => {
        item.categoryCode = this.params.categoryCode;
        item.phaseCode = this.params.projectStatus;
        item.projectId = this.params.projectId;
      });
      updateProjectbudget(data).then((response) => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
        } else {
          this.$message.error("修改失败，系统内部错误,检查数据是否全部填完");
        }
      });
    },
    treeDataToCompressed: function (source) {
      for (let i in source) {
        this.compressedData.push(source[i]);
        source[i].children && source[i].children.length > 0
          ? this.treeDataToCompressed(source[i].children)
          : ""; // 子级递归
      }
      return this.compressedData;
    },
    handleSum(row) {
      row.totalAmount = parseFloat(row.appAmount) + parseFloat(row.myAmount);
      if (row.parentSubjectId != -1) {
        this.sumParent(this.budgetInfo, row.parentSubjectId);
      }
    },
    sumParent(data, pId) {
      data.forEach((item) => {
        if (item.subjectId == pId) {
          //找到父节点
          item.appAmount = 0;
          item.myAmount = 0;
          item.totalAmount = 0;
          item.children.forEach((child) => {
            item.appAmount += parseFloat(child.appAmount);
            item.myAmount += parseFloat(child.myAmount);
            item.totalAmount += parseFloat(child.totalAmount);
          });
          if (item.parentSubjectId != -1) {
            this.sumParent(this.budgetInfo, item.parentSubjectId);
          }
        } else if (item.children) {
          this.sumParent(item.children, pId);
        }
      });
    },
    sumBudget() {
      this.sum = [];
      let a = 0;
      let b = 0;
      let c = 0;
      this.budgetInfo.forEach((item) => {
        if (item.parentSubjectId == -1) {
          a += parseFloat(item.appAmount);
          b += parseFloat(item.myAmount);
          c += parseFloat(item.totalAmount);
        }
      });
      let d = {
        projectId: this.params.projectId,
        appAmount: a,
        myAmount: b,
        totalAmount: c,
      };
      this.sum.push(d);
      // console.log(this.adc);
      if (this.params.projectStatus.indexOf("1") == 0) {
        updateProject(d).then((response) => {
          this.msgSuccess("计算成功，数据已保存");
        });
      }
      if (this.params.projectStatus.indexOf("2") == 0) {
        updateProjecttask(d).then((response) => {
          this.msgSuccess("计算成功，数据已保存");
        });
      }
    },
    listBudget() {
      this.sum = [];
      if (this.params.projectStatus.indexOf("1") === 0) {
        getProjectList(this.params.projectId).then((response) => {
          let d = {
            projectId: this.params.projectId,
            appAmount: response.data.appAmount,
            myAmount: response.data.myAmount,
            totalAmount: response.data.totalAmount,
          };
          
          this.sum.push(d);
        });
      }
      if (this.params.projectStatus.indexOf("2") === 0) {
        console.log(this.params.projectId);
        getProjecttask(this.params.projectId).then((response) => {
          // console.log(response);
          console.log(response);
          let d = {
            projectId: this.params.projectId,
            appAmount: response.data.appAmount,
            myAmount: response.data.myAmount,
            totalAmount: response.data.totalAmount,
          };
          // console.log(d);
          this.sum.push(d);
        });
      }
    },
  },
};
</script>