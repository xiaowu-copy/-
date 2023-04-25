package org.spring.domain;

public class Teacher_Class {
    private int Teacher_id;
    private int Class_id;

    public Teacher_Class() {
    }

    public Teacher_Class(int teacher_id, int class_id) {
        Teacher_id = teacher_id;
        Class_id = class_id;
    }

    public int getTeacher_id() {
        return Teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }

    public int getClass_id() {
        return Class_id;
    }

    public void setClass_id(int class_id) {
        Class_id = class_id;
    }
}
