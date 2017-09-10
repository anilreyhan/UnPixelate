package com.anilreyhan.unpixelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {
    CheckBox soundCB, vibrationCB;
    GameScreen gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundCB = (CheckBox) findViewById(R.id.soundCheckBox);
        vibrationCB = (CheckBox) findViewById(R.id.vibrationCheckBox);

        gameScreen = new GameScreen();



        vibrationCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameScreen.vibratePref = vibrationCB.isChecked();

            }
        });

        soundCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           // finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
