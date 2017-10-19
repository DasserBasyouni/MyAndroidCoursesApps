package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.TourGuideApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 18-Oct-17.
 */

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_ma_tg_activity_main);

        ViewPager tg_viewPager = findViewById(R.id.ab_ma_tg_view_pager);
        PlacesPagerAdapter placesPagerAdapter = new PlacesPagerAdapter(getSupportFragmentManager());
        tg_viewPager.setAdapter(placesPagerAdapter);

        TabLayout tg_tabLayout = findViewById(R.id.ab_ma_tg_tab_layout);
        tg_tabLayout.setupWithViewPager(tg_viewPager);
    }

}
