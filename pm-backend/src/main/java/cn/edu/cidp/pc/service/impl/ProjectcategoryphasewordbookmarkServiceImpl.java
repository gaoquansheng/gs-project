package cn.edu.cidp.pc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pc.mapper.ProjectcategoryphasewordbookmarkMapper;
import cn.edu.cidp.pc.domain.Projectcategoryphasewordbookmark;
import cn.edu.cidp.pc.service.IProjectcategoryphasewordbookmarkService;

/**
 * Word模板设置Service业务层处理
 * 
 * @author william
 * @date 2020-08-04
 */
@Service
public class ProjectcategoryphasewordbookmarkServiceImpl implements IProjectcategoryphasewordbookmarkService 
{
    @Autowired
    private ProjectcategoryphasewordbookmarkMapper projectcategoryphasewordbookmarkMapper;

    /**
     * 查询Word模板设置
     * 
     * @param categoryCode Word模板设置ID
     * @return Word模板设置
     */
    @Override
    public Projectcategoryphasewordbookmark selectProjectcategoryphasewordbookmarkById(String categoryCode,String phaseCode,String bookMarkCode)
    {
        return projectcategoryphasewordbookmarkMapper.selectProjectcategoryphasewordbookmarkById(categoryCode,phaseCode,bookMarkCode);
    }

    /**
     * 查询Word模板设置列表
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return Word模板设置
     */
    @Override
    public List<Projectcategoryphasewordbookmark> selectProjectcategoryphasewordbookmarkList(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        return projectcategoryphasewordbookmarkMapper.selectProjectcategoryphasewordbookmarkList(projectcategoryphasewordbookmark);
    }

    /**
     * 新增Word模板设置
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return 结果
     */
    @Override
    public int insertProjectcategoryphasewordbookmark(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        return projectcategoryphasewordbookmarkMapper.insertProjectcategoryphasewordbookmark(projectcategoryphasewordbookmark);
    }

    /**
     * 修改Word模板设置
     * 
     * @param projectcategoryphasewordbookmark Word模板设置
     * @return 结果
     */
    @Override
    public int updateProjectcategoryphasewordbookmark(Projectcategoryphasewordbookmark projectcategoryphasewordbookmark)
    {
        return projectcategoryphasewordbookmarkMapper.updateProjectcategoryphasewordbookmark(projectcategoryphasewordbookmark);
    }

    /**
     * 批量删除Word模板设置
     * 
     * @param categoryCodes 需要删除的Word模板设置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryphasewordbookmarkByIds(List<Projectcategoryphasewordbookmark> projectcategoryphasewordbookmarks)
    {
        return projectcategoryphasewordbookmarkMapper.deleteProjectcategoryphasewordbookmarkByIds(projectcategoryphasewordbookmarks);
    }

    /**
     * 删除Word模板设置信息
     * 
     * @param categoryCode Word模板设置ID
     * @return 结果
     */
    @Override
    public int deleteProjectcategoryphasewordbookmarkById(String categoryCode)
    {
        return projectcategoryphasewordbookmarkMapper.deleteProjectcategoryphasewordbookmarkById(categoryCode);
    }

    @Override
    public List<Projectcategoryphasewordbookmark> selectPhbmByCode(String categoryCode, String phaseCode) {
        return projectcategoryphasewordbookmarkMapper.selectPhbmByCode(categoryCode,phaseCode);
    }
}
