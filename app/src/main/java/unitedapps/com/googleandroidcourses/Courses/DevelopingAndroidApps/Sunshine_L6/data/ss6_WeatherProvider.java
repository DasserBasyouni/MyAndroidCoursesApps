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

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ss6_WeatherProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher ss6_sUriMatcher = ss6_buildUriMatcher();
    private ss6_WeatherDbHelper mOpenHelper;

    public static final int ss6_WEATHER = 100;
    public static final int ss6_WEATHER_WITH_LOCATION = 101;
    public static final int ss6_WEATHER_WITH_LOCATION_AND_DATE = 102;
    public static final int ss6_LOCATION = 300;

    private static final SQLiteQueryBuilder ss6_sWeatherByLocationSettingQueryBuilder;

    static {
        ss6_sWeatherByLocationSettingQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        ss6_sWeatherByLocationSettingQueryBuilder.setTables(
                ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME + " INNER JOIN " +
                        ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME +
                        " ON " + ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME +
                        "." + ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_LOC_KEY +
                        " = " + ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME +
                        "." + ss6_WeatherContract.ss6_LocationEntry._ID);
    }

    //location.location_setting = ?
    private static final String ss6_sLocationSettingSelection =
            ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME +
                    "." + ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING + " = ? ";

    //location.location_setting = ? AND date >= ?
    private static final String ss6_sLocationSettingWithStartDateSelection =
            ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME +
                    "." + ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING + " = ? AND " +
                    ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE + " >= ? ";

    //location.location_setting = ? AND date = ?
    private static final String ss6_sLocationSettingAndDaySelection =
            ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME +
                    "." + ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING + " = ? AND " +
                    ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE + " = ? ";

    private Cursor getWeatherByLocationSetting(Uri uri, String[] projection, String sortOrder) {
        String locationSetting = ss6_WeatherContract.ss6_WeatherEntry.ss6_getLocationSettingFromUri(uri);
        long startDate = ss6_WeatherContract.ss6_WeatherEntry.ss6_getStartDateFromUri(uri);

        String[] selectionArgs;
        String selection;

        if (startDate == 0) {
            selection = ss6_sLocationSettingSelection;
            selectionArgs = new String[]{locationSetting};
        } else {
            selectionArgs = new String[]{locationSetting, Long.toString(startDate)};
            selection = ss6_sLocationSettingWithStartDateSelection;
        }

        return ss6_sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    private Cursor getWeatherByLocationSettingAndDate(Uri uri, String[] projection, String sortOrder) {
        String locationSetting = ss6_WeatherContract.ss6_WeatherEntry.ss6_getLocationSettingFromUri(uri);
        long date = ss6_WeatherContract.ss6_WeatherEntry.ss6_getDateFromUri(uri);

        return ss6_sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                ss6_sLocationSettingAndDaySelection,
                new String[]{locationSetting, Long.toString(date)},
                null,
                null,
                sortOrder
        );
    }

    private Cursor getWeather(String tableName, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    private Cursor getLocation(String tableName, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public static UriMatcher ss6_buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        matcher.addURI(ss6_WeatherContract.ss6_CONTENT_AUTHORITY, ss6_WeatherContract.ss6_PATH_LOCATION, ss6_LOCATION);
        matcher.addURI(ss6_WeatherContract.ss6_CONTENT_AUTHORITY, ss6_WeatherContract.ss6_PATH_WEATHER, ss6_WEATHER);
        matcher.addURI(ss6_WeatherContract.ss6_CONTENT_AUTHORITY, ss6_WeatherContract.ss6_PATH_WEATHER + "/*", ss6_WEATHER_WITH_LOCATION);
        matcher.addURI(ss6_WeatherContract.ss6_CONTENT_AUTHORITY, ss6_WeatherContract.ss6_PATH_WEATHER + "/*/#", ss6_WEATHER_WITH_LOCATION_AND_DATE);

        // 3) Return the new matcher!
        return matcher;

    }

    /*
        Students: We've coded this for you.  We just create a new WeatherDbHelper for later use
        here.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new ss6_WeatherDbHelper(getContext());
        return true;
    }

    /*
        Students: Here's where you'll code the getType function that uses the UriMatcher.  You can
        test this by uncommenting testGetType in TestProvider.
     */
    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = ss6_sUriMatcher.match(uri);

        switch (match) {
            case ss6_WEATHER_WITH_LOCATION_AND_DATE:
                return ss6_WeatherContract.ss6_LocationEntry.ss6_CONTENT_ITEM_TYPE;
            case ss6_WEATHER:
                return ss6_WeatherContract.ss6_WeatherEntry.ss6_CONTENT_TYPE;
            case ss6_LOCATION:
                return ss6_WeatherContract.ss6_LocationEntry.ss6_CONTENT_TYPE;
            case ss6_WEATHER_WITH_LOCATION:
                return ss6_WeatherContract.ss6_WeatherEntry.ss6_CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (ss6_sUriMatcher.match(uri)) {
            // "weather/*/*"
            case ss6_WEATHER_WITH_LOCATION_AND_DATE: {
                retCursor = getWeatherByLocationSettingAndDate(uri, projection, sortOrder);
                break;
            }
            // "weather/*"
            case ss6_WEATHER_WITH_LOCATION: {
                retCursor = getWeatherByLocationSetting(uri, projection, sortOrder);
                break;
            }
            // "weather"
            case ss6_WEATHER: {
                retCursor = getWeather(ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME, projection
                        , selection, selectionArgs, sortOrder);
                break;
            }
            // "location"
            case ss6_LOCATION: {
                retCursor = getLocation(ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME, projection
                        , selection, selectionArgs, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    /*
        Student: Add the ability to insert Locations to the implementation of this function.
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = ss6_sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case ss6_WEATHER:
                normalizeDate(values);
                long _id = db.insert(ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ss6_WeatherContract.ss6_WeatherEntry.ss6_buildWeatherUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            case ss6_LOCATION:
                normalizeDate(values);
                _id = db.insert(ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ss6_WeatherContract.ss6_LocationEntry.ss6_buildLocationUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = ss6_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case ss6_WEATHER:
                rowDeleted = db.delete(ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME, selection, selectionArgs);
                break;
            case ss6_LOCATION:
                    rowDeleted =  db.delete(ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri need to be deleted: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        // Student: Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.

        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        // Oh, and you should notify the listeners here.

        // Student: return the actual rows deleted
        return rowDeleted;
    }

    private void normalizeDate(ContentValues values) {
        // normalize the date value
        if (values.containsKey(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE)) {
            long dateValue = values.getAsLong(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE);
            values.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE, ss6_WeatherContract.ss6_normalizeDate(dateValue));
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = ss6_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case ss6_WEATHER:
                rowDeleted = db.update(ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME, values, selection, selectionArgs);
                break;
            case ss6_LOCATION:
                rowDeleted = db.update(ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri need to be updated: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowDeleted;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = ss6_sUriMatcher.match(uri);
        switch (match) {
            case ss6_WEATHER:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        normalizeDate(value);
                        long _id = db.insert(ss6_WeatherContract.ss6_WeatherEntry.ss6_TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    // You do not need to call this method. This is a method specifically to assist the testing
    // framework in running smoothly. You can read more at:
    // http://developer.android.com/reference/android/content/ContentProvider.html#shutdown()
    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }
}