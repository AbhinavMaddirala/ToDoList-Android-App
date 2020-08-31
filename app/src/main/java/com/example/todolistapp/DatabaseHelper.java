package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final  static String DATABASE_NAME ="ToDoApp.db";
    public final  static String TABLE_NAME ="ToDoAppTable";
    public final  static String COl1 ="ID";
    public final  static String COl2 ="TASK";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);


    }

    public boolean addTask(String task){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COl2,task);


        long result =  db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
        {
            return false;
        }else{
            return true;
        }
    }

    public boolean updateTask(String id,String task){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COl1,task);


        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Cursor getData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query ="SELECT * FROM " + TABLE_NAME + " WHERE ID='"+id+ "'";

        Cursor cursor=db.rawQuery(query,null);

        return cursor;


    }


    public Integer deleteData(String id){

        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID=?",new String[]{id});

    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        return cursor;



    }
}
