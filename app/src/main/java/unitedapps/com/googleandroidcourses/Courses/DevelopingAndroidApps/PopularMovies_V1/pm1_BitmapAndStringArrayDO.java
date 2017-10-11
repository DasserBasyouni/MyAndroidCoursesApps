package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V1;

import android.graphics.Bitmap;

/**
    Created by Dasser on 14-Jun-17.
*/

public class pm1_BitmapAndStringArrayDO {
    String[] stringData;
    Bitmap bitmap;

    public pm1_BitmapAndStringArrayDO(String[] stringData, Bitmap bitmap) {
        this.stringData = stringData;
        this.bitmap = bitmap;
    }

    public String[] getStringData() {
        return stringData;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
