package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Task.TasksActivity;

public class MainActivity extends AppCompatActivity {
    String login;
    ImageView avatar;
    Button level1btn,level2btn,level3btn,level4btn,level5btn;
    Intent intent;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);
        login = getIntent().getExtras().get("login").toString();
        System.out.println(login);
        intent = new Intent(this, TasksActivity.class);
        level1btn = findViewById(R.id.button);
        level2btn = findViewById(R.id.button2);
        level3btn = findViewById(R.id.button3);
        level4btn = findViewById(R.id.button4);
        level5btn = findViewById(R.id.button5);
        level1btn.setOnClickListener(view -> onClick(1));
        level2btn.setOnClickListener(view -> onClick(2));
        level3btn.setOnClickListener(view -> onClick(3));
        level4btn.setOnClickListener(view -> onClick(4));
        level5btn.setOnClickListener(view -> onClick(5));
    }

    void onClick(int id) {
        switch (id) {
            case 1:
                intent.putExtra("level", "1");
            case 2:
                intent.putExtra("level", "2");
            case 3:
                intent.putExtra("level", "3");
            case 4:
                intent.putExtra("level", "4");
            case 5:
                intent.putExtra("level", "5");
        }
        intent.putExtra("login",login);
        startActivity(intent);
    }
}
