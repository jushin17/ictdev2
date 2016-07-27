package com.example.user.testserver;

/**
 * Created by user on 2016-07-25.
 */
public class CountItem {
    String title;
    String text;
    int count;

    public CountItem() {
    }

    public CountItem(String title, String text, int count) {
        this.title = title;
        this.text = text;
        this.count = count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public int getCount() {
        return this.count;
    }




}
