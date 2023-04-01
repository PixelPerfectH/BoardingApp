package com.example.myapplication.Profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    ArrayList<Employee> employees=new ArrayList<Employee>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.three);
        // создаем адаптер
        EmployeeAdapter adapter = new EmployeeAdapter(this, employees);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
    private void setInitialData() {
        employees.add(new Employee ("aaaaaaaa", 1));
        employees.add(new Employee ("bbbbbbbbb", 2));
        employees.add(new Employee ("ccccccc", 3));
    }
}
