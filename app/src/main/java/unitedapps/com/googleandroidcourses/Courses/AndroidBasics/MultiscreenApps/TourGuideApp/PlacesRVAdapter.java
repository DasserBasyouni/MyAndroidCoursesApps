package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.TourGuideApp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 18-Oct-17.
 */

public class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.ViewHolder>{

    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private int selectedItem = UNSELECTED;

    private PlacesDataObject[] dataList;
    private TextView name_tv, rate_or_stars_tv, description_tv, address_tv;
    private ImageView place_iv;

    PlacesRVAdapter(RecyclerView places_rv, PlacesDataObject[] dataList) {
        recyclerView = places_rv;
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener   {

        private ExpandableLayout expandableLayout;
        private LinearLayout expanding_ll;
        private int position;

        ViewHolder(View itemView) {
            super(itemView);



            expandableLayout = itemView.findViewById(R.id.ab_ma_tg_el);
            expandableLayout.setInterpolator(new OvershootInterpolator());
            expandableLayout.setOnExpansionUpdateListener(this);

            expanding_ll = itemView.findViewById(R.id.ab_ma_tg_expanding_ll);
            expanding_ll.setOnClickListener(this);
        }

        void bind(int position) {
            this.position = position;

            expanding_ll.setSelected(false);
            expandableLayout.collapse(false);

            name_tv.setText(dataList[position].getName());
            rate_or_stars_tv.setText(dataList[position].getStarsOrRate());
            description_tv.setText(dataList[position].getDesc());
            address_tv.setText(dataList[position].getAddress());
            place_iv.setImageResource(dataList[position].getImageRes());
        }

        @Override
        public void onClick(View view) {
            // this method to expand and collapse the expandable view
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.expanding_ll.setSelected(false);
                holder.expandableLayout.collapse();
            }

            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                expanding_ll.setSelected(true);
                expandableLayout.expand();
                selectedItem = position;
            }
        }

        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            Log.d("ExpandableLayout", "State: " + state);
            recyclerView.smoothScrollToPosition(getAdapterPosition());
        }
    }

    @Override
    public PlacesRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.ab_ma_tg_expanding_view, parent, false);

        name_tv = v.findViewById(R.id.ab_ma_tg_name_tv);
        rate_or_stars_tv = v.findViewById(R.id.ab_ma_tg_rate_or_stars_tv);
        description_tv = v.findViewById(R.id.ab_ma_tg_description_tv);
        address_tv = v.findViewById(R.id.ab_ma_tg_address_tv);
        place_iv = v.findViewById(R.id.ab_ma_tg_place_iv);

        return new PlacesRVAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlacesRVAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
