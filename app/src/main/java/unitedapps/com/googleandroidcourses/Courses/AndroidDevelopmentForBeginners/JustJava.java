package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 02-Feb-16.
 */
public class JustJava extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.just_java_app);
        Button DecBtn = (Button) findViewById(R.id.dec_btn);
        DecBtn.setEnabled(false);
    }

    int x = 0,y = 0;

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayThanks(String message) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText(message);
    }

    public void submitOrder(View view) {
        if(x==0){
            displayThanks("Free :D");
        }else {
            displayThanks("Name: Dasser Basyouni\n" + "Quantity: " + x + "\nTotal: $" + y + "\nThank You ;)");
        }
    }

    public void Inc(View view) {
        x++;
        y=x*5;
        displayQuantity(x);
        Button DecBtn = (Button) findViewById(R.id.dec_btn);
        DecBtn.setEnabled(true);
    }

    public void Dec(View view) {
        if (x==0){
            Button DecBtn = (Button) findViewById(R.id.dec_btn);
            DecBtn.setEnabled(false);
        }else{
            x--;
            y=x*5;
            displayQuantity(x);
            if (x==0){
                Button DecBtn = (Button) findViewById(R.id.dec_btn);
                DecBtn.setEnabled(false);
            }
        }
    }

}
