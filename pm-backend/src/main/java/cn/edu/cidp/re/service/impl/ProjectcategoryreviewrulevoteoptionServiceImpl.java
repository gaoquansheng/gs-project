package cn.edu.cidp.re.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.ProjectcategoryreviewrulevoteoptionMapper;
import cn.edu.cidp.re.domain.Projectcategoryreviewrulevoteoption;
import cn.edu.cidp.re.service.IProjectcategoryreviewrulevoteoptionService;

/**
 * projectcategoryreviewrulevoteoptionService业务层处理
 *
 * @author william
 * @date 2021-01-12
 */
@Service
public class ProjectcategoryreviewrulevoteoptionServiceImpl implements IProjectcategoryreviewrulevoteoptionService {
    @Autowired
    private ProjectcategoryreviewrulevoteoptionMapper projectcategoryreviewrulevoteoptionMapper;

    /**
     * 查询projectcategoryreviewrulevoteoption
     *
     * @param optionId projectcategoryreviewrulevoteoptionID
     * @return projectcategoryreviewrulevoteoption
     */
    @Override
    public Projectcategoryreviewrulevoteoption selectProjectcategoryreviewrulevoteoptionById(Long optionId) {
        return projectcategoryreviewrulevoteoptionMapper.selectProjectcategoryreviewrulevoteoptionById(optionId);
    }

    /**
     * 查询projectcategoryreviewrulevoteoption列表
     *
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return projectcategoryreviewrulevoteoption
     */
    @Override
    public List<Projectcategoryreviewrulevoteoption> selectProjectcategoryreviewrulevoteoptionList(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption) {
        return projectcategoryreviewrulevoteoptionMapper.selectProjectcategoryreviewrulevoteoptionList(projectcategoryreviewrulevoteoption);
    }

    /**
     * 新增projectcategoryreviewrulevoteoption
     *
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return 结果
     */
    @Override
    public int insertProjectcategoryreviewrulevoteoption(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption) {
        return projectcategoryreviewrulevoteoptionMapper.insertProjectcategoryreviewrulevoteoption(projectcategoryreviewrulevoteoption);
    }

    /**
     * 修改projectcategoryreviewrulevoteoption
     *
     * @param projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption
     * @return 结果
     */
    @Override
    public int updateProjectcategoryreviewrulevoteoption(Projectcategoryreviewrulevoteoption projectcategoryreviewrulevoteoption) {
        return projectcategoryreviewrulevoteoptionMapper.updateProjectcategoryreviewrulevoteoption(projectcategoryreviewrulevoteoption);
    }

    /**
     * 批量删除projectcategoryreviewrulevoteoption
     *
     * @param optionIds 需要删除的projectcategoryreviewrulevoteoptionID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryreviewrulevoteoptionByIds(Long[] optionIds) {
        return projectcategoryreviewrulevoteoptionMapper.deleteProjectcategoryreviewrulevoteoptionByIds(optionIds);
    }

    /**
     * 删除projectcategoryreviewrulevoteoption信息
     *
     * @param optionId projectcategoryreviewrulevoteoptionID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryreviewrulevoteoptionById(Long optionId) {
        return projectcategoryreviewrulevoteoptionMapper.deleteProjectcategoryreviewrulevoteoptionById(optionId);
    }

    @Override
    public List<Projectcategoryreviewrulevoteoption> selectVoteByCcPc(String categoryCode, String phaseCode) {
        return projectcategoryreviewrulevoteoptionMapper.selectVoteByCcPc(categoryCode, phaseCode);
    }
}
