package com.udacity.rwaheng.popularmovies.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.fragment.MovieDetailFragment;
import com.udacity.rwaheng.popularmovies.fragment.MovieRecyclerFragment;
import com.udacity.rwaheng.popularmovies.model.MovieBean;

public class MovieDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(getLayoutResource());
        if (savedInstanceState == null) {
            Intent intent = this.getIntent();
            Bundle args = new Bundle();
            MovieBean  movieBean= null;

            MovieDetailFragment fragment = new MovieDetailFragment();

            movieBean = intent.getExtras().getParcelable("movie");
            if(movieBean!=null) {
                args.putParcelable("movie", movieBean);
                fragment.setArguments(args);
            }

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_home, fragment)
                    .commit();
        }
        // super.loadInfoToolbar();

    }
     @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_detail;
    }


}
