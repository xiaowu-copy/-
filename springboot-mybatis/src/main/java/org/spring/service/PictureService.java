package org.spring.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;
import org.spring.domain.Picture;

import java.util.List;

public interface PictureService {
    public int insert(Picture picture);
    public PageInfo<Picture> findByPageService(int pageCode, int pageSize);
    List<Picture> selectAll();

    public int InsertTeacher_P(int author_id,int picture_id,String up_time);
}
