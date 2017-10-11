package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Sunshine;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C2Lesson1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c2_lesson1);
    }

    public void openSunshine_app(View view) {
        Intent intent = new Intent(this, Sunshine.class);
        startActivity(intent);
    }

}
