package com.example.myapplication.models;

import java.util.ArrayList;

public class Level {
    public long level;

    public String name;

    public ArrayList<Task> tasks;

    public long getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
