package cn.edu.cidp.re.mapper;

import java.util.List;
import cn.edu.cidp.re.domain.Groupexpert;

/**
 * 分配专家Mapper接口
 * 
 * @author william
 * @date 2021-01-19
 */
public interface GroupexpertMapper 
{
    /**
     * 查询分配专家
     * 
     * @param rid 分配专家ID
     * @return 分配专家
     */
    public Groupexpert selectGroupexpertById(Long rid);

    /**
     * 查询分配专家列表
     * 
     * @param groupexpert 分配专家
     * @return 分配专家集合
     */
    public List<Groupexpert> selectGroupexpertList(Groupexpert groupexpert);

    /**
     * 新增分配专家
     * 
     * @param groupexpert 分配专家
     * @return 结果
     */
    public int insertGroupexpert(Groupexpert groupexpert);

    /**
     * 修改分配专家
     * 
     * @param groupexpert 分配专家
     * @return 结果
     */
    public int updateGroupexpert(Groupexpert groupexpert);

    /**
     * 删除分配专家
     * 
     * @param rid 分配专家ID
     * @return 结果
     */
    public int deleteGroupexpertById(Long rid);

    /**
     * 批量删除分配专家
     * 
     * @param rids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGroupexpertByIds(Long[] rids);
}
