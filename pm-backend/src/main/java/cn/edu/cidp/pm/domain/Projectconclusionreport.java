package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目结题报告对象 projectconclusionreport
 * 
 * @author william
 * @date 2020-09-15
 */
public class Projectconclusionreport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long projectId;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 类别编码（外键） */
    @Excel(name = "类别编码", readConverterExp = "外=键")
    private String categoryCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 结题年度 */
    @Excel(name = "结题年度")
    private Long conclusionYear;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String userCode;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String userName;

    /** 结题日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结题日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date conclusionEndDate;

    /** 归口管理部门 */
    @Excel(name = "归口管理部门")
    private String proxyOrgCode;

    /** 归口管理部门 */
    @Excel(name = "归口管理部门")
    private String proxyOrgName;

    /** 申报人所在部门 */
    @Excel(name = "申报人所在部门")
    private String myOrgCode;

    /** 申报人所在部门 */
    @Excel(name = "申报人所在部门")
    private String myOrgName;

    /** 结题报告路径 */
    @Excel(name = "结题报告路径")
    private String pdfPath;

    /** 项目状态已提交、已结题 */
    @Excel(name = "项目状态 ")
    private String projectStatus;

    /** 实际支出资金 */
    @Excel(name = "实际支出资金")
    private Long outAppAmount;

    /** 实际支出自筹经费 */
    @Excel(name = "实际支出自筹经费")
    private Long outMyAmount;

    /** 总支出 */
    @Excel(name = "总支出")
    private Long outTotalaAmount;

    /** 结题描述 */
    @Excel(name = "结题描述")
    private String summary;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setProjectCode(String projectCode)
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setConclusionYear(Long conclusionYear) 
    {
        this.conclusionYear = conclusionYear;
    }

    public Long getConclusionYear() 
    {
        return conclusionYear;
    }
    public void setUserCode(String userCode) 
    {
        this.userCode = userCode;
    }

    public String getUserCode() 
    {
        return userCode;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setConclusionEndDate(Date conclusionEndDate) 
    {
        this.conclusionEndDate = conclusionEndDate;
    }

    public Date getConclusionEndDate() 
    {
        return conclusionEndDate;
    }
    public void setProxyOrgCode(String proxyOrgCode) 
    {
        this.proxyOrgCode = proxyOrgCode;
    }

    public String getProxyOrgCode() 
    {
        return proxyOrgCode;
    }
    public void setProxyOrgName(String proxyOrgName) 
    {
        this.proxyOrgName = proxyOrgName;
    }

    public String getProxyOrgName() 
    {
        return proxyOrgName;
    }
    public void setMyOrgCode(String myOrgCode) 
    {
        this.myOrgCode = myOrgCode;
    }

    public String getMyOrgCode() 
    {
        return myOrgCode;
    }
    public void setMyOrgName(String myOrgName) 
    {
        this.myOrgName = myOrgName;
    }

    public String getMyOrgName() 
    {
        return myOrgName;
    }
    public void setPdfPath(String pdfPath) 
    {
        this.pdfPath = pdfPath;
    }

    public String getPdfPath() 
    {
        return pdfPath;
    }
    public void setProjectStatus(String projectStatus) 
    {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatus() 
    {
        return projectStatus;
    }
    public void setOutAppAmount(Long outAppAmount) 
    {
        this.outAppAmount = outAppAmount;
    }

    public Long getOutAppAmount() 
    {
        return outAppAmount;
    }
    public void setOutMyAmount(Long outMyAmount) 
    {
        this.outMyAmount = outMyAmount;
    }

    public Long getOutMyAmount() 
    {
        return outMyAmount;
    }
    public void setOutTotalaAmount(Long outTotalaAmount) 
    {
        this.outTotalaAmount = outTotalaAmount;
    }

    public Long getOutTotalaAmount() 
    {
        return outTotalaAmount;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectCode", getProjectCode())
            .append("categoryCode", getCategoryCode())
            .append("projectName", getProjectName())
            .append("conclusionYear", getConclusionYear())
            .append("userCode", getUserCode())
            .append("userName", getUserName())
            .append("conclusionEndDate", getConclusionEndDate())
            .append("proxyOrgCode", getProxyOrgCode())
            .append("proxyOrgName", getProxyOrgName())
            .append("myOrgCode", getMyOrgCode())
            .append("myOrgName", getMyOrgName())
            .append("pdfPath", getPdfPath())
            .append("projectStatus", getProjectStatus())
            .append("outAppAmount", getOutAppAmount())
            .append("outMyAmount", getOutMyAmount())
            .append("outTotalaAmount", getOutTotalaAmount())
            .append("summary", getSummary())
            .toString();
    }
}
