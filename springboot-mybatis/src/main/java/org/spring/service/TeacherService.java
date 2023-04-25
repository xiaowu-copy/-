package org.spring.service;


import org.apache.ibatis.annotations.Param;
import org.spring.domain.Teacher;

import java.util.List;

public interface TeacherService {
    public int Login(@Param("LoginName") String LoginName, @Param("Password") String Password, @Param("Radio") String Radio);

    int GetTeacherId(@Param("LoginName") String LoginName, @Param("Password") String Password,@Param("Radio") String Radio);

    Teacher GetInfor(@Param("id") String id);

    List GetClass(@Param("id") String id);

    List<Teacher> GetTeacherAll();

    List<Teacher> TeacherRanking();

    int Register(Teacher teacher);

    boolean checkUsernameExists(String username);
}
