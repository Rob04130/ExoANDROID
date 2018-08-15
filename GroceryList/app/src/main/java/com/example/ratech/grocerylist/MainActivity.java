package com.example.ratech.grocerylist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;

    private SQLiteDatabase mDatabase;
    private GroceryAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroceryAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);
        
        mEditTextName = findViewById(R.id.et_name);
        mTextViewAmount = findViewById(R.id.tv_amount);

        Button buttonIncrease = findViewById(R.id.bt_increase);
        Button buttonDecrease = findViewById(R.id.bt_decrease);
        Button buttonAdd = findViewById(R.id.bt_add);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }

    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    private void addItem() {
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);

        mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
                
        mEditTextName.getText().clear(); // clear for the next entry
    }


    private Cursor getAllItems() {
        return mDatabase.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

}
