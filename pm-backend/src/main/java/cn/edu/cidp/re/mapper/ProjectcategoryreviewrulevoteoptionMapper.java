package cn.edu.cidp.re.mapper;

import java.util.List;
import cn.edu.cidp.re.domain.Projectcategoryreviewrulevoteoption;
import org.apache.ibatis.annotations.Param;

/**
 * projectcategoryreviewrulevoteoptionMapper接口
 * 
 * @author william
 * @date 2021-01-12
 */
public interface ProjectcategoryreviewrulevoteoptionMapper 
{
    /**
     * 查询projectcategoryreviewrulevoteoption
     * 
     * @param optionId projectcategoryreviewrulevoteoptionID
     * @return projectcategoryreviewrulevoteoption
     */
    public Projectcategoryreviewrulevoteoption selectProjectcategoryreviewrulevoteoptionById(Long optionId);

    /**
     * 查询projectcategoryreviewrulevoteoption列表
     * 
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return projectcategoryreviewrulevoteoption集合
     */
    public List<Projectcategoryreviewrulevoteoption> selectProjectcategoryreviewrulevoteoptionList(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption);

    /**
     * 新增projectcategoryreviewrulevoteoption
     * 
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return 结果
     */
    public int insertProjectcategoryreviewrulevoteoption(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption);

    /**
     * 修改projectcategoryreviewrulevoteoption
     * 
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return 结果
     */
    public int updateProjectcategoryreviewrulevoteoption(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption);

    /**
     * 删除projectcategoryreviewrulevoteoption
     * 
     * @param optionId projectcategoryreviewrulevoteoptionID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulevoteoptionById(Long optionId);

    /**
     * 批量删除projectcategoryreviewrulevoteoption
     * 
     * @param optionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulevoteoptionByIds(Long[] optionIds);

    /**
     * 根据categoryCode和phaseCode查询投票信息
     */
    public List<Projectcategoryreviewrulevoteoption> selectVoteByCcPc(@Param("categoryCode") String categoryCode,@Param("phaseCode") String phaseCode);
}
