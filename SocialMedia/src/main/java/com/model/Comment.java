package com.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private LocalDateTime timeStamp;
    private int postId;
    private int userId;

    public Comment() {}

    public Comment(String content, int postId, int userId) {
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.timeStamp = LocalDateTime.now();
    }

    public Comment(int id, String content, LocalDateTime timeStamp, int postId, int userId) {
        this.id = id;
        this.content = content;
        this.timeStamp = timeStamp;
        this.postId = postId;
        this.userId = userId;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimeStamp() { return timeStamp; }

    public void setCreatedAt(LocalDateTime timeStamp) { this.timeStamp = timeStamp; }


    public int getPostId() { return postId; }

    public void setPostId(int postId) { this.postId = postId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }
}
