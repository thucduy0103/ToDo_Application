package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myapplication.API.ToDoClient;
import com.example.myapplication.Adapter.ToDoAdapter;
import com.example.myapplication.Model.ToDoModel;
import com.example.myapplication.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Button newTaskSaveButton;

//    private DatabaseHandler db;
    private ToDoAdapter toDoAdapter;

    @SuppressLint("StaticFieldLeak")
    private static AddNewTask instance;

    private AddNewTask() {

    }

    public static AddNewTask getInstance() {
        return instance == null ? instance = new AddNewTask() : instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_task, container, false);
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = requireView().findViewById(R.id.etNewTaskText);
        newTaskSaveButton = requireView().findViewById(R.id.btnSave);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            assert task != null;
            if(task.length()>0)
                newTaskSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark));
        }

//        db = new DatabaseHandler(getActivity());
//        db.openDatabase();

        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().equals("")){
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(v -> {
            String text = newTaskText.getText().toString();
            if(finalIsUpdate){
//                db.updateTask(bundle.getInt("id"), text);
                ToDoModel toDoModel = new ToDoModel();
                toDoModel.setTask(text);
                toDoModel.setId(bundle.getString("id"));
                ToDoClient.getInstance().updateTask(this.getToDoAdapter(),toDoModel);
            }
            else {
                if(text.trim().equals("")){
                    Toast.makeText(requireContext(),"String empty",Toast.LENGTH_LONG).show();
                }
                else {
                    ToDoModel task = new ToDoModel();
                    task.setTask(text);
                    task.setDone(false);
//                    db.insertTask(task);
                    ToDoClient.getInstance().createTask(this.toDoAdapter,task,0);
                }
            }
            dismiss();
        });
    }

    public ToDoAdapter getToDoAdapter() {
        return toDoAdapter;
    }

    public void setToDoAdapter(ToDoAdapter toDoAdapter) {
        this.toDoAdapter = toDoAdapter;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);
    }
}
