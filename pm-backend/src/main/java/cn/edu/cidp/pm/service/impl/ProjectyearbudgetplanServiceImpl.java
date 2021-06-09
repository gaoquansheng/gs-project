package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectyearbudgetplanMapper;
import cn.edu.cidp.pm.domain.Projectyearbudgetplan;
import cn.edu.cidp.pm.service.IProjectyearbudgetplanService;

/**
 * 项目年度预算Service业务层处理
 *
 * @author william
 * @date 2020-09-13
 */
@Service
public class ProjectyearbudgetplanServiceImpl implements IProjectyearbudgetplanService
{
    @Autowired
    private ProjectyearbudgetplanMapper projectyearbudgetplanMapper;

    /**
     * 查询项目年度预算
     *
     * @param rid 项目年度预算ID
     * @return 项目年度预算
     */
    @Override
    public Projectyearbudgetplan selectProjectyearbudgetplanById(Long rid)
    {
        return projectyearbudgetplanMapper.selectProjectyearbudgetplanById(rid);
    }

    /**
     * 查询项目年度预算列表
     *
     * @param projectId 项目年度预算
     * @return 项目年度预算
     */
    @Override
    public List<Projectyearbudgetplan> selectProjectyearbudgetplanList(Long projectId,String phaseCode)
    {
        return projectyearbudgetplanMapper.selectProjectyearbudgetplanList(projectId,phaseCode);
    }

    /**
     * 新增项目年度预算
     *
     * @param projectyearbudgetplan 项目年度预算
     * @return 结果
     */
    @Override
    public int insertProjectyearbudgetplan(Projectyearbudgetplan projectyearbudgetplan)
    {
        return projectyearbudgetplanMapper.insertProjectyearbudgetplan(projectyearbudgetplan);
    }

    /**
     * 修改项目年度预算
     *
     * @param projectyearbudgetplan 项目年度预算
     * @return 结果
     */
    @Override
    public int updateProjectyearbudgetplan(Projectyearbudgetplan projectyearbudgetplan)
    {
        return projectyearbudgetplanMapper.updateProjectyearbudgetplan(projectyearbudgetplan);
    }

    /**
     * 批量删除项目年度预算
     *
     * @param rids 需要删除的项目年度预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectyearbudgetplanByIds(Long[] rids)
    {
        return projectyearbudgetplanMapper.deleteProjectyearbudgetplanByIds(rids);
    }

    /**
     * 删除项目年度预算信息
     *
     * @param rid 项目年度预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectyearbudgetplanById(Long rid)
    {
        return projectyearbudgetplanMapper.deleteProjectyearbudgetplanById(rid);
    }

    @Override
    public int checkUnique(Long year,Long projectId,String phaseCode) {
        return projectyearbudgetplanMapper.checkUnique(year,projectId,phaseCode);
    }
}
