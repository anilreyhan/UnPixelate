package com.anilreyhan.unpixelate;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {


    ImageView img;
    MediaPlayer mp;
    ConstraintLayout aboutScreenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mp = MediaPlayer.create(this, R.raw.intro);


        img = (ImageView) findViewById(R.id.imageView);
        aboutScreenLayout = (ConstraintLayout) findViewById(R.id.aboutScreenLayout);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.index_enterance);
        animation.reset();

        img.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                img.setVisibility(View.INVISIBLE);

                mp.release();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aboutScreenLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                animation.cancel();
                return false;
            }
        });

    }

}
