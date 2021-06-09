package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * Word模板设置对象 projectcategoryphasewordbookmark
 * 
 * @author william
 * @date 2020-08-04
 */
public class Projectcategoryphasewordbookmark extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String categoryCode;

    /** 管理流程 */
    @Excel(name = "管理流程")
    private String phaseCode;
    /** 项目类别名称 */
    @Excel(name = "项目阶段名称")
    private String phaseName;

    /** 书签英文 */
    @Excel(name = "书签英文")
    private String bookMarkCode;

    /** 书签中文 */
    @Excel(name = "书签中文")
    private String bookMarkName;

    /** 书签填报说明 */
    @Excel(name = "书签填报说明")
    private String bookMarkDesc;

    /** 内容类型 */
    @Excel(name = "内容类型")
    private String contentType;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private Integer isEnable;

    /** 排列顺序 */
    @Excel(name = "排列顺序")
    private Integer orderSeq;

    /** Word模板路径 */
    @Excel(name = "Word模板路径")
    private String refWordPath;

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
    public void setBookMarkCode(String bookMarkCode) 
    {
        this.bookMarkCode = bookMarkCode;
    }

    public String getPhaseName() {
        return phaseName;
    }
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getBookMarkCode() 
    {
        return bookMarkCode;
    }
    public void setBookMarkName(String bookMarkName) 
    {
        this.bookMarkName = bookMarkName;
    }

    public String getBookMarkName() 
    {
        return bookMarkName;
    }
    public void setBookMarkDesc(String bookMarkDesc) 
    {
        this.bookMarkDesc = bookMarkDesc;
    }

    public String getBookMarkDesc() 
    {
        return bookMarkDesc;
    }
    public void setContentType(String contentType) 
    {
        this.contentType = contentType;
    }

    public String getContentType() 
    {
        return contentType;
    }
    public void setIsEnable(Integer isEnable) 
    {
        this.isEnable = isEnable;
    }

    public Integer getIsEnable() 
    {
        return isEnable;
    }
    public void setOrderSeq(Integer orderSeq) 
    {
        this.orderSeq = orderSeq;
    }

    public Integer getOrderSeq() 
    {
        return orderSeq;
    }
    public void setRefWordPath(String refWordPath) 
    {
        this.refWordPath = refWordPath;
    }

    public String getRefWordPath() 
    {
        return refWordPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("phaseName", getPhaseName())
            .append("bookMarkCode", getBookMarkCode())
            .append("bookMarkName", getBookMarkName())
            .append("bookMarkDesc", getBookMarkDesc())
            .append("contentType", getContentType())
            .append("isEnable", getIsEnable())
            .append("orderSeq", getOrderSeq())
            .append("refWordPath", getRefWordPath())
            .toString();
    }
}
