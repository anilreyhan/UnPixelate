package com.anilreyhan.unpixelate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;
import android.support.annotation.IntegerRes;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    int screenWidth, screenHeight;

    public ImageAdapter(Context c, int screenWidth, int screenHeight) {
        mContext = c;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        fillArray();
    }

    private void fillArray() {
        for(int i=0;i<100;i++){
            mThumbIds[i]=randomColor();
        }
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams( (int)(screenWidth / 10.5), screenHeight / 20));
            imageView.setPadding(0, 0, 0, 0);
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


    public Integer[] mThumbIds = new Integer[100];

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


}
