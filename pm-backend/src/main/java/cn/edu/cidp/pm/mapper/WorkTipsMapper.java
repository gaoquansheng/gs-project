package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.WorkTips;

/**
 * 工作提示Mapper接口
 * 
 * @author william
 * @date 2020-08-19
 */
public interface WorkTipsMapper 
{

    /**
     * 查询工作提示列表
     * 
     *
     * @return 工作提示集合
     */
    public List<WorkTips> selectWorkTipsList(String userName);


}
