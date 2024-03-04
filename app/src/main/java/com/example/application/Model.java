package com.example.application;

import android.graphics.Bitmap;

public class Model {
    Integer u_id;
    String title,description,author,date;
    private Bitmap img;
    Integer share_count;

    public Model(Integer u_id, String title, String description, String author, String date, Bitmap img, Integer share_count) {
        this.u_id = u_id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.date = date;
        this.img = img;
        this.share_count = share_count;
    }

    public Model(){

    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Integer getShare_count() {
        return share_count;
    }

    public void setShare_count(Integer share_count) {
        this.share_count = share_count;
    }
}



