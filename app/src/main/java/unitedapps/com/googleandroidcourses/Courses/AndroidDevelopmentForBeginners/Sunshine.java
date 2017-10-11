package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import unitedapps.com.googleandroidcourses.R;

public class Sunshine extends AppCompatActivity {

    String[] data = {
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_app);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.ss_app_list_item_forecast, data);

        ListView listView = (ListView) findViewById(R.id.container);
        listView.setAdapter(adapter);
    }
}