package com.anilreyhan.unpixelate.New;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class BoxTutorial{

    public int x,y;
    public int color;
    public float boxSize;
    public int newColor;
    int id;
    boolean text = false;

    public BoxTutorial(int id, int x, int y, int color, float boxSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.boxSize = boxSize;
        this.id = id;


    }


    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(x*boxSize+ TutorialView.x_offset,y*boxSize,(x+1)*boxSize+TutorialView.x_offset,(y+1)*boxSize,paint);
        if (text) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("*", x * boxSize + TutorialView.x_offset + (float) (boxSize / 2.8), y * boxSize + (float) (boxSize / 1.3), paint);
        }
    }







}
