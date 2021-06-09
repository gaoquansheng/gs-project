package cn.edu.cidp.pm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 正文内容对象 projectwordcontent
 *
 * @author zhangmingjie
 * @date 2020-09-08
 */
public class Projectwordcontent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 项目类别
     */
    private String categoryCode;

    /**
     * 管理流程
     */
    private String phaseCode;

    /**
     * 书签
     */
    private String bookmarkCode;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 文字内容
     */
    private String textContent;

    /**
     * word路径
     */
    private String attachedWordPath;

    /**
     * 申报年份
     */
    private Long reportYear;

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setBookmarkCode(String bookmarkCode) {
        this.bookmarkCode = bookmarkCode;
    }

    public String getBookmarkCode() {
        return bookmarkCode;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getAttachedWordPath() {
        return attachedWordPath;
    }

    public void setAttachedWordPath(String attachedWordpath) {
        this.attachedWordPath = attachedWordpath;
    }

    public void setReportYear(Long reportYear) {
        this.reportYear = reportYear;
    }

    public Long getReportYear() {
        return reportYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryCode", getCategoryCode())
                .append("phaseCode", getPhaseCode())
                .append("bookmarkCode", getBookmarkCode())
                .append("projectId", getProjectId())
                .append("textContent", getTextContent())
                .append("attachedWordPath", getAttachedWordPath())
                .append("reportYear", getReportYear())
                .toString();
    }
}
