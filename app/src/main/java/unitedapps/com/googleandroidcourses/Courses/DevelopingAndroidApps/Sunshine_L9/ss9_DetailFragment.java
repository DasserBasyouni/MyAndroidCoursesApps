package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9.data.ss9_WeatherContract;
import unitedapps.com.googleandroidcourses.R;

/**
 * A placeholder fragment containing a simple view.
 */

public class ss9_DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String ss9_LOG_TAG = ss9_DetailFragment.class.getSimpleName();

    private static final String ss9_FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private ShareActionProvider mShareActionProvider;
    private String mForecast;
    ss9_WindView ss9_WindView;

    private static final int ss9_DETAIL_LOADER_ID = 0;

    private static final String[] ss9_DETAILS_COLUMNS = {
            ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME + "." + ss9_WeatherContract.ss9_WeatherEntry._ID,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_SHORT_DESC,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_MAX_TEMP,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_MIN_TEMP,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_HUMIDITY,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_PRESSURE,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_WIND_SPEED,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DEGREES,
            ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_WEATHER_ID,
            ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_LOCATION_SETTING
    };

    // these constants correspond to the projection defined above, and must change if the
    // projection changes
    private static final int ss9_COL_WEATHER_ID = 0;
    private static final int ss9_COL_WEATHER_DATE = 1;
    private static final int ss9_COL_WEATHER_DESC = 2;
    private static final int ss9_COL_WEATHER_MAX_TEMP = 3;
    private static final int ss9_COL_WEATHER_MIN_TEMP = 4;
    static final int ss9_COLUMN_HUMIDITY = 5;
    static final int ss9_COLUMN_PRESSURE = 6;
    static final int ss9_COLUMN_WIND_SPEED = 7;
    static final int ss9_COLUMN_DEGREES = 8;
    static final int ss9_COL_WEATHER_CONDITION_ID = 9;

    public ss9_DetailFragment() {
        setHasOptionsMenu(true);
    }

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    ImageView iv;
    Uri mUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle b = getArguments();
        if(b != null){
            Log.i("Zx_", b.getString("uri"));
            mUri = Uri.parse(b.getString("uri"));
        }

        View view = inflater.inflate(R.layout.ss9_fragment_detail, container, false);
        tv1 = (TextView) view.findViewById(R.id.ss9_detail_low_tv);
        tv2 = (TextView) view.findViewById(R.id.ss9_detail_forecast_tv);
        tv3 = (TextView) view.findViewById(R.id.ss9_detail_date_tv);
        tv4 = (TextView) view.findViewById(R.id.ss9_detail_high_tv);
        tv5 = (TextView) view.findViewById(R.id.ss9_detail_humidity_tv);
        tv6 = (TextView) view.findViewById(R.id.ss9_detail_pressure_tv);
        tv7 = (TextView) view.findViewById(R.id.ss9_detail_wind_tv);
        iv = (ImageView) view.findViewById(R.id.ss9_detail_icon);
        ss9_WindView = (ss9_WindView) view.findViewById(R.id.ss9_WindView);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.ss3_details, menu); //details fragment not details

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.ss3_action_share);

        // Get the provider and hold onto it to set/change the share intent.
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // If onLoadFinished happens before this, we can go ahead and set the share intent now.
        if (mForecast != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        }
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecast + ss9_FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(ss9_DETAIL_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    void onLocationChanged( String newLocation ) {
        // replace the uri, since the location has changed
        Uri uri = mUri;
        if (null != uri) {
            long date = ss9_WeatherContract.ss9_WeatherEntry.ss9_getDateFromUri(uri);
            Uri updatedUri = ss9_WeatherContract.ss9_WeatherEntry.ss9_buildWeatherLocationWithDate(newLocation, date);
            mUri = updatedUri;
            getLoaderManager().restartLoader(ss9_DETAIL_LOADER_ID, null, this);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.v(ss9_LOG_TAG, "In onCreateLoader");
        if(mUri != null) {
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    ss9_DETAILS_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.v(ss9_LOG_TAG, "In onLoadFinished");
        if (data != null && data.moveToFirst()) {

            boolean isMetric = ss9_Utility.ss9_isMetric(getActivity());
            String dateString = ss9_Utility.ss9_formatDate(data.getLong(ss9_COL_WEATHER_DATE));
            String weatherDescription = data.getString(ss9_COL_WEATHER_DESC);
            String high = ss9_Utility.ss9_formatTemperature(getContext(), data.getDouble(ss9_COL_WEATHER_MAX_TEMP), isMetric);
            String low = ss9_Utility.ss9_formatTemperature(getContext(), data.getDouble(ss9_COL_WEATHER_MIN_TEMP), isMetric);
            mForecast = String.format("%s - %s - %s/%s", dateString, weatherDescription, high, low);


            tv1.setText(ss9_Utility.ss9_formatTemperature(getContext(), data.getDouble(ss9_COL_WEATHER_MIN_TEMP), isMetric));
            tv2.setText(data.getString(ss9_COL_WEATHER_DESC));
            tv3.setText(ss9_Utility.ss9_formatDate(data.getLong(ss9_COL_WEATHER_DATE)));
            tv4.setText(ss9_Utility.ss9_formatTemperature(getContext(), data.getDouble(ss9_COL_WEATHER_MAX_TEMP), isMetric));
            tv5.setText(ss9_Utility.ss9_getFormattedHumidity(getContext(), data.getFloat(ss9_COLUMN_HUMIDITY)));
            tv6.setText(ss9_Utility.ss9_getFormattedPressure(getContext(), data.getFloat(ss9_COLUMN_PRESSURE)));
            tv7.setText(ss9_Utility.ss9_getFormattedWind(getContext(), data.getFloat(ss9_COLUMN_WIND_SPEED), data.getFloat(ss9_COLUMN_DEGREES)));
            iv.setImageResource(ss9_Utility.ss9_getWeatherConditionImage(data.getInt(ss9_COL_WEATHER_CONDITION_ID), false, getContext()));

            ss9_WindView.setWind(data.getFloat(ss9_COLUMN_WIND_SPEED),data.getFloat(ss9_COLUMN_DEGREES));

            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}