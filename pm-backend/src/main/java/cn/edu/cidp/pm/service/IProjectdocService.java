package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectdoc;

/**
 * 上传项目附件Service接口
 * 
 * @author william
 * @date 2020-09-14
 */
public interface IProjectdocService 
{
    /**
     * 查询上传项目附件
     * 
     * @param docId 上传项目附件ID
     * @return 上传项目附件
     */
    public Projectdoc selectProjectdocById(Long docId);

    /**
     * 查询上传项目附件列表
     * 
     * @param projectdoc 上传项目附件
     * @return 上传项目附件集合
     */
    public List<Projectdoc> selectProjectdocList(Projectdoc projectdoc);

    /**
     * 新增上传项目附件
     * 
     * @param projectdoc 上传项目附件
     * @return 结果
     */
    public int insertProjectdoc(Projectdoc projectdoc);

    /**
     * 修改上传项目附件
     * 
     * @param projectdoc 上传项目附件
     * @return 结果
     */
    public int updateProjectdoc(Projectdoc projectdoc);

    /**
     * 批量删除上传项目附件
     * 
     * @param docIds 需要删除的上传项目附件ID
     * @return 结果
     */
    public int deleteProjectdocByIds(Long[] docIds);

    /**
     * 删除上传项目附件信息
     * 
     * @param docId 上传项目附件ID
     * @return 结果
     */
    public int deleteProjectdocById(Long docId);
}
