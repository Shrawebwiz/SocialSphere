package com.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int userId;


    public Post() {}

    public Post(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public Post(int id, String title, String content, LocalDateTime createdAt, int userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
