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
import cn.edu.cidp.re.domain.Groupproject;
import cn.edu.cidp.re.service.IGroupprojectService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 分组项目Controller
 * 
 * @author william
 * @date 2021-01-19
 */
@RestController
@RequestMapping("/re/groupproject")
public class GroupprojectController extends BaseController
{
    @Autowired
    private IGroupprojectService groupprojectService;

    /**
     * 查询分组项目列表
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:list')")
    @GetMapping("/list")
    public TableDataInfo list(Groupproject groupproject)
    {
        startPage();
        List<Groupproject> list = groupprojectService.selectGroupprojectList(groupproject);
        return getDataTable(list);
    }

    /**
     * 导出分组项目列表
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:export')")
    @Log(title = "分组项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Groupproject groupproject)
    {
        List<Groupproject> list = groupprojectService.selectGroupprojectList(groupproject);
        ExcelUtil<Groupproject> util = new ExcelUtil<Groupproject>(Groupproject.class);
        return util.exportExcel(list, "groupproject");
    }

    /**
     * 获取分组项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid)
    {
        return AjaxResult.success(groupprojectService.selectGroupprojectById(rid));
    }

    /**
     * 新增分组项目
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:add')")
    @Log(title = "分组项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Groupproject groupproject)
    {
        return toAjax(groupprojectService.insertGroupproject(groupproject));
    }

    /**
     * 修改分组项目
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:edit')")
    @Log(title = "分组项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Groupproject groupproject)
    {
        return toAjax(groupprojectService.updateGroupproject(groupproject));
    }

    /**
     * 删除分组项目
     */
    @PreAuthorize("@ss.hasPermi('re:groupproject:remove')")
    @Log(title = "分组项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids)
    {
        return toAjax(groupprojectService.deleteGroupprojectByIds(rids));
    }
}
