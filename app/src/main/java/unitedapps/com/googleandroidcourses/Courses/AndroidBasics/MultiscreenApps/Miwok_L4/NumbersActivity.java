package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
  Created by dasse on 05-Oct-17.
 */

public class NumbersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma2_words_list);


        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("One", "Lutti", R.drawable.ab_ma_ml2_number_one));
        words.add(new Words("Two", "Otiiko", R.drawable.ab_ma_ml2_number_two));
        words.add(new Words("Three", "Tolookosu", R.drawable.ab_ma_ml2_number_three));
        words.add(new Words("Four", "Oyyisa", R.drawable.ab_ma_ml2_number_four));
        words.add(new Words("Five", "Massokka", R.drawable.ab_ma_ml2_number_five));
        words.add(new Words("Six", "Temmokka", R.drawable.ab_ma_ml2_number_six));
        words.add(new Words("Seven", "Kenekaku", R.drawable.ab_ma_ml2_number_seven));
        words.add(new Words("Eight", "Kawinta", R.drawable.ab_ma_ml2_number_eight));
        words.add(new Words("Nine", "wo’e", R.drawable.ab_ma_ml2_number_nine));
        words.add(new Words("Ten", "na’aacha", R.drawable.ab_ma_ml2_number_ten));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_numbers);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);
    }
}
