package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by DB-Project on 8/24/2017.
*/

public class pm2_MainActivity extends AppCompatActivity {

    public boolean twoPanes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm2_activity_m_main);

        twoPanes = findViewById(R.id.pm2_details_frame) != null;
        Log.i("Z_ 2p 1", String.valueOf(twoPanes));

        if (twoPanes) {
            Fragment fragment = new pm2_MainFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("2p", twoPanes);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.pm2_details_frame, fragment, "DETAILS_FRAGMENT_TAG")
                    .commit();
        }

    }
}
