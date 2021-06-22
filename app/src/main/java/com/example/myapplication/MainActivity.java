package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.API.ToDoClient;
import com.example.myapplication.Adapter.ToDoAdapter;
import com.example.myapplication.Model.ToDoModel;
import com.example.myapplication.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

//    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private RecyclerView taskRecyclerView;
    public List<ToDoModel> toDoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

//        db = new DatabaseHandler(this);
//        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.rvListTasks);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(MainActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.btnAdd);

        toDoModelList = new ArrayList<>();
//        toDoModelList = db.getAllTasks();
        ToDoClient.getInstance().getAllTask(tasksAdapter);

        Collections.reverse(toDoModelList);
        tasksAdapter.setTasks(toDoModelList);

        AddNewTask addNewTask = AddNewTask.getInstance();
        addNewTask.setToDoAdapter(tasksAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AddNewTask.getInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
                addNewTask.show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
//        toDoModelList = db.getAllTasks();
//        toDoModelList = toDoClient.getAllTask();
//        toDoClient.getAllTask(tasksAdapter);
//        Collections.reverse(toDoModelList);
//        tasksAdapter.setTasks(toDoModelList);
//        tasksAdapter.notifyDataSetChanged();
    }
}