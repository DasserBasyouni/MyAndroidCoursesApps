package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C1_AndroidDevelopmentForBeginners extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c1_android_development_for_beginners);
    }

    public void CourseOneLessonOne(View view) {
        Intent intent = new Intent(this, C1Lesson1.class);
        startActivity(intent);
    }

    public void CourseOneLessonTwo(View view) {
        Intent intent = new Intent(this, C1Lesson2.class);
        startActivity(intent);
    }

    public void CourseOneLessonThree(View view) {
        Intent intent = new Intent(this, C1Lesson3.class);
        startActivity(intent);
    }
}
