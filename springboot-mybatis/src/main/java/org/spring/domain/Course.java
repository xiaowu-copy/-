package org.spring.domain;

public class Course {
    private int Classid;
    private String ClassName;
    private String ClassIntro;

    public int getClassid() {
        return Classid;
    }

    public void setClassid(int classid) {
        Classid = classid;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassIntro() {
        return ClassIntro;
    }

    public void setClassIntro(String classIntro) {
        ClassIntro = classIntro;
    }

    public Course(int classid, String className, String classIntro) {
        Classid = classid;
        ClassName = className;
        ClassIntro = classIntro;
    }
    public Course(){}
}
