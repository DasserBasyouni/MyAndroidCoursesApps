package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import unitedapps.com.googleandroidcourses.R;


public class ss9_DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss9_details_activity);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            Log.i("Zx_", String.valueOf(getIntent().getData()));
            arguments.putString("uri", String.valueOf(getIntent().getData()));
            ss9_DetailFragment fragment = new ss9_DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.weather_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.ss3_action_settings) {
            startActivity(new Intent(this, ss9_SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}