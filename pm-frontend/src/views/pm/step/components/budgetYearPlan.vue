<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="6">
        <el-button type="primary"  @click="handleAddBudgetYearPlan">点击添加</el-button>
      </el-col>
    </el-row>

    <el-table :data="projectyearbudgetplanList">
      <el-table-column label="年度" align="center" prop="budgetYear" />
      <el-table-column label="本年度总投资" align="center" prop="totalAmountBudget"/>
      <el-table-column label="本年度中央投资" align="center" prop="centralAmountBudget"/>
      <el-table-column label="本年度地方投资" align="center" prop="localAmountBudget"/> 
      <el-table-column label="本年度自筹投资" align="center" prop="myAmountBudget"/>
      <el-table-column label="专项经费支出比例" align="center" prop="appBudgetPercent"/>
      <el-table-column label="总预算支出比例" align="center" prop="totalBudgetPercent"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateBudgetYearPlan(scope.row)"
            >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDeleteBudgetYearPlan(scope.row)"
            >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

 <!-- 添加更新项目年度预算对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openBudgetYearPlan"
      width="800px"
      height="1000px"
      append-to-body>
      <div>
        <el-form
          ref="form_budgetYearPlan"
          :model="form_budgetYearPlan"
          label-width="80px">
          <el-form-item label="年度" prop="budgetYear">
            <el-select
              v-model="form_budgetYearPlan.budgetYear"
              placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="总投资" prop="totalAmountBudget">
            <el-input
              v-model="form_budgetYearPlan.totalAmountBudget"
              placeholder="请输入总投资"
            ></el-input>
          </el-form-item>
          <el-form-item label="中央投资" prop="centralAmountBudget">
            <el-input
              v-model="form_budgetYearPlan.centralAmountBudget"
              placeholder="请输入中央投资"
            ></el-input>
          </el-form-item>
          <el-form-item label="地方投资" prop="localAmountBudget">
            <el-input
              v-model="form_budgetYearPlan.localAmountBudget"
              placeholder="请输入地方投资"
            ></el-input>
          </el-form-item>
          <el-form-item label="自筹投资" prop="myAmountBudget">
            <el-input
              v-model="form_budgetYearPlan.myAmountBudget"
              placeholder="请输入自筹投资"
            ></el-input>
          </el-form-item>

        </el-form>
      </div>
      <div slot="footer">
        <el-button @click="cancelBudgetYearPlan">取 消</el-button>
        <el-button type="primary"  @click="submitForm_BudgetYearPlan">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {listProjectyearbudgetplan,updatebudgetYearPlan,addBudgetYearPlan,getBudgetYearPlan,delBudgetYearPlan} from "@/api/pm/step.js";
import { getProjectList } from "@/api/pm/projectList";
export default {
  data() {
    return {
      options: [],
      openBudgetYearPlan: false,
      projectyearbudgetplanList: [],
      title: "",
      form_budgetYearPlan: {},
      params: this.initParams
    };
  },
  props: {
    initParams: {
      type: Object
    }
  },
  mounted(){
    this.getProjectYear();
    this.getBudgetYearPlanList();  
  },
  methods: {
    /** 查询项目年度预算列表 */
    getBudgetYearPlanList() {
      // console.log(this.params);
      listProjectyearbudgetplan(this.params.projectId,this.params.projectStatus).then((response) => {
        this.projectyearbudgetplanList = response.rows;
      });
    },
    /** 新增按钮操作 */
    handleAddBudgetYearPlan() {
      this.resetBudgetYearPlanForm();
      this.openBudgetYearPlan = true;
      this.title = "添加项目年度预算";
    },
    handleUpdateBudgetYearPlan(row) {
      this.resetBudgetYearPlanForm();
      const rid = row.rid;
      getBudgetYearPlan(rid).then((response) => {
        this.form_budgetYearPlan = response.data;
        this.openBudgetYearPlan = true;
        this.title = "修改项目年度预算";
      });
    },
    /** 删除项目年度预算按钮操作 */
    handleDeleteBudgetYearPlan(row) {
      let rid = [];
      rid.push(row.rid);
      rid.push(this.params.projectId);
      rid.push(this.params.projectStatus);
      this.$confirm("是否确认删除该项数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delBudgetYearPlan(rid);
        })
        .then(() => {
          this.getBudgetYearPlanList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    resetBudgetYearPlanForm() {
      this.form_budgetYearPlan = {
        rid: undefined,
        categoryCode: this.params.categoryCode,
        phaseCode: this.params.projectStatus,
        projectId: this.params.projectId,
        budgetYear: undefined,
        appAmount: undefined,
        myAmountBudget: undefined,
      };
      this.resetForm("form_budgetYearPlan");
    },
    // 取消按钮
    cancelBudgetYearPlan() {
      this.openBudgetYearPlan = false;
      this.resetBudgetYearPlanForm();
    },
    submitForm_BudgetYearPlan: function () {
      if (this.title === "修改项目年度预算") {
        updatebudgetYearPlan(this.form_budgetYearPlan).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("修改成功");
            this.openBudgetYearPlan = false;
            this.getBudgetYearPlanList();
          }
        });
      } else {
        addBudgetYearPlan(this.form_budgetYearPlan).then((response) => {
          if (response.code === 200) {
            this.msgSuccess("新增成功");
            this.openBudgetYearPlan = false;
            this.getBudgetYearPlanList();
          }
        });
      }
    },
    getProjectYear(){
      console.log(this.params);
      getProjectList(this.params.projectId).then(res => {
        let startYear = parseInt(res.data.startDate.split("-")[0]);
        let endYear = parseInt(res.data.endDate.split("-")[0]);
        this.generateYear(startYear,endYear);
      })
    },
    generateYear(s,e){
      for(let i = 0;i <= e - s;i++){
        let year = {
          value: s+i,
          label: s+i + "年度",
        };
        this.options.push(year);
      }
    }
    // generateYear() {
    //   // console.log(this.params.projectYear);
    //   let bYear = this.params.projectYear;
    //   for (let i = 0; i < 5; i++) {
    //     let year = {
    //       value: bYear,
    //       lable: bYear + "年度",
    //     };
    //     this.options.push(year);
    //     bYear++;
    //   }
    // },
  }
};
</script>