package unitedapps.com.googleandroidcourses.OldClasses.LessonsList;


import android.annotation.SuppressLint;
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

import unitedapps.com.googleandroidcourses.OldClasses.MainData;
import unitedapps.com.googleandroidcourses.R;

/**
     Created by Dasser on 03-Jun-17.
*/

@SuppressLint("ValidFragment")
public class LessonsList extends Fragment {

    int position;
    MainData mainData;

    public LessonsList(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m_lessons_rv, container, false);

        mainData = new MainData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lessons_rv);
        RecyclerView.Adapter adapter = new LessonsListAdapter(getActivity().getSupportFragmentManager()
                , mainData.getLessonCount(position), ((AppCompatActivity)getActivity()).getSupportActionBar(), getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
