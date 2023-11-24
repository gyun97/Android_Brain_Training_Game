package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomButton extends FrameLayout {
    int row, col, value;
    TextView textView;
    //ConstraintLayout con2=(ConstraintLayout) findViewById(R.id.container2);
     //layoutInflater;
    View memo;
    //TableLayout memo;
    TextView[] memos=new TextView[9];


    public CustomButton(Context context, int row, int col) {
        super(context);
        this.row=row; this.col=col;
        textView=new TextView(context);
        LayoutInflater layoutInflater=
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //memo=(ViewGroup) layoutInflater.inflate(R.layout.memo_layout, con2,true);
        memo=layoutInflater.inflate(R.layout.memo_layout, null);

        int k=0;

        addView(textView);
        setClickable(true);
        setBackgroundResource(R.drawable.button_selector);
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(25);
        addView(memo);

        /*for(int i=0; i<3; i++) {
            TableRow tableRow=(TableRow) memo.getChildAt(i);
            for(int j=0; j<3; j++) {
                memos[k]=(TextView) tableRow.getChildAt(j);
                k++;
            }
        }*/
    }

    public void hidden() {
        textView.setText(" ");
    }

    public void set(int a) {
        if(a>0 && a<10) {
            value=a;
            textView.setText(String.valueOf(value));
        }
        else {
            value=0;
            hidden();
        }
    }

    /*public void setMemo() {
        for (int i=0; i<9; i++) {
            if (true) {
                memos[i].setVisibility(VISIBLE);
            }
        }
    }*/

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void wrong() {
        textView.setBackgroundColor(Color.RED);
    }

    public void correct() {
        textView.setBackgroundColor(Color.WHITE);
    }

}
