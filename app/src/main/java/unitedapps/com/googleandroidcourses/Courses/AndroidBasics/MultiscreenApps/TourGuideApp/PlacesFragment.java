package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.TourGuideApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 18-Oct-17.
 */

public class PlacesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getLayoutInflater().inflate(R.layout.ab_ma_tg_fragment_places, container,false);

        RecyclerView places_rv = rootView.findViewById(R.id.ab_ma_tg_places_rv);
        places_rv.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        places_rv.setLayoutManager(mLayoutManager);

        places_rv.setAdapter(new PlacesRVAdapter(places_rv, createDataList()));

        return rootView;

    }

    // this method create the attractions data according to the argument "p"that'ss is sent with creating the fragment
    private PlacesDataObject[] createDataList() {
        PlacesDataObject[] placesDataObjects = new PlacesDataObject[5];
        int p = getArguments().getInt("p");

        switch (p){
            case 0:
                placesDataObjects[0] = new PlacesDataObject(getString(R.string.ab_ma_tg_hotel_1_name), getString(R.string.ab_ma_tg_hotel_1_location)
                        , getString(R.string.ab_ma_tg_hotel_1_desc), R.drawable.ab_ma_tg_hotel_1, getString(R.string.ab_ma_tg_hotel_1_stars) );
                placesDataObjects[1] = new PlacesDataObject(getString(R.string.ab_ma_tg_hotel_2_name), getString(R.string.ab_ma_tg_hotel_2_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_hotel_2, getString(R.string.ab_ma_tg_hotel_2_stars) );
                placesDataObjects[2] = new PlacesDataObject(getString(R.string.ab_ma_tg_hotel_3_name), getString(R.string.ab_ma_tg_hotel_3_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_hotel_3, getString(R.string.ab_ma_tg_hotel_3_stars) );
                placesDataObjects[3] = new PlacesDataObject(getString(R.string.ab_ma_tg_hotel_4_name), getString(R.string.ab_ma_tg_hotel_4_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_hotel_4, getString(R.string.ab_ma_tg_hotel_4_stars) );
                placesDataObjects[4] = new PlacesDataObject(getString(R.string.ab_ma_tg_hotel_5_name), getString(R.string.ab_ma_tg_hotel_5_location)
                        , getString(R.string.ab_ma_tg_hotel_5_desc), R.drawable.ab_ma_tg_hotel_5, getString(R.string.ab_ma_tg_hotel_5_rate) );
            case 1:
                placesDataObjects[0] = new PlacesDataObject(getString(R.string.ab_ma_tg_res_1_name), getString(R.string.ab_ma_tg_res_1_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_res_1, getString(R.string.ab_ma_tg_res_1_rate) );
                placesDataObjects[1] = new PlacesDataObject(getString(R.string.ab_ma_tg_res_2_name), getString(R.string.ab_ma_tg_res_2_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_res_2, getString(R.string.ab_ma_tg_res_2_rate) );
                placesDataObjects[2] = new PlacesDataObject(getString(R.string.ab_ma_tg_res_3_name), getString(R.string.ab_ma_tg_res_3_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_res_3, getString(R.string.ab_ma_tg_res_3_rate) );
                placesDataObjects[3] = new PlacesDataObject(getString(R.string.ab_ma_tg_res_4_name), getString(R.string.ab_ma_tg_res_4_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_res_4, getString(R.string.ab_ma_tg_res_4_rate) );
                placesDataObjects[4] = new PlacesDataObject(getString(R.string.ab_ma_tg_res_5_name), getString(R.string.ab_ma_tg_res_5_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_res_5, getString(R.string.ab_ma_tg_res_5_rate) );
            case 2:
                placesDataObjects[0] = new PlacesDataObject(getString(R.string.ab_ma_tg_his_1_name), getString(R.string.ab_ma_tg_his_1_location)
                        , getString(R.string.ab_ma_tg_his_1_desc), R.drawable.ab_ma_tg_his_1, getString(R.string.ab_ma_tg_his_1_rate) );
                placesDataObjects[1] = new PlacesDataObject(getString(R.string.ab_ma_tg_his_2_name), getString(R.string.ab_ma_tg_his_2_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_his_2, getString(R.string.ab_ma_tg_his_2_rate) );
                placesDataObjects[2] = new PlacesDataObject(getString(R.string.ab_ma_tg_his_3_name), getString(R.string.ab_ma_tg_his_3_location)
                        , getString(R.string.ab_ma_tg_his_3_desc), R.drawable.ab_ma_tg_his_3, getString(R.string.ab_ma_tg_his_3_rate) );
                placesDataObjects[3] = new PlacesDataObject(getString(R.string.ab_ma_tg_his_4_name), getString(R.string.ab_ma_tg_his_4_location)
                        , getString(R.string.ab_ma_tg_his_4_desc), R.drawable.ab_ma_tg_his_4, getString(R.string.ab_ma_tg_his_4_rate) );
                placesDataObjects[4] = new PlacesDataObject(getString(R.string.ab_ma_tg_his_5_name), getString(R.string.ab_ma_tg_his_5_location)
                        , getString(R.string.ab_ma_tg_his_5_desc), R.drawable.ab_ma_tg_his_5, getString(R.string.ab_ma_tg_his_5_rate) );
            case 3:
                placesDataObjects[0] = new PlacesDataObject(getString(R.string.ab_ma_tg_cafe_1_name), getString(R.string.ab_ma_tg_cafe_1_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_cafe_1, getString(R.string.ab_ma_tg_cafe_1_rate) );
                placesDataObjects[1] = new PlacesDataObject(getString(R.string.ab_ma_tg_cafe_2_name), getString(R.string.ab_ma_tg_cafe_2_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_cafe_2, getString(R.string.ab_ma_tg_cafe_2_rate) );
                placesDataObjects[2] = new PlacesDataObject(getString(R.string.ab_ma_tg_cafe_3_name), getString(R.string.ab_ma_tg_cafe_3_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_cafe_3, getString(R.string.ab_ma_tg_cafe_3_rate) );
                placesDataObjects[3] = new PlacesDataObject(getString(R.string.ab_ma_tg_cafe_4_name), getString(R.string.ab_ma_tg_cafe_4_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_cafe_4, getString(R.string.ab_ma_tg_cafe_4_rate) );
                placesDataObjects[4] = new PlacesDataObject(getString(R.string.ab_ma_tg_cafe_5_name), getString(R.string.ab_ma_tg_cafe_5_location)
                        , getString(R.string.ab_ma_tg_place_no_desc), R.drawable.ab_ma_tg_cafe_5, getString(R.string.ab_ma_tg_cafe_5_rate) );
        }
        return placesDataObjects;
    }

}
