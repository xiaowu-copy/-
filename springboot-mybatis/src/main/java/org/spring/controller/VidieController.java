package org.spring.controller;


import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.controller.tools.NonStaticResourceHttpRequestHandler;
import org.spring.domain.News;
import org.spring.domain.Video;
import org.spring.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
//@CrossOrigin 是允许跨域的进行
@CrossOrigin
@RestController
@RequestMapping("/class")
public class VidieController {
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    @Autowired
    public VideoService videoService;

    public VidieController(NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler) {
        this.nonStaticResourceHttpRequestHandler = nonStaticResourceHttpRequestHandler;
    }


    @CrossOrigin
    @RequestMapping(value = "/{pageCode}/{pageSize}",method = RequestMethod.GET)
    //@PathVariable(value = "paheSize") int pageSize 这段代码的意思是从请求的 路径中取值，并且赋值给 int型的pageSize
    public PageInfo<Video> findByPage(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize){

        PageInfo<Video> pageInfo = videoService.findByPageService(pageCode, pageSize);
        return pageInfo;

    }
    @RequestMapping("/find")
    public List<Video> selectClass() throws IOException, ServletException {
        Video video;
        //获得所有的视频的信息
        List<Video> videos = videoService.selectVideo();

        return videos;

        }
    //    查找一个视频的信息方法
    @RequestMapping("/getOne")
    public Video getOne(@Param("id") int id){
        return videoService.getOne(id);
    }

    //浏览次数的增加方法
    @RequestMapping("/View")
    public int View(@Param("id") int id){
        return videoService.View(id);
    }

    @RequestMapping("/Ranking")
    public List<Video> Ranking(){
        return videoService.Ranking();
    }

//    搜索方法
    @RequestMapping("/Search")
    public List<Video> Search(@Param("key") String key){
        return videoService.Search(key);
    }

//    管理员方法，获取没审核的课程
    @RequestMapping("/Cheack")
    public List<Video> Cheak(){
        return videoService.Cheak();
    }

    @RequestMapping("/Approved")
    public int Approved(@Param("id") int id){
        return videoService.Approved(id);
    }


}







