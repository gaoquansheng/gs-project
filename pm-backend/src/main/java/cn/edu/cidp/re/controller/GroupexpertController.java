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
import cn.edu.cidp.re.domain.Groupexpert;
import cn.edu.cidp.re.service.IGroupexpertService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 分配专家Controller
 * 
 * @author william
 * @date 2021-01-19
 */
@RestController
@RequestMapping("/re/groupexpert")
public class GroupexpertController extends BaseController
{
    @Autowired
    private IGroupexpertService groupexpertService;

    /**
     * 查询分配专家列表
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:list')")
    @GetMapping("/list")
    public TableDataInfo list(Groupexpert groupexpert)
    {
        startPage();
        List<Groupexpert> list = groupexpertService.selectGroupexpertList(groupexpert);
        return getDataTable(list);
    }

    /**
     * 导出分配专家列表
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:export')")
    @Log(title = "分配专家", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Groupexpert groupexpert)
    {
        List<Groupexpert> list = groupexpertService.selectGroupexpertList(groupexpert);
        ExcelUtil<Groupexpert> util = new ExcelUtil<Groupexpert>(Groupexpert.class);
        return util.exportExcel(list, "groupexpert");
    }

    /**
     * 获取分配专家详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid)
    {
        return AjaxResult.success(groupexpertService.selectGroupexpertById(rid));
    }

    /**
     * 新增分配专家
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:add')")
    @Log(title = "分配专家", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Groupexpert groupexpert)
    {
        return toAjax(groupexpertService.insertGroupexpert(groupexpert));
    }

    /**
     * 修改分配专家
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:edit')")
    @Log(title = "分配专家", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Groupexpert groupexpert)
    {
        return toAjax(groupexpertService.updateGroupexpert(groupexpert));
    }

    /**
     * 删除分配专家
     */
    @PreAuthorize("@ss.hasPermi('re:groupexpert:remove')")
    @Log(title = "分配专家", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids)
    {
        return toAjax(groupexpertService.deleteGroupexpertByIds(rids));
    }
}
