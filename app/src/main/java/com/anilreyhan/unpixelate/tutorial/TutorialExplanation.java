package com.anilreyhan.unpixelate.tutorial;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.anilreyhan.unpixelate.R;

public class TutorialExplanation extends Activity {
    Button button;
    TextView textView;
    CheckBox checkBox;
    SharedPreferences preferences;

    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor;

        setContentView(R.layout.activity_tutorial_explanation);
        button = (Button) findViewById(R.id.tutorialExplanationButton);
        textView = (TextView) findViewById(R.id.tutorialText);
        checkBox = (CheckBox) findViewById(R.id.tutorialExplanationCB);

        if (preferences.getBoolean("dontShowTutorialExplanation", false)) {
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .7), (int) (height * .5));





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    preferences.edit().putBoolean("dontShowTutorialExplanation", true).apply();
                } else {
                    preferences.edit().putBoolean("dontShowTutorialExplanation", false).apply();
                }
            }
        });


    }
}
