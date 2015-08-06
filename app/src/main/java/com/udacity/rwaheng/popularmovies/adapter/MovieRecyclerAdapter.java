package com.udacity.rwaheng.popularmovies.adapter;

import android.content.Context;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.model.Movie;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rwaheng on 8/1/2015.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Movie> items;


    MovieViewHolder mViewholder;
    private final RecyclerCardCallback mRecyclerCardCallback;

  //  public  static String mDefaulImagetSize = IMAGE_MEDIUM_SIZE;

    public MovieRecyclerAdapter(Context context, RecyclerCardCallback recyclerCardCallback) {
        this.context = context;
        this.mRecyclerCardCallback = recyclerCardCallback;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movie, viewGroup, false);
        mViewholder = new MovieViewHolder(v);

    /*    mViewholder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerCardCallback.onItemImageClick(mViewholder.getAdapterPosition());
            }
        });*/

        return mViewholder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, final int i) {
        if (items != null) {
            MovieViewHolder viewHolder = (MovieViewHolder) vh;
            Movie selectedItem = items.get(i);
            viewHolder.mMovieNameView.setText(selectedItem.getOriginal_title());
            viewHolder.mMovieRatingView.setText(selectedItem.getVote_average()+"");
            //viewHolder.mImageView.setOnClickListener(this);
            // Picasso.with(context).setLoggingEnabled(true);
            Picasso.with(context).load(RecyclerItem.makeTmdbURL(selectedItem.getPoster_path())).into(viewHolder.mImageView);
            mViewholder.mImageView.setOnTouchListener(new ImageOnTouchListener(i));
            }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;

    }

    public void addNewItems(List<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Movie> items) {
        this.items.addAll(items);
        // sortByRating();
        notifyDataSetChanged();
    }

    public void sortByPopularity() {
        Collections.sort(items, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {

                if (lhs.getPopularity() > rhs.getPopularity())
                    return 1;
                else
                    return -1;
            }
        });
        notifyDataSetChanged();
    }


    public void sortByRating() {
        Collections.sort(items, new Comparator<Movie>() {
            @Override
            public int compare(Movie lhs, Movie rhs) {

                if (lhs.getVote_count() > rhs.getVote_count())
                    return 1;
                else
                    return -1;
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


    class ImageOnTouchListener implements View.OnTouchListener {
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
                    mRecyclerCardCallback.onItemImageClick(this.position);
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
