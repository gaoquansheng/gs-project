package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategorybudgetsubject;

/**
 * 项目类别预算Service接口
 * 
 * @author william
 * @date 2020-08-10
 */
public interface IProjectcategorybudgetsubjectService 
{
    /**
     * 查询项目类别预算
     * 
     * @param subjectId 项目类别预算ID
     * @return 项目类别预算
     */
    public Projectcategorybudgetsubject selectProjectcategorybudgetsubjectById(Long subjectId);

    /**
     * 查询项目类别预算列表
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 项目类别预算集合
     */
    public List<Projectcategorybudgetsubject> selectProjectcategorybudgetsubjectList(Projectcategorybudgetsubject projectcategorybudgetsubject);

    /**
     * 新增项目类别预算
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 结果
     */
    public int insertProjectcategorybudgetsubject(Projectcategorybudgetsubject projectcategorybudgetsubject);

    /**
     * 修改项目类别预算
     * 
     * @param projectcategorybudgetsubject 项目类别预算
     * @return 结果
     */
    public int updateProjectcategorybudgetsubject(Projectcategorybudgetsubject projectcategorybudgetsubject);

    /**
     * 批量删除项目类别预算
     * 
     * @param subjectIds 需要删除的项目类别预算ID
     * @return 结果
     */
    public int deleteProjectcategorybudgetsubjectByIds(Long[] subjectIds);

    /**
     * 删除项目类别预算信息
     * 
     * @param subjectId 项目类别预算ID
     * @return 结果
     */
    public int deleteProjectcategorybudgetsubjectById(Long subjectId);
}
