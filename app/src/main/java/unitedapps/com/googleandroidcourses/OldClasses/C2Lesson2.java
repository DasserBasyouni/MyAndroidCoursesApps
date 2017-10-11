package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L2.Sunshine_L2;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C2Lesson2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c2_lesson2);
    }

    public void openSunshine_app(View view) {
        Intent intent = new Intent(this, Sunshine_L2.class);
        startActivity(intent);
    }
}
