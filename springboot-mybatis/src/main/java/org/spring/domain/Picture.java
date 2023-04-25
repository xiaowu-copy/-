package org.spring.domain;

public class Picture {
    private int id;
    private String address;
    private String name;
    private String Avatar;
    private String LoginName;
    private int IsAdmited;

    private int author_id;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public int getIsAdmited() {
        return IsAdmited;
    }

    public void setIsAdmited(int isAdmited) {
        IsAdmited = isAdmited;
    }

    public Picture() {

    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture(int id, String address, String name, String avatar, String loginName, int isAdmited, int author_id, String time) {
        this.id = id;
        this.address = address;
        this.name = name;
        Avatar = avatar;
        LoginName = loginName;
        this.IsAdmited = isAdmited;
        this.author_id = author_id;
        this.time = time;
    }
}
