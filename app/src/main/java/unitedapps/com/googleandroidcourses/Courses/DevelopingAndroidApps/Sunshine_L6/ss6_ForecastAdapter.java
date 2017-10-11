package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
 * {@link ss6_ForecastAdapter} exposes a list of weather forecasts
 * from a {@link android.database.Cursor} to a {@link android.widget.ListView}.
 */
public class ss6_ForecastAdapter extends CursorAdapter {
    public ss6_ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    private String formatHighLows(double high, double low) {
        boolean isMetric = Utility.ss6_isMetric(mContext);
        String highLowStr = Utility.ss6_formatTemperature(high, isMetric) + "/" + Utility.ss6_formatTemperature(low, isMetric);
        return highLowStr;
    }

    /**
        This is ported from ss9_FetchWeatherTask --- but now we go straight from the cursor to the
        string.
     */
    private String convertCursorRowToUXFormat(Cursor cursor) {
        String highAndLow = formatHighLows(
                cursor.getDouble(ss6_ForecastFragment.ss6_COL_WEATHER_MAX_TEMP),
                cursor.getDouble(ss6_ForecastFragment.ss6_COL_WEATHER_MIN_TEMP));

        return Utility.ss6_formatDate(cursor.getLong(ss6_ForecastFragment.ss6_COL_WEATHER_DATE)) +
                cursor.getString(ss6_ForecastFragment.ss6_COL_WEATHER_DESC) +
                highAndLow;
    }

    /**
        Remember that these views are reused as needed.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.ss_app_list_item_forecast, parent, false);

        return view;
    }

    /**
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // our view is pretty simple here --- just a text view
        // we'll keep the UI functional with a simple (and slow!) binding.

        TextView tv = (TextView)view;
        tv.setText(convertCursorRowToUXFormat(cursor));
    }

    @Override
    public int getCount() {
        Log.i("Z_", "ss6: count " + super.getCount());
        return super.getCount();
    }
}