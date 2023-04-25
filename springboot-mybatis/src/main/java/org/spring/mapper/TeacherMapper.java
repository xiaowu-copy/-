package org.spring.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.domain.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int Login(@Param("LoginName") String LoginName, @Param("Password") String Password, @Param("Radio") String Radio);

    int GetTeacherId(@Param("LoginName") String LoginName, @Param("Password") String Password, @Param("Radio") String Radio);

    List GetClass (@Param("id") String id);
    Teacher GetInfor(@Param("id") String id);

    List<Teacher> GetTeacherAll();

    List<Teacher> TeacherRanking();

    int Register(Teacher teacher);

    boolean checkUsernameExists(@Param("username") String username);

}
