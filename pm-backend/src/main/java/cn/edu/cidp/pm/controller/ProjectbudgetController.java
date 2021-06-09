package cn.edu.cidp.pm.controller;

import java.util.List;

import cn.edu.cidp.pc.domain.Projectcategorybudgetsubject;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import org.apache.ibatis.annotations.Param;
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
import cn.edu.cidp.pm.domain.Projectbudget;
import cn.edu.cidp.pm.service.IProjectbudgetService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;
/**
 * @ClassNameProjectbudgetController
 * @Description
 * @Author
 * @Date2020/9/1/001 15:09
 * @Version V1.0
 **/
@RestController
@RequestMapping("/pm/projectbudget")
public class ProjectbudgetController extends BaseController {

    @Autowired
    private IProjectbudgetService projectbudgetService;

    /**
     * 查询projectBudget列表
     */
    @GetMapping("/list/{phaseCode}/{projectId}/{categoryCode}")
    public AjaxResult list(@PathVariable("phaseCode") String phaseCode,@PathVariable("projectId") Long projectId,@PathVariable("categoryCode") String categoryCode)
    {
        List<ProjectbudgetVo> list = projectbudgetService.selectProjectbudgetList(phaseCode,projectId,categoryCode);
        return AjaxResult.success(list);
    }
    /**
     * 新增projectBudget
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody List<ProjectbudgetVo> projectbudgetVo)
    {
        return toAjax(projectbudgetService.addProjectbudget(projectbudgetVo));
    }
    /**
     * 更新projectBudget
     */

    @PostMapping("/update")
    public  AjaxResult update(@RequestBody List<ProjectbudgetVo> projectbudgetVo)
    {
//        System.out.println(projectbudgetVo.get(0));
        projectbudgetService.deleteProjectbudget(projectbudgetVo.get(0).getProjectId(),projectbudgetVo.get(0).getPhaseCode());
        return toAjax(projectbudgetService.addProjectbudget(projectbudgetVo));
    }
}
