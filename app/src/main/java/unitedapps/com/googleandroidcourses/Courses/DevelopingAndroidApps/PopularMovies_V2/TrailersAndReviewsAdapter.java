package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by DB-Project on 8/23/2017.
*/

class TrailersAndReviewsAdapter extends ArrayAdapter<String> {

    private String[] Strings;
    private boolean isTrailers;
    private List<ReviewsDO> reviewsDO;

    TrailersAndReviewsAdapter(Context context, int resource, String[] Strings, boolean isTrailers) {
        super(context, resource, Strings);
        this.Strings = Strings;
        this.isTrailers = isTrailers;
    }

    TrailersAndReviewsAdapter(Context context, int resource, List<ReviewsDO> reviewsDO, boolean isTrailers) {
        super(context, resource);
        this.reviewsDO = reviewsDO;
        this.isTrailers = isTrailers;
    }


    @SuppressLint({"SetTextI18n", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (isTrailers) {
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.pm2_trailer_list_item, null);
            }
            TextView tt1 = (TextView) v.findViewById(R.id.pm2_trailer_tv);
            if (tt1 != null) {
                position++;
                tt1.setText("Trailer " + position);
            }
            final int finalPosition = position;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(Strings[finalPosition - 1]));
                    getContext().startActivity(i);

                }
            });
        }else {
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.pm2_review_list_item, null);
            }
            TextView tt1 = (TextView) v.findViewById(R.id.pm2_author_tv);
            if (tt1 != null) {
                tt1.setText(reviewsDO.get(position).getAuthor());
            }
            TextView tt2 = (TextView) v.findViewById(R.id.pm2_content_tv);
            if (tt2 != null) {
                tt2.setText(reviewsDO.get(position).getContent());
            }
        }

        return v;
    }

    @Override
    public int getCount() {
        if(isTrailers)
            return super.getCount();
        else
            return reviewsDO.size();
    }
}