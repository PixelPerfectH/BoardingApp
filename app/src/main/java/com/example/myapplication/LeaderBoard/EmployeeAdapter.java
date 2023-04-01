package com.example.myapplication.LeaderBoard;

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
    private final List<Employee> leaderbord;
    public EmployeeAdapter(Context context, List<Employee> leaderbord) {
        this.leaderbord = EmployeeAdapter.this.leaderbord;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.employee, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Employee employee=leaderbord.get(position);
        holder.set(employee);
    }

    @Override
    public int getItemCount() {
        return leaderbord.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,date;

        ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);
            description=view.findViewById(R.id.description);
            date=view.findViewById(R.id.date);
        }
        void set(Employee employee){
            this.name.setText(employee.getName());
            this.description.setText(employee.getPlace());
        }
    }
}
