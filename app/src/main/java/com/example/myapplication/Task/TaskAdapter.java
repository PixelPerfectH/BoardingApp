package com.example.myapplication.Task;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>  {
    Context context;
    private final List<Task> mData;
    public final List<Task> FullData;
    private final TaskListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mData.get(position);
        holder.bindView(task);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void removeTask(int pos) {
        Task task = mData.get(pos);
        mData.remove(task);
        FullData.remove(task);
        notifyItemRemoved(pos);
    }

    public TaskAdapter(List<Task> mData, TaskListener taskListener){
        FullData = mData;
        this.mData = new ArrayList<>(mData);
        this.listener = taskListener;
    }


    public Task getTask(int position){
        return FullData.get(position);
    }

    public interface TaskListener{
        void onTaskClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        final TextView name,description;
        final ImageView avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewNameItem);
            description = itemView.findViewById(R.id.textViewItem);
            avatar = itemView.findViewById(R.id.imageViewItem);
            itemView.setOnClickListener(v -> listener.onTaskClick(v, getAdapterPosition()));
        }

        void bindView(Task task) {
            this.task = task;
            if(!task.isActive()){
                CardView cardView = itemView.findViewById(R.id.CardViewItem);
                cardView.setCardBackgroundColor(context.getColor(R.color.grey));
            }
            name.setText(task.getName());
            description.setText(task.getDescription());
            //avatar.setImageBitmap(task.);//вот здесь доделать
            //avatar.setImageBitmap(task.getUser().getAvatar());Убрать комментарий когда появятся фотки
        }
    }
}
