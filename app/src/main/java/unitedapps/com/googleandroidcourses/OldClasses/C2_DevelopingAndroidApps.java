package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C2_DevelopingAndroidApps extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c2_developing_android_apps);
    }

    public void CourseTwoLessonOne(View view) {
        Intent intent = new Intent(this, C2Lesson1.class);
        startActivity(intent);
    }

    public void CourseTwoLessonTwo(View view) {
        Intent intent = new Intent(this, C2Lesson2.class);
        startActivity(intent);
    }

    public void CourseTwoLessonThree(View view) {
        Intent intent = new Intent(this, C2Lesson3.class);
        startActivity(intent);
    }

    public void CourseTwoLessonFour(View view) {
        Intent intent = new Intent(this, C2Lesson4.class);
        startActivity(intent);
    }

    public void CourseTwoLessonFive (View view) {
        Intent intent = new Intent(this, C2Lesson5.class);
        startActivity(intent);
    }

    public void CourseTwoLessonSix(View view) {
        Intent intent = new Intent(this, C2Lesson6.class);
        startActivity(intent);
    }
}
