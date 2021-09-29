package com.example.todolist;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

public class ToDoList {

    public static final String FILENAME = "todolist.txt";
    private Context mContext;
    private List<String> mTaskList;

    public ToDoList(Context context) {
        mContext = context;
        mTaskList = new ArrayList<>();
    }

    public void addItem(String task) throws IllegalArgumentException {
        mTaskList.add(task);
    }

    public String[] getTasks() {
        String[] tasks = new String[mTaskList.size()];
        return mTaskList.toArray(tasks);
    }

    public void clear() {
        mTaskList.clear();
    }

    public void saveToFile() throws IOException {
        FileOutputStream outputStream = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        writeListToStream(outputStream);
    }

    private void writeListToStream(FileOutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        for(String item : mTaskList) {
            writer.println(item);
        }
        writer.close();
    }

    public void readFromFile() throws IOException {
        FileInputStream inputStream = mContext.openFileInput(FILENAME);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            mTaskList.clear();
            String line;

            while((line = reader.readLine()) != null) {
                mTaskList.add(line);
            }
        }
        catch (FileNotFoundException ex) {

        }
    }

}
