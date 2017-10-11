package unitedapps.com.googleandroidcourses.OldClasses.AppsList;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import unitedapps.com.googleandroidcourses.R;

/**
     Created by Dasser on 03-Jun-17.
*/

@SuppressLint("ValidFragment")
public class AppsList extends Fragment {
    int position;

    public AppsList(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m_apps_rv, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lessons_rv);
        //RecyclerView.Adapter adapter = new AppsListAdapter(appsListDataObject, getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);

        return view;
    }
}
