package cn.edu.cidp.re.service;

import java.util.List;
import cn.edu.cidp.re.domain.Groupproject;

/**
 * 分组项目Service接口
 * 
 * @author william
 * @date 2021-01-19
 */
public interface IGroupprojectService 
{
    /**
     * 查询分组项目
     * 
     * @param rid 分组项目ID
     * @return 分组项目
     */
    public Groupproject selectGroupprojectById(Long rid);

    /**
     * 查询分组项目列表
     * 
     * @param groupproject 分组项目
     * @return 分组项目集合
     */
    public List<Groupproject> selectGroupprojectList(Groupproject groupproject);

    /**
     * 新增分组项目
     * 
     * @param groupproject 分组项目
     * @return 结果
     */
    public int insertGroupproject(Groupproject groupproject);

    /**
     * 修改分组项目
     * 
     * @param groupproject 分组项目
     * @return 结果
     */
    public int updateGroupproject(Groupproject groupproject);

    /**
     * 批量删除分组项目
     * 
     * @param rids 需要删除的分组项目ID
     * @return 结果
     */
    public int deleteGroupprojectByIds(Long[] rids);

    /**
     * 删除分组项目信息
     * 
     * @param rid 分组项目ID
     * @return 结果
     */
    public int deleteGroupprojectById(Long rid);
}
