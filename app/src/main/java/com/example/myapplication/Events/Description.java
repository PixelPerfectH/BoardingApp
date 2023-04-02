package com.example.myapplication.Events;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.requests.PostRequest;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class Description extends AppCompatActivity {
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);
        ScrollView scrollView = findViewById(R.id.scrollView);
        TextView descriptionTV = findViewById(R.id.DescriptionTV);
        TextView nameTV = findViewById(R.id.NameTV);
        login = getIntent().getExtras().get("login").toString();
        descriptionTV.setText(getIntent().getExtras().get("eventDescription").toString());
        nameTV.setText(getIntent().getExtras().get("eventName").toString());
        descriptionTV.setTextSize(26);
        CheckBox enableBox = findViewById(R.id.check);
        setContentView(scrollView);


        enableBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Gson gson = new Gson();
                WillGoEventModel model = new WillGoEventModel(login, getIntent().getExtras().get("eventName").toString());
                PostRequest postRequest = new PostRequest(gson.toJson(model));
                try {
                    Integer responseCode = postRequest.execute("https://clerostyle.drawy.ru/api/event/willgo").get();
                    if (responseCode == 200) {
                        System.out.println("OK");
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        Description.super.finish();
        super.onStop();
    }
}
