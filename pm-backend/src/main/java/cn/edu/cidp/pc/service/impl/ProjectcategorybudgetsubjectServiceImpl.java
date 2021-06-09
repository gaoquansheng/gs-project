package cn.edu.cidp.pc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.ProjectcategorybudgetsubjectMapper;
import cn.edu.cidp.pc.domain.Projectcategorybudgetsubject;
import cn.edu.cidp.pc.service.IProjectcategorybudgetsubjectService;

/**
 * 项目类别预算Service业务层处理
 * 
 * @author william
 * @date 2020-08-10
 */
@Service
public class ProjectcategorybudgetsubjectServiceImpl implements IProjectcategorybudgetsubjectService 
{
    @Autowired
    private ProjectcategorybudgetsubjectMapper projectcategorybudgetsubjectMapper;

    /**
     * 查询项目类别预算
     * 
     * @param subjectId 项目类别预算ID
     * @return 项目类别预算
     */
    @Override
    public Projectcategorybudgetsubject selectProjectcategorybudgetsubjectById(Long subjectId)
    {
        return projectcategorybudgetsubjectMapper.selectProjectcategorybudgetsubjectById(subjectId);
    }

    /**
     * 查询项目类别预算列表
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 项目类别预算
     */
    @Override
    public List<Projectcategorybudgetsubject> selectProjectcategorybudgetsubjectList(Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        return projectcategorybudgetsubjectMapper.selectProjectcategorybudgetsubjectList(projectcategorybudgetsubject);
    }

    /**
     * 新增项目类别预算
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 结果
     */
    @Override
    public int insertProjectcategorybudgetsubject(Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        return projectcategorybudgetsubjectMapper.insertProjectcategorybudgetsubject(projectcategorybudgetsubject);
    }

    /**
     * 修改项目类别预算
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 结果
     */
    @Override
    public int updateProjectcategorybudgetsubject(Projectcategorybudgetsubject projectcategorybudgetsubject)
    {
        return projectcategorybudgetsubjectMapper.updateProjectcategorybudgetsubject(projectcategorybudgetsubject);
    }

    /**
     * 批量删除项目类别预算
     * 
     * @param subjectIds 需要删除的项目类别预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategorybudgetsubjectByIds(Long[] subjectIds)
    {
        return projectcategorybudgetsubjectMapper.deleteProjectcategorybudgetsubjectByIds(subjectIds);
    }

    /**
     * 删除项目类别预算信息
     * 
     * @param subjectId 项目类别预算ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategorybudgetsubjectById(Long subjectId)
    {
        return projectcategorybudgetsubjectMapper.deleteProjectcategorybudgetsubjectById(subjectId);
    }
}
