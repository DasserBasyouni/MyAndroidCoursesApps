package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
   Created by DB-Project on 8/16/2017.
*/

class pm2_Utils {

    public static String strSeparator = "__,__";

    static Bitmap getImageFromByteArray(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    static String getRateFormat(String rate) {
        return rate + "/10";
    }

    static String[] getArrayFormat(String array) {
        return array.split(strSeparator);
    }

    static String[] getYoutubeTrailers(String[] youtubeTrailers) {
        for(int i=0; i<youtubeTrailers.length ;i++){
            youtubeTrailers[i] = "https://www.youtube.com/watch?v=" + youtubeTrailers[i];
        }
        return youtubeTrailers;
    }

    static String getRunTimeFormat(String time) {
        return time + "mins";
    }

    static boolean isThisTablesSW600Plus(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }
}
