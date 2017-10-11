package unitedapps.com.googleandroidcourses.OldClasses.CoursesList;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import unitedapps.com.googleandroidcourses.OldClasses.LessonsList.LessonsList;
import unitedapps.com.googleandroidcourses.R;
import unitedapps.com.googleandroidcourses.Utils.FragmentUtils;

/**
     Created by Dasser on 03-Jun-17.
*/

class CoursesListAdapter extends RecyclerView.Adapter<CoursesListAdapter.MyViewHolder> {

    private FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private Activity activity;
    private ArrayList<CoursesListDataObject> coursesListDataObject = new ArrayList<>();

    CoursesListAdapter(ArrayList<CoursesListDataObject> coursesListDataObject, Activity activity, FragmentManager fragmentManager, ActionBar actionBar) {
        this.fragmentManager = fragmentManager;
        this.actionBar = actionBar;
        this.coursesListDataObject = coursesListDataObject;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_courses_cv, parent, false);
        fragmentUtils = new FragmentUtils();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.button.setText(coursesListDataObject.get(holder.getAdapterPosition()).getLessonName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, coursesListDataObject.get(holder.getAdapterPosition()).getUri());
                activity.startActivity(intent);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentUtils.replaceFragmentAndChangeActionBar(fragmentManager, R.id.frame_container
                        , new LessonsList(holder.getAdapterPosition()), activity
                        , coursesListDataObject.get(holder.getAdapterPosition()).getLessonName(), actionBar, true);
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentUtils.replaceFragmentAndChangeActionBar(fragmentManager, R.id.frame_container
                        , new LessonsList(holder.getAdapterPosition()), activity
                        , coursesListDataObject.get(holder.getAdapterPosition()).getLessonName(), actionBar, true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coursesListDataObject.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView button;
        CardView cardView;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            button = (TextView) itemView.findViewById(R.id.m_courses_tv);
            cardView = (CardView) itemView.findViewById(R.id.m_courses_cv);
            imageView = (ImageView) itemView.findViewById(R.id.m_courses_iv);
        }
    }
}
