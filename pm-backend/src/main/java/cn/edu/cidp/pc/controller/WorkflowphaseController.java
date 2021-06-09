package cn.edu.cidp.pc.controller;

import java.util.List;
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
import cn.edu.cidp.pc.domain.Workflowphase;
import cn.edu.cidp.pc.service.IWorkflowphaseService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;

/**
 * 管理流程Controller
 *
 * @author william
 * @date 2020-08-10
 */
@RestController
@RequestMapping("/pc/workflowphase")
public class WorkflowphaseController extends BaseController
{
    @Autowired
    private IWorkflowphaseService workflowphaseService;

    /**
     * 查询管理流程列表
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<Workflowphase> list = workflowphaseService.selectWorkflowphaseList();
        return AjaxResult.success(list);
    }

    /**
     * 导出管理流程列表
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:export')")
    @Log(title = "管理流程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Workflowphase workflowphase)
    {
        List<Workflowphase> list = workflowphaseService.selectWorkflowphaseList();
        ExcelUtil<Workflowphase> util = new ExcelUtil<Workflowphase>(Workflowphase.class);
        return util.exportExcel(list, "workflowphase");
    }

    /**
     * 获取管理流程详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:query')")
    @GetMapping(value = "/{phaseCode}")
    public AjaxResult getInfo(@PathVariable("phaseCode") String phaseCode)
    {
        return AjaxResult.success(workflowphaseService.selectWorkflowphaseById(phaseCode));
    }

    /**
     * 新增管理流程
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:add')")
    @Log(title = "管理流程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workflowphase workflowphase)
    {
        return toAjax(workflowphaseService.insertWorkflowphase(workflowphase));
    }

    /**
     * 修改管理流程
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:edit')")
    @Log(title = "管理流程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workflowphase workflowphase)
    {
        return toAjax(workflowphaseService.updateWorkflowphase(workflowphase));
    }

    /**
     * 删除管理流程
     */
//    @PreAuthorize("@ss.hasPermi('pc:workflowphase:remove')")
    @Log(title = "管理流程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{phaseCodes}")
    public AjaxResult remove(@PathVariable String[] phaseCodes)
    {
        return toAjax(workflowphaseService.deleteWorkflowphaseByIds(phaseCodes));
    }
}
