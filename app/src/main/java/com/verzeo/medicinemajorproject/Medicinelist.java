package com.verzeo.medicinemajorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Medicinelist extends RecyclerView.Adapter<Medicinelist.MyViewHolder> {

    Context context;

    ArrayList<UserHelperClass> list;

    public Medicinelist(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserHelperClass userHelperClass = list.get(position);
        holder.name.setText(userHelperClass.getName());
        holder.dose.setText(userHelperClass.getDose());
        holder.date.setText(userHelperClass.getDate());
        holder.time.setText(userHelperClass.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, dose, date, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            dose = itemView.findViewById(R.id.dose);
            date = itemView.findViewById(R.id.dt);
            time = itemView.findViewById(R.id.tm);

        }
    }
}
