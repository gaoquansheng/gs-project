package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.WorkTips;

/**
 * 工作提示Service接口
 * 
 * @author william
 * @date 2020-08-19
 */
public interface IWorkTipsService 
{

    /**
     * 查询工作提示列表
     * 
     * @param
     * @return 工作提示集合
     */
    public List<WorkTips> selectWorkTipsList(String userName);

}
