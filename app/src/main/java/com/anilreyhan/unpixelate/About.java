package com.anilreyhan.unpixelate;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView textView;
    ImageView img;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mp = MediaPlayer.create(this, R.raw.intro);


        textView = (TextView) findViewById(R.id.aboutScreenText);
        img = (ImageView) findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.index_enterance);
        animation.reset();

        img.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //mp.start();
                textView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                img.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);

                mp.release();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
