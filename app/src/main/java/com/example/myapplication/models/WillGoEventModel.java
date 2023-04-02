package com.example.myapplication.models;

public class WillGoEventModel {
    private String userName;
    private String eventName;

    public WillGoEventModel(String userName, String eventName) {
        this.userName = userName;
        this.eventName = eventName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEventName() {
        return eventName;
    }
}
