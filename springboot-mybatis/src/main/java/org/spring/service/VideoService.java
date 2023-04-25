package org.spring.service;



import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.News;
import org.spring.domain.Video;

import java.util.List;

public interface VideoService {
    public List<Video> selectVideo();

    public Video getOne(@Param("id") int id);

//    增加浏览次数的接口方法
    public int View(@Param("id") int id);

    public PageInfo<Video> findByPageService(int pageCode, int pageSize);

    public List<Video> Ranking();

    public List<Video> Cheak();

    public int Approved(@Param("id") int id);

    public List<Video> Search(@Param("key") String key);

}
