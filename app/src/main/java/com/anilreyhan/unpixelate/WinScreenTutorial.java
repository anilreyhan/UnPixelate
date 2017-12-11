package com.anilreyhan.unpixelate;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class WinScreenTutorial extends AppCompatActivity {

    Button nextLevel, quit;
    VideoView videoView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen_tutorial);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        nextLevel = (Button) findViewById(R.id.nextButtonTutorial);
        quit = (Button) findViewById(R.id.exitButtonTutorial);
        videoView = (VideoView) findViewById(R.id.videoViewTutorial);
        textView = (TextView) findViewById(R.id.tutorialCongratsView);

        videoView.setVideoURI(randomGif());
        videoView.start();


        textView.setText(R.string.tutorialCongrats);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(0, 0);

            }
        });

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameScreen.class);
                onBackPressed();
                startActivity(i);
                videoView.suspend();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                onBackPressed();
                startActivity(i);
                videoView.suspend();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public Uri randomGif() {
        Uri anim1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win_anim_1);
        Uri anim2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win_anim_2);
        Uri anim3 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win_anim_3);
        Uri anim4 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win_anim_4);

        Uri anim = anim1;
        int a;
        do {
            a = (int) (Math.random() * 5);
        }
        while (a <= 0 || a > 5);


        if (a == 1) {
            anim = anim1;
        }
        if (a == 2) {
            anim = anim2;
        }
        if (a == 3) {
            anim = anim3;
        }
        if (a == 4) {
            anim = anim4;
        }
        return anim;
    }

    public Context getContext() {
        return getApplicationContext();
    }





}
