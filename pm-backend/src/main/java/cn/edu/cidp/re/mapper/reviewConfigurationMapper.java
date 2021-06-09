package cn.edu.cidp.re.mapper;

import java.util.List;
import cn.edu.cidp.re.domain.reviewConfiguration;
import org.apache.ibatis.annotations.Param;

/**
 * 评审配置Mapper接口
 * 
 * @author william
 * @date 2021-01-11
 */
public interface reviewConfigurationMapper 
{
    /**
     * 查询评审配置
     * 
     * @param categoryCode 评审配置ID
     * @return 评审配置
     */
    public List<reviewConfiguration> selectreviewConfigurationById(String categoryCode);

    /**
     * 查询评审配置列表
     * 
     * @param reviewConfiguration 评审配置
     * @return 评审配置集合
     */
    public List<reviewConfiguration> selectreviewConfigurationList(reviewConfiguration reviewConfiguration);

    /**
     * 新增评审配置
     * 
     * @param reviewConfiguration 评审配置
     * @return 结果
     */
    public int insertreviewConfiguration(reviewConfiguration reviewConfiguration);

    /**
     * 修改评审配置
     * 
     * @param reviewConfiguration 评审配置
     * @return 结果
     */
    public int updatereviewConfiguration(reviewConfiguration reviewConfiguration);

    /**
     * 删除评审配置
     * 
     * @param categoryCode 评审配置ID
     * @return 结果
     */
    public int deletereviewConfigurationById(String categoryCode);

    /**
     * 批量删除评审配置
     * 
     * @param categoryCodes 需要删除的数据ID
     * @return 结果
     */
    public int deletereviewConfigurationByIds(String[] categoryCodes);

    public int checkUnique(@Param("categoryCode") String categoryCode , @Param("phaseCode") String phaseCode);
}
