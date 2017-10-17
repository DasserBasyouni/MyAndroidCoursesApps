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

public class FamilyMembersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_m2_words_list);

        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("father", "әpә", R.drawable.ab_ma_ml2_family_father));
        words.add(new Words("mother", "әṭa", R.drawable.ab_ma_ml2_family_mother));
        words.add(new Words("son", "angsi", R.drawable.ab_ma_ml2_family_son));
        words.add(new Words("daughter", "tune", R.drawable.ab_ma_ml2_family_daughter));
        words.add(new Words("older brother", "taachi", R.drawable.ab_ma_ml2_family_older_brother));
        words.add(new Words("younger brother", "chalitti", R.drawable.ab_ma_ml2_family_younger_brother));
        words.add(new Words("older sister", "teṭe", R.drawable.ab_ma_ml2_family_older_sister));
        words.add(new Words("younger sister", "kolliti", R.drawable.ab_ma_ml2_family_younger_sister));
        words.add(new Words("grandmother", "ama", R.drawable.ab_ma_ml2_family_grandmother));
        words.add(new Words("grandfather", "paapa", R.drawable.ab_ma_ml2_family_grandfather));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_family);
        ListView listView = findViewById(R.id.ab_ma_m2_list_view);
        listView.setAdapter(wordsAdapter);
    }
}
