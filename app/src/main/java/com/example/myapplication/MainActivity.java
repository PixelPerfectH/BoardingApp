package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button findWeatherBtn;
    EditText password;
    EditText login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWeatherBtn = findViewById(R.id.LoginBtn);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.editTextTextPersonName);
        findWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int exitCode = 105;
                switch (exitCode){
                    case 404:
                        Toast.makeText(MainActivity.this, "This user doesn't exist"
                            , Toast.LENGTH_SHORT).show();
                        break;
                    case 105:
                        Toast.makeText(MainActivity.this, "Error 105", Toast.LENGTH_SHORT).show();
                }
                System.out.println(login.getText());
                System.out.println(password.getText());
            }
        });
    }

}