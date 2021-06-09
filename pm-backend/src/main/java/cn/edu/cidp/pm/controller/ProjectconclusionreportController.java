package cn.edu.cidp.pm.controller;

import java.security.Security;
import java.util.List;

import cn.edu.cidp.common.utils.SecurityUtils;
import cn.edu.cidp.framework.security.LoginUser;
import cn.edu.cidp.pm.service.IProjectListService;
import org.aspectj.weaver.loadtime.Aj;
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
import cn.edu.cidp.pm.domain.Projectconclusionreport;
import cn.edu.cidp.pm.service.IProjectconclusionreportService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目结题报告Controller
 *
 * @author william
 * @date 2020-09-15
 */
@RestController
@RequestMapping("/pm/projectconclusionreport")
public class ProjectconclusionreportController extends BaseController
{
    @Autowired
    private IProjectconclusionreportService projectconclusionreportService;

    @Autowired
    private IProjectListService projectListService;

    /**
     * 查询项目结题报告列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectconclusionreport projectconclusionreport)
    {
        startPage();
        List<Projectconclusionreport> list = projectconclusionreportService.selectProjectconclusionreportList(projectconclusionreport);
        return getDataTable(list);
    }

    /**
     * 导出项目结题报告列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:export')")
    @Log(title = "项目结题报告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectconclusionreport projectconclusionreport)
    {
        List<Projectconclusionreport> list = projectconclusionreportService.selectProjectconclusionreportList(projectconclusionreport);
        ExcelUtil<Projectconclusionreport> util = new ExcelUtil<Projectconclusionreport>(Projectconclusionreport.class);
        return util.exportExcel(list, "projectconclusionreport");
    }

    /**
     * 获取项目结题报告详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        Projectconclusionreport projectconclusionreport = projectconclusionreportService.selectProjectconclusionreportById(projectId);
        if(projectconclusionreport == null){
            return AjaxResult.success(projectListService.selectprojectListById(projectId));
        }else{
            return AjaxResult.success(projectconclusionreportService.selectProjectconclusionreportById(projectId));
        }
    }

    /**
     * 新增项目结题报告
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:add')")
    @Log(title = "项目结题报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectconclusionreport projectconclusionreport)
    {
        LoginUser currentUSer = SecurityUtils.getLoginUser();
        projectconclusionreport.setMyOrgCode(currentUSer.getUser().getDeptId().toString());
        projectconclusionreport.setMyOrgName(currentUSer.getUser().getDept().getDeptName());
        projectconclusionreport.setUserName(currentUSer.getUser().getUserName());
        projectconclusionreport.setUserCode(currentUSer.getUser().getUserId().toString());

        Projectconclusionreport flag = projectconclusionreportService.selectProjectconclusionreportById(projectconclusionreport.getProjectId());
        if (flag == null) {
            return toAjax(projectconclusionreportService.insertProjectconclusionreport(projectconclusionreport));
        }else{
            return toAjax(projectconclusionreportService.updateProjectconclusionreport(projectconclusionreport));
        }
    }

    /**
     * 修改项目结题报告
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:edit')")
    @Log(title = "项目结题报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectconclusionreport projectconclusionreport)
    {
        return toAjax(projectconclusionreportService.updateProjectconclusionreport(projectconclusionreport));
    }

    /**
     * 删除项目结题报告
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectconclusionreport:remove')")
    @Log(title = "项目结题报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectconclusionreportService.deleteProjectconclusionreportByIds(projectIds));
    }
}
