package cn.edu.cidp.re.mapper;

import java.util.List;

import cn.edu.cidp.re.domain.Projectcategoryreviewrulescoreoption;
import org.apache.ibatis.annotations.Param;

/**
 * 评分设置Mapper接口
 *
 * @author william
 * @date 2021-01-12
 */
public interface ProjectcategoryreviewrulescoreoptionMapper {
    /**
     * 查询评分设置
     *
     * @param scoreId 评分设置ID
     * @return 评分设置
     */
    public Projectcategoryreviewrulescoreoption selectProjectcategoryreviewrulescoreoptionById(Long scoreId);

    /**
     * 查询评分设置列表
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 评分设置集合
     */
    public List<Projectcategoryreviewrulescoreoption> selectProjectcategoryreviewrulescoreoptionList(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption);

    /**
     * 新增评分设置
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 结果
     */
    public int insertProjectcategoryreviewrulescoreoption(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption);

    /**
     * 修改评分设置
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 结果
     */
    public int updateProjectcategoryreviewrulescoreoption(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption);

    /**
     * 删除评分设置
     *
     * @param scoreId 评分设置ID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulescoreoptionById(Long scoreId);

    /**
     * 批量删除评分设置
     *
     * @param scoreIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectcategoryreviewrulescoreoptionByIds(Long[] scoreIds);


    /**
     *
     */
    public List<Projectcategoryreviewrulescoreoption> selectScoreByCcPc(@Param("categoryCode") String categoryCode, @Param("phaseCode") String phaseCode);
}
