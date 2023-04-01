package com.example.myapplication.Events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.models.Description;

import java.util.ArrayList;
import java.util.Date;

public class EventsActivity extends Fragment {

    ArrayList<Event> events=new ArrayList<>();
    public EventsActivity(){super(R.layout.list_events);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Intent intent = new Intent(getActivity(), Description.class);
        //Заполнить массив events
        EventAdapter.OnEventClickListener eventClickListener = new EventAdapter.OnEventClickListener() {
            @Override
            public void onEventClick(Event state, int position) {
                intent.putExtra("eventName", events.get(position).getName());
                intent.putExtra("eventDescription", events.get(position).getDescription());
                startActivity(intent);
            }
        };
        EventAdapter adapter = new EventAdapter(getActivity(), events,eventClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
//        events.add(new Event ("Бразилия", "Бразилиа", new Date()));
//        events.add(new Event ("Аргентина", "Буэнос-Айрес", new Date()));
//        events.add(new Event ("Колумбия", "Богота", new Date()));
//        events.add(new Event ("Уругвай", "Монтевидео",new Date()));
//        events.add(new Event ("Чили", "Сантьяго", new Date()));
    }

}