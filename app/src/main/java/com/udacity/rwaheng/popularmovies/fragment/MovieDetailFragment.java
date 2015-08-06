package com.udacity.rwaheng.popularmovies.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
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
import com.udacity.rwaheng.popularmovies.util.ColumnCalculator;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {
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

        Point size= ColumnCalculator.getScreenSize(appCompatActivity);

        Intent intent = appCompatActivity.getIntent();
         View view=inflater.inflate(R.layout.fragment_movie_detail, container, false);

        imageView = (ImageView)view.findViewById(R.id.movie_image);
        imageView.setMaxHeight(size.y/2);
        imageView.setMaxWidth(size.x-20);
        Picasso.with(appCompatActivity).load(intent.getStringExtra("image_path")).into(imageView);

        TextView titleView = (TextView)view.findViewById(R.id.title);
        TextView overviewView = (TextView)view.findViewById(R.id.overview);
        TextView vote = (TextView)view.findViewById(R.id.vote);
        TextView realeaseDateView = (TextView)view.findViewById(R.id.release_date);

        titleView.setText(intent.getStringExtra("title"));
        overviewView.setText(intent.getStringExtra("overview"));
        vote.setText(intent.getDoubleExtra("vote", 0)+"");
        realeaseDateView.setText(intent.getStringExtra("release_date"));




        Log.v("intent " ,
                intent.getStringExtra("title") +
                intent.getStringExtra("image_path") +
                intent.getStringExtra("overview") +
                intent.getDoubleExtra("vote", 0) +
                intent.getStringExtra("release_date"));



        return view;
    }
}
