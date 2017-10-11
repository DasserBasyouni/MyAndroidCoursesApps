package unitedapps.com.googleandroidcourses.OldClasses.CoursesList;

import android.net.Uri;

/**
     Created by Dasser  on 03-Jun-17.
*/

class CoursesListDataObject {

    private String text;
    private Uri uri;


    CoursesListDataObject(String text, Uri uri) {
        this.text = text;
        this.uri = uri;
    }

    Uri getUri() {
        return uri;
    }

    String getLessonName() {
        return text;
    }
}
