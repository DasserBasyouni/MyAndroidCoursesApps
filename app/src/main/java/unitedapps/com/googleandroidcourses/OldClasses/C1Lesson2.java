package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.CourtCounter;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.JustJava;
import unitedapps.com.googleandroidcourses.R;

/**
    Created by Fujutsu on 31-Jan-16.
*/

public class C1Lesson2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c1_lesson2);
    }

    public void OpenJJapp(View view) {
        Intent intent = new Intent(this, JustJava.class);
        startActivity(intent);
    }

    public void OpenCCapp(View view) {
        Intent intent = new Intent(this, CourtCounter.class);
        startActivity(intent);
    }
}
