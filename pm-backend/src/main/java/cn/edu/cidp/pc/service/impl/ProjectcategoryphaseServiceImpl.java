package cn.edu.cidp.pc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.ProjectcategoryphaseMapper;
import cn.edu.cidp.pc.domain.Projectcategoryphase;
import cn.edu.cidp.pc.service.IProjectcategoryphaseService;

/**
 * 项目类别流程配置Service业务层处理
 * 
 * @author william
 * @date 2020-08-10
 */
@Service
public class ProjectcategoryphaseServiceImpl implements IProjectcategoryphaseService 
{
    @Autowired
    private ProjectcategoryphaseMapper projectcategoryphaseMapper;



    /**
     * 查询项目类别流程配置列表
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 项目类别流程配置
     */
    @Override
    public List<Projectcategoryphase> selectProjectcategoryphaseList(Projectcategoryphase projectcategoryphase)
    {
        return projectcategoryphaseMapper.selectProjectcategoryphaseList(projectcategoryphase);
    }

    /**
     * 新增项目类别流程配置
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 结果
     */
    @Override
    public int insertProjectcategoryphase(Projectcategoryphase projectcategoryphase)
    {
//        return projectcategoryphaseMapper.insertProjectcategoryphase(projectcategoryphase);
        return 1;
    }

    /**
     * 修改项目类别流程配置
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 结果
     */
    @Override
    public int updateProjectcategoryphase(List<Projectcategoryphase> projectcategoryphase)
    {
        return projectcategoryphaseMapper.updateProjectcategoryphase(projectcategoryphase);
    }

    /**
     * 批量删除项目类别流程配置
     * 
     * @param categoryCodes 需要删除的项目类别流程配置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryphaseByIds(String[] categoryCodes)
    {
        return projectcategoryphaseMapper.deleteProjectcategoryphaseByIds(categoryCodes);
    }

    /**
     * 删除项目类别流程配置信息
     * 
     * @param categoryCode 项目类别流程配置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryphaseById(String categoryCode)
    {
        return projectcategoryphaseMapper.deleteProjectcategoryphaseById(categoryCode);
    }

    @Override
    public Projectcategoryphase selectProjectcategoryphaseById(String categoryCode, String phaseCode) {
        return projectcategoryphaseMapper.selectProjectcategoryphaseById(categoryCode,phaseCode);
    }


}
