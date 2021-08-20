package com.AndroidGPA.tutorial;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private Integer id;
    private String title;

    @SerializedName("body")
    private String description;

    public Post(int userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
