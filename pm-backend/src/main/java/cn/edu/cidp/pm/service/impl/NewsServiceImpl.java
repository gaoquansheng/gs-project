package cn.edu.cidp.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.cidp.pm.mapper.NewsMapper;
import cn.edu.cidp.pm.domain.News;
import cn.edu.cidp.pm.service.INewsService;

/**
 * 新闻Service业务层处理
 * 
 * @author william
 * @date 2020-07-29
 */
@Service
public class NewsServiceImpl implements INewsService 
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询新闻
     * 
     * @param newsid 新闻ID
     * @return 新闻
     */
    @Override
    public News selectNewsById(Long newsid)
    {
        return newsMapper.selectNewsById(newsid);
    }

    /**
     * 查询新闻列表
     * 
     * @param news 新闻
     * @return 新闻
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增新闻
     * 
     * @param news 新闻
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改新闻
     * 
     * @param news 新闻
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除新闻
     * 
     * @param newsids 需要删除的新闻ID
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(Long[] newsids)
    {
        return newsMapper.deleteNewsByIds(newsids);
    }

    /**
     * 删除新闻信息
     * 
     * @param newsid 新闻ID
     * @return 结果
     */
    @Override
    public int deleteNewsById(Long newsid)
    {
        return newsMapper.deleteNewsById(newsid);
    }
}
