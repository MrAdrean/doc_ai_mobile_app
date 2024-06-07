package com.example.docai_utlimatum;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.ID.setText(String.valueOf(model.getID()));
        holder.BPM.setText(String.valueOf(model.getBpm()));
        holder.SpO2.setText(String.valueOf(model.getSpo2()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ID;
        TextView BPM;
        TextView SpO2;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            ID = itemView.findViewById(R.id.ID_text);
            BPM = itemView.findViewById(R.id.BPM);
            SpO2 = itemView.findViewById(R.id.spo2);

        }
    }
}
