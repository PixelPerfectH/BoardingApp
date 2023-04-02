package com.example.myapplication.models;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

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
        CheckBox enableBox = findViewById(R.id.check);
        setContentView(scrollView);


        enableBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    //добавить бал галочка включена
                }
                else {
                    //убрать бал галочка выключена
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
