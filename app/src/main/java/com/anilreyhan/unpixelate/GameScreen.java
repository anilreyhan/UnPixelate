
package com.anilreyhan.unpixelate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anilreyhan.unpixelate.New.GameView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class GameScreen extends Activity {

    ImageButton blueButton, greenButton, cyanButton, redButton, yellowButton;
    TextView movesLeft;
    int counter = 20;
    ImageAdapter imageAdapter;
    public ArrayList<Integer> matchedBoxes = new ArrayList<>(100);
    int lastColor;
    Vibrator vibrator;
    boolean vibratePref;
    public static int selected = 0;
    static boolean[][] checkTable;
    Preferences preferences;
    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        final Intent lostScreen = new Intent(getApplicationContext(), GameOverLose.class);
        preferences = new Preferences();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        gameView = (GameView) findViewById(R.id.gameLayout);

        vibratePref = preferences.vibration;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(getApplicationContext(), "W: " + width + " -- H: " + height, Toast.LENGTH_SHORT).show();

        imageAdapter = new ImageAdapter(this, width, height);

        //GridView gridview = (GridView) findViewById(R.id.gameLayout);
        // gridview.setAdapter(imageAdapter);

        int[][] numberList;
        numberList = turnMulti();

        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        cyanButton = (ImageButton) findViewById(R.id.cyanButton);
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        movesLeft = (TextView) findViewById(R.id.movesLeft);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        matchedBoxes.clear();
        //initializeLastColor();
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    //Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                    startActivity(lostScreen);
                } else {
                    if (lastColor == 1) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onBlueClicked();
                        //gameView.deneme();
                    }
                }
                checkWin();
                lastColor = 1;
            }
        });


        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkNumberOfMoves()) {
                    //Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                    startActivity(lostScreen);
                } else {
                    if (lastColor == 2) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onGreenClicked();
                        //gameView.deneme();
                    }
                }
                checkWin();
                lastColor = 2;
            }
        });


        cyanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    //Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                    startActivity(lostScreen);
                } else {
                    if (lastColor == 3) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onCyanClicked();
                    }
                }
                checkWin();
                lastColor = 3;
            }
        });


        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    //Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                    startActivity(lostScreen);
                } else {
                    if (lastColor == 4) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onRedClicked();
                    }
                }
                checkWin();
                lastColor = 4;
            }
        });


        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    //Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                    startActivity(lostScreen);
                } else {
                    if (lastColor == 5) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onYellowClicked();
                    }
                }
                checkWin();
                lastColor = 5;
            }
        });


    }


    public boolean checkNumberOfMoves() {
        //return false;
        return counter <= 0;
    }




    public void updateView() {
        imageAdapter.notifyDataSetChanged();
        Log.i("unPixelate", String.valueOf(matchedBoxes));
    }

    public void initializeLastColor() {
        if (gameView.boxes.get(0).color == R.color.blue) {
            lastColor = 1;
        } else if (gameView.boxes.get(0).color == R.color.green) {
            lastColor = 2;
        } else if (gameView.boxes.get(0).color == R.color.cyan) {
            lastColor = 3;
        } else if (gameView.boxes.get(0).color == R.color.red) {
            lastColor = 4;
        } else if (gameView.boxes.get(0).color == R.color.yellow) {
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


    public void checkWin() {
        if (checkWin_()) {
            Intent i = new Intent(getApplicationContext(), GameOverWin.class);
            startActivity(i);
        }
    }

    public boolean checkWin_() {
        int a = 0;
        for (int i = 0; i < imageAdapter.mThumbIds.length; i++) {
            if (imageAdapter.mThumbIds[i].equals(imageAdapter.mThumbIds[0])) {
                a++;
            }
        }
        return a == 100;

    }

    public int[][] turnMulti() {
        int count = 0;
        int[][] multiArray = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                multiArray[i][j] = imageAdapter.mThumbIds[count];
                count++;
            }
        }
        return multiArray;
    }

    public void turnOne(int[][] multiArray) {
        int count = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                imageAdapter.mThumbIds[count] = multiArray[i][j];
                count++;
            }
        }

    }


}
