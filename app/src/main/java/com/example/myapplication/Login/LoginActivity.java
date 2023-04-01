package com.example.myapplication.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.requests.PostRequest;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    Button LoginBtn;
    EditText password;
    EditText login;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         LoginBtn = findViewById(R.id.LoginBtn);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, MainActivity.class);
        LoginBtn.setOnClickListener(view -> {

            Gson gson = new Gson();
            LoginModel model = new LoginModel(login.getText().toString(), password.getText().toString());
            PostRequest postRequest = new PostRequest(gson.toJson(model));
            try {
                Integer responseCode = postRequest.execute("https://clerostyle.drawy.ru/api/auth/loginbypassword").get();
                if (responseCode == 200) {
                    intent.putExtra("login", login.getText().toString());
                    startActivity(intent);
                    LoginActivity.super.finish();
                } else {
                    login.setText("");
                    password.setText("");
                    Toast.makeText(getApplicationContext(), "Пользователя с таким логином и/" +
                            "или паролем не существует!", Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
