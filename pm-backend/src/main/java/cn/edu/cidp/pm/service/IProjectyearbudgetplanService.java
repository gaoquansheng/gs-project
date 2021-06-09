package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectyearbudgetplan;

/**
 * 项目年度预算Service接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface IProjectyearbudgetplanService
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
    public List<Projectyearbudgetplan> selectProjectyearbudgetplanList(Long projectId,String phaseCode);

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
     * 批量删除项目年度预算
     *
     * @param rids 需要删除的项目年度预算ID
     * @return 结果
     */
    public int deleteProjectyearbudgetplanByIds(Long[] rids);

    /**
     * 删除项目年度预算信息
     *
     * @param rid 项目年度预算ID
     * @return 结果
     */
    public int deleteProjectyearbudgetplanById(Long rid);
    //检查唯一
    public int checkUnique(Long year,Long projectId,String phaseCode);
}
