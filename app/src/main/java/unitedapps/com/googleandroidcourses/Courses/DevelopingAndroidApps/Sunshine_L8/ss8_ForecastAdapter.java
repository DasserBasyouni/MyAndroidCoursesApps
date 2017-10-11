package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L8;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
 * {@link ss8_ForecastAdapter} exposes a list of weather forecasts
 * from a {@link Cursor} to a {@link android.widget.ListView}.
 */
public class ss8_ForecastAdapter extends CursorAdapter {
    private static final int ss8_VIEW_TYPE_TODAY = 0;
    private static final int ss8_VIEW_TYPE_FUTURE_DAY = 1;
    private boolean mUseTodayLayout = true;

    private static class ss8_ViewHolder {
        final ImageView iconView;
        final TextView dateView;
        final TextView descriptionView;
        final TextView highTempView;
        final TextView lowTempView;

        ss8_ViewHolder(View view) {
            iconView = (ImageView) view.findViewById(R.id.ss8_detail_icon);
            dateView = (TextView) view.findViewById(R.id.ss8_detail_date_tv);
            descriptionView = (TextView) view.findViewById(R.id.ss8_detail_forecast_tv);
            highTempView = (TextView) view.findViewById(R.id.ss8_detail_high_tv);
            lowTempView = (TextView) view.findViewById(R.id.ss8_detail_low_tv);
        }
    }

    ss8_ForecastAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    private String formatHighLows(double high, double low) {
        boolean isMetric = ss8_Utility.ss8_isMetric(mContext);
        return ss8_Utility.ss8_formatTemperature(mContext, high, isMetric) + "/" + ss8_Utility.ss8_formatTemperature(mContext, low, isMetric);
    }

    /**
        This is ported from ss9_FetchWeatherTask --- but now we go straight from the cursor to the
        string.
     */
    private String convertCursorRowToUXFormat(Cursor cursor) {
        String highAndLow = formatHighLows(
                cursor.getDouble(ss8_ForecastFragment.ss8_COL_WEATHER_MAX_TEMP),
                cursor.getDouble(ss8_ForecastFragment.ss8_COL_WEATHER_MIN_TEMP));

        return ss8_Utility.ss8_formatDate(cursor.getLong(ss8_ForecastFragment.ss8_COL_WEATHER_DATE)) +
                cursor.getString(ss8_ForecastFragment.ss8_COL_WEATHER_DESC) +
                highAndLow;
    }

    /**
        Remember that these views are reused as needed.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int viewType = getItemViewType(cursor.getPosition());
        int layoutId = -1;
        if(viewType == ss8_VIEW_TYPE_TODAY){
            layoutId = R.layout.ss8_list_item_forecast_today;
        }else if(viewType == ss8_VIEW_TYPE_FUTURE_DAY){
            layoutId = R.layout.ss8_list_item_forecast;
        }
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        view.setTag(new ss8_ViewHolder(view));
        return view;
    }

    /**
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ss8_ViewHolder viewHolder = (ss8_ViewHolder) view.getTag();
        Log.i("Z_", "here 2");
        Log.i("Z_", String.valueOf(viewHolder.dateView));
        viewHolder.dateView.setText(ss8_Utility.ss8_getFriendlyDayString(context, cursor.getLong(ss8_ForecastFragment.ss8_COL_WEATHER_DATE)));
        viewHolder.descriptionView.setText(cursor.getString(ss8_ForecastFragment.ss8_COL_WEATHER_DESC));
        viewHolder.highTempView.setText(ss8_Utility.ss8_formatTemperature(mContext, cursor.getDouble(ss8_ForecastFragment.ss8_COL_WEATHER_MAX_TEMP), ss8_Utility.ss8_isMetric(context)));
        viewHolder.lowTempView.setText(ss8_Utility.ss8_formatTemperature(mContext, cursor.getDouble(ss8_ForecastFragment.ss8_COL_WEATHER_MIN_TEMP), ss8_Utility.ss8_isMetric(context)));

        int viewType = getItemViewType(cursor.getPosition());
        switch (viewType) {
            case ss8_VIEW_TYPE_TODAY: {
                viewHolder.iconView.setImageResource(ss8_Utility.ss8_getWeatherConditionImage(cursor.getInt(9), false, context));
                break;
            }
            case ss8_VIEW_TYPE_FUTURE_DAY: {
                viewHolder.iconView.setImageResource(ss8_Utility.ss8_getWeatherConditionImage(cursor.getInt(9), true, context));
                break;
            }
        }
    }


    public void setUseTodayLayout(boolean yesOrNo) {
        mUseTodayLayout = yesOrNo;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 && mUseTodayLayout) ? ss8_VIEW_TYPE_TODAY : ss8_VIEW_TYPE_FUTURE_DAY;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        Log.i("Z_", "ss8: count " + super.getCount());
        return super.getCount();
    }
}