package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.AdvJustJava;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Cookies;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.CourtCounter;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Menu;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C2Lesson5 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c2_lesson5);
    }

    public void OpenAdvJJapp(View view) {
        Intent intent = new Intent(this, AdvJustJava.class);
        startActivity(intent);
    }

    public void OpenAdvCCapp(View view) {
        Intent intent = new Intent(this, CourtCounter.class);
        startActivity(intent);
    }

    public void openCookiesApp(View view) {
        Intent intent = new Intent(this, Cookies.class);
        startActivity(intent);
    }

    public void openMenuApp(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
