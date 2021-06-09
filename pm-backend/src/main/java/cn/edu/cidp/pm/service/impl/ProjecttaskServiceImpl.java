package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjecttaskMapper;
import cn.edu.cidp.pm.domain.Projecttask;
import cn.edu.cidp.pm.service.IProjecttaskService;

/**
 * 项目任务书Service业务层处理
 * 
 * @author william
 * @date 2020-09-15
 */
@Service
public class ProjecttaskServiceImpl implements IProjecttaskService 
{
    @Autowired
    private ProjecttaskMapper projecttaskMapper;

    /**
     * 查询项目任务书
     * 
     * @param projectId 项目任务书ID
     * @return 项目任务书
     */
    @Override
    public Projecttask selectProjecttaskById(Long projectId)
    {
        return projecttaskMapper.selectProjecttaskById(projectId);
    }

    /**
     * 查询项目任务书列表
     * 
     * @param projecttask 项目任务书
     * @return 项目任务书
     */
    @Override
    public List<Projecttask> selectProjecttaskList(Projecttask projecttask)
    {
        return projecttaskMapper.selectProjecttaskList(projecttask);
    }

    /**
     * 新增项目任务书
     * 
     * @param projecttask 项目任务书
     * @return 结果
     */
    @Override
    public int insertProjecttask(Projecttask projecttask)
    {
        return projecttaskMapper.insertProjecttask(projecttask);
    }

    /**
     * 修改项目任务书
     * 
     * @param projecttask 项目任务书
     * @return 结果
     */
    @Override
    public int updateProjecttask(Projecttask projecttask)
    {
        return projecttaskMapper.updateProjecttask(projecttask);
    }

    /**
     * 批量删除项目任务书
     * 
     * @param projectIds 需要删除的项目任务书ID
     * @return 结果
     */
    @Override
    public int deleteProjecttaskByIds(Long[] projectIds)
    {
        return projecttaskMapper.deleteProjecttaskByIds(projectIds);
    }

    /**
     * 删除项目任务书信息
     * 
     * @param projectId 项目任务书ID
     * @return 结果
     */
    @Override
    public int deleteProjecttaskById(Long projectId)
    {
        return projecttaskMapper.deleteProjecttaskById(projectId);
    }
}
