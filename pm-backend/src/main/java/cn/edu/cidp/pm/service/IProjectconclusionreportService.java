package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectconclusionreport;

/**
 * 项目结题报告Service接口
 * 
 * @author william
 * @date 2020-09-15
 */
public interface IProjectconclusionreportService 
{
    /**
     * 查询项目结题报告
     * 
     * @param projectId 项目结题报告ID
     * @return 项目结题报告
     */
    public Projectconclusionreport selectProjectconclusionreportById(Long projectId);

    /**
     * 查询项目结题报告列表
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 项目结题报告集合
     */
    public List<Projectconclusionreport> selectProjectconclusionreportList(Projectconclusionreport projectconclusionreport);

    /**
     * 新增项目结题报告
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 结果
     */
    public int insertProjectconclusionreport(Projectconclusionreport projectconclusionreport);

    /**
     * 修改项目结题报告
     * 
     * @param projectconclusionreport 项目结题报告
     * @return 结果
     */
    public int updateProjectconclusionreport(Projectconclusionreport projectconclusionreport);

    /**
     * 批量删除项目结题报告
     * 
     * @param projectIds 需要删除的项目结题报告ID
     * @return 结果
     */
    public int deleteProjectconclusionreportByIds(Long[] projectIds);

    /**
     * 删除项目结题报告信息
     * 
     * @param projectId 项目结题报告ID
     * @return 结果
     */
    public int deleteProjectconclusionreportById(Long projectId);
}
