package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.BookListingApp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
   Created by dasse on 28-Oct-17.
 */

class BooksQueryUtils {
    static ArrayList<BooksDataObject> extractBooksData(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<BooksDataObject> booksDataObjects = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonItemsObject = jsonObject.optJSONArray("items");

            for (int i=0; i<jsonItemsObject.length(); i++){
                JSONObject jsonObject1 = jsonItemsObject.getJSONObject(i).getJSONObject("volumeInfo");

                JSONObject jsonImagesObject = jsonObject1.optJSONObject("imageLinks");

                booksDataObjects.add(new BooksDataObject(getBookNameAndYear(jsonObject1.optString("title")
                        , jsonObject1.optString("publishedDate")),
                        convertArrayToString(jsonObject1.optJSONArray("authors")),
                        jsonObject1.optInt("pageCount"), jsonObject1.optString("previewLink"),
                        getBitmapFromURL(jsonImagesObject.optString("thumbnail"))));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return booksDataObjects;
    }

    private static Bitmap getBitmapFromURL(String bBitmapURL) {
        try {
            URL url = new URL(bBitmapURL);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  this method is to get book name and year together in one string
     */
    private static String getBookNameAndYear(String title, String publishedDate) {
        return title + " (" + publishedDate.substring(0, 4) + ")";
    }

    /**
    *  this method is to convert json_array to string
    */
    private static String convertArrayToString(JSONArray authorsArray) throws JSONException {
        StringBuilder authors = new StringBuilder();

        for(int i=0; i < authorsArray.length(); i++){
            authors.append(authorsArray.get(i).toString());

            if(i < authorsArray.length()-1)
                authors.append(", ");
        }

        return authors.toString();
    }
}
