package cn.edu.cidp.re.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.reviewConfigurationMapper;
import cn.edu.cidp.re.domain.reviewConfiguration;
import cn.edu.cidp.re.service.IreviewConfigurationService;

/**
 * 评审配置Service业务层处理
 * 
 * @author william
 * @date 2021-01-11
 */
@Service
public class reviewConfigurationServiceImpl implements IreviewConfigurationService 
{
    @Autowired
    private reviewConfigurationMapper reviewConfigurationMapper;

    /**
     * 查询评审配置
     * 
     * @param categoryCode 评审配置ID
     * @return 评审配置
     */
    @Override
    public List<reviewConfiguration> selectreviewConfigurationById(String categoryCode)
    {
        return reviewConfigurationMapper.selectreviewConfigurationById(categoryCode);
    }

    /**
     * 查询评审配置列表
     * 
     * @param reviewConfiguration 评审配置
     * @return 评审配置
     */
    @Override
    public List<reviewConfiguration> selectreviewConfigurationList(reviewConfiguration reviewConfiguration)
    {
        return reviewConfigurationMapper.selectreviewConfigurationList(reviewConfiguration);
    }

    /**
     * 新增评审配置
     * 
     * @param reviewConfiguration 评审配置
     * @return 结果
     */
    @Override
    public int insertreviewConfiguration(reviewConfiguration reviewConfiguration)
    {
        return reviewConfigurationMapper.insertreviewConfiguration(reviewConfiguration);
    }

    /**
     * 修改评审配置
     * 
     * @param reviewConfiguration 评审配置
     * @return 结果
     */
    @Override
    public int updatereviewConfiguration(reviewConfiguration reviewConfiguration)
    {
        return reviewConfigurationMapper.updatereviewConfiguration(reviewConfiguration);
    }

    /**
     * 批量删除评审配置
     * 
     * @param categoryCodes 需要删除的评审配置ID
     * @return 结果
     */
    @Override
    public int deletereviewConfigurationByIds(String[] categoryCodes)
    {
        return reviewConfigurationMapper.deletereviewConfigurationByIds(categoryCodes);
    }

    /**
     * 删除评审配置信息
     * 
     * @param categoryCode 评审配置ID
     * @return 结果
     */
    @Override
    public int deletereviewConfigurationById(String categoryCode)
    {
        return reviewConfigurationMapper.deletereviewConfigurationById(categoryCode);
    }

    @Override
    public int checkUnique(String categoryCode, String phaseCode) {
        return reviewConfigurationMapper.checkUnique(categoryCode, phaseCode);
    }
}
