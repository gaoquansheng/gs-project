package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.common.utils.SecurityUtils;
import cn.edu.cidp.framework.security.LoginUser;
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
import cn.edu.cidp.pm.domain.MyProject;
import cn.edu.cidp.pm.service.IMyProjectService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 我的项目Controller
 *
 * @author william
 * @date 2020-08-24
 */
@RestController
@RequestMapping("/pm/myProject")
public class MyProjectController extends BaseController
{
    @Autowired
    private IMyProjectService myProjectService;

    /**
     * 查询我的项目列表
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:list')")
    @GetMapping("/list")
    public TableDataInfo list(MyProject myProject)
    {
        startPage();
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String userName = currentUser.getUsername();
        myProject.setUserName(userName);
        List<MyProject> list = myProjectService.selectmyProjectList(myProject);
        return getDataTable(list);
    }


    /**
     * 查询我的项目列表
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:list')")
    @GetMapping("/myList")
    public TableDataInfo myList( )
    {
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String userName = currentUser.getUsername();
        List<MyProject> list = myProjectService.selectMyList(userName);
        return getDataTable(list);
    }

    /**
     * 导出我的项目列表
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:export')")
    @Log(title = "我的项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MyProject myProject)
    {
        List<MyProject> list = myProjectService.selectmyProjectList(myProject);
        ExcelUtil<MyProject> util = new ExcelUtil<MyProject>(MyProject.class);
        return util.exportExcel(list, "myProject");
    }

    /**
     * 获取我的项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(myProjectService.selectmyProjectById(projectId));
    }

    /**
     * 新增我的项目
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:add')")
    @Log(title = "我的项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyProject myProject)
    {
        return toAjax(myProjectService.insertmyProject(myProject));
    }

    /**
     * 修改我的项目
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:edit')")
    @Log(title = "我的项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyProject myProject)
    {
        return toAjax(myProjectService.updatemyProject(myProject));
    }

    /**
     * 删除我的项目
     */
    @PreAuthorize("@ss.hasPermi('pm:myProject:remove')")
    @Log(title = "我的项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(myProjectService.deletemyProjectByIds(projectIds));
    }
}
