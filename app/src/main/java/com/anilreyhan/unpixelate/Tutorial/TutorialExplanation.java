package com.anilreyhan.unpixelate.Tutorial;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import com.anilreyhan.unpixelate.R;

public class TutorialExplanation extends Activity {
    Button button;
    TextView textView;

    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_explanation);
        button = (Button) findViewById(R.id.tutorialExplanationButton);
        textView = (TextView) findViewById(R.id.tutorialText);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .7), (int) (height * .5));
        //getActionBar().setTitle(R.string.settingsPopUp);
        //getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions

        ActionBar bar = getActionBar();
       // bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(102, 102, 102)));


    }
}
