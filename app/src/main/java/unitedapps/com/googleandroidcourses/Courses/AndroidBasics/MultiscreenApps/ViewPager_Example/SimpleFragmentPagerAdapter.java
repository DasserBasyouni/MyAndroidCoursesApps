/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.ViewPager_Example;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DayFragment dayFragment = new DayFragment();
        Bundle b = new Bundle();

        if (position == 0) {
            b.putString("day", "Monday");
        } else if (position == 1){
            b.putString("day", "Tuesday");
        } else if (position == 2){
            b.putString("day", "Wednesday");
        } else if (position == 3){
            b.putString("day", "Thursday");
        } else {
            b.putString("day", "Friday");
        }

        dayFragment.setArguments(b);
        return dayFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}