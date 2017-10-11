package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

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

public class pm1_MainActivity extends AppCompatActivity {

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm1_activity_main);

        gridview = (GridView) findViewById(R.id.gridview);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(pm1_MainActivity.this, pm1_DetailsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        new FetchMoviesPosters().execute("p");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.pm1_menu, menu);

        MenuItem item = menu.findItem(R.id.pm1_sort_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pm1_sort_spinner_list_item, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    new FetchMoviesPosters().execute("p");
                }else{
                    new FetchMoviesPosters().execute("r");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return true;
    }

    private class FetchMoviesPosters extends AsyncTask<Object, Object, Bitmap[]> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        @Override
        protected Bitmap[] doInBackground(Object... params) {

            String popularMoviesJsonStr;
            String ratedOrPopular;

            if(params[0].equals("p")){
                ratedOrPopular = "popular";
            }else{
                ratedOrPopular = "top_rated";
            }
            try {
                String baseUrl = "https://api.themoviedb.org/3/movie/";
                String apiKey = "?api_key=64bed607af1f9b1c73ec98c70004f5e2";
                String lang = "&language=en-US";
                String aPage = "&page=1";

                URL url = new URL(baseUrl + ratedOrPopular + apiKey + lang + aPage);

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
                return getMoviesDataFromJson(popularMoviesJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private Bitmap[] getMoviesDataFromJson(String moviesJsonStr) throws JSONException {
            // These are the names of the JSON objects that need to be extracted.
            final String OWM_result = "results";
            final String OWM_poster_path = "poster_path";

            JSONObject moviesObjectsJson = new JSONObject(moviesJsonStr);
            JSONArray moviesResultsArray = moviesObjectsJson.getJSONArray(OWM_result);

            Bitmap[] postersBitmapArray = new Bitmap[20];
            for (int i = 0; i < moviesResultsArray.length(); i++) {
                String posterPath;
                
                JSONObject movieNumber = moviesResultsArray.getJSONObject(i);
                posterPath = movieNumber.getString(OWM_poster_path);

                URL PostersUrl;
                try {
                    PostersUrl = new URL("https://image.tmdb.org/t/p/w300" + posterPath);

                    urlConnection = (HttpURLConnection) PostersUrl.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();
                    if (inputStream == null) {
                        return null;
                    }

                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    postersBitmapArray[i] = BitmapFactory.decodeStream(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return postersBitmapArray;
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmaps) {
            gridview.setAdapter(new pm1_MoviesAdapter(pm1_MainActivity.this, bitmaps));
            super.onPostExecute(bitmaps);
        }
    }
}
