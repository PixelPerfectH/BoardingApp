package com.example.myapplication.Events;

import java.util.Date;

public class Event {
    private String name;
    private String description;
    private Date date;
    private boolean willGo;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public boolean willGo() {
        return willGo;
    }
}
