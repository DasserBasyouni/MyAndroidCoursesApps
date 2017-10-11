package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6.data.ss6_WeatherContract;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Dasser on 06-Jun-17.
*/

public class ss6_ForecastFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static  final int ss6_MY_LOADER_ID = 123;
    CursorAdapter mForecastAdapter;
    static final String[] ss6_FORECAST_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME + "." + ss6_WeatherContract.ss6_WeatherEntry._ID,
            ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE,
            ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_SHORT_DESC,
            ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MAX_TEMP,
            ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MIN_TEMP,
            ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING,
            ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_WEATHER_ID,
            ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LAT,
            ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LONG
    };

    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    public static final int ss6_COL_WEATHER_ID = 0;
    public static final int ss6_COL_WEATHER_DATE = 1;
    public static final int ss6_COL_WEATHER_DESC = 2;
    public static final int ss6_COL_WEATHER_MAX_TEMP = 3;
    public static final int ss6_COL_WEATHER_MIN_TEMP = 4;
    public static final int ss6_COL_LOCATION_SETTING = 5;
    public static final int ss6_COL_WEATHER_CONDITION_ID = 6;
    public static final int ss6_COL_COORD_LAT = 7;
    public static final int ss6_COL_COORD_LONG = 8;
    public ss6_ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ss6_activity_main, container, false);
        setHasOptionsMenu(true);

        mForecastAdapter = new ss6_ForecastAdapter(getContext(), null, 0);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.ss6_listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                if (cursor != null) {
                    String locationSetting = Utility.ss6_getPreferredLocation(getActivity());
                    Intent intent = new Intent(getActivity(), ss6_DetailsActivity.class)
                            .setData(ss6_WeatherContract.ss6_WeatherEntry.ss6_buildWeatherLocationWithDate(
                                    locationSetting, cursor.getLong(ss6_COL_WEATHER_DATE)
                            ));
                    startActivity(intent);
                }
            }
        });

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
                new FetchWeatherTask(getContext()).execute(PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .getString(getString(R.string.pref_units_key),
                                getString(R.string.pref_temp_default)));
                return true;
            case R.id.ss3_action_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
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
        new FetchWeatherTask(getContext()).execute(Utility.ss6_getPreferredLocation(getActivity()));
        getLoaderManager().initLoader(ss6_MY_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        new FetchWeatherTask(getContext()).execute(Utility.ss6_getPreferredLocation(getActivity()));
        super.onStart();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String locationSetting = Utility.ss6_getPreferredLocation(getActivity());

        // Sort order:  Ascending, by date.
        String sortOrder = ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE + " ASC";
        Uri weatherForLocationUri = ss6_WeatherContract.ss6_WeatherEntry.ss6_buildWeatherLocationWithStartDate(
                locationSetting, System.currentTimeMillis());

        return new CursorLoader(getActivity(),
                weatherForLocationUri,
                ss6_FORECAST_COLUMNS,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        mForecastAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mForecastAdapter.swapCursor(null);
    }

}