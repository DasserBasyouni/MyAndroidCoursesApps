package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import unitedapps.com.googleandroidcourses.R;

public class Sunshine_L2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_app_vl2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ss_frame_container, new ForecastFragment())
                    .commit();
        }
    }

}