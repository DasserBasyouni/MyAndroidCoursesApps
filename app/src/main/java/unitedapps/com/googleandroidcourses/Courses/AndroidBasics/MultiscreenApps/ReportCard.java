package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.MultiscreenApps;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 07-Oct-17.
 */

public class ReportCard extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (16 * scale + 0.5f);
        linearLayout.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

        TextView textView1 = new TextView(this);
        textView1.setText("Name: Kristopher Hector Ike");
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        TextView textView2 = new TextView(this);
        textView2.setText("Year: Third Year");
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        TextView textView3 = new TextView(this);
        textView3.setText("Total Grade: A");
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , 0));
        layoutParams.weight = 1;
        listView.setLayoutParams(layoutParams);
        listView.setPadding(dpAsPixels/4, dpAsPixels/4, dpAsPixels/4, dpAsPixels/4);

        String[] grades = new String[]{"Subject 1: A", "Subject 2: A", "Subject 3: A", "Subject 4: A",
                "Subject 5: A", "Subject 6: A", "Subject 7: A"};

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grades));

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(listView);
        linearLayout.addView(textView3);
        setContentView(linearLayout);
    }
}
