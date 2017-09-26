package com.anilreyhan.unpixelate;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.VideoView;

public class GameOverLose extends Activity {

    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_lose);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int Width = dm.widthPixels;
        int height = dm.heightPixels;

       // getWindow().setLayout((int) (Width * .8), (int) (height * .5));

        videoView = (VideoView)findViewById(R.id.youLoseView);





        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.you_lost);

        videoView.setVideoURI(uri);
        videoView.start();



        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });


    }



}
