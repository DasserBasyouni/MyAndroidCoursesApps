package unitedapps.com.googleandroidcourses.Main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface.BusinessDetailsCard;
import unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface.HappyBirthdayCard;
import unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface.MyCardApp;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.AdvJustJava;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Cookies;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.CourtCounter;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.JustJava;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Menu;
import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L5.Sunshine_L5;
import unitedapps.com.googleandroidcourses.R;

/**
     Created by Dasser on 03-Jun-17.
*/

@SuppressLint("ValidFragment")
public class CoursesList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m_courses_epl, container, false);

        createExpandingList(view);
        return view;
    }

    private void createExpandingList(View view) {
        ExpandingList expandingList = view.findViewById(R.id.expanding_list_main);
        ExpandingItem item0old = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item0old.findViewById(R.id.title)).setText("Android Development for Beginners");
        item0old.createSubItems(6);

        View subItemZero0old = item0old.getSubItemView(0);
        TextView tv0old = subItemZero0old.findViewById(R.id.sub_title);
        tv0old.setText("HappyBirthdayCard App (L1)");
        tv0old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), HappyBirthdayCard.class);
                startActivity(i);
            }
        });

        View subItemOne0old = item0old.getSubItemView(1);
        TextView tv1old = subItemOne0old.findViewById(R.id.sub_title);
        tv1old.setText("Just Java App (L2)");
        tv1old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), JustJava.class);
                startActivity(i);
            }
        });

        View subItemTwo0old = item0old.getSubItemView(2);
        TextView tv2old = subItemTwo0old.findViewById(R.id.sub_title);
        tv2old.setText("Court Counter App (L2)");
        tv2old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CourtCounter.class);
                startActivity(i);
            }
        });

        View subItemThree0old = item0old.getSubItemView(3);
        TextView tv3old = subItemThree0old.findViewById(R.id.sub_title);
        tv3old.setText("Advanced Just Java App (L3)");
        tv3old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AdvJustJava.class);
                startActivity(i);
            }
        });

        View subItemFour0old = item0old.getSubItemView(4);
        TextView tv4old = subItemFour0old.findViewById(R.id.sub_title);
        tv4old.setText("Cookies (L3)");
        tv4old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Cookies.class);
                startActivity(i);
            }
        });

        View subItemFive0old = item0old.getSubItemView(5);
        TextView tv5old = subItemFive0old.findViewById(R.id.sub_title);
        tv5old.setText("Menu App (L3)");
        tv5old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Menu.class);
                startActivity(i);
            }
        });

        item0old.setIndicatorColorRes(R.color.m_pink);
        item0old.setIndicatorIconRes(R.drawable.m_ic_android);


        ExpandingItem item0 = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item0.findViewById(R.id.title)).setText("Android Basics: User Interface");
        item0.createSubItems(3);

        View subItemZero0 = item0.getSubItemView(0);
        TextView tv1 = subItemZero0.findViewById(R.id.sub_title);
        tv1.setText("HappyBirthdayCard App (L3)");
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), HappyBirthdayCard.class);
                startActivity(i);
            }
        });

        View subItemOne0 = item0.getSubItemView(1);
        TextView tv2 = subItemOne0.findViewById(R.id.sub_title);
        tv2.setText("My Card App (L3)");
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyCardApp.class);
                startActivity(i);
            }
        });

        View subItemTwo0 = item0.getSubItemView(2);
        TextView tv3 = subItemTwo0.findViewById(R.id.sub_title);
        tv3.setText("Business Details Card (L5)");
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), BusinessDetailsCard.class);
                startActivity(i);
            }
        });

        item0.setIndicatorColorRes(R.color.m_orange);
        item0.setIndicatorIconRes(R.drawable.m_ic_android);


        ExpandingItem item1 = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item1.findViewById(R.id.title)).setText("Android Basics: Multiscreen Apps");
        item1.createSubItems(6);

        View subItemZero00 = item1.getSubItemView(0);
        TextView tv00 = subItemZero00.findViewById(R.id.sub_title);
        tv00.setText(getString(R.string.ab_ma_m_app_name) + " (L1)");
        tv00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.Miwok_L1.MainActivity.class);
                startActivity(i);
            }
        });

        View subItemOne01 = item1.getSubItemView(1);
        TextView tv01 = subItemOne01.findViewById(R.id.sub_title);
        tv01.setText(getString(R.string.ab_ma_m_app_name) + " (L2)");
        tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.Miwok_L2.MainActivity.class);
                startActivity(i);
            }
        });

        View subItemOne02 = item1.getSubItemView(2);
        TextView tv02 = subItemOne02.findViewById(R.id.sub_title);
        tv02.setText(getString(R.string.ab_rc_app_name) + " (L3)");
        tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.ReportCard.class);
                startActivity(i);
            }
        });

        View subItemTwo03 = item1.getSubItemView(3);
        TextView tv03 = subItemTwo03.findViewById(R.id.sub_title);
        tv03.setText(getString(R.string.ab_ma_m_app_name) + " (L4)");
        tv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.Miwok_L4.MainActivity.class);
                startActivity(i);
            }
        });

        View subItemTwo04 = item1.getSubItemView(4);
        TextView tv04 = subItemTwo04.findViewById(R.id.sub_title);
        tv04.setText(getString(R.string.ab_ma_mp_app_name) + " (L5)");
        tv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.MediaPlayerApp.class);
                startActivity(i);
            }
        });

        View subItemTwo05 = item1.getSubItemView(6);
        TextView tv05 = subItemTwo05.findViewById(R.id.sub_title);
        tv05.setText(getString(R.string.ab_ma_m_app_name) + " (L5)");
        tv05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.AndroidBasics
                        .MultiscreenApps.Miwok_L5.MainActivity.class);
                startActivity(i);
            }
        });

        item1.setIndicatorColorRes(R.color.m_yellow);
        item1.setIndicatorIconRes(R.drawable.m_ic_android);


        ExpandingItem item3 = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item3.findViewById(R.id.title)).setText("Developing Android Apps");
        item3.createSubItems(10);

        View subItemZero4_0 = item3.getSubItemView(0);
        TextView tv4_0 = subItemZero4_0.findViewById(R.id.sub_title);
        tv4_0.setText(getString(R.string.ss_app_name) + " (L1)");
        tv4_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        //.Sunshine_L1.class);
                //startActivity(i);
                Toast.makeText(getContext(), "This version is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        View subItemOne4_1 = item3.getSubItemView(1);
        TextView tv4_1 = subItemOne4_1.findViewById(R.id.sub_title);
        tv4_1.setText(getString(R.string.ss_app_name) + " (L2)");
        tv4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .Sunshine_L2.Sunshine_L2.class);
                startActivity(i);
            }
        });

        View subItemOne4_2 = item3.getSubItemView(2);
        TextView tv4_2 = subItemOne4_2.findViewById(R.id.sub_title);
        tv4_2.setText(getString(R.string.ss_app_name) + " (L3)");
        tv4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .Sunshine_L3.Sunshine_L3.class);
                startActivity(i);
            }
        });

        View subItemZero4_3 = item3.getSubItemView(3);
        TextView tv4_3 = subItemZero4_3.findViewById(R.id.sub_title);
        tv4_3.setText(getString(R.string.pm1_app_name) + " (L4)");
        tv4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .PopularMovies_V1.pm1_MainActivity.class);
                startActivity(i);
            }
        });

        View subItemOne4_4 = item3.getSubItemView(4);
        TextView tv4_4 = subItemOne4_4.findViewById(R.id.sub_title);
        tv4_4.setText(getString(R.string.ss_app_name) + " (L5)");
        tv4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Sunshine_L5.class);
                startActivity(i);
            }
        });

        View subItemOne4_5 = item3.getSubItemView(5);
        TextView tv4_5 = subItemOne4_5.findViewById(R.id.sub_title);
        tv4_5.setText(getString(R.string.ss_app_name) + " (L6)");
        tv4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .Sunshine_L6.Sunshine_L6.class);
                startActivity(i);
            }
        });

        View subItemZero4_6 = item3.getSubItemView(6);
        TextView tv4_6 = subItemZero4_6.findViewById(R.id.sub_title);
        tv4_6.setText(getString(R.string.ss_app_name) + " (L7)");
        tv4_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                //        .Sunshine_L7.Miwok_L1.MainActivity.class);
                //startActivity(i);
                Toast.makeText(getContext(), "This version is under construction", Toast.LENGTH_SHORT).show();
            }
        });

        View subItemOne4_7 = item3.getSubItemView(7);
        TextView tv4_7 = subItemOne4_7.findViewById(R.id.sub_title);
        tv4_7.setText(getString(R.string.ss_app_name) + " (L8)");
        tv4_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You might experience an older version/bugs of sunshine cuz this " +
                        "version is under construction", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .Sunshine_L8.ss8_MainActivity.class);
                startActivity(i);
            }
        });

        View subItemOne4_8 = item3.getSubItemView(8);
        TextView tv4_8 = subItemOne4_8.findViewById(R.id.sub_title);
        tv4_8.setText(getString(R.string.ss_app_name) + " (L9)");
        tv4_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You might experience an older version/bugs of sunshine cuz this " +
                        "version is under construction", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .Sunshine_L9.ss9_MainActivity.class);
                startActivity(i);
            }
        });

        View subItemOne4_9 = item3.getSubItemView(9);
        TextView tv4_9 = subItemOne4_9.findViewById(R.id.sub_title);
        tv4_9.setText(getString(R.string.pm2_app_name) + " (L10)");
        tv4_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps
                        .PopularMovies_V2.pm2_MainActivity.class);
                startActivity(i);
            }
        });

        item3.setIndicatorColorRes(R.color.m_blue);
        item3.setIndicatorIconRes(R.drawable.m_ic_android);


        ExpandingItem item2 = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item2.findViewById(R.id.title)).setText("How to Use a Content Provider");
        item2.createSubItems(1);

        View subItemZero2 = item2.getSubItemView(0);
        ((TextView) subItemZero2.findViewById(R.id.sub_title)).setText("No Apps Made Here");

        item2.setIndicatorColorRes(R.color.m_purple);
        item2.setIndicatorIconRes(R.drawable.m_ic_android);


        ExpandingItem item4 = expandingList.createNewItem(R.layout.expanding_layout);
        ((TextView) item4.findViewById(R.id.title)).setText("Google Location Service");
        item4.createSubItems(4);

        View subItemZero5_0 = item4.getSubItemView(0);
        TextView tv5_0 = subItemZero5_0.findViewById(R.id.sub_title);
        tv5_0.setText(getString(R.string.gls_l1_app_name) + " (L2)");
        tv5_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.GoogleLocationService
                        .Location1.class);
                startActivity(i);
            }
        });

        View subItemOne5_1 = item4.getSubItemView(1);
        TextView tv5_1 = subItemOne5_1.findViewById(R.id.sub_title);
        tv5_1.setText(getString(R.string.gls_l2_1_app_name) + " (L3)");
        tv5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.GoogleLocationService
                        .Location2_1.class);
                startActivity(i);
            }
        });

        View subItemOne5_2 = item4.getSubItemView(2);
        TextView tv5_2 = subItemOne5_2.findViewById(R.id.sub_title);
        tv5_2.setText(getString(R.string.gls_ar_app_name) + " (L3)");
        tv5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.GoogleLocationService
                        .ActivityRecognition.ar_ActivityMain.class);
                startActivity(i);
            }
        });

        View subItemZero5_3 = item4.getSubItemView(3);
        TextView tv5_3 = subItemZero5_3.findViewById(R.id.sub_title);
        tv5_3.setText(getString(R.string.gls_gf_app_name) + " (L4)");
        tv5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), unitedapps.com.googleandroidcourses.Courses.GoogleLocationService
                        .Geofencing.gf_ActivityMain.class);
                startActivity(i);
            }
        });

        item4.setIndicatorColorRes(R.color.m_green);
        item4.setIndicatorIconRes(R.drawable.m_ic_android);
    }
}
