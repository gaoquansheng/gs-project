package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategory;

/**
 * 项目类别Service接口
 * 
 * @author william
 * @date 2020-08-06
 */
public interface IProjectcategoryService 
{
    /**
     * 查询项目类别
     * 
     * @param categoryCode 项目类别ID
     * @return 项目类别
     */
    public Projectcategory selectProjectcategoryById(String categoryCode);

    /**
     * 查询项目类别列表
     * 
     * @param projectcategory 项目类别
     * @return 项目类别集合
     */
    public List<Projectcategory> selectProjectcategoryList(Projectcategory projectcategory);

    /**
     * 新增项目类别
     * 
     * @param projectcategory 项目类别
     * @return 结果
     */
    public int insertProjectcategory(Projectcategory projectcategory);

    /**
     * 修改项目类别
     * 
     * @param projectcategory 项目类别
     * @return 结果
     */
    public int updateProjectcategory(Projectcategory projectcategory);

    /**
     * 批量删除项目类别
     * 
     * @param categoryCodes 需要删除的项目类别ID
     * @return 结果
     */
    public int deleteProjectcategoryByIds(String[] categoryCodes);

    /**
     * 删除项目类别信息
     * 
     * @param categoryCode 项目类别ID
     * @return 结果
     */
    public int deleteProjectcategoryById(String categoryCode);
}
