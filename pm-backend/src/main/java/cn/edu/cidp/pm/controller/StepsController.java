package cn.edu.cidp.pm.controller;

import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.pc.domain.Projectcategory;
import cn.edu.cidp.pc.domain.Projectcategoryphasewordbookmark;
import cn.edu.cidp.pc.service.IProjectcategoryService;
import cn.edu.cidp.pc.service.IProjectcategoryphasewordbookmarkService;
import cn.edu.cidp.pm.domain.Steps;
import cn.edu.cidp.pm.domain.WorkTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Controller
 *
 * @author william
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/pm/steps")
public class StepsController extends BaseController {
    @Autowired
    private IProjectcategoryService projectcategoryService;

    @Autowired
    private IProjectcategoryphasewordbookmarkService projectcategoryphasewordbookmarkService;

    /**
     * 查询测试列表
     */
    @PostMapping("/list")
    public List<Steps> list(@RequestBody WorkTips workTips) {
        String categoryCode = workTips.getCategoryCode();
        String phaseCode = workTips.getPhaseCode();
        //两次数据库查询
        Projectcategory projectcategory = projectcategoryService.selectProjectcategoryById(categoryCode);
        List<Projectcategoryphasewordbookmark> projectcategoryhbm = projectcategoryphasewordbookmarkService.selectPhbmByCode(categoryCode, phaseCode);
        //返回给前端的集合信息
        List<Steps> steps = new ArrayList<Steps>();
        //在外边创建对象，节省堆栈内存空间
        Steps stepsObj = null;
        //先添加一个项目基本信息
        stepsObj = new Steps("-1", categoryCode, phaseCode);
        stepsObj.setStartDate(projectcategory.getStartMonth() + "-" + projectcategory.getStartDay());
        stepsObj.setEndDate(projectcategory.getEndMonth() + "-" + projectcategory.getEndDay());
        stepsObj.setStepid("baseInfo");
        stepsObj.setStepName("项目基本信息");
        steps.add(stepsObj);
        //根据项目类别添加流程
//        if (projectcategory.getHasBudgetPlan() == 1) {
//            stepsObj = new Steps("-1", categoryCode, phaseCode);
//            stepsObj.setStepid("budgetPlan");
//            stepsObj.setStepName("项目预算");
//            steps.add(stepsObj);
//        }
        if (projectcategory.getHasBudgetYearplan() == 1) {
            stepsObj = new Steps("-1", categoryCode, phaseCode);
            stepsObj.setStepid("budgetYearPlan");
            stepsObj.setStepName("年度预算");
            steps.add(stepsObj);
        }
        //先不传自筹经费
//        if (projectcategory.getHasSelfFinancing() == 1){
//            stepsObj = new Steps("-1","GS001","1001");
//            stepsObj.setStepName("自筹经费");
//            stepsObj.setSelfFinancing(1);
//            steps.add(stepsObj);
//        }
//        if (projectcategory.getHasOrgs() == 1) {
//            stepsObj = new Steps("-1", categoryCode, phaseCode);
//            stepsObj.setStepid("hasOrgs");
//            stepsObj.setStepName("合作单位");
//            steps.add(stepsObj);
//        }
        if (projectcategory.getHasMembers() == 1) {
            stepsObj = new Steps("-1", categoryCode, phaseCode);
            stepsObj.setStepid("hasMembers");
            stepsObj.setStepName("项目负责人");
            steps.add(stepsObj);
        }

        //根据书签表添加步骤
//        for (int i = 0; i < projectcategoryhbm.size(); i++) {
//            stepsObj = new Steps("-1", categoryCode, phaseCode);
//            stepsObj.setStepid(projectcategoryhbm.get(i).getContentType());
//            stepsObj.setStepName(projectcategoryhbm.get(i).getBookMarkName());
//            stepsObj.setContentType(projectcategoryhbm.get(i).getContentType());
//            stepsObj.setBookmarkCode(projectcategoryhbm.get(i).getBookMarkCode());
//            stepsObj.setBookmarkDesc(projectcategoryhbm.get(i).getBookMarkDesc());
//            steps.add(stepsObj);
//        }
        //最后添加一个预览提交申请书
        stepsObj = new Steps("-1", categoryCode, phaseCode);
        stepsObj.setStepid("application");
        stepsObj.setStepName("提交申请书");
        steps.add(stepsObj);
        return steps;
    }
}
