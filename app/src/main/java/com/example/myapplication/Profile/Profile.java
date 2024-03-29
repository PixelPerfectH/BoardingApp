package com.example.myapplication.Profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.User;
import com.example.myapplication.requests.GetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Profile extends Fragment {
    ArrayList<Employee> employees = new ArrayList<>();
    TextView yourPlaceTV;
    TextView nameTV;
    TextView nameMainTV;
    TextView pointsTV;
    TextView scoreDB;
    String login;
    ImageView avatar;
    User user;

    public Profile() {
        super(R.layout.activity_user_profile);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetRequest getRequest = new GetRequest();
        GetRequest getRequest1 = new GetRequest();
        yourPlaceTV = view.findViewById(R.id.place);
        nameTV = view.findViewById(R.id.name);
        scoreDB = view.findViewById(R.id.score);
        nameMainTV = view.findViewById(R.id.nameTV);
        pointsTV = view.findViewById(R.id.pointsTV);
        avatar = view.findViewById(R.id.avatarIV);
        String result;
        String userResult;
        login = requireActivity().getIntent().getExtras().get("login").toString();
        try {
            result = getRequest.execute("https://clerostyle.drawy.ru/api/point/gettopusers?userName=" + login + "&amount=3").get();
            userResult = getRequest1.execute("https://clerostyle.drawy.ru/api/profile/getuser?userName=" + login).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            Gson gson = new Gson();
            employees = gson.fromJson(result, new TypeToken<List<Employee>>() {
            }.getType());
            employees = gson.fromJson(result, new TypeToken<List<Employee>>() {
            }.getType());
        }
        if (userResult != null) {
            Gson gson = new Gson();
            user = gson.fromJson(userResult, User.class);
        }
        RecyclerView recyclerView = view.findViewById(R.id.three);
        yourPlaceTV.setText(Integer.toString(employees.get(employees.size() - 1).getPlace()));
        nameTV.setText(employees.get(employees.size() - 1).getName());
        nameMainTV.setText(user.getFirstName() + " " + user.getLastName());
        scoreDB.setText(Integer.toString(employees.get(employees.size() - 1).getPoints()));
        pointsTV.setText(Integer.toString(employees.get(employees.size() - 1).getPoints()));
        employees.remove(employees.size() - 1);
        avatar.setImageBitmap(user.getAvatar());
        EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), employees);
        recyclerView.setAdapter(adapter);
    }
}
