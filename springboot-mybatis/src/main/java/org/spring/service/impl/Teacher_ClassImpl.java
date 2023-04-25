package org.spring.service.impl;

import org.spring.mapper.Teacher_ClassMapper;
import org.spring.service.TeacherService;
import org.spring.service.Teacher_ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Teacher_ClassImpl implements Teacher_ClassService {
    @Autowired
    Teacher_ClassMapper teacher_classMapper;
    @Override
    public int insertClass(String id, String classid) {
        return teacher_classMapper.insertClass(id,classid);
    }
}
