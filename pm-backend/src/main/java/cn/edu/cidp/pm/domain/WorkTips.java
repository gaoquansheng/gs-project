package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 工作提示对象 opentime
 *
 * @author william
 * @date 2020-08-19
 */
public class WorkTips extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long rId;

    /** 开始年份 */
    @Excel(name = "开始年份")
    private Long openYear;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 项目阶段 */
    @Excel(name = "项目阶段")
    private String phaseCode;

    /** 项目阶段名称 */
    @Excel(name = "项目阶段名称")
    private String phaseName;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    @Excel(name = "项目Id")
    private Integer projectId;

    @Excel(name = "项目状态")
    private String projectStatus;

    @Excel(name = "项目类别名称")
    private String categoryName;

    @Excel(name = "项目年度")
    private Integer projectYear;

    @Excel(name = "项目名称")
    private String projectName;

    @Override
    public String toString() {
        return "WorkTips{" +
                "rId=" + rId +
                ", openYear=" + openYear +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", phaseCode='" + phaseCode + '\'' +
                ", phaseName='" + phaseName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", projectId=" + projectId +
                ", projectStatus='" + projectStatus + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", projectYear=" + projectYear +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public void setrId(Long rId)
    {
        this.rId = rId;
    }

    public Long getrId()
    {
        return rId;
    }
    public void setOpenYear(Long openYear)
    {
        this.openYear = openYear;
    }

    public Long getOpenYear()
    {
        return openYear;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setPhaseCode(String phaseCode)
    {
        this.phaseCode = phaseCode;
    }

    public String getPhaseCode()
    {
        return phaseCode;
    }
    public void setCategoryCode(String categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode()
    {
        return categoryCode;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Integer projectYear) {
        this.projectYear = projectYear;
    }
}
