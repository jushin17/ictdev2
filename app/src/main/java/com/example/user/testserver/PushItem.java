package com.example.user.testserver;

import java.io.Serializable;

/**
 * Created by user on 2016-07-25.
 */
public class PushItem implements Serializable{
    String title;
    String text;
    String time;

    public PushItem() {
    }

    public PushItem(String title, String text, String time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public String getTime() {
        return this.time;
    }




}
