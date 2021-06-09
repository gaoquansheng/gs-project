package cn.edu.cidp.re.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 评审配置对象 reviewconfiguration
 * 
 * @author william
 * @date 2021-01-11
 */
public class reviewConfiguration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 流程 */
    @Excel(name = "流程")
    private String phaseCode;

    /** 业务评审意见 */
    @Excel(name = "业务评审意见")
    private String businessReviewConfiguation;

    /** 预算评审意见 */
    @Excel(name = "预算评审意见")
    private String budgetReviewConfiguation;

    /** 是否评分 */
    @Excel(name = "是否评分")
    private String scoreReviewConfiguation;

    /** 是否投票: */
    @Excel(name = "是否投票:")
    private String voteReviewConfiguation;

    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setPhaseCode(String phaseCode) 
    {
        this.phaseCode = phaseCode;
    }

    public String getPhaseCode() 
    {
        return phaseCode;
    }
    public void setBusinessReviewConfiguation(String businessReviewConfiguation) 
    {
        this.businessReviewConfiguation = businessReviewConfiguation;
    }

    public String getBusinessReviewConfiguation() 
    {
        return businessReviewConfiguation;
    }
    public void setBudgetReviewConfiguation(String budgetReviewConfiguation) 
    {
        this.budgetReviewConfiguation = budgetReviewConfiguation;
    }

    public String getBudgetReviewConfiguation() 
    {
        return budgetReviewConfiguation;
    }
    public void setScoreReviewConfiguation(String scoreReviewConfiguation) 
    {
        this.scoreReviewConfiguation = scoreReviewConfiguation;
    }

    public String getScoreReviewConfiguation() 
    {
        return scoreReviewConfiguation;
    }
    public void setVoteReviewConfiguation(String voteReviewConfiguation) 
    {
        this.voteReviewConfiguation = voteReviewConfiguation;
    }

    public String getVoteReviewConfiguation() 
    {
        return voteReviewConfiguation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("businessReviewConfiguation", getBusinessReviewConfiguation())
            .append("budgetReviewConfiguation", getBudgetReviewConfiguation())
            .append("scoreReviewConfiguation", getScoreReviewConfiguation())
            .append("voteReviewConfiguation", getVoteReviewConfiguation())
            .toString();
    }
}
