package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L6_BackupV1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 07-Oct-17.
 */

class WordsAdapter extends ArrayAdapter<Words>{

    private int backgroundColor;

    WordsAdapter(@NonNull Context context, ArrayList<Words> arrayList, int backgroundColor) {
        super(context, 0, arrayList);
        this.backgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.ab_ma_m5_list_items_layout, parent, false);
        }
        final Words currentWord = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.english_tv);
        assert currentWord != null;
        nameTextView.setText(currentWord.getDefaultTranslation());

        TextView numberTextView = listItemView.findViewById(R.id.miwok_tv);
        numberTextView.setText(currentWord.getMiwokTranslation());

        LinearLayout ab_ma_ml4_ll = listItemView.findViewById(R.id.ab_ma_ml5_ll);
        ImageView imageView = listItemView.findViewById(R.id.miwok_iv);

        if (currentWord.getImageResourceId() != 0) {
            imageView.setImageResource(currentWord.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        ab_ma_ml4_ll.setBackgroundResource(backgroundColor);

        return listItemView;
    }
}
