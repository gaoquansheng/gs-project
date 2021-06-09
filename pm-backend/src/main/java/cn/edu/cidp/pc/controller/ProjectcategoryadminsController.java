package cn.edu.cidp.pc.controller;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cidp.common.utils.SecurityUtils;
import cn.edu.cidp.framework.security.LoginUser;
import cn.edu.cidp.pc.service.IProjectcategoryService;
import cn.edu.cidp.project.system.domain.SysUser;
import cn.edu.cidp.project.system.service.ISysUserService;
import com.alibaba.fastjson.JSONObject;
import com.sun.jna.platform.win32.Winspool;
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
import cn.edu.cidp.pc.domain.Projectcategoryadmins;
import cn.edu.cidp.pc.service.IProjectcategoryadminsService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 项目类别管理员Controller
 *
 * @author william
 * @date 2020-08-06
 */
@RestController
@RequestMapping("/pc/projectcategoryadmins")
public class ProjectcategoryadminsController extends BaseController
{
    @Autowired
    private IProjectcategoryadminsService projectcategoryadminsService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IProjectcategoryService projectcategoryService;

    /**
     * 查询项目类别管理员列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projectcategoryadmins projectcategoryadmins)
    {
        startPage();
        List<Projectcategoryadmins> list = projectcategoryadminsService.selectProjectcategoryadminsList(projectcategoryadmins);
        return getDataTable(list);
    }

    /**
     * 查询项目类别管理员列表
     */
//    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:list')")
    @GetMapping("/listByAdminUserName")
    public TableDataInfo listByAdminUserName( )
    {
        startPage();
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String adminUserName = currentUser.getUsername();
        List<Projectcategoryadmins> list = projectcategoryadminsService.selectProjectcategoryadminsByAdminUserName(adminUserName);
        return getDataTable(list);
    }

    /**
     * 导出项目类别管理员列表
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:export')")
    @Log(title = "项目类别管理员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Projectcategoryadmins projectcategoryadmins)
    {
        List<Projectcategoryadmins> list = projectcategoryadminsService.selectProjectcategoryadminsList(projectcategoryadmins);
        ExcelUtil<Projectcategoryadmins> util = new ExcelUtil<Projectcategoryadmins>(Projectcategoryadmins.class);
        return util.exportExcel(list, "projectcategoryadmins");
    }

    /**
     * 获取项目类别管理员详细信息
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:query')")
    @GetMapping(value = "/{categoryCode}")
    public AjaxResult getInfo(@PathVariable("categoryCode") String categoryCode)
    {
        return AjaxResult.success(projectcategoryadminsService.selectProjectcategoryadminsById(categoryCode));
    }

    /**
     * 新增项目类别管理员
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:add')")
    @Log(title = "项目类别管理员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JSONObject obj)
    {

        String categoryCode = (String) obj.get("categoryCode");

        String categoryName = (String) obj.get("categoryName");

        List<String> adminUserCodes = (List<String>) obj.get("adminuserCode");
        //强转Long类型，查询user
        List<SysUser> users = sysUserService.selectUserByIds((List<Long>) obj.get("adminuserCode"));
        //判断是否重复添加管理员,如果重复返回错误信息
        int count = projectcategoryadminsService.checkUnique(categoryCode,adminUserCodes);
        if(count > 0){
            return AjaxResult.error("新增失败，"+"请检查是否为 '"+categoryCode+"-"+categoryName+"' 重复添加管理员");
        }
        return toAjax(projectcategoryadminsService.insertProjectcategoryadminsByList(categoryCode,categoryName,users));
    }

    /**
     * 修改项目类别管理员
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:edit')")
    @Log(title = "项目类别管理员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projectcategoryadmins projectcategoryadmins)
    {
        return toAjax(projectcategoryadminsService.updateProjectcategoryadmins(projectcategoryadmins));
    }

    /**
     * 删除项目类别管理员
     */
    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:remove')")
    @Log(title = "项目类别管理员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryCodes}")
    public AjaxResult remove(@PathVariable String[] categoryCodes)
    {

        return toAjax(projectcategoryadminsService.deleteProjectcategoryadminsByIds(categoryCodes));
    }


    @PreAuthorize("@ss.hasPermi('pc:projectcategoryadmins:remove')")
    @Log(title = "项目类别管理员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryCode}/{adminUserCode}")
    public AjaxResult remove(@PathVariable String categoryCode,@PathVariable String[] adminUserCode)
    {

        return toAjax(projectcategoryadminsService.deleteProjectcategoryadminsByIdAndName(categoryCode,adminUserCode));
    }
}
