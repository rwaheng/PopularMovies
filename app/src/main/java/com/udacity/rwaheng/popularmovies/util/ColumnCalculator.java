package com.udacity.rwaheng.popularmovies.util;


import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


public class ColumnCalculator
{

    public static int getMaxColumnsForScreen(AppCompatActivity context, int width)
    {
        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = context.getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        return Math.round(dpWidth/width);
    }

    public static Point getScreenSize(AppCompatActivity context){

        Display display = context.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        Point size = new Point();
        size.x=outMetrics.widthPixels;
        size.y=outMetrics.heightPixels;
        return size;
    }
}
