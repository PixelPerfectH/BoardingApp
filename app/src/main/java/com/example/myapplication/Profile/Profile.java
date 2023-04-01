package com.example.myapplication.Profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Profile extends Fragment {
    ArrayList<Employee> employees=new ArrayList<>();

    public Profile(){super(R.layout.activity_user_profile);}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employees.clear();
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.three);
        // создаем адаптер
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), employees);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
    private void setInitialData() {
        employees.add(new Employee ("aaaaaaaa", 1));
        employees.add(new Employee ("bbbbbbbbb", 2));
        employees.add(new Employee ("ccccccc", 3));
    }
}
