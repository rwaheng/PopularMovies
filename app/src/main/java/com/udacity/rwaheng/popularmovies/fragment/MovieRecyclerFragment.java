package com.udacity.rwaheng.popularmovies.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.udacity.rwaheng.popularmovies.model.movie.MovieAllBean;
import com.udacity.rwaheng.popularmovies.util.FetchDataAsync;
import com.udacity.rwaheng.popularmovies.util.PreferencesManager;
import com.udacity.rwaheng.popularmovies.R;
import com.udacity.rwaheng.popularmovies.activity.MovieDetailActivity;
import com.udacity.rwaheng.popularmovies.util.ColumnCalculator;
import com.udacity.rwaheng.popularmovies.adapter.MovieRecyclerAdapter;
import com.udacity.rwaheng.popularmovies.adapter.RecyclerItem;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiClient;
import com.udacity.rwaheng.popularmovies.api.MovieDbApiServices;
import com.udacity.rwaheng.popularmovies.model.MovieBean;
import com.udacity.rwaheng.popularmovies.model.MovieResults;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by rwaheng on 8/1/2015.
 */
public class MovieRecyclerFragment extends Fragment {
    public static final String LOG_TAG = MovieRecyclerFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;
    private MovieDbApiServices mMovieDbApiServices;
    private AppCompatActivity appCompatActivity;
    private ArrayList<MovieBean> movieList;
  //  private Results mResult;
    private boolean mLoadingFlag;
    private LayoutInflater mInflater;
    private PreferencesManager  pref;

