package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import unitedapps.com.googleandroidcourses.R;



public class pm2_DetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm2_details_activity);

        String mID = getIntent().getExtras().getString("id");


        Fragment fragment = new pm2_DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", mID);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.pm2_details_activity_farme, fragment, "DETAILS_FRAGMENT_TAG")
                .commit();
    }



}
