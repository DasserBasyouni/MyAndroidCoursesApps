package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.TourGuideApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
   Created by dasse on 18-Oct-17.
 */

public class PlacesPagerAdapter extends FragmentPagerAdapter {

    PlacesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PlacesFragment placesFragment = new PlacesFragment();

        Bundle b = new Bundle();
        b.putInt("p", position);

        placesFragment.setArguments(b);
        return placesFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Hotels";
            case 1:
                return "Restaurants";
            case 2:
                return "Historical Places";
            default:
                return "Enjoyable Places";
        }
    }
}
