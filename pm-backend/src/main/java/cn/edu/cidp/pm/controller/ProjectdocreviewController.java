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
import cn.edu.cidp.pm.domain.Projectdocreview;
import cn.edu.cidp.pm.service.IProjectdocreviewService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目附件审核Controller
 * 
 * @author william
 * @date 2020-10-10
 */
@RestController
@RequestMapping("/pm/projectdocreview")
public class ProjectdocreviewController extends BaseController
{
    @Autowired
    private IProjectdocreviewService projectdocreviewService;

    /**
     * 查询项目附件审核列表
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectdocreview projectdocreview)
    {
        startPage();
        List<Projectdocreview> list = projectdocreviewService.selectProjectdocreviewList(projectdocreview);
        return getDataTable(list);
    }

    /**
     * 导出项目附件审核列表
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:export')")
    @Log(title = "项目附件审核", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectdocreview projectdocreview)
    {
        List<Projectdocreview> list = projectdocreviewService.selectProjectdocreviewList(projectdocreview);
        ExcelUtil<Projectdocreview> util = new ExcelUtil<Projectdocreview>(Projectdocreview.class);
        return util.exportExcel(list, "projectdocreview");
    }

    /**
     * 获取项目附件审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:query')")
    @GetMapping(value = "/{docId}")
    public AjaxResult getInfo(@PathVariable("docId") Long docId)
    {
        return AjaxResult.success(projectdocreviewService.selectProjectdocreviewById(docId));
    }

    /**
     * 新增项目附件审核
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:add')")
    @Log(title = "项目附件审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectdocreview projectdocreview)
    {
        return toAjax(projectdocreviewService.insertProjectdocreview(projectdocreview));
    }

    /**
     * 修改项目附件审核
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:edit')")
    @Log(title = "项目附件审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectdocreview projectdocreview)
    {
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String userName = currentUser.getUsername();
        projectdocreview.setReviewBy(userName);
        projectdocreview.setReviewTime(new Date());
        return toAjax(projectdocreviewService.updateProjectdocreview(projectdocreview));
    }

    /**
     * 删除项目附件审核
     */
    @PreAuthorize("@ss.hasPermi('pm:projectdocreview:remove')")
    @Log(title = "项目附件审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{docIds}")
    public AjaxResult remove(@PathVariable Long[] docIds)
    {
        return toAjax(projectdocreviewService.deleteProjectdocreviewByIds(docIds));
    }
}
