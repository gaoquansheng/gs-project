package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectdocMapper;
import cn.edu.cidp.pm.domain.Projectdoc;
import cn.edu.cidp.pm.service.IProjectdocService;

/**
 * 上传项目附件Service业务层处理
 * 
 * @author william
 * @date 2020-09-14
 */
@Service
public class ProjectdocServiceImpl implements IProjectdocService 
{
    @Autowired
    private ProjectdocMapper projectdocMapper;

    /**
     * 查询上传项目附件
     * 
     * @param docId 上传项目附件ID
     * @return 上传项目附件
     */
    @Override
    public Projectdoc selectProjectdocById(Long docId)
    {
        return projectdocMapper.selectProjectdocById(docId);
    }

    /**
     * 查询上传项目附件列表
     * 
     * @param projectdoc 上传项目附件
     * @return 上传项目附件
     */
    @Override
    public List<Projectdoc> selectProjectdocList(Projectdoc projectdoc)
    {
        return projectdocMapper.selectProjectdocList(projectdoc);
    }

    /**
     * 新增上传项目附件
     * 
     * @param projectdoc 上传项目附件
     * @return 结果
     */
    @Override
    public int insertProjectdoc(Projectdoc projectdoc)
    {
        return projectdocMapper.insertProjectdoc(projectdoc);
    }

    /**
     * 修改上传项目附件
     * 
     * @param projectdoc 上传项目附件
     * @return 结果
     */
    @Override
    public int updateProjectdoc(Projectdoc projectdoc)
    {
        return projectdocMapper.updateProjectdoc(projectdoc);
    }

    /**
     * 批量删除上传项目附件
     * 
     * @param docIds 需要删除的上传项目附件ID
     * @return 结果
     */
    @Override
    public int deleteProjectdocByIds(Long[] docIds)
    {
        return projectdocMapper.deleteProjectdocByIds(docIds);
    }

    /**
     * 删除上传项目附件信息
     * 
     * @param docId 上传项目附件ID
     * @return 结果
     */
    @Override
    public int deleteProjectdocById(Long docId)
    {
        return projectdocMapper.deleteProjectdocById(docId);
    }
}
