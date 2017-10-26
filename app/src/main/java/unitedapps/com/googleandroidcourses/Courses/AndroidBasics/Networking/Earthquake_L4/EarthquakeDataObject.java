package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.Earthquake_L4;

/**
   Created by dasse on 22-Oct-17.
 */

class EarthquakeDataObject {
    private String location, url;
    private long timeInMilliseconds;
    private double mag;

    EarthquakeDataObject(double mag, String location, long timeInMilliseconds, String url) {
        this.mag = mag;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    double getMag() {
        return mag;
    }

    String getLocation() {
        return location;
    }

    long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
