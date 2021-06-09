package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projecttask;

/**
 * 项目任务书Service接口
 * 
 * @author william
 * @date 2020-09-15
 */
public interface IProjecttaskService 
{
    /**
     * 查询项目任务书
     * 
     * @param projectId 项目任务书ID
     * @return 项目任务书
     */
    public Projecttask selectProjecttaskById(Long projectId);

    /**
     * 查询项目任务书列表
     * 
     * @param projecttask 项目任务书
     * @return 项目任务书集合
     */
    public List<Projecttask> selectProjecttaskList(Projecttask projecttask);

    /**
     * 新增项目任务书
     * 
     * @param projecttask 项目任务书
     * @return 结果
     */
    public int insertProjecttask(Projecttask projecttask);

    /**
     * 修改项目任务书
     * 
     * @param projecttask 项目任务书
     * @return 结果
     */
    public int updateProjecttask(Projecttask projecttask);

    /**
     * 批量删除项目任务书
     * 
     * @param projectIds 需要删除的项目任务书ID
     * @return 结果
     */
    public int deleteProjecttaskByIds(Long[] projectIds);

    /**
     * 删除项目任务书信息
     * 
     * @param projectId 项目任务书ID
     * @return 结果
     */
    public int deleteProjecttaskById(Long projectId);
}
