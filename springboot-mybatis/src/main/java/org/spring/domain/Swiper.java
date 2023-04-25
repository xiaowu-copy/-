package org.spring.domain;

public class Swiper {
    private int id;

    private String Img;
    private String Label;

    private int NumberofViews;

    private int NumberofReferences;

    private String ViedioTitile;

    private String Address;

    private String UploadTime;

    private String UploadAuthor;

    private int AuthorId;


    private String Avatar;

    private String Intro;

    private String LoginName;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public Swiper(){}
    public Swiper(String loginName,int id, String img, String label, int numberofViews, int numberofReferences, String title, String address, String uploadTime, String uploadAuthor, String avatar, String intro, int authorId) {
        this.id = id;
        LoginName = loginName;
        Img = img;
        Label = label;
        NumberofViews = numberofViews;
        NumberofReferences = numberofReferences;
        ViedioTitile = title;
        Address = address;
        UploadTime = uploadTime;
        UploadAuthor = uploadAuthor;
        Avatar = avatar;
        Intro = intro;
        AuthorId = authorId;
    }

    public int getNumberofViews() {
        return NumberofViews;
    }

    public void setNumberofViews(int numberofViews) {
        NumberofViews = numberofViews;
    }

    public int getNumberofReferences() {
        return NumberofReferences;
    }

    public void setNumberofReferences(int numberofReferences) {
        NumberofReferences = numberofReferences;
    }

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }







    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getViedioTitile() {
        return ViedioTitile;
    }

    public void setViedioTitile(String viedioTitile) {
        ViedioTitile = viedioTitile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String uploadTime) {
        UploadTime = uploadTime;
    }

    public String getUploadAuthor() {
        return UploadAuthor;
    }

    public void setUploadAuthor(String uploadAuthor) {
        UploadAuthor = uploadAuthor;
    }
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Swiper(String label) {
        Label = label;
    }


}
