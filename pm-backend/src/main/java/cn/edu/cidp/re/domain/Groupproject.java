package cn.edu.cidp.re.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 分组项目对象 groupproject
 * 
 * @author william
 * @date 2021-01-19
 */
public class Groupproject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long rid;

    /** 组id */
    @Excel(name = "组id")
    private Long groupId;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

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
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("groupId", getGroupId())
            .append("projectId", getProjectId())
            .toString();
    }
}
