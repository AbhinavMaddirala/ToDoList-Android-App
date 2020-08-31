package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button addbutton;
    ImageButton todopage,addTodoPage;
    EditText edittexttask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db=new DatabaseHelper(this);
        addbutton = findViewById(R.id.addtask);
        todopage= findViewById(R.id.listviewbtn);
        addTodoPage=findViewById(R.id.addviewbtn);
        edittexttask = findViewById(R.id.taskInput);

        addtasktodb();

        todopage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        addTodoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You are already in Add ToDo",Toast.LENGTH_LONG).show();
            }
        });


    }


    public void addtasktodb(){

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = edittexttask.getText().toString();


                if(task.isEmpty()){
                    edittexttask.setError("please enter a task");
                }else{

                    boolean isAdded = db.addTask(task);

                    if(isAdded){
                        Toast.makeText(MainActivity.this,"Task added",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity.this,"Unable to add your task",Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });


    }

}


