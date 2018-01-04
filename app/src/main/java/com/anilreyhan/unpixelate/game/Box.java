package com.anilreyhan.unpixelate.game;


import android.graphics.Canvas;
import android.graphics.Paint;


public class Box {

    int x, y;
    public int color;
    private float boxSize;
    int newColor;
    int id;


    Box(int id, int x, int y, int color, float boxSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.boxSize = boxSize;
        this.id = id;


    }


    void draw(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(x * boxSize + GameView.x_offset, y * boxSize, (x + 1) * boxSize + GameView.x_offset, (y + 1) * boxSize, paint);

    }


}
