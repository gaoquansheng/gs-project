package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategoryphase;
import org.apache.ibatis.annotations.Param;

/**
 * 项目类别流程配置Service接口
 * 
 * @author william
 * @date 2020-08-10
 */
public interface IProjectcategoryphaseService 
{


    /**
     * 查询项目类别流程配置列表
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 项目类别流程配置集合
     */
    public List<Projectcategoryphase> selectProjectcategoryphaseList(Projectcategoryphase projectcategoryphase);

    /**
     * 新增项目类别流程配置
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 结果
     */
    public int insertProjectcategoryphase(Projectcategoryphase projectcategoryphase);

    /**
     * 修改项目类别流程配置
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 结果
     */
    public int updateProjectcategoryphase(List<Projectcategoryphase> projectcategoryphase);

    /**
     * 批量删除项目类别流程配置
     * 
     * @param categoryCodes 需要删除的项目类别流程配置ID
     * @return 结果
     */
    public int deleteProjectcategoryphaseByIds(String[] categoryCodes);

    /**
     * 删除项目类别流程配置信息
     * 
     * @param categoryCode 项目类别流程配置ID
     * @return 结果
     */
    public int deleteProjectcategoryphaseById(String categoryCode);

    /**
     * 查询文件路径
     */
    public Projectcategoryphase selectProjectcategoryphaseById(String categoryCode,String phaseCode);

}
