package com.gokisoft.c1812l.models;

import java.io.Serializable;

/**
 * Created by Diep.Tran on 11/27/20.
 */

public class Food implements Serializable{
    int _id;

    int resId;
    String title, content;
    float price;

    public Food(int resId) {
        this.resId = resId;
    }

    public Food(int resId, String title, String content, float price) {
        this.resId = resId;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Food(String title, String content, float price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
