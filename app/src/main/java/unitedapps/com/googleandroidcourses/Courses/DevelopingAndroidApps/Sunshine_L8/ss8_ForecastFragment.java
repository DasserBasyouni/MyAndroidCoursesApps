package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L8;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L8.data.ss8_WeatherContract;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Dasser on 06-Jun-17.
*/

public class ss8_ForecastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ss8_FORECAST_LOADER = 0;
    private static  final int ss8_MY_LOADER_ID = 123;
    ss8_ForecastAdapter mForecastAdapter;
    boolean mUseTodayLayout;

    private static final String[] ss8_FORECAST_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ss8_WeatherContract.ss8_WeatherEntry.ss8_TABLE_NAME + "." + ss8_WeatherContract.ss8_WeatherEntry._ID,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_DATE,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_SHORT_DESC,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_MAX_TEMP,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_MIN_TEMP,
            ss8_WeatherContract.ss8_LocationEntry.ss8_COLUMN_LOCATION_SETTING,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_WEATHER_ID,
            ss8_WeatherContract.ss8_LocationEntry.ss8_COLUMN_COORD_LAT,
            ss8_WeatherContract.ss8_LocationEntry.ss8_COLUMN_COORD_LONG,
            ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_WEATHER_ID
    };

    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    static final int ss8_COL_WEATHER_ID = 0;
    static final int ss8_COL_WEATHER_DATE = 1;
    static final int ss8_COL_WEATHER_DESC = 2;
    static final int ss8_COL_WEATHER_MAX_TEMP = 3;
    static final int ss8_COL_WEATHER_MIN_TEMP = 4;
    static final int ss8_COL_LOCATION_SETTING = 5;
    static final int ss8_COL_WEATHER_CONDITION_ID = 6;
    static final int ss8_COL_COORD_LAT = 7;
    static final int ss8_COL_COORD_LONG = 8;

    private int mPosition = ListView.INVALID_POSITION;

    private static final String ss8_SELECTED_KEY = "selected_position";

    public ss8_ForecastFragment() {
    }

    public void setUseTodayLayout(boolean useTodayLayout) {
        mUseTodayLayout = useTodayLayout;
        if (mForecastAdapter != null) {
            mForecastAdapter.setUseTodayLayout(mUseTodayLayout);
        }
    }

    interface ss8_Callback {
        void onItemSelected(Uri dateUri);
    }

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mForecastAdapter = new ss8_ForecastAdapter(getContext(), null, 0);
        View rootView = inflater.inflate(R.layout.ss8_activity_main_list, container, false);
        setHasOptionsMenu(true);

        /*Log.i("Z_1", String.valueOf(rootView.findViewById(R.id.ss8_listview_forecast) == null));
        Log.i("Z_2", String.valueOf(rootView.findViewById(R.id.ss8_listview_forecast)));
        Log.i("Z_3", String.valueOf(mForecastAdapter));
        Log.i("Z_4", String.valueOf(mForecastAdapter));*/
        listView = (ListView) rootView.findViewById(R.id.ss8_listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
                    String locationSetting = ss8_Utility.ss8_getPreferredLocation(getActivity());

                    ((ss8_Callback) getActivity()).onItemSelected(ss8_WeatherContract.ss8_WeatherEntry.ss8_buildWeatherLocationWithDate(
                            locationSetting, cursor.getLong(ss8_COL_WEATHER_DATE)));
                    mPosition = position;
                }
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(ss8_SELECTED_KEY)) {
            mPosition = savedInstanceState.getInt(ss8_SELECTED_KEY);
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
                new ss8_FetchWeatherTask(getContext()).execute(PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .getString(getString(R.string.pref_units_key),
                                getString(R.string.pref_temp_default)));
                return true;
            case R.id.ss3_action_settings:
                startActivity(new Intent(getActivity(), ss8_SettingsActivity.class));
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
        //new ss9_FetchWeatherTask(getContext()).execute(ss9_Utility.ss8_getPreferredLocation(getActivity()));
        getLoaderManager().initLoader(ss8_MY_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        //new ss9_FetchWeatherTask(getContext()).execute(ss9_Utility.ss8_getPreferredLocation(getActivity()));
        super.onStart();
    }

    void onLocationChanged( ) {
        updateWeather();
        getLoaderManager().restartLoader(ss8_FORECAST_LOADER, null, this);
    }

    private void updateWeather() {
        ss8_FetchWeatherTask weatherTask = new ss8_FetchWeatherTask(getActivity());
        String location = ss8_Utility.ss8_getPreferredLocation(getActivity());
        weatherTask.execute(location);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // When tablets rotate, the currently selected list item needs to be saved.
        // When no item is selected, mPosition will be set to Listview.INVALID_POSITION,
        // so check for that before storing.
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(ss8_SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String locationSetting = ss8_Utility.ss8_getPreferredLocation(getActivity());
        String sortOrder = ss8_WeatherContract.ss8_WeatherEntry.ss8_COLUMN_DATE + " ASC";

        Uri weatherForLocationUri = ss8_WeatherContract.ss8_WeatherEntry.ss8_buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        return new CursorLoader(
                getActivity(),
                weatherForLocationUri,
                ss8_FORECAST_COLUMNS,
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