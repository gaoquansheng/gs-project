package cn.edu.cidp.pc.mapper;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategoryadmins;
import cn.edu.cidp.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 项目类别管理员Mapper接口
 * 
 * @author william
 * @date 2020-08-06
 */
public interface ProjectcategoryadminsMapper 
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
     * 删除项目类别管理员
     * 
     * @param categoryCode 项目类别管理员ID
     * @return 结果
     */
    public int deleteProjectcategoryadminsById(String categoryCode);

    /**
     * 批量删除项目类别管理员
     * 
     * @param categoryCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectcategoryadminsByIds(String[] categoryCodes);

    /**


    *@Description 根据两个Code批量删除项目管理员

    *@Author 张明杰

    *@Date 2020/8/9/009 14:58

    *@Version V1.0

    **/
    public int deleteProjectcategoryadminsByIdAndName(@Param("categoryCode") String categoryCode, @Param("adminUserCode") String[] adminUserCode);

    /**
     * 批量添加项目类别管理员
     *
     * @param categoryCode
     * @param categoryName
     * @param users
     * @return 结果
     */
    public int insertProjectcategoryadminsByList(@Param("categoryCode") String categoryCode,@Param("categoryName") String categoryName,@Param("users") List<SysUser> users);


    /**
     * 检查是否唯一
     *
     */
    public int checkUnique(@Param("categoryCode") String categoryCode ,@Param("adminUserCodes") List<String> adminUserCodes);
}
