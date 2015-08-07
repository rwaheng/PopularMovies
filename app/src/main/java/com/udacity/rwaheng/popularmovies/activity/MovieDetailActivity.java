package com.udacity.rwaheng.popularmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.fragment.MovieDetailFragment;
import com.udacity.rwaheng.popularmovies.fragment.MovieRecyclerFragment;

public class MovieDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(getLayoutResource());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_home, new MovieDetailFragment())
                    .commit();
        }
        // super.loadInfoToolbar();

    }
     @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_detail;
    }


}
