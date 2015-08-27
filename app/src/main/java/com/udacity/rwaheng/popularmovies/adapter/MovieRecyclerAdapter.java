package com.udacity.rwaheng.popularmovies.adapter;

import android.content.Context;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.model.MovieBean;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rwaheng on 8/1/2015.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String LOG_TAG=MovieRecyclerAdapter.class.getSimpleName();
    private final Context context;
    private ArrayList<MovieBean> items;


    MovieViewHolder mViewholder;
   // private final RecyclerCardCallback mRecyclerCardCallback;

   // RecyclerView mRecyclerView;

    public MovieRecyclerAdapter(Context context) {
        this.context = context;
       // this.mRecyclerCardCallback = recyclerCardCallback;
       // this.mRecyclerView=mRecyclerView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movie, viewGroup, false);
        mViewholder = new MovieViewHolder(v);
        return mViewholder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, final int i) {
       if (items != null) {
            MovieViewHolder viewHolder = (MovieViewHolder) vh;
           MovieBean selectedItem = items.get(i);

         if(null!=viewHolder.mMovieNameView)
                    viewHolder.mMovieNameView.setText(selectedItem.getOriginal_title());
         if(null!=viewHolder.mMovieRatingView)
                    viewHolder.mMovieRatingView.setText(selectedItem.getVote_average()+"");

         Picasso.with(context).load(RecyclerItem.makeTmdbURL(selectedItem.getPoster_path())).into(viewHolder.mImageView);

            //Log.v(LOG_TAG ,selectedItem.getOriginal_title()+" "+i);
           }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public ArrayList<MovieBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<MovieBean> items) {
        this.items = items;

    }

    public void addNewItems(ArrayList<MovieBean> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<MovieBean> items) {
        this.items.addAll(items);
        // sortByRating();
        notifyDataSetChanged();
    }

    public void sortByPopularity() {
        Collections.sort(items, new Comparator<MovieBean>() {
            @Override
            public int compare(MovieBean lhs, MovieBean rhs) {

                if (lhs.getPopularity() > rhs.getPopularity())
                    return -1;
                else
                    return 1;
            }
        });
        notifyDataSetChanged();
    }


    public void sortByRating() {
        Collections.sort(items, new Comparator<MovieBean>() {
            @Override
            public int compare(MovieBean lhs, MovieBean rhs) {

                if (lhs.getVote_average() > rhs.getVote_average())
                    return -1;
                else
                    return 1;
            }
        });
        notifyDataSetChanged();
    }



    public interface RecyclerCardCallback {
        void onItemImageClick(int position);
    }

    public RecyclerView.ViewHolder getmViewholder() {
        return mViewholder;
    }


    class ImageOnTouchListener implements RecyclerView.OnTouchListener {
        private int position;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    //overlay is black with transparency of 0x77 (119)
                    view.getDrawable().
                            setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                  //  Log.v(LOG_TAG,"ACTION_UP"+this.position+v.getClass());
                  //  int itemPosition = mRecyclerView.getChildPosition(c);
                  //  mRecyclerCardCallback.onItemImageClick(this.position);
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    //clear the overlay
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }

            return true;
        }

        ImageOnTouchListener(int i){
            this.position=i;
        }


    }


}
