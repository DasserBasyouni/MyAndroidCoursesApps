package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 07-Oct-17.
 */

class WordsAdapter extends ArrayAdapter<Words>{

    WordsAdapter(@NonNull Context context, ArrayList<Words> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.ab_ma_m2_list_items_layout, parent, false);
        }
        Words currentWord = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.english_tv);
        assert currentWord != null;
        nameTextView.setText(currentWord.getDefaultTranslation());

        TextView numberTextView = listItemView.findViewById(R.id.miwok_tv);
        numberTextView.setText(currentWord.getMiwokTranslation());

        return listItemView;
    }

}
