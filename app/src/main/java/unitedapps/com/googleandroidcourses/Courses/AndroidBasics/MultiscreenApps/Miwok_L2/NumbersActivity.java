package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L2;

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
        setContentView(R.layout.ab_ma_m2_words_list);


        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("One", "Lutti"));
        words.add(new Words("Two", "Otiiko"));
        words.add(new Words("Three", "Tolookosu"));
        words.add(new Words("Four", "Oyyisa"));
        words.add(new Words("Five", "Massokka"));
        words.add(new Words("Six", "Temmokka"));
        words.add(new Words("Seven", "Kenekaku"));
        words.add(new Words("Eight", "Kawinta"));
        words.add(new Words("Nine", "wo’e"));
        words.add(new Words("Ten", "na’aacha"));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words);
        ListView listView = findViewById(R.id.ab_ma_m2_list_view);
        listView.setAdapter(wordsAdapter);
    }
}
