package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.graphics.Bitmap;

/**
    Created by Dasser on 14-Jun-17.
*/

public class pm2_BitmapAndStringArrayDO {
    String[] stringData;
    Bitmap bitmap;

    public pm2_BitmapAndStringArrayDO(String[] stringData, Bitmap bitmap) {
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
