package unitedapps.com.googleandroidcourses.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import unitedapps.com.googleandroidcourses.OldClasses.C2_DevelopingAndroidApps;
import unitedapps.com.googleandroidcourses.OldClasses.AppsList.AppsList;
import unitedapps.com.googleandroidcourses.OldClasses.MainData;
import unitedapps.com.googleandroidcourses.R;
import unitedapps.com.googleandroidcourses.Utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {

    FragmentUtils fragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentUtils = new FragmentUtils();

        //new MainData().testttt(getApplicationContext());

        fragmentUtils.replaceFragmentAndChangeActionBar(getSupportFragmentManager(), R.id.frame_container
                , new CoursesList(), this, "Google Courses Apps", getSupportActionBar(), false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This Button Will Provide Developer Details", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.ss3_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void CourseNumberOne(View view){
        fragmentUtils.replaceFragmentAndChangeActionBar(getSupportFragmentManager(), R.id.frame_container
                , new AppsList(0), this, "Course one Name xD", getSupportActionBar(), true);

    }
    public void OpenCourseOneLink(View view) {
        Uri uri = Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void CourseNumberTwo(View view){
        Intent intent = new Intent(this, C2_DevelopingAndroidApps.class);
        startActivity(intent);
    }
    public void OpenCourseTwoLink(View view) {
        Uri uri = Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
