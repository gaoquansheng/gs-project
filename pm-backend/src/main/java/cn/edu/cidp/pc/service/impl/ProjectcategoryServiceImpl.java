package cn.edu.cidp.pc.service.impl;

import java.util.List;

import cn.edu.cidp.pc.domain.Projectcategoryphase;
import cn.edu.cidp.pc.domain.Workflowphase;
import cn.edu.cidp.pc.mapper.ProjectcategoryphaseMapper;
import cn.edu.cidp.pc.mapper.WorkflowphaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.ProjectcategoryMapper;
import cn.edu.cidp.pc.domain.Projectcategory;
import cn.edu.cidp.pc.service.IProjectcategoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目类别Service业务层处理
 *
 * @author william
 * @date 2020-08-06
 */
@Service
public class ProjectcategoryServiceImpl implements IProjectcategoryService
{
    @Autowired
    private ProjectcategoryMapper projectcategoryMapper;
    @Autowired
    private ProjectcategoryphaseMapper projectcategoryphaseMapper;
    @Autowired
    private WorkflowphaseMapper workflowphaseMapper;
    /**
     * 查询项目类别
     *
     * @param categoryCode 项目类别ID
     * @return 项目类别
     */
    @Override
    public Projectcategory selectProjectcategoryById(String categoryCode)
    {
        return projectcategoryMapper.selectProjectcategoryById(categoryCode);
    }

    /**
     * 查询项目类别列表
     *
     * @param projectcategory 项目类别
     * @return 项目类别
     */
    @Override
    public List<Projectcategory> selectProjectcategoryList(Projectcategory projectcategory)
    {
        return projectcategoryMapper.selectProjectcategoryList(projectcategory);
    }

    /**
     * 新增项目类别
     *
     * @param projectcategory 项目类别
     * @return 结果
     */
    @Override
    @Transactional
    public int insertProjectcategory(Projectcategory projectcategory)
    {
        //1.首先判断当前项目类别是否存在
        int rows = projectcategoryMapper.checkProjectCategory(projectcategory.getCategoryCode());
        //2.存在则返回添加失败
        if(rows>0){
            return 0;
        }else{
            List<Workflowphase> workflowphases = workflowphaseMapper.selectWorkflowphaseList();
            projectcategoryphaseMapper.insertProjectcategoryphase(workflowphases,projectcategory.getCategoryCode());
            return projectcategoryMapper.insertProjectcategory(projectcategory);
        }


    }

    /**
     * 修改项目类别
     *
     * @param projectcategory 项目类别
     * @return 结果
     */
    @Override
    public int updateProjectcategory(Projectcategory projectcategory)
    {
        return projectcategoryMapper.updateProjectcategory(projectcategory);
    }

    /**
     * 批量删除项目类别
     *
     * @param categoryCodes 需要删除的项目类别ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryByIds(String[] categoryCodes)
    {
        return projectcategoryMapper.deleteProjectcategoryByIds(categoryCodes);
    }

    /**
     * 删除项目类别信息
     *
     * @param categoryCode 项目类别ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryById(String categoryCode)
    {
        return projectcategoryMapper.deleteProjectcategoryById(categoryCode);
    }
}