    final private int MAX_PAGE = 2;
    private int PAGE_COUNT = 1;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(MovieBean movieBean);
    }

    @Override
    public void onAttach(Activity activity) {
        appCompatActivity = (AppCompatActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      //  outState.putString("message", "This is my message to be reloaded");
       outState.putParcelableArrayList("list", movieList);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler_card_fragment, container, false);

            GridLayoutManager layoutManager = new GridLayoutManager(appCompatActivity, ColumnCalculator.getMaxColumnsForScreen(appCompatActivity, 300));
            mRecyclerView.setLayoutManager(layoutManager);
            mMovieRecyclerAdapter = new MovieRecyclerAdapter(appCompatActivity);
            mRecyclerView.setAdapter(mMovieRecyclerAdapter);
            pref = PreferencesManager.initializeInstance(appCompatActivity);
            pref.setValuePageCount(PAGE_COUNT);
           // Log.v(LOG_TAG, "onCreateView " + "execute  " + pref.getValuePageCount()+"  "+pref.getValueSortBy());
            if(savedInstanceState!=null){
               // ArrayList<Movie>

                movieList=savedInstanceState.getParcelableArrayList("list");
                if(movieList!=null) {
                    mMovieRecyclerAdapter.addNewItems(movieList);
                    Log.v(LOG_TAG, "restore-----------" + movieList.size());
                }
                else {
                    Log.e(LOG_TAG, "FAILED to Restored");
                }

            }else {
                new FetchMovie().execute(FetchMovie.GET_DISCOVERY_MOVIE, pref.getValuePageCount(), pref.getValueSortBy());

            }
            setHasOptionsMenu(true);

            mRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(appCompatActivity, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            ((Callback)getActivity()).onItemSelected(((List<MovieBean>)mMovieRecyclerAdapter.getItems()).get(position));

                          //  invokeMovieDetailActivity(((List<MovieBean>)mMovieRecyclerAdapter.getItems()).get(position));
                        }
                    }));

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                 int visibleItemCount,totalItemCount,pastVisiblesItems;
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                   GridLayoutManager layoutManager= (GridLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount=layoutManager.getChildCount();

                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!mLoadingFlag) {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount && PAGE_COUNT<=MAX_PAGE) {
                            new FetchMovie().execute(FetchMovie.GET_DISCOVERY_MOVIE,pref.getValuePageCount(), pref.getValueSortBy());
                           // Log.v(LOG_TAG, "Wow");
                        }
                    }
               }
            });
        }
        return mRecyclerView;
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
           // mMovieRecyclerAdapter.sortByPopularity();

            pref=PreferencesManager.initializeInstance(appCompatActivity);
            pref.setValueSortBy(PreferencesManager.BY_POPULARITY);
            pref.setValuePageCount(1);
           Log.v(LOG_TAG, "onOptionsItemSelected " + "sortByPopularity  " + pref.getValueSortBy());
            new FetchMovie().execute(FetchMovie.GET_DISCOVERY_MOVIE,pref.getValuePageCount(), pref.getValueSortBy());

            return true;
        } else if (id == R.id.action_settings_rating) {
           // mMovieRecyclerAdapter.sortByRating();
            pref=PreferencesManager.initializeInstance(appCompatActivity);
            pref.setValueSortBy(PreferencesManager.BY_RATING);
            pref.setValuePageCount(1);
            Log.v(LOG_TAG, "onOptionsItemSelected " + "sortByRating  " + pref.getValueSortBy());
            new FetchMovie().execute(FetchMovie.GET_DISCOVERY_MOVIE,pref.getValuePageCount(), pref.getValueSortBy());

            return true;
        }else if (id == R.id.action_settings_favorite) {
            // mMovieRecyclerAdapter.sortByRating();
            pref=PreferencesManager.initializeInstance(appCompatActivity);
           // pref.setValueSortBy(PreferencesManager.BY_RATING);
            pref.setValuePageCount(1);
            String favMovieList=pref.getFavMovie();

            Log.v(LOG_TAG,  "favMovieList"+favMovieList);

            //Log.v(LOG_TAG,favMovieList);
          //  new FetchMovie().execute(pref.getValuePageCount(), pref.getValueSortBy());
            new FetchMovie().execute(FetchMovie.GET_DETAIL_MOVIE,favMovieList);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class FetchMovie extends AsyncTask {
        final static String GET_DISCOVERY_MOVIE="discovery_movie";
        final static String GET_DETAIL_MOVIE="detail_movie";


        @Override
        protected void onPostExecute(Object results) {
            super.onPostExecute(results);
            //movieList = ((MovieResults) results).getResults();
            movieList=(ArrayList<MovieBean>)results;
            pref=PreferencesManager.initializeInstance(appCompatActivity);
            PAGE_COUNT=pref.getValuePageCount();
            //Log.v(LOG_TAG,"page count :-"+PAGE_COUNT+" "+pref.getValuePageCount());

            if (PAGE_COUNT == 1) {
                mMovieRecyclerAdapter.addNewItems(movieList);
                movieList=mMovieRecyclerAdapter.getItems();
              //  Log.v(LOG_TAG, "addNewItems " + PAGE_COUNT + " " + ((List<Movie>) ((Results) results).getResults()).size());
                PAGE_COUNT++;

            } else if (PAGE_COUNT <= MAX_PAGE) {
                mMovieRecyclerAdapter.addItems(movieList);
                movieList=mMovieRecyclerAdapter.getItems();
              //  Log.v(LOG_TAG, "addItems " + PAGE_COUNT +" "+((List<Movie>) ((Results) results).getResults()).size());
                PAGE_COUNT++;
            }


            //showing only 1 page now so no increment
            pref.setValuePageCount(PAGE_COUNT);
            mLoadingFlag = false;
            //Log.v(LOG_TAG, "onPostExecute " + mResult.getResults().size());
        }

        @Override
        protected Object doInBackground(Object[] params) {
      //     Log.v(LOG_TAG, "doInBackground  GetMoviesBy " + params[0] + "   " + (int) params[1] + "   " + (String) params[2]);
            String apiType=(String)params[0];
            List<MovieBean> result=null;
            mLoadingFlag = true;
            if (mMovieDbApiServices == null)
                mMovieDbApiServices = MovieDbApiClient.getMovieService();
            if(FetchMovie.GET_DISCOVERY_MOVIE.equals(apiType)) {
                result = mMovieDbApiServices.GetMoviesBy((int) params[1], (String) params[2]).getResults();
            }
            else if(FetchMovie.GET_DETAIL_MOVIE.equals(apiType)){
                result= getFavMovies((String)params[1]);
            }

            return result;
        }

        List<MovieBean> getFavMovies(String favMovieList){
            if (mMovieDbApiServices == null)
                mMovieDbApiServices = MovieDbApiClient.getMovieService();

          //  String a="116741|166424|102899";
            MovieAllBean movieAll;
            List<MovieBean> movieBeanList= new ArrayList<MovieBean>();

            StringTokenizer st= new StringTokenizer(favMovieList,"|");

            while(st.hasMoreElements()){
                movieAll=mMovieDbApiServices.getDetailMovie(st.nextToken());
            //    Log.v(LOG_TAG,movieAll.getBackdropPath());
              //  Log.v(LOG_TAG,movieAll.getOriginalTitle());
                if(movieAll!=null)
                movieBeanList.add(new MovieBean(movieAll));
            }
            return movieBeanList;
        }
    }

}
