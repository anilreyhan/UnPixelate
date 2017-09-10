
package com.anilreyhan.unpixelate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class GameScreen extends AppCompatActivity {

    ImageButton backButton, blueButton, greenButton, cyanButton, redButton, yellowButton;
    TextView movesLeft;
    int counter = 20;
    ImageAdapter imageAdapter;
    public ArrayList<Integer> matchedBoxes = new ArrayList<>(100);
    int lastColor;
    Vibrator vibrator;
    boolean vibratePref = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(getApplicationContext(), "W: " + width + " -- H: " + height, Toast.LENGTH_SHORT).show();

        imageAdapter = new ImageAdapter(this, width, height);

        GridView gridview = (GridView) findViewById(R.id.gameLayout);
        gridview.setAdapter(imageAdapter);


        backButton = (ImageButton) findViewById(R.id.backButton);
        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        cyanButton = (ImageButton) findViewById(R.id.cyanButton);
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        movesLeft = (TextView) findViewById(R.id.movesLeft);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        matchedBoxes.clear();
        updateLastColor();
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 1) {
                    } else {
                        if (vibratePref) {
                            vibrator.vibrate(50);
                        }

                        checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }

                        counter--;
                        movesLeft.setText("Moves Left " + String.valueOf(counter));
                        imageAdapter.mThumbIds[0] = R.color.blue;


                        for (int i = 0; i < matchedBoxes.size(); i++) {

                            imageAdapter.mThumbIds[matchedBoxes.get(i)] = R.color.blue;

                        }


                        lastColor = 1;

                        updateView();
                    }
                }
            }
        });


        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkNumberOfMoves()) {
                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 2) {
                    } else {
                        if (vibratePref) {
                            vibrator.vibrate(50);
                        }
                        checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }
                        counter--;
                        movesLeft.setText("Moves Left " + String.valueOf(counter));
                        imageAdapter.mThumbIds[0] = R.color.green;

                        for (int i = 0; i < matchedBoxes.size(); i++) {

                            imageAdapter.mThumbIds[matchedBoxes.get(i)] = R.color.green;

                        }
                        lastColor = 2;

                        updateView();

                    }
                }
            }
        });


        cyanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 3) {
                    } else {
                        if (vibratePref) {
                            vibrator.vibrate(50);
                        }
                        checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }
                        counter--;
                        movesLeft.setText("Moves Left " + String.valueOf(counter));
                        imageAdapter.mThumbIds[0] = R.color.cyan;
                        for (int i = 0; i < matchedBoxes.size(); i++) {

                            imageAdapter.mThumbIds[matchedBoxes.get(i)] = R.color.cyan;

                        }
                        lastColor = 3;

                        updateView();
                    }
                }
            }
        });


        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 4) {
                    } else {
                        if (vibratePref) {
                            vibrator.vibrate(50);
                        }
                        checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }
                        counter--;
                        movesLeft.setText("Moves Left " + String.valueOf(counter));
                        imageAdapter.mThumbIds[0] = R.color.red;
                        for (int i = 0; i < matchedBoxes.size(); i++) {

                            imageAdapter.mThumbIds[matchedBoxes.get(i)] = R.color.red;

                        }
                        lastColor = 4;
                        updateView();
                    }
                }
            }
        });


        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 5) {
                    } else {
                        if (vibratePref) {
                            vibrator.vibrate(50);
                        }
                        checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }
                        counter--;
                        movesLeft.setText("Moves Left " + String.valueOf(counter));
                        imageAdapter.mThumbIds[0] = R.color.yellow;
                        for (int i = 0; i < matchedBoxes.size(); i++) {

                            imageAdapter.mThumbIds[matchedBoxes.get(i)] = R.color.yellow;

                        }
                        lastColor = 5;
                        updateView();
                    }
                }
            }
        });


    }


    public boolean checkNumberOfMoves() {
        return false;
        //   return counter <= 0;
    }

    public void checkColorsHorizontally(int box) {
        if (imageAdapter.mThumbIds[0].equals(imageAdapter.mThumbIds[box])) {
            matchedBoxes.add(box);
            box++;
            if (box >= 10) {
                return;
            }
            deleteRepeated();
            checkColorsHorizontally(box);
        }
    }

    public void checkColorsVertically(int box) {
        if (imageAdapter.mThumbIds[0].equals(imageAdapter.mThumbIds[box])) {
            matchedBoxes.add(box);
            box += 10;
            if (box / 10 >= 10) {
                return;
            }
            deleteRepeated();
            checkColorsVertically(box);
        }
    }


    public void checkColors(int box) {


    }

    public void updateView() {
        imageAdapter.notifyDataSetChanged();
        Log.i("unPixelate", String.valueOf(matchedBoxes));
    }

    public void updateLastColor() {
        if (imageAdapter.mThumbIds[0] == R.color.blue) {
            lastColor = 1;
        } else if (imageAdapter.mThumbIds[0] == R.color.green) {
            lastColor = 2;
        } else if (imageAdapter.mThumbIds[0] == R.color.cyan) {
            lastColor = 3;
        } else if (imageAdapter.mThumbIds[0] == R.color.red) {
            lastColor = 4;
        } else if (imageAdapter.mThumbIds[0] == R.color.yellow) {
            lastColor = 5;
        }
    }

    public void deleteRepeated() {
        Set<Integer> hs = new HashSet<>();
        hs.addAll(matchedBoxes);
        matchedBoxes.clear();
        matchedBoxes.addAll(hs);
        if (matchedBoxes.contains(100)) {
            matchedBoxes.remove(100);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
