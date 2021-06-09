package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.Projectbudget;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;

public interface IProjectbudgetService {

    /**
     * 查询projectBudget列表
     *
     * @return projectBudget集合
     */
    public List<ProjectbudgetVo> selectProjectbudgetList(String phaseCode,Long projectId,String categoryCode);


    //新增项目预算
    public int addProjectbudget(List<ProjectbudgetVo> projectbudgetVo);

    //删除项目预算
    public int deleteProjectbudget(int projectId,String phaseCode);
}
