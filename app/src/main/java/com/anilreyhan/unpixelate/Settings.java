package com.anilreyhan.unpixelate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CheckBox vibrationCB;
    GameScreen gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("vibrate", true);
        editor.putInt("difficulty", 2);
        editor.apply();

        vibrationCB = (CheckBox) findViewById(R.id.vibrationCheckBox);

        gameScreen = new GameScreen();

        if (preferences.getBoolean("vibrate", false)) {
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
                if (preferences.getBoolean("vibrate", false)) {
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();

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


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        int item = parent.getSelectedItemPosition();

        switch (item) {
            case 1:
                getSharedPreferences("difficulty", 2).edit().putInt("difficulty", 1).apply();
            case 2:
                getSharedPreferences("difficulty", 2).edit().putInt("difficulty", 2).apply();
            case 3:
                getSharedPreferences("difficulty", 2).edit().putInt("difficulty", 3).apply();
        }
        //Toast.makeText(parent.getContext(), "Selected: " + getSharedPreferences("difficulty", 2).getInt("difficulty", 2), Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
