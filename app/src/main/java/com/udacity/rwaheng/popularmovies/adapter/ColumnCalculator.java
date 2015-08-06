package com.udacity.rwaheng.popularmovies.adapter;


import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;


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
}
