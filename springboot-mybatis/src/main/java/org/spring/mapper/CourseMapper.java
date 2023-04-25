package org.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.spring.domain.Course;

import java.util.List;

@Mapper
public interface CourseMapper {
    public List<Course> selectAll();
}
