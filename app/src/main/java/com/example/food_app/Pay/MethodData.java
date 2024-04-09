package com.example.food_app.Pay;

import java.io.Serializable;

public class MethodData implements Serializable {
    private String title;

    public MethodData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
