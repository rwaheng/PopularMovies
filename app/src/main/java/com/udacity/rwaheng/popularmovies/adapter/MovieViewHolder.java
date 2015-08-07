package com.udacity.rwaheng.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.rwaheng.popularmovies.R;


public class MovieViewHolder extends RecyclerView.ViewHolder
{
    public ImageView mImageView;
    public TextView mMovieNameView;
    public TextView mMovieRatingView;


    public MovieViewHolder(View itemView)
    {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.movie_image);
        mMovieNameView = (TextView) itemView.findViewById(R.id.movie_name);
        mMovieRatingView = (TextView) itemView.findViewById(R.id.movie_rating);
     //   mImageView.setOnClickListener(new onClick());

    }
/*
    class onClick implements RecyclerView.OnClickListener {

        @Override
        public void onClick(View v) {
            //mRecyclerCardCallback.

            //int position =((RecyclerView)mRecyclerView).getChildAdapterPosition((ImageView) v);

            // mRecyclerCardCallback.onItemImageClick(v);
        }
    }*/
}
