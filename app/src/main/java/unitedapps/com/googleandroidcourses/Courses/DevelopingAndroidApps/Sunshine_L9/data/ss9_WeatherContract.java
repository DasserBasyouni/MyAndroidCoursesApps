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

package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Defines table and column names for the weather database.
 */
public class ss9_WeatherContract {
    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String ss9_CONTENT_AUTHORITY = "unitedapps.com.googleandroidcourses";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + ss9_CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    public static final String ss9_PATH_WEATHER = "weather";
    public static final String ss9_PATH_LOCATION = "location";

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long ss9_normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    /**
        Inner class that defines the table contents of the location table
        Students: This is where you will add the strings.  (Similar to what has been
        done for WeatherEntry)
     */
    public static final class ss9_LocationEntry implements BaseColumns {
        public static final String ss9_TABLE_NAME = "location";
        public static final String ss9_COLUMN_LOCATION_SETTING = "location_settings";
        public static final String ss9_COLUMN_COORD_LAT = "latitude";
        public static final String ss9_COLUMN_COORD_LONG = "longitude";
        public static final String ss9_COLUMN_CITY_NAME = "city_name";

        public static final Uri ss9_CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(ss9_PATH_LOCATION).build();

        public static final String ss9_CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + ss9_CONTENT_AUTHORITY + "/" + ss9_PATH_LOCATION;
        public static final String ss9_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + ss9_CONTENT_AUTHORITY + "/" + ss9_PATH_WEATHER;

        public static Uri ss9_buildLocationUri(long id) {
            return ContentUris.withAppendedId(ss9_CONTENT_URI, id);
        }
    }

    /* Inner class that defines the table contents of the weather table */
    public static final class ss9_WeatherEntry implements BaseColumns {

        public static final String ss9_TABLE_NAME = "weather";

        // Column with the foreign key into the location table.
        public static final String ss9_COLUMN_LOC_KEY = "location_id";
        // Date, stored as long in milliseconds since the epoch
        public static final String ss9_COLUMN_DATE = "date";
        // Weather id as returned by API, to identify the icon to be used
        public static final String ss9_COLUMN_WEATHER_ID = "weather_id";

        // Short description and long description of the weather, as provided by API.
        // e.g "clear" vs "sky is clear".
        public static final String ss9_COLUMN_SHORT_DESC = "short_desc";

        // Min and max temperatures for the day (stored as floats)
        public static final String ss9_COLUMN_MIN_TEMP = "min";
        public static final String ss9_COLUMN_MAX_TEMP = "max";

        // Humidity is stored as a float representing percentage
        public static final String ss9_COLUMN_HUMIDITY = "humidity";

        // Humidity is stored as a float representing percentage
        public static final String ss9_COLUMN_PRESSURE = "pressure";

        // Windspeed is stored as a float representing windspeed  mph
        public static final String ss9_COLUMN_WIND_SPEED = "wind";

        // Degrees are meteorological degrees (e.g, 0 is north, 180 is south).  Stored as floats.
        public static final String ss9_COLUMN_DEGREES = "degrees";
        public static final Uri ss9_CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(ss9_PATH_WEATHER).build();

        public static final String ss9_CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + ss9_CONTENT_AUTHORITY + "/" + ss9_PATH_WEATHER;
        public static final String ss9_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + ss9_CONTENT_AUTHORITY + "/" + ss9_PATH_WEATHER;


        public static Uri ss9_buildWeatherUri(long id) {
            return ContentUris.withAppendedId(ss9_CONTENT_URI, id);
        }

        /*
            Student: Fill in this buildWeatherLocation function
         */
        public static Uri ss9_buildWeatherLocation(String locationSetting) {
            return ss9_CONTENT_URI.buildUpon().appendPath(locationSetting).build();
        }

        public static Uri ss9_buildWeatherLocationWithStartDate(
                String locationSetting, long startDate) {
            long normalizedDate = ss9_normalizeDate(startDate);
            return ss9_CONTENT_URI.buildUpon().appendPath(locationSetting)
                    .appendQueryParameter(ss9_COLUMN_DATE, Long.toString(normalizedDate)).build();
        }

        public static Uri ss9_buildWeatherLocationWithDate(String locationSetting, long date) {
            return ss9_CONTENT_URI.buildUpon().appendPath(locationSetting)
                    .appendPath(Long.toString(ss9_normalizeDate(date))).build();
        }

        public static String ss9_getLocationSettingFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long ss9_getDateFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(2));
        }

        public static long ss9_getStartDateFromUri(Uri uri) {
            String dateString = uri.getQueryParameter(ss9_COLUMN_DATE);
            if (null != dateString && dateString.length() > 0)
                return Long.parseLong(dateString);
            else
                return 0;
        }

    }
}