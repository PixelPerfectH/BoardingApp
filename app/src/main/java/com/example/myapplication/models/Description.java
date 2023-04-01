package com.example.myapplication.models;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Task.TasksActivity;

import org.w3c.dom.Text;

public class Description extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);
        ScrollView scrollView = findViewById(R.id.scrollView);
        TextView descriptionTV = findViewById(R.id.DescriptionTV);
        TextView nameTV = findViewById(R.id.NameTV);
        descriptionTV.setText(getIntent().getExtras().get("eventDescription").toString());
        nameTV.setText(getIntent().getExtras().get("eventName").toString());
        descriptionTV.setTextSize(26);
        setContentView(scrollView);
    }
    @Override
    protected void onStop() {
        Description.super.finish();
        super.onStop();
    }
}
