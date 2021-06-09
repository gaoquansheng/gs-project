package cn.edu.cidp.pc.service;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategoryadmins;
import cn.edu.cidp.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 项目类别管理员Service接口
 * 
 * @author william
 * @date 2020-08-06
 */
public interface IProjectcategoryadminsService 
{
    /**
     * 查询项目类别管理员
     * 
     * @param categoryCode 项目类别管理员ID
     * @return 项目类别管理员
     */
    public Projectcategoryadmins selectProjectcategoryadminsById(String categoryCode);

    public List<Projectcategoryadmins> selectProjectcategoryadminsByAdminUserName(String adminUserName);

    /**
     * 查询项目类别管理员列表
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 项目类别管理员集合
     */
    public List<Projectcategoryadmins> selectProjectcategoryadminsList(Projectcategoryadmins projectcategoryadmins);

    /**
     * 新增项目类别管理员
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 结果
     */
    public int insertProjectcategoryadmins(Projectcategoryadmins projectcategoryadmins);

    /**
     * 修改项目类别管理员
     * 
     * @param projectcategoryadmins 项目类别管理员
     * @return 结果
     */
    public int updateProjectcategoryadmins(Projectcategoryadmins projectcategoryadmins);

    /**
     * 批量删除项目类别管理员
     * 
     * @param categoryCodes 需要删除的项目类别管理员ID
     * @return 结果
     */
    public int deleteProjectcategoryadminsByIds(String[] categoryCodes);

    /**
     * 删除项目类别管理员信息
     * 
     * @param categoryCode 项目类别管理员ID
     * @return 结果
     */
    public int deleteProjectcategoryadminsById(String categoryCode);
    /**

    *@ClassName IProjectcategoryadminsService

    *@Description 删除单个值

    *@Author 张明杰

    *@Date 2020/8/7/007 9:54

    *@Version V1.0

    **/

    public int deleteProjectcategoryadminsByIdAndName(String categoryCode, String[] adminUserCode);


    public int insertProjectcategoryadminsByList(String categoryCode, String categoryName,List<SysUser> users);

    public int checkUnique(String categoryCode ,List<String> adminUserCodes);
}
