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
import cn.edu.cidp.pm.domain.WorkTips;
import cn.edu.cidp.pm.service.IWorkTipsService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 工作提示Controller
 *
 * @author william
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/pm/worktips")
public class WorkTipsController extends BaseController
{
    @Autowired
    private IWorkTipsService workTipsService;

    /**
     * 查询工作提示列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:worktips:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        LoginUser currentUser = SecurityUtils.getLoginUser();
        // 获取当前的用户名称
        String userName = currentUser.getUsername();
        List<WorkTips> list = workTipsService.selectWorkTipsList(userName);
        return getDataTable(list);
    }


}
