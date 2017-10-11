package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9.data.ss9_WeatherContract;
import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9.sync.ss9_Service;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Dasser on 06-Jun-17.
*/

public class ss9_ForecastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ss9_FORECAST_LOADER_ID = 0;
    ss9_ForecastAdapter mForecastAdapter;
    boolean mUseTodayLayout;

    private static final String[] ss9_FORECAST_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME + "." + ss9_WeatherContract.ss9_WeatherEntry._ID,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_SHORT_DESC,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_MAX_TEMP,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_MIN_TEMP,
            ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_LOCATION_SETTING,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_WEATHER_ID,
            ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_COORD_LAT,
            ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_COORD_LONG,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_WEATHER_ID
    };

    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    static final int ss9_COL_WEATHER_ID = 0;
    static final int ss9_COL_WEATHER_DATE = 1;
    static final int ss9_COL_WEATHER_DESC = 2;
    static final int ss9_COL_WEATHER_MAX_TEMP = 3;
    static final int ss9_COL_WEATHER_MIN_TEMP = 4;
    static final int ss9_COL_LOCATION_SETTING = 5;
    static final int ss9_COL_WEATHER_CONDITION_ID = 6;
    static final int ss9_COL_COORD_LAT = 7;
    static final int ss9_COL_COORD_LONG = 8;

    private int mPosition = ListView.INVALID_POSITION;

    private static final String ss9_SELECTED_KEY = "selected_position";

    public ss9_ForecastFragment() {}

    public void setUseTodayLayout(boolean useTodayLayout) {
        mUseTodayLayout = useTodayLayout;
        if (mForecastAdapter != null) {
            mForecastAdapter.setUseTodayLayout(mUseTodayLayout);
        }
    }

    interface ss9_Callback {
        void onItemSelected(Uri dateUri);
    }

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mForecastAdapter = new ss9_ForecastAdapter(getContext(), null, 0);
        View rootView = inflater.inflate(R.layout.ss9_activity_main_list, container, false);
        setHasOptionsMenu(true);

        listView = (ListView) rootView.findViewById(R.id.ss9_listview_forecast);
        listView.setAdapter(mForecastAdapter);
        Log.i("Z_1", String.valueOf(listView));
        Log.i("Z_2", String.valueOf(mForecastAdapter));
        Log.i("Z_3", String.valueOf(listView.getAdapter()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
                    String locationSetting = ss9_Utility.ss9_getPreferredLocation(getActivity());

                    ((ss9_Callback) getActivity()).onItemSelected(ss9_WeatherContract.ss9_WeatherEntry.ss9_buildWeatherLocationWithDate(
                            locationSetting, cursor.getLong(ss9_COL_WEATHER_DATE)));
                    mPosition = position;
                }
            }
        });
        if (savedInstanceState != null && savedInstanceState.containsKey(ss9_SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(ss9_SELECTED_KEY);
        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.ss3_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ss3_action_refresh:
                updateWeather();
                return true;
            case R.id.ss3_action_settings:
                startActivity(new Intent(getActivity(), ss9_SettingsActivity.class));
                return true;
            case R.id.ss3_action_map:
                String location = PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .getString(getString(R.string.pref_units_key),
                                getString(R.string.pref_temp_default));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                        .appendQueryParameter("q", location).build();
                intent.setData(geoLocation);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "There is no Map app", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("Z_", String.valueOf(getLoaderManager().initLoader(ss9_FORECAST_LOADER_ID, null, this)));
        super.onActivityCreated(savedInstanceState);
    }

    void onLocationChanged( ) {
        updateWeather();
        getLoaderManager().restartLoader(ss9_FORECAST_LOADER_ID, null, this);
    }

    private void updateWeather() {
        Log.i("ZTT_", "start updating weather");
        /*s9_SyncAdapter.ss9_SyncImmediately(getContext());
        Bundle b = new Bundle();
        b.putString(ss9_LOCATION_QUERY_EXTRA, ss9_Utility.ss9_getPreferredLocation(getActivity()));
        ContentResolver.requestSync(pm2_SyncAdapter.getSyncAccount(getContext()), getString(R.string.ss9_content_authority), b);
        getLoaderManager().restartLoader(ss9_FORECAST_LOADER_ID, null, this);*/

        Intent serviceIntent = new Intent(getContext(), ss9_Service.class);
        serviceIntent.putExtra(ss9_Service.ss9_LOCATION_QUERY_EXTRA, ss9_Utility.ss9_getPreferredLocation(getActivity()));
        getContext().startService(serviceIntent);

        /*AlarmManager alarmMgr;
        PendingIntent alarmIntent;



        alarmMgr = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), ss9_Service.ss9_AlarmReceiver.class);
        intent.putExtra("preferredLocation", ss9_Utility.ss9_getPreferredLocation(getActivity()));
        alarmIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        Log.i("ZTT_", "i am here");
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                //SystemClock.elapsedRealtime() +
                        1, alarmIntent);

        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                //SystemClock.elapsedRealtime() +
                        5000,         // 5 sec
                60000, alarmIntent);  // 60 sec*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(ss9_SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String locationSetting = ss9_Utility.ss9_getPreferredLocation(getActivity());
        String sortOrder = ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE + " ASC";
        Uri weatherForLocationUri = ss9_WeatherContract.ss9_WeatherEntry.ss9_buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());
        return new CursorLoader(
                getActivity(),
                weatherForLocationUri,
                ss9_FORECAST_COLUMNS,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        mForecastAdapter.swapCursor(cursor);
       if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            listView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mForecastAdapter.swapCursor(null);
    }

}