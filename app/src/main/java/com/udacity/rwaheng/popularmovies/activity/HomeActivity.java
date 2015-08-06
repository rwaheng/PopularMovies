package com.udacity.rwaheng.popularmovies.activity;

import android.os.Bundle;
import android.view.Menu;

import com.udacity.rwaheng.popularmovies.fragment.MovieRecyclerFragment;
import com.udacity.rwaheng.popularmovies.R;

public class HomeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(getLayoutResource());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_activity, new MovieRecyclerFragment())
                    .commit();
        }
        // super.loadInfoToolbar();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}