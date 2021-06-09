package cn.edu.cidp.pm.service.impl;

import java.util.List;

import cn.edu.cidp.framework.security.LoginUser;
import cn.edu.cidp.framework.security.service.TokenService;
import cn.edu.cidp.project.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.DepartmentProjectMapper;
import cn.edu.cidp.pm.domain.DepartmentProject;
import cn.edu.cidp.pm.service.IDepartmentProjectService;

import javax.servlet.http.HttpServletRequest;

/**
 * 单位项目Service业务层处理
 *
 * @author william
 * @date 2020-08-27
 */
@Service
public class DepartmentProjectServiceImpl implements IDepartmentProjectService
{
    @Autowired
    private DepartmentProjectMapper departmentProjectMapper;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 查询单位项目
     *
     * @param projectId 单位项目ID
     * @return 单位项目
     */
    @Override
    public DepartmentProject selectdepartmentProjectById(Long projectId)
    {
        return departmentProjectMapper.selectdepartmentProjectById(projectId);
    }

    /**
     * 查询单位项目列表
     *
     * @param departmentProject 单位项目
     * @return 单位项目
     */
    @Override
    public List<DepartmentProject> selectdepartmentProjectList(DepartmentProject departmentProject)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        String deptId = String.valueOf(loginUser.getUser().getDeptId());
        departmentProject.setProxyOrgCode(deptId);
        return departmentProjectMapper.selectdepartmentProjectList(departmentProject);
    }

    /**
     * 新增单位项目
     *
     * @param departmentProject 单位项目
     * @return 结果
     */
    @Override
    public int insertdepartmentProject(DepartmentProject departmentProject)
    {
        return departmentProjectMapper.insertdepartmentProject(departmentProject);
    }

    /**
     * 修改单位项目
     *
     * @param departmentProject 单位项目
     * @return 结果
     */
    @Override
    public int updatedepartmentProject(DepartmentProject departmentProject)
    {
        return departmentProjectMapper.updatedepartmentProject(departmentProject);
    }

    /**
     * 批量删除单位项目
     *
     * @param projectIds 需要删除的单位项目ID
     * @return 结果
     */
    @Override
    public int deletedepartmentProjectByIds(Long[] projectIds)
    {
        return departmentProjectMapper.deletedepartmentProjectByIds(projectIds);
    }

    /**
     * 删除单位项目信息
     *
     * @param projectId 单位项目ID
     * @return 结果
     */
    @Override
    public int deletedepartmentProjectById(Long projectId)
    {
        return departmentProjectMapper.deletedepartmentProjectById(projectId);
    }
}
