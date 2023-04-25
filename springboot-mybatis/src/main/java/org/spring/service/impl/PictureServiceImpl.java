package org.spring.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.spring.domain.News;
import org.spring.domain.Picture;
import org.spring.mapper.PictureMapper;
import org.spring.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;
    @Override
    public List<Picture> selectAll() {
        return pictureMapper.selectAll();
    }

    @Override
    public int InsertTeacher_P(int author_id, int picture_id, String up_time) {
        return pictureMapper.InsertTeacher_P(author_id,picture_id,up_time);
    }

    @Override
    public int insert(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    public PageInfo<Picture> findByPageService(int pageCode, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode,pageSize);
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        List<Picture> classInfos = pictureMapper.selectAll();
        return new PageInfo<>(classInfos);

    }
}
