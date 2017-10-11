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
package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class pm2_Provider extends ContentProvider {
    // The URI Matcher used by this content provider.
    private static final UriMatcher pm2_sUriMatcher = pm2_buildUriMatcher();
    private pm2_DbHelper mOpenHelper;

    public static final int pm2_MOVIES = 100;
    public static final int pm2_FAV = 101;

    private Cursor get(String tableName, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public static UriMatcher pm2_buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 2) Use the addURI function to match each of the types.  Use the constants from
        // Contract to help define the types to the UriMatcher.
        matcher.addURI(pm2_Contract.pm2_CONTENT_AUTHORITY, pm2_Contract.pm2_PATH_MOVIES + "/*", pm2_MOVIES);
        matcher.addURI(pm2_Contract.pm2_CONTENT_AUTHORITY, pm2_Contract.pm2_PATH_FAV + "/*", pm2_FAV);

        // 3) Return the new matcher!
        return matcher;

    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new pm2_DbHelper(getContext());
        return true;
    }

    /*
        Students: Here's where you'll code the getType function that uses the UriMatcher.  You can
        test this by uncommenting testGetType in TestProvider.
     */
    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = pm2_sUriMatcher.match(uri);

        switch (match) {
            case pm2_MOVIES:
                return pm2_Contract.pm2_MoviesEntry.pm2_CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (pm2_sUriMatcher.match(uri)) {
            case pm2_MOVIES: {
                retCursor = get(pm2_Contract.pm2_MoviesEntry.pm2_TABLE_NAME, projection
                        , selection, selectionArgs, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = pm2_sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case pm2_MOVIES:
                long _id = db.insert(pm2_Contract.pm2_MoviesEntry.pm2_TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(_id);
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
        final int match = pm2_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case pm2_MOVIES:
                rowDeleted = db.delete(pm2_Contract.pm2_MoviesEntry.pm2_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri need to be deleted: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        // Student: Use the uriMatcher to match the  and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.

        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        // Oh, and you should notify the listeners here.

        // Student: return the actual rows deleted
        return rowDeleted;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = pm2_sUriMatcher.match(uri);
        int rowDeleted;
        switch (match) {
            case pm2_MOVIES:
                rowDeleted = db.update(pm2_Contract.pm2_MoviesEntry.pm2_TABLE_NAME, values, selection, selectionArgs);
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
        final int match = pm2_sUriMatcher.match(uri);
        switch (match) {
            case pm2_MOVIES:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(pm2_Contract.pm2_MoviesEntry.pm2_TABLE_NAME, null, value);
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