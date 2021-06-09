package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目类别对象 projectcategory
 * 
 * @author william
 * @date 2020-08-06
 */
public class Projectcategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别代号 */
    @Excel(name = "类别代号")
    private String categoryCode;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer enabled;

    /** 开始月份 */
    @Excel(name = "开始月份")
    private Integer startMonth;

    /** 开始日 */
    @Excel(name = "开始日")
    private Integer startDay;

    /** 结束月份 */
    @Excel(name = "结束月份")
    private Integer endMonth;

    /** 结束日 */
    @Excel(name = "结束日")
    private Integer endDay;

    /** 详细经费 */
    @Excel(name = "详细经费")
    private Integer hasBudgetPlan;

    /** 年度预算支出计划 */
    @Excel(name = "年度预算支出计划")
    private Integer hasBudgetYearplan;

    /** 预算编制说明 */
    @Excel(name = "预算编制说明")
    private Integer hasBudgetDesc;

    /** 自筹经费 */
    @Excel(name = "自筹经费")
    private Integer hasSelfFinancing;

    /** 合作单位 */
    @Excel(name = "合作单位")
    private Integer hasOrgs;

    /** 成员添加 */
    @Excel(name = "成员添加")
    private Integer hasMembers;

    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setEnabled(Integer enabled) 
    {
        this.enabled = enabled;
    }

    public Integer getEnabled() 
    {
        return enabled;
    }
    public void setStartMonth(Integer startMonth) 
    {
        this.startMonth = startMonth;
    }

    public Integer getStartMonth() 
    {
        return startMonth;
    }
    public void setStartDay(Integer startDay) 
    {
        this.startDay = startDay;
    }

    public Integer getStartDay() 
    {
        return startDay;
    }
    public void setEndMonth(Integer endMonth) 
    {
        this.endMonth = endMonth;
    }

    public Integer getEndMonth() 
    {
        return endMonth;
    }
    public void setEndDay(Integer endDay) 
    {
        this.endDay = endDay;
    }

    public Integer getEndDay() 
    {
        return endDay;
    }
    public void setHasBudgetPlan(Integer hasBudgetPlan) 
    {
        this.hasBudgetPlan = hasBudgetPlan;
    }

    public Integer getHasBudgetPlan() 
    {
        return hasBudgetPlan;
    }
    public void setHasBudgetYearplan(Integer hasBudgetYearplan) 
    {
        this.hasBudgetYearplan = hasBudgetYearplan;
    }

    public Integer getHasBudgetYearplan() 
    {
        return hasBudgetYearplan;
    }
    public void setHasBudgetDesc(Integer hasBudgetDesc) 
    {
        this.hasBudgetDesc = hasBudgetDesc;
    }

    public Integer getHasBudgetDesc() 
    {
        return hasBudgetDesc;
    }
    public void setHasSelfFinancing(Integer hasSelfFinancing) 
    {
        this.hasSelfFinancing = hasSelfFinancing;
    }

    public Integer getHasSelfFinancing() 
    {
        return hasSelfFinancing;
    }
    public void setHasOrgs(Integer hasOrgs) 
    {
        this.hasOrgs = hasOrgs;
    }

    public Integer getHasOrgs() 
    {
        return hasOrgs;
    }
    public void setHasMembers(Integer hasMembers) 
    {
        this.hasMembers = hasMembers;
    }

    public Integer getHasMembers() 
    {
        return hasMembers;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("enabled", getEnabled())
            .append("startMonth", getStartMonth())
            .append("startDay", getStartDay())
            .append("endMonth", getEndMonth())
            .append("endDay", getEndDay())
            .append("hasBudgetPlan", getHasBudgetPlan())
            .append("hasBudgetYearplan", getHasBudgetYearplan())
            .append("hasBudgetDesc", getHasBudgetDesc())
            .append("hasSelfFinancing", getHasSelfFinancing())
            .append("hasOrgs", getHasOrgs())
            .append("hasMembers", getHasMembers())
            .toString();
    }
}
