package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_app);
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView textView1 = (TextView) findViewById(R.id.menu_item_1);
        textView1.setText(getString(Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_1))));
        Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_1));

        // Find second menu item TextView and print the text to the logs
        TextView textView2 = (TextView) findViewById(R.id.menu_item_2);
        textView2.setText(Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_2)));
        Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_2));

        // Find third menu item TextView and print the text to the logs
        TextView textView3 = (TextView) findViewById(R.id.menu_item_3);
        textView3.setText(Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_3)));
        Log.i("Menu", "MyClass.getView() — get item number " + findViewById(R.id.menu_item_3));

    }
}
