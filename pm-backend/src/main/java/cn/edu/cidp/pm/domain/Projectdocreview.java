package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目附件审核对象 projectdoc
 * 
 * @author william
 * @date 2020-10-10
 */
public class Projectdocreview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文档id */
    @Excel(name = "文档id")
    private Long docId;

    /** 文档标题 */
    @Excel(name = "文档标题")
    private String docTitle;

    /** 上传文件路径 */
    @Excel(name = "上传文件路径")
    private String docPath;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 阶段流程 */
    @Excel(name = "阶段流程")
    private String phaseCode;

    /** 阶段流程 */
    @Excel(name = "阶段流程名称")
    private String phaseName;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadBy;

    /** 是否审核通过 */
    @Excel(name = "是否审核通过")
    private Integer passed;

    /** 评审意见 */
    @Excel(name = "评审意见")
    private String review;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    public void setDocId(Long docId) 
    {
        this.docId = docId;
    }

    public Long getDocId() 
    {
        return docId;
    }
    public void setDocTitle(String docTitle) 
    {
        this.docTitle = docTitle;
    }

    public String getDocTitle() 
    {
        return docTitle;
    }
    public void setDocPath(String docPath) 
    {
        this.docPath = docPath;
    }

    public String getDocPath() 
    {
        return docPath;
    }
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
    public void setPhaseCode(String phaseCode) 
    {
        this.phaseCode = phaseCode;
    }

    public String getPhaseCode() 
    {
        return phaseCode;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public String getPhaseName() {
        return phaseName;
    }
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setUploadBy(String uploadBy) 
    {
        this.uploadBy = uploadBy;
    }

    public String getUploadBy() 
    {
        return uploadBy;
    }
    public void setPassed(Integer passed) 
    {
        this.passed = passed;
    }

    public Integer getPassed() 
    {
        return passed;
    }
    public void setReview(String review) 
    {
        this.review = review;
    }

    public String getReview() 
    {
        return review;
    }
    public void setReviewBy(String reviewBy) 
    {
        this.reviewBy = reviewBy;
    }

    public String getReviewBy() 
    {
        return reviewBy;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("docId", getDocId())
            .append("docTitle", getDocTitle())
            .append("docPath", getDocPath())
            .append("projectId", getProjectId())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("phaseName", getPhaseName())
            .append("uploadTime", getUploadTime())
            .append("uploadBy", getUploadBy())
            .append("passed", getPassed())
            .append("review", getReview())
            .append("reviewBy", getReviewBy())
            .append("reviewTime", getReviewTime())
            .toString();
    }
}
