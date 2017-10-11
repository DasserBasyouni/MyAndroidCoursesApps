package unitedapps.com.googleandroidcourses.Courses.GoogleLocationService.ActivityRecognition;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import unitedapps.com.googleandroidcourses.R;

import static android.R.string.no;

/**
   Created by DB-Project on 9/4/2017.
*/

public class ar_DetectedActivitiesIntentService extends IntentService implements GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener {

    protected static final String ar_TAG = "detection_is";
    GoogleApiClient googleApiClient;

    public ar_DetectedActivitiesIntentService() {
        super(ar_TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            //EventBus.getDefault().postSticky(String.valueOf(result.getProbableActivities()));
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("Z_" + ar_TAG, "connected");
        Intent intent = new Intent( this, ar_DetectedActivitiesIntentService.class );
        PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( googleApiClient, 3000, pendingIntent );
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        StringBuilder s = new StringBuilder(100);
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    s.append("In Vehicle: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    s.append("On Bicycle: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    s.append("On Foot: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.RUNNING: {
                    s.append("Running: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.STILL: {
                    s.append("Still: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.TILTING: {
                    s.append("Tilting: ").append(activity.getConfidence()).append("\n");
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() + "\n" );
                    break;
                }
                case DetectedActivity.WALKING: {
                    s.append("Walking: ").append(activity.getConfidence()).append("\n");
                    Log.e(no + "ActivityRecogition", "Walking: " + activity.getConfidence() + "\n" );
                    if( activity.getConfidence() >= 75 ) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                        builder.setContentText( "Are you walking?" );
                        builder.setSmallIcon( R.mipmap.ic_launcher );
                        builder.setContentTitle( getString( R.string.app_name ) );
                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    s.append("Unknown: ").append(activity.getConfidence());
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence()  + "\n" );
                    break;
                }
            }
        }
        EventBus.getDefault().postSticky(s.toString());
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("Z_" + ar_TAG, "connection has ben suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("Z_" + ar_TAG, "connection failed");
    }

}
