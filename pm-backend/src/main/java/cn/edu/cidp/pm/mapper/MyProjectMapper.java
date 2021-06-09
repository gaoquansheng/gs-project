package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.MyProject;

/**
 * 我的项目Mapper接口
 *
 * @author william
 * @date 2020-08-24
 */
public interface MyProjectMapper
{
    /**
     * 查询我的项目
     *
     * @param projectId 我的项目ID
     * @return 我的项目
     */
    public MyProject selectmyProjectById(Long projectId);

    /**
     * 查询我的项目列表
     *
     * @param myProject 我的项目
     * @return 我的项目集合
     */
    public List<MyProject> selectmyProjectList(MyProject myProject);

    /**
     * 新增我的项目
     *
     * @param myProject 我的项目
     * @return 结果
     */
    public int insertmyProject(MyProject myProject);

    /**
     * 修改我的项目
     *
     * @param myProject 我的项目
     * @return 结果
     */
    public int updatemyProject(MyProject myProject);

    /**
     * 删除我的项目
     *
     * @param projectId 我的项目ID
     * @return 结果
     */
    public int deletemyProjectById(Long projectId);

    /**
     * 批量删除我的项目
     *
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deletemyProjectByIds(Long[] projectIds);

    List<MyProject> selectMyList(String userName);
}
