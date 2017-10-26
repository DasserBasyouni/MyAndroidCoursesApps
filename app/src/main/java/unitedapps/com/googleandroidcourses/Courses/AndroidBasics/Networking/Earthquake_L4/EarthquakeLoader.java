package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L4;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
   Created by dasse on 26-Oct-17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeDataObject>> {

    public static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mURL;

    EarthquakeLoader(Context context, String REPORT_URL) {
        super(context);
        mURL = REPORT_URL;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthquakeDataObject> loadInBackground() {
        URL url = null;
        try {
            url = new URL(mURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Z_" + LOG_TAG, "Error closing input stream", e);
        }

        return QueryUtils.extractEarthquakes(jsonResponse);
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("Z_" + LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("Z_" + LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}