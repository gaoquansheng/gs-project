package cn.edu.cidp.pm.domain;

/**
 * @ClassNameSteps
 * @Description
 * @Author
 * @Date2020/8/18/018 16:18
 * @Version V1.0
 **/
public class Steps {


    private String stepid;
    private String stepName;
    private String projectCode;
    private String contentType;
    private Integer selfFinancing;
    private String categoryCode;
    private String bookmarkCode;
    private String phaseCode;
    private String bookmarkDesc;

    private String StartDate;
    private String EndDate;

    public Steps(String projectCode, String categoryCode, String phaseCode) {
        this.projectCode = projectCode;
        this.categoryCode = categoryCode;
        this.phaseCode = phaseCode;
    }

    public String getStepid() {
        return stepid;
    }

    public void setStepid(String stepid) {
        this.stepid = stepid;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getSelfFinancing() {
        return selfFinancing;
    }

    public void setSelfFinancing(Integer selfFinancing) {
        this.selfFinancing = selfFinancing;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBookmarkCode() {
        return bookmarkCode;
    }

    public void setBookmarkCode(String bookmarkCode) {
        this.bookmarkCode = bookmarkCode;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getBookmarkDesc() {
        return bookmarkDesc;
    }

    public void setBookmarkDesc(String bookmarkDesc) {
        this.bookmarkDesc = bookmarkDesc;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
