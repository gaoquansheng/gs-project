package cn.edu.cidp.pm.domain.vo;

import cn.edu.cidp.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameProjectbudgetVo
 * @Description
 * @Author
 * @Date2020/9/1/001 16:13
 * @Version V1.0
 **/
public class ProjectbudgetVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer rid;

    private Integer projectId;

    private String categoryCode;

    private String phaseCode;

    private Long subjectId;

    private Long parentSubjectId;

    private String subjectName;

    private float appAmount;

    private float myAmount;

    private float totalAmount;

    private String reasonDesc;


    public List<ProjectbudgetVo> getChildrenBudget() {
        return childrenBudget;
    }

    public void setChildrenBudget(List<ProjectbudgetVo> childrenBudget) {
        this.childrenBudget = childrenBudget;
    }

    private List<ProjectbudgetVo> childrenBudget = new ArrayList<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getParentSubjectId() {
        return parentSubjectId;
    }

    public void setParentSubjectId(Long parentSubjectId) {
        this.parentSubjectId = parentSubjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rid", getRid())
                .append("projectId", getProjectId())
                .append("categoryCode", getCategoryCode())
                .append("phaseCode", getPhaseCode())
                .append("subjectId", getSubjectId())
                .append("subjectName",getSubjectName())
                .append("parentSubjectId",getParentSubjectId())
                .append("subjectName",getSubjectName())
                .append("appAmount", getAppAmount())
                .append("myAmount", getMyAmount())
                .append("totalAmount", getTotalAmount())
                .append("reasonDesc", getReasonDesc())
                .append("childrenBudget",getChildrenBudget())
                .toString();
    }
}
