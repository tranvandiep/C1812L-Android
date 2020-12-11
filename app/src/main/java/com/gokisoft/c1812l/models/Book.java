package com.gokisoft.c1812l.models;

/**
 * Created by Diep.Tran on 12/11/20.
 */

public class Book {
    String thumbnail, title, content;

    public Book() {
    }

    public Book(String thumbnail, String title, String content) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
}
