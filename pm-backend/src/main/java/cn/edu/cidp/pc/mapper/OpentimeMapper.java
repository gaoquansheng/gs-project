package cn.edu.cidp.pc.mapper;

import java.util.List;
import cn.edu.cidp.pc.domain.Opentime;
import org.apache.ibatis.annotations.Param;

/**
 * 项目起止时间Mapper接口
 *
 * @author william
 * @date 2020-08-11
 */
public interface OpentimeMapper
{
    /**
     * 查询项目起止时间
     *
     * @param rId 项目起止时间ID
     * @return 项目起止时间
     */
    public Opentime selectOpentimeById(@Param("rId") Long rId);

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
     * 删除项目起止时间
     *
     * @param rId 项目起止时间ID
     * @return 结果
     */
    public int deleteOpentimeById(Long rId);

    /**
     * 批量删除项目起止时间
     *
     * @param rIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOpentimeByIds(Long[] rIds);
}
