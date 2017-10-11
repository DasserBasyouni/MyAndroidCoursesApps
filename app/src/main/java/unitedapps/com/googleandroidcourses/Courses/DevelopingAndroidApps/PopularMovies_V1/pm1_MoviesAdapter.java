package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
    Created by Dasser on 12-Jun-17.
*/

class pm1_MoviesAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap[] bitmaps;

    pm1_MoviesAdapter(Context c, Bitmap[] bitmaps) {
        mContext = c;
        this.bitmaps = bitmaps;
    }

    public int getCount() {
        return bitmaps.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(bitmaps[position]);
        return imageView;
    }

}
