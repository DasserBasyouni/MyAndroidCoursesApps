package unitedapps.com.googleandroidcourses.OldClasses.CoursesList;


import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.R;

/**
     Created by Dasser on 03-Jun-17.
*/

@SuppressLint("ValidFragment")
public class CoursesListold extends Fragment {

    ArrayList<CoursesListDataObject> coursesListDataObject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m_courses_epl, container, false);

        coursesListDataObject = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lessons_rv);
        RecyclerView.Adapter adapter = new CoursesListAdapter(coursesListDataObject, getActivity()
                , getActivity().getSupportFragmentManager(), ((AppCompatActivity)getActivity()).getSupportActionBar());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        coursesListDataObject.add(new CoursesListDataObject("Android Development for Beginners"
                , Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837")));

        coursesListDataObject.add(new CoursesListDataObject("Developing Android Apps"
                , Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837")));
        adapter.notifyDataSetChanged();

        return view;
    }
}
