package cn.edu.cidp.pc.mapper;

import java.util.List;
import cn.edu.cidp.pc.domain.Projectcategoryphasewordbookmark;
import org.apache.ibatis.annotations.Param;

/**
 * Word模板设置Mapper接口
 * 
 * @author william
 * @date 2020-08-04
 */
public interface ProjectcategoryphasewordbookmarkMapper 
{
    /**
     * 查询Word模板设置
     * 
     * @param categoryCode Word模板设置ID
     * @return Word模板设置
     */
    public Projectcategoryphasewordbookmark selectProjectcategoryphasewordbookmarkById(String categoryCode,String phaseCode,String bookMarkCode);

    /**
     * 查询Word模板设置列表
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return Word模板设置集合
     */
    public List<Projectcategoryphasewordbookmark> selectProjectcategoryphasewordbookmarkList(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark);

    /**
     * 新增Word模板设置
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return 结果
     */
    public int insertProjectcategoryphasewordbookmark(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark);

    /**
     * 修改Word模板设置
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return 结果
     */
    public int updateProjectcategoryphasewordbookmark(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark);

    /**
     * 删除Word模板设置
     * 
     * @param categoryCode Word模板设置ID
     * @return 结果
     */
    public int deleteProjectcategoryphasewordbookmarkById(String categoryCode);

    /**
     * 批量删除Word模板设置
     * 
     * @param categoryCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectcategoryphasewordbookmarkByIds(List<Projectcategoryphasewordbookmark> projectcategoryphasewordbookmarks);

    /**
     * 批量查询书签
     *
     * @param
     * @return 结果
     */

    public List<Projectcategoryphasewordbookmark> selectPhbmByCode(@Param("categoryCode") String categoryCode,@Param("phaseCode") String phaseCode);
}
