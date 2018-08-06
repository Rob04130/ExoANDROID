package com.example.ratech.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editId, editFirstName, editLastName, editMark;
    Button btnAddStudent, btnViewStudents, btnUpdateStudent, btnDeleteStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editId  = (EditText) findViewById(R.id.et_id); // only used for updating or deleting a student
        editFirstName = (EditText) findViewById(R.id.et_firstname);
        editLastName = (EditText) findViewById(R.id.et_lastname);
        editMark = (EditText) findViewById(R.id.et_mark);
        btnAddStudent = (Button) findViewById(R.id.bt_addStudent);
        btnViewStudents = (Button) findViewById(R.id.bt_viewStudents);
        btnUpdateStudent = (Button) findViewById(R.id.bt_updateStudent);
        btnDeleteStudent = (Button) findViewById((R.id.bt_deleteStudent));

        addStudent();
        viewStudents();
        updateStudent();
        deleteStudent();
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
                    Toast.makeText(MainActivity.this, "Student inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Student not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void viewStudents() {
        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllStudents();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "No students found");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id: " + res.getString(0) + "\n");
                        buffer.append("Firstname: " + res.getString(1) + "\n");
                        buffer.append("Lastname: " + res.getString(2) + "\n");
                        buffer.append("Mark: " + res.getString(3) + "\n\n");
                    }

                    // Show all data
                    showMessage("Students",buffer.toString());
                }
            }
        });
    }


    public void updateStudent() {
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateStudent(
                        Integer.parseInt(editId.getText().toString()),
                        editFirstName.getText().toString(),
                        editLastName.getText().toString(),
                        Integer.parseInt(editMark.getText().toString())
                );
                if (isUpdated) {
                    Toast.makeText(MainActivity.this, "Student updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Student not updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteStudent() {
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deletedStudent = myDb.deleteStudent(Integer.parseInt(editId.getText().toString()));
                if (deletedStudent > 0) {
                    Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Student not deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}