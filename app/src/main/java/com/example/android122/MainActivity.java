package com.example.android122;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter adapter;
    FloatingActionButton fab;
    private Student student;
    private int pos;
    private boolean isChanged = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        ArrayList<Student> list = new ArrayList<>();


//        adapter.changeStudent(list);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                isChanged = false;
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        student = new Student(data.getStringExtra("name"),data.getStringExtra("fio"));

        if (isChanged) {
            adapter.changeStudent(student, pos);
        } else {
            adapter.addStudent(student);
        }

        adapter.onRecyclerViewClickListener(new MainAdapter.onRecyclerViewClickListener() {
            @Override
            public void onItemClick(Student students, int position) {
                pos = position;
                isChanged = true;
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("Name", data.getStringExtra("name"));
                intent.putExtra("Fio",data.getStringExtra("fio"));
                startActivityForResult(intent, 200);
            }
        });
    }
}