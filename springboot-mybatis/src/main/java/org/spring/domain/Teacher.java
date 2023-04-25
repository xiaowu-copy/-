package org.spring.domain;


import java.util.List;

public class Teacher {
    private int Id;
    private String LoginName;
    private String Password;
    private int IsAction;
    private String CreateTime;
    private int Isdeleted;

    private int Active;

    private String Avatar;

    private String phone;


    private String orgnazation;

    private String Teach_Class;

    private List CheckList;

    public List getCheckList() {
        return CheckList;
    }

    public void setCheckList(List checkList) {
        CheckList = checkList;
    }

    public String getTeach_Class() {
        return Teach_Class;
    }

    public void setTeach_Class(String teach_Class) {
        Teach_Class = teach_Class;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrgnazation() {
        return orgnazation;
    }

    public void setOrgnazation(String orgnazation) {
        this.orgnazation = orgnazation;
    }
    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }



    public Teacher() {
    }


    public Teacher(int id, String loginName, String password, int isAction, String creatTime, int active, String avatar, String phone, String orgnazation, String teach_class, List checkList) {
        Id = id;
        LoginName = loginName;
        Password = password;
        IsAction = isAction;
        CreateTime = creatTime;
        Active = active;
        Avatar = avatar;
        this.phone = phone;
        this.orgnazation = orgnazation;
        Teach_Class = teach_class;

        CheckList = checkList;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setIsAction(int isAction) {
        IsAction = isAction;
    }

    public void setCreatTime(String creatTime) {
        CreateTime = creatTime;
    }

    public void setIsdeleted(int isdeleted) {
        Isdeleted = isdeleted;
    }

    public int getId() {
        return Id;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getPassword() {
        return Password;
    }

    public int getIsAction() {
        return IsAction;
    }

    public String getCreatTime() {
        return CreateTime;
    }

    public int getIsdeleted() {
        return Isdeleted;
    }
}
