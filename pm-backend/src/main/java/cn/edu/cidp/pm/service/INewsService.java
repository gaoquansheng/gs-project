package cn.edu.cidp.pm.service;

import java.util.List;
import cn.edu.cidp.pm.domain.News;

/**
 * 新闻Service接口
 * 
 * @author william
 * @date 2020-07-29
 */
public interface INewsService 
{
    /**
     * 查询新闻
     * 
     * @param newsid 新闻ID
     * @return 新闻
     */
    public News selectNewsById(Long newsid);

    /**
     * 查询新闻列表
     * 
     * @param news 新闻
     * @return 新闻集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增新闻
     * 
     * @param news 新闻
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改新闻
     * 
     * @param news 新闻
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除新闻
     * 
     * @param newsids 需要删除的新闻ID
     * @return 结果
     */
    public int deleteNewsByIds(Long[] newsids);

    /**
     * 删除新闻信息
     * 
     * @param newsid 新闻ID
     * @return 结果
     */
    public int deleteNewsById(Long newsid);
}
