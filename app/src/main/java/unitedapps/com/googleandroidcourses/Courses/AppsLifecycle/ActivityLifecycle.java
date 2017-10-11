package unitedapps.com.googleandroidcourses.Courses.AppsLifecycle;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import unitedapps.com.googleandroidcourses.R;

/**
  Created by DB-Project on 8/30/2017.
*/

public class ActivityLifecycle extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FrameLayout parent = new FrameLayout(getApplicationContext());
        parent.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                , FrameLayout.LayoutParams.MATCH_PARENT));
        parent.setId(R.id.lifecycle_fragment);

        Button btn = new Button(getApplicationContext());
        btn.setText("Fragment Lifecycle");
        btn.setGravity(Gravity.CENTER);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(parent.getId(), new FragmentLifecycle(), "DETAILS_FRAGMENT_TAG")
                        .commit();
            }
        });
        parent.addView(btn);

        setContentView(parent);

        Log.i("Z_a", "onCreate");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("Z_a", "onPostCreate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("Z_a", "onConfigurationChanged");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("Z_a", "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Z_a", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Z_a", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Z_a", "onDestroy");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        Log.i("Z_a", "onTitleChanged");
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        Log.i("Z_a", "onSupportActionModeStarted");
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        Log.i("Z_a", "onSupportActionModeFinished");
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(@NonNull ActionMode.Callback callback) {
        Log.i("Z_a", "onWindowStartingSupportActionMode");
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
        Log.i("Z_a", "onCreateSupportNavigateUpTaskStack");
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
        Log.i("Z_a", "onPrepareSupportNavigateUpTaskStack");
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.i("Z_a", "onSupportNavigateUp");
        return super.onSupportNavigateUp();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.i("Z_a", "onContentChanged");
    }

    @Override
    public void onSupportContentChanged() {
        super.onSupportContentChanged();
        Log.i("Z_a", "onSupportContentChanged");
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.i("Z_a", "onMenuOpened");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        Log.i("Z_a", "onPanelClosed");
        super.onPanelClosed(featureId, menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("Z_a", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}
