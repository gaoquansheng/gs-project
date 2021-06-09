import request from '@/utils/request'

// 生成导航栏
export function listSteps(data) {
  return request({
    url: '/pm/steps/list',
    method: 'post',
    data: data
  })
}

// 返回预算信息
export function listProjectbugdet(phaseCode,projectId,categoryCode) {
  return request({
    url: '/pm/projectbudget/list/' + phaseCode + '/' + projectId + '/' + categoryCode,
    method: 'get',
  })
}

//添加预算信息
export function addProjectbudget(data) {
  return request({
    url: '/pm/projectbudget/add',
    method: 'post',
    data: data
  })
}
//更新预算信息
export function updateProjectbudget(data) {
  return request({
    url: '/pm/projectbudget/update',
    method: 'post',
    data: data
  })
}
// 查询项目年度预算列表
export function listProjectyearbudgetplan(projectId,phaseCode) {
  return request({
    url: '/pm/projectyearbudgetplan/list/' + projectId + '/' + phaseCode,
    method: 'get',
  })
}

// 查询项目组成员列表
export function listProjectmeber(projectId,phaseCode) {
  return request({
    url: '/pm/projectmeber/list/' + projectId + '/' + phaseCode,
    method: 'get',
  })
}
// 查询添加合作单位列表
export function listProjectorg(projectId,phaseCode) {
  return request({
    url: '/pm/projectorg/list/' + projectId + '/' + phaseCode,
    method: 'get',
  })
}





//添加书签项目word正文内容
export function InsertProjectwordcontent(data) {
  return request({
    url: '/pm/projectwordcontent',
    method: 'post',
    data: data
  })
}
//根据projectId查询书签内容、文件路径
export function getProjectwordcontent(projectId,phaseCode) {
  return request({
    url: '/pm/projectwordcontent/' + projectId + '/' + phaseCode,
    method: 'get'
  })
}


//添加项目基本信息
export function addProject(data) {
  return request({
    url: '/pm/projectList',
    method: 'post',
    data: data
  })
}
export function updateProject(data) {
  return request({
    url: '/pm/projectList',
    method: 'put',
    data: data
  })
}

//添加项目年度预算信息
export function addBudgetYearPlan(data){
  return request({
    url: '/pm/projectyearbudgetplan',
    method: 'post',
    data: data
  })
}


//删除项目年度预算信息
export function delBudgetYearPlan(rid){
  return request({
    url: '/pm/projectyearbudgetplan/' + rid,
    method: 'delete',
  })
}

//查询项目年度预算详细
export function getBudgetYearPlan(rid) {
  return request({
    url: '/pm/projectyearbudgetplan/' + rid,
    method: 'get'
  })
}
//更新项目年度预算
export function updatebudgetYearPlan(data){
  return request({
    url: '/pm/projectyearbudgetplan',
    method: 'put',
    data: data
  })
}

// 查询项目组成员详细
export function getProjectmeber(rid) {
  return request({
    url: '/pm/projectmeber/' + rid,
    method: 'get'
  })
}

// 新增项目组成员
export function addProjectmeber(data) {
  return request({
    url: '/pm/projectmeber',
    method: 'post',
    data: data
  })
}
// 新增内部项目组成员
export function addProjectuser(data) {
  return request({
    url: '/pm/projectmeber/add',
    method: 'post',
    data:data
  })
}

// 修改项目组成员
export function updateProjectmeber(data) {
  return request({
    url: '/pm/projectmeber',
    method: 'put',
    data: data
  })
}

// 删除项目组成员
export function delProjectmeber(rid) {
  return request({
    url: '/pm/projectmeber/' + rid,
    method: 'delete'
  })
}



// 查询添加合作单位详细
export function getProjectorg(rid) {
  return request({
    url: '/pm/projectorg/' + rid,
    method: 'get'
  })
}

// 新增添加合作单位
export function addProjectorg(data) {
  return request({
    url: '/pm/projectorg',
    method: 'post',
    data: data
  })
}

// 新增内部项目组成员
export function addProjectDept(data) {
  return request({
    url: '/pm/projectorg/add',
    method: 'post',
    data:data
  })
}
// 修改添加合作单位
export function updateProjectorg(data) {
  return request({
    url: '/pm/projectorg',
    method: 'put',
    data: data
  })
}

// 删除添加合作单位
export function delProjectorg(rid) {
  return request({
    url: '/pm/projectorg/' + rid,
    method: 'delete'
  })
}



//结题报告相关

// 查询项目结题报告列表
export function listProjectconclusionreport(query) {
  return request({
    url: '/pm/projectconclusionreport/list',
    method: 'get',
    params: query
  })
}

// 查询项目结题报告详细
export function getProjectconclusionreport(projectId) {
  return request({
    url: '/pm/projectconclusionreport/' + projectId,
    method: 'get'
  })
}

// 新增项目结题报告
export function addProjectconclusionreport(data) {
  return request({
    url: '/pm/projectconclusionreport',
    method: 'post',
    data: data
  })
}

// 修改项目结题报告
export function updateProjectconclusionreport(data) {
  return request({
    url: '/pm/projectconclusionreport',
    method: 'put',
    data: data
  })
}

// 删除项目结题报告
export function delProjectconclusionreport(projectId) {
  return request({
    url: '/pm/projectconclusionreport/' + projectId,
    method: 'delete'
  })
}




//内容回显
// 查询项目申请书详细
export function getProjectList(projectId) {
  return request({
    url: '/pm/projectList/' + projectId,
    method: 'get'
  })
}





//项目任务书相关接口
// 查询项目任务书列表
export function listProjecttask(query) {
  return request({
    url: '/pm/projecttask/list',
    method: 'get',
    params: query
  })
}

// 查询项目任务书详细
export function getProjecttask(projectId) {
  return request({
    url: '/pm/projecttask/' + projectId,
    method: 'get'
  })
}

// 新增项目任务书
export function addProjecttask(data) {
  return request({
    url: '/pm/projecttask',
    method: 'post',
    data: data
  })
}

// 修改项目任务书
export function updateProjecttask(data) {
  return request({
    url: '/pm/projecttask',
    method: 'put',
    data: data
  })
}

// 删除项目任务书
export function delProjecttask(projectId) {
  return request({
    url: '/pm/projecttask/' + projectId,
    method: 'delete'
  })
}


//查询结题报告详细
export function getProjectreport(projectId) {
  return request({
    url: '/pm/projectconclusionreport/' + projectId,
    method: 'get'
  })
}




export function getPdf(projectId,phaseCode,categoryCode){
  return request({
    url:'/pm/pdf/make/'+ projectId + '/' + phaseCode + '/' + categoryCode,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    responseType: 'blob',
    method: 'get'
  })
}