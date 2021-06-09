package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.framework.security.LoginUser;
import cn.edu.cidp.framework.security.service.TokenService;
import cn.edu.cidp.project.system.domain.SysDept;
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
import cn.edu.cidp.pm.domain.DepartmentProject;
import cn.edu.cidp.pm.service.IDepartmentProjectService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 单位项目Controller
 *
 * @author william
 * @date 2020-08-27
 */
@RestController
@RequestMapping("/pm/departmentProject")
public class DepartmentProjectController extends BaseController
{
    @Autowired
    private IDepartmentProjectService departmentProjectService;

    /**
     * 查询单位项目列表
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:list')")
    @GetMapping("/list")
    public TableDataInfo list(DepartmentProject departmentProject)
    {
        startPage();
        List<DepartmentProject> list = departmentProjectService.selectdepartmentProjectList(departmentProject);
        return getDataTable(list);
    }

    /**
     * 导出单位项目列表
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:export')")
    @Log(title = "单位项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DepartmentProject departmentProject)
    {
        List<DepartmentProject> list = departmentProjectService.selectdepartmentProjectList(departmentProject);
        ExcelUtil<DepartmentProject> util = new ExcelUtil<DepartmentProject>(DepartmentProject.class);
        return util.exportExcel(list, "departmentProject");
    }

    /**
     * 获取单位项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(departmentProjectService.selectdepartmentProjectById(projectId));
    }

    /**
     * 新增单位项目
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:add')")
    @Log(title = "单位项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentProject departmentProject)
    {
        return toAjax(departmentProjectService.insertdepartmentProject(departmentProject));
    }

    /**
     * 修改单位项目
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:edit')")
    @Log(title = "单位项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentProject departmentProject)
    {
        return toAjax(departmentProjectService.updatedepartmentProject(departmentProject));
    }

    /**
     * 删除单位项目
     */
    @PreAuthorize("@ss.hasPermi('pm:departmentProject:remove')")
    @Log(title = "单位项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(departmentProjectService.deletedepartmentProjectByIds(projectIds));
    }
}
