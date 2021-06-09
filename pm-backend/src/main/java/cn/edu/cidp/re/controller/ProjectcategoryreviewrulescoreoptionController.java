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
import cn.edu.cidp.re.domain.Projectcategoryreviewrulescoreoption;
import cn.edu.cidp.re.service.IProjectcategoryreviewrulescoreoptionService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 评分设置Controller
 *
 * @author william
 * @date 2021-01-12
 */
@RestController
@RequestMapping("/re/projectcategoryreviewrulescoreoption")
public class ProjectcategoryreviewrulescoreoptionController extends BaseController {
    @Autowired
    private IProjectcategoryreviewrulescoreoptionService projectcategoryreviewrulescoreoptionService;

    /**
     * 查询评分设置列表
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:list')")
    @GetMapping("/list/{categoryCode}/{phaseCode}")
    public TableDataInfo list(@PathVariable("categoryCode") String categoryCode, @PathVariable("phaseCode") String phaseCode) {
        startPage();
        List<Projectcategoryreviewrulescoreoption> list = projectcategoryreviewrulescoreoptionService.selectScoreByCcPc(categoryCode, phaseCode);
        return getDataTable(list);
    }

    /**
     * 导出评分设置列表
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:export')")
    @Log(title = "评分设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        List<Projectcategoryreviewrulescoreoption> list = projectcategoryreviewrulescoreoptionService.selectProjectcategoryreviewrulescoreoptionList(projectcategoryreviewrulescoreoption);
        ExcelUtil<Projectcategoryreviewrulescoreoption> util = new ExcelUtil<Projectcategoryreviewrulescoreoption>(Projectcategoryreviewrulescoreoption.class);
        return util.exportExcel(list, "projectcategoryreviewrulescoreoption");
    }

    /**
     * 获取评分设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId) {
        return AjaxResult.success(projectcategoryreviewrulescoreoptionService.selectProjectcategoryreviewrulescoreoptionById(scoreId));
    }

    /**
     * 新增评分设置
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:add')")
    @Log(title = "评分设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        return toAjax(projectcategoryreviewrulescoreoptionService.insertProjectcategoryreviewrulescoreoption(projectcategoryreviewrulescoreoption));
    }

    /**
     * 修改评分设置
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:edit')")
    @Log(title = "评分设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        return toAjax(projectcategoryreviewrulescoreoptionService.updateProjectcategoryreviewrulescoreoption(projectcategoryreviewrulescoreoption));
    }

    /**
     * 删除评分设置
     */
    @PreAuthorize("@ss.hasPermi('re:projectcategoryreviewrulescoreoption:remove')")
    @Log(title = "评分设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds) {
        return toAjax(projectcategoryreviewrulescoreoptionService.deleteProjectcategoryreviewrulescoreoptionByIds(scoreIds));
    }
}
