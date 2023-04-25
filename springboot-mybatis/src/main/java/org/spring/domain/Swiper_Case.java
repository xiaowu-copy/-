package org.spring.domain;

public class Swiper_Case {
    private int id;
    private String title;
    private String content;
    private String img;
    private String time;
    private String author;
    private String label;

    private int NumberofViews;

    private int NumberofReferences;


    public Swiper_Case() {
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

    public Swiper_Case(int id, String title, String content, String img, String time, String author, String label, int numberofViews, int numberofReferences) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.time = time;
        this.author = author;
        this.label = label;
        NumberofViews = numberofViews;
        NumberofReferences = numberofReferences;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
