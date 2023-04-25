package org.spring.service.impl;

import org.apache.ibatis.annotations.Param;
import org.spring.domain.Teacher;
import org.spring.mapper.TeacherMapper;
import org.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public int Login(@Param("LoginName") String LoginName, @Param("Password") String Password, @Param("Radio") String Radio) {
        int x = teacherMapper.Login(LoginName,Password,Radio);
        if(x == 1){
            return 1;
        }else
            return 0;
    }

    @Override
    public int GetTeacherId(@Param("LoginName") String LoginName, @Param("Password") String Password,@Param("Radio") String Radio) {
        int i = teacherMapper.GetTeacherId(LoginName,Password,Radio);
        return i;
    }

    @Override
    public Teacher GetInfor(@Param("id")String id) {
        return teacherMapper.GetInfor(id);
    }

    @Override
    public List GetClass(@Param("id")String id) {
        return teacherMapper.GetClass(id);
    }

    @Override
    public List<Teacher> GetTeacherAll() {
        return teacherMapper.GetTeacherAll();
    }

    @Override
    public List<Teacher> TeacherRanking() {
        return teacherMapper.TeacherRanking();
    }

    @Override
    public int Register(Teacher teacher) {
        System.out.println(teacher.getAvatar()+teacher.getCreatTime());
        return teacherMapper.Register(teacher);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return teacherMapper.checkUsernameExists(username);
    }


}
