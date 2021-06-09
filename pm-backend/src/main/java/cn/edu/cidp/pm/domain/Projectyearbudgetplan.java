package cn.edu.cidp.pm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目年度预算对象 projectyearbudgetplan
 *
 * @author william
 * @date 2020-09-13
 */
public class Projectyearbudgetplan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long rid;


    private String categoryCode;

    private String phaseCode;

    /** 项目ID */
    private Long projectId;

    /** 年度 */
    @Excel(name = "年度")
    private Long budgetYear;

    /** 本年度专项经费 */
    @Excel(name = "本年度中央投资")
    private float centralAmountBudget;

    /** 本年度专项经费 */
    @Excel(name = "本年度地方投资")
    private float LocalAmountBudget;

    /** 本年度自筹经费 */
    @Excel(name = "本年度自筹投资")
    private float myAmountBudget;

    /** 本年度总经费 */
    @Excel(name = "本年度总投资")
    private float totalAmountBudget;

    /** 专项经费支出比例 */
    @Excel(name = "专项经费支出比例")
    private float appBudgetPercent;

    /** 总预算支出比例 */
    @Excel(name = "总预算支出比例")
    private float totalBudgetPercent;

    @Override
    public String toString() {
        return "Projectyearbudgetplan{" +
                "rid=" + rid +
                ", categoryCode='" + categoryCode + '\'' +
                ", phaseCode='" + phaseCode + '\'' +
                ", projectId=" + projectId +
                ", budgetYear=" + budgetYear +
                ", centralAmountBudget=" + centralAmountBudget +
                ", LocalAmountBudget=" + LocalAmountBudget +
                ", myAmountBudget=" + myAmountBudget +
                ", totalAmountBudget=" + totalAmountBudget +
                ", appBudgetPercent=" + appBudgetPercent +
                ", totalBudgetPercent=" + totalBudgetPercent +
                '}';
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(Long budgetYear) {
        this.budgetYear = budgetYear;
    }

    public float getCentralAmountBudget() {
        return centralAmountBudget;
    }

    public void setCentralAmountBudget(float centralAmountBudget) {
        this.centralAmountBudget = centralAmountBudget;
    }

    public float getLocalAmountBudget() {
        return LocalAmountBudget;
    }

    public void setLocalAmountBudget(float localAmountBudget) {
        LocalAmountBudget = localAmountBudget;
    }

    public float getMyAmountBudget() {
        return myAmountBudget;
    }

    public void setMyAmountBudget(float myAmountBudget) {
        this.myAmountBudget = myAmountBudget;
    }

    public float getTotalAmountBudget() {
        return totalAmountBudget;
    }

    public void setTotalAmountBudget(float totalAmountBudget) {
        this.totalAmountBudget = totalAmountBudget;
    }

    public float getAppBudgetPercent() {
        return appBudgetPercent;
    }

    public void setAppBudgetPercent(float appBudgetPercent) {
        this.appBudgetPercent = appBudgetPercent;
    }

    public float getTotalBudgetPercent() {
        return totalBudgetPercent;
    }

    public void setTotalBudgetPercent(float totalBudgetPercent) {
        this.totalBudgetPercent = totalBudgetPercent;
    }
}
