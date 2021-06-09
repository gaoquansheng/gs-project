package cn.edu.cidp.pc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.WorkflowphaseMapper;
import cn.edu.cidp.pc.domain.Workflowphase;
import cn.edu.cidp.pc.service.IWorkflowphaseService;

/**
 * 管理流程Service业务层处理
 * 
 * @author william
 * @date 2020-08-10
 */
@Service
public class WorkflowphaseServiceImpl implements IWorkflowphaseService 
{
    @Autowired
    private WorkflowphaseMapper workflowphaseMapper;

    /**
     * 查询管理流程
     * 
     * @param phaseCode 管理流程ID
     * @return 管理流程
     */
    @Override
    public Workflowphase selectWorkflowphaseById(String phaseCode)
    {
        return workflowphaseMapper.selectWorkflowphaseById(phaseCode);
    }

    /**
     * 查询管理流程列表
     * 
     * @param workflowphase 管理流程
     * @return 管理流程
     */
    @Override
    public List<Workflowphase> selectWorkflowphaseList()
    {
        return workflowphaseMapper.selectWorkflowphaseList();
    }

    /**
     * 新增管理流程
     * 
     * @param workflowphase 管理流程
     * @return 结果
     */
    @Override
    public int insertWorkflowphase(Workflowphase workflowphase)
    {
        return workflowphaseMapper.insertWorkflowphase(workflowphase);
    }

    /**
     * 修改管理流程
     * 
     * @param workflowphase 管理流程
     * @return 结果
     */
    @Override
    public int updateWorkflowphase(Workflowphase workflowphase)
    {
        return workflowphaseMapper.updateWorkflowphase(workflowphase);
    }

    /**
     * 批量删除管理流程
     * 
     * @param phaseCodes 需要删除的管理流程ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowphaseByIds(String[] phaseCodes)
    {
        return workflowphaseMapper.deleteWorkflowphaseByIds(phaseCodes);
    }

    /**
     * 删除管理流程信息
     * 
     * @param phaseCode 管理流程ID
     * @return 结果
     */
    @Override
    public int deleteWorkflowphaseById(String phaseCode)
    {
        return workflowphaseMapper.deleteWorkflowphaseById(phaseCode);
    }
}
