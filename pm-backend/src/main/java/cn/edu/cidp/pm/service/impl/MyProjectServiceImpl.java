package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.MyProjectMapper;
import cn.edu.cidp.pm.domain.MyProject;
import cn.edu.cidp.pm.service.IMyProjectService;

/**
 * 我的项目Service业务层处理
 *
 * @author william
 * @date 2020-08-24
 */
@Service
public class MyProjectServiceImpl implements IMyProjectService
{
    @Autowired
    private MyProjectMapper myProjectMapper;

    /**
     * 查询我的项目
     *
     * @param projectId 我的项目ID
     * @return 我的项目
     */
    @Override
    public MyProject selectmyProjectById(Long projectId)
    {
        return myProjectMapper.selectmyProjectById(projectId);
    }

    /**
     * 查询我的项目列表
     *
     * @param myProject 我的项目
     * @return 我的项目
     */
    @Override
    public List<MyProject> selectmyProjectList(MyProject myProject)
    {
        return myProjectMapper.selectmyProjectList(myProject);
    }

    /**
     * 新增我的项目
     *
     * @param myProject 我的项目
     * @return 结果
     */
    @Override
    public int insertmyProject(MyProject myProject)
    {
        return myProjectMapper.insertmyProject(myProject);
    }

    /**
     * 修改我的项目
     *
     * @param myProject 我的项目
     * @return 结果
     */
    @Override
    public int updatemyProject(MyProject myProject)
    {
        return myProjectMapper.updatemyProject(myProject);
    }

    /**
     * 批量删除我的项目
     *
     * @param projectIds 需要删除的我的项目ID
     * @return 结果
     */
    @Override
    public int deletemyProjectByIds(Long[] projectIds)
    {
        return myProjectMapper.deletemyProjectByIds(projectIds);
    }

    /**
     * 删除我的项目信息
     *
     * @param projectId 我的项目ID
     * @return 结果
     */
    @Override
    public int deletemyProjectById(Long projectId)
    {
        return myProjectMapper.deletemyProjectById(projectId);
    }

    @Override
    public List<MyProject> selectMyList(String userName) {
        return myProjectMapper.selectMyList(userName);
    }
}
