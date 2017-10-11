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

public class PhrasesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma2_words_list);

        ArrayList<Words> words = new ArrayList<>();

        words.add(new Words("Where are you going?", "minto wuksus"));
        words.add(new Words("What is your name?", "tinnә oyaase'nә"));
        words.add(new Words("My name is...","oyaaset..."));
        words.add(new Words("How are you feeling?", "michәksәs?"));
        words.add(new Words("I’m feeling good.", "kuchi achit"));
        words.add(new Words("Are you coming?", "әәnәs'aa?"));
        words.add(new Words("Yes, I’m coming.","hәә’ әәnәm"));
        words.add(new Words("I’m coming.", "әәnәm"));
        words.add(new Words("Let’s go.", "yoowutis"));
        words.add(new Words("Come here.", "әnni'nem"));

        WordsAdapter wordsAdapter = new WordsAdapter(this, words, R.color.ab_ma_category_phrases);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);
    }
}
