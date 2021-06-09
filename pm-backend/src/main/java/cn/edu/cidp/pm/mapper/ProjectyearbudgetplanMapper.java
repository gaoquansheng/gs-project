package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectyearbudgetplan;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * 项目年度预算Mapper接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface ProjectyearbudgetplanMapper
{
    /**
     * 查询项目年度预算
     *
     * @param rid 项目年度预算ID
     * @return 项目年度预算
     */
    public Projectyearbudgetplan selectProjectyearbudgetplanById(Long rid);

    /**
     * 查询项目年度预算列表
     *
     * @param projectId 项目年度预算
     * @return 项目年度预算集合
     */
    public List<Projectyearbudgetplan> selectProjectyearbudgetplanList(@Param("projectId") Long projectId,@Param("phaseCode") String phaseCode);

    /**
     * 新增项目年度预算
     *
     * @param projectyearbudgetplan 项目年度预算
     * @return 结果
     */
    public int insertProjectyearbudgetplan(Projectyearbudgetplan projectyearbudgetplan);

    /**
     * 修改项目年度预算
     *
     * @param projectyearbudgetplan 项目年度预算
     * @return 结果
     */
    public int updateProjectyearbudgetplan(Projectyearbudgetplan projectyearbudgetplan);

    /**
     * 删除项目年度预算
     *
     * @param rid 项目年度预算ID
     * @return 结果
     */
    public int deleteProjectyearbudgetplanById(Long rid);

    /**
     * 批量删除项目年度预算
     *
     * @param rids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectyearbudgetplanByIds(Long[] rids);
    /**
    *@Description 检查年度预算的唯一性

    *@Author 张明杰

    *@Date 2020/9/14/014 8:51
    **/
    public int checkUnique(@Param("year") Long year, @Param("projectId") Long projectId,@Param("phaseCode") String phaseCode);


    public int insertProjectyearbudgetplanList(List<Projectyearbudgetplan> projectyearbudgetplans);

    public int deleteProjectyearbudgetplanList(Long projectId,String phaseCode);

}
