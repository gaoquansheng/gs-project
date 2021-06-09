package cn.edu.cidp.pm.controller;

import java.math.BigDecimal;
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
import cn.edu.cidp.pm.domain.Projectyearbudgetplan;
import cn.edu.cidp.pm.service.IProjectyearbudgetplanService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目年度预算Controller
 *
 * @author william
 * @date 2020-09-13
 */
@RestController
@RequestMapping("/pm/projectyearbudgetplan")
public class ProjectyearbudgetplanController extends BaseController
{
    @Autowired
    private IProjectyearbudgetplanService projectyearbudgetplanService;

    /**
     * 查询项目年度预算列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectyearbudgetplan:list')")
    @GetMapping(value = "/list/{projectId}/{phaseCode}")
    public TableDataInfo list(@PathVariable("projectId") Long projectId,@PathVariable("phaseCode") String phaseCode)
    {
        startPage();
        List<Projectyearbudgetplan> list = projectyearbudgetplanService.selectProjectyearbudgetplanList(projectId,phaseCode);
        return getDataTable(list);
    }


    /**
     * 获取项目年度预算详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectyearbudgetplan:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid)
    {
        return AjaxResult.success(projectyearbudgetplanService.selectProjectyearbudgetplanById(rid));
    }

    /**
     * 新增项目年度预算
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectyearbudgetplan:add')")
    @Log(title = "项目年度预算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectyearbudgetplan projectyearbudgetplan)
    {
        int count = projectyearbudgetplanService.checkUnique(projectyearbudgetplan.getBudgetYear(),projectyearbudgetplan.getProjectId(),projectyearbudgetplan.getPhaseCode());
        if (count > 0){
            return AjaxResult.error("该年度已经添加，不可重复添加");
        }
        projectyearbudgetplan.setTotalAmountBudget(projectyearbudgetplan.getCentralAmountBudget() + projectyearbudgetplan.getMyAmountBudget());
        projectyearbudgetplanService.insertProjectyearbudgetplan(projectyearbudgetplan);
        List<Projectyearbudgetplan> list = projectyearbudgetplanService.selectProjectyearbudgetplanList(projectyearbudgetplan.getProjectId(),projectyearbudgetplan.getPhaseCode());
        float totalAppAmountBudget = 0;
        float allAmountBudget = 0;
        if (list != null && list.size() > 0){
            for (int i = 0 ; i < list.size() ;i++){
                totalAppAmountBudget += list.get(i).getCentralAmountBudget();
                allAmountBudget += list.get(i).getTotalAmountBudget();
            }
        }
        for(int j = 0 ; j < list.size() ; j++){
            list.get(j).setAppBudgetPercent(list.get(j).getCentralAmountBudget() / totalAppAmountBudget);
            list.get(j).setTotalBudgetPercent(list.get(j).getTotalAmountBudget() / allAmountBudget);
            projectyearbudgetplanService.updateProjectyearbudgetplan(list.get(j));
        }
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改项目年度预算
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectyearbudgetplan:edit')")
    @Log(title = "项目年度预算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectyearbudgetplan projectyearbudgetplan)
    {
        projectyearbudgetplan.setTotalAmountBudget(projectyearbudgetplan.getCentralAmountBudget() + projectyearbudgetplan.getMyAmountBudget());
        projectyearbudgetplanService.updateProjectyearbudgetplan(projectyearbudgetplan);
        List<Projectyearbudgetplan> list = projectyearbudgetplanService.selectProjectyearbudgetplanList(projectyearbudgetplan.getProjectId(),projectyearbudgetplan.getPhaseCode());
        float totalAppAmountBudget = 0;
        float allAmountBudget = 0;
        if (list != null && list.size() > 0){
            for (int i = 0 ; i < list.size() ;i++){
                totalAppAmountBudget += list.get(i).getCentralAmountBudget();
                allAmountBudget += list.get(i).getTotalAmountBudget();
            }
        }
        for(int j = 0 ; j < list.size() ; j++){
            list.get(j).setAppBudgetPercent(list.get(j).getCentralAmountBudget() / totalAppAmountBudget);
            list.get(j).setTotalBudgetPercent(list.get(j).getTotalAmountBudget() / allAmountBudget);
            projectyearbudgetplanService.updateProjectyearbudgetplan(list.get(j));
        }
        return AjaxResult.success("操作成功");
    }

    /**
     * 删除项目年度预算
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectyearbudgetplan:remove')")
    @Log(title = "项目年度预算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rid}")
    public AjaxResult remove(@PathVariable Long[] rid)
    {
//        for(int i = 0 ; i < rid.length ; i ++){
//
//        }
//        System.out.println(rid);
        projectyearbudgetplanService.deleteProjectyearbudgetplanById(rid[0]);
        List<Projectyearbudgetplan> list = projectyearbudgetplanService.selectProjectyearbudgetplanList(rid[1],rid[2].toString());
        float totalAppAmountBudget = 0;
        float allAmountBudget = 0;
        if (list != null && list.size() > 0){
            for (int i = 0 ; i < list.size() ;i++){
                totalAppAmountBudget += list.get(i).getCentralAmountBudget();
                allAmountBudget += list.get(i).getTotalAmountBudget();
            }
        }
        for(int j = 0 ; j < list.size() ; j++){
            list.get(j).setAppBudgetPercent(list.get(j).getCentralAmountBudget() / totalAppAmountBudget);
            list.get(j).setTotalBudgetPercent(list.get(j).getTotalAmountBudget() / allAmountBudget);
            projectyearbudgetplanService.updateProjectyearbudgetplan(list.get(j));
        }
        return toAjax(3);
    }
}
