package org.spring.domain;

public class Video {
    private int ViedioId;
    private String ViedioName;
    private String Intro;
    private String address;
    private String ViedioTitile;

    private int    IsAdmit;

    private String Label;

    private int NumberofViews;

    private int NumberofReferences;

    private int AuthorId;
    private int Id;
    private String LoginName;
    private String Password;
    private int IsAction;
    private String CreatTime;
    private int Isdeleted;

    private int Active;



    private int IsSwiper;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getIsAction() {
        return IsAction;
    }

    public void setIsAction(int isAction) {
        IsAction = isAction;
    }

    public String getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(String creatTime) {
        CreatTime = creatTime;
    }

    public int getIsdeleted() {
        return Isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        Isdeleted = isdeleted;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getOrgnazation() {
        return orgnazation;
    }

    public void setOrgnazation(String orgnazation) {
        this.orgnazation = orgnazation;
    }

    private String Avatar;

    private int phone;

    public Video(int viedioId, String viedioName, String intro,int isSwiper ,String address, String viedioTitile, int isAdmit, String label, int numberofViews, int numberofReferences, int authorId, int id, String loginName, String password, int isAction, String creatTime, int isdeleted, int active, String avatar, int phone, String orgnazation) {
        ViedioId = viedioId;
        ViedioName = viedioName;
        Intro = intro;
        IsSwiper = isSwiper;
        this.address = address;
        ViedioTitile = viedioTitile;
        IsAdmit = isAdmit;
        Label = label;
        NumberofViews = numberofViews;
        NumberofReferences = numberofReferences;
        AuthorId = authorId;
        Id = id;
        LoginName = loginName;
        Password = password;
        IsAction = isAction;
        CreatTime = creatTime;
        Isdeleted = isdeleted;
        Active = active;
        Avatar = avatar;
        this.phone = phone;
        this.orgnazation = orgnazation;
    }

    private String orgnazation;

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

    public int getIsAdmit() {
        return IsAdmit;
    }

    public void setIsAdmit(int isAdmit) {
        IsAdmit = isAdmit;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }




    public Video() {
    }

    public int getViedioId() {
        return ViedioId;
    }

    public void setViedioId(int viedioId) {
        ViedioId = viedioId;
    }

    public String getViedioName() {
        return ViedioName;
    }

    public void setViedioName(String viedioName) {
        ViedioName = viedioName;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getViedioTitile() {
        return ViedioTitile;
    }

    public int getNumberofViews() {
        return NumberofViews;
    }

    public void setNumberofViews(int numberofViews) {
        NumberofViews = numberofViews;
    }

    public void setViedioTitile(String viedioTitile) {
        ViedioTitile = viedioTitile;
    }

    public int getViedioIsAdmit() {
        return IsAdmit;
    }

    public void setViedioIsAdmit(int isAdmit) {
        IsAdmit = isAdmit;
    }

}
