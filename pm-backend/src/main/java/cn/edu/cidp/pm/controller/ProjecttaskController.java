package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.pm.service.IProjectListService;
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
import cn.edu.cidp.pm.domain.Projecttask;
import cn.edu.cidp.pm.service.IProjecttaskService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目任务书Controller
 *
 * @author william
 * @date 2020-09-15
 */
@RestController
@RequestMapping("/pm/projecttask")
public class ProjecttaskController extends BaseController
{
    @Autowired
    private IProjecttaskService projecttaskService;

    @Autowired
    private IProjectListService projectListService;
    /**
     * 查询项目任务书列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projecttask projecttask)
    {
        startPage();
        List<Projecttask> list = projecttaskService.selectProjecttaskList(projecttask);
        return getDataTable(list);
    }

    /**
     * 导出项目任务书列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:export')")
    @Log(title = "项目任务书", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projecttask projecttask)
    {
        List<Projecttask> list = projecttaskService.selectProjecttaskList(projecttask);
        ExcelUtil<Projecttask> util = new ExcelUtil<Projecttask>(Projecttask.class);
        return util.exportExcel(list, "projecttask");
    }

    /**
     * 获取项目任务书详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        Projecttask projecttask = projecttaskService.selectProjecttaskById(projectId);
        if(projecttask == null){
            return AjaxResult.success(projectListService.selectprojectListById(projectId));
        }
        return AjaxResult.success(projecttaskService.selectProjecttaskById(projectId));
    }

    /**
     * 新增项目任务书
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:add')")
    @Log(title = "项目任务书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projecttask projecttask)
    {
//        System.out.println(projecttask);
        Projecttask flag = projecttaskService.selectProjecttaskById(projecttask.getProjectId());
        if(flag != null) {
            return toAjax(projecttaskService.updateProjecttask(projecttask));
        }else{
            return toAjax(projecttaskService.insertProjecttask(projecttask));
        }
    }

    /**
     * 修改项目任务书
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:edit')")
    @Log(title = "项目任务书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projecttask projecttask)
    {
        return toAjax(projecttaskService.updateProjecttask(projecttask));
    }

    /**
     * 删除项目任务书
     */
//    @PreAuthorize("@ss.hasPermi('pm:projecttask:remove')")
    @Log(title = "项目任务书", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projecttaskService.deleteProjecttaskByIds(projectIds));
    }
}
