package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Expert;

/**
 * 专家Service接口
 * 
 * @author william
 * @date 2021-05-31
 */
public interface IExpertService 
{
    /**
     * 查询专家
     * 
     * @param expertid 专家ID
     * @return 专家
     */
    public Expert selectExpertById(Long expertid);

    /**
     * 查询专家列表
     * 
     * @param expert 专家
     * @return 专家集合
     */
    public List<Expert> selectExpertList(Expert expert);

    /**
     * 新增专家
     * 
     * @param expert 专家
     * @return 结果
     */
    public int insertExpert(Expert expert);

    /**
     * 修改专家
     * 
     * @param expert 专家
     * @return 结果
     */
    public int updateExpert(Expert expert);

    /**
     * 批量删除专家
     * 
     * @param expertids 需要删除的专家ID
     * @return 结果
     */
    public int deleteExpertByIds(Long[] expertids);

    /**
     * 删除专家信息
     * 
     * @param expertid 专家ID
     * @return 结果
     */
    public int deleteExpertById(Long expertid);
}
