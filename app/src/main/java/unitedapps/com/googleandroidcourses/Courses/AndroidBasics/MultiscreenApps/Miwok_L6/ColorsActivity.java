package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.Miwok_L6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 05-Oct-17.
 */

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_m6_activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ab_ma_m6_container, new ColorsFragment())
                .commit();

    }
}