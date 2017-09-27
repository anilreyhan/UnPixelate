
package com.anilreyhan.unpixelate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anilreyhan.unpixelate.New.GameView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class GameScreen extends Activity {

    ImageButton blueButton, greenButton, cyanButton, redButton, yellowButton;
    TextView movesLeft;
    int counter = 20;
    public ArrayList<Integer> matchedBoxes = new ArrayList<>(100);
    int lastColor;
    Vibrator vibrator;
    boolean vibratePref;
    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        gameView = (GameView) findViewById(R.id.gameLayout);



        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        cyanButton = (ImageButton) findViewById(R.id.cyanButton);
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        movesLeft = (TextView) findViewById(R.id.movesLeft);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        matchedBoxes.clear();
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 1) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onBlueClicked();
                        counter--;
                        movesLeft.setText(getString(R.string.numberOfMoves, counter));
                    }
                }

                lastColor = 1;

            }
        });


        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkNumberOfMoves()) {
                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();

                } else {
                    if (lastColor == 2) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onGreenClicked();
                        counter--;
                        movesLeft.setText(getString(R.string.numberOfMoves, counter));
                    }
                }
                lastColor = 2;
            }
        });


        cyanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();

                } else {
                    if (lastColor == 3) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onCyanClicked();
                        counter--;
                        movesLeft.setText(getString(R.string.numberOfMoves, counter));
                    }
                }

                lastColor = 3;
            }
        });


        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 4) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onRedClicked();
                        counter--;
                        movesLeft.setText(getString(R.string.numberOfMoves, counter));
                    }
                }

                lastColor = 4;

            }
        });


        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {

                    Toast.makeText(GameScreen.this, "No more moves left!", Toast.LENGTH_SHORT).show();
                } else {
                    if (lastColor == 5) {
                        Toast.makeText(GameScreen.this, "Why are you wasting your moves?", Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        gameView.onYellowClicked();
                        counter--;

                        movesLeft.setText(getString(R.string.numberOfMoves, counter));
                    }
                }
                lastColor = 5;

            }
        });


    }


    public boolean checkNumberOfMoves() {
        //return false;
        return counter <= 0;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
