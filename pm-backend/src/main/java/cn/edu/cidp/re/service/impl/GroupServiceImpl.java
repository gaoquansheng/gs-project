package cn.edu.cidp.re.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.GroupMapper;
import cn.edu.cidp.re.domain.Group;
import cn.edu.cidp.re.service.IGroupService;

/**
 * 分组信息Service业务层处理
 * 
 * @author william
 * @date 2021-01-19
 */
@Service
public class GroupServiceImpl implements IGroupService 
{
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 查询分组信息
     * 
     * @param groupId 分组信息ID
     * @return 分组信息
     */
    @Override
    public Group selectGroupById(Long groupId)
    {
        return groupMapper.selectGroupById(groupId);
    }

    /**
     * 查询分组信息列表
     * 
     * @param group 分组信息
     * @return 分组信息
     */
    @Override
    public List<Group> selectGroupList(Group group)
    {
        return groupMapper.selectGroupList(group);
    }

    /**
     * 新增分组信息
     * 
     * @param group 分组信息
     * @return 结果
     */
    @Override
    public int insertGroup(Group group)
    {
        return groupMapper.insertGroup(group);
    }

    /**
     * 修改分组信息
     * 
     * @param group 分组信息
     * @return 结果
     */
    @Override
    public int updateGroup(Group group)
    {
        return groupMapper.updateGroup(group);
    }

    /**
     * 批量删除分组信息
     * 
     * @param groupIds 需要删除的分组信息ID
     * @return 结果
     */
    @Override
    public int deleteGroupByIds(Long[] groupIds)
    {
        return groupMapper.deleteGroupByIds(groupIds);
    }

    /**
     * 删除分组信息信息
     * 
     * @param groupId 分组信息ID
     * @return 结果
     */
    @Override
    public int deleteGroupById(Long groupId)
    {
        return groupMapper.deleteGroupById(groupId);
    }
}
