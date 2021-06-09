package cn.edu.cidp.pm.controller;

import cn.edu.cidp.framework.aspectj.lang.annotation.Log;
import cn.edu.cidp.framework.aspectj.lang.enums.BusinessType;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.framework.web.page.TableDataInfo;
import cn.edu.cidp.pm.domain.Projectwordcontent;
import cn.edu.cidp.pm.service.IProjectwordcontentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 正文内容Controller
 *
 * @author william
 * @date 2020-09-08
 */
@RestController
@RequestMapping("/pm/projectwordcontent")
public class ProjectwordcontentController extends BaseController {
    @Autowired
    private IProjectwordcontentService projectwordcontentService;

    /**
     * 新增正文内容
     */

    @Log(title = "正文内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projectwordcontent projectwordcontent)
    {
//        System.out.println(projectwordcontent);
        int flag = projectwordcontentService.checkUnique(projectwordcontent);
//        System.out.println(flag);
        if (flag == 0){
            return toAjax(projectwordcontentService.insertProjectwordcontent(projectwordcontent));
        }else {
            return toAjax(projectwordcontentService.updateProjectwordcontent(projectwordcontent));
        }
    }

    /**
     * 根据id查询书签内容
     */
//    @PreAuthorize("@ss.hasPermi('pm:projectwordcontent:query')")
    @GetMapping(value = "/{projectId}/{phaseCode}")
    public AjaxResult list(@PathVariable Long projectId,@PathVariable String phaseCode )
    {
        return AjaxResult.success(projectwordcontentService.selectProjectwordcontentList(projectId,phaseCode));
    }
}

