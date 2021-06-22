package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class ToDoModel {
    @SerializedName("_id")
    private String id;

    @SerializedName("is_done")
    private boolean isDone;

    @SerializedName("task")
    private String Task;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }
}
