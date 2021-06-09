package cn.edu.cidp.pm.controller;

import java.util.Date;
import java.util.List;

import cn.edu.cidp.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.cidp.framework.aspectj.lang.annotation.Log;
import cn.edu.cidp.framework.aspectj.lang.enums.BusinessType;
import cn.edu.cidp.pm.domain.News;
import cn.edu.cidp.pm.service.INewsService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 新闻Controller
 * 
 * @author william
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/pm/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('pm:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('pm:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(News news)
    {
        List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        return util.exportExcel(list, "news");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('pm:news:query')")
    @GetMapping(value = "/{newsid}")
    public AjaxResult getInfo(@PathVariable("newsid") Long newsid)
    {
        return AjaxResult.success(newsService.selectNewsById(newsid));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('pm:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news)
    {
        //插入或修改时，如果数据库中某些字段需要默认值，需要在control中设置，当前用户信息，科研通过SecurityUtils获取
        news.setUsercode(SecurityUtils.getUsername());
        news.setClicks(0L);
        news.setCreateTime(new Date());
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('pm:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('pm:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsids}")
    public AjaxResult remove(@PathVariable Long[] newsids)
    {
        return toAjax(newsService.deleteNewsByIds(newsids));
    }
}
