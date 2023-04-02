package com.example.myapplication.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Task {
    private String name;
    private String description;
    private String avatar;
    public boolean isActive = true;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getAvatar() {
        byte[] decodedString = Base64.decode(avatar, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public boolean isActive() { return isActive; }
}
