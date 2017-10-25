package com.anilreyhan.unpixelate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Activity implements AdapterView.OnItemSelectedListener {
    CheckBox vibrationCB;
    GameScreen gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final SharedPreferences.Editor editor = preferences.edit();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.3));
        getActionBar().setTitle(R.string.settingsPopUp);
        //getSupportActionBar().setTitle("Hello world App");  // provide compatibility to all the versions

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(102,102,102)));

        vibrationCB = (CheckBox) findViewById(R.id.vibrationCheckBox);

        gameScreen = new GameScreen();

        if (preferences.getBoolean("vibrate", true)) {
            vibrationCB.setChecked(true);
        } else {
            vibrationCB.setChecked(false);
        }


        vibrationCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vibrationCB.isChecked()) {
                    editor.putBoolean("vibrate", true);
                    editor.commit();

                } else {
                    editor.putBoolean("vibrate", false);
                    editor.commit();
                }


            }
        });

        if (preferences.getBoolean("vibrate", true)) {
            vibrationCB.setChecked(true);
        } else {
            vibrationCB.setChecked(false);
        }


        Spinner spinner = (Spinner) findViewById(R.id.difficultySpinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        switch (preferences.getInt("difficulty", 2)) {
            case 1:
                spinner.setSelection(0);
                //Toast.makeText(getApplicationContext()," set Easy",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                spinner.setSelection(1);

                //Toast.makeText(getApplicationContext()," set medium",Toast.LENGTH_SHORT).show();

                break;
            case 3:
                spinner.setSelection(2);
                //Toast.makeText(getApplicationContext()," set hard",Toast.LENGTH_SHORT).show();

                break;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        switch (position) {
            case 0:
                preferences.edit().putInt("difficulty", 1).apply();
                //Toast.makeText(getApplicationContext(), String.valueOf(position) +"Easy",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                preferences.edit().putInt("difficulty", 2).apply();
                //Toast.makeText(getApplicationContext(), String.valueOf(position) +"Medium",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                preferences.edit().putInt("difficulty", 3).apply();
                //Toast.makeText(getApplicationContext(),String.valueOf(position) +"Hard",Toast.LENGTH_SHORT).show();
                break;

        }

        //Toast.makeText(parent.getContext(), "Selected: " + preferences.getInt("difficulty", 2), Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
