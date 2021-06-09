package cn.edu.cidp.pm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 添加合作单位对象 projectorg
 *
 * @author william
 * @date 2020-09-13
 */
public class Projectorg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long rid;

    /** 项目ID */
    private Long projectId;

    /** 项目类别 */
    private String categoryCode;

    /** 阶段代码 */
    private String phaseCode;

    /** 代码编码 */
    @Excel(name = "代码编码")
    private String orgCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String orgName;

    /** 单位联系人 */
    @Excel(name = "单位联系人")
    private String orgAdminname;

    /** 单位电话 */
    @Excel(name = "单位电话")
    private String orgTel;

    public void setRid(Long rid)
    {
        this.rid = rid;
    }

    public Long getRid()
    {
        return rid;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getOrgCode()
    {
        return orgCode;
    }
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOrgName()
    {
        return orgName;
    }
    public void setOrgAdminname(String orgAdminname)
    {
        this.orgAdminname = orgAdminname;
    }

    public String getOrgAdminname()
    {
        return orgAdminname;
    }
    public void setOrgTel(String orgTel)
    {
        this.orgTel = orgTel;
    }

    public String getOrgTel()
    {
        return orgTel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("projectId", getProjectId())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("orgAdminname", getOrgAdminname())
            .append("orgTel", getOrgTel())
            .toString();
    }
}
