package unitedapps.com.googleandroidcourses.OldClasses;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface.HappyBirthdayCard;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.AdvJustJava;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Cookies;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.CourtCounter;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.JustJava;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Menu;
import unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners.Sunshine;
import unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L2.Sunshine_L2;

/**
     Created by Dasser on 05-Jun-17.
*/

public class MainData {
    private String[] CourseName = {"Android Development for Beginners", "Developing Android Apps"};

    private Uri[] CourseUri = {Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837")
            , Uri.parse("https://www.udacity.com/course/android-development-for-beginners--ud837")};

    private HashMap<Integer, HashMap<Integer, Activity[]>> LessonNumberAndApps;


    {
        LessonNumberAndApps = new HashMap<>();
        EasyAddToHash(1, 1, new Activity[]{new HappyBirthdayCard()});
        EasyAddToHash(1, 2, new Activity[]{new JustJava(), new CourtCounter()});
        EasyAddToHash(1, 3, new Activity[]{new AdvJustJava(), new CourtCounter(), new Cookies(), new Menu()});

        EasyAddToHash(2, 1, new Activity[]{new Sunshine()});
        EasyAddToHash(2, 2, new Activity[]{new Sunshine_L2()});
        //EasyAddToHash(2, 3, new Activity[] {});
        //EasyAddToHash(2, 4, new Activity[] {});
        //EasyAddToHash(2, 5, new Activity[] {});
        //EasyAddToHash(2, 6, new Activity[] {});
    }

    void testttt(Context context){
        try
        {
            String jsonLocation = AssetJSONFile("unitedapps/com/googleandroidcourses/OldClasses/data.json", context);
            JSONObject jsonobject = new JSONObject(jsonLocation);
            JSONArray jarray = jsonobject.getJSONArray("2");
            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jb =(JSONObject) jarray.get(i);
                String formula = jb.getString("1");
                String url = jb.getString("2");

                Log.i("Z_", formula + url);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static String AssetJSONFile(String filename, Context context) throws IOException {
        InputStream file = context.getAssets().open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

    private void EasyAddToHash(int CourseNum, final int LessonNum, final Activity[] AppsActivities) {
        LessonNumberAndApps.put(CourseNum, new HashMap<Integer, Activity[]>() {{put(LessonNum, AppsActivities );}});
    }


    public String[] getCourseName() {
        return CourseName;
    }

    public Uri[] getCourseUri() {
        return CourseUri;
    }

    public int getLessonCount(int position) {
        Log.i("Z_", String.valueOf(LessonNumberAndApps.get(1).keySet()));
        Log.i("Z_", String.valueOf(LessonNumberAndApps.get(2).keySet()));


        // first +1 is for avoiding  0-placed position, 2nd +1 is for getting the count start from 1 not 0
        return LessonNumberAndApps.get(position+1).size()+1;
    }
}
