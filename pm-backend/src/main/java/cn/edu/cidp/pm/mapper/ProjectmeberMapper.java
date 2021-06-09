package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectmeber;
import cn.edu.cidp.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 项目组成员Mapper接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface ProjectmeberMapper
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
     * @param projectId 项目组成员
     * @return 项目组成员集合
     */
    public List<Projectmeber> selectProjectmeberList(@Param("projectId") Long projectId, @Param("phaseCode") String phaseCode);

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
     * 删除项目组成员
     *
     * @param rid 项目组成员ID
     * @return 结果
     */
    public int deleteProjectmeberById(Long rid);

    /**
     * 批量删除项目组成员
     *
     * @param rids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectmeberByIds(Long[] rids);
    /**
     * 新增项目组成员(内部)
     *
     * @param sysUser 项目组成员
     * @return 结果
     */
    public int addProjectmeber(@Param("sysUser") List<SysUser> sysUser);


    public int insertProjectmeberList(List<Projectmeber> projectmebers);

    public int deleteProjectmeberList(Long projectId,String phaseCode);

    public int checkUnique(Projectmeber projectmeber);

}
