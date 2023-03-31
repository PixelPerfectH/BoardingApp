package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.auth.LoginModel;
import com.google.gson.Gson;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

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
        Intent intent = new Intent(this, TasksActivity.class);
        findWeatherBtn.setOnClickListener(view -> {
            String postUrl = "clerostyle.drawy.ru/api/auth/loginbypassword";
            Gson gson = new Gson();
            LoginModel loginModel = new LoginModel(login.getText().toString(), password.getText().toString());
            String payload = gson.toJson(loginModel);
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            try {
                StringEntity postingString = new StringEntity(payload);
                post.setEntity(postingString);
                post.setHeader("Content-Type", "application/json");
                HttpResponse response = client.execute(post);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    System.out.println("NO");
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (ClientProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
