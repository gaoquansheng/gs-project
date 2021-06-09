<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="项目类别" prop="categoryCode">
        <el-select
          @change="handleQuery"
          v-model="queryParams.categoryCode"
          placeholder="请选择项目类别"
          size="small"
        >
          <el-option
            v-for="category in categoryList"
            :key="category.categoryCode"
            :label="category.categoryCode+'-'+category.categoryName"
            :value="category.categoryCode"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="setprojectcategoryphaseList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label=" 管理流程代码" align="center" prop="categoryCode" /> -->
      <!-- <el-table-column label="管理流程代码" align="center" prop="phaseCode" /> -->
      <el-table-column label="类别流程" align="center" prop="phaseName" />
      <el-table-column label="在线填写word" align="center" prop="needWord" :formatter="needWordFormat" />
      <el-table-column label="在线填报模板文件路径" align="center" prop="wordPath" >
        <template slot-scope="scope" >
          <el-link v-if="scope.row.wordPath" :href="scope.row.wordPath" type="primary">下载</el-link>
        </template>
      </el-table-column>
      <el-table-column label="参考下载文档模板" align="center" prop="refFilePath" >
        <template slot-scope="scope" >
          <el-link v-if="scope.row.refFilePath" :href="scope.row.refFilePath" type="primary">下载</el-link>
        </template>
      </el-table-column>
      <el-table-column
        label="评审"
        align="center"
        prop="needReview"
        :formatter="needReviewFormat"
      />
      <!-- <el-table-column label="是否启用" align="center" prop="enabled" :formatter="enabledFormat" /> -->
      <!-- <el-table-column label="是否启用" align="center" prop="parentCode" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pc:setprojectcategoryphase:edit']"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改配置项目类别对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="项目类别" prop="categoryCode">
          <el-input disabled v-model="form.categoryCode" />
        </el-form-item>
        <!-- <el-form-item label="流程代码" prop="phaseCode">
          <el-input disabled v-model="form.phaseCode" />
        </el-form-item> -->
        <el-form-item label="流程名称" prop="phaseName">
          <el-input disabled v-model="form.phaseName" />
        </el-form-item>
        <!-- <el-form-item label="是否启用">
          <el-select disabled v-model="form.enabled">
            <el-option
              v-for="dict in enabledOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="是否需要评审">
          <el-select v-model="form.needReview">
            <el-option
              v-for="dict in needReviewOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="在线填写word">
          <el-select v-model="form.needWord">
            <el-option
              v-for="dict in needWordOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="模板文件路径" prop="wordPath">
          <el-upload
            :disabled="form.needWord == 0"
            ref="upload"
            class="upload-demo"
            :action="uploadDocUrl"
            :headers="headers"
            :on-change="handleDocChange"
            :on-success="docFileUploadSuccess"
            :file-list="docFileList"
            accept=".doc,.docx"
          >
            <el-button
              :disabled="form.needWord == 0"
              slot="trigger"
              size="small"
              type="success"
            >点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="参考文档下载模板" >
          <el-upload
            :disabled="form.needWord == 0"
            ref="upload"
            class="upload-demo"
            :action="uploadRefUrl"
            :headers="headers"
            :on-change="handleRefChange"
            :on-success="refFileUploadSuccess"
            :on-error="fileUploadError"
            :file-list="refFileList"
          >
            <el-button :disabled="form.needWord == 0" slot="trigger" size="small" type="success">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  updateProjectcategoryphase,
  listProjectcategoryphase
} from "@/api/pc/projectcategoryphase";
import {listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { getToken } from "@/utils/auth";

export default {
  // name: "Setprojectcategoryphase",
  data() {
    let checkFile = (rule,value,callback) =>{
      if(this.form.needWord){
        console.log("值为：",value);
        if(!value){
          callback(new Error('请上传模板文件'));
        }else{
          callback();
        }
      }else{
        callback();
      }
    }
    return {
      docFileList: [],
      refFileList: [],
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 配置项目类别表格数据
      setprojectcategoryphaseList: [],
      // 弹出层标题
      title: "类别流程配置",
      // 是否显示弹出层
      open: false,
      // 当前管理流程阶段是否需要在线填写word字典
      needWordOptions: [],
      // 当前管理流程阶段是否需要评审字典
      needReviewOptions: [],
      // 是否启用字典
      enabledOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enabled: 1
      },
      // 表单参数
      form: {},
      categoryList: [],
      rules: {
        wordPath: [{ validator: checkFile, trigger: "blur" }]
        // refFilePath: [{ validator: checkFile, trigger: "blur" }]
      },
      headers: {
        Authorization: "Bearer " + getToken()
      },
      uploadDocUrl: process.env.VUE_APP_BASE_API + "/common/doc",
      uploadRefUrl: process.env.VUE_APP_BASE_API + "/common/ref"
    };
  },
  created() {
    this.getCategoryList();
    // this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.needWordOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.needReviewOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.enabledOptions = response.data;
    });
  },
  methods: {
    //查询所有的项目类别

    getCategoryList() {
      this.loading = true;
      listByAdminUserName(this.queryParams).then(res => {
        this.loading = false;
        this.categoryList = res.rows;
        //加载项目类别的时候默认选中第一个
        if (this.categoryList.length) {
          this.queryParams.categoryCode = this.categoryList[0].categoryCode;
          this.getList();
        }
      });
    },

    /** 查询配置项目类别列表 */
    getList() {
      this.loading = true;
      listProjectcategoryphase(this.queryParams).then(response => {
        this.setprojectcategoryphaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 当前管理流程阶段是否需要在线填写word字典翻译
    needWordFormat(row, column) {
      return this.selectDictLabel(this.needWordOptions, row.needWord);
    },
    // 当前管理流程阶段是否需要评审字典翻译
    needReviewFormat(row, column) {
      return this.selectDictLabel(this.needReviewOptions, row.needReview);
    },
    // 是否启用字典翻译
    enabledFormat(row, column) {
      return this.selectDictLabel(this.enabledOptions, row.enabled);
    },
    // wordPathFormat(row, column) {
    //   console.log(row.wordPath);
    //   return row.wordPath == null? "": "<el-link>下载</el-link>";
    // },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        categoryCode: undefined,
        phaseCode: undefined,
        phaseName: undefined,
        needWord: undefined,
        wordPath: undefined,
        refFilePath: undefined,
        needReview: undefined,
        enabled: undefined,
        parentCode: undefined
      };
      this.docFileList = [];
      this.refFileList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.categoryCode);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },

    /** 修改按钮操作 */
    handleUpdate(row) { 
      //这里之前都去用id再取回来数据,原来这个会影响原数据的.!!!!,这里还是去拿后端的数据比较好!!
      this.reset();
      this.form = JSON.parse(JSON.stringify(row));
      if(this.form.wordPath){
        this.docFileList = [{name:this.form.wordPath}];
      }
      if(this.form.refFilePath){
        this.refFileList = [{name:this.form.refFilePath}];
      }
      // this.refFileList = this.form.refFilePath;
      // let docFileName = row.wordPath;
      // let refFileName = row.refFilePath;
      // if(row.wordPath){
      // if(row.wordPath.split("doc/").length>1){
      //   docFileName = row.wordPath.split("doc/")[row.wordPath.split("doc/").length-1]
      // }
      // }
      // if(row.refFilePath){
      // if(row.refFilePath.split("ref/").length>1){
      //   refFileName = row.refFilePath.split("ref/")[row.refFilePath.split("ref/").length-1]
      // }
      // }
      // this.docFileList = [{name:docFileName, url: row.wordPath }];
      // this.refFileList = [{name:refFileName, url: row.refFilePath }];
      this.open = true;

    },
    /** 提交按钮 */
    submitForm: function() {

      this.$refs.upload.submit();
      this.$refs["form"].validate(valid => {
        if (valid) {
          let tmp = [];
          tmp.push(this.form);
          updateProjectcategoryphase(tmp).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const categoryCodes = row.categoryCode || this.ids;
      this.$confirm(
        '是否确认删除配置项目类别编号为"' + categoryCodes + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delSetprojectcategoryphase(categoryCodes);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },

    handleDocChange(files, fileList) {
      if (fileList.length > 0) {
        this.docFileList = [fileList[fileList.length - 1]]; // 这一步，是 展示最后一次选择的csv文件
      }
    },
    handleRefChange(files, fileList) {
      if (fileList.length > 0) {
        this.refFileList = [fileList[fileList.length - 1]]; // 这一步，是 展示最后一次选择的csv文件
      }
    },
    docFileUploadSuccess(res, file) {
      this.form.wordPath = res.url;
    },
    refFileUploadSuccess(res, file) {
      this.form.refFilePath = res.url;
    },
    fileUploadError() {
      this.msgError("上传失败")
    }
  }
};
</script>

