package com.anilreyhan.unpixelate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CheckBox soundCB, vibrationCB;
    GameScreen gameScreen;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundCB = (CheckBox) findViewById(R.id.soundCheckBox);
        vibrationCB = (CheckBox) findViewById(R.id.vibrationCheckBox);

        gameScreen = new GameScreen();
        preferences = new Preferences();


        vibrationCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.vibration = vibrationCB.isChecked();

            }
        });

        soundCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.sound = soundCB.isChecked();

            }
        });

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
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
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
