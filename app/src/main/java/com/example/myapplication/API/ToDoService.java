package com.example.myapplication.API;

import com.example.myapplication.Model.ToDoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ToDoService {
    @GET("/api/get-all-tasks")
    Call<List<ToDoModel>> doGetListTask();

    @POST("/api/create-task")
    Call<ToDoModel> createTask(@Body ToDoModel toDoModel);

    @PUT("/api/update-task")
    Call<ToDoModel> updateTask(@Body ToDoModel toDoModel);

    @DELETE("/api/delete-task/{id}")
    Call<ToDoModel> deleteTask(@Path("id") String id);
}
