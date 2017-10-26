package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L4;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 22-Oct-17.
 */

class EarthquakeAdapter extends ArrayAdapter {

    private ArrayList<EarthquakeDataObject> earthquakeDataObject;
    private static final String LOCATION_SEPARATOR = " of ";

    EarthquakeAdapter(@NonNull Context context, ArrayList<EarthquakeDataObject> earthquakeDataObject) {
        super(context, 0);
        this.earthquakeDataObject = earthquakeDataObject;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.ab_n_list_item, parent, false);
        }

        final EarthquakeDataObject currentEarthquake = earthquakeDataObject.get(position);

        String formattedMagnitude = formatMagnitude(currentEarthquake.getMag());

        TextView mag_tv = view.findViewById(R.id.ab_n_mag_tv);
        mag_tv.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag_tv.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView date_tv = view.findViewById(R.id.ab_n_date_tv);
        date_tv.setText(formatData(dateObject));

        TextView time_tv = view.findViewById(R.id.ab_n_time_tv);
        time_tv.setText(formatTime(dateObject));

        String earthquakeFullPlace = currentEarthquake.getLocation();

        String locationOffset, primaryLocation;
        if (earthquakeFullPlace.contains(LOCATION_SEPARATOR)) {
            String[] parts = earthquakeFullPlace.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.ab_n_near_the);
            primaryLocation = earthquakeFullPlace;
        }

        TextView direction_tv = view.findViewById(R.id.ab_n_location_offset_tv);
        direction_tv.setText(locationOffset);

        TextView place_tv = view.findViewById(R.id.ab_n_location_tv);
        place_tv.setText(primaryLocation);

        return view;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.ab_n_magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.ab_n_magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.ab_n_magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.ab_n_magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.ab_n_magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.ab_n_magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.ab_n_magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.ab_n_magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.ab_n_magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.ab_n_magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private static String formatMagnitude(Double mag) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(mag);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private static String formatData(Date dataObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dataObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    @Override
    public int getCount() {
        return earthquakeDataObject.size();
    }

    @Nullable
    @Override
    public EarthquakeDataObject getItem(int position) {
        return earthquakeDataObject.get(position);
    }
}
