package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目类别管理员对象 projectcategoryadmins
 * 
 * @author william
 * @date 2020-08-09
 */
public class Projectcategoryadmins extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String categoryName;

    /** 管理员代码 */
    @Excel(name = "管理员代码")
    private String adminuserCode;

    /** 管理员姓名 */
    @Excel(name = "管理员姓名")
    private String adminuserName;

    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setAdminuserCode(String adminuserCode) 
    {
        this.adminuserCode = adminuserCode;
    }

    public String getAdminuserCode() 
    {
        return adminuserCode;
    }
    public void setAdminuserName(String adminuserName) 
    {
        this.adminuserName = adminuserName;
    }

    public String getAdminuserName() 
    {
        return adminuserName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("adminuserCode", getAdminuserCode())
            .append("adminuserName", getAdminuserName())
            .toString();
    }
}
