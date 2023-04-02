package com.example.myapplication.Profile;

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

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private final List<Employee> employees;
    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.employees = employees;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.leaderboard, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Employee employee= employees.get(position);
        holder.set(employee);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,place,score;

        ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);
            place=view.findViewById(R.id.place);
            score = view.findViewById(R.id.score);
        }
        @SuppressLint("SetTextI18n")
        void set(Employee employee){
            this.name.setText(employee.getName());
            this.place.setText(String.valueOf(employee.getPlace()));
            this.score.setText(Integer.toString(employee.getPoints()));
        }
    }
}
