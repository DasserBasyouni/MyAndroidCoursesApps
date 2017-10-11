package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L8;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import unitedapps.com.googleandroidcourses.R;

public class ss8_MainActivity extends AppCompatActivity implements ss8_ForecastFragment.ss8_Callback {

    String mLocation;
    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = ss8_Utility.ss8_getPreferredLocation(this);

        setContentView(R.layout.ss8_activity_main);
        if (findViewById(R.id.weather_detail_container) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.weather_detail_container, new ss8_DetailFragment(), "DETAILFRAGMENT_TAG")
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }
        ss8_ForecastFragment ss8_FF = ((ss8_ForecastFragment) getSupportFragmentManager()
                .findFragmentById(R.id.ss8_fragment_forecast));
        ss8_FF.setUseTodayLayout(!mTwoPane);
    }

    @Override
    public void onItemSelected(Uri dateUri) {
        if(mTwoPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.weather_detail_container, newInstanceFor_DF(dateUri), "DETAILFRAGMENT_TAG")
                    .commit();
        }else{
            Intent intent = new Intent(this, ss8_DetailsActivity.class).setData(dateUri);
            startActivity(intent);
        }
    }

    public static ss8_DetailFragment newInstanceFor_DF(Uri uri) {
        ss8_DetailFragment f = new ss8_DetailFragment();
        Bundle args = new Bundle();
        args.putString("uri", String.valueOf(uri));
        f.setArguments(args);
        return f;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String location = ss8_Utility.ss8_getPreferredLocation( this );
        ss8_ForecastFragment ff = null;
        // update the location in our second pane using the fragment manager
        if (location != null && !location.equals(mLocation))
            ff = (ss8_ForecastFragment)getSupportFragmentManager().findFragmentById(R.id.ss8_fragment_forecast);
        if ( null != ff ) {
            ff.onLocationChanged();
        }
        ss8_DetailFragment df = (ss8_DetailFragment)getSupportFragmentManager().findFragmentByTag("DETAILFRAGMENT_TAG");
        if ( null != df ) {
            df.onLocationChanged(location);
        }
        mLocation = location;
    }

}