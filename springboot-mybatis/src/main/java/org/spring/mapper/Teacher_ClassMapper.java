package org.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.Teacher;

@Mapper
public interface Teacher_ClassMapper {
    int insertClass(@Param("id") String id,@Param("Classid") String Classid);
}
