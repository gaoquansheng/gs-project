package cn.edu.cidp.pm.service.impl;

import java.util.List;

import cn.edu.cidp.project.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectorgMapper;
import cn.edu.cidp.pm.domain.Projectorg;
import cn.edu.cidp.pm.service.IProjectorgService;

/**
 * 添加合作单位Service业务层处理
 *
 * @author william
 * @date 2020-09-13
 */
@Service
public class ProjectorgServiceImpl implements IProjectorgService
{
    @Autowired
    private ProjectorgMapper projectorgMapper;

    /**
     * 查询添加合作单位
     *
     * @param rid 添加合作单位ID
     * @return 添加合作单位
     */
    @Override
    public Projectorg selectProjectorgById(Long rid)
    {
        return projectorgMapper.selectProjectorgById(rid);
    }

    /**
     * 查询添加合作单位列表
     *
     * @param projectId 添加合作单位
     * @return 添加合作单位
     */
    @Override
    public List<Projectorg> selectProjectorgList(Long projectId,String phaseCode)
    {
        return projectorgMapper.selectProjectorgList(projectId,phaseCode);
    }

    /**
     * 新增添加合作单位
     *
     * @param projectorg 添加合作单位
     * @return 结果
     */
    @Override
    public int insertProjectorg(Projectorg projectorg)
    {
        return projectorgMapper.insertProjectorg(projectorg);
    }

    /**
     * 修改添加合作单位
     *
     * @param projectorg 添加合作单位
     * @return 结果
     */
    @Override
    public int updateProjectorg(Projectorg projectorg)
    {
        return projectorgMapper.updateProjectorg(projectorg);
    }

    /**
     * 批量删除添加合作单位
     *
     * @param rids 需要删除的添加合作单位ID
     * @return 结果
     */
    @Override
    public int deleteProjectorgByIds(Long[] rids)
    {
        return projectorgMapper.deleteProjectorgByIds(rids);
    }

    /**
     * 删除添加合作单位信息
     *
     * @param rid 添加合作单位ID
     * @return 结果
     */
    @Override
    public int deleteProjectorgById(Long rid)
    {
        return projectorgMapper.deleteProjectorgById(rid);
    }

    @Override
    public int addProjectorg(List<SysDept> sysDept) {
        return projectorgMapper.addProjectorg(sysDept);
    }

    @Override
    public int checkUnique(Projectorg projectorg) {
        return projectorgMapper.checkUnique(projectorg);
    }
}
