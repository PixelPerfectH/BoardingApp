package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button findWeatherBtn;
    EditText password;
    EditText login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findWeatherBtn = findViewById(R.id.LoginBtn);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, MainActivity.class);
        findWeatherBtn.setOnClickListener(view -> {
            intent.putExtra("1", login.getText().toString());
            System.out.println(login.getText());
            System.out.println(password.getText());
            startActivity(intent);
        });
    }

}
