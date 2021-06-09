package cn.edu.cidp.pm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.edu.cidp.pm.domain.*;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import cn.edu.cidp.pm.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.service.IUnitReviewService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单位项目审核Service业务层处理
 *
 * @author william
 * @date 2020-10-20
 */
@Service
public class UnitReviewServiceImpl implements IUnitReviewService
{
    @Autowired
    private UnitReviewMapper unitReviewMapper;
    @Autowired
    private ProjectorgMapper projectorgMapper;
    @Autowired
    private ProjectmeberMapper projectmeberMapper;
    @Autowired
    private ProjectwordcontentMapper projectwordcontentMapper;
    @Autowired
    private ProjectbudgetMapper projectbudgetMapper;
    @Autowired
    private ProjectyearbudgetplanMapper projectyearbudgetplanMapper;


    /**
     * 查询单位项目审核
     *
     * @param projectId 单位项目审核ID
     * @return 单位项目审核
     */
    @Override
    public UnitReview selectUnitReviewById(Long projectId)
    {
        return unitReviewMapper.selectUnitReviewById(projectId);
    }

    /**
     * 查询单位项目审核列表
     *
     * @param unitReview 单位项目审核
     * @return 单位项目审核
     */
    @Override
    public List<UnitReview> selectUnitReviewList(UnitReview unitReview)
    {
        return unitReviewMapper.selectUnitReviewList(unitReview);
    }

    /**
     * 新增单位项目审核
     *
     * @param unitReview 单位项目审核
     * @return 结果
     */
    @Override
    public int insertUnitReview(UnitReview unitReview)
    {
        return unitReviewMapper.insertUnitReview(unitReview);
    }

