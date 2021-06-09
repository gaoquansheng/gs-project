package cn.edu.cidp.pm.controller;

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
import cn.edu.cidp.pm.domain.Expert;
import cn.edu.cidp.pm.service.IExpertService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 专家Controller
 * 
 * @author william
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/pm/expert")
public class ExpertController extends BaseController
{
    @Autowired
    private IExpertService expertService;

    /**
     * 查询专家列表
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:list')")
    @GetMapping("/list")
    public TableDataInfo list(Expert expert)
    {
        startPage();
        List<Expert> list = expertService.selectExpertList(expert);
        return getDataTable(list);
    }

    /**
     * 导出专家列表
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:export')")
    @Log(title = "专家", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Expert expert)
    {
        List<Expert> list = expertService.selectExpertList(expert);
        ExcelUtil<Expert> util = new ExcelUtil<Expert>(Expert.class);
        return util.exportExcel(list, "expert");
    }

    /**
     * 获取专家详细信息
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:query')")
    @GetMapping(value = "/{expertid}")
    public AjaxResult getInfo(@PathVariable("expertid") Long expertid)
    {
        return AjaxResult.success(expertService.selectExpertById(expertid));
    }

    /**
     * 新增专家
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:add')")
    @Log(title = "专家", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Expert expert)
    {
        return toAjax(expertService.insertExpert(expert));
    }

    /**
     * 修改专家
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:edit')")
    @Log(title = "专家", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Expert expert)
    {
        return toAjax(expertService.updateExpert(expert));
    }

    /**
     * 删除专家
     */
    @PreAuthorize("@ss.hasPermi('pm:expert:remove')")
    @Log(title = "专家", businessType = BusinessType.DELETE)
	@DeleteMapping("/{expertids}")
    public AjaxResult remove(@PathVariable Long[] expertids)
    {
        return toAjax(expertService.deleteExpertByIds(expertids));
    }
}
