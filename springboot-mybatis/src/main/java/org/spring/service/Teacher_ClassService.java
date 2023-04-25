package org.spring.service;

import org.apache.ibatis.annotations.Param;

public interface Teacher_ClassService {

    public int insertClass(@Param("id") String id,@Param("Classid") String Classid);
}
