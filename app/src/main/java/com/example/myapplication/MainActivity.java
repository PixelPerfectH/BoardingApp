package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Events.EventsActivity;
import com.example.myapplication.Profile.Profile;
import com.example.myapplication.Task.TasksActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    EventsActivity eventsFragment = new EventsActivity();
    LevelsFragment levelsFragment = new LevelsFragment();
    Profile profileFragment = new Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, LevelsFragment.class,null)
                    .commit();
        }*/
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, profileFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.events:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container_view, eventsFragment).commit();
                                return true;
                            case R.id.levels:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container_view, levelsFragment).commit();
                                return true;
                            case R.id.profile:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container_view, profileFragment).commit();
                                return true;
                        }
                        return false;
                    }
                }

        );
    }


}
