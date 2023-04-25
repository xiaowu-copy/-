package org.spring.controller;


import org.apache.ibatis.annotations.Param;
import org.spring.domain.Teacher;
import org.spring.service.TeacherService;
import org.spring.service.Teacher_ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class TeacherController {
    @Autowired
    public TeacherService teacherService;
    @Autowired
    Teacher_ClassService teacher_classService;

    @RequestMapping("/login")
    public String Login(@Param("LoginName") String LoginName, @Param("Password") String Password, @Param("Radio") String Radio){
        System.out.println(LoginName+"---"+Password+"----"+Radio);
        //因为这里的登录逻辑只是有过的符合账号密码的个数，但是并没有获取该用户的Id，所以再写一个getId的方法，为了存在session当中展示个人信息
        int x = teacherService.Login(LoginName, Password, Radio);
        int id = teacherService.GetTeacherId(LoginName,Password,Radio);

        if(x == 1){
            return "succuss,"+id;
        }
        return "false,"+id;

    }
    @RequestMapping("/details")
    public Teacher GetInfor(@Param("LoginName") String id){
        List list = teacherService.GetClass(id);
        Teacher teacher = teacherService.GetInfor(id);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        String result = sb.toString();
        String result_new = result.substring(0,result.length()-1);
        teacher.setTeach_Class(result);
        System.out.println(result_new);
        return teacher;

    }

    //管理员方法，查找所有的用户，进行管理
    @RequestMapping("/GetTeacherAll")
    public List<Teacher> GetTeacherAll(){
        List<Teacher> list =teacherService.TeacherRanking();
        return list;

    }

    @RequestMapping("/GetTeacherRanking")
    public List<Teacher> TeacherMapper(){
        List<Teacher> list =teacherService.TeacherRanking();
        return list;

    }

    @PostMapping("/Register")
    public int Register(Teacher teacher){
        List ll = teacher.getCheckList();
        int i = teacherService.Register(teacher);
        for (Object vv:ll) {
            teacher_classService.insertClass(String.valueOf(teacher.getId()),String.valueOf(vv));
        }
        System.out.println(teacher.getId());
        return i;

    }
    @RequestMapping("/check-username")
    public Map<String, Boolean> checkUsername(@Param("username") String username) {
        boolean exists = teacherService.checkUsernameExists(username);
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists", exists);
        return result;
    }

}
