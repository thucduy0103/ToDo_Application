package com.example.myapplication.Model;

public class ToDoModel {
    private int id;
    private boolean isDone;
    private String Task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
