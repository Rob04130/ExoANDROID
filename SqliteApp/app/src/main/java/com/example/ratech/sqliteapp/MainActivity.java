package com.example.ratech.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editFirstName, editLastName, editMark;
    Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editFirstName = (EditText) findViewById(R.id.et_firstname);
        editLastName = (EditText) findViewById(R.id.et_lastname);
        editMark = (EditText) findViewById(R.id.et_mark);
        btnAddStudent = (Button) findViewById(R.id.bt_addStudent);

        addStudent();
    }


    public void addStudent() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertStudent(
                        editFirstName.getText().toString(),
                        editLastName.getText().toString(),
                        Integer.parseInt(editMark.getText().toString()));
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this,"Student inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"Student not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
