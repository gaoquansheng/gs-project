<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('applicationProjectList')">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="edit" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            项目申请数
          </div>
          <count-to :start-val="0" :end-val="applicationProjectCount" :duration="10" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('approvalProjectList')">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="skill" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            项目立项数
          </div>
          <count-to :start-val="0" :end-val="approvalProjectCount" :duration="10" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('finalizeProjectList')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="checkbox" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            项目结题数
          </div>
          <count-to :start-val="0" :end-val="finalizeProjectCount" :duration="10" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" >
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="annex" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            项目附件数
          </div>
          <count-to :start-val="0" :end-val="annexCount" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import { listUser } from "@/api/system/user";
import {listProjectdoc} from "@/api/pm/projectdoc"
import { listProjectList} from "@/api/pm/projectList"
import { listProjecttask} from "@/api/pm/step.js";

export default {
  components: {
    CountTo
  },
  data(){
    return {
      userCount: 0,
      annexCount: 0,
      applicationProjectCount: 0,
      approvalProjectCount: 0,
      finalizeProjectCount: 0,
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },
    getUserCount(){
      listUser().then(res =>{
        this.userCount = res.total;
      })
    },
    getAnnexCount(){
      listProjectdoc().then(res =>{
        this.annexCount = res.total;
      })
    },
    getProjectCount(){
      listProjectList().then(res => {
        this.applicationProjectCount = res.total;
        this.finalizeProjectCount = res.rows.filter(project => project.projectStatus == 1).length;
      })
    },
    getProjectTaskCount(){
      listProjecttask().then(res => {
        console.log(res.total);
        this.approvalProjectCount = res.total;
        console.log(this.approvalProjectCount);
      })
    }
  },
  mounted(){
    this.getUserCount();
    this.getAnnexCount();
    this.getProjectCount();
    this.getProjectTaskCount();
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
