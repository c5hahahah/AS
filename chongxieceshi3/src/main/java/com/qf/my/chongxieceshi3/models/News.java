package com.qf.my.chongxieceshi3.models;

/**
 * Created by my on 2016/6/27.
 */
public class News {
    private String title;
    private String litpic;

    public News() {
    }

    public News(String title, String litpic) {
        this.title = title;
        this.litpic = litpic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }
}
