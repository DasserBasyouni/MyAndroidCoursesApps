package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.data.pm2_Contract;
import unitedapps.com.googleandroidcourses.R;

import static unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.pm2_Utils.getArrayFormat;
import static unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.pm2_Utils.getYoutubeTrailers;

public class pm2_DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    TextView pm_title_tv, pm_plotSynopsis_tv, pm_rekeaseDate_tv, pm_userRate_tv, pm2_runtime_tv;
    ImageView pm_thumbnail_iv;
    ListView pm2_trailer_lv;
    FloatingActionButton pm2_fab;
    LoaderManager.LoaderCallbacks loaderCallback;
    Button pm2_fav_btn;
    String mID;

    private static final int pm2_DETAILS_LOADER_ID = 2;
    int moviePosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.i("Z_b not", "null");
            moviePosition = bundle.getInt("p")+1;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pm2_details_fragment, container, false);

        pm_title_tv = (TextView) rootView.findViewById(R.id.pm2_title_tv);
        pm_plotSynopsis_tv = (TextView) rootView.findViewById(R.id.pm2_plotSynopsis_tv);
        pm_rekeaseDate_tv = (TextView) rootView.findViewById(R.id.pm2_releaseDate_tv_wt);
        pm_userRate_tv = (TextView) rootView.findViewById(R.id.pm2_userRate_tv);
        pm_thumbnail_iv = (ImageView) rootView.findViewById(R.id.pm2_thumbnail_iv);
        pm2_runtime_tv = (TextView) rootView.findViewById(R.id.pm2_runtime_tv);
        pm2_trailer_lv = (ListView) rootView.findViewById(R.id.pm2_trailer_lv);
        pm2_fab = (FloatingActionButton) rootView.findViewById(R.id.pm2_fab);
        pm2_fav_btn = (Button) rootView.findViewById(R.id.pm2_fav_btn);

        loaderCallback = this;
        getActivity().getSupportLoaderManager().initLoader(pm2_DETAILS_LOADER_ID, null, loaderCallback);
        return rootView;
    }


    public void restartLoader(){
        getActivity().getSupportLoaderManager().restartLoader(pm2_DETAILS_LOADER_ID, null, loaderCallback);
    }

    public void restartLoader(int position){
        moviePosition = position;
        getActivity().getSupportLoaderManager().restartLoader(pm2_DETAILS_LOADER_ID, null, loaderCallback);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.v("Z_pm2", "In onCreateLoader");
        Uri mUri = pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100);

        String cSelection = pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID + "=?";
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.i("Z_b not", "null");
            mID = bundle.getString("id");
        }

        String[] cSelectionArg = new String[]{mID};
        Log.i("Z_arg", Arrays.toString(cSelectionArg));

        if (mUri != null) {
            return new CursorLoader(
                    getContext(),
                    mUri,
                    pm2_MainFragment.pm2_Main_Movies_COLUMNS,
                    cSelection,
                    cSelectionArg,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.v("Z_pm2_DetailsActivity", "In onLoadFinished");

        String mName = null, mRate = null, mSys = null, mYear = null, mRuntime = null, mID = null;
        Bitmap bitmap = null;
        boolean mFav = false;
        String[] mTrailers = new String[0], mAuthors = new String[0], mContent = new String[0];

        if (cursor != null && cursor.moveToFirst()) {
            //Log.i("Z_c", "here");
            bitmap = pm2_Utils.getImageFromByteArray(cursor.getBlob(pm2_MainFragment.pm2_COLUMN_MOVIE_POSTER));
            mName = cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_NAME);
            mRuntime =  pm2_Utils.getRunTimeFormat(cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_LENGTH));
            mRate = pm2_Utils.getRateFormat(cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_RATE));
            mSys = cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_SYS);
            mYear = cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_YEAR);
            mID = cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_ID);

            mFav = cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_FAV) != null;

            mTrailers = getYoutubeTrailers(getArrayFormat(cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_TRAILERS)));
            mAuthors = getArrayFormat(cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_REVIEWS_A));
            mContent = getArrayFormat(cursor.getString(pm2_MainFragment.pm2_COLUMN_MOVIE_REVIEWS_C));
        }
        assert cursor != null;
        cursor.close();

        pm_thumbnail_iv.setImageBitmap(bitmap);
        pm_title_tv.setText(mName);
        pm_plotSynopsis_tv.setText(mSys);
        pm_userRate_tv.setText(mRate);
        pm_rekeaseDate_tv.setText(mYear);
        pm2_runtime_tv.setText(mRuntime);

        pm2_trailer_lv.setAdapter(new TrailersAndReviewsAdapter(getContext(), R.layout.pm2_trailer_list_item, mTrailers, true));
        setListViewHeightBasedOnChildren(pm2_trailer_lv);

        final List<ReviewsDO> reviewsDO = new ArrayList<>();
        for(int i=0; i<mAuthors.length ;i++){
            ReviewsDO reviewsDO1 = new ReviewsDO();
            reviewsDO1.setAuthorAndContent(mAuthors[i], mContent[i]);
            reviewsDO.add(reviewsDO1);
        }

        final String favValue;
        if(mFav){
            pm2_fav_btn.setText("In\nFavorite");
            favValue = null;
        }else{
            pm2_fav_btn.setText("Make As\nFavorite");
            favValue = "1";
        }

        final String finalMID = mID;
        pm2_fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues updatedFav = new ContentValues();
                updatedFav.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_FAV, favValue);

                String mSelectionClause = pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID + "=?";
                String[] mSelectionArgs = {finalMID};

                getActivity().getContentResolver().update(pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100)
                        , updatedFav, mSelectionClause, mSelectionArgs);
                restartLoader();
            }
        });

        pm2_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.pm2_dialog_lv);

                ListView lv = (ListView ) dialog.findViewById(R.id.pm2_dialog_lv);

                lv.setAdapter(new TrailersAndReviewsAdapter(getContext(), R.layout.pm2_review_list_item, reviewsDO,false));
                dialog.setTitle("Reviews");
                dialog.show();
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("Z_", "reset is called");
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        TrailersAndReviewsAdapter listAdapter = (TrailersAndReviewsAdapter) listView.getAdapter();

        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
