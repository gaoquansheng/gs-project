<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item>
        <el-button icon="el-icon-refresh" size="mini">刷新</el-button>
      </el-form-item>
    </el-form> -->



    <el-table v-loading="loading" :data="projectcategoryList">
      <el-table-column label="类别代号" align="center" prop="categoryCode" />
      <el-table-column label="类别名称" align="center" prop="categoryName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >设置项目流程</el-button>
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

    <!-- 添加或修改项目类别对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <div style="text-align:center">
        <vue2-org-tree 
          :data="phaseTreeList" 
          selected-class-name="bg-gold"
          selected-key="selectedKey"
          :label-class-name="labelClassName"
          @on-node-click="onNodeClick"
          :props = "props"
        /> 
      </div>
 

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listByAdminUserName } from "@/api/pc/projectcategoryadmins";
import { listWorkflowphase} from "@/api/pc/workflowphase";
import {listProjectcategoryphase,updateProjectcategoryphase} from "@/api/pc/projectcategoryphase"

import "vue2-org-tree/dist/style.css"
export default {
  // name: "Projectcategory",
  data() {
    return {
      props: {
        id: "phaseCode",
        label: "phaseName",
        children: "children"
      },
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
      // 项目类别表格数据
      projectcategoryList: [],
      phaseTreeList: {},
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      updateList: []
      // 表单参数
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目类别列表 */
    getList() {
      this.loading = true;
      listByAdminUserName(this.queryParams).then(response => {
        this.projectcategoryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    //查询出对应项目类别的项目流程
    handleUpdate(row) {
      this.loading = true;
      this.queryParams.categoryCode = row.categoryCode;
      let tmp = {
        categoryCode: row.categoryCode
      }
      listProjectcategoryphase(tmp).then(response => {
        response.rows.forEach(item => {
          if(item.enabled == 1){ 
            item.selectedKey = true;
          }
        })
        this.phaseTreeList = this.handleTree(response.rows,"phaseCode","parentCode","children","-1")[0]
        this.open = true;
        this.title = "定制项目类别流程";
        this.loading = false;
        
      })

    },
    /** 提交按钮 */
    submitForm: function() {
      this.updateList = [];
      this.treeToJson(this.phaseTreeList)
      this.loading = true;
      updateProjectcategoryphase(this.updateList).then(response => {
        this.open = false;
        this.loading = false;
        this.msgSuccess("修改成功");
      })
      
    },
    onNodeClick: function(e, data) {
      console.log('onNodeClick: %o', data);
      this.$set(data, 'selectedKey', !data.selectedKey);
      data.enabled == 1? data.enabled =0: data.enabled =1;
      
    },
    labelClassName: function(data) {
      return 'clickable-node'
    },
    treeToJson(tree){
      //还有更好的办法
      this.updateList.push({
        "categoryCode": tree.categoryCode,
        "phaseCode": tree.phaseCode,
        "enabled": tree.enabled
      })
      if(tree.children != undefined){
        this.treeToJson(tree.children[0])
      }
    }
  }
};
</script>
<style>
    .org-tree-node-label {
      white-space: nowrap;
    }
    .bg-white {
      background-color: white;
    }
    .bg-orange {
      background-color: orange;
    }
    .bg-gold {
      background-color: gold;
    }
    .bg-gray {
      background-color: gray;
    }
    .bg-lightpink {
      background-color: lightpink;
    }
    .bg-chocolate {
      background-color: chocolate;
    }
    .bg-tomato {
      background-color: tomato;
    }
    .clickable-node {
      cursor: pointer;
    }
</style>
