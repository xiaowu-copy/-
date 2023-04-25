package org.spring.controller;

import org.apache.ibatis.annotations.Param;
import org.spring.service.Teacher_ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tc")
public class Teacher_ClassController {
    @Autowired
    Teacher_ClassService teacher_classService;

    @RequestMapping("/insert")
    public void insertClass(@Param("id") String id,@Param("Classid") String Classid){
        String  str[]  = Classid.split(",");
        for (String i:str) {
            teacher_classService.insertClass(id,i);
        }


    }
}
