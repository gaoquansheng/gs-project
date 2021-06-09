package cn.edu.cidp.re.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 分配专家对象 groupexpert
 * 
 * @author william
 * @date 2021-01-19
 */
public class Groupexpert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long rid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long groupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String expertUserCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String expertUserName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String expertRole;

    public void setRid(Long rid) 
    {
        this.rid = rid;
    }

    public Long getRid() 
    {
        return rid;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setExpertUserCode(String expertUserCode) 
    {
        this.expertUserCode = expertUserCode;
    }

    public String getExpertUserCode() 
    {
        return expertUserCode;
    }
    public void setExpertUserName(String expertUserName) 
    {
        this.expertUserName = expertUserName;
    }

    public String getExpertUserName() 
    {
        return expertUserName;
    }
    public void setExpertRole(String expertRole) 
    {
        this.expertRole = expertRole;
    }

    public String getExpertRole() 
    {
        return expertRole;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("groupId", getGroupId())
            .append("expertUserCode", getExpertUserCode())
            .append("expertUserName", getExpertUserName())
            .append("expertRole", getExpertRole())
            .toString();
    }
}
