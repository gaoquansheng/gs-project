package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectorg;
import cn.edu.cidp.project.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

/**
 * 添加合作单位Service接口
 *
 * @author william
 * @date 2020-09-13
 */
public interface IProjectorgService
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
    public List<Projectorg> selectProjectorgList(Long projectId,String phaseCode);

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
     * 批量删除添加合作单位
     *
     * @param rids 需要删除的添加合作单位ID
     * @return 结果
     */
    public int deleteProjectorgByIds(Long[] rids);

    /**
     * 删除添加合作单位信息
     *
     * @param rid 添加合作单位ID
     * @return 结果
     */
    public int deleteProjectorgById(Long rid);

    /**
     * 添加内部合作单位
     *
     * @param sysDept
     * @return 结果
     */
    public int addProjectorg (List<SysDept> sysDept);

    /**
     * 检查合作单位是否已添加
     */
    public int checkUnique(Projectorg projectorg);

}
