
package com.anilreyhan.unpixelate.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anilreyhan.unpixelate.R;
import com.anilreyhan.unpixelate.etc.Settings;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class GameScreen extends Activity {

    public ImageButton blueButton, greenButton, cyanButton, redButton, yellowButton;
    TextView movesLeft;
    int counter = 20;
    int lastColor;
    boolean vibratePref;
    public GameView gameView;
    public InterstitialAd mInterstitialAd;
    Settings settings;
    int movesCounter;
    int score, highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Intent goToLostPopUp = new Intent(getApplicationContext(), LostPopUp.class);

        settings = new Settings();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4253179676056693/9020454337");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                onBackPressed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        gameView = (GameView) findViewById(R.id.gameLayout);

        gameView.setActivity(this);

        movesCounter = preferences.getInt("movesLeft", 0);

        blueButton = (ImageButton) findViewById(R.id.blueButton);
        greenButton = (ImageButton) findViewById(R.id.greenButton);
        cyanButton = (ImageButton) findViewById(R.id.cyanButton);
        redButton = (ImageButton) findViewById(R.id.redButton);
        yellowButton = (ImageButton) findViewById(R.id.yellowButton);
        movesLeft = (TextView) findViewById(R.id.movesLeft);


        vibratePref = preferences.getBoolean("vibrate", true);

        if (preferences.getBoolean("freshStart", false)) {

            switch (preferences.getInt("difficulty", 2)) {
                case 1:
                    counter = 10;
                    break;
                case 2:
                    counter = 16;
                    break;
                case 3:
                    counter = 24;
                    break;
            }

        } else {
            switch (preferences.getInt("difficulty", 2)) {
                case 1:
                    counter = movesCounter + (preferences.getInt("progress", 10) * (2));
                    break;
                case 2:
                    counter = movesCounter + (int) (preferences.getInt("progress", 10) * (1.8));
                    break;
                case 3:
                    counter = movesCounter + (int) (preferences.getInt("progress", 10) * (1.6));
                    break;
            }
        }


        movesLeft.setText(getString(R.string.numberOfMoves, counter));

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(GameScreen.this, R.string.youLostToast, Toast.LENGTH_LONG).show();
                    settings.deleteProgress(preferences);
                    preferences.edit().putBoolean("freshStart", true).apply();
                    preferences.edit().putInt("movesLeft", 0).apply();
                    startActivity(goToLostPopUp);
                    //mInterstitialAd.show();
                } else {
                    if (lastColor == 1) {
                        Toast.makeText(GameScreen.this, getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(GameScreen.this, R.string.youLostToast, Toast.LENGTH_LONG).show();
                    settings.deleteProgress(preferences);
                    preferences.edit().putBoolean("freshStart", true).apply();
                    preferences.edit().putInt("movesLeft", 0).apply();
                    startActivity(goToLostPopUp);
                    //mInterstitialAd.show();
                } else {
                    if (lastColor == 2) {
                        Toast.makeText(GameScreen.this, getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            greenButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
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
                    Toast.makeText(GameScreen.this, R.string.youLostToast, Toast.LENGTH_LONG).show();
                    settings.deleteProgress(preferences);
                    preferences.edit().putBoolean("freshStart", true).apply();
                    preferences.edit().putInt("movesLeft", 0).apply();
                    startActivity(goToLostPopUp);
                    //mInterstitialAd.show();
                } else {
                    if (lastColor == 3) {
                        Toast.makeText(GameScreen.this, getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            cyanButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
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
                    Toast.makeText(GameScreen.this, R.string.youLostToast, Toast.LENGTH_LONG).show();
                    settings.deleteProgress(preferences);
                    preferences.edit().putBoolean("freshStart", true).apply();
                    preferences.edit().putInt("movesLeft", 0).apply();
                    startActivity(goToLostPopUp);
                    //mInterstitialAd.show();

                } else {
                    if (lastColor == 4) {
                        Toast.makeText(GameScreen.this, getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            redButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
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
                    Toast.makeText(GameScreen.this, R.string.youLostToast, Toast.LENGTH_LONG).show();
                    settings.deleteProgress(preferences);
                    preferences.edit().putBoolean("freshStart", true).apply();
                    preferences.edit().putInt("movesLeft", 0).apply();
                    startActivity(goToLostPopUp);
                    //mInterstitialAd.show();


                } else {
                    if (lastColor == 5) {
                        Toast.makeText(GameScreen.this, getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            yellowButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
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

    public void gameFinished(SharedPreferences preferences) {
        preferences.edit().putInt("movesLeft", (counter - 1)).apply();
        yellowButton.setEnabled(false);
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(false);
        Intent i = new Intent(getApplicationContext(), WinScreen.class);
        startActivity(i);
        onBackPressed();
    }


    public Context getContext() {
        return getApplicationContext();
    }
}
