package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.project.system.domain.SysUser;
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
import cn.edu.cidp.pm.domain.Projectmeber;
import cn.edu.cidp.pm.service.IProjectmeberService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目组成员Controller
 *
 * @author william
 * @date 2020-09-13
 */
@RestController
@RequestMapping("/pm/projectmeber")
public class ProjectmeberController extends BaseController {
    @Autowired
    private IProjectmeberService projectmeberService;

    /**
     * 查询项目组成员列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:list')")
    @GetMapping(value = "/list/{projectId}/{phaseCode}")
    public TableDataInfo list(@PathVariable("projectId") Long projectId, @PathVariable("phaseCode") String phaseCode) {
        startPage();
        List<Projectmeber> list = projectmeberService.selectProjectmeberList(projectId, phaseCode);
        return getDataTable(list);
    }

    /**
     * 新增内部项目组成员
     *
     * @param sysUser
     * @return
     */
    @PostMapping(value = "/add")
    public void addProjectUser(@RequestBody List<SysUser> sysUser) {
        projectmeberService.addProjectmeber(sysUser);
    }

//    /**
//     * 导出项目组成员列表
//     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:export')")
//    @Log(title = "项目组成员", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Projectmeber projectmeber)
//    {
//        List<Projectmeber> list = projectmeberService.selectProjectmeberList(projectmeber);
//        ExcelUtil<Projectmeber> util = new ExcelUtil<Projectmeber>(Projectmeber.class);
//        return util.exportExcel(list, "projectmeber");
//    }

    /**
     * 获取项目组成员详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:query')")
    @GetMapping(value = "/{rid}")
    public AjaxResult getInfo(@PathVariable("rid") Long rid) {
        return AjaxResult.success(projectmeberService.selectProjectmeberById(rid));
    }

    /**
     * 新增项目组成员
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:add')")
    @Log(title = "项目组成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectmeber projectmeber) {
        int count = projectmeberService.checkUnique(projectmeber);
        if (count > 0) {
            return AjaxResult.error(projectmeber.getUserName() + "已经添加");
        } else {
            return toAjax(projectmeberService.insertProjectmeber(projectmeber));
        }
    }

    /**
     * 修改项目组成员
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:edit')")
    @Log(title = "项目组成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectmeber projectmeber) {
        return toAjax(projectmeberService.updateProjectmeber(projectmeber));
    }

    /**
     * 删除项目组成员
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectmeber:remove')")
    @Log(title = "项目组成员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rids}")
    public AjaxResult remove(@PathVariable Long[] rids) {
        return toAjax(projectmeberService.deleteProjectmeberByIds(rids));
    }
}
