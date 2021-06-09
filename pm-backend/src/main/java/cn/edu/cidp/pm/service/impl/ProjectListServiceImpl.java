package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectListMapper;
import cn.edu.cidp.pm.domain.ProjectList;
import cn.edu.cidp.pm.service.IProjectListService;

/**
 * 项目列表Service业务层处理
 * 
 * @author william
 * @date 2020-08-27
 */
@Service
public class ProjectListServiceImpl implements IProjectListService
{
    @Autowired
    private ProjectListMapper projectListMapper;

    /**
     * 查询项目列表
     * 
     * @param projectId 项目列表ID
     * @return 项目列表
     */
    @Override
    public ProjectList selectprojectListById(Long projectId)
    {
        return projectListMapper.selectprojectListById(projectId);
    }

    /**
     * 查询项目列表列表
     * 
     * @param projectList 项目列表
     * @return 项目列表
     */
    @Override
    public List<ProjectList> selectprojectListList(ProjectList projectList)
    {
        return projectListMapper.selectprojectListList(projectList);
    }

    /**
     * 新增项目列表
     * 
     * @param projectList 项目列表
     * @return 结果
     */
    @Override
    public int insertprojectList(ProjectList projectList)
    {
        projectListMapper.insertprojectList(projectList);
        return projectList.getProjectId();
    }

    /**
     * 修改项目列表
     * 
     * @param projectList 项目列表
     * @return 结果
     */
    @Override
    public int updateprojectList(ProjectList projectList)
    {
        return projectListMapper.updateprojectList(projectList);
    }

    /**
     * 批量删除项目列表
     * 
     * @param projectIds 需要删除的项目列表ID
     * @return 结果
     */
    @Override
    public int deleteprojectListByIds(Long[] projectIds)
    {
        return projectListMapper.deleteprojectListByIds(projectIds);
    }

    /**
     * 删除项目列表信息
     * 
     * @param projectId 项目列表ID
     * @return 结果
     */
    @Override
    public int deleteprojectListById(Long projectId)
    {
        return projectListMapper.deleteprojectListById(projectId);
    }
}
