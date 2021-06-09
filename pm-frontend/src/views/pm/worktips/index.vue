<template>
  <div class="app-container">
    <el-card>
      <div slot="header">填报内容</div>
      <div>
        <el-table v-loading="loading" :data="worktipsList">
          <!-- <el-table-column label="" align="center" prop="rId" /> -->
          <el-table-column label="年度" align="center" prop="openYear" />
          <el-table-column
            label="项目类别"
            align="center"
            prop="categoryName"
          />
          <el-table-column label="项目阶段" align="center" prop="phaseName" />
          <!-- <el-table-column
            label="项目阶段代码(测试)"
            align="center"
            prop="phaseCode"
          /> -->
          <!-- <el-table-column label="项目Id" align="center" prop="projectId" /> -->
          <!-- <el-table-column label="年度" align="center" prop="projectYear" /> -->
          <!-- <el-table-column label="开始年份" align="center" prop="openYear" /> -->
          <el-table-column
            label="开始时间"
            align="center"
            prop="startTime"
            width="180"
          >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.startTime, "{y}-{m}-{d}") }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="结束时间"
            align="center"
            prop="endTime"
            width="180"
          >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.endTime, "{y}-{m}-{d}") }}</span>
            </template>
          </el-table-column>
          <el-table-column label="项目名称" align="center" prop="projectName" />
          <el-table-column
            label="项目状态"
            align="center"
            prop="projectStatus"
            :formatter="projectStatusFormat"
          />
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-show="
                  showList.indexOf(scope.row.projectStatus) != -1 ||
                  !scope.row.projectStatus
                "
                @click="handleClick(scope.row)"
                >{{ scope.row.projectStatus | formatProjectStatus }}</el-button
              >
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                v-show="
                  showList.indexOf(scope.row.projectStatus) != -1 ||
                  !scope.row.projectStatus
                "
                @click="handleDelete(scope.row)"
                v-hasPermi="['pm:worktips:remove']"
                >丢弃</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
    <el-card>
      <el-button type="primary" @click="test">页面测试</el-button>
      <div slot="header">评审内容</div>
      <div>
        <el-table v-loading="loading" :data="worktipsList">
          <!-- <el-table-column label="" align="center" prop="rId" /> -->
          <el-table-column label="年度" align="center" prop="openYear" />
          <el-table-column
            label="项目类别"
            align="center"
            prop="categoryName"
          />
          <el-table-column label="项目阶段" align="center" prop="phaseName" />
          <!-- <el-table-column
            label="项目阶段代码(测试)"
            align="center"
            prop="phaseCode"
          /> -->
          <!-- <el-table-column label="项目Id" align="center" prop="projectId" /> -->
          <!-- <el-table-column label="年度" align="center" prop="projectYear" /> -->
          <!-- <el-table-column label="开始年份" align="center" prop="openYear" /> -->
          <el-table-column
            label="开始时间"
            align="center"
            prop="startTime"
            width="180"
          >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.startTime, "{y}-{m}-{d}") }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="结束时间"
            align="center"
            prop="endTime"
            width="180"
          >
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.endTime, "{y}-{m}-{d}") }}</span>
            </template>
          </el-table-column>
          <el-table-column label="项目名称" align="center" prop="projectName" />
          <el-table-column
            label="项目状态"
            align="center"
            prop="projectStatus"
            :formatter="projectStatusFormat"
          />
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                v-show="
                  showList.indexOf(scope.row.projectStatus) != -1 ||
                  !scope.row.projectStatus
                "
                @click="handleClick(scope.row)"
                >{{ scope.row.projectStatus | formatProjectStatus }}</el-button
              >
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                v-show="
                  showList.indexOf(scope.row.projectStatus) != -1 ||
                  !scope.row.projectStatus
                "
                @click="handleDelete(scope.row)"
                v-hasPermi="['pm:worktips:remove']"
                >丢弃</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>

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
import { listWorktips } from "@/api/pm/worktips";

export default {
  // name: "Worktips",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 工作提示表格数据
      worktipsList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      path: "step",
      statusOptions: "",
      showList: ["1001", "2001", "4001"],
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_project_status").then((response) => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    test() {
      console.log("1");
      this.$router.push({ name: "review" });
    },
    /** 查询工作提示列表 */
    getList() {
      this.loading = true;
      listWorktips().then((response) => {
        console.log(response.rows);

        this.worktipsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleClick(data) {
      // console.log(data);

      let res = {
        categoryCode: data.categoryCode,
        projectStatus: data.projectStatus == null ? "1001" : data.projectStatus,
        projectId: data.projectId,
        projectYear: data.openYear,
        phaseCode: data.projectStatus == null ? "1001" : data.projectStatus,
      };
      // console.log(data.projectStatus);
      this.$router.push({ name: this.path, query: res });
    },
    handleDelete(data) {},
    projectStatusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.projectStatus);
    },
  },
  filters: {
    formatProjectStatus(projectStatus) {
      if (!projectStatus) {
        return "申报";
      } else {
        return "修改";
      }
    },
  },
};
</script>