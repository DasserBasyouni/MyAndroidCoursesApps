package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.sync;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.data.pm2_Contract;
import unitedapps.com.googleandroidcourses.R;

/**
    Created by Dasser on 17-Jul-17.
 */

public class pm2_Service extends IntentService {

    private final String LOG_TAG = pm2_Service.class.getSimpleName();
    public static final String pm2_LOCATION_QUERY_EXTRA = "lqe";
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    int w;

    public pm2_Service() {
        super("pm2_Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        w = displayMetrics.widthPixels / 2;

        Log.i("Z_", String.valueOf(w));

        if (w < 300) {
            w = 300;
        } else if (w > 600) {
            w = 600;
        }
        String popularMoviesJsonStr;
        String ratedOrPopular = null;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        int sortOption = sharedPref.getInt(getString(R.string.pm2_savedSortMode), 1234);


        if (sortOption == 0) {
            ratedOrPopular = "popular";
        } else if (sortOption == 1) {
            ratedOrPopular = "top_rated";
        } else {
            ratedOrPopular = "fav";
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
                return;
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
                return;
            }
            popularMoviesJsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            return;
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
            getMoviesDataFromJson(popularMoviesJsonStr, sortOption);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void getMoviesDataFromJson(String moviesJsonStr, int sortOption) throws JSONException, ParseException {
        // These are the names of the JSON objects that need to be extracted.
        final String JSON_Movie_result = "results";
        final String JSON_Movie_poster_path = "poster_path";
        final String JSON_Movie_id = "id";

        final String JSON_Movie_vote = "vote_average";
        final String JSON_Movie_sys = "overview";
        final String JSON_Movie_year = "release_date";
        final String JSON_Movie_name = "original_title";
        //final String JSON_Movie_length = "length";
        //final String JSON_Movie_reviews = "reviews";

        JSONObject moviesObjectsJson = new JSONObject(moviesJsonStr);
        JSONArray moviesResultsArray = moviesObjectsJson.getJSONArray(JSON_Movie_result);

        int id, year;
        String name, sys, vote;

        for (int i = 0; i < moviesResultsArray.length(); i++) {
            String posterPath;

            JSONObject movieNumber = moviesResultsArray.getJSONObject(i);

            posterPath = movieNumber.getString(JSON_Movie_poster_path);
            id = movieNumber.getInt(JSON_Movie_id);
            vote = movieNumber.getString(JSON_Movie_vote);
            sys = movieNumber.getString(JSON_Movie_sys);
            year = getJustYearFromDate(movieNumber.getString(JSON_Movie_year));
            name = movieNumber.getString(JSON_Movie_name);
            //trailers = movieNumber.getString(JSON_Movie_name);
            //length = movieNumber.getInt(length);
            //reviews = movieNumber.getInt(reviews);

            URL PostersUrl;
            try {
                PostersUrl = new URL("https://image.tmdb.org/t/p/w" + w + posterPath);

                urlConnection = (HttpURLConnection) PostersUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    Log.i("Z_" , "stream = null");
                    return;
                }
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                addMovieData(id, name, vote, year, sys, BitmapFactory.decodeStream(bis), getArrayFromJson(getJSONFromURL(id, 2), 0)
                        , getMoviesRuntimeFromJson(getJSONFromURL(id, 1)), getArrayFromJson(getJSONFromURL(id, 3), 1)
                        , getArrayFromJson(getJSONFromURL(id, 3), 2), sortOption);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("Z_ws", "add movie done all");

        //getApplicationContext().getContentResolver().notifyChange(pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100), null);
        //Place the below two lines where you want to trigger BroadcastReceiver
        Intent intent =new Intent("com.your_package.ANY_TEXT_STRING");
        sendBroadcast(intent);
    }

    private String getJSONFromURL(int id, int type) throws IOException {
        String JsonStr = null;
        URL url = null;
        String apiKey = "?api_key=64bed607af1f9b1c73ec98c70004f5e2";
        String baseUrl = "https://api.themoviedb.org/3/movie/";

        if (type == 1) { // movie runtime
            url = new URL(baseUrl + id + apiKey);
        } else if (type == 2) { // trailers
            url = new URL(baseUrl + id + "/videos" + apiKey);
        } else if (type == 3) { // reviews
            url = new URL(baseUrl + id + "/reviews" + apiKey);
        }

        try {
            assert url != null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream;
            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK)
                inputStream = urlConnection.getErrorStream();
            else
                inputStream = urlConnection.getInputStream();


            // Read the input stream into a String
            //inputStream = urlConnection.getInputStream();

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
            JsonStr = buffer.toString();

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
        return JsonStr;
    }

    private String getArrayFromJson(String movieJsonStr, int type) throws JSONException {
        final String JSON_Movie_result = "results", JSON_Movie_trailers, JSON_Movie_rAuthor, JSON_Movie_rContent;

        if (movieJsonStr != null) {

            JSONObject moviesObjectsJson = new JSONObject(movieJsonStr);
            JSONArray moviesResultsArray = moviesObjectsJson.getJSONArray(JSON_Movie_result);
            String arrayStr = "", separator = "";

            if (type == 0) {
                JSON_Movie_trailers = "key";
                for (int i = 0; i < moviesResultsArray.length(); i++) {
                    if (i == 1) {
                        separator = "__,__";
                    }
                    arrayStr = arrayStr + separator + moviesResultsArray.getJSONObject(i).getString(JSON_Movie_trailers);
                }
            } else if (type == 1) {
                JSON_Movie_rAuthor = "author";
                for (int i = 0; i < moviesResultsArray.length(); i++) {
                    if (i == 1) {
                        separator = "__,__";
                    }
                    arrayStr = arrayStr + separator + moviesResultsArray.getJSONObject(i).getString(JSON_Movie_rAuthor);
                }
            } else if (type == 2) {
                JSON_Movie_rContent = "content";
                for (int i = 0; i < moviesResultsArray.length(); i++) {
                    if (i == 1) {
                        separator = "__,__";
                    }
                    arrayStr = arrayStr + separator + moviesResultsArray.getJSONObject(i).getString(JSON_Movie_rContent);
                }
            }
            return arrayStr;
        }else {
            Log.i("Z_ movie reviewTrailers", "it is null");
        }
        return null;
    }

    private String getMoviesRuntimeFromJson(String movieJsonStr) throws JSONException {
        final String JSON_Movie_runTime = "runtime";
        JSONObject moviesObjectsJson = new JSONObject(movieJsonStr);
        return moviesObjectsJson.getString(JSON_Movie_runTime);
    }

    private int getJustYearFromDate(String date) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date yourDate = null;
        try {
            yourDate = parser.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yourDate);
        return calendar.get(Calendar.YEAR);
    }

    public long addMovieData(int Movie_ID, String movieName, String movieVote, int movieYear
            , String movieSYS, Bitmap moviePoster, String movieTrailers, String movieLength, String movieReviews_A
            , String movieReviews_C, int ratedOrPopular) {
        long movieIndexedId;

        Cursor movieCursor = getApplicationContext().getContentResolver().query(
                pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100),
                new String[]{pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID},
                pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID + " = ?",
                new String[]{String.valueOf(Movie_ID)},
                null);

        if (movieCursor.moveToFirst()) {
            int locationIdIndex = movieCursor.getColumnIndex(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID);
            movieIndexedId = movieCursor.getLong(locationIdIndex);
        } else {
            ContentValues locationValues = new ContentValues();

            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_ID, Movie_ID);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_NAME, movieName);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_RATE, movieVote);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_YEAR, movieYear);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_SYS, movieSYS);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_POSTER, getBytesFromBitmap(moviePoster));

            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_LENGTH, movieLength);

            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_TRAILERS, movieTrailers);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_REVIEWS_A, movieReviews_A);
            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_REVIEWS_C, movieReviews_C);

            locationValues.put(pm2_Contract.pm2_MoviesEntry.pm2_COLUMN_MOVIE_RATED, ratedOrPopular);

            Uri insertedUri = getApplicationContext().getContentResolver().insert(
                    pm2_Contract.pm2_MoviesEntry.pm2_buildMoviesUri(100), locationValues);

            movieIndexedId = ContentUris.parseId(insertedUri);

        }
        movieCursor.close();
        return movieIndexedId;
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
