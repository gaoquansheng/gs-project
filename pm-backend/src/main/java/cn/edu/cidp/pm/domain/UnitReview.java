package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 单位项目审核对象 project
 * 
 * @author william
 * @date 2020-10-20
 */
public class UnitReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增1 */
    private Long projectId;

    /** 类别 */
    @Excel(name = "类别")
    private String categoryCode;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 申报年度 */
    @Excel(name = "申报年度")
    private Long projectYear;

    /** 申报人 */
    @Excel(name = "申报人")
    private String userCode;

    /** 申报人 */
    @Excel(name = "申报人")
    private String userName;

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

    /** 所属领域 */
    @Excel(name = "所属领域")
    private String projectArear;

    /** 投资来源 */
    @Excel(name = "投资来源")
    private String moneyFrom;

    /** 项目优先级   数据字典 */
    @Excel(name = "项目优先级   数据字典")
    private String projectPriority;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appTime;

    /** 申请经费金额 */
    @Excel(name = "申请经费金额")
    private Long appAmount;

    /** 自筹经费金额 */
    @Excel(name = "自筹经费金额")
    private Long myAmount;

    /** 总金额 */
    @Excel(name = "总金额")
    private Long totalAmount;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String projectStatus;

    /** 建设内容描述 */
    @Excel(name = "建设内容描述")
    private String projectSummary;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keywords;

    /** 电子文档路径 */
    @Excel(name = "电子文档路径")
    private String pdfPath;

    /** 1-5为了扩展、预留 */
    private String projecttype1;

    /** 1-5为了扩展、预留 */
    private String projecttype2;

    /** 1-5为了扩展、预留 */
    private String projecttype3;

    /** 1-5为了扩展、预留 */
    private String projecttype4;

    /** 1-5为了扩展、预留 */
    private String projecttype5;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setProjectCode(String projectCode) 
    {
        this.projectCode = projectCode;
    }

    public String getProjectCode() 
    {
        return projectCode;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectYear(Long projectYear) 
    {
        this.projectYear = projectYear;
    }

    public Long getProjectYear() 
    {
        return projectYear;
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
    public void setProjectArear(String projectArear) 
    {
        this.projectArear = projectArear;
    }

    public String getProjectArear() 
    {
        return projectArear;
    }
    public void setMoneyFrom(String moneyFrom) 
    {
        this.moneyFrom = moneyFrom;
    }

    public String getMoneyFrom() 
    {
        return moneyFrom;
    }
    public void setProjectPriority(String projectPriority) 
    {
        this.projectPriority = projectPriority;
    }

    public String getProjectPriority() 
    {
        return projectPriority;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setAppTime(Date appTime) 
    {
        this.appTime = appTime;
    }

    public Date getAppTime() 
    {
        return appTime;
    }
    public void setAppAmount(Long appAmount) 
    {
        this.appAmount = appAmount;
    }

    public Long getAppAmount() 
    {
        return appAmount;
    }
    public void setMyAmount(Long myAmount) 
    {
        this.myAmount = myAmount;
    }

    public Long getMyAmount() 
    {
        return myAmount;
    }
    public void setTotalAmount(Long totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmount() 
    {
        return totalAmount;
    }
    public void setProjectStatus(String projectStatus) 
    {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatus() 
    {
        return projectStatus;
    }
    public void setProjectSummary(String projectSummary) 
    {
        this.projectSummary = projectSummary;
    }

    public String getProjectSummary() 
    {
        return projectSummary;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setPdfPath(String pdfPath) 
    {
        this.pdfPath = pdfPath;
    }

    public String getPdfPath() 
    {
        return pdfPath;
    }
    public void setProjecttype1(String projecttype1) 
    {
        this.projecttype1 = projecttype1;
    }

    public String getProjecttype1() 
    {
        return projecttype1;
    }
    public void setProjecttype2(String projecttype2) 
    {
        this.projecttype2 = projecttype2;
    }

    public String getProjecttype2() 
    {
        return projecttype2;
    }
    public void setProjecttype3(String projecttype3) 
    {
        this.projecttype3 = projecttype3;
    }

    public String getProjecttype3() 
    {
        return projecttype3;
    }
    public void setProjecttype4(String projecttype4) 
    {
        this.projecttype4 = projecttype4;
    }

    public String getProjecttype4() 
    {
        return projecttype4;
    }
    public void setProjecttype5(String projecttype5) 
    {
        this.projecttype5 = projecttype5;
    }

    public String getProjecttype5() 
    {
        return projecttype5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("categoryCode", getCategoryCode())
            .append("projectCode", getProjectCode())
            .append("projectName", getProjectName())
            .append("projectYear", getProjectYear())
            .append("userCode", getUserCode())
            .append("userName", getUserName())
            .append("proxyOrgCode", getProxyOrgCode())
            .append("proxyOrgName", getProxyOrgName())
            .append("myOrgCode", getMyOrgCode())
            .append("myOrgName", getMyOrgName())
            .append("projectArear", getProjectArear())
            .append("moneyFrom", getMoneyFrom())
            .append("projectPriority", getProjectPriority())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("appTime", getAppTime())
            .append("appAmount", getAppAmount())
            .append("myAmount", getMyAmount())
            .append("totalAmount", getTotalAmount())
            .append("projectStatus", getProjectStatus())
            .append("projectSummary", getProjectSummary())
            .append("keywords", getKeywords())
            .append("pdfPath", getPdfPath())
            .append("projecttype1", getProjecttype1())
            .append("projecttype2", getProjecttype2())
            .append("projecttype3", getProjecttype3())
            .append("projecttype4", getProjecttype4())
            .append("projecttype5", getProjecttype5())
            .toString();
    }
}
