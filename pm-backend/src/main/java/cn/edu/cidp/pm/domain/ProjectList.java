package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目列表对象 project
 *
 * @author william
 * @date 2020-08-27
 */
public class ProjectList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private int projectId;

    /** 类别编码 */
    @Excel(name = "类别编码")
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

    /** 申报人Code */
    @Excel(name = "申报人Code")
    private String userCode;

    /** 申报人Name */
    @Excel(name = "申报人Name")
    private String userName;

    /** 归口管理部门Code */
    @Excel(name = "归口管理部门Code")
    private String proxyOrgCode;

    /** 归口管理部门 */
    @Excel(name = "归口管理部门")
    private String proxyOrgName;

    /** 所在部门Code */
    @Excel(name = "所在部门Code")
    private String myOrgCode;

    /** 所在部门 */
    @Excel(name = "所在部门")
    private String myOrgName;

    /** 所属领域 */
    @Excel(name = "所属领域")
    private String projectArear;

    /** 投资来源 */
    @Excel(name = "投资来源")
    private String moneyFrom;

    /** 项目优先级 */
    @Excel(name = "项目优先级")
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
    private float appAmount;

    /** 自筹经费金额 */
    @Excel(name = "自筹经费金额")
    private float myAmount;

    /** 总金额 */
    @Excel(name = "总金额")
    private float totalAmount;

    @Excel(name = "中央投资")
    private float centralAmount;

    @Excel(name = "地方投资")
    private float localAmount;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String projectStatus;

    /** 建设内容描述 */
    @Excel(name = "建设内容描述")
    private String projectSummary;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyWords;

    /** 电子文档路径 */
    @Excel(name = "电子文档路径")
    private String pdfPath;



    /** 1-5为了扩展、预留 */
    private String projecttype3;

    /** 1-5为了扩展、预留 */
    private String projecttype4;

    /** 1-5为了扩展、预留 */
    private String projecttype5;


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Long projectYear) {
        this.projectYear = projectYear;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProxyOrgCode() {
        return proxyOrgCode;
    }

    public void setProxyOrgCode(String proxyOrgCode) {
        this.proxyOrgCode = proxyOrgCode;
    }

    public String getProxyOrgName() {
        return proxyOrgName;
    }

    public void setProxyOrgName(String proxyOrgName) {
        this.proxyOrgName = proxyOrgName;
    }

    public String getMyOrgCode() {
        return myOrgCode;
    }

    public void setMyOrgCode(String myOrgCode) {
        this.myOrgCode = myOrgCode;
    }

    public String getMyOrgName() {
        return myOrgName;
    }

    public void setMyOrgName(String myOrgName) {
        this.myOrgName = myOrgName;
    }

    public String getProjectArear() {
        return projectArear;
    }

    public void setProjectArear(String projectArear) {
        this.projectArear = projectArear;
    }

    public String getMoneyFrom() {
        return moneyFrom;
    }

    public void setMoneyFrom(String moneyFrom) {
        this.moneyFrom = moneyFrom;
    }

    public String getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(String projectPriority) {
        this.projectPriority = projectPriority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public float getAppAmount() {
        return appAmount;
    }

    public void setAppAmount(float appAmount) {
        this.appAmount = appAmount;
    }

    public float getMyAmount() {
        return myAmount;
    }

    public void setMyAmount(float myAmount) {
        this.myAmount = myAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }



    public String getProjecttype3() {
        return projecttype3;
    }

    public void setProjecttype3(String projecttype3) {
        this.projecttype3 = projecttype3;
    }

    public String getProjecttype4() {
        return projecttype4;
    }

    public void setProjecttype4(String projecttype4) {
        this.projecttype4 = projecttype4;
    }

    public String getProjecttype5() {
        return projecttype5;
    }

    public void setProjecttype5(String projecttype5) {
        this.projecttype5 = projecttype5;
    }

    public float getCentralAmount() {
        return centralAmount;
    }

    public void setCentralAmount(float centralAmount) {
        this.centralAmount = centralAmount;
    }

    public float getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(float localAmount) {
        this.localAmount = localAmount;
    }

    @Override
    public String toString() {
        return "ProjectList{" +
                "projectId=" + projectId +
                ", categoryCode='" + categoryCode + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectYear=" + projectYear +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", proxyOrgCode='" + proxyOrgCode + '\'' +
                ", proxyOrgName='" + proxyOrgName + '\'' +
                ", myOrgCode='" + myOrgCode + '\'' +
                ", myOrgName='" + myOrgName + '\'' +
                ", projectArear='" + projectArear + '\'' +
                ", moneyFrom='" + moneyFrom + '\'' +
                ", projectPriority='" + projectPriority + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", appTime=" + appTime +
                ", appAmount=" + appAmount +
                ", myAmount=" + myAmount +
                ", totalAmount=" + totalAmount +
                ", centralAmount=" + centralAmount +
                ", localAmount=" + localAmount +
                ", projectStatus='" + projectStatus + '\'' +
                ", projectSummary='" + projectSummary + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", pdfPath='" + pdfPath + '\'' +
                ", projecttype3='" + projecttype3 + '\'' +
                ", projecttype4='" + projecttype4 + '\'' +
                ", projecttype5='" + projecttype5 + '\'' +
                '}';
    }
}
