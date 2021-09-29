package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ToDoList mToDoList;
    private EditText mItemEditText;
    private TextView mItemListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemEditText = findViewById(R.id.todo_item);
        mItemListTextView = findViewById(R.id.item_list);

        mToDoList = new ToDoList(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            mToDoList.readFromFile();
            displayList();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            mToDoList.saveToFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addButtonClick(View view) {
        String task = mItemEditText.getText().toString().trim();
        mItemEditText.setText("");

        if (task.length() > 0) {
            mToDoList.addItem(task);
            displayList();
        }
    }

    private void displayList() {
        StringBuffer taskText = new StringBuffer();
        String[] tasks = mToDoList.getTasks();

        for (int i = 0; i < tasks.length; i++) {
            taskText.append(i +1).append(". ").append(tasks[i]).append("\n");
        }
        mItemListTextView.setText(taskText);
    }

    public void clearButtonClick(View view) {
        mToDoList.clear();
        displayList();
    }
}