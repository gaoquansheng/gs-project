package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectconclusionreport;

/**
 * 项目结题报告Mapper接口
 * 
 * @author william
 * @date 2020-09-15
 */
public interface ProjectconclusionreportMapper 
{
    /**
     * 查询项目结题报告
     * 
     * @param projectld 项目结题报告ID
     * @return 项目结题报告
     */
    public Projectconclusionreport selectProjectconclusionreportById(Long projectld);

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
     * 删除项目结题报告
     * 
     * @param projectld 项目结题报告ID
     * @return 结果
     */
    public int deleteProjectconclusionreportById(Long projectld);

    /**
     * 批量删除项目结题报告
     * 
     * @param projectlds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectconclusionreportByIds(Long[] projectlds);
}
