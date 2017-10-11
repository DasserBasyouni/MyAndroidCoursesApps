package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import unitedapps.com.googleandroidcourses.R;

public class pm1_DetailsActivity extends AppCompatActivity {

    TextView pm_title_tv, pm_plotSynopsis_tv, pm_rekeaseDate_tv, pm_userRate_tv;
    ImageView pm_thumbnail_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm1_details_activity);

        pm_title_tv = (TextView) findViewById(R.id.pm_title_tv);
        pm_plotSynopsis_tv = (TextView) findViewById(R.id.pm_plotSynopsis_tv);
        pm_rekeaseDate_tv = (TextView) findViewById(R.id.pm_rekeaseDate_tv);
        pm_userRate_tv = (TextView) findViewById(R.id.pm_userRate_tv);
        pm_thumbnail_iv = (ImageView) findViewById(R.id.pm_thumbnail_iv);

        new FetchDetails().execute(getIntent().getExtras().getInt("position"));
    }

    private class FetchDetails extends AsyncTask<Integer, Void, pm1_BitmapAndStringArrayDO> {

        HttpURLConnection urlConnection;

        @Override
        protected pm1_BitmapAndStringArrayDO doInBackground(Integer... params) {
            // Will contain the raw JSON response as a string.
            String popularMoviesJsonStr;

            BufferedReader reader = null;
            try {
                String baseUrl = "https://api.themoviedb.org/3/movie/popular";
                String apiKey = "?api_key=64bed607af1f9b1c73ec98c70004f5e2";
                String lang = "&language=en-US";
                String aPage = "&page=1";

                URL url = new URL(baseUrl + apiKey + lang + aPage);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                popularMoviesJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            try {
                return getMovieDataFromJson(popularMoviesJsonStr, params[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private pm1_BitmapAndStringArrayDO getMovieDataFromJson(String movieJsonStr, Integer param) throws JSONException {
            // These are the names of the JSON objects that need to be extracted.
            final String OWM_result = "results";
            final String OWM_backdrop_path = "backdrop_path";
            String posterPath;
            String[] stringData = new String[4];

            JSONObject moviesObjectsJson = new JSONObject(movieJsonStr);
            JSONArray moviesResultsArray = moviesObjectsJson.getJSONArray(OWM_result);

            JSONObject movieNumber = moviesResultsArray.getJSONObject(param);
            posterPath = movieNumber.getString(OWM_backdrop_path);

            stringData[0] = movieNumber.getString("original_title");
            stringData[1] = movieNumber.getString("release_date");
            stringData[2] = movieNumber.getString("vote_average");
            stringData[3] = movieNumber.getString("overview");

            URL PostersUrl;
            Bitmap backDropBitmap = null;
            try {
                PostersUrl = new URL("https://image.tmdb.org/t/p/w500" + posterPath);

                urlConnection = (HttpURLConnection) PostersUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }

                BufferedInputStream bis = new BufferedInputStream(inputStream);
                backDropBitmap = BitmapFactory.decodeStream(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new pm1_BitmapAndStringArrayDO(stringData, backDropBitmap);
        }

        @Override
        protected void onPostExecute(pm1_BitmapAndStringArrayDO pm1BitmapAndStringArrayDO) {
            pm_title_tv.setText((pm1BitmapAndStringArrayDO.getStringData()[0]));
            pm_plotSynopsis_tv .setText((pm1BitmapAndStringArrayDO.getStringData()[3]));
            pm_rekeaseDate_tv.setText((pm1BitmapAndStringArrayDO.getStringData()[1]));
            pm_userRate_tv .setText((pm1BitmapAndStringArrayDO.getStringData()[2]));
            pm_thumbnail_iv.setImageBitmap((pm1BitmapAndStringArrayDO.getBitmap()));
            super.onPostExecute(pm1BitmapAndStringArrayDO);
        }
    }
}
