package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.BookListingApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 28-Oct-17.
 */

public class BooksAdapter extends ArrayAdapter<BooksDataObject> {

    private ArrayList<BooksDataObject> booksDataObject;

    BooksAdapter(@NonNull Context context, ArrayList<BooksDataObject> booksDataObject) {
        super(context, 0);
        this.booksDataObject = booksDataObject;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        
        if(view == null) 
            view = LayoutInflater.from(getContext()).inflate(R.layout.ab_n_bla_book_list_item, parent, false);

        final BooksDataObject currentBooksDataObject = booksDataObject.get(position);
        
        TextView title_tv = view.findViewById(R.id.ab_n_bla_book_title_tv);
        title_tv.setText(currentBooksDataObject.getBookNameAndYear());

        TextView author_tv = view.findViewById(R.id.ab_n_bla_book_author_tv);
        author_tv.setText(currentBooksDataObject.getBookAuthor());

        TextView book_pages_tv = view.findViewById(R.id.ab_n_bla_book_pages_tv);
        book_pages_tv.setText(String.format(getContext().getString(R.string.ab_n_bla_pages_format)
                , currentBooksDataObject.getNumberOfPages()));

        Button preview_btn = view.findViewById(R.id.ab_n_bla_preview_btn);
        preview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentBooksDataObject.getPreviewLink()));
                getContext().startActivity(i);
            }
        });

        ImageView book_iv = view.findViewById(R.id.ab_n_bla_book_iv);
        Bitmap bBitmap = currentBooksDataObject.getbBitmap();
        if(bBitmap == null)
            book_iv.setImageResource(R.drawable.ab_n_bla_no_image);
        else
            book_iv.setImageBitmap(currentBooksDataObject.getbBitmap());

        scaleImage(book_iv);

        return view;
    }



    /**
     * for scaling the bitmap to fit its Height only not Width also
    */
    private void scaleImage(ImageView view) throws NoSuchElementException  {
        // Get bitmap from the the ImageView.
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("No drawable on given view");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        // Get current dimensions AND the desired bounding box
        int width;

        try {
            width = bitmap != null ? bitmap.getWidth() : 0;
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
        }

        int height = bitmap != null ? bitmap.getHeight() : 0;
        int bounding = dpToPx(view.getDrawable().getIntrinsicHeight());
        Log.d("Z_here", view.getDrawable().getIntrinsicHeight() + " + " + view.getMeasuredHeight());
        Log.i("Test", "original width = " + Integer.toString(width));
        Log.i("Test", "original height = " + Integer.toString(height));
        Log.i("Test", "bounding = " + Integer.toString(bounding));

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Log.i("Test", "xScale = " + Float.toString(xScale));
        Log.i("Test", "yScale = " + Float.toString(yScale));
        Log.i("Test", "scale = " + Float.toString(scale));

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = null;
        if (bitmap != null) {
            scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        width = scaledBitmap != null ? scaledBitmap.getWidth() : 0; // re-use
        height = scaledBitmap != null ? scaledBitmap.getHeight() : 0; // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        Log.i("Test", "scaled width = " + Integer.toString(width));
        Log.i("Test", "scaled height = " + Integer.toString(height));

        // Apply the scaled bitmap
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);

        Log.i("Test", "done");
    }
    private int dpToPx(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

    @Override
    public int getCount() {
        return booksDataObject.size();
    }
}
