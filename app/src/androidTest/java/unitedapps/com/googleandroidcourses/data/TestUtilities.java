package unitedapps.com.googleandroidcourses.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6.data.ss6_WeatherContract;
import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L6.data.ss6_WeatherDbHelper;
import unitedapps.com.googleandroidcourses.utils.PollingCheck;

/*
    Students: These are functions and some test data to make it easier to test your database and
    Content Provider.  Note that you'll want your ss6_WeatherContract class to exactly match the one
    in our solution to use these as-given.
 */
public class TestUtilities extends AndroidTestCase {
    static final String TEST_LOCATION = "99705";
    static final long TEST_DATE = 1419033600L;  // December 20th, 2014

    static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }

    /*
        Students: Use this to create some default weather values for your database tests.
     */
    static ContentValues createWeatherValues(long locationRowId) {
        ContentValues weatherValues = new ContentValues();
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_LOC_KEY, locationRowId);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DATE, TEST_DATE);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_DEGREES, 1.1);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_HUMIDITY, 1.2);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_PRESSURE, 1.3);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MAX_TEMP, 75);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_MIN_TEMP, 65);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_SHORT_DESC, "Asteroids");
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_WIND_SPEED, 5.5);
        weatherValues.put(ss6_WeatherContract.ss6_WeatherEntry.ss6_COLUMN_WEATHER_ID, 321);

        return weatherValues;
    }

    /*
        Students: You can uncomment this helper function once you have finished creating the
        ss6_LocationEntry part of the ss6_WeatherContract.
     */
    static ContentValues createNorthPoleLocationValues() {
        // Create a new map of values, where column names are the keys
        ContentValues testValues = new ContentValues();
        testValues.put(ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_LOCATION_SETTING, TEST_LOCATION);
        testValues.put(ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_CITY_NAME, "North Pole");
        testValues.put(ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LAT, 64.7488);
        testValues.put(ss6_WeatherContract.ss6_LocationEntry.ss6_COLUMN_COORD_LONG, -147.353);

        return testValues;
    }

    /*
        Students: You can uncomment this function once you have finished creating the
        ss6_LocationEntry part of the ss6_WeatherContract as well as the WeatherDbHelper.
     */
    static long insertNorthPoleLocationValues(Context context) {
        // insert our test records into the database
        ss6_WeatherDbHelper dbHelper = new ss6_WeatherDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues testValues = TestUtilities.createNorthPoleLocationValues();

        long locationRowId;
        locationRowId = db.insert(ss6_WeatherContract.ss6_LocationEntry.ss6_TABLE_NAME, null, testValues);

        // Verify we got a row back.
        assertTrue("Error: Failure to insert North Pole Location Values", locationRowId != -1);

        return locationRowId;
    }

    /*
        Students: The functions we provide inside of TestProvider use this utility class to test
        the ContentObserver callbacks using the PollingCheck class that we grabbed from the Android
        CTS tests.
        Note that this only tests that the onChange function is called; it does not test that the
        correct Uri is returned.
     */
    static class TestContentObserver extends ContentObserver {
        final HandlerThread mHT;
        boolean mContentChanged;

        static TestContentObserver getTestContentObserver() {
            HandlerThread ht = new HandlerThread("ContentObserverThread");
            ht.start();
            return new TestContentObserver(ht);
        }

        private TestContentObserver(HandlerThread ht) {
            super(new Handler(ht.getLooper()));
            mHT = ht;
        }

        // On earlier versions of Android, this onChange method is called
        @Override
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            mContentChanged = true;
        }

        public void waitForNotificationOrFail() {
            // Note: The PollingCheck class is taken from the Android CTS (Compatibility Test Suite).
            // It's useful to look at the Android CTS source for ideas on how to test your Android
            // applications.  The reason that PollingCheck works is that, by default, the JUnit
            // testing framework is not running on the main Android application thread.
            new PollingCheck(5000) {
                @Override
                protected boolean check() {
                    return mContentChanged;
                }
            }.run();
            mHT.quit();
        }
    }

    static TestContentObserver getTestContentObserver() {
        return TestContentObserver.getTestContentObserver();
    }
}