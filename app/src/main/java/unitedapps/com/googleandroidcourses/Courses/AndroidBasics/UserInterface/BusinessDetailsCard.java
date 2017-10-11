package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.UserInterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by Fujutsu on 02-Feb-16.
*/
public class BusinessDetailsCard extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_bdc_main_activity);

        TextView ab_bdc_contact_tv = findViewById(R.id.ab_bdc_contact_tv);
        ab_bdc_contact_tv.setMovementMethod(LinkMovementMethod.getInstance());

        TextView ab_bdc_site_tv = findViewById(R.id.ab_bdc_site_tv);
        ab_bdc_site_tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
