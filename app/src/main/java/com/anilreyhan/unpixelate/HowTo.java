package com.anilreyhan.unpixelate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HowTo extends Activity {

    Button startButton, mufeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        startButton = (Button) findViewById(R.id.startButtonHowTo);
        mufeButton = (Button)findViewById(R.id.mufeButton);
        mufeButton.setBackgroundColor(Color.TRANSPARENT);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameScreen.class);
                startActivity(i);
                finish();
            }
        });

        mufeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"#mufepolaristiyor",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}