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

public class ColorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma2_words_list);

        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("red", "weṭeṭṭi"));
        words.add(new Words("green", "chokokki"));
        words.add(new Words("brown", "ṭakaakki"));
        words.add(new Words("gray", "ṭopoppi"));
        words.add(new Words("black", "kululli"));
        words.add(new Words("white", "kelelli"));
        words.add(new Words("dusty yellow", "ṭopiisә"));
        words.add(new Words("mustard yellow", "chiwiiṭә"));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);
    }
}
