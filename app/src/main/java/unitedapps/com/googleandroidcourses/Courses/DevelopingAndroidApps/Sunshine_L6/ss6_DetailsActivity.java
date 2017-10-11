package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6.data.ss6_WeatherContract;
import unitedapps.com.googleandroidcourses.R;


public class ss6_DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss3_details_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ss3_container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ss3_action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment implements LoaderCallbacks<Cursor> {

        private static final String ss6_LOG_TAG = DetailFragment.class.getSimpleName();

        private static final String ss6_FORECAST_SHARE_HASHTAG = " #SunshineApp";

        private ShareActionProvider mShareActionProvider;
        private String mForecast;

        private static final int ss6_DETAIL_LOADER = 0;

        private static final String[] ss6_FORECAST_COLUMNS = {
                ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME + "." + ss6_WeatherContract.ss6_WeatherEntry._ID,
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE,
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_SHORT_DESC,
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MAX_TEMP,
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MIN_TEMP,
        };

        // these constants correspond to the projection defined above, and must change if the
        // projection changes
        private static final int ss6_COL_WEATHER_ID = 0;
        private static final int ss6_COL_WEATHER_DATE = 1;
        private static final int ss6_COL_WEATHER_DESC = 2;
        private static final int ss6_COL_WEATHER_MAX_TEMP = 3;
        private static final int ss6_COL_WEATHER_MIN_TEMP = 4;

        public DetailFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.ss3_fragment_detail, container, false);
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
            shareIntent.putExtra(Intent.EXTRA_TEXT, mForecast + ss6_FORECAST_SHARE_HASHTAG);
            return shareIntent;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            getLoaderManager().initLoader(ss6_DETAIL_LOADER, null, this);
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            Log.v(ss6_LOG_TAG, "In onCreateLoader");
            Intent intent = getActivity().getIntent();
            if (intent == null) {
                return null;
            }

            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    intent.getData(),
                    ss6_FORECAST_COLUMNS,
                    null,
                    null,
                    null
            );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.v(ss6_LOG_TAG, "In onLoadFinished");
            if (!data.moveToFirst()) {
                return;
            }

            String dateString = Utility.ss6_formatDate(
                    data.getLong(ss6_COL_WEATHER_DATE));

            String weatherDescription =
                    data.getString(ss6_COL_WEATHER_DESC);

            boolean isMetric = Utility.ss6_isMetric(getActivity());

            String high = Utility.ss6_formatTemperature(
                    data.getDouble(ss6_COL_WEATHER_MAX_TEMP), isMetric);

            String low = Utility.ss6_formatTemperature(
                    data.getDouble(ss6_COL_WEATHER_MIN_TEMP), isMetric);

            mForecast = String.format("%s - %s - %s/%s", dateString, weatherDescription, high, low);

            TextView detailTextView = (TextView) getView().findViewById(R.id.ss3_data_tv);
            detailTextView.setText(mForecast);

            // If onCreateOptionsMenu has already happened, we need to update the share intent now.
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    }
}