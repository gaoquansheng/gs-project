package cn.edu.cidp.pm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 项目组成员对象 projectmeber
 *
 * @author william
 * @date 2020-09-13
 */
public class Projectmeber extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long rid;

    /** 项目id */
    private Long projectId;

    /** ProjectCategoryPhase */
    private String categoryCode;

    /** ProjectCategoryPhase */
    private String phaseCode;

    /** 如果是系统内用户，直接填写用户代码，否则为null */
    @Excel(name = "如果是系统内用户，直接填写用户代码，否则为null")
    private String userCode;

    /** 成员姓名 */
    @Excel(name = "成员姓名")
    private String userName;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 学历，在项目执行期间可能变化 */
    @Excel(name = "学历，在项目执行期间可能变化")
    private String education;

    /** 职称，在项目执行期间可能变化 */
    @Excel(name = "职称，在项目执行期间可能变化")
    private String title;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 工作时间 */
    @Excel(name = "工作时间")
    private Long workMonth;

    /** 任务分工 */
    @Excel(name = "任务分工")
    private String task;

    /** 用户所在单位代码，系统外单位为NULL */
    @Excel(name = "用户所在单位代码，系统外单位为NULL")
    private String orgCode;

    /** 用户所在单位名称 */
    @Excel(name = "用户所在单位名称")
    private String orgName;

    /** 是否为项目负责人 */
    @Excel(name = "是否为项目负责人")
    private Long isLeader;

    /** 人员排序 */
    @Excel(name = "人员排序")
    private Long orderNum;

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
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserCode()
    {
        return userCode;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setAge(Long age)
    {
        this.age = age;
    }

    public Long getAge()
    {
        return age;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getEducation()
    {
        return education;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setMajor(String major)
    {
        this.major = major;
    }

    public String getMajor()
    {
        return major;
    }

    public Long getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(Long workMonth) {
        this.workMonth = workMonth;
    }

    public void setTask(String task)
    {
        this.task = task;
    }

    public String getTask()
    {
        return task;
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
    public void setIsLeader(Long isLeader)
    {
        this.isLeader = isLeader;
    }

    public Long getIsLeader()
    {
        return isLeader;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum()
    {
        return orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("rid", getRid())
            .append("projectId", getProjectId())
            .append("categoryCode", getCategoryCode())
            .append("phaseCode", getPhaseCode())
            .append("userCode", getUserCode())
            .append("userName", getUserName())
            .append("age", getAge())
            .append("sex", getSex())
            .append("education", getEducation())
            .append("title", getTitle())
            .append("major", getMajor())
            .append("workMonth", getWorkMonth())
            .append("task", getTask())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("isLeader", getIsLeader())
            .append("orderNum", getOrderNum())
            .toString();
    }
}
