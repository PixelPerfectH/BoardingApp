package com.example.myapplication.Profile;

import android.graphics.Bitmap;

public class Employee {
    private String userName;
    private int place;

    private int points;
    private Bitmap avatar;

    public String getName() {
        return userName;
    }

    public int getPlace() {
        return place;
    }

    public int getPoints() {
        return points;
    }

    public Bitmap getAvatar() {
        return avatar;
    }
}
