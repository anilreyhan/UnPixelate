package com.anilreyhan.unpixelate;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class About extends AppCompatActivity {

    Intent intent;
    ImageView img;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mp=MediaPlayer.create(this,R.raw.intro);

        intent = new Intent(getApplicationContext(), MainActivity.class);

        img=(ImageView)findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.index_enterance);
        animation.reset();

        img.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //mp.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {



                        startActivity(intent);
                        finish();
                        mp.release();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
