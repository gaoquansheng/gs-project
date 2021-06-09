package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectorg;
import cn.edu.cidp.project.system.domain.SysDept;
import cn.edu.cidp.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 添加合作单位Mapper接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface ProjectorgMapper
{
    /**
     * 查询添加合作单位
     *
     * @param rid 添加合作单位ID
     * @return 添加合作单位
     */
    public Projectorg selectProjectorgById(Long rid);

    /**
     * 查询添加合作单位列表
     *
     * @param projectId 添加合作单位
     * @return 添加合作单位集合
     */
    public List<Projectorg> selectProjectorgList(@Param("projectId") Long projectId,@Param("phaseCode") String phaseCode);

    /**
     * 新增添加合作单位
     *
     * @param projectorg 添加合作单位
     * @return 结果
     */
    public int insertProjectorg(Projectorg projectorg);

    /**
     * 修改添加合作单位
     *
     * @param projectorg 添加合作单位
     * @return 结果
     */
    public int updateProjectorg(Projectorg projectorg);

    /**
     * 删除添加合作单位
     *
     * @param rid 添加合作单位ID
     * @return 结果
     */
    public int deleteProjectorgById(Long rid);

    /**
     * 批量删除添加合作单位
     *
     * @param rids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectorgByIds(Long[] rids);

    /**
     * 添加内部合作单位
     *
     * @param
     * @return 结果
     */
    public int addProjectorg(@Param("sysDept") List<SysDept> sysDept);


    public int insertProjectorgList(List<Projectorg> projectorgs);

    public int deleteProjectorgList(Long projectId,String phaseCode);

    public int checkUnique(Projectorg projectorg);

}
