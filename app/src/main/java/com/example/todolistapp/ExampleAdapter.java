package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

private ArrayList<ExampleItem> mexanpleList;
private onItemClickListener mListner;

    public interface onItemClickListener{
        void onItemClick(int position);
        void onRemoveClick(int position);
    }

    public void setOnItemClickListner (onItemClickListener listner){
        mListner = listner;
    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v,mListner);
        return exampleViewHolder;
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList){

        mexanpleList = exampleList;

    }




    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem = mexanpleList.get(position);
        holder.mtextview.setText(currentItem.getmText());

    }

    @Override
    public int getItemCount() {
        return mexanpleList.size();
    }

    public  static class ExampleViewHolder extends RecyclerView.ViewHolder{

        private TextView mtextview;
        public Button mCompletebtn;
        public Button mRemovebtn;

        public ExampleViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);

            mtextview = itemView.findViewById(R.id.tasktext);
            mCompletebtn=itemView.findViewById(R.id.completebtn);
            mRemovebtn=itemView.findViewById(R.id.removebtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });

            mRemovebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onRemoveClick(position);
                        }
                    }

                }
            });

        }
    }
}
