package com.example.food_app.fragment.notification;

public class Notification_item {
    private int imgResourceID;
    private String title, content;

    public Notification_item(int imgResourceID, String title, String content) {
        this.imgResourceID = imgResourceID;
        this.title = title;
        this.content = content;
    }

    public int getImgResourceID() {
        return imgResourceID;
    }

    public void setImgResourceID(int imgResourceID) {
        this.imgResourceID = imgResourceID;
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
