package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
    Created by Dasser on 11-Jun-17.
*/

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss3_details_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ss3_container, new ss3_PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ss3_details, menu);
        menu.findItem(R.id.ss3_action_refresh).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ss3_action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ss3_PlaceholderFragment extends Fragment {
        TextView ss3_weather_tv;
        ShareActionProvider mShareActionProvider;

        public ss3_PlaceholderFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.ss3_fragment_detail, container, false);

            Bundle extras = getActivity().getIntent().getExtras();
            if(extras != null) {
                Log.i("Z_", extras.getString("data"));
                ss3_weather_tv = (TextView) rootView.findViewById(R.id.ss3_data_tv);
                ss3_weather_tv.setText(extras.getString("data"));
            } else {
                Log.i("Z_", "extras.getString is null");
            }

            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.ss3_details, menu);
            MenuItem item = menu.findItem(R.id.ss3_action_share);

            // Fetch and store ShareActionProvider
            ShareActionProvider mShareActionProvider;
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ss3_action_settings:
                    startActivity(new Intent(getActivity(), SettingsActivity.class));
                    return true;
                case R.id.ss3_action_share:
                    ShareWithFriends();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        private void setShareIntent(Intent shareIntent) {
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(shareIntent);
            }
        }

        private void ShareWithFriends() {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, ss3_weather_tv.getText() + "#SunShine App");
            sendIntent.setType("text/plain");
            setShareIntent(sendIntent);
            startActivity(sendIntent);
        }
    }
}
