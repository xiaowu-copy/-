package org.spring.service.impl;

import org.spring.domain.Course;
import org.spring.mapper.CourseMapper;
import org.spring.mapper.NewsMapper;
import org.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }
}
