package org.spring.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;
import org.spring.domain.Video;
import org.spring.mapper.VideoMapper;
import org.spring.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Override
    public List<Video> selectVideo() {
        return videoMapper.selectVidio();
    }

    @Override
    public Video getOne(int id) {
        return videoMapper.getOne(id);
    }
    public PageInfo<Video> findByPageService(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Video> classInfos = videoMapper.selectVidio();
        return new PageInfo<>(classInfos);

    }

    @Override
    public List<Video> Ranking() {
        return videoMapper.Ranking();
    }

    @Override
    public List<Video> Cheak() {
        return videoMapper.Cheak();
    }

    @Override
    public int View(int id) {
        return videoMapper.View(id);
    }
    @Override
    public int Approved(int id){

        return  videoMapper.Approved(id);
    }

    @Override
    public List<Video> Search(String key) {
        return videoMapper.Search(key);
    }
}
