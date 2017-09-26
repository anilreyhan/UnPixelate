package com.anilreyhan.unpixelate.New;


import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anilreyhan on 26.09.2017.
 */

public class Box  {

    public int x,y;
    public int color;
    public float boxSize;
    public int newColor;
    int id;


    public Box(int id, int x, int y, int color, float boxSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.boxSize = boxSize;
        this.id = id;


    }


    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(x*boxSize,y*boxSize,(x+1)*boxSize,(y+1)*boxSize,paint);



    }







}
