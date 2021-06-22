package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.ToDoClient;
import com.example.myapplication.AddNewTask;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.ToDoModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
  private List<ToDoModel> toDoModelList;
  private MainActivity mainActivity;
  private MainActivity activity;
  //    private DatabaseHandler db;

  public ToDoAdapter(MainActivity activity) {
//        this.db = db;
    this.activity = activity;
  }

  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
    return new ViewHolder(itemView);
  }

  public void onBindViewHolder(ViewHolder holder, int position) {
    ToDoModel item = toDoModelList.get(position);
    holder.task.setText(item.getTask());
    holder.task.setChecked(item.isDone());
  }

  public int getItemCount() {
    return toDoModelList.size();
  }

  public void setTasks(List<ToDoModel> tasks) {
    this.toDoModelList = tasks;
    notifyDataSetChanged();
  }

  public Context getContext() {
    return activity;
  }

  public void deleteItem(int position) {
    ToDoModel item = toDoModelList.get(position);
//        db.deleteTask(item.getId());
    ToDoClient.getInstance().deleteTask(this, item.getId(), position);
  }

  public void deleteItemSuccess(int position) {
    toDoModelList.remove(position);
    notifyItemRemoved(position);
  }

  public void addItemSuccess(ToDoModel toDoModel,int position) {
    this.toDoModelList.add(toDoModel);
    notifyItemInserted(position);
  }

  public void updateItemSuccess(ToDoModel toDoModel) {
    int position = this.toDoModelList.indexOf(this.toDoModelList.stream().map(x->x.getId() == toDoModel.getId()));
    this.toDoModelList.remove(position);
    this.toDoModelList.add(position,toDoModel);
    notifyItemChanged(position);
  }

  public void editItem(int position) {
    ToDoModel item = toDoModelList.get(position);
    Bundle bundle = new Bundle();
    bundle.putString("id", item.getId());
    bundle.putString("task", item.getTask());
    AddNewTask fragment = AddNewTask.getInstance();
    fragment.setArguments(bundle);
    fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    CheckBox task;

    ViewHolder(View view) {
      super(view);
      task = view.findViewById(R.id.todoCheckBox);
    }
  }
}
