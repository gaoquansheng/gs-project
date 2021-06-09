package cn.edu.cidp.pm.service.impl;

import java.util.List;

import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.ProjectbudgetMapper;
import cn.edu.cidp.pm.domain.Projectbudget;
import cn.edu.cidp.pm.service.IProjectbudgetService;

@Service
public class ProjectbudgetServiceImpl implements IProjectbudgetService {
    @Autowired
    private ProjectbudgetMapper projectbudgetMapper;
    /**
     * 查询projectBudget列表
     *
     * @return projectBudget
     */
    @Override
    public List<ProjectbudgetVo> selectProjectbudgetList(String phaseCode,Long projectId,String categoryCode)
    {
        return projectbudgetMapper.selectProjectbudgetList(phaseCode,projectId,categoryCode);
    }

    @Override
    public int addProjectbudget(List<ProjectbudgetVo> projectbudgetVo) {
        return projectbudgetMapper.addProjectbudget(projectbudgetVo);
    }


    @Override
    public int deleteProjectbudget(int projectId,String phaseCode) {
        return projectbudgetMapper.deleteProjectbudget(projectId,phaseCode);
    }
}
