package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectdocreview;

/**
 * 项目附件审核Mapper接口
 * 
 * @author william
 * @date 2020-10-10
 */
public interface ProjectdocreviewMapper 
{
    /**
     * 查询项目附件审核
     * 
     * @param docId 项目附件审核ID
     * @return 项目附件审核
     */
    public Projectdocreview selectProjectdocreviewById(Long docId);

    /**
     * 查询项目附件审核列表
     * 
     * @param projectdocreview 项目附件审核
     * @return 项目附件审核集合
     */
    public List<Projectdocreview> selectProjectdocreviewList(Projectdocreview projectdocreview);

    /**
     * 新增项目附件审核
     * 
     * @param projectdocreview 项目附件审核
     * @return 结果
     */
    public int insertProjectdocreview(Projectdocreview projectdocreview);

    /**
     * 修改项目附件审核
     * 
     * @param projectdocreview 项目附件审核
     * @return 结果
     */
    public int updateProjectdocreview(Projectdocreview projectdocreview);

    /**
     * 删除项目附件审核
     * 
     * @param docId 项目附件审核ID
     * @return 结果
     */
    public int deleteProjectdocreviewById(Long docId);

    /**
     * 批量删除项目附件审核
     * 
     * @param docIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectdocreviewByIds(Long[] docIds);
}
