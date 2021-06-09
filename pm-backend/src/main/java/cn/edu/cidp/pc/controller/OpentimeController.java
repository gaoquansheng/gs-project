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
import cn.edu.cidp.pc.domain.Opentime;
import cn.edu.cidp.pc.service.IOpentimeService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目起止时间Controller
 * 
 * @author william
 * @date 2020-08-11
 */
@RestController
@RequestMapping("/pc/opentime")
public class OpentimeController extends BaseController
{
    @Autowired
    private IOpentimeService opentimeService;

    /**
     * 查询项目起止时间列表
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:list')")
    @GetMapping("/list")
    public TableDataInfo list(Opentime opentime)
    {
        startPage();
        List<Opentime> list = opentimeService.selectOpentimeList(opentime);
        return getDataTable(list);
    }

    /**
     * 导出项目起止时间列表
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:export')")
    @Log(title = "项目起止时间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Opentime opentime)
    {
        List<Opentime> list = opentimeService.selectOpentimeList(opentime);
        ExcelUtil<Opentime> util = new ExcelUtil<Opentime>(Opentime.class);
        return util.exportExcel(list, "opentime");
    }

    /**
     * 获取项目起止时间详细信息
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:query')")
    @GetMapping(value = "/{rId}")
    public AjaxResult getInfo(@PathVariable("rId") Long rId)
    {
        return AjaxResult.success(opentimeService.selectOpentimeById(rId));
    }

    /**
     * 新增项目起止时间
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:add')")
    @Log(title = "项目起止时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Opentime opentime)
    {
        System.out.println(opentime);
        return toAjax(opentimeService.insertOpentime(opentime));
    }

    /**
     * 修改项目起止时间
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:edit')")
    @Log(title = "项目起止时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Opentime opentime)
    {
        System.out.println(opentime);
        return toAjax(opentimeService.updateOpentime(opentime));
    }

    /**
     * 删除项目起止时间
     */
    @PreAuthorize("@ss.hasPermi('pc:opentime:remove')")
    @Log(title = "项目起止时间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rIds}")
    public AjaxResult remove(@PathVariable Long[] rIds)
    {
        return toAjax(opentimeService.deleteOpentimeByIds(rIds));
    }
}