    /**
     * 修改单位项目审核
     *
     * @param unitReview 单位项目审核
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUnitReview(UnitReview unitReview)
    {

        String newProjectStatus = unitReview.getProjectStatus();
        UnitReview oldProject = unitReviewMapper.selectUnitReviewById(unitReview.getProjectId());
        String oldProjectStatus = oldProject.getProjectStatus();
        oldProject.setProjectStatus(newProjectStatus);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("1001", "2001","4001"));
        //当老状态为1且新状态为2或者3时候 复制project到projecttack
        if(oldProjectStatus.startsWith("1") && (newProjectStatus.startsWith("2") || newProjectStatus.startsWith("3"))){
            unitReviewMapper.insertProjectTask(oldProject);
            List<Projectorg> projectorgs = projectorgMapper.selectProjectorgList(unitReview.getProjectId(),list.get(0));
            List<Projectmeber> projectmebers = projectmeberMapper.selectProjectmeberList(unitReview.getProjectId(),list.get(0));
            List<Projectwordcontent> projectwordcontents = projectwordcontentMapper.selectProjectwordcontentList(unitReview.getProjectId(),list.get(0));
            List<Projectbudget> projectbudgets = projectbudgetMapper.selectProjectbudgets(unitReview.getProjectId(),list.get(0));
            List<Projectyearbudgetplan> projectyearbudgetplans = projectyearbudgetplanMapper.selectProjectyearbudgetplanList(unitReview.getProjectId(),list.get(0));
            for(Projectorg i : projectorgs){
                i.setPhaseCode(list.get(1));
            }
            for(Projectmeber i: projectmebers){
                i.setPhaseCode(list.get(1));
            }
            for(Projectyearbudgetplan i : projectyearbudgetplans){
                i.setPhaseCode(list.get(1));
            }
            for(Projectbudget i : projectbudgets){
                i.setPhaseCode(list.get(1));
            }
            for(Projectwordcontent i : projectwordcontents){
                i.setPhaseCode(list.get(1));
            }
            if(projectorgs.size()>0){
                projectorgMapper.insertProjectorgList(projectorgs);
            }
            if(projectmebers.size()>0){
                projectmeberMapper.insertProjectmeberList(projectmebers);

            }
            if(projectbudgets.size()>0){
                projectbudgetMapper.insertProjectbudgetList(projectbudgets);
            }
            if(projectwordcontents.size()>0){
                projectwordcontentMapper.insertProjectwordcontentList(projectwordcontents);
            }
            if(projectyearbudgetplans.size()>0){
                projectyearbudgetplanMapper.insertProjectyearbudgetplanList(projectyearbudgetplans);
            }
        //当老状态为2或者3且新状态为4时候 复制project到projectconclusionreport并更新projecttask
        }else if((oldProjectStatus.startsWith("2") || oldProjectStatus.startsWith("3")) && newProjectStatus.startsWith("4")){
            unitReviewMapper.updateProjectTask(oldProject);
            unitReviewMapper.insertProjectConclusionReport(oldProject);
            List<Projectorg> projectorgs = projectorgMapper.selectProjectorgList(unitReview.getProjectId(),list.get(1));
            List<Projectmeber> projectmebers = projectmeberMapper.selectProjectmeberList(unitReview.getProjectId(),list.get(1));
            List<Projectwordcontent> projectwordcontents = projectwordcontentMapper.selectProjectwordcontentList(unitReview.getProjectId(),list.get(1));
            List<Projectbudget> projectbudgets = projectbudgetMapper.selectProjectbudgets(unitReview.getProjectId(),list.get(1));
            List<Projectyearbudgetplan> projectyearbudgetplans = projectyearbudgetplanMapper.selectProjectyearbudgetplanList(unitReview.getProjectId(),list.get(1));
            for(Projectorg i : projectorgs){
                i.setPhaseCode(list.get(2));
            }
            for(Projectmeber i: projectmebers){
                i.setPhaseCode(list.get(2));
            }
            for(Projectyearbudgetplan i : projectyearbudgetplans){
                i.setPhaseCode(list.get(2));
            }
            for(Projectbudget i : projectbudgets){
                i.setPhaseCode(list.get(2));
            }
            for(Projectwordcontent i : projectwordcontents){
                i.setPhaseCode(list.get(2));
            }
            if(projectorgs.size()>0){
                projectorgMapper.insertProjectorgList(projectorgs);
            }
            if(projectmebers.size()>0){
                projectmeberMapper.insertProjectmeberList(projectmebers);

            }
            if(projectbudgets.size()>0){
                projectbudgetMapper.insertProjectbudgetList(projectbudgets);
            }
            if(projectwordcontents.size()>0){
                projectwordcontentMapper.insertProjectwordcontentList(projectwordcontents);
            }
            if(projectyearbudgetplans.size()>0){
                projectyearbudgetplanMapper.insertProjectyearbudgetplanList(projectyearbudgetplans);
            }
        //当老状态为4且新状态为2或者3时候 删除projectconclusionreport 更新projecttask
        }else if(oldProjectStatus.startsWith("4") && (newProjectStatus.startsWith("2") || newProjectStatus.startsWith("3"))){
            unitReviewMapper.deleteProjectConclusionReportById(oldProject.getProjectId());
            unitReviewMapper.updateProjectTask(oldProject);
            //删除projectorg等数据
            projectorgMapper.deleteProjectorgList(unitReview.getProjectId(),list.get(2));
            projectmeberMapper.deleteProjectmeberList(unitReview.getProjectId(),list.get(2));
            projectbudgetMapper.deleteProjectbudgetList(unitReview.getProjectId(),list.get(2));
            projectwordcontentMapper.deleteProjectwordcontentList(unitReview.getProjectId(),list.get(2));
            projectyearbudgetplanMapper.deleteProjectyearbudgetplanList(unitReview.getProjectId(),list.get(2));
        //当老状态为2或者3且新状态为1时候 删除projecttask
        }else if((oldProjectStatus.startsWith("2") || oldProjectStatus.startsWith("3")) && newProjectStatus.startsWith("1")){
            unitReviewMapper.deleteProjectTaskById(oldProject.getProjectId());
            //删除projectorg等数据
            projectorgMapper.deleteProjectorgList(unitReview.getProjectId(),list.get(1));
            projectmeberMapper.deleteProjectmeberList(unitReview.getProjectId(),list.get(1));
            projectbudgetMapper.deleteProjectbudgetList(unitReview.getProjectId(),list.get(1));
            projectwordcontentMapper.deleteProjectwordcontentList(unitReview.getProjectId(),list.get(1));
            projectyearbudgetplanMapper.deleteProjectyearbudgetplanList(unitReview.getProjectId(),list.get(1));
        //当老状态为2或者3且新状态为2或者3时候 更新projecttask
        }else if((oldProjectStatus.startsWith("2") || oldProjectStatus.startsWith("3")) && (newProjectStatus.startsWith("2") || newProjectStatus.startsWith("3"))){
            unitReviewMapper.updateProjectTask(oldProject);
            //当老状态为4且新状态为4时候 更新projecttask projectconclusionreport
        }else if(oldProjectStatus.startsWith("4") && (newProjectStatus.startsWith("4")|| newProjectStatus.equals("1")) ){
            unitReviewMapper.updateProjectTask(oldProject);
            unitReviewMapper.updateProjectConclusionReport(oldProject);
        }
        return unitReviewMapper.updateUnitReview(unitReview);
    }

    /**
     * 批量删除单位项目审核
     *
     * @param projectIds 需要删除的单位项目审核ID
     * @return 结果
     */
    @Override
    public int deleteUnitReviewByIds(Long[] projectIds)
    {
        return unitReviewMapper.deleteUnitReviewByIds(projectIds);
    }

    /**
     * 删除单位项目审核信息
     *
     * @param projectId 单位项目审核ID
     * @return 结果
     */
    @Override
    public int deleteUnitReviewById(Long projectId)
    {
        return unitReviewMapper.deleteUnitReviewById(projectId);
    }
}
