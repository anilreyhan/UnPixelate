package com.anilreyhan.unpixelate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    Button startButton;
    ImageView banner;
    Context context;
    static int a = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (a == 0) {
            startAnimation();
            a++;
        }


        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_NAME, R.id.startButton);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        banner = (ImageView) findViewById(R.id.imageView);
        startButton = (Button) findViewById(R.id.startButton);

        //banner.setLayoutParams(new ImageView());
        //Toast.makeText(getApplicationContext(), "Width: " + screen_width + "Height: " + screen_height, Toast.LENGTH_SHORT).show();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GameScreen.class);
                startActivity(i);

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), Settings.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_feedback) {
            Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
            startActivity(i);
        } else if (id == R.id.action_about) {
            Intent i = new Intent(getApplicationContext(), About.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);

    }

    public void startAnimation() {
        Intent startAnimation = new Intent(getApplicationContext(), StartAnimation.class);
        startActivity(startAnimation);

    }

}


