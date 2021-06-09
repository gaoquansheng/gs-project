<template>
  <!-- 如果是word显示文件上传框 -->
  <div class="info">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>填报说明</span>
      </div>
      <div class="text item">
        <p>{{ desc }}</p>
      </div>
    </el-card>
    <el-upload
      drag
      ref="upload"
      class="upload-demo"
      :action="uploadDocUrl"
      :headers="headers"
      :on-change="handleDocChange"
      :on-success="docFileUploadSuccess"
      :on-error="fileUploadError"
      :file-list="docFileList"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">
        只能上传word文件，且不超过999999999MB
      </div>
    </el-upload>
  </div>
</template>
<script>
import { getToken } from "@/utils/auth";
import {
  getProjectwordcontent,
  InsertProjectwordcontent,
} from "@/api/pm/step.js";
export default {
  data() {
    return {
      phaseCode: "",
      form_wordContent: {
        bookmarkCode: undefined,
        textContent: "",
        attachedWordPath: "",
      },
      desc: "",
      uploadDocUrl: process.env.VUE_APP_BASE_API + "/common/bmark",
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      docFileList: [],
      params: this.initParams,
      allStep: this.allSteps,
    };
  },
  props: {
    initParams: {
      type: Object,
    },
    allSteps: {
      type: Object,
    },
  },
  mounted() {
    this.getProjectwordcontentList();
    this.desc = this.allSteps.bookmarkDesc;
  },
  beforeDestroy() {
    this.submitForm();
  },
  methods: {
    //上传文件相关方法
    handleDocChange(files, fileList) {
      if (fileList.length > 0) {
        this.docFileList = [fileList[fileList.length - 1]]; // 这一步，是 展示最后一次选择的csv文件
      }
    },
    docFileUploadSuccess(res, file) {
      this.form_wordContent.attachedWordPath = res.url;
    },
    fileUploadError() {
      this.msgError("上传失败");
    },
    getProjectwordcontentList() {
      if (this.params.projectStatus == "0") {
        this.phaseCode = "1001";
      } else {
        this.phaseCode = this.params.projectStatus;
      }
      getProjectwordcontent(
        this.params.projectId,
        this.phaseCode
      ).then((response) => {
        // console.log(response.data);
        this.wordContentList = response.data;
        this.wordContentList.forEach((item) => {
          if (
            item.bookmarkCode == this.allStep.bookmarkCode &&
            item.attachedWordPath != null
          ) {
            this.form_wordContent.attachedWordPath = item.attachedWordPath;
            this.docFileList = [{ name: item.attachedWordPath }];
          }
        });
      });
    },
    submitForm() {
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
    },
  },
};
</script>