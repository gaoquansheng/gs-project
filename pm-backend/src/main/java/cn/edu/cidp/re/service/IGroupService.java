package cn.edu.cidp.re.service;

import java.util.List;
import cn.edu.cidp.re.domain.Group;

/**
 * 分组信息Service接口
 * 
 * @author william
 * @date 2021-01-19
 */
public interface IGroupService 
{
    /**
     * 查询分组信息
     * 
     * @param groupId 分组信息ID
     * @return 分组信息
     */
    public Group selectGroupById(Long groupId);

    /**
     * 查询分组信息列表
     * 
     * @param group 分组信息
     * @return 分组信息集合
     */
    public List<Group> selectGroupList(Group group);

    /**
     * 新增分组信息
     * 
     * @param group 分组信息
     * @return 结果
     */
    public int insertGroup(Group group);

    /**
     * 修改分组信息
     * 
     * @param group 分组信息
     * @return 结果
     */
    public int updateGroup(Group group);

    /**
     * 批量删除分组信息
     * 
     * @param groupIds 需要删除的分组信息ID
     * @return 结果
     */
    public int deleteGroupByIds(Long[] groupIds);

    /**
     * 删除分组信息信息
     * 
     * @param groupId 分组信息ID
     * @return 结果
     */
    public int deleteGroupById(Long groupId);
}
