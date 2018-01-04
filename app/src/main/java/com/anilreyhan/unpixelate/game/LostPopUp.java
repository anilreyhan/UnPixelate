package com.anilreyhan.unpixelate.game;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.anilreyhan.unpixelate.R;

public class LostPopUp extends Activity {
    WebView gifView;
    Button asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pop_up);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .85), (int) (height * .65));
        if (getActionBar() != null) {
            getActionBar().setTitle("Sorry, you lost! :(");
        }

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(102, 102, 102)));

        gifView = (WebView) findViewById(R.id.lostView);

        gifView.loadUrl(randomGif());
        gifView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


    }

    public String randomGif() {
        String anim1 = ("file:///android_asset/" + "lost_gif_1.html");
        String anim2 = ("file:///android_asset/" + "lost_gif_2.html");
        String anim3 = ("file:///android_asset/" + "lost_gif_3.html");
        String anim4 = ("file:///android_asset/" + "lost_gif_4.html");

        String anim = anim1;
        int a;
        do {
            a = (int) (Math.random() * 5);
        }
        while (a <= 0 || a > 5);


        if (a == 1) {
            anim = anim2;
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
