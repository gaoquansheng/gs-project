package cn.edu.cidp.re.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.ProjectcategoryreviewrulescoreoptionMapper;
import cn.edu.cidp.re.domain.Projectcategoryreviewrulescoreoption;
import cn.edu.cidp.re.service.IProjectcategoryreviewrulescoreoptionService;

/**
 * 评分设置Service业务层处理
 *
 * @author william
 * @date 2021-01-12
 */
@Service
public class ProjectcategoryreviewrulescoreoptionServiceImpl implements IProjectcategoryreviewrulescoreoptionService {
    @Autowired
    private ProjectcategoryreviewrulescoreoptionMapper projectcategoryreviewrulescoreoptionMapper;

    /**
     * 查询评分设置
     *
     * @param scoreId 评分设置ID
     * @return 评分设置
     */
    @Override
    public Projectcategoryreviewrulescoreoption selectProjectcategoryreviewrulescoreoptionById(Long scoreId) {
        return projectcategoryreviewrulescoreoptionMapper.selectProjectcategoryreviewrulescoreoptionById(scoreId);
    }

    /**
     * 查询评分设置列表
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 评分设置
     */
    @Override
    public List<Projectcategoryreviewrulescoreoption> selectProjectcategoryreviewrulescoreoptionList(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        return projectcategoryreviewrulescoreoptionMapper.selectProjectcategoryreviewrulescoreoptionList(projectcategoryreviewrulescoreoption);
    }

    /**
     * 新增评分设置
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 结果
     */
    @Override
    public int insertProjectcategoryreviewrulescoreoption(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        return projectcategoryreviewrulescoreoptionMapper.insertProjectcategoryreviewrulescoreoption(projectcategoryreviewrulescoreoption);
    }

    /**
     * 修改评分设置
     *
     * @param projectcategoryreviewrulescoreoption 评分设置
     * @return 结果
     */
    @Override
    public int updateProjectcategoryreviewrulescoreoption(Projectcategoryreviewrulescoreoption projectcategoryreviewrulescoreoption) {
        return projectcategoryreviewrulescoreoptionMapper.updateProjectcategoryreviewrulescoreoption(projectcategoryreviewrulescoreoption);
    }

    /**
     * 批量删除评分设置
     *
     * @param scoreIds 需要删除的评分设置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryreviewrulescoreoptionByIds(Long[] scoreIds) {
        return projectcategoryreviewrulescoreoptionMapper.deleteProjectcategoryreviewrulescoreoptionByIds(scoreIds);
    }

    /**
     * 删除评分设置信息
     *
     * @param scoreId 评分设置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryreviewrulescoreoptionById(Long scoreId) {
        return projectcategoryreviewrulescoreoptionMapper.deleteProjectcategoryreviewrulescoreoptionById(scoreId);
    }

    @Override
    public List<Projectcategoryreviewrulescoreoption> selectScoreByCcPc(String categoryCode, String phaseCode) {
        return projectcategoryreviewrulescoreoptionMapper.selectScoreByCcPc(categoryCode, phaseCode);
    }
}
