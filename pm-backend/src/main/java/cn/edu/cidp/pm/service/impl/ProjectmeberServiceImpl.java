package cn.edu.cidp.pm.service.impl;

import java.util.List;

import cn.edu.cidp.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectmeberMapper;
import cn.edu.cidp.pm.domain.Projectmeber;
import cn.edu.cidp.pm.service.IProjectmeberService;

/**
 * 项目组成员Service业务层处理
 *
 * @author william
 * @date 2020-09-13
 */
@Service
public class ProjectmeberServiceImpl implements IProjectmeberService
{
    @Autowired
    private ProjectmeberMapper projectmeberMapper;

    /**
     * 查询项目组成员
     *
     * @param rid 项目组成员ID
     * @return 项目组成员
     */
    @Override
    public Projectmeber selectProjectmeberById(Long rid)
    {
        return projectmeberMapper.selectProjectmeberById(rid);
    }

    /**
     * 查询项目组成员列表
     *
     * @param projectId 项目组成员
     * @return 项目组成员
     */
    @Override
    public List<Projectmeber> selectProjectmeberList(Long projectId,String phaseCode)
    {
        return projectmeberMapper.selectProjectmeberList(projectId,phaseCode);
    }

    /**
     * 新增项目组成员
     *
     * @param projectmeber 项目组成员
     * @return 结果
     */
    @Override
    public int insertProjectmeber(Projectmeber projectmeber)
    {
        return projectmeberMapper.insertProjectmeber(projectmeber);
    }

    /**
     * 修改项目组成员
     *
     * @param projectmeber 项目组成员
     * @return 结果
     */
    @Override
    public int updateProjectmeber(Projectmeber projectmeber)
    {
        return projectmeberMapper.updateProjectmeber(projectmeber);
    }

    /**
     * 批量删除项目组成员
     *
     * @param rids 需要删除的项目组成员ID
     * @return 结果
     */
    @Override
    public int deleteProjectmeberByIds(Long[] rids)
    {
        return projectmeberMapper.deleteProjectmeberByIds(rids);
    }

    /**
     * 删除项目组成员信息
     *
     * @param rid 项目组成员ID
     * @return 结果
     */
    @Override
    public int deleteProjectmeberById(Long rid)
    {
        return projectmeberMapper.deleteProjectmeberById(rid);
    }

    @Override
    public int addProjectmeber(List<SysUser> sysUser) {
        return projectmeberMapper.addProjectmeber(sysUser);
    }

    @Override
    public int checkUnique(Projectmeber projectmeber) {
        return projectmeberMapper.checkUnique(projectmeber);
    }
}
