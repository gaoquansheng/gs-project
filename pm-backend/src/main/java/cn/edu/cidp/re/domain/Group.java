package cn.edu.cidp.re.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 分组信息对象 group
 * 
 * @author william
 * @date 2021-01-19
 */
public class Group extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 组别id */
    private Long groupId;

    /** 名称 */
    @Excel(name = "名称")
    private String groupName;

    /** 年度 */
    @Excel(name = "年度")
    private Long groupYear;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 项目阶段 */
    @Excel(name = "项目阶段")
    private String phaseCode;

    /** 业务专家组长 */
    private String groupManagerUserCode;

    /** 预算专家组长 */
    private String budgetManagerUserCode;

    /** 添加此记录的人员UserCode */
    private String addBy;

    /** 添加此记录时间 */
    private Date addTime;

    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setGroupYear(Long groupYear) 
    {
        this.groupYear = groupYear;
    }

    public Long getGroupYear() 
    {
        return groupYear;
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
    public void setGroupManagerUserCode(String groupManagerUserCode) 
    {
        this.groupManagerUserCode = groupManagerUserCode;
    }

    public String getGroupManagerUserCode() 
    {
        return groupManagerUserCode;
    }
    public void setBudgetManagerUserCode(String budgetManagerUserCode) 
    {
        this.budgetManagerUserCode = budgetManagerUserCode;
    }

    public String getBudgetManagerUserCode() 
    {
        return budgetManagerUserCode;
    }
    public void setAddBy(String addBy) 
    {
        this.addBy = addBy;
    }

    public String getAddBy() 
    {
        return addBy;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("groupYear", getGroupYear())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("groupManagerUserCode", getGroupManagerUserCode())
            .append("budgetManagerUserCode", getBudgetManagerUserCode())
            .append("addBy", getAddBy())
            .append("addTime", getAddTime())
            .toString();
    }
}
