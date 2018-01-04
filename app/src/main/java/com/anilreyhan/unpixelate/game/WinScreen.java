package com.anilreyhan.unpixelate.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.anilreyhan.unpixelate.MainActivity;
import com.anilreyhan.unpixelate.R;

public class WinScreen extends AppCompatActivity {

    Button nextLevel, quit;
    WebView gifView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        nextLevel = (Button) findViewById(R.id.nextButton);
        quit = (Button) findViewById(R.id.exitButton);
        gifView = (WebView) findViewById(R.id.videoView);
        textView = (TextView) findViewById(R.id.movesPassed);

        gifView.loadUrl(randomGif());
        gifView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        textView.setText(getString(R.string.movesPassed, preferences.getInt("movesLeft", 0)));


        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameScreen.class);
                onBackPressed();
                startActivity(i);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                onBackPressed();
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public String randomGif() {
        String anim1 = ("file:///android_asset/" + "win_gif_1.html");
        String anim2 = ("file:///android_asset/" + "win_gif_2.html");
        String anim3 = ("file:///android_asset/" + "win_gif_3.html");
        String anim4 = ("file:///android_asset/" + "win_gif_4.html");

        String anim = anim1;
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

}
