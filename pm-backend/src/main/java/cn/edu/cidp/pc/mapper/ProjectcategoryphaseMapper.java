package cn.edu.cidp.pc.mapper;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategoryphase;
import cn.edu.cidp.pc.domain.Workflowphase;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 项目类别流程配置Mapper接口
 * 
 * @author william
 * @date 2020-08-10
 */
public interface ProjectcategoryphaseMapper 
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
     * @param
     * @return 结果
     */
//    public int insertProjectcategoryphase(Projectcategoryphase projectcategoryphase);
    public int insertProjectcategoryphase(@Param("workflowphases") List<Workflowphase> workflowphases,@Param("categoryCode") String categoryCode);

    /**
     * 修改项目类别流程配置
     * 
     * @param projectcategoryphase 项目类别流程配置
     * @return 结果
     */
    public int updateProjectcategoryphase(List<Projectcategoryphase> projectcategoryphase);

    /**
     * 删除项目类别流程配置
     * 
     * @param categoryCode 项目类别流程配置ID
     * @return 结果
     */
    public int deleteProjectcategoryphaseById(String categoryCode);

    /**
     * 批量删除项目类别流程配置
     * 
     * @param categoryCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectcategoryphaseByIds(String[] categoryCodes);

    /**
     * 查询文件路径
     */
    public Projectcategoryphase selectProjectcategoryphaseById(@Param("categoryCode") String categoryCode,@Param("phaseCode") String phaseCode);
}
