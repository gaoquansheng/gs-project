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
import cn.edu.cidp.pc.domain.Projectcategoryphasewordbookmark;
import cn.edu.cidp.pc.service.IProjectcategoryphasewordbookmarkService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * Word模板设置Controller
 * 
 * @author william
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/pc/projectcategoryphasewordbookmark")
public class ProjectcategoryphasewordbookmarkController extends BaseController
{
    @Autowired
    private IProjectcategoryphasewordbookmarkService projectcategoryphasewordbookmarkService;

    /**
     * 查询Word模板设置列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        startPage();
        List<Projectcategoryphasewordbookmark> list = projectcategoryphasewordbookmarkService.selectProjectcategoryphasewordbookmarkList(projectcategoryphasewordbookmark);
        return getDataTable(list);
    }

    /**
     * 导出Word模板设置列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:export')")
    @Log(title = "Word模板设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        List<Projectcategoryphasewordbookmark> list = projectcategoryphasewordbookmarkService.selectProjectcategoryphasewordbookmarkList(projectcategoryphasewordbookmark);
        ExcelUtil<Projectcategoryphasewordbookmark> util = new ExcelUtil<Projectcategoryphasewordbookmark>(Projectcategoryphasewordbookmark.class);
        return util.exportExcel(list, "projectcategoryphasewordbookmark");
    }

    /**
     * 获取Word模板设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:query')")
    @GetMapping(value = "/{categoryCode}/{phaseCode}/{bookMarkCode}")
    public AjaxResult getInfo(@PathVariable("categoryCode") String categoryCode,
                              @PathVariable("phaseCode") String phaseCode,
                              @PathVariable("bookMarkCode") String bookMarkCode
                              )
    {
        return AjaxResult.success(projectcategoryphasewordbookmarkService.selectProjectcategoryphasewordbookmarkById(categoryCode,phaseCode,bookMarkCode));
    }

    /**
     * 新增Word模板设置
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:add')")
    @Log(title = "Word模板设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        return toAjax(projectcategoryphasewordbookmarkService.insertProjectcategoryphasewordbookmark(projectcategoryphasewordbookmark));
    }

    /**
     * 修改Word模板设置
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:edit')")
    @Log(title = "Word模板设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        return toAjax(projectcategoryphasewordbookmarkService.updateProjectcategoryphasewordbookmark(projectcategoryphasewordbookmark));
    }

    /**
     * 删除Word模板设置
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphasewordbookmark:remove')")
    @Log(title = "Word模板设置", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody List<Projectcategoryphasewordbookmark> res)
    {

        return toAjax(projectcategoryphasewordbookmarkService.deleteProjectcategoryphasewordbookmarkByIds(res));
    }
}
