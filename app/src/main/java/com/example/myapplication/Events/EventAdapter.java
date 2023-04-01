package com.example.myapplication.Events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    interface OnEventClickListener{
        void onEventClick(Event event, int position);
    }

    private OnEventClickListener onEventListener;
    private LayoutInflater inflater;
    private final List<Event> eventList;
    public EventAdapter(Context context, List<Event> eventList, OnEventClickListener onEventListener) {
        this.onEventListener =onEventListener;
        this.eventList = eventList;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    Event event=eventList.get(position);
    holder.set(event);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onEventListener.onEventClick(event, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,date;

        ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);
            description=view.findViewById(R.id.description);
            date=view.findViewById(R.id.date);
        }
        void set(Event event){
            this.name.setText(event.getName());
            this.description.setText(event.getDescription());
            this.date.setText(event.getDate().toString());//продумать дату, щас лень
        }
    }
}
