package com.anilreyhan.unpixelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;

public class Settings extends AppCompatActivity {
    CheckBox soundCB, vibrationCB;
    GameScreen gameScreen;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundCB = (CheckBox) findViewById(R.id.soundCheckBox);
        vibrationCB = (CheckBox) findViewById(R.id.vibrationCheckBox);

        gameScreen = new GameScreen();
        preferences = new Preferences();



        vibrationCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.vibration = vibrationCB.isChecked();

            }
        });

        soundCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.sound = soundCB.isChecked();

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
