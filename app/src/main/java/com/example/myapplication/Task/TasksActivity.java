package com.example.myapplication.Task;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Level;
import com.example.myapplication.models.Task;
import com.example.myapplication.requests.GetRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TasksActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private TextView mainTask;
    int level;
    String login;
    private List<Task> taskList = new ArrayList<>(); //Этот массив надо как-то тасками заполнить
    //И перед завершением этот же массив обратно в БД отправлять , чтобы он обновлялся
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
            login = getIntent().getStringExtra("login");
            level = getIntent().getIntExtra("level",1);// номер уровня

            GetRequest getRequest = new GetRequest();
            String result = null;
            try {
                System.out.println(level);
                result = getRequest.execute("https://clerostyle.drawy.ru/api/level/get?userName=" + "CleroStyle" + "&levelId=" + level).get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (result != null) {
                // вот тебе объект уровня, наслаждайся
                Level level = gson.fromJson(result, Level.class);
                System.out.println(level.getName());
                System.out.println(level.level);
            }


            mainTask = findViewById(R.id.mainTaskTV);
            mainTask.setText("");//сюда надо главное задание из БД подгрузить(Можно и в переменную));
            taskAdapter = new TaskAdapter(taskList,listener);
            recyclerView.setAdapter(taskAdapter);

        }

        @Override
        protected void onStop() {
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
                //метод свайпа должен удалять выполненную задачу из БД, Коля это тебе
                taskAdapter.removeTask(viewHolder.getAdapterPosition());
            }
        }
    }
}
