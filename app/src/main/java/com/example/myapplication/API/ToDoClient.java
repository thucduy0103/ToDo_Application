package com.example.myapplication.API;

import com.example.myapplication.Adapter.ToDoAdapter;
import com.example.myapplication.AddNewTask;
import com.example.myapplication.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoClient {
    private ToDoService toDoService= APIClient.getClient().create(ToDoService.class);

    private static ToDoClient instance;

    private ToDoClient(){

    }

    public static ToDoClient getInstance() {
        return instance == null ? instance = new ToDoClient() : instance;
    }

    public void getAllTask (ToDoAdapter tasksAdapter){
        Call<List<ToDoModel>> call = toDoService.doGetListTask();
            call.enqueue(new Callback<List<ToDoModel>>() {
                @Override
                public void onResponse(Call<List<ToDoModel>> call, Response<List<ToDoModel>> response) {
                    tasksAdapter.setTasks(response.body());
                }

                @Override
                public void onFailure(Call<List<ToDoModel>> call, Throwable t) {

                }
            });
    }
    public void createTask (ToDoAdapter tasksAdapter,ToDoModel task,int position){
        Call<ToDoModel> call = toDoService.createTask(task);
            call.enqueue(new Callback<ToDoModel>() {
                @Override
                public void onResponse(Call<ToDoModel> call, Response<ToDoModel> response) {
                    tasksAdapter.addItemSuccess(response.body(),position);
                }

                @Override
                public void onFailure(Call<ToDoModel> call, Throwable t) {

                }
            });
    }
    public void updateTask (ToDoAdapter tasksAdapter,ToDoModel task){
        Call<ToDoModel> call = toDoService.updateTask(task);
        call.enqueue(new Callback<ToDoModel>() {
            @Override
            public void onResponse(Call<ToDoModel> call, Response<ToDoModel> response) {
                tasksAdapter.updateItemSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ToDoModel> call, Throwable t) {

            }
        });
    }
    public void deleteTask (ToDoAdapter tasksAdapter,String id,int position){
        Call<ToDoModel> call = toDoService.deleteTask(id);
        call.enqueue(new Callback<ToDoModel>() {
            @Override
            public void onResponse(Call<ToDoModel> call, Response<ToDoModel> response) {
                tasksAdapter.deleteItemSuccess(position);
            }

            @Override
            public void onFailure(Call<ToDoModel> call, Throwable t) {

            }
        });
    }
}
