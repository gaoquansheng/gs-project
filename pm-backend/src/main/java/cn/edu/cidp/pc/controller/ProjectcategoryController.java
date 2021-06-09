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
import cn.edu.cidp.pc.domain.Projectcategory;
import cn.edu.cidp.pc.service.IProjectcategoryService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目类别Controller
 *
 * @author william
 * @date 2020-08-06
 */
@RestController
@RequestMapping("/pc/projectcategory")
public class ProjectcategoryController extends BaseController
{
    @Autowired
    private IProjectcategoryService projectcategoryService;

    /**
     * 查询项目类别列表
     */
//    @PreAuthorize("@ss.hasPermi('pc:projectcategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectcategory projectcategory)
    {
        startPage();
        List<Projectcategory> list = projectcategoryService.selectProjectcategoryList(projectcategory);
        return getDataTable(list);
    }

    /**
     * 导出项目类别列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategory:export')")
    @Log(title = "项目类别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategory projectcategory)
    {
        List<Projectcategory> list = projectcategoryService.selectProjectcategoryList(projectcategory);
        ExcelUtil<Projectcategory> util = new ExcelUtil<Projectcategory>(Projectcategory.class);
        return util.exportExcel(list, "projectcategory");
    }

    /**
     * 获取项目类别详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pc:projectcategory:query')")
    @GetMapping(value = "/{categoryCode}")
    public AjaxResult getInfo(@PathVariable("categoryCode") String categoryCode)
    {
        return AjaxResult.success(projectcategoryService.selectProjectcategoryById(categoryCode));
    }

    /**
     * 新增项目类别
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategory:add')")
    @Log(title = "项目类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategory projectcategory)
    {
        return toAjax(projectcategoryService.insertProjectcategory(projectcategory));
    }

    /**
     * 修改项目类别
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategory:edit')")
    @Log(title = "项目类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategory projectcategory)
    {
        return toAjax(projectcategoryService.updateProjectcategory(projectcategory));
    }

    /**
     * 删除项目类别
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategory:remove')")
    @Log(title = "项目类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryCodes}")
    public AjaxResult remove(@PathVariable String[] categoryCodes)
    {
        return toAjax(projectcategoryService.deleteProjectcategoryByIds(categoryCodes));
    }
}
