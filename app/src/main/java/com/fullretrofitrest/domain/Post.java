package com.fullretrofitrest.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post
{

    private int userId;

    private Integer id;

    private String title;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
