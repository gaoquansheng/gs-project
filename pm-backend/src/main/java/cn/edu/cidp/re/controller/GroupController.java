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
import cn.edu.cidp.re.domain.Group;
import cn.edu.cidp.re.service.IGroupService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 分组信息Controller
 * 
 * @author william
 * @date 2021-01-19
 */
@RestController
@RequestMapping("/re/group")
public class GroupController extends BaseController
{
    @Autowired
    private IGroupService groupService;

    /**
     * 查询分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('re:group:list')")
    @GetMapping("/list")
    public void list(Group group)
    {
        System.out.println("aaaaa");
//        startPage();
//        List<Group> list = groupService.selectGroupList(group);
//        return getDataTable(list);
    }

    /**
     * 导出分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('re:group:export')")
    @Log(title = "分组信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Group group)
    {
        List<Group> list = groupService.selectGroupList(group);
        ExcelUtil<Group> util = new ExcelUtil<Group>(Group.class);
        return util.exportExcel(list, "group");
    }

    /**
     * 获取分组信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('re:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return AjaxResult.success(groupService.selectGroupById(groupId));
    }

    /**
     * 新增分组信息
     */
    @PreAuthorize("@ss.hasPermi('re:group:add')")
    @Log(title = "分组信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Group group)
    {
        return toAjax(groupService.insertGroup(group));
    }

    /**
     * 修改分组信息
     */
    @PreAuthorize("@ss.hasPermi('re:group:edit')")
    @Log(title = "分组信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Group group)
    {
        return toAjax(groupService.updateGroup(group));
    }

    /**
     * 删除分组信息
     */
    @PreAuthorize("@ss.hasPermi('re:group:remove')")
    @Log(title = "分组信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(groupService.deleteGroupByIds(groupIds));
    }
}
