package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import unitedapps.com.googleandroidcourses.R;

public class ss9_MainActivity extends AppCompatActivity implements ss9_ForecastFragment.ss9_Callback {

    String mLocation;
    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = ss9_Utility.ss9_getPreferredLocation(this);

        setContentView(R.layout.ss9_activity_main);
        if (findViewById(R.id.weather_detail_container) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.weather_detail_container, new ss9_DetailFragment(), "DETAILFRAGMENT_TAG")
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }

        ss9_ForecastFragment ss9_FF = ((ss9_ForecastFragment)getSupportFragmentManager()
                .findFragmentById(R.id.ss9_fragment_forecast));
        ss9_FF.setUseTodayLayout(!mTwoPane);
    }

    @Override
    public void onItemSelected(Uri dateUri) {
        if(mTwoPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.weather_detail_container, newInstanceFor_DF(dateUri), "DETAILFRAGMENT_TAG")
                    .commit();
        }else{
            Intent intent = new Intent(this, ss9_DetailsActivity.class).setData(dateUri);
            startActivity(intent);
        }
    }

    public static ss9_DetailFragment newInstanceFor_DF(Uri uri) {
        ss9_DetailFragment f = new ss9_DetailFragment();
        Bundle args = new Bundle();
        args.putString("uri", String.valueOf(uri));
        f.setArguments(args);
        return f;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String location = ss9_Utility.ss9_getPreferredLocation( this );
        if (location != null && !location.equals(mLocation)) {
            ss9_ForecastFragment ff = (ss9_ForecastFragment)getSupportFragmentManager().findFragmentById(R.id.ss9_fragment_forecast);
            if ( null != ff ) {
                ff.onLocationChanged();
            }
            ss9_DetailFragment df = (ss9_DetailFragment)getSupportFragmentManager().findFragmentByTag("DETAILFRAGMENT_TAG");
            if ( null != df ) {
                df.onLocationChanged(location);
            }
            mLocation = location;
        }
    }

}

