package com.example.todolistapp;

public class ExampleItem {

    private String mText;


    public ExampleItem(String text) {
     mText = text;
    }

    public void changeText(String text){
        mText=text;
    }


    public String getmText() {

        return mText;
    }
}
