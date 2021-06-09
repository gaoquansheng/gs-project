package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.DepartmentProject;

/**
 * 单位项目Mapper接口
 * 
 * @author william
 * @date 2020-08-27
 */
public interface DepartmentProjectMapper
{
    /**
     * 查询单位项目
     * 
     * @param projectId 单位项目ID
     * @return 单位项目
     */
    public DepartmentProject selectdepartmentProjectById(Long projectId);

    /**
     * 查询单位项目列表
     * 
     * @param departmentProject 单位项目
     * @return 单位项目集合
     */
    public List<DepartmentProject> selectdepartmentProjectList(DepartmentProject departmentProject);

    /**
     * 新增单位项目
     * 
     * @param departmentProject 单位项目
     * @return 结果
     */
    public int insertdepartmentProject(DepartmentProject departmentProject);

    /**
     * 修改单位项目
     * 
     * @param departmentProject 单位项目
     * @return 结果
     */
    public int updatedepartmentProject(DepartmentProject departmentProject);

    /**
     * 删除单位项目
     * 
     * @param projectId 单位项目ID
     * @return 结果
     */
    public int deletedepartmentProjectById(Long projectId);

    /**
     * 批量删除单位项目
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deletedepartmentProjectByIds(Long[] projectIds);
}
