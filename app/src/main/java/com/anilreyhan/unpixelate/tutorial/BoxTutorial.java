package com.anilreyhan.unpixelate.tutorial;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class BoxTutorial{

    int x,y;
    public int color;
    private float boxSize;
    int newColor;
    int id;
    boolean text = false;

    BoxTutorial(int id, int x, int y, int color, float boxSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.boxSize = boxSize;
        this.id = id;


    }


    void draw(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(x*boxSize+ TutorialView.x_offset,y*boxSize,(x+1)*boxSize+TutorialView.x_offset,(y+1)*boxSize,paint);
        if (text) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("*", x * boxSize + TutorialView.x_offset + (float) (boxSize / 2.8), y * boxSize + (float) (boxSize / 1.3), paint);
        }
    }







}
