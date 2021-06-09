package cn.edu.cidp.pc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.TreeEntity;

/**
 * 管理流程对象 workflowphase
 * 
 * @author william
 * @date 2020-08-10
 */
public class Workflowphase extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 流程代号 */
    @Excel(name = "流程代号")
    private String phaseCode;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String phaseName;

    /** 父流程代号 */
    @Excel(name = "父流程代号")
    private String parentCode;

    /** 流程说明 */
    @Excel(name = "流程说明")
    private String phaseDesc;

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
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }
    public void setPhaseDesc(String phaseDesc) 
    {
        this.phaseDesc = phaseDesc;
    }

    public String getPhaseDesc() 
    {
        return phaseDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phaseCode", getPhaseCode())
            .append("phaseName", getPhaseName())
            .append("parentCode", getParentCode())
            .append("phaseDesc", getPhaseDesc())
            .toString();
    }
}
