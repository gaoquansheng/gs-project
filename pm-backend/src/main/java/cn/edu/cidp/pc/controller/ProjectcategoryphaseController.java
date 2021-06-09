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
import cn.edu.cidp.pc.domain.Projectcategoryphase;
import cn.edu.cidp.pc.service.IProjectcategoryphaseService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目类别流程配置Controller
 *
 * @author william
 * @date 2020-08-10
 */
@RestController
@RequestMapping("/pc/projectcategoryphase")
public class ProjectcategoryphaseController extends BaseController
{
    @Autowired
    private IProjectcategoryphaseService projectcategoryphaseService;

    /**
     * 查询项目类别流程配置列表
     */
//    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphase:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectcategoryphase projectcategoryphase)
    {
        startPage();
        List<Projectcategoryphase> list = projectcategoryphaseService.selectProjectcategoryphaseList(projectcategoryphase);
        return getDataTable(list);
    }


    /**
     * 导出项目类别流程配置列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphase:export')")
    @Log(title = "项目类别流程配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategoryphase projectcategoryphase)
    {
        List<Projectcategoryphase> list = projectcategoryphaseService.selectProjectcategoryphaseList(projectcategoryphase);
        ExcelUtil<Projectcategoryphase> util = new ExcelUtil<Projectcategoryphase>(Projectcategoryphase.class);
        return util.exportExcel(list, "projectcategoryphase");
    }


    /**
     * 新增项目类别流程配置
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphase:add')")
    @Log(title = "项目类别流程配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectcategoryphase projectcategoryphase)
    {
        return toAjax(projectcategoryphaseService.insertProjectcategoryphase(projectcategoryphase));
    }

    /**
     * 修改项目类别流程配置
     * 这里接收一个List<></>
     *
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphase:edit')")
    @Log(title = "项目类别流程配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<Projectcategoryphase> projectcategoryphase)
    {
        System.out.println(projectcategoryphase);
       return toAjax(projectcategoryphaseService.updateProjectcategoryphase(projectcategoryphase));
    }

    /**
     * 删除项目类别流程配置
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryphase:remove')")
    @Log(title = "项目类别流程配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryCodes}")
    public AjaxResult remove(@PathVariable String[] categoryCodes)
    {
        return toAjax(projectcategoryphaseService.deleteProjectcategoryphaseByIds(categoryCodes));
    }
}
