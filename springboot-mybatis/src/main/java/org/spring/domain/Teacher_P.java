package org.spring.domain;

public class Teacher_P {
    private int author_id;
    private int picture_id;
    private String up_time;

    public Teacher_P(int author_id, int picture_id, String up_time) {
        this.author_id = author_id;
        this.picture_id = picture_id;
        this.up_time = up_time;
    }

    public Teacher_P() {
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }
}
