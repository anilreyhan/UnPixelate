package com.anilreyhan.unpixelate.Etc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.anilreyhan.unpixelate.MainActivity;
import com.anilreyhan.unpixelate.R;

public class StartAnimation extends Activity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);


        img = (ImageView) findViewById(R.id.startAnimImg);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        int SPLASH_DISPLAY_LENGTH = 1000;
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
