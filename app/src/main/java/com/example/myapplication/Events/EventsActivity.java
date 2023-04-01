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
import com.example.myapplication.requests.GetRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventsActivity extends Fragment {

    ArrayList<Event> events=new ArrayList<>();
    public EventsActivity(){super(R.layout.list_events);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Intent intent = new Intent(getActivity(), Description.class);
        //Заполнить массив events
        GetRequest getRequest = new GetRequest();
        String result;
        try {
            result = getRequest.execute("https://clerostyle.drawy.ru/api/event/getlist?userName=" + "CleroStyle" + "&firstElement=0&lastElement=4").get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            Gson gson = new Gson();
            events = gson.fromJson(result, ArrayList.class);
        }
        EventAdapter.OnEventClickListener eventClickListener = (state, position) -> {
            intent.putExtra("eventName", events.get(position).getName());
            intent.putExtra("eventDescription", events.get(position).getDescription());
            startActivity(intent);
        };
        EventAdapter adapter = new EventAdapter(getActivity(), events,eventClickListener);
        recyclerView.setAdapter(adapter);
    }
}
