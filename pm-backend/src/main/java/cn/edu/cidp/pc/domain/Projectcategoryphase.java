package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目类别流程配置对象 projectcategoryphase
 * 
 * @author william
 * @date 2020-08-10
 */
public class Projectcategoryphase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联合主键 管理流程代码 */
    private String categoryCode;

    /** 联合主键 管理流程代码 */
    private String phaseCode;

    /** 流程名称 */
    private String phaseName;

    /** 当前管理流程阶段是否需要在线填写word */
    @Excel(name = "当前管理流程阶段是否需要在线填写word")
    private Integer needWord;

    /** 在线填报模板文件路径 */
    @Excel(name = "在线填报模板文件路径")
    private String wordPath;

    /** 参考下载文档模板 */
    @Excel(name = "参考下载文档模板")
    private String refFilePath;

    /** 当前管理流程阶段是否需要评审 */
    @Excel(name = "当前管理流程阶段是否需要评审")
    private Integer needReview;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer enabled;

    /** $column.columnComment */
    @Excel(name = "是否启用")
    private String parentCode;

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
    public void setPhaseName(String phaseName) 
    {
        this.phaseName = phaseName;
    }

    public String getPhaseName() 
    {
        return phaseName;
    }
    public void setNeedWord(Integer needWord) 
    {
        this.needWord = needWord;
    }

    public Integer getNeedWord() 
    {
        return needWord;
    }
    public void setWordPath(String wordPath) 
    {
        this.wordPath = wordPath;
    }

    public String getWordPath() 
    {
        return wordPath;
    }
    public void setRefFilePath(String refFilePath) 
    {
        this.refFilePath = refFilePath;
    }

    public String getRefFilePath() 
    {
        return refFilePath;
    }
    public void setNeedReview(Integer needReview) 
    {
        this.needReview = needReview;
    }

    public Integer getNeedReview() 
    {
        return needReview;
    }
    public void setEnabled(Integer enabled) 
    {
        this.enabled = enabled;
    }

    public Integer getEnabled() 
    {
        return enabled;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("phaseName", getPhaseName())
            .append("needWord", getNeedWord())
            .append("wordPath", getWordPath())
            .append("refFilePath", getRefFilePath())
            .append("needReview", getNeedReview())
            .append("enabled", getEnabled())
            .append("parentCode", getParentCode())
            .toString();
    }
}
