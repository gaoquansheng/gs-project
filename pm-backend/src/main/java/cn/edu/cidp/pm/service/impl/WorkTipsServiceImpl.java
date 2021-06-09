package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.WorkTipsMapper;
import cn.edu.cidp.pm.domain.WorkTips;
import cn.edu.cidp.pm.service.IWorkTipsService;

/**
 * 工作提示Service业务层处理
 * 
 * @author william
 * @date 2020-08-19
 */
@Service
public class WorkTipsServiceImpl implements IWorkTipsService 
{
    @Autowired
    private WorkTipsMapper workTipsMapper;



    /**
     * 查询工作提示列表
     * 
     * @param
     * @return 工作提示
     */
    @Override
    public List<WorkTips> selectWorkTipsList(String userName)
    {
        return workTipsMapper.selectWorkTipsList(userName);
    }

}
