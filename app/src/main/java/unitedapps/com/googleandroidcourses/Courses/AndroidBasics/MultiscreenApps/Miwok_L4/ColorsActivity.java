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

public class ColorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_m2_words_list);

        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("red", "weṭeṭṭi", R.drawable.ab_ma_ml2_color_red));
        words.add(new Words("green", "chokokki", R.drawable.ab_ma_ml2_color_green));
        words.add(new Words("brown", "ṭakaakki", R.drawable.ab_ma_ml2_color_brown));
        words.add(new Words("gray", "ṭopoppi", R.drawable.ab_ma_ml2_color_gray));
        words.add(new Words("black", "kululli", R.drawable.ab_ma_ml2_color_black));
        words.add(new Words("white", "kelelli", R.drawable.ab_ma_ml2_color_white));
        words.add(new Words("dusty yellow", "ṭopiisә", R.drawable.ab_ma_ml2_color_dusty_yellow));
        words.add(new Words("mustard yellow", "chiwiiṭә", R.drawable.ab_ma_ml2_color_mustard_yellow));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_colors);
        ListView listView = findViewById(R.id.ab_ma_m2_list_view);
        listView.setAdapter(wordsAdapter);
    }
}
