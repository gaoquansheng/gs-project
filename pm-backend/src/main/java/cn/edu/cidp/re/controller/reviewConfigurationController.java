package cn.edu.cidp.re.controller;

import java.util.List;

import com.aspose.pdf.internal.doc.ml.MlTableConverter;
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
import cn.edu.cidp.re.domain.reviewConfiguration;
import cn.edu.cidp.re.service.IreviewConfigurationService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 评审配置Controller
 * 
 * @author william
 * @date 2021-01-11
 */
@RestController
@RequestMapping("/re/reviewconfiguration")
public class reviewConfigurationController extends BaseController
{
    @Autowired
    private IreviewConfigurationService reviewConfigurationService;

    /**
     * 查询评审配置列表
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:list')")
    @GetMapping("/list")
    public TableDataInfo list(reviewConfiguration reviewConfiguration)
    {
        startPage();
        List<reviewConfiguration> list = reviewConfigurationService.selectreviewConfigurationList(reviewConfiguration);
        return getDataTable(list);
    }

    /**
     * 导出评审配置列表
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:export')")
    @Log(title = "评审配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(reviewConfiguration reviewConfiguration)
    {
        List<reviewConfiguration> list = reviewConfigurationService.selectreviewConfigurationList(reviewConfiguration);
        ExcelUtil<reviewConfiguration> util = new ExcelUtil<reviewConfiguration>(reviewConfiguration.class);
        return util.exportExcel(list, "reviewconfiguration");
    }

    /**
     * 获取评审配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:query')")
    @GetMapping(value = "/{categoryCode}")
    public TableDataInfo getInfo(@PathVariable("categoryCode") String categoryCode)
    {
        return getDataTable(reviewConfigurationService.selectreviewConfigurationById(categoryCode));
    }

    /**
     * 新增评审配置
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:add')")
    @Log(title = "评审配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody reviewConfiguration reviewConfiguration)
    {
        int c = reviewConfigurationService.checkUnique(reviewConfiguration.getCategoryCode(), reviewConfiguration.getPhaseCode());
        if(c > 0){
            return toAjax(reviewConfigurationService.updatereviewConfiguration(reviewConfiguration));
        }
        return toAjax(reviewConfigurationService.insertreviewConfiguration(reviewConfiguration));
    }

    /**
     * 修改评审配置
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:edit')")
    @Log(title = "评审配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody reviewConfiguration reviewConfiguration)
    {
        return toAjax(reviewConfigurationService.updatereviewConfiguration(reviewConfiguration));
    }

    /**
     * 删除评审配置
     */
    @PreAuthorize("@ss.hasPermi('re:reviewconfiguration:remove')")
    @Log(title = "评审配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryCodes}")
    public AjaxResult remove(@PathVariable String[] categoryCodes)
    {
        return toAjax(reviewConfigurationService.deletereviewConfigurationByIds(categoryCodes));
    }
}
