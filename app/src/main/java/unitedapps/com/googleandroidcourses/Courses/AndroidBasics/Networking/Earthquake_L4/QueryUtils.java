package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L4;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link EarthquakeDataObject} objects that has been built up from
     * parsing a JSON response.
     * @param jsonResponse is the JSON data GET from the URL
     */
    static ArrayList<EarthquakeDataObject> extractEarthquakes(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeDataObject> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonFeaturesObject = jsonObject.optJSONArray("features");

            for (int i=0; i<jsonFeaturesObject.length(); i++){
                JSONObject jsonObject1 = jsonFeaturesObject.getJSONObject(i).getJSONObject("properties");
                earthquakes.add(new EarthquakeDataObject(jsonObject1.optDouble("mag"),
                        jsonObject1.optString("place"), jsonObject1.optLong("time"), jsonObject1.optString("url")));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}