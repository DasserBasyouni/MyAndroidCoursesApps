package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L1;

/**
   Created by dasse on 22-Oct-17.
 */

class EarthquakeDataObject {
    private String place, url;
    private long timeInMilliseconds;
    private double mag;

    EarthquakeDataObject(double mag, String place, long timeInMilliseconds, String url) {
        this.mag = mag;
        this.place = place;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    double getMag() {
        return mag;
    }

    String getPlace() {
        return place;
    }

    long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
