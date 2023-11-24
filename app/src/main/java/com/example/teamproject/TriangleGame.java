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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class TriangleGame extends AppCompatActivity {

    Button[] button = new Button[10];
    Integer[] Rid_button = {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
    };
    Button btErase,btEnter, btMinus;

    TextView tvNum1, tvNum2, tvNum3, tvCount, tvExp1, tvExp2, tvExp3, tvAnswer; // tvAnswer = 입력한 정답
    int gameAnswer;  // 게임의 정답
    int count;  // 맞힌 개수

    ProgressBar pbTime;
    private static final int START = 1;
    int timeCount = 60;

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
        setContentView(R.layout.activity_triangle_game);

        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        tvNum3 = findViewById(R.id.tvNum3);
        tvExp1 = findViewById(R.id.tvExp1);
        tvExp2 = findViewById(R.id.tvExp2);
        tvExp3 = findViewById(R.id.tvExp3);

        tvCount = findViewById(R.id.tvCount);
        btErase = findViewById(R.id.buttonErase);
        btEnter = findViewById(R.id.buttonEnter);
        btMinus = findViewById(R.id.buttonMinus);
        tvAnswer = findViewById(R.id.tvAnswer);

        pbTime = findViewById(R.id.pbTime);

        //버튼 정의
        for (int i = 0 ; i<10;i++){
            int index = i;
            button[index] = (Button) findViewById(Rid_button[index]);
            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvAnswer.append(button[index].getText().toString());
                }
            });
        }

        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAnswer.append("-");
            }
        });

        btErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAnswer.setText("");
            }
        });

        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameAnswer == Integer.parseInt(tvAnswer.getText().toString())){
                    count+=1;
                    tvCount.setText("정답 수 : " + count);
                    tvAnswer.setText("");
                    GameStart();
                }
                else {
                    tvAnswer.setText("");
                }
            }
        });

        GameStart();
        timeHandler.sendEmptyMessage(START);
    }



    public void GameStart(){
        Random RANDOM = new Random();
        String[] expr = {"+", "-", "*"};

        int ran1 = RANDOM.nextInt(3);
        int ran2 = RANDOM.nextInt(3);
        int ran3 = RANDOM.nextInt(3);

        String ranExpr1 = expr[ran1];
        String ranExpr2 = expr[ran2];
        String ranExpr3 = expr[ran3];

        int randNum1 = RANDOM.nextInt(9) + 1;
        int randNum2 = RANDOM.nextInt(9) + 1;
        int randNum3 = RANDOM.nextInt(9) + 1;

        tvExp1.setText(ranExpr1);
        tvExp2.setText(ranExpr2);
        tvExp3.setText(ranExpr3);

        tvNum1.setText(Integer.toString(randNum1));
        tvNum2.setText(Integer.toString(randNum2));
        tvNum3.setText(Integer.toString(randNum3));

        String text1 = randNum1 + ranExpr1 + randNum2;
        String text2 = randNum2 + ranExpr2 + randNum3;

        int[] numbers = {randNum1, randNum2, randNum3};

        double temp1 = Double.parseDouble(cal(text1.trim()));
        double temp2 = Double.parseDouble(cal(text2.trim()));
        String text3 = temp1 + ranExpr3 + temp2;

        gameAnswer = (int) Double.parseDouble(cal(text3.trim()));
    }

    public static String cal(String result) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("rhino");
        try {
            return engine.eval(result).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
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
                tvAnswer.setText("");
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

    public static class MainMainActivity {
    }
}