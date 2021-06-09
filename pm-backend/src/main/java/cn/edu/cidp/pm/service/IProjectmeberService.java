package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectmeber;
import cn.edu.cidp.project.system.domain.SysUser;

/**
 * 项目组成员Service接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface IProjectmeberService
{
    /**
     * 查询项目组成员
     *
     * @param rid 项目组成员ID
     * @return 项目组成员
     */
    public Projectmeber selectProjectmeberById(Long rid);

    /**
     * 查询项目组成员列表
     *
     * @param projectId 项目id
     * @return 项目组成员集合
     */
    public List<Projectmeber> selectProjectmeberList(Long projectId,String phaseCode);

    /**
     * 新增项目组成员
     *
     * @param projectmeber 项目组成员
     * @return 结果
     */
    public int insertProjectmeber(Projectmeber projectmeber);

    /**
     * 修改项目组成员
     *
     * @param projectmeber 项目组成员
     * @return 结果
     */
    public int updateProjectmeber(Projectmeber projectmeber);

    /**
     * 批量删除项目组成员
     *
     * @param rids 需要删除的项目组成员ID
     * @return 结果
     */
    public int deleteProjectmeberByIds(Long[] rids);

    /**
     * 删除项目组成员信息
     *
     * @param rid 项目组成员ID
     * @return 结果
     */
    public int deleteProjectmeberById(Long rid);

    /**
     * 新增项目组成员
     *
     * @param sysUser 项目组成员
     * @return 结果
     */
    public int addProjectmeber(List<SysUser> sysUser);

    /**
     * 检查是都重复添加
     */
    public int checkUnique(Projectmeber projectmeber);
}
