package com.udacity.rwaheng.popularmovies.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public abstract class BaseActivity extends AppCompatActivity
{
    private Toolbar mToolBar;

    private SharedPreferences setting;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());


    }


    protected abstract int getLayoutResource();




    public SharedPreferences getSetting() {
        return setting;
    }
}