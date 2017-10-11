package unitedapps.com.googleandroidcourses.Courses.GoogleLocationService.Geofencing;

/**
   Created by DB-Project on 9/17/2017.
*/

class LatLng {
    private Double Lat;
    private Double Long;

    LatLng(Double lat, Double aLong) {
        Lat = lat;
        Long = aLong;
    }

    Double getLat() {
        return Lat;
    }

    public Double getLong() {
        return Long;
    }
}
