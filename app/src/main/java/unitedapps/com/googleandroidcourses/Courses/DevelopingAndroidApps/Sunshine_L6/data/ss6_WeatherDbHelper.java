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
package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Manages a local database for weather data.
 */
public class ss6_WeatherDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int ss6_DATABASE_VERSION = 2;

    public static final String ss6_DATABASE_NAME = "weather.db";

    public ss6_WeatherDbHelper(Context context) {
        super(context, ss6_DATABASE_NAME, null, ss6_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME + " (" +
                // Why AutoIncrement here, and not above?
                // Unique keys will be auto-generated in either case.  But for weather
                // forecasting, it's reasonable to assume the user will want information
                // for a certain date and all dates *following*, so the forecast data
                // should be sorted accordingly.
                ss6_WeatherContract.ss6_WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                // the ID of the location entry associated with this weather data
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_LOC_KEY + " INTEGER NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE + " INTEGER NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_WEATHER_ID + " INTEGER NOT NULL," +

                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MAX_TEMP + " REAL NOT NULL, " +

                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_HUMIDITY + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_PRESSURE + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DEGREES + " REAL NOT NULL, " +

                // Set up the location column as a foreign key to location table.
                " FOREIGN KEY (" + ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_LOC_KEY + ") REFERENCES " +
                ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME + " (" + ss6_WeatherContract.ss6_LocationEntry._ID + "), " +

                // To assure the application have just one weather entry per day
                // per location, it's created a UNIQUE constraint with REPLACE strategy
                " UNIQUE (" + ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE + ", " +
                ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME + " (" +
                ss6_WeatherContract.ss6_WeatherEntry._ID + " INTEGER PRIMARY KEY," +
                ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING + " TEXT UNIQUE NOT NULL, " +
                ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LAT + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LONG + " REAL NOT NULL, " +
                ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_CITY_NAME + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}