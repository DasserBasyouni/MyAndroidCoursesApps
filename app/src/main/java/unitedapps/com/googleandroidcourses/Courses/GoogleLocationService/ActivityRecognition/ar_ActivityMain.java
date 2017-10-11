package unitedapps.com.googleandroidcourses.Courses.GoogleLocationService.ActivityRecognition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by DB-Project on 9/4/2017.
*/

public class ar_ActivityMain extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener{
    TextView detectedActivities;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gls_ar_main_activity);
        detectedActivities = (TextView) findViewById(R.id.detectedActivities);
    }

    public void requestActivityUpdatesButtonHandler(View view) {
        //EventBus.getDefault().register(this);
        startService(new Intent(this, ar_DetectedActivitiesIntentService.class));
    }

    public void removeActivityUpdatesButtonHandler(View view) {
        //EventBus.getDefault().unregister(this);
        stopService(new Intent(this, ar_DetectedActivitiesIntentService.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String string) {
        Log.i("Z_", string);
        detectedActivities.setText(string);
    };


    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
