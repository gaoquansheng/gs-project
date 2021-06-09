package cn.edu.cidp.pm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.edu.cidp.framework.aspectj.lang.annotation.Excel;
import cn.edu.cidp.framework.web.domain.BaseEntity;

/**
 * 新闻对象 news
 * 
 * @author william
 * @date 2020-07-29
 */
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻ID */
    private Long newsid;

    /** 标题 */
    @Excel(name = "标题")
    private String newstitle;

    /** 内容 */
    @Excel(name = "内容")
    private String newscontent;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adddate = new Date();

    /**  */
    @Excel(name = "")
    private Long clicks = 0L;

    /** $column.columnComment */
    @Excel(name = "")
    private String usercode;

    public void setNewsid(Long newsid) 
    {
        this.newsid = newsid;
    }

    public Long getNewsid() 
    {
        return newsid;
    }
    public void setNewstitle(String newstitle) 
    {
        this.newstitle = newstitle;
    }

    public String getNewstitle() 
    {
        return newstitle;
    }
    public void setNewscontent(String newscontent) 
    {
        this.newscontent = newscontent;
    }

    public String getNewscontent() 
    {
        return newscontent;
    }
    public void setAdddate(Date adddate) 
    {
        this.adddate = adddate;
    }

    public Date getAdddate() 
    {
        return adddate;
    }
    public void setClicks(Long clicks) 
    {
        this.clicks = clicks;
    }

    public Long getClicks() 
    {
        return clicks;
    }
    public void setUsercode(String usercode) 
    {
        this.usercode = usercode;
    }

    public String getUsercode() 
    {
        return usercode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsid", getNewsid())
            .append("newstitle", getNewstitle())
            .append("newscontent", getNewscontent())
            .append("adddate", getAdddate())
            .append("clicks", getClicks())
            .append("usercode", getUsercode())
            .toString();
    }
}
