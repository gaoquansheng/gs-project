<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :myData="pieChartData"  :title="'项目比例'"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :myData="pieChartData2" :title="'项目所属领域'"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart :myData="barChartData" :title="'项目投资来源'" />
        </div>
      </el-col>
    </el-row>

    
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import { listProjectList} from "@/api/pm/projectList";
import { listProjecttask} from "@/api/pm/step.js";
const lineChartData = {
  applicationProjectList: {
    xAxis: [],
    yAxis: []
  },
  approvalProjectList: {
    xAxis: [],
    yAxis: []
  },
  finalizeProjectList: {
    xAxis: [],
    yAxis: []
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.applicationProjectList,
      projectList: [],
      projectTaskList: [],
      pieChartData: [],
      pieChartData2: [],
      moneyFromOptions: [],
      projectArearOptions: [],
      barChartData: {
        xAxis: [],
        yAxis: []
      }
    }
  },
  created() {
    this.getDicts("project_arear").then(response =>{
      this.projectArearOptions = response.data;
    })
    this.getDicts("money_from").then(response => {
      this.moneyFromOptions = response.data;
      this.getProjectTaskList();
      this.getProjectList();
    })
    
  },
  methods: {
    getProjectTaskList(){
      listProjecttask().then(res =>{
        this.projectTaskList = res.rows;
        this.handleApprovalProject();
      })
    },
    getProjectList(){
      listProjectList().then(res => {
        this.projectList = res.rows;
        this.handleApplicationProject();
        this.handleFinalizeProject();
        this.handlePieChartData();
        this.handleBarChartData();
      })
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    handleApplicationProject(){
      this.projectList.forEach(project =>{
        let year = new Date(project.appTime).getFullYear();
        if(!lineChartData.applicationProjectList.xAxis.includes(year)){
          lineChartData.applicationProjectList.xAxis.push(year);
          lineChartData.applicationProjectList.yAxis.push(1);
        }else{
          let index = lineChartData.applicationProjectList.xAxis.indexOf(year);
          lineChartData.applicationProjectList.yAxis[index]++;
        }
      })
    },
    handleApprovalProject(){
      this.projectTaskList.forEach(project => {
        let year = new Date(project.appTime).getFullYear();
        if(!lineChartData.approvalProjectList.xAxis.includes(year)){
          lineChartData.approvalProjectList.xAxis.push(year);
          lineChartData.approvalProjectList.yAxis.push(1);
        }else{
          let index = lineChartData.approvalProjectList.xAxis.indexOf(year);
          lineChartData.approvalProjectList.yAxis[index]++;
        } 
      })
    },
    handleFinalizeProject(){
      let tmp = this.projectList.filter(project =>project.projectStatus == 1);
      tmp.forEach(project => {
        let year = new Date(project.appTime).getFullYear();
        if(!lineChartData.finalizeProjectList.xAxis.includes(year)){
          lineChartData.finalizeProjectList.xAxis.push(year);
          lineChartData.finalizeProjectList.yAxis.push(1);
        }else{
          let index = lineChartData.finalizeProjectList.xAxis.indexOf(year);
          lineChartData.finalizeProjectList.yAxis[index]++;
        } 
      })
    },
    handlePieChartData(){
      this.pieChartData.push({name: "项目申请数", value: this.projectList.length});
      this.pieChartData.push({name: "项目立项数", value: this.projectTaskList.length});
      this.pieChartData.push({name: "项目结题数", value: this.projectList.filter(project => project.projectStatus == 1).length});
      //这里处理项目投资来源
      
      this.projectArearOptions.forEach(projectArear =>{
        this.pieChartData2.push({name: projectArear.dictLabel, value: this.projectList.filter(project =>{
          return project.projectArear == projectArear.dictValue
        }).length})
      })

    },
    handleBarChartData(){
      //按照年度划分，横坐标
      //这写的跟个屎一样。没办法。现在脑子有点混乱。
      this.projectList.forEach(project =>{
        let year = new Date(project.appTime).getFullYear();
        if(!this.barChartData.xAxis.includes(year)){
          this.barChartData.xAxis.push(year);
        }
      });
      this.moneyFromOptions.forEach(moneyFrom => {
        let tmp = {
          name: moneyFrom.dictLabel,
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: new Array(this.barChartData.xAxis.length).fill(0)
        };
        this.barChartData.yAxis.push(tmp);
      });
      this.projectList.forEach(project => {
        let year = new Date(project.appTime).getFullYear();
        let index = this.barChartData.xAxis.indexOf(year);
        this.barChartData.yAxis.forEach(bar => {
          if(this.moneyFromFormat(project.moneyFrom) == bar.name){
            bar.data[index]++;
          }
        })
      })
    },
    moneyFromFormat(data) {
      return this.selectDictLabel(this.moneyFromOptions, data);
    },
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
