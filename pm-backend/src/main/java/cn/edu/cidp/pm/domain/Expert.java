package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 专家对象 expert
 * 
 * @author william
 * @date 2021-05-31
 */
public class Expert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long expertid;

    /** 专家姓名 */
    @Excel(name = "专家姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private Integer sex;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 职称 */
    @Excel(name = "职称")
    private String jobTitle;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String work;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 专业领域 */
    @Excel(name = "专业领域")
    private Integer specialty;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 通讯地址 */
    @Excel(name = "通讯地址")
    private String address;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    public void setExpertid(Long expertid) 
    {
        this.expertid = expertid;
    }

    public Long getExpertid() 
    {
        return expertid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(Integer sex) 
    {
        this.sex = sex;
    }

    public Integer getSex() 
    {
        return sex;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() 
    {
        return jobTitle;
    }
    public void setWork(String work) 
    {
        this.work = work;
    }

    public String getWork() 
    {
        return work;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setSpecialty(Integer specialty) 
    {
        this.specialty = specialty;
    }

    public Integer getSpecialty() 
    {
        return specialty;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expertid", getExpertid())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("jobTitle", getJobTitle())
            .append("work", getWork())
            .append("phone", getPhone())
            .append("specialty", getSpecialty())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("idCard", getIdCard())
            .toString();
    }
}
