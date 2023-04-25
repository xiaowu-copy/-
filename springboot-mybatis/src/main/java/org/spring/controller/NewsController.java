package org.spring.controller;

import com.github.pagehelper.PageInfo;

import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;
import org.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @CrossOrigin
    @RequestMapping(value = "/{pageCode}/{pageSize}",method = RequestMethod.GET)
    //@PathVariable(value = "paheSize") int pageSize 这段代码的意思是从请求的 路径中取值，并且赋值给 int型的pageSize
    public PageInfo<News> findByPage(@PathVariable(value = "pageCode") int pageCode,@PathVariable(value = "pageSize") int pageSize){

        PageInfo<News> pageInfo = newsService.findByPageService(pageCode, pageSize);
        return pageInfo;

    }

    @RequestMapping("/find/{id}")
    public News findByPage(@PathVariable(value = "id") int id){

        News news = newsService.getItemById(id);
        int author_id = news.getAuthorId();
        String author_name = newsService.getAuthor_Name(author_id);
        System.out.println(author_name);
        news.setAuthor_Name(author_name);
        return news;
    }

    //浏览次数的增加方法
    @RequestMapping("/View")
    public int View(@Param("id") int id){
        return newsService.View(id);
    }

    //上传相应的资源的方法 根本不需要@RequestBody注解呀
    @PostMapping("/Upload")
    public int Upload(News news){
        System.out.println(news.getContent()+news.getTitle()+news.getType());
        return newsService.Upload(news);
    }

    @RequestMapping("/Cheack")
    public List<News> Cheack(){
        return newsService.Cheack();
    }
    //审核通过
    @RequestMapping("/Approved")
    public int Approved(@Param("id") int id){
        return newsService.Approved(id);
    }

    //审核失败
    @RequestMapping("/Failed")
    public int Failed(@Param("id") int id){
        return newsService.Failed(id);
    }

}
