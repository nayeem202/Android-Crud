package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;

public class MainActivity extends AppCompatActivity {
    EditText  ed1,ed2,ed3, ed4, ed5;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.email);
        ed3 = findViewById(R.id.mobile);
        ed4 = findViewById(R.id.subject);
        ed5 = findViewById(R.id.address);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }


    public void insert(){
        {
            try
            {
                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String mobile = ed3.getText().toString();
                String subject = ed4.getText().toString();
                String address = ed5.getText().toString();



                SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,email VARCHAR,mobile INTEGER, subject VARCHAR, address VARCHAR )");

                String sql = "insert into students(name,email,mobile,subject,address)values(?,?,?,?,?)";

                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1,name);
                statement.bindString(2,email);
                statement.bindString(3,mobile);
                statement.bindString(4,subject);
                statement.bindString(5,address);
                statement.execute();
                Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
                ed5.setText("");
                ed1.requestFocus();
            }
            catch (Exception ex)
            {
                Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
            }
        }
    }}