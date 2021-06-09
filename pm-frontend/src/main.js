import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './assets/styles/element-variables.scss'



import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'

import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";

import Vue2OrgTree from 'vue2-org-tree'
 

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

// 全局组件挂载
Vue.component('Pagination', Pagination)

Vue.use(permission)
Vue.use(Vue2OrgTree)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})


Vue.directive("input-limit", {
  bind(el, binding) {
    var wins_0 = /[^\d]/g //整数判断
    var wins_1 = /[^\d^\.]/g //小数判断
    var flag = true;
    var points = 0;
    var lengths = 0
    var remainder = 0
    var no_int = 0
    const target = el instanceof HTMLInputElement ? el : el.querySelector("input");
    target.addEventListener("compositionstart", e => {
      flag = false;
    });
    target.addEventListener("compositionend", e => {
      flag = true;
    });
    target.addEventListener("input", e => {
      setTimeout(function() {
        if (flag) {
          if (binding.value == 0) {
            if (wins_0.test(e.target.value)) {
              e.target.value = e.target.value.replace(wins_0, "");
              e.target.dispatchEvent(new Event("input")) //手动更新v-model值
            }
          }
          if (binding.value == 1) {
            if (wins_0.test(e.target.value.toString().replace(/\d+\.(\d*)/, '$1'))) {
              remainder = true
            }
            if ((e.target.value.split('.')).length - 1 > 1) {
              points = true
            }
            if (e.target.value.toString().split(".")[1] != undefined) {
              if (e.target.value.toString().split(".")[1].length > 1) {
                lengths = true
              }
            }
            if (e.target.value.toString().indexOf(".") != -1) {
              no_int = false
            } else {
              no_int = true
            }
            if (wins_1.test(e.target.value) || lengths || points || remainder) {
              if (!no_int) {
                e.target.value = e.target.value.replace(wins_1, "").replace('.', '$#$').replace(/\./g, '').replace(
                  '$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').substring(0, e.target.value.indexOf(
                  ".") + 2)
              } else {
                e.target.value = e.target.value.replace(wins_0, "")
              }
              e.target.dispatchEvent(new Event("input"))
            }
          }


          if (binding.value == 2) {
            if (wins_0.test(e.target.value.toString().replace(/\d+\.(\d*)/, '$1'))) {
              remainder = true
            }
            if ((e.target.value.split('.')).length - 1 > 1) {
              points = true
            }
            if (e.target.value.toString().split(".")[1] != undefined) {
              if (e.target.value.toString().split(".")[1].length > 2) {
                lengths = true
              }
            }
            if (e.target.value.toString().indexOf(".") != -1) {
              no_int = false
            } else {
              no_int = true
            }
            if (wins_1.test(e.target.value) || lengths || points || remainder) {
              if (!no_int) {
                e.target.value = e.target.value.replace(wins_1, "").replace('.', '$#$').replace(/\./g, '').replace(
                  '$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').substring(0, e.target.value.indexOf(
                  ".") + 3)
              } else {
                e.target.value = e.target.value.replace(wins_0, "")
              }
              e.target.dispatchEvent(new Event("input"))
            }
          }
        }
      }, 0)
    })
  }
})