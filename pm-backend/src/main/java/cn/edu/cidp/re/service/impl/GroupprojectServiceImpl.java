package cn.edu.cidp.re.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.re.mapper.GroupprojectMapper;
import cn.edu.cidp.re.domain.Groupproject;
import cn.edu.cidp.re.service.IGroupprojectService;

/**
 * 分组项目Service业务层处理
 * 
 * @author william
 * @date 2021-01-19
 */
@Service
public class GroupprojectServiceImpl implements IGroupprojectService 
{
    @Autowired
    private GroupprojectMapper groupprojectMapper;

    /**
     * 查询分组项目
     * 
     * @param rid 分组项目ID
     * @return 分组项目
     */
    @Override
    public Groupproject selectGroupprojectById(Long rid)
    {
        return groupprojectMapper.selectGroupprojectById(rid);
    }

    /**
     * 查询分组项目列表
     * 
     * @param groupproject 分组项目
     * @return 分组项目
     */
    @Override
    public List<Groupproject> selectGroupprojectList(Groupproject groupproject)
    {
        return groupprojectMapper.selectGroupprojectList(groupproject);
    }

    /**
     * 新增分组项目
     * 
     * @param groupproject 分组项目
     * @return 结果
     */
    @Override
    public int insertGroupproject(Groupproject groupproject)
    {
        return groupprojectMapper.insertGroupproject(groupproject);
    }

    /**
     * 修改分组项目
     * 
     * @param groupproject 分组项目
     * @return 结果
     */
    @Override
    public int updateGroupproject(Groupproject groupproject)
    {
        return groupprojectMapper.updateGroupproject(groupproject);
    }

    /**
     * 批量删除分组项目
     * 
     * @param rids 需要删除的分组项目ID
     * @return 结果
     */
    @Override
    public int deleteGroupprojectByIds(Long[] rids)
    {
        return groupprojectMapper.deleteGroupprojectByIds(rids);
    }

    /**
     * 删除分组项目信息
     * 
     * @param rid 分组项目ID
     * @return 结果
     */
    @Override
    public int deleteGroupprojectById(Long rid)
    {
        return groupprojectMapper.deleteGroupprojectById(rid);
    }
}
