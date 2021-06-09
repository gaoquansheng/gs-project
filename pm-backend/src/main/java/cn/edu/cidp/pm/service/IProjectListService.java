package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.ProjectList;

/**
 * 项目列表Service接口
 * 
 * @author william
 * @date 2020-08-27
 */
public interface IProjectListService
{
    /**
     * 查询项目列表
     * 
     * @param projectId 项目列表ID
     * @return 项目列表
     */
    public ProjectList selectprojectListById(Long projectId);

    /**
     * 查询项目列表列表
     * 
     * @param projectList 项目列表
     * @return 项目列表集合
     */
    public List<ProjectList> selectprojectListList(ProjectList projectList);

    /**
     * 新增项目列表
     * 
     * @param projectList 项目列表
     * @return 结果
     */
    public int insertprojectList(ProjectList projectList);

    /**
     * 修改项目列表
     * 
     * @param projectList 项目列表
     * @return 结果
     */
    public int updateprojectList(ProjectList projectList);

    /**
     * 批量删除项目列表
     * 
     * @param projectIds 需要删除的项目列表ID
     * @return 结果
     */
    public int deleteprojectListByIds(Long[] projectIds);

    /**
     * 删除项目列表信息
     * 
     * @param projectId 项目列表ID
     * @return 结果
     */
    public int deleteprojectListById(Long projectId);
}
