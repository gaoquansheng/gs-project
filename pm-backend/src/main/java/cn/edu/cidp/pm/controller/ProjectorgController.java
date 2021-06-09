package cn.edu.cidp.pm.controller;

import java.util.List;

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
import cn.edu.cidp.pm.domain.Projectorg;
import cn.edu.cidp.pm.service.IProjectorgService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 添加合作单位Controller
 *
 * @author william
 * @date 2020-09-13
 */
@RestController
@RequestMapping("/pm/projectorg")
public class ProjectorgController extends BaseController {
    @Autowired
    private IProjectorgService projectorgService;

    /**
     * 查询添加合作单位列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectorg:list')")
    @GetMapping("/list/{projectId}/{phaseCode}")
    public TableDataInfo list(@PathVariable("projectId") Long projectId,@PathVariable("phaseCode") String phaseCode) {
        System.out.println(projectId);
        startPage();
        List<Projectorg> list = projectorgService.selectProjectorgList(projectId,phaseCode);
        return getDataTable(list);
    }

    /**
     * 获取添加合作单位详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectorg:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid) {
        return AjaxResult.success(projectorgService.selectProjectorgById(rid));
    }

    /**
     * 新增添加合作单位
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectorg:add')")
    @Log(title = "添加合作单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectorg projectorg) {
        int count = projectorgService.checkUnique(projectorg);
        if(count > 0){
            return AjaxResult.error(projectorg.getOrgName() + "已添加过");
        }else{
            return toAjax(projectorgService.insertProjectorg(projectorg));
        }
    }

    /**
     * 新增内部合作单位
     *
     * @param sysdept
     */
    @PostMapping(value = "/add")
    public void addProjectDept(@RequestBody List<SysDept> sysdept)
    {
        projectorgService.addProjectorg(sysdept);
    }

    /**
     * 修改添加合作单位
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectorg:edit')")
    @Log(title = "添加合作单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectorg projectorg) {
        return toAjax(projectorgService.updateProjectorg(projectorg));
    }

    /**
     * 删除添加合作单位
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectorg:remove')")
    @Log(title = "添加合作单位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids) {
        return toAjax(projectorgService.deleteProjectorgByIds(rids));
    }
}
