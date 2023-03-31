package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    private TextView mainTask;
    private TaskAdapter taskAdapter;
        private final TaskAdapter.TaskListener listener = new TaskAdapter.TaskListener() {
            @Override
            public void onTaskClick(View v, int position) {
                Task task = taskAdapter.getTask(position);
            }
        };
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tasks);
            RecyclerView recyclerView = findViewById(R.id.recyclerView_tasks);
            ItemTouchHelper.SimpleCallback callback = new SwipeItem(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
            mainTask = findViewById(R.id.mainTaskTV);
            Task task1 = new Task();//просто для проверки
            Task task2 = new Task();//просто для проверки
            List<Task> ltask = new ArrayList<>();//просто для проверки
            ltask.add(task1);//просто для проверки
            ltask.add(task2);//просто для проверки
            taskAdapter = new TaskAdapter(ltask,listener);
            recyclerView.setAdapter(taskAdapter);
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
                //метод свайпа должен удалять выполненную задачу из БД, Коля это тебе
                taskAdapter.removeTask(viewHolder.getAdapterPosition());
            }
        }
    }
}
