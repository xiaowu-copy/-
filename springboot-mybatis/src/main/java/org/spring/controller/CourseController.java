package org.spring.controller;

import org.spring.domain.Course;
import org.spring.domain.News;
import org.spring.service.CourseService;
import org.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService  courseService ;

    @RequestMapping("/selectAll")
    public List<Course> SelectAll(){

        return courseService.selectAll();
    }
}
