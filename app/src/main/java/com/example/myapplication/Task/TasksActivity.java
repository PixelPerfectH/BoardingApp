package com.example.myapplication.Task;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Level;
import com.example.myapplication.models.Task;
import com.example.myapplication.models.TaskComplete;
import com.example.myapplication.requests.GetRequest;
import com.example.myapplication.requests.PostRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TasksActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private final Gson gson = new Gson();
    private List<Task> tasksList;
    int level;
    String login;

    private TaskAdapter taskAdapter;
    private final TaskAdapter.TaskListener listener = new TaskAdapter.TaskListener() {
        @Override
        public void onTaskClick(View v, int position) {
            Task task = taskAdapter.getTask(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        recyclerView = findViewById(R.id.recyclerView_tasks);
        ItemTouchHelper.SimpleCallback callback = new SwipeItem(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
        login = getIntent().getExtras().get("login").toString();
        level = Integer.parseInt(getIntent().getExtras().get("level").toString());// номер уровня
        GetRequest getRequest = new GetRequest();
        String result;
        try {
            result = getRequest.execute("https://clerostyle.drawy.ru/api/level/get?userName=" + login + "&levelId=" + level).get();
            System.out.println(level);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            Level level = gson.fromJson(result, Level.class);
            TextView mainTask = findViewById(R.id.mainTaskTV);
            mainTask.setText(level.getName());
            tasksList = level.getTasks();
            taskAdapter = new TaskAdapter(tasksList, listener);
            recyclerView.setAdapter(taskAdapter);
        }

    }

    @Override
    protected void onStop() {
        //Осталось только залить переменную в БД , а потом вывести в профиле
        TasksActivity.super.finish();
        super.onStop();
    }


    class SwipeItem extends ItemTouchHelper.SimpleCallback {

        public SwipeItem(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (viewHolder instanceof TaskAdapter.ViewHolder) {
                Task task = tasksList.get(viewHolder.getAdapterPosition());
                Gson gson = new Gson();
                TaskComplete taskComplete = new TaskComplete(login, task.getName());
                PostRequest postRequest = new PostRequest(gson.toJson(taskComplete));
                try {
                    Integer responseCode = postRequest.execute("https://clerostyle.drawy.ru/api/task/complete").get();
                    if (responseCode == 200) {
                        System.out.println("OK");
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!task.isActive) {
                    Toast.makeText(TasksActivity.this, "Это задание уже выполнено!", Toast.LENGTH_SHORT).show();
                } else {
                    task.isActive = false;
                }
                tasksList.remove(viewHolder.getAdapterPosition());
                tasksList.add(task);
                taskAdapter = new TaskAdapter(tasksList, listener);
                recyclerView.setAdapter(taskAdapter);
            }
        }
    }
}
