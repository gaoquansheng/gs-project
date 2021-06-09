package cn.edu.cidp.pm.mapper;

import cn.edu.cidp.pm.domain.Projectwordcontent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectwordcontentMapper {
    /**
     * 新增、插入
     */
    public int insertProjectwordcontent(Projectwordcontent projectwordcontent);
    /**
     * 查询正文内容
     *
     * @param projectId 正文内容ID
     * @return 正文内容
     */
    public List<Projectwordcontent> selectProjectwordcontentList(@Param("projectId") Long projectId,@Param("phaseCode") String phaseCode);
    /**
     * 校验书签是否已经存在
     */
    public int checkUnique(Projectwordcontent projectwordcontent);

    /**
     * 更新接口
     */
    public int updateProjectwordcontent(Projectwordcontent projectwordcontent);


    public int insertProjectwordcontentList(List<Projectwordcontent> projectwordcontents);

    public int deleteProjectwordcontentList(Long projectId,String phaseCode);

}
