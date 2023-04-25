package org.spring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.spring.domain.News;
import org.spring.mapper.NewsMapper;
import org.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public PageInfo<News> findByPageService(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<News> classInfos = newsMapper.selectAll();
        return new PageInfo<>(classInfos);

    }

    @Override
    public News getItemById(int id) {
        return newsMapper.getItemById(id);
    }
    //获取发布人的姓名的方法
    @Override
    public String getAuthor_Name(int id) {
        return newsMapper.getAuthor_Name(id);
    }

    @Override
    public int View(int id) {
        return newsMapper.View(id);
    }

    @Override
    public int Upload(News news) {
        return newsMapper.Upload(news);
    }

    @Override
    public List<News> Cheack() {
        return newsMapper.Cheack();
    }

    @Override
    public int Approved(int id) {
        return newsMapper.Approved(id);
    }

    public int Failed(int id) {
        return newsMapper.Failed(id);
    }
}
