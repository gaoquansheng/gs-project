package cn.edu.cidp.re.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * projectcategoryreviewrulevoteoption对象 projectcategoryreviewrulevoteoption
 * 
 * @author william
 * @date 2021-01-12
 */
public class Projectcategoryreviewrulevoteoption extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long optionId;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 项目流程 */
    @Excel(name = "项目流程")
    private String phaseCode;

    /** 投票流程 */
    @Excel(name = "投票流程")
    private String optionTitle;

    public void setOptionId(Long optionId) 
    {
        this.optionId = optionId;
    }

    public Long getOptionId() 
    {
        return optionId;
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
    public void setOptionTitle(String optionTitle) 
    {
        this.optionTitle = optionTitle;
    }

    public String getOptionTitle() 
    {
        return optionTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("optionId", getOptionId())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("optionTitle", getOptionTitle())
            .toString();
    }
}
