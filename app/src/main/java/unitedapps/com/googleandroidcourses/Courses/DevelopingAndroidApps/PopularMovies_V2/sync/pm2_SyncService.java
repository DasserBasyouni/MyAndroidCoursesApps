package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9.sync.ss9_SyncAdapter;

public class pm2_SyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static ss9_SyncAdapter sSunshineSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("SunshineSyncService", "onCreate - SunshineSyncService");
        synchronized (sSyncAdapterLock) {
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new ss9_SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSunshineSyncAdapter.getSyncAdapterBinder();
    }
}