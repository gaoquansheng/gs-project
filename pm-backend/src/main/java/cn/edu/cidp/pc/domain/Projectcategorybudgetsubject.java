package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.TreeEntity;

/**
 * 项目类别预算对象 projectcategorybudgetsubject
 * 
 * @author william
 * @date 2020-08-10
 */
public class Projectcategorybudgetsubject extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 预算ID */
    @Excel(name = "预算ID")
    private Long subjectId;

    /** 科目名称 */
    @Excel(name = "科目名称")
    private String subjectName;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer enabled;

    /** 父科目类别 */
    @Excel(name = "父科目类别")
    private Long parentSubjectId;

    /** 类别填报说明 */
    @Excel(name = "类别填报说明")
    private String subjectDesc;

    public void setSubjectId(Long subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() 
    {
        return subjectId;
    }
    public void setSubjectName(String subjectName) 
    {
        this.subjectName = subjectName;
    }

    public String getSubjectName() 
    {
        return subjectName;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setEnabled(Integer enabled) 
    {
        this.enabled = enabled;
    }

    public Integer getEnabled() 
    {
        return enabled;
    }
    public void setParentSubjectId(Long parentSubjectId) 
    {
        this.parentSubjectId = parentSubjectId;
    }

    public Long getParentSubjectId() 
    {
        return parentSubjectId;
    }
    public void setSubjectDesc(String subjectDesc) 
    {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectDesc() 
    {
        return subjectDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("subjectId", getSubjectId())
            .append("subjectName", getSubjectName())
            .append("categoryCode", getCategoryCode())
            .append("enabled", getEnabled())
            .append("parentSubjectId", getParentSubjectId())
            .append("subjectDesc", getSubjectDesc())
            .toString();
    }
}
