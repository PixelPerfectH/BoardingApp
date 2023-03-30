package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TasksActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private TaskAdapter taskAdapter;
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tasks);
            recyclerView = findViewById(R.id.recyclerView_tasks);

            ItemTouchHelper.SimpleCallback callback = new SwipeItem(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);

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
                //метод свайпа должен удалять выполненную задачу из списка, Коля это тебе
                taskAdapter.removeTask(viewHolder.getAdapterPosition());
            }
        }
    }
}
