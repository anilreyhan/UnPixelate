
package com.anilreyhan.unpixelate;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        preferences = new Preferences();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        vibratePref = preferences.vibration;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(getApplicationContext(), "W: " + width + " -- H: " + height, Toast.LENGTH_SHORT).show();

        imageAdapter = new ImageAdapter(this, width, height);

        GridView gridview = (GridView) findViewById(R.id.gameLayout);
        gridview.setAdapter(imageAdapter);

        int[][] numberList;
        numberList = turnMulti();
        checkTable = checkColors(numberList);

        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        cyanButton = (ImageButton) findViewById(R.id.cyanButton);
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        movesLeft = (TextView) findViewById(R.id.movesLeft);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


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

                       /* checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }*/

                        int[][] numberList;
                        numberList = turnMulti();
                        numberList = changeIt(R.color.blue, numberList, checkTable);
                        checkTable = checkColors(numberList);
                        turnOne(numberList);

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
                checkWin();
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
                       /* checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }*/

                        int[][] numberList;
                        numberList = turnMulti();
                        numberList = changeIt(R.color.green, numberList, checkTable);
                        checkTable = checkColors(numberList);
                        turnOne(numberList);

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
                checkWin();
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
                        /* checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }*/


                        int[][] numberList;
                        numberList = turnMulti();
                        numberList = changeIt(R.color.cyan, numberList, checkTable);
                        checkTable = checkColors(numberList);
                        turnOne(numberList);

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
                checkWin();
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
                        /* checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }*/
                        int[][] numberList;
                        numberList = turnMulti();
                        numberList = changeIt(R.color.red, numberList, checkTable);
                        checkTable = checkColors(numberList);
                        turnOne(numberList);

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
                checkWin();
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
                        /* checkColorsHorizontally(0);

                        for (int i = 0; i < 10; i++) {
                            checkColorsVertically(i);
                        }*/
                        int[][] numberList;
                        numberList = turnMulti();
                        numberList = changeIt(R.color.yellow, numberList, checkTable);
                        checkTable = checkColors(numberList);
                        turnOne(numberList);

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
                checkWin();
            }
        });


    }


    public boolean checkNumberOfMoves() {
        return false;
        //  return counter <= 0;
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


    public boolean[][] checkColors(int[][] numbers) {
        boolean[][] checkingNumbers = new boolean[10][10];
        numbers = turnMulti();
        selected = numbers[0][0];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                checkingNumbers[i][j] = false;
            }
        }

        selected = numbers[0][0];
        int endingNum = 0;
        for (int i = 0; i < 10; i++) {
            if (numbers[0][i] == selected) {
                endingNum = i + 1;
            } else
                break;

        }

        for (int i = 0; i < endingNum; i++) {
            for (int j = 0; j < 10; j++) {
                if (numbers[j][i] == selected) {
                    checkingNumbers[j][i] = true;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (checkingNumbers[i][j]) {
                    if (j != 9) {
                        if (numbers[i][j + 1] == selected) {
                            checkingNumbers[i][j + 1] = true;
                        }
                    }
                    if (j != 0) {
                        if (numbers[i][j - 1] == selected) {
                            checkingNumbers[i][j - 1] = true;
                        }
                    }
                    if (i != 0) {
                        if (numbers[i - 1][j] == selected) {
                            checkingNumbers[i - 1][j] = true;
                        }
                    }
                    if (i != 9) {
                        if (numbers[i + 1][j] == selected) {
                            checkingNumbers[i + 1][j] = true;
                        }
                    }
                }
            }
        }
        return checkingNumbers;
    }

    public static int[][] changeIt(int x, int[][] numbers, boolean[][] checkList) {



        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (checkList[i][j]) {
                    numbers[i][j] = x;
                }
            }
        }
        return numbers;
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


    public void checkWin() {
        if (checkWin_()) {
            Toast.makeText(getApplicationContext(), "You Win!", Toast.LENGTH_SHORT).show();
            onBackPressed();
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
