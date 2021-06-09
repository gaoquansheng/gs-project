package cn.edu.cidp.re.service;

import java.util.List;
import cn.edu.cidp.re.domain.Projectcategoryreviewrulevoteoption;

/**
 * projectcategoryreviewrulevoteoptionService接口
 * 
 * @author william
 * @date 2021-01-12
 */
public interface IProjectcategoryreviewrulevoteoptionService 
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
     * 批量删除projectcategoryreviewrulevoteoption
     * 
     * @param optionIds 需要删除的projectcategoryreviewrulevoteoptionID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulevoteoptionByIds(Long[] optionIds);

    /**
     * 删除projectcategoryreviewrulevoteoption信息
     * 
     * @param optionId projectcategoryreviewrulevoteoptionID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulevoteoptionById(Long optionId);


    public List<Projectcategoryreviewrulevoteoption> selectVoteByCcPc(String categoryCode,String phaseCode);
}
