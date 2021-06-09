package cn.edu.cidp.pc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.OpentimeMapper;
import cn.edu.cidp.pc.domain.Opentime;
import cn.edu.cidp.pc.service.IOpentimeService;

/**
 * 项目起止时间Service业务层处理
 * 
 * @author william
 * @date 2020-08-11
 */
@Service
public class OpentimeServiceImpl implements IOpentimeService 
{
    @Autowired
    private OpentimeMapper opentimeMapper;

    /**
     * 查询项目起止时间
     * 
     * @param rId 项目起止时间ID
     * @return 项目起止时间
     */
    @Override
    public Opentime selectOpentimeById(Long rId)
    {
        return opentimeMapper.selectOpentimeById(rId);
    }

    /**
     * 查询项目起止时间列表
     * 
     * @param opentime 项目起止时间
     * @return 项目起止时间
     */
    @Override
    public List<Opentime> selectOpentimeList(Opentime opentime)
    {
        return opentimeMapper.selectOpentimeList(opentime);
    }

    /**
     * 新增项目起止时间
     * 
     * @param opentime 项目起止时间
     * @return 结果
     */
    @Override
    public int insertOpentime(Opentime opentime)
    {
        return opentimeMapper.insertOpentime(opentime);
    }

    /**
     * 修改项目起止时间
     * 
     * @param opentime 项目起止时间
     * @return 结果
     */
    @Override
    public int updateOpentime(Opentime opentime)
    {
        return opentimeMapper.updateOpentime(opentime);
    }

    /**
     * 批量删除项目起止时间
     * 
     * @param rIds 需要删除的项目起止时间ID
     * @return 结果
     */
    @Override
    public int deleteOpentimeByIds(Long[] rIds)
    {
        return opentimeMapper.deleteOpentimeByIds(rIds);
    }

    /**
     * 删除项目起止时间信息
     * 
     * @param rId 项目起止时间ID
     * @return 结果
     */
    @Override
    public int deleteOpentimeById(Long rId)
    {
        return opentimeMapper.deleteOpentimeById(rId);
    }
}
