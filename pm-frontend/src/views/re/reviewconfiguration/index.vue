<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="115px">
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
        <el-col :span="10">
          <el-form-item label="业务评审意见" prop="businessReviewConfiguation">
            <el-select
              v-model="form.businessReviewConfiguation"
              placeholder="请选择业务评审意见"
            >
              <el-option
                v-for="dict in businessReviewConfiguationOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="10"
          ><el-form-item label="预算评审意见" prop="budgetReviewConfiguation">
            <el-select
              v-model="form.budgetReviewConfiguation"
              placeholder="请选择预算评审意见"
            >
              <el-option
                v-for="dict in budgetReviewConfiguationOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select> </el-form-item
        ></el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="10"
          ><el-form-item label="是否评分" prop="scoreReviewConfiguation">
            <el-select
              v-model="form.scoreReviewConfiguation"
              placeholder="请选择是否评分"
            >
              <el-option
                v-for="dict in scoreReviewConfiguationOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option> </el-select></el-form-item
        ></el-col>
        <el-col :span="6">
          <el-button type="primary" @click="handleScore" >评分设置</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="10"
          ><el-form-item label="是否投票:" prop="voteReviewConfiguation">
            <el-select
              v-model="form.voteReviewConfiguation"
              placeholder="请选择是否投票:"
            >
              <el-option
                v-for="dict in voteReviewConfiguationOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select> </el-form-item
        ></el-col>
        <el-col :span="6">
          <el-button type="primary" @click="handleVote" >投票设置</el-button>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">保存配置</el-button>
    </div>

    <el-table v-loading="loading" :data="reviewconfigurationList">
      <el-table-column label="项目类别" align="center" prop="categoryCode" />
      <el-table-column label="流程" align="center" prop="phaseCode" />
      <el-table-column
        label="业务评审意见"
        align="center"
        prop="businessReviewConfiguation"
        :formatter="businessReviewConfiguationFormat"
      />
      <el-table-column
        label="预算评审意见"
        align="center"
        prop="budgetReviewConfiguation"
        :formatter="budgetReviewConfiguationFormat"
      />
      <el-table-column
        label="是否评分"
        align="center"
        prop="scoreReviewConfiguation"
        :formatter="scoreReviewConfiguationFormat"
      />
      <el-table-column
        label="是否投票"
        align="center"
        prop="voteReviewConfiguation"
        :formatter="voteReviewConfiguationFormat"
      />
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import {
  listReviewconfiguration,
  getReviewconfiguration,
  delReviewconfiguration,
  addReviewconfiguration,
  updateReviewconfiguration,
  exportReviewconfiguration,
} from "@/api/re/reviewconfiguration";
import { listProjectcategory } from "@/api/pc/projectcategory";
import { listProjectcategoryphase } from "@/api/pc/projectcategoryphase";
export default {
  name: "Reviewconfiguration",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 评审配置表格数据
      reviewconfigurationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 业务评审意见字典
      businessReviewConfiguationOptions: [],
      // 预算评审意见字典
      budgetReviewConfiguationOptions: [],
      // 是否评分字典
      scoreReviewConfiguationOptions: [],
      // 是否投票:字典
      voteReviewConfiguationOptions: [],
      categorys: [],
      phases: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {
        voteReviewConfiguation: null,
        scoreReviewConfiguation: null

      },
      // 表单校验
      rules: {

      },
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_review_type").then((response) => {
      this.businessReviewConfiguationOptions = response.data;
    });
    this.getDicts("budget_review").then((response) => {
      this.budgetReviewConfiguationOptions = response.data;
    });
    this.getDicts("sys_review_type").then((response) => {
      this.scoreReviewConfiguationOptions = response.data;
    });
    this.getDicts("sys_review_type").then((response) => {
      this.voteReviewConfiguationOptions = response.data;
    });
    this.getCategorys();
  },
  methods: {
    /** 查询评审配置列表 */
    getList() {
      this.loading = true;
      listReviewconfiguration(this.queryParams).then((response) => {
        this.form = response.rows[0]
        this.reviewconfigurationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      this.getPhase()
    },
    // 业务评审意见字典翻译
    businessReviewConfiguationFormat(row, column) {
      return this.selectDictLabel(
        this.businessReviewConfiguationOptions,
        row.businessReviewConfiguation
      );
    },
    // 预算评审意见字典翻译
    budgetReviewConfiguationFormat(row, column) {
      return this.selectDictLabel(
        this.budgetReviewConfiguationOptions,
        row.budgetReviewConfiguation
      );
    },
    // 是否评分字典翻译
    scoreReviewConfiguationFormat(row, column) {
      return this.selectDictLabel(
        this.scoreReviewConfiguationOptions,
        row.scoreReviewConfiguation
      );
    },
    // 是否投票:字典翻译
    voteReviewConfiguationFormat(row, column) {
      return this.selectDictLabel(
        this.voteReviewConfiguationOptions,
        row.voteReviewConfiguation
      );
    },
    //得到所有项目类别
    getCategorys() {
      listProjectcategory().then((res) => {
        this.categorys = res.rows;
      });
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
      // getReviewconfiguration(this.form.categoryCode).then((response) => {
      //   console.log(response.rows)
      //   this.form = response.rows[0];
      // });
    },
    handleScore(){
      if(this.form.scoreReviewConfiguation == '0' || this.form.scoreReviewConfiguation == null){
          this.msgSuccess("选择以后才能设置")
      }else{
        let res = {
        categoryCode : this.form.categoryCode,
        phaseCode : this.form.phaseCode
      }
      this.$router.push({name: "score",query: res});
      }
    },
    handleVote(){
      if(this.form.voteReviewConfiguation == '0' || this.form.voteReviewConfiguation == null){
          this.msgSuccess("选择以后才能设置")
      }else{
        let res = {
        categoryCode : this.form.categoryCode,
        phaseCode : this.form.phaseCode
      }
      this.$router.push({name: "vote",query: res});
      }
      
    },
    // 表单重置
    reset() {
      this.form = {
        categoryCode: undefined,
        phaseCode: undefined,
        businessReviewConfiguation: undefined,
        budgetReviewConfiguation: undefined,
        scoreReviewConfiguation: undefined,
        voteReviewConfiguation: undefined,
      };
      this.resetForm("form");
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评审配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const categoryCode = row.categoryCode || this.ids;
      getReviewconfiguration(categoryCode).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评审配置";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      addReviewconfiguration(this.form).then((response) => {
        console.log("aaa");
        if (response.code === 200) {
          this.msgSuccess("新增成功");
          this.getList();
        }
      });
    },
  },
};
</script>