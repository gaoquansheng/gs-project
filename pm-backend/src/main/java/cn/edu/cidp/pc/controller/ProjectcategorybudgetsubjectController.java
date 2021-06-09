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
import cn.edu.cidp.pc.domain.Projectcategorybudgetsubject;
import cn.edu.cidp.pc.service.IProjectcategorybudgetsubjectService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;

/**
 * 项目类别预算Controller
 * 
 * @author william
 * @date 2020-08-10
 */
@RestController
@RequestMapping("/pc/projectcategorybudgetsubject")
public class ProjectcategorybudgetsubjectController extends BaseController
{
    @Autowired
    private IProjectcategorybudgetsubjectService projectcategorybudgetsubjectService;

    /**
     * 查询项目类别预算列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:list')")
    @GetMapping("/list")
    public AjaxResult list(Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        List<Projectcategorybudgetsubject> list = projectcategorybudgetsubjectService.selectProjectcategorybudgetsubjectList(projectcategorybudgetsubject);
        return AjaxResult.success(list);
    }

    /**
     * 导出项目类别预算列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:export')")
    @Log(title = "项目类别预算", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        List<Projectcategorybudgetsubject> list = projectcategorybudgetsubjectService.selectProjectcategorybudgetsubjectList(projectcategorybudgetsubject);
        ExcelUtil<Projectcategorybudgetsubject> util = new ExcelUtil<Projectcategorybudgetsubject>(Projectcategorybudgetsubject.class);
        return util.exportExcel(list, "projectcategorybudgetsubject");
    }

    /**
     * 获取项目类别预算详细信息
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:query')")
    @GetMapping(value = "/{subjectId}")
    public AjaxResult getInfo(@PathVariable("subjectId") Long subjectId)
    {
        return AjaxResult.success(projectcategorybudgetsubjectService.selectProjectcategorybudgetsubjectById(subjectId));
    }

    /**
     * 新增项目类别预算
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:add')")
    @Log(title = "项目类别预算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        return toAjax(projectcategorybudgetsubjectService.insertProjectcategorybudgetsubject(projectcategorybudgetsubject));
    }

    /**
     * 修改项目类别预算
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:edit')")
    @Log(title = "项目类别预算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        return toAjax(projectcategorybudgetsubjectService.updateProjectcategorybudgetsubject(projectcategorybudgetsubject));
    }

    /**
     * 删除项目类别预算
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategorybudgetsubject:remove')")
    @Log(title = "项目类别预算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{subjectIds}")
    public AjaxResult remove(@PathVariable Long[] subjectIds)
    {
        return toAjax(projectcategorybudgetsubjectService.deleteProjectcategorybudgetsubjectByIds(subjectIds));
    }
}
