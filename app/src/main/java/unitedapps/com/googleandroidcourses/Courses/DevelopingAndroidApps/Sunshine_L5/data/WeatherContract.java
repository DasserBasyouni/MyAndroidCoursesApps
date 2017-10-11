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

package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L5.data;

import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Defines table and column names for the weather database.
 */
public class WeatherContract {

    // To make it easy to query for the exact date, we normalize all dates that go into
    // the database to the start of the the Julian day at UTC.
    public static long ss5_normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }

    /*
        Inner class that defines the table contents of the location table
        Students: This is where you will add the strings.  (Similar to what has been
        done for WeatherEntry)
     */
    public static final class ss5_LocationEntry implements BaseColumns {
        public static final String ss5_TABLE_NAME = "location";
        public static final String ss5_COLUMN_LOCATION_SETTING = "location_settings";
        public static final String ss5_COLUMN_COORD_LAT = "latitude";
        public static final String ss5_COLUMN_COORD_LONG = "longitude";
        public static final String ss5_COLUMN_CITY_NAME = "city_name";
    }

    /* Inner class that defines the table contents of the weather table */
    public static final class ss5_WeatherEntry implements BaseColumns {

        public static final String ss5_TABLE_NAME = "weather";

        // Column with the foreign key into the location table.
        public static final String ss5_COLUMN_LOC_KEY = "location_id";
        // Date, stored as long in milliseconds since the epoch
        public static final String ss5_COLUMN_DATE = "date";
        // Weather id as returned by API, to identify the icon to be used
        public static final String ss5_COLUMN_WEATHER_ID = "weather_id";

        // Short description and long description of the weather, as provided by API.
        // e.g "clear" vs "sky is clear".
        public static final String ss5_COLUMN_SHORT_DESC = "short_desc";

        // Min and max temperatures for the day (stored as floats)
        public static final String ss5_COLUMN_MIN_TEMP = "min";
        public static final String ss5_COLUMN_MAX_TEMP = "max";

        // Humidity is stored as a float representing percentage
        public static final String ss5_COLUMN_HUMIDITY = "humidity";

        // Humidity is stored as a float representing percentage
        public static final String ss5_COLUMN_PRESSURE = "pressure";

        // Windspeed is stored as a float representing windspeed  mph
        public static final String ss5_COLUMN_WIND_SPEED = "wind";

        // Degrees are meteorological degrees (e.g, 0 is north, 180 is south).  Stored as floats.
        public static final String ss5_COLUMN_DEGREES = "degrees";
    }
}