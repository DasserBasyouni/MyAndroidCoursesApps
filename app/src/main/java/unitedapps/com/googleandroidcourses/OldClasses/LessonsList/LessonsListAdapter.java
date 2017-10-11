package unitedapps.com.googleandroidcourses.OldClasses.LessonsList;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.OldClasses.AppsList.AppsList;
import unitedapps.com.googleandroidcourses.R;
import unitedapps.com.googleandroidcourses.Utils.FragmentUtils;

/**
     Created by Dasser on 03-Jun-17.
*/

class LessonsListAdapter extends RecyclerView.Adapter<LessonsListAdapter.MyViewHolder> {

    private FragmentUtils fragmentUtils;
    private int listCount;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;
    private Activity activity;


    LessonsListAdapter(FragmentManager fragmentManager, int listCount
            , ActionBar actionBar, Activity activity) {
        this.fragmentManager = fragmentManager;
        this.listCount = listCount;
        this.actionBar = actionBar;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_lessons_cv, parent, false);
        fragmentUtils = new FragmentUtils();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText("Lesson " + getStringPosition(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentUtils.replaceFragmentAndChangeActionBar(fragmentManager, R.id.frame_container
                        , new AppsList(holder.getAdapterPosition()), activity, "Course one Name xD", actionBar, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCount;
    }

    private String getStringPosition(int position) {
        switch (position+1){
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            default: return "Unknown";
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.lessonNumber_tv);
            cardView = (CardView) itemView.findViewById(R.id.lesson_cv);
        }
    }
}
