package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

public class Cookies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookies_app);
    }

    public void eatCookie(View view) {
        ImageView imageView = (ImageView)findViewById(R.id.android_cookie_image_view);
        imageView.setImageResource(R.drawable.after_cookie);
        TextView textView = (TextView)findViewById(R.id.status_text_view);
        textView.setText("I'm so full");
    }
}
