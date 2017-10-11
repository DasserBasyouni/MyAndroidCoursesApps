package unitedapps.com.googleandroidcourses.Courses.GoogleLocationService.Geofencing;

import java.util.HashMap;

/**
   Created by DB-Project on 9/17/2017.
*/

final class gf_Constants {

    private gf_Constants() {}

    static final float GEOFENCE_RADIUS_IN_METERS = 5;
    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 50000;

    static final HashMap<String, LatLng> Home_AREA_LANDMARKS = new HashMap<>();

    static {
        Home_AREA_LANDMARKS.put("myHome", new LatLng(31.204190, 29.877638));
    }
}
