package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.models.auth.LoginModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    Button findWeatherBtn;
    EditText password;
    EditText login;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findWeatherBtn = findViewById(R.id.LoginBtn);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, TasksActivity.class);
        findWeatherBtn.setOnClickListener(view -> {
            new LoginTask(login.getText().toString(), password.getText().toString()) {
                @Override
                protected void onPostExecute(Integer result) {
                    if (result == 200) {
                        // тут перекидывай на следующую страницу
                    } else {
                        // тут ошибку выводи
                    }
                }
            }.execute("https://clerostyle.drawy.ru/api/auth/loginbypassword");
        });
    }
}
