package com.udacity.rwaheng.popularmovies.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
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
import com.udacity.rwaheng.popularmovies.model.MovieResults;
import com.udacity.rwaheng.popularmovies.model.ReviewBean;
import com.udacity.rwaheng.popularmovies.model.ReviewResults;
import com.udacity.rwaheng.popularmovies.model.VideoBean;
import com.udacity.rwaheng.popularmovies.model.VideoResults;
import com.udacity.rwaheng.popularmovies.util.ColumnCalculator;
import com.udacity.rwaheng.popularmovies.util.FetchDataAsync;
import com.udacity.rwaheng.popularmovies.util.PreferencesManager;
import com.udacity.rwaheng.popularmovies.util.YouTubeHelper;

import java.util.List;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    final static String LOG_TAG = MovieDetailFragment.class.getSimpleName();


    MovieDbApiServices mMovieDBApiServices = null;
    List<ReviewBean> mReviews;
    List<VideoBean> mVideos;
    ImageView thumbNail;
    ViewGroup thumbNailContainer;
    PreferencesManager pref;


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

        pref = PreferencesManager.initializeInstance(appCompatActivity);
        Log.v(LOG_TAG,pref.getFavMovie());
        pref.addFavMovie("hello");
        pref.addFavMovie("hello1");
     //   pref.addFavMovie("hello2");
      //  pref.addFavMovie("hello3");
        Log.v(LOG_TAG, pref.getFavMovie());

        Bundle arguments = getArguments();
        MovieBean movie=null;
        if (arguments != null) {
            movie = arguments.getParcelable("movie");
        }

        ImageView imageView;
      //  Intent intent = appCompatActivity.getIntent();
       // = intent.getExtras().getParcelable("movie");
       /// new FetchReview().execute(movie.getId()+"");
       // new FetchVideo().execute(movie.getId()+"");


        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);


        imageView = (ImageView) view.findViewById(R.id.movie_image);
        thumbNail=(ImageView) view.findViewById(R.id.videoThumbNail);

        Picasso.with(appCompatActivity).load(RecyclerItem.makeTmdbURL(movie.getPoster_path())).into(imageView);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView overviewView = (TextView) view.findViewById(R.id.overview);
        TextView vote = (TextView) view.findViewById(R.id.vote);
        TextView realeaseDateView = (TextView) view.findViewById(R.id.release_date);
        thumbNailContainer=(ViewGroup)view.findViewById(R.id.thumbnail_container);

        if(movie==null)
            return view;

        titleView.setText(movie.getTitle());
        overviewView.setText(movie.getOverview());
        vote.setText("" + movie.getVote_average());
        realeaseDateView.setText(movie.getRelease_date());
        Log.v(LOG_TAG, movie.getOriginal_title());

        new FetchDataAsync() {
            @Override
            public void onSuccess(Object results) {
                mVideos=(List)((VideoResults)results).getVideos();
               // Picasso.with(appCompatActivity).load(YouTubeHelper.makeImgURL(mVideos.get(0).getKey(),1)).into(thumbNail);

                Log.v(LOG_TAG,YouTubeHelper.makeImgURL(mVideos.get(0).getKey(),1));
                if(mVideos!=null)
                for(VideoBean video:mVideos){
                    Log.v(LOG_TAG,""+video);
                    insertThumbNail(thumbNailContainer, video.getKey());
                }

            }

            @Override
            public String getApiName() {
                return FetchDataAsync.GET_VIDEOS;
            }
        }.execute(movie.getId() + "");

        new FetchDataAsync() {
            @Override
            public void onSuccess(Object results) {
                mReviews=(List)((ReviewResults)results).getReviews();
               // Log.v(LOG_TAG,String.valueOf(results));
               // Log.v(LOG_TAG,mVideos)
             //  mReviews.get(0).get

                if(mReviews!=null)
                for(ReviewBean review:mReviews){
                    Log.v(LOG_TAG,""+review);
                }

            }

            @Override
            public String getApiName() {
                return FetchDataAsync.GET_REVIEWS;
            }
        }.execute(movie.getId()+"");






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
        final static String REVIEW = "review";
        final static String VIDEO = "video";

        @Override
        protected Object doInBackground(Object[] params) {
            if (mMovieDBApiServices == null)
                mMovieDBApiServices = MovieDbApiClient.getMovieService();

            if (null != params[0])
                return mMovieDBApiServices.getVideos((String) params[0]);
            else
                return null;
        }

        @Override
        protected void onPostExecute(Object results) {
           mVideos=((VideoResults)results).getVideos();
        }

    }

    class FetchReview extends AsyncTask {
        final static String REVIEW = "review";
        final static String VIDEO = "video";

        @Override
        protected Object doInBackground(Object[] params) {
            if (mMovieDBApiServices == null)
                mMovieDBApiServices = MovieDbApiClient.getMovieService();
            if (null != params[0])
                return mMovieDBApiServices.getReviews((String) params[0]);
            else
                return null;
        }

        @Override
        protected void onPostExecute(Object results) {
            mReviews=((ReviewResults)results).getReviews();
        }
    }

    void insertThumbNail(ViewGroup container,final String key){
        LayoutInflater vi = (LayoutInflater) appCompatActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.movie_thumbnail, null);

// fill in any details dynamically here
        ImageView iview = (ImageView) v.findViewById(R.id.videoThumbNail);
        Picasso.with(appCompatActivity).load(YouTubeHelper.makeImgURL(key,1)).into(iview);

        iview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(YouTubeHelper.makeVideoURL(key))));

               // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key)));

            }
        });

// insert into main view

        container.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


}
