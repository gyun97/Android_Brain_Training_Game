package com.example.teamproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class AnimalGame_DoGyun extends View {

    private static final int BLANK = 0; // 상태값 (대기상태)
    private static final int PLAY = 1; //게임이 진행중
    private static final int DELAY = 1500; // 이미지 생성 시간 (1500 = 1.5초)

    private int level = 1; // 현재 레벨을 저장하는 변수

    private int imageSize = 90; // 이미지의 고정 크기

    private int status;

    private ArrayList<Shape> arShape = new ArrayList<Shape>();
    private Random rnd = new Random(); //난수값을 구하는 클래스
    private Activity mParent;

    public AnimalGame_DoGyun(Context context) {
        super(context);
        mParent = (Activity) context;
        status = BLANK;

        //핸들러 실행
        handler.sendEmptyMessageDelayed(0, DELAY);
    }

    class Shape {
        static final int CAT = R.drawable.cat;
        static final int CHICKEN = R.drawable.chicken;
        static final int COW = R.drawable.cow;
        static final int  DEER = R.drawable.deer;
        static final int DOG = R.drawable.dog;
        static final int DONKEY = R.drawable.donkey;
        static final int ELEPHAT = R.drawable.elephat;
        static final int FOW = R.drawable.fox;
        static final int JAGUAR = R.drawable.jaguar;
        static final int LEOPARD = R.drawable.leopard;

        static final int LION = R.drawable.lion;
        static final int MONKEY = R.drawable.monkey;
        static final int OWL = R.drawable.owl;
        static final int  PANDA = R.drawable.panda;
        static final int PENGUIN = R.drawable.penguin;
        static final int PIG = R.drawable.pig;
        static final int RABBIT = R.drawable.rabbit;
        static final int SHEEP = R.drawable.sheep;
        static final int TIGER = R.drawable.tiger;
        static final int ZEBRA = R.drawable.zebra;


        int what;
        Rect rt;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        if (status == BLANK) {
            return;
        }

        for (int idx = 0; idx < arShape.size(); idx++) {
            Rect rt = arShape.get(idx).rt;
            int drawableResId = arShape.get(idx).what;

            Drawable drawable = getResources().getDrawable(drawableResId);
            drawable.setBounds(rt.left, rt.top, rt.right, rt.bottom);
            drawable.draw(canvas);
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            AddNewShape();
            status = PLAY;
            invalidate();

            String title = "Stage  " + arShape.size();
            mParent.setTitle(title);
        }
    };

    public void AddNewShape() {
        Shape shape = new Shape();
        int idx;

        boolean bFindIntersect;
        Rect rt = new Rect();

        while (true) {
            rt.left = rnd.nextInt(getWidth() - imageSize);
            rt.top = rnd.nextInt(getHeight() - imageSize);
            rt.right = rt.left + imageSize;
            rt.bottom = rt.top + imageSize;

            bFindIntersect = false;

            for (idx = 0; idx < arShape.size(); idx++) {
                if (rt.intersect(arShape.get(idx).rt)) {
                    bFindIntersect = true;
                }
            }

            if (!bFindIntersect) {
                break;
            }
        }

        shape.what = getRandomNumberImageResource();
        shape.rt = rt;
        arShape.add(shape);
    }



    private int getRandomNumberImageResource() {
        int[] numberImageResources = {
                Shape.CAT, Shape.CHICKEN, Shape.COW,
                Shape.DEER, Shape.DOG, Shape.DONKEY,
                Shape.ELEPHAT, Shape.FOW, Shape.JAGUAR, Shape.LEOPARD,

                Shape.LION, Shape.MONKEY, Shape.OWL,
                Shape.PANDA, Shape.PENGUIN, Shape.PIG,
                Shape.RABBIT, Shape.SHEEP, Shape.TIGER, Shape.ZEBRA};


        int randomIndex = rnd.nextInt(numberImageResources.length);
        return numberImageResources[randomIndex];
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int sel = findShapeIdx((int) event.getX(), (int) event.getY());

            if (sel == -1) {
                return true;
            }

            if (sel == arShape.size() - 1) {
                level++;

                if (level > 20) { // Stage 20을 통과한 경우
                    AlertDialog.Builder builder = new AlertDialog.Builder(mParent);
                    builder.setMessage("Mission Clear!");
                    builder.setTitle("Congratulations!");
                    builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 게임 종료 후 다른 액티비티로 전환
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getContext().startActivity(intent);
//                            mParent.finish(); // 게임 종료
                        }
                    });

                    builder.setCancelable(false);
                    builder.show();
                } else {
                    status = BLANK;
                    invalidate();
                    handler.sendEmptyMessageDelayed(0, DELAY);
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mParent);
                builder.setMessage("Restart Game?");
                builder.setTitle("Game Over!");
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arShape.clear();
                        status = BLANK;
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, DELAY);
                    }
                });

                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 게임 종료 후 다른 액티비티로 전환
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getContext().startActivity(intent);
//                        mParent.finish(); // 종료
                    }
                });

                builder.setCancelable(false);
                builder.show();

            }
        }
        return false;
    }



    public int findShapeIdx(int x,int y) {
        for (int idx = 0; idx < arShape.size(); idx++) {
            if (arShape.get(idx).rt.contains(x, y)) {
                return idx;
            }
        }
        return -1;
    }

}
