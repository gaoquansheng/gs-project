package cn.edu.cidp.pc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目起止时间对象 opentime
 * 
 * @author william
 * @date 2020-09-14-10：49
 */
public class Opentime extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long rId;

    /** 开始年份 */
    @Excel(name = "开始年份")
    private Long openYear;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30,dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 项目阶段 */
    @Excel(name = "项目阶段")
    private String phaseCode;
    /** 项目类别名称 */
    @Excel(name = "项目阶段名称")
    private String phaseName;
    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;
    /** 项目类别名称 */
    @Excel(name = "项目类别名称")
    private String categoryName;

    @Override
    public String toString() {
        return "Opentime{" +
                "rId=" + rId +
                ", openYear=" + openYear +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", phaseCode='" + phaseCode + '\'' +
                ", phaseName='" + phaseName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getOpenYear() {
        return openYear;
    }

    public void setOpenYear(Long openYear) {
        this.openYear = openYear;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
