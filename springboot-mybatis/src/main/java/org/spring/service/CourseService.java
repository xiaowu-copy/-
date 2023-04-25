package org.spring.service;

import org.spring.domain.Course;
import org.spring.mapper.CourseMapper;

import java.util.List;

public interface CourseService {
    public List<Course> selectAll();
}
