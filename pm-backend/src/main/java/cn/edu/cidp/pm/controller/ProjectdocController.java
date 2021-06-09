package cn.edu.cidp.pm.controller;

import java.util.Date;
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
import cn.edu.cidp.pm.domain.Projectdoc;
import cn.edu.cidp.pm.service.IProjectdocService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 上传项目附件Controller
 *
 * @author william
 * @date 2020-09-14
 */
@RestController
@RequestMapping("/pm/projectdoc")
public class ProjectdocController extends BaseController
{
    @Autowired
    private IProjectdocService projectdocService;

    /**
     * 查询上传项目附件列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectdoc projectdoc)
    {
        startPage();
        List<Projectdoc> list = projectdocService.selectProjectdocList(projectdoc);
        return getDataTable(list);
    }

    /**
     * 导出上传项目附件列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:export')")
    @Log(title = "上传项目附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectdoc projectdoc)
    {
        List<Projectdoc> list = projectdocService.selectProjectdocList(projectdoc);
        ExcelUtil<Projectdoc> util = new ExcelUtil<Projectdoc>(Projectdoc.class);
        return util.exportExcel(list, "projectdoc");
    }

    /**
     * 获取上传项目附件详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:query')")
    @GetMapping(value = "/{docId}")
    public AjaxResult getInfo(@PathVariable("docId") Long docId)
    {
        return AjaxResult.success(projectdocService.selectProjectdocById(docId));
    }

    /**
     * 新增上传项目附件
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:add')")
    @Log(title = "上传项目附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectdoc projectdoc)
    {
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String userName = currentUser.getUsername();
        projectdoc.setUploadBy(userName);
        projectdoc.setUploadTime(new Date());
        return toAjax(projectdocService.insertProjectdoc(projectdoc));
    }

    /**
     * 修改上传项目附件
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:edit')")
    @Log(title = "上传项目附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectdoc projectdoc)
    {
        return toAjax(projectdocService.updateProjectdoc(projectdoc));
    }

    /**
     * 删除上传项目附件
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectdoc:remove')")
    @Log(title = "上传项目附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{docIds}")
    public AjaxResult remove(@PathVariable Long[] docIds)
    {
        return toAjax(projectdocService.deleteProjectdocByIds(docIds));
    }
}
