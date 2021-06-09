package cn.edu.cidp.re.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 评分设置对象 projectcategoryreviewrulescoreoption
 * 
 * @author william
 * @date 2021-01-12
 */
public class Projectcategoryreviewrulescoreoption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long scoreId;

    /** 类别代码 */
    @Excel(name = "类别代码")
    private String categoryCode;

    /** 阶段代码 */
    @Excel(name = "阶段代码")
    private String phaseCode;

    /** 评分项 */
    @Excel(name = "评分项")
    private String scoreTitle;

    /** 最低分 */
    @Excel(name = "最低分")
    private Long minScore;

    /** 最高分 */
    @Excel(name = "最高分")
    private Long maxScore;

    /** 评分描述 */
    @Excel(name = "评分描述")
    private String scroreDesc;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }
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
    public void setScoreTitle(String scoreTitle) 
    {
        this.scoreTitle = scoreTitle;
    }

    public String getScoreTitle() 
    {
        return scoreTitle;
    }
    public void setMinScore(Long minScore) 
    {
        this.minScore = minScore;
    }

    public Long getMinScore() 
    {
        return minScore;
    }
    public void setMaxScore(Long maxScore) 
    {
        this.maxScore = maxScore;
    }

    public Long getMaxScore() 
    {
        return maxScore;
    }
    public void setScroreDesc(String scroreDesc) 
    {
        this.scroreDesc = scroreDesc;
    }

    public String getScroreDesc() 
    {
        return scroreDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scoreId", getScoreId())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("scoreTitle", getScoreTitle())
            .append("minScore", getMinScore())
            .append("maxScore", getMaxScore())
            .append("scroreDesc", getScroreDesc())
            .toString();
    }
}
