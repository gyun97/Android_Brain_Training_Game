package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MemoryGame_Chae extends AppCompatActivity {
    Random rnd = new Random();
    int time = 60;
    int cnt = 0;
    int k=0;
    char[] problem = new char[cnt+1];
    char[] answer = new char[cnt+1];
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num10;
    Button num11;
    Button num12;
    Button num13;
    Button num14;
    Button num15;
    Button reset;
    Button enter;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textview1;
    TextView textview2;
    TextView textview3;
    TextView textview4;
    TextView textview5;
    TextView textview6;
    BackThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        setButton();
        setTextView();
        textView2.setText(String.valueOf(cnt));
        main();


        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 60) {
                    textView.setText(
                            "Time : " + msg.arg1);
                    time = msg.arg1;
                    if (time == 0) {
                        Intent i = new Intent(MemoryGame_Chae.this, MainActivity.class);
                        String textview1 = textView2.getText().toString();
                        i.putExtra("textview1", textview1);
                        String textview22 = textview2.getText().toString();
                        i.putExtra("textview2", textview22);
                        String textview33 = textview3.getText().toString();
                        i.putExtra("textview3", textview33);
                        String textview44 = textview4.getText().toString();
                        i.putExtra("textview4", textview44);
                        String textview55 = textview5.getText().toString();
                        i.putExtra("textview5", textview55);
                        String textview66 = textview6.getText().toString();
                        i.putExtra("textview6", textview66);
                        startActivity(i);
                        finish();
                    }
                    Intent intent = getIntent();
                    String textview22 = intent.getStringExtra("textview1");
                    textview2.setText(textview22);
                    String textview33 = intent.getStringExtra("textview2");
                    textview3.setText(textview33);
                    String textview44 = intent.getStringExtra("textview3");
                    textview4.setText(textview44);
                    String textview55 = intent.getStringExtra("textview4");
                    textview5.setText(textview55);
                    String textview66 = intent.getStringExtra("textview5");
                    textview6.setText(textview66);
//                    String textview77 = intent.getStringExtra("textview6");
//                    textview3.setText(textview77);
                }
            }
        };
        textView4 = (TextView) findViewById(R.id.second_textView);
        mThread = new BackThread(mHandler);
        mThread.setDaemon(true);
        mThread.start();
    }

    private void setButton() {
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num10 = findViewById(R.id.num10);
        num11 = findViewById(R.id.num11);
        num12 = findViewById(R.id.num12);
        num13 = findViewById(R.id.num13);
        num14 = findViewById(R.id.num14);
        num15 = findViewById(R.id.num15);
        reset = findViewById(R.id.reset);
        enter = findViewById(R.id.enter);
        num1.setOnClickListener(numClickListener);
        num2.setOnClickListener(numClickListener);
        num3.setOnClickListener(numClickListener);
        num4.setOnClickListener(numClickListener);
        num5.setOnClickListener(numClickListener);
        num6.setOnClickListener(numClickListener);
        num7.setOnClickListener(numClickListener);
        num8.setOnClickListener(numClickListener);
        num9.setOnClickListener(numClickListener);
        num10.setOnClickListener(numClickListener);
        num11.setOnClickListener(numClickListener);
        num12.setOnClickListener(numClickListener);
        num13.setOnClickListener(numClickListener);
        num14.setOnClickListener(numClickListener);
        num15.setOnClickListener(numClickListener);
        reset.setOnClickListener(numClickListener);
        enter.setOnClickListener(numClickListener);
        num1.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num2.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num3.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num4.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num5.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num6.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num7.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num8.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num9.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num10.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num11.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num12.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num13.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num14.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num15.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
    }

    Button.OnClickListener numClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.num10:
                    problem[k] = 'a';
                    k++;
                    break;
                case R.id.num11:
                    problem[k] = 'b';
                    k++;
                    break;
                case R.id.num12:
                    problem[k] = 'c';
                    k++;
                    break;
                case R.id.num13:
                    problem[k] = 'd';
                    k++;
                    break;
                case R.id.num14:
                    problem[k] = 'e';
                    k++;
                    break;
                case R.id.num15:
                    problem[k] = 'f';
                    k++;
                    break;
                case R.id.num1:
                    problem[k] = '1';
                    k++;
                    break;
                case R.id.num2:
                    problem[k] = '2';
                    k++;
                    break;
                case R.id.num3:
                    problem[k] = '3';
                    k++;
                    break;
                case R.id.num4:
                    problem[k] = '4';
                    k++;
                    break;
                case R.id.num5:
                    problem[k] = '5';
                    k++;
                    break;
                case R.id.num6:
                    problem[k] = '6';
                    k++;
                    break;
                case R.id.num7:
                    problem[k] = '7';
                    k++;
                    break;
                case R.id.num8:
                    problem[k] = '8';
                    k++;
                    break;
                case R.id.num9:
                    problem[k] = '9';
                    k++;
                    break;
                case R.id.reset:
                    problem = new char[cnt+1];
                    k=0;
                    main3();
                    break;
                case R.id.enter:
                    int cntt=0;
                    k=0;
                    for (char m : answer){
                        for ( char j : problem){
                            if (m==j){
                                cntt++;
                            }
                        }
                    }
                    if (cntt-1==cnt) {
                        cnt++;
                        problem = new char[cnt+1];
                        textView2.setText(String.valueOf(cnt));
                        main3();
                    }
                    break;
            }
        }

    };

    private void setTextView() {
        textView = findViewById(R.id.first_textView);
        textView2 = findViewById(R.id.second_textView);
        textView3 = findViewById(R.id.third_textView);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        textview3 = findViewById(R.id.textview3);
        textview4 = findViewById(R.id.textview4);
        textview5 = findViewById(R.id.textview5);
        textview6 = findViewById(R.id.textview6);
    }
    private void main(){
        main1();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                main2();
            }
        }, 2000);
    }
    private void main3(){
        main1();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                main2();
            }
        }, 600);
    }
    private void main1(){
        problem = new char[cnt+1];
        answer = new char[cnt+1];
        for (int i=0;i<=cnt;i++){
            int a = rnd.nextInt(2);
            char c;
            if(a==0){
                c = (char) (rnd.nextInt(9) + 49);
            }
            else{
                c = (char) (rnd.nextInt(6) + 97);
            }
            answer[i]=c;
        }
        for(char l : answer){
            if (l == '1') {
                num1.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '2') {
                num2.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '3') {
                num3.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '4') {
                num4.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '5') {
                num5.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '6') {
                num6.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '7') {
                num7.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '8') {
                num8.setBackgroundColor(Color.TRANSPARENT);
            }if (l == '9') {
                num9.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'a') {
                num10.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'b') {
                num11.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'c') {
                num12.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'd') {
                num13.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'e') {
                num14.setBackgroundColor(Color.TRANSPARENT);
            }if (l == 'f') {
                num15.setBackgroundColor(Color.TRANSPARENT);
            }


        }

    }
    private void main2(){
        num1.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num2.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num3.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num4.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num5.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num6.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num7.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num8.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num9.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num10.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num11.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num12.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num13.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num14.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
        num15.setBackgroundColor(Color.rgb(0x20,0x20,0x20));
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
            ir.setClass(MemoryGame_Chae.this, MainActivity.class);
            startActivity(ir);
        }


        return super.onOptionsItemSelected(item);
    }
}
class BackThread extends Thread {
    int mBackValue = 60;
    Handler bHandler;
    BackThread(Handler handler) {
        bHandler = handler;
    }
    public void run() {
        while (mBackValue!=0) {
            mBackValue--;
            Message msg = new Message();
            msg.what = 60;
            msg.arg1 = mBackValue;
            bHandler.sendMessage(msg);
            try { Thread.sleep(1000); }
            catch (InterruptedException e) {;}
        }
    }
}