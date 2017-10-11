/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L8;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import unitedapps.com.googleandroidcourses.R;

public class ss8_Utility {

    public static String ss8_getPreferredLocation(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_location_key),
                context.getString(R.string.pref_location_default));
    }

    public static boolean ss8_isMetric(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_units_key),
                context.getString(R.string.pref_units_metric))
                .equals(context.getString(R.string.pref_units_metric));
    }

    static String ss8_formatTemperature(Context context, double temperature, boolean isMetric) {
        double temp;
        if ( !isMetric ) {
            temp = 9*temperature/5+32;
        } else {
            temp = temperature;
        }
        return context.getString(R.string.format_temperature, temp);
    }

    static String ss8_formatDate(long dateInMillis) {
        Date date = new Date(dateInMillis);
        return DateFormat.getDateInstance().format(date);
    }

    // Format used for storing dates in the database.  ALso used for converting those strings
    // back into date objects for comparison/processing.
    public static final String ss8_DATE_FORMAT = "yyyyMMdd";

    /**
     * Helper method to convert the database representation of the date into something to display
     * to users.  As classy and polished a user experience as "20140102" is, we can do better.
     *
     * @param context Context to use for resource localization
     * @param dateInMillis The date in milliseconds
     * @return a user-friendly representation of the date.
     */
    public static String ss8_getFriendlyDayString(Context context, long dateInMillis) {
        // The day string for forecast uses the following logic:
        // For today: "Today, June 8"
        // For tomorrow:  "Tomorrow"
        // For the next 5 days: "Wednesday" (just the day name)
        // For all days after that: "Mon Jun 8"

        Time time = new Time();
        time.setToNow();
        long currentTime = System.currentTimeMillis();
        int julianDay = Time.getJulianDay(dateInMillis, time.gmtoff);
        int currentJulianDay = Time.getJulianDay(currentTime, time.gmtoff);

        // If the date we're building the String for is today's date, the format
        // is "Today, June 24", by me: today
        if (julianDay == currentJulianDay) {
            String today = context.getString(R.string.today);
            int formatId = R.string.format_full_friendly_date;
            return context.getString(
                    formatId,
                    today,
                    ss8_getFormattedMonthDay(context, dateInMillis));
        } else if ( julianDay < currentJulianDay + 7 ) {
            // If the input date is less than a week in the future, just return the day name. by me: this week
            return ss8_getDayName(context, dateInMillis);
        } else {
            // Otherwise, use the form "Mon Jun 3" by me: more than this week
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(dateInMillis);
        }
    }

    /**
     * Given a day, returns just the name to use for that day.
     * E.g "today", "tomorrow", "wednesday".
     *
     * @param context Context to use for resource localization
     * @param dateInMillis The date in milliseconds
     * @return
     */
    public static String ss8_getDayName(Context context, long dateInMillis) {
        // If the date is today, return the localized version of "Today" instead of the actual
        // day name.

        Time t = new Time();
        t.setToNow();
        int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
        int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
        if (julianDay == currentJulianDay) {
            return context.getString(R.string.today);
        } else if ( julianDay == currentJulianDay +1 ) {
            return context.getString(R.string.tomorrow);
        } else {
            Time time = new Time();
            time.setToNow();
            // Otherwise, the format is just the day of the week (e.g "Wednesday".
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(dateInMillis);
        }
    }

    /**
     * Converts db date format to the format "Month day", e.g "June 24".
     * @param context Context to use for resource localization
     * @param dateInMillis The db formatted date string, expected to be of the form specified
     *                in Utility.DATE_FORMAT
     * @return The day in the form of a string formatted "December 6"
     */
    public static String ss8_getFormattedMonthDay(Context context, long dateInMillis ) {
        Time time = new Time();
        time.setToNow();
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(ss8_Utility.ss8_DATE_FORMAT);
        SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMMM dd");
        String monthDayString = monthDayFormat.format(dateInMillis);
        return monthDayString;
    }

    public static String ss8_getFormattedWind(Context context, float windSpeed, float degrees) {
        int windFormat;
        if (ss8_Utility.ss8_isMetric(context)) {
            windFormat = R.string.format_wind_kmh;
        } else {
            windFormat = R.string.format_wind_mph;
            windSpeed = .621371192237334f * windSpeed;
        }

        String[] directions = new String[] {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        String direction = directions[Math.round(degrees / 45) % 8];

        return String.format(context.getString(windFormat), windSpeed, direction);
    }

    static String ss8_getFormattedHumidity(Context context, float f) {
        return String.format(context.getString(R.string.format_pressure), f);
    }

    static String ss8_getFormattedPressure(Context context, float f) {
        return String.format(context.getString(R.string.format_pressure), f);
    }

    /**
     * Helper method to provide the icon or art resource id according to the weather condition id
     * returned by the OpenWeatherMap call.
     * @param weatherId from OpenWeatherMap API response
     * @return resource id for the corresponding icon or art, or ic_launcher if no relation is found.
     */
    public static int ss8_getWeatherConditionImage(int weatherId, boolean isIcon, Context context) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        Log.i("Z_", String.valueOf(weatherId));
        String resourceName = "ic_";
        if (!isIcon) {
            resourceName = "art_";
        }
        if (weatherId >= 200 && weatherId <= 232) {
            resourceName += "storm";
        } else if (weatherId >= 300 && weatherId <= 321) {
            resourceName += "light_rain";
        } else if (weatherId >= 500 && weatherId <= 504) {
            resourceName += "rain";
        } else if (weatherId == 511) {
            resourceName += "snow";
        } else if (weatherId >= 520 && weatherId <= 531) {
            resourceName += "rain";
        } else if (weatherId >= 600 && weatherId <= 622) {
            resourceName += "snow";
        } else if (weatherId >= 701 && weatherId <= 761) {
            resourceName += "fog";
        } else if (weatherId == 761 || weatherId == 781) {
            resourceName += "storm";
        } else if (weatherId == 800) {
            resourceName += "clear";
        } else if (weatherId == 801) {
            resourceName += "light_clouds";
        } else if (weatherId >= 802 && weatherId <= 804) {
            resourceName += "clouds";
        } else {
            resourceName = "ss8_ic_launcher";
        }

        Log.i("Z_rn", resourceName);
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

}