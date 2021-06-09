package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.UnitReview;

/**
 * 单位项目审核Service接口
 * 
 * @author william
 * @date 2020-10-20
 */
public interface IUnitReviewService 
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
     * 批量删除单位项目审核
     * 
     * @param projectIds 需要删除的单位项目审核ID
     * @return 结果
     */
    public int deleteUnitReviewByIds(Long[] projectIds);

    /**
     * 删除单位项目审核信息
     * 
     * @param projectId 单位项目审核ID
     * @return 结果
     */
    public int deleteUnitReviewById(Long projectId);
}
