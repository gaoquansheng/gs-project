package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Workflowphase;

/**
 * 管理流程Service接口
 * 
 * @author william
 * @date 2020-08-10
 */
public interface IWorkflowphaseService 
{
    /**
     * 查询管理流程
     * 
     * @param phaseCode 管理流程ID
     * @return 管理流程
     */
    public Workflowphase selectWorkflowphaseById(String phaseCode);

    /**
     * 查询管理流程列表
     * 
     * @param workflowphase 管理流程
     * @return 管理流程集合
     */
    public List<Workflowphase> selectWorkflowphaseList();

    /**
     * 新增管理流程
     * 
     * @param workflowphase 管理流程
     * @return 结果
     */
    public int insertWorkflowphase(Workflowphase workflowphase);

    /**
     * 修改管理流程
     * 
     * @param workflowphase 管理流程
     * @return 结果
     */
    public int updateWorkflowphase(Workflowphase workflowphase);

    /**
     * 批量删除管理流程
     * 
     * @param phaseCodes 需要删除的管理流程ID
     * @return 结果
     */
    public int deleteWorkflowphaseByIds(String[] phaseCodes);

    /**
     * 删除管理流程信息
     * 
     * @param phaseCode 管理流程ID
     * @return 结果
     */
    public int deleteWorkflowphaseById(String phaseCode);
}
