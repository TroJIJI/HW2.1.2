package com.example.android122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {
    EditText name;
    EditText surname;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        name = findViewById(R.id.et_name);
        surname = findViewById(R.id.et_surname);
        save = findViewById(R.id.btn_save);


        if (getIntent() != null){
            name.setText(getIntent().getStringExtra("Name"));
            surname.setText(getIntent().getStringExtra("Fio"));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("fio", surname.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
        });

    }
}