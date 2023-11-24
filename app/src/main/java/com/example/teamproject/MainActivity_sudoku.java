package com.example.teamproject;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity_sudoku extends AppCompatActivity {
    int row=0, col=0;

    ConstraintLayout con;
    TableLayout pad;
    LayoutInflater inflater;

    CustomButton[][] buttons=new CustomButton[9][9];

    View Memo;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sudoku);

        TableLayout table=(TableLayout)findViewById(R.id.tableLayout);
        TableRow[] tableRows=new TableRow[9];
        BoardGenerator board=new BoardGenerator();

        con=findViewById(R.id.container);
        inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.number_pad, con, true);
        pad=con.findViewById(R.id.customPad);

        Memo=(View)View.inflate(this, R.layout.memo_pad, null);
        builder=new AlertDialog.Builder(this)
                .setTitle("메모")
                .setView(Memo)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*for (int j=0; j<9; j++) {
                            onToggleClicked();
                        }*/
                    }
                })
                .setNegativeButton("취소", null)
                .setNeutralButton("초기화", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onToggleRestart();
                    }
                });
        alertDialog=builder.create();
        for (int i=0; i<9; i++) {
            tableRows[i]=new TableRow(this);
            table.addView(tableRows[i]);
            for (int j=0; j<9; j++) {
                int number=board.get(i, j);
                double problem=Math.random()*10;
                CustomButton button= new CustomButton(this, i, j);
                buttons[i][j]=button;
                //출제 난이도 조정
                if (problem<4) button.set(number);
                else {
                    button.hidden();
                    //button.addView(pad2);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pad.setVisibility(VISIBLE);
                            row=button.getRow(); col=button.getCol();
                        }
                    });
                    button.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            alertDialog.show();
                            row=button.getRow(); col=button.getCol();
                            return true;
                        }
                    });
                }

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);
                if (j%3==2)
                    layoutParams.setMargins(10, 10, 30, 10);
                else if (i%3==2)
                    layoutParams.setMargins(10, 10, 10, 30);
                else
                    layoutParams.setMargins(10, 10, 10, 10);
                button.setLayoutParams(layoutParams);
                tableRows[i].addView(button);
            }
        }
    }

    public void unsetConflict() {
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                buttons[i][j].correct();
    }

    public void setConflict() {
        int conflict=0;

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (i==row && j==col) continue;
                else if (row==i) {
                    if (buttons[row][col].getValue() == buttons[i][j].getValue()) {
                        buttons[i][j].wrong();
                        conflict++;
                    }
                }
                else if (col==j) {
                    if (buttons[row][col].getValue() == buttons[i][j].getValue()) {
                        buttons[i][j].wrong();
                        conflict++;
                    }
                }
            }
        }
        //3x3 내의 중복 필터 필요
        //row/3
        //col/3
        //row,col=6,4일 경우 2,1이 됨
        //6, 7, 8 / 3, 4, 5 -> row/3*3, row/3*3+1, row/3*3+2
        for (int i=row/3*3; i<(row+3)/3*3; i++) {
            for (int j=col/3*3; j<(col+3)/3*3; j++) {
                if (i==row && j==col) continue;
                else if (buttons[i][j].getValue()==buttons[row][col].getValue()) {
                    buttons[i][j].wrong();
                    conflict++;
                }
            }
        }

        if (conflict==0) unsetConflict();
        else {
            buttons[row][col].wrong();
            Toast.makeText(this, String.valueOf(conflict), Toast.LENGTH_SHORT).show();
        }
    }

    /*public void setMemo(View v) {
            if (true) {
                v.setVisibility(VISIBLE);
            }

    }

    public void onToggleClicked(View v) {
        boolean on =((ToggleButton)v).isChecked();
        if (on) {

        }
    }*/

    public void onToggleRestart() {
        ToggleButton switches=(ToggleButton) Memo.findViewById(R.id.toggleButton);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton2);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton3);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton4);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton5);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton6);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton7);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton8);
        switches.setChecked(false);
        switches=(ToggleButton) Memo.findViewById(R.id.toggleButton9);
        switches.setChecked(false);

    }

    public void onClickNum1(View v) {
        buttons[row][col].set(1);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum2(View v) {
        buttons[row][col].set(2);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum3(View v) {
        buttons[row][col].set(3);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum4(View v) {
        buttons[row][col].set(4);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum5(View v) {
        buttons[row][col].set(5);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum6(View v) {
        buttons[row][col].set(6);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum7(View v) {
        buttons[row][col].set(7);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum8(View v) {
        buttons[row][col].set(8);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickNum9(View v) {
        buttons[row][col].set(9);
        pad.setVisibility(View.INVISIBLE);
        setConflict();
    }
    public void onClickCan(View v) {
        pad.setVisibility(View.INVISIBLE);
    }
    public void onClickNull(View v) {
        buttons[row][col].set(0);
        pad.setVisibility(View.INVISIBLE);
        unsetConflict();
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
            ir.setClass(MainActivity_sudoku.this, MainActivity.class);
            startActivity(ir);
        }


        return super.onOptionsItemSelected(item);
    }
    //public void onClick
}