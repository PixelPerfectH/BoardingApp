package com.example.myapplication.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private byte[] avatar;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Bitmap getAvatar() {
        return BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
    }
}
