package com.example.myapplication.models;

public class Task {
    private String name;
    public String description;

    public boolean isActive = true;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() { return isActive; }
}
