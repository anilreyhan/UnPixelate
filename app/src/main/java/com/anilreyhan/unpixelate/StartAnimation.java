package com.anilreyhan.unpixelate;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Handler;

public class StartAnimation extends Activity {

    Intent intent;
    ImageView img;
    MediaPlayer mp;
    Thread thread;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        img = (ImageView) findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(StartAnimation.this, MainActivity.class);
                StartAnimation.this.startActivity(mainIntent);
                StartAnimation.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
