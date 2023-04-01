package com.example.myapplication.models;

public class TaskComplete {
    private String userName;
    private String taskName;

    public TaskComplete(String userName, String taskName) {
        this.userName = userName;
        this.taskName = taskName;
    }

    public String getUserName() {
        return userName;
    }

    public String getTaskName() {
        return taskName;
    }
}
