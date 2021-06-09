package cn.edu.cidp.pm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * @ClassNameProjectbudget
 * @Description
 * @Author 张明杰
 * @Date2020/9/1/001 14:54
 * @Version V1.0
 **/
public class Projectbudget extends BaseEntity {
    private static final long serialVersionUID = 1L;


    private Integer rid;


    private Long projectId;


    private String categoryCode;


    private String phaseCode;


    private Long subjectId;

    private String subjectName;


    private Long appAmount;


    private Long myAmount;


    private Long totalAmount;


    private String reasonDesc;

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

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

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setAppAmount(Long appAmount) {
        this.appAmount = appAmount;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getAppAmount() {
        return appAmount;
    }

    public void setMyAmount(Long myAmount) {
        this.myAmount = myAmount;
    }

    public Long getMyAmount() {
        return myAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public String getReasonDesc() {
        return reasonDesc;
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
                .append("appAmount", getAppAmount())
                .append("myAmount", getMyAmount())
                .append("totalAmount", getTotalAmount())
                .append("reasonDesc", getReasonDesc())
                .toString();
    }
}
