package org.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.Picture;

import java.util.List;

@Mapper
public interface PictureMapper {

    List<Picture> selectAll();

    int insert(Picture picture);

    int InsertTeacher_P(@Param("author_id") int author_id,@Param("picture_id") int picture_id,@Param("up_time") String up_time);

}
