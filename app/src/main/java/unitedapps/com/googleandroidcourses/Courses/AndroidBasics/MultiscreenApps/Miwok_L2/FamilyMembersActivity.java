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

public class FamilyMembersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma2_words_list);

        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("father", "әpә"));
        words.add(new Words("mother", "әṭa"));
        words.add(new Words("son", "angsi"));
        words.add(new Words("daughter", "tune"));
        words.add(new Words("older brother", "taachi"));
        words.add(new Words("younger brother", "chalitti"));
        words.add(new Words("older sister", "teṭe"));
        words.add(new Words("younger sister", "kolliti"));
        words.add(new Words("grandmother", "ama"));
        words.add(new Words("grandfather", "paapa"));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);
    }
}
