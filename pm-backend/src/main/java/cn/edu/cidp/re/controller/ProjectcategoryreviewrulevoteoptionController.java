package cn.edu.cidp.re.controller;

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
import cn.edu.cidp.re.domain.Projectcategoryreviewrulevoteoption;
import cn.edu.cidp.re.service.IProjectcategoryreviewrulevoteoptionService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * projectcategoryreviewrulevoteoptionController
 * 
 * @author william
 * @date 2021-01-12
 */
@RestController
@RequestMapping("/re/projectcategoryreviewrulevoteoption")
public class ProjectcategoryreviewrulevoteoptionController extends BaseController
{
    @Autowired
    private IProjectcategoryreviewrulevoteoptionService projectcategoryreviewrulevoteoptionService;

    /**
     * 查询projectcategoryreviewrulevoteoption列表
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:list')")
    @GetMapping("/list/{categoryCode}/{phaseCode}")
    public TableDataInfo list(@PathVariable("categoryCode") String categoryCode,  @PathVariable("phaseCode") String phaseCode)
    {
        startPage();
        List<Projectcategoryreviewrulevoteoption> list = projectcategoryreviewrulevoteoptionService.selectVoteByCcPc(categoryCode,phaseCode);
        return getDataTable(list);
    }

    /**
     * 导出projectcategoryreviewrulevoteoption列表
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:export')")
    @Log(title = "projectcategoryreviewrulevoteoption", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption)
    {
        List<Projectcategoryreviewrulevoteoption> list = projectcategoryreviewrulevoteoptionService.selectProjectcategoryreviewrulevoteoptionList(projectcategoryreviewrulevoteoption);
        ExcelUtil<Projectcategoryreviewrulevoteoption> util = new ExcelUtil<Projectcategoryreviewrulevoteoption>(Projectcategoryreviewrulevoteoption.class);
        return util.exportExcel(list, "projectcategoryreviewrulevoteoption");
    }

    /**
     * 获取projectcategoryreviewrulevoteoption详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:query')")
    @GetMapping(value = "/{optionId}")
    public AjaxResult getInfo(@PathVariable("optionId") Long optionId)
    {
        return AjaxResult.success(projectcategoryreviewrulevoteoptionService.selectProjectcategoryreviewrulevoteoptionById(optionId));
    }

    /**
     * 新增projectcategoryreviewrulevoteoption
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:add')")
    @Log(title = "projectcategoryreviewrulevoteoption", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption)
    {
        return toAjax(projectcategoryreviewrulevoteoptionService.insertProjectcategoryreviewrulevoteoption(projectcategoryreviewrulevoteoption));
    }

    /**
     * 修改projectcategoryreviewrulevoteoption
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:edit')")
    @Log(title = "projectcategoryreviewrulevoteoption", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption)
    {
        return toAjax(projectcategoryreviewrulevoteoptionService.updateProjectcategoryreviewrulevoteoption(projectcategoryreviewrulevoteoption));
    }

    /**
     * 删除projectcategoryreviewrulevoteoption
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulevoteoption:remove')")
    @Log(title = "projectcategoryreviewrulevoteoption", businessType = BusinessType.DELETE)
	@DeleteMapping("/{optionIds}")
    public AjaxResult remove(@PathVariable Long[] optionIds)
    {
        return toAjax(projectcategoryreviewrulevoteoptionService.deleteProjectcategoryreviewrulevoteoptionByIds(optionIds));
    }
}
