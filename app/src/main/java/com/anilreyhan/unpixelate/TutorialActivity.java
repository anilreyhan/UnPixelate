
package com.anilreyhan.unpixelate;

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

import com.anilreyhan.unpixelate.New.TutorialView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class TutorialActivity extends Activity {

    public ImageButton blueButton, greenButton, cyanButton, redButton, yellowButton;
    TextView movesLeft;
    int counter = 20;
    int lastColor;
    boolean vibratePref;
    public TutorialView tutorialView;
    public InterstitialAd mInterstitialAd;
    Settings settings;
    static int stepCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        blueButton = (ImageButton) findViewById(R.id.blueButtonTutorial);
        greenButton = (ImageButton) findViewById(R.id.greenButtonTutorial);
        cyanButton = (ImageButton) findViewById(R.id.cyanButtonTutorial);
        redButton = (ImageButton) findViewById(R.id.redButtonTutorial);
        yellowButton = (ImageButton) findViewById(R.id.yellowButtonTutorial);
        movesLeft = (TextView) findViewById(R.id.movesLeftTutorial);

        lastColor = 4;
        //updateButtons();
        setGreenAvailable();

        settings = new Settings();
        AdView mAdView = (AdView) findViewById(R.id.adViewTutorial);
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

        tutorialView = (TutorialView) findViewById(R.id.gameLayoutTutorial);

        tutorialView.setActivity(this);



        vibratePref = preferences.getBoolean("vibrate", true);


        movesLeft.setText(getString(R.string.numberOfMoves, counter));

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(getApplicationContext(), R.string.youLostToast, Toast.LENGTH_LONG).show();
                    mInterstitialAd.show();
                } else {
                    if (lastColor == 1) {
                        Toast.makeText(getApplicationContext(), getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            blueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }
                        if (tutorialView.onBlueClicked()) {
                            counter--;
                            stepCounter++;
                            movesLeft.setText(getString(R.string.numberOfMoves, counter));
                            updateButtons();
                        }
                    }
                }
                lastColor = 1;
            }
        });


        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkNumberOfMoves()) {
                    Toast.makeText(getApplicationContext(), R.string.youLostToast, Toast.LENGTH_LONG).show();
                    mInterstitialAd.show();
                } else {
                    if (lastColor == 2) {
                        Toast.makeText(getApplicationContext(), getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            greenButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }
                        if (tutorialView.onGreenClicked()) {
                            counter--;
                            stepCounter++;
                            movesLeft.setText(getString(R.string.numberOfMoves, counter));
                            updateButtons();
                        }
                    }
                }
                lastColor = 2;
            }
        });


        cyanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(getApplicationContext(), R.string.youLostToast, Toast.LENGTH_LONG).show();
                    mInterstitialAd.show();
                } else {
                    if (lastColor == 3) {
                        Toast.makeText(getApplicationContext(), getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            cyanButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }
                        if (tutorialView.onCyanClicked()) {
                            counter--;
                            stepCounter++;
                            movesLeft.setText(getString(R.string.numberOfMoves, counter));
                            updateButtons();
                        }
                    }
                }

                lastColor = 3;

            }
        });


        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(getApplicationContext(), R.string.youLostToast, Toast.LENGTH_LONG).show();
                    mInterstitialAd.show();

                } else {
                    if (lastColor == 4) {
                        Toast.makeText(getApplicationContext(), getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            redButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }
                        if (tutorialView.onRedClicked()) {
                            counter--;
                            stepCounter++;
                            movesLeft.setText(getString(R.string.numberOfMoves, counter));
                            updateButtons();
                        }
                    }
                }

                lastColor = 4;

            }
        });


        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNumberOfMoves()) {
                    Toast.makeText(getApplicationContext(), R.string.youLostToast, Toast.LENGTH_LONG).show();
                    mInterstitialAd.show();


                } else {
                    if (lastColor == 5) {
                        Toast.makeText(getApplicationContext(), getString(R.string.wasteMoves), Toast.LENGTH_SHORT).show();
                    } else {
                        if (vibratePref) {
                            yellowButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        }

                        if (tutorialView.onYellowClicked()) {
                            counter--;
                            stepCounter++;
                            movesLeft.setText(getString(R.string.numberOfMoves, counter));
                            updateButtons();
                        }
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

    public void gameFinished() {
        yellowButton.setEnabled(false);
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(false);
        finish();
        Intent i = new Intent(getApplicationContext(), WinScreenTutorial.class);
        startActivity(i);
        onBackPressed();
    }


    public Context getContext() {
        return getApplicationContext();
    }

    public void setBlueAvailable() {
        yellowButton.setEnabled(false);
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(true);
    }

    public void setCyanAvailable() {
        yellowButton.setEnabled(false);
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(true);
        blueButton.setEnabled(false);
    }

    public void setGreenAvailable() {
        yellowButton.setEnabled(false);
        redButton.setEnabled(false);
        greenButton.setEnabled(true);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(false);
    }

    public void setRedAvailable() {
        yellowButton.setEnabled(false);
        redButton.setEnabled(true);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(false);
    }

    public void setYellowAvailable() {
        yellowButton.setEnabled(true);
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        cyanButton.setEnabled(false);
        blueButton.setEnabled(false);
    }

    public void updateButtons() {

        switch (stepCounter) {
            case 0:
                setGreenAvailable();
                break;
            case 1:
                setRedAvailable();
                break;
            case 2:
                setYellowAvailable();
                break;
            case 3:
                setGreenAvailable();
                break;
            case 4:
                setYellowAvailable();
                break;
            case 5:
                setCyanAvailable();
                break;
            case 6:
                setRedAvailable();
                break;
            case 7:
                setGreenAvailable();
                break;
            case 8:
                setCyanAvailable();
                break;
            case 9:
                setYellowAvailable();
                break;
            case 10:
                setBlueAvailable();
                break;
            case 11:
                setCyanAvailable();
                break;
            case 12:
                setYellowAvailable();
                break;
            case 13:
                setRedAvailable();
                break;
            case 14:
                setGreenAvailable();
                break;
            case 15:
                setBlueAvailable();
                break;
            case 16:
                setRedAvailable();
                break;

        }

    }
}
