package com.anilreyhan.unpixelate.New;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.anilreyhan.unpixelate.GameScreen;
import com.anilreyhan.unpixelate.R;

import java.util.ArrayList;
import java.util.Stack;


public class GameView extends View {

    public int size;
    Paint paint;
    public ArrayList<Box> boxes = null;
    public GameScreen gameScreen;
    SharedPreferences preferences;
    public static int x_offset = 0;


    public void setActivity(GameScreen gameScreen_){

       this.gameScreen = gameScreen_;

    }


    public GameView(Context context) {
        super(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (preferences.getInt("difficulty", 2)) {
            case 1:
                size = 5;
                break;
            case 2:
                size = 10;
                break;
            case 3:
                size = 15;
                break;
        }
        init(null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (preferences.getInt("difficulty", 2)) {
            case 1:
                size = 5;
                break;
            case 2:
                size = 10;
                break;
            case 3:
                size = 15;
                break;
        }
        init(attrs);

    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (preferences.getInt("difficulty", 2)) {
            case 1:
                size = 5;
                break;
            case 2:
                size = 10;
                break;
            case 3:
                size = 15;
                break;
        }
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        gameScreen = new GameScreen();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (preferences.getInt("difficulty", 2)) {
            case 1:
                size = 5;
                break;
            case 2:
                size = 10;
                break;
            case 3:
                size = 15;
                break;
        }
        init(attrs);
    }

    private Stack<Box> boxStack;
    private ArrayList<Box> visitedBoxes;

    public void init(@Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        boxStack = new Stack<>();
        visitedBoxes = new ArrayList<>();
    }

    public void algorithm(int color) {
        visitedBoxes.clear();
        boxStack.clear();

        for (int i = 0; i < size * size; i++) {

            boxes.get(i).newColor = -1;

        }

        boxes.get(0).newColor = color;
        boxStack.push(boxes.get(0));
        while (!boxStack.isEmpty()) {
            Box currentBox = boxStack.pop();
            System.out.println("XXX: ID: " + currentBox.id);
            visitedBoxes.add(currentBox);

            currentBox.newColor = color;

            for (Box b : getNeighbours(currentBox, currentBox.color)) {
                if (!visitedBoxes.contains(b)) {
                    boxStack.push(b);
                }
            }

        }

        for (int i = 0; i < size * size; i++) {
            if (boxes.get(i).newColor != -1) {
                boxes.get(i).color = boxes.get(i).newColor;
            }
        }

        boolean isFinished = true;
        for (int i = 0; i < size * size; i++) {
            if (boxes.get(i).color != color) {
                isFinished = false;
            }
        }

        if (isFinished) {
            Toast.makeText(gameScreen.getApplicationContext(), R.string.youWinToast, Toast.LENGTH_LONG).show();
            //onBlueClicked();
            winAnim(this);
            gameScreen.gameFinished();
            /// preferences.edit().putBoolean("finished", true).apply();
            //gameScreen.onBackPressed();

        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < size * size; i++) {
            boxes.get(i).draw(canvas, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (boxes == null) {
            boxes = new ArrayList<>();

            for (int i = 0; i < size * size; i++) {
                boxes.add(new Box(i, i % size, i / size, getContext().getResources().getColor(randomColor()), parentHeight / size));
            }
        }

        x_offset = (parentWidth - (parentHeight))/2;

        postInvalidate();
    }

    public void onBlueClicked() {
        algorithm(returnColor(R.color.blue));
    }

    public void onCyanClicked() {
        algorithm(returnColor(R.color.cyan));
    }

    public void onGreenClicked() {
        algorithm(returnColor(R.color.green));
    }

    public void onRedClicked() {
        algorithm(returnColor(R.color.red));
    }

    public void onYellowClicked() {
        algorithm(returnColor(R.color.yellow));
    }


    public ArrayList<Box> getNeighbours(Box box, int color) {
        ArrayList<Box> neighbours = new ArrayList<>();

        if (box.x - 1 >= 0) {
            Box candidateBox = getBox(box.x - 1, box.y);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.x + 1 < size) {
            Box candidateBox = getBox(box.x + 1, box.y);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.y - 1 >= 0) {
            Box candidateBox = getBox(box.x, box.y - 1);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.y + 1 < size) {
            Box candidateBox = getBox(box.x, box.y + 1);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        return neighbours;
    }

    public Box getBox(int x, int y) {

        for (int i = 0; i < boxes.size(); i++) {
            if (i == y * size + x) {
                return boxes.get(i);
            }
        }
        return null;
    }

    public int returnColor(int color) {
        return getContext().getResources().getColor(color);
    }


    public int randomColor() {

        int a;

        int color = 0;


        do {
            a = (int) (Math.random() * 6);
        }
        while (a <= 0 || a > 5);


        if (a == 1) {
            color = R.color.blue;
        }
        if (a == 2) {
            color = R.color.cyan;
        }
        if (a == 3) {
            color = R.color.green;
        }
        if (a == 4) {
            color = R.color.red;
        }
        if (a == 5) {
            color = R.color.yellow;
        }

        //System.out.println(a);

        return color;


    }

    public void winAnim(View view) {
        Animation animation1 =
                AnimationUtils.loadAnimation(getContext(),
                        R.anim.win_animation);
        view.startAnimation(animation1);
    }


}









