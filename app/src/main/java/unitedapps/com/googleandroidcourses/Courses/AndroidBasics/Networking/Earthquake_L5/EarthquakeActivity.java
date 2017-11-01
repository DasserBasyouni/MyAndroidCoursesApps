/*
 * Copyright (C) 2016 The Android Open Source Project
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
package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L5;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import unitedapps.com.googleandroidcourses.R;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeDataObject>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public final int EARTHQUAKE_LOADER_ID = 7589;
    private final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    EarthquakeAdapter earthquakeAdapter;
    ListView earthquakeListView;
    ArrayList<EarthquakeDataObject> earthquakeDataObjectsMian;
    TextView no_views_tv;
    ProgressBar loading_pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_n_earthquake4_activity);

        earthquakeListView = findViewById(R.id.ab_n_list);
        no_views_tv = findViewById(R.id.ab_n_qr4_no_views_tv);
        loading_pb = findViewById(R.id.ab_n_qr4_loading_pb);

        // Create a new {@link ArrayAdapter} of earthquakes
        earthquakeDataObjectsMian = new ArrayList<>();
        earthquakeAdapter = new EarthquakeAdapter(this, earthquakeDataObjectsMian);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                EarthquakeDataObject currentEarthquake =  earthquakeAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        if(haveInternetConnection())
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
        else{
            loading_pb.setVisibility(View.GONE);
            no_views_tv.setText(getString(R.string.ab_n_eq4_no_internet));
            no_views_tv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ab_n_qr4_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ab_n_qr4_action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<EarthquakeDataObject>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.ab_n_qr4_settings_min_magnitude_key),
                getString(R.string.ab_n_qr4_settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.ab_n_qr4_settings_order_by_key),
                getString(R.string.ab_n_qr4_settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new EarthquakeLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeDataObject>> loader, List<EarthquakeDataObject> earthquakeDataObjects) {
        // Clear the adapter of previous earthquake data
        earthquakeDataObjectsMian.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakeDataObjects != null && !earthquakeDataObjects.isEmpty()) {
            earthquakeDataObjectsMian.addAll(earthquakeDataObjects);
            earthquakeAdapter.notifyDataSetChanged();
        }
        loading_pb.setVisibility(View.GONE);
        earthquakeListView.setEmptyView(no_views_tv);
    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeDataObject>> loader) {
        earthquakeAdapter.clear();
    }

    public boolean haveInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}