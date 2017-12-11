package com.anilreyhan.unpixelate.Tutorial;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.anilreyhan.unpixelate.R;

import java.util.ArrayList;
import java.util.Stack;


public class TutorialView extends View {

    public int size = 10;
    public int moveCount = 0;
    Paint paint;
    public ArrayList<BoxTutorial> boxes = null;
    public TutorialActivity tutorialActivity;
    public int tutorialColors[] = {
            4, 3, 4, 4, 5, 5, 5, 5, 5, 2,
            2, 5, 1, 3, 1, 3, 2, 4, 1, 2,
            3, 3, 2, 3, 4, 2, 3, 1, 3, 3,
            1, 3, 4, 3, 4, 3, 3, 5, 2, 2,
            5, 5, 5, 2, 1, 5, 2, 1, 2, 3,
            2, 2, 3, 4, 3, 2, 3, 1, 5, 4,
            2, 4, 1, 5, 5, 2, 2, 1, 5, 5,
            4, 2, 3, 1, 3, 5, 1, 4, 3, 4,
            5, 5, 2, 4, 5, 5, 2, 3, 1, 3,
            1, 4, 5, 1, 5, 2, 4, 1, 4, 3};


    public static int x_offset = 0;


    public void setActivity(TutorialActivity tutorialActivity_) {

        this.tutorialActivity = tutorialActivity_;

    }

    public TutorialView(Context context) {
        super(context);
        init(null);
    }

    public TutorialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);


    }

    public TutorialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TutorialView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        tutorialActivity = new TutorialActivity();
        init(attrs);

    }

    private Stack<BoxTutorial> boxStack;

    private ArrayList<BoxTutorial> visitedBoxes;

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
        boxes.get(0).text=true;

        boxes.get(0).newColor = color;
        boxStack.push(boxes.get(0));
        while (!boxStack.isEmpty()) {
            BoxTutorial currentBox = boxStack.pop();
            System.out.println("XXX: ID: " + currentBox.id);
            visitedBoxes.add(currentBox);

            currentBox.newColor = color;

            for (BoxTutorial b : getNeighbours(currentBox, currentBox.color)) {
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
            winAnim(this);
            tutorialActivity.gameFinished();
        }


        visitedBoxes.clear();
        boxStack.clear();
        boxStack.push(boxes.get(0));
        while (!boxStack.isEmpty()) {
            BoxTutorial currentBox = boxStack.pop();
            System.out.println("XXX: ID: " + currentBox.id);
            visitedBoxes.add(currentBox);

            currentBox.text = true;


            for (BoxTutorial b : getNeighbours(currentBox, currentBox.color)) {
                if (!visitedBoxes.contains(b)) {
                    boxStack.push(b);
                }
            }

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
                boxes.add(new BoxTutorial(i, i % size, i / size, getContext().getResources().getColor(getColorFromInt(tutorialColors[i])), parentHeight / size));
            }
            //
        }

        x_offset = (parentWidth - (parentHeight)) / 2;
        boxes.get(0).text=true;

        postInvalidate();
    }

    public boolean onBlueClicked() {
        if (boxes.get(0).color == R.color.blue) {
            return false;
        }
        algorithm(returnColor(R.color.blue));
        return true;
    }

    public boolean onCyanClicked() {
        if (boxes.get(0).color == R.color.cyan) {
            return false;
        }
        algorithm(returnColor(R.color.cyan));
        return true;
    }

    public boolean onGreenClicked() {
        if (boxes.get(0).color == R.color.green) {
            return false;
        }
        algorithm(returnColor(R.color.green));
        return true;
    }

    public boolean onRedClicked() {
        if (boxes.get(0).color == R.color.red) {
            return false;
        }
        algorithm(returnColor(R.color.red));
        return true;
    }


    public boolean onYellowClicked() {
        if (boxes.get(0).color == R.color.yellow) {
            return false;
        }
        algorithm(returnColor(R.color.yellow));
        return true;
    }

    public ArrayList<BoxTutorial> getNeighbours(BoxTutorial box, int color) {
        ArrayList<BoxTutorial> neighbours = new ArrayList<>();

        if (box.x - 1 >= 0) {
            BoxTutorial candidateBox = getBox(box.x - 1, box.y);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.x + 1 < size) {
            BoxTutorial candidateBox = getBox(box.x + 1, box.y);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.y - 1 >= 0) {
            BoxTutorial candidateBox = getBox(box.x, box.y - 1);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        if (box.y + 1 < size) {
            BoxTutorial candidateBox = getBox(box.x, box.y + 1);
            if (candidateBox.color == color) {
                neighbours.add(candidateBox);
            }
        }
        return neighbours;
    }

    public BoxTutorial getBox(int x, int y) {

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

    public int getColorFromInt(int code) {


        int color = 0;

        if (code == 1) {
            color = R.color.blue;
        }
        if (code == 2) {
            color = R.color.cyan;
        }
        if (code == 3) {
            color = R.color.green;
        }
        if (code == 4) {
            color = R.color.red;
        }
        if (code == 5) {
            color = R.color.yellow;
        }


        return color;
    }

    public void winAnim(View view) {
        Animation animation1 =
                AnimationUtils.loadAnimation(getContext(),
                        R.anim.win_animation);
        view.startAnimation(animation1);

    }


}









