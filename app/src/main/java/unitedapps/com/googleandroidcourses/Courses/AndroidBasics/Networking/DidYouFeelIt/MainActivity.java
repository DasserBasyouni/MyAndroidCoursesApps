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
package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.DidYouFeelIt;

import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.TextView;

        import unitedapps.com.googleandroidcourses.R;

/**
 * Displays the perceived strength of a single earthquake event based on responses from people who
 * felt the earthquake.
 */
public class MainActivity extends AppCompatActivity {

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    TextView titleTextView, tsunamiTextView, magnitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_n_dyfi_activity_main);

        titleTextView = findViewById(R.id.ab_n_dyfi_title);
        tsunamiTextView = findViewById(R.id.ab_n_dyfi_number_of_people);
        magnitudeTextView = findViewById(R.id.ab_n_dyfi_perceived_magnitude);

        new fetchEarthquakeDataAsync().execute(USGS_REQUEST_URL);
    }


    public class fetchEarthquakeDataAsync extends AsyncTask<String, Void, Event>{

        @Override
        protected Event doInBackground(String... strings) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }

            // Perform the HTTP request for earthquake data and return the response.
            return Utils.fetchEarthquakeData(strings[0]);
        }

        @Override
        protected void onPostExecute(Event event) {
            // If there is no result, do nothing.
            if (event == null) {
                return;
            }

            // Update the information displayed to the user.
            updateUi(event);
        }

        /**
         * Update the UI with the given earthquake information.
         */
        private void updateUi(Event earthquake) {

            titleTextView.setText(earthquake.title);
            tsunamiTextView.setText(getString(R.string.ab_n_dyfi_num_people_felt_it, earthquake.numOfPeople));
            magnitudeTextView.setText(earthquake.perceivedStrength);
        }
    }
}