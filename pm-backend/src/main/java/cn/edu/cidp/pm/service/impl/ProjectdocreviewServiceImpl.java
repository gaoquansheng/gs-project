package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectdocreviewMapper;
import cn.edu.cidp.pm.domain.Projectdocreview;
import cn.edu.cidp.pm.service.IProjectdocreviewService;

/**
 * 项目附件审核Service业务层处理
 * 
 * @author william
 * @date 2020-10-10
 */
@Service
public class ProjectdocreviewServiceImpl implements IProjectdocreviewService 
{
    @Autowired
    private ProjectdocreviewMapper projectdocreviewMapper;

    /**
     * 查询项目附件审核
     * 
     * @param docId 项目附件审核ID
     * @return 项目附件审核
     */
    @Override
    public Projectdocreview selectProjectdocreviewById(Long docId)
    {
        return projectdocreviewMapper.selectProjectdocreviewById(docId);
    }

    /**
     * 查询项目附件审核列表
     * 
     * @param projectdocreview 项目附件审核
     * @return 项目附件审核
     */
    @Override
    public List<Projectdocreview> selectProjectdocreviewList(Projectdocreview projectdocreview)
    {
        return projectdocreviewMapper.selectProjectdocreviewList(projectdocreview);
    }

    /**
     * 新增项目附件审核
     * 
     * @param projectdocreview 项目附件审核
     * @return 结果
     */
    @Override
    public int insertProjectdocreview(Projectdocreview projectdocreview)
    {
        return projectdocreviewMapper.insertProjectdocreview(projectdocreview);
    }

    /**
     * 修改项目附件审核
     * 
     * @param projectdocreview 项目附件审核
     * @return 结果
     */
    @Override
    public int updateProjectdocreview(Projectdocreview projectdocreview)
    {
        return projectdocreviewMapper.updateProjectdocreview(projectdocreview);
    }

    /**
     * 批量删除项目附件审核
     * 
     * @param docIds 需要删除的项目附件审核ID
     * @return 结果
     */
    @Override
    public int deleteProjectdocreviewByIds(Long[] docIds)
    {
        return projectdocreviewMapper.deleteProjectdocreviewByIds(docIds);
    }

    /**
     * 删除项目附件审核信息
     * 
     * @param docId 项目附件审核ID
     * @return 结果
     */
    @Override
    public int deleteProjectdocreviewById(Long docId)
    {
        return projectdocreviewMapper.deleteProjectdocreviewById(docId);
    }
}
