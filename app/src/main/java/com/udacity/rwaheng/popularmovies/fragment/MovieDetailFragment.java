package com.udacity.rwaheng.popularmovies.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.adapter.RecyclerItem;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiClient;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiServices;
import com.udacity.rwaheng.popularmovies.model.MovieBean;
import com.udacity.rwaheng.popularmovies.util.ColumnCalculator;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    final static String LOG_TAG=MovieDetailFragment.class.getSimpleName();

    MovieDbApiServices mMovieDBApiServices=null;


    private AppCompatActivity appCompatActivity;

    public MovieDetailFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        appCompatActivity = (AppCompatActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView imageView;

       // Point size= ColumnCalculator.getScreenSize(appCompatActivity);

        Intent intent = appCompatActivity.getIntent();
         View view=inflater.inflate(R.layout.fragment_movie_detail, container, false);

        MovieBean movie = intent.getExtras().getParcelable("movie");
        imageView = (ImageView)view.findViewById(R.id.movie_image);

        Picasso.with(appCompatActivity).load(RecyclerItem.makeTmdbURL(movie.getPoster_path())).into(imageView);

        TextView titleView = (TextView)view.findViewById(R.id.title);
        TextView overviewView = (TextView)view.findViewById(R.id.overview);
        TextView vote = (TextView)view.findViewById(R.id.vote);
        TextView realeaseDateView = (TextView)view.findViewById(R.id.release_date);


        titleView.setText(movie.getTitle());
        overviewView.setText(movie.getOverview());
        vote.setText(""+movie.getVote_average());
        realeaseDateView.setText(movie.getRelease_date());
        Log.v(LOG_TAG,movie.getOriginal_title());



/*
        Log.v(LOG_TAG ,
                "title:-  "+intent.getStringExtra("title") +
                        "   image_path:-   "+intent.getStringExtra("image_path") +
                        "   overview:-   "+intent.getStringExtra("overview") +
                        "   vote:-   "+intent.getDoubleExtra("vote", 0) +
                        "   release_date:-   "+intent.getStringExtra("release_date"));

*/

        return view;
    }


    class FetchVideo extends AsyncTask {
        final static String REVIEW="review";
        final static String VIDEO="video";

        @Override
        protected Object doInBackground(Object[] params) {
            if(mMovieDBApiServices==null)
                mMovieDBApiServices= MovieDbApiClient.getMovieService();


        if(null!=params[0])
            return mMovieDBApiServices.getVideos((String) params[0]);
            else
            return null;
        }
    }

    class FetchReview extends AsyncTask {
        final static String REVIEW="review";
        final static String VIDEO="video";

        @Override
        protected Object doInBackground(Object[] params) {
            if(mMovieDBApiServices==null)
                mMovieDBApiServices= MovieDbApiClient.getMovieService();
            if(null!=params[0])
                return mMovieDBApiServices.getReviews((String) params[0]);
            else
                return null;
        }
    }
    
}
