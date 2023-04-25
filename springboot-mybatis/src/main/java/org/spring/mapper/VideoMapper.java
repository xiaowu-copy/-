package org.spring.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.Video;

import java.util.List;

@Mapper
public interface VideoMapper {
    public List<Video> selectVidio();

    public Video getOne(@Param("id") int id);


//    这里是增加浏览次数的方法
    public int View(@Param("id") int id);

    public List<Video> Ranking();

    public List<Video> Cheak();


    public int Approved(@Param("id") int id);
    public List<Video> Search(@Param("key") String key);

}
