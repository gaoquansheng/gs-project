package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.common.utils.SecurityUtils;
import cn.edu.cidp.framework.security.LoginUser;
import cn.edu.cidp.project.system.domain.SysDept;
import cn.edu.cidp.project.system.service.ISysDeptService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.cidp.framework.aspectj.lang.annotation.Log;
import cn.edu.cidp.framework.aspectj.lang.enums.BusinessType;
import cn.edu.cidp.pm.domain.ProjectList;
import cn.edu.cidp.pm.service.IProjectListService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目列表Controller
 *
 * @author william
 * @date 2020-08-27
 */
@RestController
@RequestMapping("/pm/projectList")
public class ProjectListController extends BaseController {
    @Autowired
    private IProjectListService projectListService;
    @Autowired
    private ISysDeptService deptService;
    /**
     * 查询项目列表列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectList projectList) {
        startPage();
        List<ProjectList> list = projectListService.selectprojectListList(projectList);
        return getDataTable(list);
    }

    /**
     * 导出项目列表列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:export')")
    @Log(title = "项目列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProjectList projectList) {
        List<ProjectList> list = projectListService.selectprojectListList(projectList);
        ExcelUtil<ProjectList> util = new ExcelUtil<ProjectList>(ProjectList.class);
        return util.exportExcel(list, "projectList");
    }

    /**
     * 获取项目列表详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId) {
        return AjaxResult.success(projectListService.selectprojectListById(projectId));
    }

    /**
     * 新增项目列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:add')")
    @Log(title = "项目列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectList projectList) {
        System.out.println(projectList);
        LoginUser currentUser = SecurityUtils.getLoginUser();
        projectList.setUserCode(currentUser.getUser().getUserId().toString());
        projectList.setUserName(currentUser.getUser().getUserName());
        projectList.setMyOrgCode(currentUser.getUser().getDeptId().toString());
        projectList.setMyOrgName(currentUser.getUser().getDept().getDeptName());
        //添加proxyOrgName?通过dept表加上deptName
        Long proxyOrgCode = Long.parseLong(projectList.getProxyOrgCode());
        String deptName = deptService.selectDeptById(proxyOrgCode).getDeptName();
        projectList.setProxyOrgName(deptName);
        int rows = projectListService.insertprojectList(projectList);
        System.out.println(rows);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("projectId",rows);
        return ajax;
    }

    /**
     * 修改项目列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:edit')")
    @Log(title = "项目列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectList projectList) {
        System.out.println(projectList);
        return toAjax(projectListService.updateprojectList(projectList));
    }

    /**
     * 删除项目列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectList:remove')")
    @Log(title = "项目列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds) {
        return toAjax(projectListService.deleteprojectListByIds(projectIds));
    }

}
