package com.example.myapplication.Events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.requests.GetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventsActivity extends Fragment {

    ArrayList<Event> events = new ArrayList<>();
    String login;

    public EventsActivity() {
        super(R.layout.list_events);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Intent intent = new Intent(getActivity(), Description.class);
        login = requireActivity().getIntent().getExtras().get("login").toString();
        GetRequest getRequest = new GetRequest();
        String result;
        try {
            result = getRequest.execute("https://clerostyle.drawy.ru/api/event/getlist?userName=" + login + "&firstElement=0&lastElement=4").get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            Gson gson = new Gson();
            events = gson.fromJson(result, new TypeToken<List<Event>>() {
            }.getType());
        }
        EventAdapter.OnEventClickListener eventClickListener = (state, position) -> {
            intent.putExtra("eventName", events.get(position).getName());
            intent.putExtra("eventDescription", events.get(position).getDescription());
            intent.putExtra("login",login);
            startActivity(intent);
        };
        EventAdapter adapter = new EventAdapter(getActivity(), events, eventClickListener);
        recyclerView.setAdapter(adapter);
    }
}
