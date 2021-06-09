package cn.edu.cidp.pm.mapper;


import java.util.List;

import cn.edu.cidp.pm.domain.Projectbudget;
import cn.edu.cidp.pm.domain.vo.ProjectbudgetVo;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;

/**
 * @ClassNameProjectbudgetMapper
 * @Description
 * @Author
 * @Date2020/9/1/001 14:58
 * @Version V1.0
 **/
public interface ProjectbudgetMapper {
    /**
     * 查询projectBudget列表
     *
     * @return projectBudget集合
     */
    public List<ProjectbudgetVo> selectProjectbudgetList(@Param("phaseCode") String phaseCode ,@Param("projectId") Long projectId,@Param("categoryCode") String categoryCode);

    //新增项目预算
    public int addProjectbudget(@Param("projectbudgetVo") List<ProjectbudgetVo> projectbudgetVo);

    //删除项目预算
    public int deleteProjectbudget(@Param("projectId") int projectId,@Param("phaseCode") String phaseCode);

    public List<Projectbudget> selectProjectbudgets(@Param("projectId") Long projectId,@Param("phaseCode") String phaseCode);


    public int insertProjectbudgetList(List<Projectbudget> projectbudgets);

    public int deleteProjectbudgetList(Long projectId,String phaseCode);


}
