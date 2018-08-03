package com.example.ratech.displaydate1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String today() {
        final Date date = new Date();
        return new SimpleDateFormat("EEEE d MMMM yyyy").format(date);
        //return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Java 8
    }

    public void displayDate(View view) {
        String date = today();
        TextView zoneMsg = (TextView) findViewById(R.id.textView);
        zoneMsg.setText(date);
    }

}