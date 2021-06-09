package cn.edu.cidp.pm.service.impl;

import cn.edu.cidp.pm.domain.Projectwordcontent;
import cn.edu.cidp.pm.mapper.ProjectwordcontentMapper;
import cn.edu.cidp.pm.service.IProjectwordcontentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectwordcontentServiceImpl implements IProjectwordcontentService {

    @Autowired
    private ProjectwordcontentMapper projectwordcontentMapper;

    @Override
    public int insertProjectwordcontent(Projectwordcontent projectwordcontent) {
        return projectwordcontentMapper.insertProjectwordcontent(projectwordcontent);
    }

    /**
     * 查询正文内容
     *
     * @param projectId 正文内容ID
     * @return 正文内容
     */
    @Override
    public List<Projectwordcontent> selectProjectwordcontentList(Long projectId,String phaseCode)
    {
        return projectwordcontentMapper.selectProjectwordcontentList(projectId,phaseCode);
    }

    @Override
    public int checkUnique(Projectwordcontent projectwordcontent) {
        return projectwordcontentMapper.checkUnique(projectwordcontent);
    }

    @Override
    public int updateProjectwordcontent(Projectwordcontent projectwordcontent) {
        return projectwordcontentMapper.updateProjectwordcontent(projectwordcontent);
    }
}
