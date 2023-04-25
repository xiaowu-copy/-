package org.spring.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;

import java.util.List;


public interface NewsService {
    public PageInfo<News> findByPageService(int pageCode, int pageSize);

    public News getItemById(int id);
    public String getAuthor_Name(int id);
    public int View(@Param("id") int id);

    public int Upload(News news);

    public List<News> Cheack();

    public int Approved(@Param("id") int id);

    public int Failed(@Param("id") int id);


}
