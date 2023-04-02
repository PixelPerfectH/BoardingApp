package com.example.myapplication.Profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Events.Event;
import com.example.myapplication.R;
import com.example.myapplication.requests.GetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Profile extends Fragment {
    ArrayList<Employee> employees = new ArrayList<>();

    public Profile() {
        super(R.layout.activity_user_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employees.clear();
        // начальная инициализация списка
        GetRequest getRequest = new GetRequest();
        String result;
        try {
            result = getRequest.execute("https://clerostyle.drawy.ru/api/point/gettopusers?userName=" + "CleroStyle" + "&amount=3").get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            Gson gson = new Gson();
            employees = gson.fromJson(result, new TypeToken<List<Employee>>(){}.getType());
        }
        RecyclerView recyclerView = view.findViewById(R.id.three);
        // создаем адаптер
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), employees);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }
}
