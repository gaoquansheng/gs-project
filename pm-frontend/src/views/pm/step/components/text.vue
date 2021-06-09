<template>
  <!-- 如果是text显示文本域输入框 -->
  <div class="info">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>填报说明</span>
      </div>
      <div class="text item">
        <p>{{ desc }}</p>
      </div>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>填报内容</span>
      </div>
      <div class="text item">
        <el-form ref="form_wordContent" :model="form_wordContent">
          <el-form-item prop="textContent">
            <el-input
              type="textarea"
              :rows="7"
              v-model="form_wordContent.textContent"
              placeholder="请输入内容"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getProjectwordcontent , InsertProjectwordcontent} from "@/api/pm/step.js";
export default {
  data() {
    return {
      phaseCode:'',
      steps:[],
      //储存书签的描述
      desc: "",
      //书签内容列表
      wordContentList: [],
      form_wordContent: {
        bookmarkCode: undefined,
        textContent: "",
        attachedWordPath: "",
      },
      params: this.initParams,
      allStep:this.allSteps
    };
  },
  mounted() {
    this.getProjectwordcontentList();
    this.desc = this.allStep.bookmarkDesc;
  },
  props: {
    initParams: {
      type: Object,
    },
    allSteps:{
      type: Object,
    }
  },
  beforeDestroy() {
    this.submitForm();
  },
  methods: {
    //查询projectWordContent表渲染书签内容,根据projectId得到所有的书签内容
    getProjectwordcontentList() {
      if(this.params.projectStatus == "0"){
        this.phaseCode = "1001"
      }else{
        this.phaseCode = this.params.projectStatus;
      }
      getProjectwordcontent(this.params.projectId, this.phaseCode).then(
        (response) => {
          // console.log(response.data);
          this.wordContentList = response.data;
          this.wordContentList.forEach((item) => {
            if(item.bookmarkCode == this.allStep.bookmarkCode && item.textContent != null){
              this.form_wordContent.textContent = item.textContent;
            }
          })
        }
      );
    },
    submitForm(){
       this.form_wordContent.bookmarkCode = this.allStep.bookmarkCode;
          this.form_wordContent.projectId = this.params.projectId;
          this.form_wordContent.categoryCode = this.params.categoryCode;
          this.form_wordContent.phaseCode = this.phaseCode;
          this.form_wordContent.reportYear = this.params.reportYear;
          InsertProjectwordcontent(this.form_wordContent).then((response) => {
            if (response.code === 200) {
              this.msgSuccess("添加成功");
            } else {
              this.$message.error("添加失败");
            }
          });
    }
  },
};
</script>