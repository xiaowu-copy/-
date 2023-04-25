package org.spring.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;
import org.spring.domain.Picture;
import org.spring.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/seletAll")
    public List<Picture> SelectAll(){
        return pictureService.selectAll();
    }
    @PostMapping("/insert")
    public int InsertPivture(Picture picture){
        int ll = pictureService.insert(picture);
        int id = picture.getId();
        String time = picture.getTime();
        int author_id = picture.getAuthor_id();
        //下面的这个方法是插入到教师图片表当中.
        System.out.println("ffff"+time);
        pictureService.InsertTeacher_P(author_id,id,time);

        return ll;
    }
    @RequestMapping(value = "/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<Picture> findByPage(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize){
        PageInfo<Picture> pageInfo = pictureService.findByPageService(pageCode, pageSize);
        return pageInfo;

    }



}
