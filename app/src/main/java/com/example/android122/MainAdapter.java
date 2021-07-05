package com.example.android122;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private onRecyclerViewClickListener listener;
    private ArrayList<Student> list = new ArrayList<>();

    public interface onRecyclerViewClickListener {
        void onItemClick(Student students, int position);
    }

    public void onRecyclerViewClickListener(onRecyclerViewClickListener listener) {
        this.listener = listener;
    }
//    public MainAdapter() {
//    }

    public void addStudent(Student student) {
        this.list.add(student);
        notifyDataSetChanged();
    }

    public void changeStudent(Student student, int pos) {
        list.remove(pos);
        this.list.add(pos, student);
        notifyItemChanged(pos);
    }

    public MainAdapter() {
    }
//
//    public void setList(ArrayList<Student> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }
//
//    public void addStudent(Student student){
//        this.list.add(student);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));

        if (position%2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF000000"));
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    TextView name;
    TextView surname;

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            surname = view.findViewById(R.id.surname);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.onItemClick(list.get(getAdapterPosition()),getAdapterPosition());
                    }
                }
            });
        }

        public void onBind(Student student) {
            name.setText(student.getName());
            surname.setText(student.getSurname());
        }
    }
}
