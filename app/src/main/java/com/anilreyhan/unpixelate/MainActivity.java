package com.anilreyhan.unpixelate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    Button startButton, settingsButton;
    ImageView banner;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_NAME, R.id.startButton);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        banner = (ImageView) findViewById(R.id.imageView);
        startButton = (Button) findViewById(R.id.startButton);
        //settingsButton = (Button) findViewById(R.id.settingsButton);


        //banner.setLayoutParams(new ImageView());
        //Toast.makeText(getApplicationContext(), "Width: " + screen_width + "Height: " + screen_height, Toast.LENGTH_SHORT).show();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameScreen.class);
                startActivity(i);

            }
        });
/*
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), Settings.class);
                startActivity(a);
            }
        });

        */
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}

/*
*
* <Button
        android:id="@+id/settingsButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Settings"


        android:layout_marginTop="8dp"
        app:layout_constraintTop_toBottomOf="@+id/imageView"
        android:layout_marginLeft="70dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginBottom="8dp"
        app:layout_constraintVertical_bias="0.529" />
*/
