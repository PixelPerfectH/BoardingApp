package com.example.myapplication.Events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.models.Description;

import java.util.ArrayList;
import java.util.Date;

public class EventsActivity extends AppCompatActivity {

    ArrayList<Event> events=new ArrayList<Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_events);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        Intent intent = new Intent(this, Description.class);
        // определяем слушателя нажатия элемента в списке
        EventAdapter.OnEventClickListener eventClickListener = new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event state, int position) {

                /*Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();*/
                startActivity(intent);
            }
        };
        //
        // создаем адаптер
        EventAdapter adapter = new EventAdapter(this, events,eventClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        /*ScrollView scrollView = new ScrollView(this);

        TextView textView = new TextView(this);
        textView.setText("");
        textView.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(26);
        scrollView.addView(textView);
        setContentView(scrollView);*/
    }

    private void setInitialData() {
        events.add(new Event ("Бразилия", "Бразилиа", new Date()));
        events.add(new Event ("Аргентина", "Буэнос-Айрес", new Date()));
        events.add(new Event ("Колумбия", "Богота", new Date()));
        events.add(new Event ("Уругвай", "Монтевидео",new Date()));
        events.add(new Event ("Чили", "Сантьяго", new Date()));
    }

}