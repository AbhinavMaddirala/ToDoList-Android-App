package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity<exampleList> extends AppCompatActivity {

    ImageButton goToAddToDo,listviewbtn;
    Button addbtn;
    DatabaseHelper myDb;

    ArrayList<ExampleItem> exampleList ;


    private RecyclerView recyclerView;
    private ExampleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView=findViewById(R.id.recyclerView);
        listviewbtn=findViewById(R.id.listviewbtn2);
        goToAddToDo= findViewById(R.id.addviewbtn2);
        addbtn=findViewById(R.id.addtask);
        myDb= new DatabaseHelper(this);


        goToAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        listviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this,"You are already in Todo List",Toast.LENGTH_SHORT).show();
            }
        });


        generateData();
        recyclerViewConfig();











    }

    public void changeItem(int position , String text){

        exampleList.get(position).changeText(text);
        adapter.notifyItemChanged(position);

    }

    public void removeItem(int position){
            exampleList.remove(position);
        adapter.notifyItemRemoved(position);


        }







    public void generateData(){
        exampleList = new ArrayList<>();

        Cursor cursor=myDb.getAllData();

        if(cursor.getCount()==0){
            Toast.makeText(Main2Activity.this,"No tasks in List",Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer=new StringBuffer();



        while(cursor.moveToNext()){

            exampleList.add(new ExampleItem(cursor.getString(1)));

        }


    }









    public void recyclerViewConfig(){
        //rv config

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        adapter = new ExampleAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListner(new ExampleAdapter.onItemClickListener() {
            @Override
            public void onRemoveClick(int position) {

                removeItem(position);



            }

            @Override
            public void onItemClick(int position) {

                changeItem(position,"Clicked");


            }
        });
    }


    public void  alreadyInListView(){
        Toast.makeText(this,"you are already in listview",Toast.LENGTH_SHORT).show();
    }
    public void returnToAddView(){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }





}
