package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps.TourGuideApp;

/**
   Created by dasse on 19-Oct-17.
 */

public class PlacesDataObject {
    private String name, location, desc, rate;
    private int imageRes;

    PlacesDataObject(String name, String location, String desc, int imageRes, String rate) {
        this.name = name;
        this.location = location;
        this.desc = desc;
        this.imageRes = imageRes;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    String getAddress() {
        return location;
    }

    String getDesc() {
        return desc;
    }

    int getImageRes() {
        return imageRes;
    }

    String getStarsOrRate() {
        return rate;
    }
}
