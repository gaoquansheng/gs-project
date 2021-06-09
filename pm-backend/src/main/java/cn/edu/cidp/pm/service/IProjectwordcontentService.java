package cn.edu.cidp.pm.service;

import cn.edu.cidp.pm.domain.Projectwordcontent;

import java.util.List;

public interface IProjectwordcontentService {
    /**
     * 新增。插入
     */
    public int insertProjectwordcontent(Projectwordcontent projectwordcontent);
    /**
     * 查询正文内容
     *
     * @param projectId 正文内容ID
     * @return 正文内容
     */
    public List<Projectwordcontent> selectProjectwordcontentList(Long projectId,String phaseCode);


    public int checkUnique(Projectwordcontent projectwordcontent);

    /**
     * 更新接口
     * @param projectwordcontent
     * @return
     */
    public int updateProjectwordcontent(Projectwordcontent projectwordcontent);
}
