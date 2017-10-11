package unitedapps.com.googleandroidcourses.OldClasses.AppsList;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
     Created by Dasser on 03-Jun-17.
*/

class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.MyViewHolder> {

    private AppsListDataObject appsListDataObject;
    private Activity activity;


    AppsListAdapter(AppsListDataObject appsListDataObject, Activity activity) {
        this.appsListDataObject = appsListDataObject;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_lessons_cv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(appsListDataObject.getAppName());
        //holder.imageView.setBackgroundResource(appsListDataObject.getImageView());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, appsListDataObject.getaClass(holder.getAdapterPosition()));
                activity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return classList.length;
        return 0;
    }

    private String getStringPosition(int position) {
        switch (position){
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
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.m_apps_tv);
            cardView = (CardView) itemView.findViewById(R.id.m_apps_cv);
            imageView = (ImageView) itemView.findViewById(R.id.m_apps_iv);
        }
    }
}
