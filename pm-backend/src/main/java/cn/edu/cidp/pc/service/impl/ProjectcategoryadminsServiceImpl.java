package cn.edu.cidp.pc.service.impl;

import java.util.List;

import cn.edu.cidp.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.ProjectcategoryadminsMapper;
import cn.edu.cidp.pc.domain.Projectcategoryadmins;
import cn.edu.cidp.pc.service.IProjectcategoryadminsService;

/**
 * 项目类别管理员Service业务层处理
 * 
 * @author william
 * @date 2020-08-06
 */
@Service
public class ProjectcategoryadminsServiceImpl implements IProjectcategoryadminsService 
{
    @Autowired
    private ProjectcategoryadminsMapper projectcategoryadminsMapper;

    /**
     * 查询项目类别管理员
     * 
     * @param categoryCode 项目类别管理员ID
     * @return 项目类别管理员
     */
    @Override
    public Projectcategoryadmins selectProjectcategoryadminsById(String categoryCode)
    {
        return projectcategoryadminsMapper.selectProjectcategoryadminsById(categoryCode);
    }

    @Override
    public List<Projectcategoryadmins> selectProjectcategoryadminsByAdminUserName(String adminUserName)
    {
        return projectcategoryadminsMapper.selectProjectcategoryadminsByAdminUserName(adminUserName);
    }

    /**
     * 查询项目类别管理员列表
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 项目类别管理员
     */
    @Override
    public List<Projectcategoryadmins> selectProjectcategoryadminsList(Projectcategoryadmins projectcategoryadmins)
    {
        return projectcategoryadminsMapper.selectProjectcategoryadminsList(projectcategoryadmins);
    }

    /**
     * 新增项目类别管理员
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 结果
     */
    @Override
    public int insertProjectcategoryadmins(Projectcategoryadmins projectcategoryadmins)
    {
        return projectcategoryadminsMapper.insertProjectcategoryadmins(projectcategoryadmins);
    }

    /**
     * 修改项目类别管理员
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 结果
     */
    @Override
    public int updateProjectcategoryadmins(Projectcategoryadmins projectcategoryadmins)
    {
        return projectcategoryadminsMapper.updateProjectcategoryadmins(projectcategoryadmins);
    }

    /**
     * 批量删除项目类别管理员
     * 
     * @param categoryCodes 需要删除的项目类别管理员ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryadminsByIds(String[] categoryCodes)
    {
        return projectcategoryadminsMapper.deleteProjectcategoryadminsByIds(categoryCodes);
    }

    /**
     * 删除项目类别管理员信息
     * 
     * @param categoryCode 项目类别管理员ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryadminsById(String categoryCode)
    {
        return projectcategoryadminsMapper.deleteProjectcategoryadminsById(categoryCode);
    }

    @Override
    public int deleteProjectcategoryadminsByIdAndName(String categoryCode, String[] adminUserCode) {
        return projectcategoryadminsMapper.deleteProjectcategoryadminsByIdAndName(categoryCode,adminUserCode);
    }

    @Override
    public int insertProjectcategoryadminsByList(String categoryCode, String categoryName, List<SysUser> users) {
        return projectcategoryadminsMapper.insertProjectcategoryadminsByList(categoryCode,categoryName,users);
    }

    @Override
    public int checkUnique(String categoryCode, List<String> adminUserCodes) {
        return projectcategoryadminsMapper.checkUnique(categoryCode,adminUserCodes);
    }
}
