package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class BlankGame extends AppCompatActivity {

    Button btErase,btEnter;
    TextView tvQuestion,tvCount;
    EditText etAnswer;
    ProgressBar pbTime;
    int gameAnswer;
    int count;
    int timeCount = 60;
    Button[] button = new Button[10];
    Integer[] Rid_button = {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
    };
    int randNum2;

    private static final int START = 1;

    Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case START:
                    if(timeCount > 0)
                    {
                        pbTime.setProgress(timeCount);
                        timeCount--;
                        sendEmptyMessageDelayed(START, 1000);
                    }
                    else {
                        Intent ir = getIntent() ;
                        ir.putExtra("result" , count) ;
                        setResult(RESULT_OK , ir);
                        finish();
                    }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_game);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvCount = findViewById(R.id.tvCount);
        etAnswer = findViewById(R.id.etAnswer);
        btErase = findViewById(R.id.buttonErase);
        btEnter = findViewById(R.id.buttonEnter);
        pbTime = findViewById(R.id.pbTime);

        //버튼 정의
        for (int i = 0 ; i<10;i++){
            int index = i;
            button[index] = (Button) findViewById(Rid_button[index]);
            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etAnswer.append(button[index].getText().toString());
                }
            });
        }

        btErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAnswer.setText("");
            }
        });

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randNum2 == Integer.parseInt(etAnswer.getText().toString())){
                    count+=1;
                    tvCount.setText("정답 수 : " + count);
                    etAnswer.setText("");
                    GameStart();
                }
                else {
                    etAnswer.setText("");
                }
            }
        });

        GameStart();
        timeHandler.sendEmptyMessage(START);
    }


    final int Restart = Menu.FIRST;
    final int Exit = Menu.FIRST + 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, Restart, Menu.NONE, "Restart");
        menu.add(Menu.NONE, Exit, Menu.NONE, "Exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case Restart:{
                timeCount = 60;
                count=0;
                tvCount.setText("정답 수 : 0");
                etAnswer.setText("");
                GameStart();
                break;
            }
            case Exit:{
                Intent ir = getIntent() ;
                ir.putExtra("result" , count) ;
                setResult(RESULT_OK , ir);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);

    }


    public int GameStart(){
        Random RANDOM = new Random();
        int randNum1 = RANDOM.nextInt(8) + 2;
        randNum2 = RANDOM.nextInt(8) + 2;
        gameAnswer = randNum1 * randNum2;

        String text = randNum1 + " *" + " ?" + " = " + gameAnswer;
        tvQuestion.setText(text);

        return gameAnswer;
    }




}