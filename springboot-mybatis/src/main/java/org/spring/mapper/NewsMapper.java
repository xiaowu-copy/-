package org.spring.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;

import java.util.List;

@Mapper
public interface NewsMapper {
    public List<News> selectAll();

    public News getItemById(int id);

    public String getAuthor_Name(int id);

    public int View(@Param("id") int id);

    public int Upload(News news);

    public List<News> Cheack();

    public int Approved(@Param("id") int id);

    public int Failed(@Param("id") int id);
}
