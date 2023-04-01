package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Task.TasksActivity;

public class MainActivity extends AppCompatActivity {
    Button level1btn,level2btn,level3btn,level4btn,level5btn;
    Intent intent;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);
        TextView loginTextView = findViewById(R.id.LoginTextTV);
        String login = getIntent().getExtras().get("login").toString();
        loginTextView.setText("Прогресс: " + login);
        intent = new Intent(this, TasksActivity.class);
        level1btn = findViewById(R.id.button);
        level2btn = findViewById(R.id.button2);
        level3btn = findViewById(R.id.button3);
        level4btn = findViewById(R.id.button4);
        level5btn = findViewById(R.id.button5);
        level1btn.setOnClickListener(view -> onClick(level1btn.getId()));
        level2btn.setOnClickListener(view -> onClick(level2btn.getId()));
        level3btn.setOnClickListener(view -> onClick(level3btn.getId()));
        level4btn.setOnClickListener(view -> onClick(level4btn.getId()));
        level5btn.setOnClickListener(view -> onClick(level5btn.getId()));
    }
    void onClick(int id){
        switch(id){
            case 1000010: getIntent().putExtra("level","1");
            case 1000004: getIntent().putExtra("level","2");
            case 1000002: getIntent().putExtra("level","3");
            case 1000001: getIntent().putExtra("level","4");
            case 1000006: getIntent().putExtra("level","5");
        }
        startActivity(intent);
    }

}
