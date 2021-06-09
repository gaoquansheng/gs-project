package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectconclusionreportMapper;
import cn.edu.cidp.pm.domain.Projectconclusionreport;
import cn.edu.cidp.pm.service.IProjectconclusionreportService;

/**
 * 项目结题报告Service业务层处理
 * 
 * @author william
 * @date 2020-09-15
 */
@Service
public class ProjectconclusionreportServiceImpl implements IProjectconclusionreportService 
{
    @Autowired
    private ProjectconclusionreportMapper projectconclusionreportMapper;

    /**
     * 查询项目结题报告
     * 
     * @param projectId 项目结题报告ID
     * @return 项目结题报告
     */
    @Override
    public Projectconclusionreport selectProjectconclusionreportById(Long projectId)
    {
        return projectconclusionreportMapper.selectProjectconclusionreportById(projectId);
    }

    /**
     * 查询项目结题报告列表
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 项目结题报告
     */
    @Override
    public List<Projectconclusionreport> selectProjectconclusionreportList(Projectconclusionreport projectconclusionreport)
    {
        return projectconclusionreportMapper.selectProjectconclusionreportList(projectconclusionreport);
    }

    /**
     * 新增项目结题报告
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 结果
     */
    @Override
    public int insertProjectconclusionreport(Projectconclusionreport projectconclusionreport)
    {
        return projectconclusionreportMapper.insertProjectconclusionreport(projectconclusionreport);
    }

    /**
     * 修改项目结题报告
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 结果
     */
    @Override
    public int updateProjectconclusionreport(Projectconclusionreport projectconclusionreport)
    {
        return projectconclusionreportMapper.updateProjectconclusionreport(projectconclusionreport);
    }

    /**
     * 批量删除项目结题报告
     * 
     * @param projectIds 需要删除的项目结题报告ID
     * @return 结果
     */
    @Override
    public int deleteProjectconclusionreportByIds(Long[] projectIds)
    {
        return projectconclusionreportMapper.deleteProjectconclusionreportByIds(projectIds);
    }

    /**
     * 删除项目结题报告信息
     * 
     * @param projectId 项目结题报告ID
     * @return 结果
     */
    @Override
    public int deleteProjectconclusionreportById(Long projectId)
    {
        return projectconclusionreportMapper.deleteProjectconclusionreportById(projectId);
    }
}
