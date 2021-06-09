package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ExpertMapper;
import cn.edu.cidp.pm.domain.Expert;
import cn.edu.cidp.pm.service.IExpertService;

/**
 * 专家Service业务层处理
 * 
 * @author william
 * @date 2021-05-31
 */
@Service
public class ExpertServiceImpl implements IExpertService 
{
    @Autowired
    private ExpertMapper expertMapper;

    /**
     * 查询专家
     * 
     * @param expertid 专家ID
     * @return 专家
     */
    @Override
    public Expert selectExpertById(Long expertid)
    {
        return expertMapper.selectExpertById(expertid);
    }

    /**
     * 查询专家列表
     * 
     * @param expert 专家
     * @return 专家
     */
    @Override
    public List<Expert> selectExpertList(Expert expert)
    {
        return expertMapper.selectExpertList(expert);
    }

    /**
     * 新增专家
     * 
     * @param expert 专家
     * @return 结果
     */
    @Override
    public int insertExpert(Expert expert)
    {
        return expertMapper.insertExpert(expert);
    }

    /**
     * 修改专家
     * 
     * @param expert 专家
     * @return 结果
     */
    @Override
    public int updateExpert(Expert expert)
    {
        return expertMapper.updateExpert(expert);
    }

    /**
     * 批量删除专家
     * 
     * @param expertids 需要删除的专家ID
     * @return 结果
     */
    @Override
    public int deleteExpertByIds(Long[] expertids)
    {
        return expertMapper.deleteExpertByIds(expertids);
    }

    /**
     * 删除专家信息
     * 
     * @param expertid 专家ID
     * @return 结果
     */
    @Override
    public int deleteExpertById(Long expertid)
    {
        return expertMapper.deleteExpertById(expertid);
    }
}
