package com.fullretrofitrest.domain;

import com.google.gson.annotations.SerializedName;

public class Comment
{
    private int postId;

    private String id;

    private String name;

    private String email;

    @SerializedName("body")
    private String text;

    public Comment(int postId, String id, String name, String email, String text) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
