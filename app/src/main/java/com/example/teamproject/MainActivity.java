package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btTriGame, btDB;
    Button bt1, bt3, bt4, bt5, bt6, bt7;


    final int RQCodeBlankGame = 1;
    final int RQCodeTriGame = 2;
    final int RQCode3= 3;
    final int RQCode4 = 4;
    final int RQCode5 = 5;
    final int RQCode6 = 6;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        btTriGame = findViewById(R.id.bt2);

        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity_sudoku.class);
                startActivity(i);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MemoryGame_Chae.class);
                startActivity(i);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CardGame_Kang.class);
                startActivity(i);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SameNumberGame_Hojin.class);
                startActivity(i);
            }
        });

        btTriGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TriangleGame.class);
                startActivity(i);
            }
        });


    }


     // bt5 김도균
    public void startGame(View view) {
        setContentView(new AnimalGame_DoGyun(this));
    }
    final int Exit = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Exit, Menu.NONE, "Exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == Exit) {
            Intent ir = getIntent();
            ir.setClass(MainActivity.this, MainActivity.class);
            startActivity(ir);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}
