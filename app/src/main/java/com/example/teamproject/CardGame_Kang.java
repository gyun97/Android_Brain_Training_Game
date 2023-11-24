package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardGame_Kang extends AppCompatActivity {
    // Images for our cards
    Integer[] images = {
            R.drawable.card_1,
            R.drawable.card_1,
            R.drawable.card_2,
            R.drawable.card_2,
            R.drawable.card_3,
            R.drawable.card_3,
            R.drawable.card_4,
            R.drawable.card_4,
            R.drawable.card_5,
            R.drawable.card_5,
            R.drawable.card_6,
            R.drawable.card_6
    };
    // Array to keep track of the flipped cards
    ImageView[] cards = new ImageView[12];
    int[] cardStatus = new int[12];
    int cardIndex = -1;
    private boolean[] cardFlipped = new boolean[12];
    private Button restartButton;
    private long gameStartTime, gameEndTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);
        Collections.shuffle(Arrays.asList(images));
        List<Integer> imagesList = Arrays.asList(images);
        Collections.shuffle(imagesList);
        imagesList.toArray(images);
        Arrays.fill(cardFlipped, false);

        for (int i = 0; i < 12; i++) {
            String cardId = "card" + (i + 1);
            int resId = getResources().getIdentifier(cardId, "id", getPackageName());
            cards[i] = findViewById(resId);
            final int finalI = i;
            cards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flipCard(finalI);
                }
            });
            restartButton = findViewById(R.id.restartButton);
            restartButton.setVisibility(View.GONE);
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartGame();
                }
            });
        }
        showAllCardsTemporarily();

    }



    private void flipCard(int i) {
        if (cardIndex < 0) {
            // No card has been flipped over yet
            cardIndex = i;
            cards[i].setImageResource(images[i]);
            cardFlipped[i] = true;
        } else if (cardIndex != i && !cardFlipped[i]) {
            // A different card has been flipped over already
            cards[i].setImageResource(images[i]);
            cardFlipped[i] = true;

            if (!images[cardIndex].equals(images[i])) {
                // The second card does not match the first card
                final int finalCardIndex = cardIndex;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Flip both cards back over
                        cards[i].setImageResource(R.drawable.card_back);
                        cards[finalCardIndex].setImageResource(R.drawable.card_back);
                        cardFlipped[finalCardIndex] = false;
                        cardFlipped[i] = false;
                    }
                }, 1000);  // Flip back over after 1 second
            }
            cardIndex = -1;
        }
        if (allCardsFlipped()) {
            gameEndTime = System.currentTimeMillis();
            long timeTaken = gameEndTime - gameStartTime;  // Time taken in milliseconds
            // Convert to seconds
            double timeTakenSeconds = timeTaken / 1000.0;
            Toast.makeText(CardGame_Kang.this, "Time taken: " + timeTakenSeconds + " seconds", Toast.LENGTH_SHORT).show();
        }

    }

    private void showAllCardsTemporarily() {
        for (int i = 0; i < 12; i++) {
            cards[i].setImageResource(images[i]);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    cards[i].setImageResource(R.drawable.card_back);
                }
                gameStartTime = System.currentTimeMillis();  // Game starts now
            }
        }, 3000);  // 3 seconds
    }


    private void restartGame() {
        // Shuffle the images
        List<Integer> imagesList = Arrays.asList(images);
        Collections.shuffle(imagesList);
        imagesList.toArray(images);

        // Reset the status of the cards
        Arrays.fill(cardFlipped, false);

        // Reset the images of the cards
        for (int i = 0; i < 12; i++) {
            cards[i].setImageResource(R.drawable.card_back);
        }
        restartButton.setVisibility(View.GONE);

        // Show all cards temporarily
        showAllCardsTemporarily();
    }
    private boolean allCardsFlipped() {
        for (boolean flipped : cardFlipped) {
            if (!flipped) {
                return false;
            }
        }
        restartButton.setVisibility(View.VISIBLE); // 게임 클리어 시 버튼 보이기
        return true;
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
            ir.setClass(CardGame_Kang.this, MainActivity.class);
            startActivity(ir);
        }


        return super.onOptionsItemSelected(item);
    }
}