package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.Arrays;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.data.pm2_Contract;
import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.sync.pm2_Service;
import unitedapps.com.googleandroidcourses.R;

public class pm2_MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    int positionOfSortSpinner;
    GridView gridview;
    Uri mUri;
    int[] IDs;
    private static final int pm2_MAIN_LOADER_ID = 1;
    private static final String SELECTED_KEY = "selected_position";
    private int mPosition = GridView.INVALID_POSITION;


    static final String[] pm2_Main_Movies_COLUMNS = {
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_POSTER,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_YEAR,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_RATE,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_LENGTH,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_SYS,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_FAV,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_NAME,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_REVIEWS_A,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_REVIEWS_C,
            pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_TRAILERS
    };

    static final int pm2_COLUMN_MOVIE_POSTER = 0;
    static final int pm2_COLUMN_MOVIE_ID = 1;
    static final int pm2_COLUMN_MOVIE_YEAR = 2;
    static final int pm2_COLUMN_MOVIE_RATE = 3;
    static final int pm2_COLUMN_MOVIE_LENGTH = 4;
    static final int pm2_COLUMN_MOVIE_SYS = 5;
    static final int pm2_COLUMN_MOVIE_FAV = 6;
    static final int pm2_COLUMN_MOVIE_NAME = 7;
    static final int pm2_COLUMN_MOVIE_REVIEWS_A = 8;
    static final int pm2_COLUMN_MOVIE_REVIEWS_C = 9;
    static final int pm2_COLUMN_MOVIE_TRAILERS = 10;

    SharedPreferences sharedPref;
    LoaderManager.LoaderCallbacks loaderCallback;
    Cursor cursor;
    IntentFilter filter;
    BroadcastReceiver receiver;
    boolean twoPanes;
    pm2_DetailsFragment fragment = null;
    int gridColNub;

    @Override
    public void onStart() {
        filter = new IntentFilter("com.your_package.ANY_TEXT_STRING");
        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("Z_", "recived yea");
                restartLoader();
            }
        };
        getActivity().registerReceiver(receiver, filter);
        Log.i("Z_test", "yea its runed again");
        getActivity().getSupportLoaderManager().initLoader(pm2_MAIN_LOADER_ID, null, this);
        if (twoPanes) {
            fragment = (pm2_DetailsFragment) getFragmentManager().findFragmentByTag("DETAILS_FRAGMENT_TAG");
            fragment.restartLoader(1);
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration config = getResources().getConfiguration();
        gridColNub = 2;
        if (config.smallestScreenWidthDp >= 600 || config.orientation == 2) {
            gridColNub = 3;
        }
        gridview.setNumColumns(gridColNub);
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderCallback = this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != GridView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.getBundle("oBundle");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.pm2_activity_main, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            twoPanes = bundle.getBoolean("2p");
        }

        gridview = rootView.findViewById(R.id.gridview);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                positionOfSortSpinner = sharedPref.getInt(getString(R.string.pm2_savedSortMode), 0);
                Log.i("Z_ poss", String.valueOf(positionOfSortSpinner));
                if (!twoPanes) {
                    Log.i("Z_id here =", String.valueOf(IDs[position]));
                    Log.i("Z_ids here =", Arrays.toString(IDs));
                    Intent intent = new Intent(getActivity(), pm2_DetailsActivity.class);
                    intent.putExtra("id", String.valueOf(IDs[position]));
                    startActivity(intent);
                } else {
                    fragment.restartLoader(IDs[position]);
                }
                mPosition = position;

            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }


        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.pm2_menu, menu);

        MenuItem item = menu.findItem(R.id.pm2_sort_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.pm2_sort_spinner_list_item, R.layout.pm2_spinner_item);
        adapter.setDropDownViewResource(R.layout.pm2_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //positionOfSortSpinner = sharedPref.getInt(getString(R.string.pm2_savedSortMode), 0);

        spinner.setSelection(positionOfSortSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.pm2_savedSortMode), position);
                editor.apply();
                if (position != 2) {
                    Log.i("Z_", "Service Launched ...");
                    Intent serviceIntent = new Intent(getContext(), pm2_Service.class);
                    getContext().startService(serviceIntent);
                }
                restartLoader();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void restartLoader() {
        getActivity().getSupportLoaderManager().restartLoader(pm2_MAIN_LOADER_ID, null, loaderCallback);
    }

    public BroadcastReceiver test() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("Z_", "FIREEEED test");
                restartLoader();
            }
        };
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.v("Z_pm2", "In onCreateLoader");
        mUri = pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100);

        String cSelection = null;
        String[] cSelectionArg = null;

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        positionOfSortSpinner = sharedPref.getInt(getString(R.string.pm2_savedSortMode), 0);
        if (positionOfSortSpinner == 0) {
            cSelection = pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_RATED + "=?";
            cSelectionArg = new String[]{"0"};

        } else if (positionOfSortSpinner == 1) {
            cSelection = pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_RATED + "=?";
            cSelectionArg = new String[]{"1"};

        } else if (positionOfSortSpinner == 2) {
            cSelection = pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_FAV + "=?";
            cSelectionArg = new String[]{"1"};
        }

        if (mUri != null) {
            return new CursorLoader(
                    getContext(),
                    mUri,
                    pm2_Main_Movies_COLUMNS,
                    cSelection,
                    cSelectionArg,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.v("Z_pm2_MainActivity", "In onLoadFinished");

        this.cursor = cursor;
        Bitmap[] bitmaps;
        bitmaps = new Bitmap[cursor.getCount()];

            if (cursor.moveToFirst() && !cursor.isClosed()) {
                int i = 0;
                IDs = new int[cursor.getCount()];
                do {
                    IDs[i] = cursor.getInt(pm2_COLUMN_MOVIE_ID);
                    byte[] data = cursor.getBlob(pm2_COLUMN_MOVIE_POSTER);
                    bitmaps[i] = pm2_Utils.getImageFromByteArray(data);
                    i++;
                } while (cursor.moveToNext() && i < cursor.getCount());
            }
        gridview.setAdapter(new pm2_MoviesAdapter(getActivity(), bitmaps));

        if (mPosition != GridView.INVALID_POSITION) {
            gridview.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("Z_", "reset is called");
    }
}
