package com.udacity.rwaheng.popularmovies.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.activity.MovieDetailActivity;
import com.udacity.rwaheng.popularmovies.adapter.ColumnCalculator;
import com.udacity.rwaheng.popularmovies.adapter.MovieRecyclerAdapter;
import com.udacity.rwaheng.popularmovies.adapter.RecyclerItem;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiClient;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiServices;
import com.udacity.rwaheng.popularmovies.model.Movie;
import com.udacity.rwaheng.popularmovies.model.Results;

import java.util.List;

/**
 * Created by rwaheng on 8/1/2015.
 */
public class MovieRecyclerFragment extends Fragment implements MovieRecyclerAdapter.RecyclerCardCallback {
    public static final String LOG_TAG = MovieRecyclerFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;
    private MovieDbApiServices mMovieDbApiServices;
    private AppCompatActivity appCompatActivity;
    private Results mResult;
    private boolean mApiCallingFlag;
    LayoutInflater mInflater;

    final private int MAX_PAGE = 4;
    private int PAGE_COUNT = 1;

    @Override
    public void onAttach(Activity activity) {
        appCompatActivity = (AppCompatActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler_card_fragment, container, false);
            mMovieRecyclerAdapter = new MovieRecyclerAdapter(appCompatActivity,this);
            GridLayoutManager layoutManager = new GridLayoutManager(appCompatActivity, ColumnCalculator.getMaxColumnsForScreen(appCompatActivity, 300));
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mMovieRecyclerAdapter);
            setHasOptionsMenu(true);
         //   mRecyclerView.addOnScrollListener(new ScrollListener());

            //  layoutManager.get
        }
        new FetchMovie().execute(PAGE_COUNT, "popularity.desc");
       // new FetchMovie().execute(PAGE_COUNT, "vote_average.desc");

        return mRecyclerView;
    }

    @Override
    public void onItemImageClick(int position) {
        Toast.makeText(appCompatActivity, "check image", Toast.LENGTH_LONG).show();
        List<Movie> array=mMovieRecyclerAdapter.getItems();


        Intent intent= new Intent(appCompatActivity,MovieDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", array.get(position).getOriginal_title());
        intent.putExtra("image_path", RecyclerItem.makeTmdbURL(array.get(position).getPoster_path()));
        intent.putExtra("overview",array.get(position).getOverview());
        intent.putExtra("vote",array.get(position).getVote_average());
        intent.putExtra("release_date",array.get(position).getRelease_date());
        appCompatActivity.startActivity(intent);
        // Log.v("click", "click");
        //Toast.makeText(v.getContext(),"toast",Toast.LENGTH_SHORT);


    }



//for SwipeRefreshLayout onRefresh
    public void onRefresh() {

        // getSwipeRefreshLayout().postInvalidateDelayed(20000);
        Log.v("onRefresh", "onRefresh");

        if (PAGE_COUNT <= MAX_PAGE) {
            Log.v(LOG_TAG, "onRefresh " + PAGE_COUNT);
            new FetchMovie().execute(PAGE_COUNT, "popularity.desc");
        } else {
            Log.v(LOG_TAG, "onRefresh not call" + PAGE_COUNT);
            //getSwipeRefreshLayout().setRefreshing(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_popularity) {
            //on selecting the option, Call APi call if there is no pending call
            mMovieRecyclerAdapter.sortByPopularity();
            Log.v(LOG_TAG, "sortByPopularity");
            return true;
        } else if (id == R.id.action_settings_release) {
            mMovieRecyclerAdapter.sortByRating();
            Log.v(LOG_TAG, "sortByRating");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class FetchMovie extends AsyncTask {
        @Override
        protected void onPostExecute(Object results) {
            super.onPostExecute(results);

            mResult = (Results) results;

            if (PAGE_COUNT == 1) {
                mMovieRecyclerAdapter.addNewItems((List<Movie>) ((Results) results).getResults());
                Log.v(LOG_TAG, "addNewItems " + PAGE_COUNT);

            } else if (PAGE_COUNT <= MAX_PAGE) {
                mMovieRecyclerAdapter.addItems((List<Movie>) ((Results) results).getResults());
                Log.v(LOG_TAG, "addItems " + PAGE_COUNT);
            }


            PAGE_COUNT++;
            mApiCallingFlag = false;
            // mApiCallingFlag=false;
            //Log.v(LOG_TAG, "onPostExecute " + mResult.getResults().size());
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.v(LOG_TAG, "doInBackground ");
            Results result;
            mApiCallingFlag = true;
            if (mMovieDbApiServices == null)
                mMovieDbApiServices = MovieDbApiClient.getMovieService();
            result = mMovieDbApiServices.GetMoviesBy((int) params[0], (String) params[1]);
            return result;
        }
    }

}
