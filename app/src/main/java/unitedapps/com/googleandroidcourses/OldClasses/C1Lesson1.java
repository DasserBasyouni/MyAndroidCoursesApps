package unitedapps.com.googleandroidcourses.OldClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface.HappyBirthdayCard;
import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 31-Jan-16.
 */
public class C1Lesson1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c1_lesson1);
    }

    public void OpenHBDapp(View arg0) {
        Intent intent = new Intent(this, HappyBirthdayCard.class);
        startActivity(intent);
    }

}
