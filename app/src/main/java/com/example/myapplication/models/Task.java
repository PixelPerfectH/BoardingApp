package com.example.myapplication.models;

public class Task {
    private String name;
    public String description;
    public User user;
    public boolean isActive = true;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public boolean isActive() { return isActive; }
}
