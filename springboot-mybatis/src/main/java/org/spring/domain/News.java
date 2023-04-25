package org.spring.domain;

public class News {
    private int Id;
    private String Title;
    private String Img;
    private int Type;
    private String Content;
    private String CreateTime;
    private int IsAdmited;
    private String source;
    private int NumberofViews;

    private int NumberofReferences;

    private int AuthorId;
    private String Author_Name;

    private String label;

    public int getIsAdmited() {
        return IsAdmited;
    }

    public void setIsAdmited(int isAdmited) {
        IsAdmited = isAdmited;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public News(int id, String title, String img, int type, String content, String createTime, int isAdmited, String source, int numberofViews, int numberofReferences, int authorId, String author_name, String label) {
        Id = id;
        Title = title;
        Img = img;
        Type = type;
        Content = content;
        CreateTime = createTime;
        IsAdmited = isAdmited;
        this.source = source;
        NumberofViews = numberofViews;
        NumberofReferences = numberofReferences;
        AuthorId = authorId;
        Author_Name = author_name;
        this.label = label;
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
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getIsDeleted() {
        return IsAdmited;
    }

    public void setIsDeleted(int isDeleted) {
        IsAdmited = isDeleted;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public News() {

    }
}
