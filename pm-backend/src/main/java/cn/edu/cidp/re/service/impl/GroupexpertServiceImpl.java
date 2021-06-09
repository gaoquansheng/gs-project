package cn.edu.cidp.re.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.GroupexpertMapper;
import cn.edu.cidp.re.domain.Groupexpert;
import cn.edu.cidp.re.service.IGroupexpertService;

/**
 * 分配专家Service业务层处理
 * 
 * @author william
 * @date 2021-01-19
 */
@Service
public class GroupexpertServiceImpl implements IGroupexpertService 
{
    @Autowired
    private GroupexpertMapper groupexpertMapper;

    /**
     * 查询分配专家
     * 
     * @param rid 分配专家ID
     * @return 分配专家
     */
    @Override
    public Groupexpert selectGroupexpertById(Long rid)
    {
        return groupexpertMapper.selectGroupexpertById(rid);
    }

    /**
     * 查询分配专家列表
     * 
     * @param groupexpert 分配专家
     * @return 分配专家
     */
    @Override
    public List<Groupexpert> selectGroupexpertList(Groupexpert groupexpert)
    {
        return groupexpertMapper.selectGroupexpertList(groupexpert);
    }

    /**
     * 新增分配专家
     * 
     * @param groupexpert 分配专家
     * @return 结果
     */
    @Override
    public int insertGroupexpert(Groupexpert groupexpert)
    {
        return groupexpertMapper.insertGroupexpert(groupexpert);
    }

    /**
     * 修改分配专家
     * 
     * @param groupexpert 分配专家
     * @return 结果
     */
    @Override
    public int updateGroupexpert(Groupexpert groupexpert)
    {
        return groupexpertMapper.updateGroupexpert(groupexpert);
    }

    /**
     * 批量删除分配专家
     * 
     * @param rids 需要删除的分配专家ID
     * @return 结果
     */
    @Override
    public int deleteGroupexpertByIds(Long[] rids)
    {
        return groupexpertMapper.deleteGroupexpertByIds(rids);
    }

    /**
     * 删除分配专家信息
     * 
     * @param rid 分配专家ID
     * @return 结果
     */
    @Override
    public int deleteGroupexpertById(Long rid)
    {
        return groupexpertMapper.deleteGroupexpertById(rid);
    }
}
