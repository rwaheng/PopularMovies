package com.udacity.rwaheng.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.udacity.rwaheng.popularmovies.fragment.MovieDetailFragment;
import com.udacity.rwaheng.popularmovies.fragment.MovieRecyclerFragment;
import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.model.MovieBean;

import java.util.List;

public class HomeActivity extends BaseActivity implements MovieRecyclerFragment.Callback {
    public static final String LOG_TAG = HomeActivity.class.getSimpleName();
    private boolean mTwoPane=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutResource());



        if (findViewById(R.id.fragment_moviedetail) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
        /*    if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_moviedetail, new MovieDetailFragment())
                        .commit();
            } */
        } else {
            mTwoPane = false;
           // getSupportActionBar().setElevation(0f);
        }

     /*   if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.home_activity, new MovieRecyclerFragment())
                    .commit();
        }*/
        // super.loadInfoToolbar();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.home_activity;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemSelected(MovieBean movieBean) {

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable("movie", movieBean);

            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(args);
            Log.v(LOG_TAG,"mTwoPane click");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_moviedetail, fragment)
                    .commit();
        } else {
            Log.v(LOG_TAG,"mOnePane click");
            Intent intent= new Intent(this,MovieDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("movie", movieBean);
            this.startActivity(intent);
        }
    }
}