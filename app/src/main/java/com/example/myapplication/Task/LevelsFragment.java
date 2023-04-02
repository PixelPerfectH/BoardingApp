package com.example.myapplication.Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class LevelsFragment extends Fragment {
    String login;
    Button level1btn,level2btn,level3btn,level4btn,level5btn;
    Intent intent;

    public LevelsFragment(){
        super(R.layout.activty_levels);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = requireActivity().getIntent().getExtras().get("login").toString();
        System.out.println(login);
        intent = new Intent(requireActivity(), TasksActivity.class);
        level1btn = view.findViewById(R.id.button1);
        level2btn = view.findViewById(R.id.button2);
        level3btn = view.findViewById(R.id.button3);
        level4btn = view.findViewById(R.id.button4);
        level5btn = view.findViewById(R.id.button5);
        level1btn.setOnClickListener(v -> onClick(1));
        level2btn.setOnClickListener(v -> onClick(2));
        level3btn.setOnClickListener(v -> onClick(3));
        level4btn.setOnClickListener(v -> onClick(4));
        level5btn.setOnClickListener(v -> onClick(5));
    }

    void onClick(int id) {
        switch (id) {
            case 1:
                intent.putExtra("level", "1");
                break;
            case 2:
                intent.putExtra("level", "2");
                break;
            case 3:
                intent.putExtra("level", "3");
                break;
            case 4:
                intent.putExtra("level", "4");
                break;
            case 5:
                intent.putExtra("level", "5");
                break;
        }
        intent.putExtra("login",login);
        startActivity(intent);
    }
}
