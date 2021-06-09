package cn.edu.cidp.pm.mapper;

import java.util.List;
import cn.edu.cidp.pm.domain.UnitReview;

/**
 * 单位项目审核Mapper接口
 *
 * @author william
 * @date 2020-10-20
 */
public interface UnitReviewMapper
{
    /**
     * 查询单位项目审核
     *
     * @param projectId 单位项目审核ID
     * @return 单位项目审核
     */
    public UnitReview selectUnitReviewById(Long projectId);

    /**
     * 查询单位项目审核列表
     *
     * @param unitReview 单位项目审核
     * @return 单位项目审核集合
     */
    public List<UnitReview> selectUnitReviewList(UnitReview unitReview);

    /**
     * 新增单位项目审核
     *
     * @param unitReview 单位项目审核
     * @return 结果
     */
    public int insertUnitReview(UnitReview unitReview);

    /**
     * 修改单位项目审核
     *
     * @param unitReview 单位项目审核
     * @return 结果
     */
    public int updateUnitReview(UnitReview unitReview);

    /**
     * 删除单位项目审核
     *
     * @param projectId 单位项目审核ID
     * @return 结果
     */
    public int deleteUnitReviewById(Long projectId);

    /**
     * 批量删除单位项目审核
     *
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteUnitReviewByIds(Long[] projectIds);

    public int insertProjectTask(UnitReview unitReview);
    public int updateProjectTask(UnitReview unitReview);
    public int deleteProjectTaskById(Long projectId);

    public int insertProjectConclusionReport(UnitReview unitReview);
    public int updateProjectConclusionReport(UnitReview unitReview);
    public int deleteProjectConclusionReportById(Long projectId);

}
