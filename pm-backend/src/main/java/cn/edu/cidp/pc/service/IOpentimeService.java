package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Opentime;

/**
 * 项目起止时间Service接口
 * 
 * @author william
 * @date 2020-08-11
 */
public interface IOpentimeService 
{
    /**
     * 查询项目起止时间
     * 
     * @param rId 项目起止时间ID
     * @return 项目起止时间
     */
    public Opentime selectOpentimeById(Long rId);

    /**
     * 查询项目起止时间列表
     * 
     * @param opentime 项目起止时间
     * @return 项目起止时间集合
     */
    public List<Opentime> selectOpentimeList(Opentime opentime);

    /**
     * 新增项目起止时间
     * 
     * @param opentime 项目起止时间
     * @return 结果
     */
    public int insertOpentime(Opentime opentime);

    /**
     * 修改项目起止时间
     * 
     * @param opentime 项目起止时间
     * @return 结果
     */
    public int updateOpentime(Opentime opentime);

    /**
     * 批量删除项目起止时间
     * 
     * @param rIds 需要删除的项目起止时间ID
     * @return 结果
     */
    public int deleteOpentimeByIds(Long[] rIds);

    /**
     * 删除项目起止时间信息
     * 
     * @param rId 项目起止时间ID
     * @return 结果
     */
    public int deleteOpentimeById(Long rId);
}
