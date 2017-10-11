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

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class WeatherProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher ss9_sUriMatcher = ss9_buildUriMatcher();
    private WeatherDbHelper mOpenHelper;

    public static final int ss9_WEATHER = 100;
    public static final int ss9_WEATHER_WITH_LOCATION = 101;
    public static final int ss9_WEATHER_WITH_LOCATION_AND_DATE = 102;
    public static final int ss9_LOCATION = 300;

    private static final SQLiteQueryBuilder ss9_sWeatherByLocationSettingQueryBuilder;

    static {
        ss9_sWeatherByLocationSettingQueryBuilder = new SQLiteQueryBuilder();

        //This is an inner join which looks like
        //weather INNER JOIN location ON weather.location_id = location._id
        ss9_sWeatherByLocationSettingQueryBuilder.setTables(
                ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME + " INNER JOIN " +
                        ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME +
                        " ON " + ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME +
                        "." + ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_LOC_KEY +
                        " = " + ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME +
                        "." + ss9_WeatherContract.ss9_LocationEntry._ID);
    }

    //location.location_setting = ?
    private static final String ss9_sLocationSettingSelection =
            ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME +
                    "." + ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_LOCATION_SETTING + " = ? ";

    //location.location_setting = ? AND date >= ?
    private static final String ss9_sLocationSettingWithStartDateSelection =
            ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME +
                    "." + ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_LOCATION_SETTING + " = ? AND " +
                    ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE + " >= ? ";

    //location.location_setting = ? AND date = ?
    private static final String ss9_sLocationSettingAndDaySelection =
            ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME +
                    "." + ss9_WeatherContract.ss9_LocationEntry.ss9_COLUMN_LOCATION_SETTING + " = ? AND " +
                    ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE + " = ? ";

    private Cursor getWeatherByLocationSetting(Uri uri, String[] projection, String sortOrder) {
        String locationSetting = ss9_WeatherContract.ss9_WeatherEntry.ss9_getLocationSettingFromUri(uri);
        long startDate = ss9_WeatherContract.ss9_WeatherEntry.ss9_getStartDateFromUri(uri);

        String[] selectionArgs;
        String selection;

        if (startDate == 0) {
            selection = ss9_sLocationSettingSelection;
            selectionArgs = new String[]{locationSetting};
        } else {
            selectionArgs = new String[]{locationSetting, Long.toString(startDate)};
            selection = ss9_sLocationSettingWithStartDateSelection;
        }

        return ss9_sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    private Cursor getWeatherByLocationSettingAndDate(Uri uri, String[] projection, String sortOrder) {
        String locationSetting = ss9_WeatherContract.ss9_WeatherEntry.ss9_getLocationSettingFromUri(uri);
        long date = ss9_WeatherContract.ss9_WeatherEntry.ss9_getDateFromUri(uri);

        return ss9_sWeatherByLocationSettingQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                ss9_sLocationSettingAndDaySelection,
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

    public static UriMatcher ss9_buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        matcher.addURI(ss9_WeatherContract.ss9_CONTENT_AUTHORITY, ss9_WeatherContract.ss9_PATH_LOCATION, ss9_LOCATION);
        matcher.addURI(ss9_WeatherContract.ss9_CONTENT_AUTHORITY, ss9_WeatherContract.ss9_PATH_WEATHER, ss9_WEATHER);
        matcher.addURI(ss9_WeatherContract.ss9_CONTENT_AUTHORITY, ss9_WeatherContract.ss9_PATH_WEATHER + "/*", ss9_WEATHER_WITH_LOCATION);
        matcher.addURI(ss9_WeatherContract.ss9_CONTENT_AUTHORITY, ss9_WeatherContract.ss9_PATH_WEATHER + "/*/#", ss9_WEATHER_WITH_LOCATION_AND_DATE);

        // 3) Return the new matcher!
        return matcher;

    }

    /*
        Students: We've coded this for you.  We just create a new WeatherDbHelper for later use
        here.
     */
    @Override
    public boolean onCreate() {
        mOpenHelper = new WeatherDbHelper(getContext());
        return true;
    }

    /*
        Students: Here's where you'll code the getType function that uses the UriMatcher.  You can
        test this by uncommenting testGetType in TestProvider.
     */
    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = ss9_sUriMatcher.match(uri);

        switch (match) {
            case ss9_WEATHER_WITH_LOCATION_AND_DATE:
                return ss9_WeatherContract.ss9_LocationEntry.ss9_CONTENT_ITEM_TYPE;
            case ss9_WEATHER:
                return ss9_WeatherContract.ss9_WeatherEntry.ss9_CONTENT_TYPE;
            case ss9_LOCATION:
                return ss9_WeatherContract.ss9_LocationEntry.ss9_CONTENT_TYPE;
            case ss9_WEATHER_WITH_LOCATION:
                return ss9_WeatherContract.ss9_WeatherEntry.ss9_CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (ss9_sUriMatcher.match(uri)) {
            // "weather/*/*"
            case ss9_WEATHER_WITH_LOCATION_AND_DATE: {
                retCursor = getWeatherByLocationSettingAndDate(uri, projection, sortOrder);
                break;
            }
            // "weather/*"
            case ss9_WEATHER_WITH_LOCATION: {
                retCursor = getWeatherByLocationSetting(uri, projection, sortOrder);
                break;
            }
            // "weather"
            case ss9_WEATHER: {
                retCursor = getWeather(ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME, projection
                        , selection, selectionArgs, sortOrder);
                break;
            }
            // "location"
            case ss9_LOCATION: {
                retCursor = getLocation(ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME, projection
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
        final int match = ss9_sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case ss9_WEATHER:
                normalizeDate(values);
                long _id = db.insert(ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ss9_WeatherContract.ss9_WeatherEntry.ss9_buildWeatherUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            case ss9_LOCATION:
                normalizeDate(values);
                _id = db.insert(ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ss9_WeatherContract.ss9_LocationEntry.ss9_buildLocationUri(_id);
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
        final int match = ss9_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case ss9_WEATHER:
                rowDeleted = db.delete(ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME, selection, selectionArgs);
                break;
            case ss9_LOCATION:
                    rowDeleted =  db.delete(ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME, selection, selectionArgs);
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
        if (values.containsKey(ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE)) {
            long dateValue = values.getAsLong(ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE);
            values.put(ss9_WeatherContract.ss9_WeatherEntry.ss9_COLUMN_DATE, ss9_WeatherContract.ss9_normalizeDate(dateValue));
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = ss9_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case ss9_WEATHER:
                rowDeleted = db.update(ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME, values, selection, selectionArgs);
                break;
            case ss9_LOCATION:
                rowDeleted = db.update(ss9_WeatherContract.ss9_LocationEntry.ss9_TABLE_NAME, values, selection, selectionArgs);
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
        final int match = ss9_sUriMatcher.match(uri);
        switch (match) {
            case ss9_WEATHER:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        normalizeDate(value);
                        long _id = db.insert(ss9_WeatherContract.ss9_WeatherEntry.ss9_TABLE_NAME, null, value);
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