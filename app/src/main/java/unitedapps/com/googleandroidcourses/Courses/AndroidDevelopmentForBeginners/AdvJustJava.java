package unitedapps.com.googleandroidcourses.Courses.AndroidDevelopmentForBeginners;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import unitedapps.com.googleandroidcourses.R;

/**
 * Created by Fujutsu on 02-Feb-16.
 */
public class AdvJustJava extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adv_just_java_app);
        checkBoxWC = (CheckBox)findViewById(R.id.WCcheckBox);
        checkBoxC = (CheckBox)findViewById(R.id.CcheckBox);
        quantityTextView = (TextView) findViewById(R.id.adv_quantity_text_view);
        OrderTextView = (TextView) findViewById(R.id.adv_order_summary_text_view);
        ThanksTextView = (TextView) findViewById(R.id.adv_thank_you_tv);
        DecBtn = (Button) findViewById(R.id.adv_dec_btn);
        DecBtn.setEnabled(false);
        IncBtn = (Button) findViewById(R.id.adv_inc_btn);
    }

    int Q=0, P=0, WC=0, C=0, MP=5;
    CheckBox checkBoxWC, checkBoxC;
    TextView quantityTextView, ThanksTextView,OrderTextView;
    Button DecBtn, IncBtn;
    String name, adv_Price, Chocolate = "Chocolate", WhippedCream = "WhippedCream";

    private void adv_displayQuantity(int numberOfCoffees) {
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void adv_displayOrderSummary(String message) {
        ThanksTextView.setText("Thank You ;)");

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)ThanksTextView.getLayoutParams();
        params.setMargins(0, 4, 0, 4    ); //substitute parameters for left, top, right, bottom
        ThanksTextView.setLayoutParams(params);

        OrderTextView.setText(message);
    }

    public void adv_OrderSummary(View view) {
        if(Q==0) {
            adv_displayOrderSummary("Free :D");
            Toast toast = Toast.makeText(this, "Your Ordered Quantity is Zero", Toast.LENGTH_LONG);
            toast.show();
            ThanksTextView.setText("");
        }else if(!checkBoxWC.isChecked()&& !checkBoxC.isChecked() ){
                adv_displayOrderSummary("Free :D");
                Toast.makeText(this, "You Did Not Select the Product", Toast.LENGTH_LONG).show();
        }else {
            WC = Q * 1;
            C= Q * 2;
            if(!checkBoxWC.isChecked()){WC = 0; Chocolate="";}
            if(!checkBoxC.isChecked()){C = 0; WhippedCream="";}
            if(checkBoxC.isChecked() && checkBoxWC.isChecked()){Chocolate=" & Chocolate";}
            P= WC + C + MP;
            EditText editText = (EditText) findViewById(R.id.nameET);
            name = editText.getText().toString();
            adv_Price = NumberFormat.getCurrencyInstance().format(P);
            adv_displayOrderSummary("Name: " + name + "\nQuantity: " + Q + " Choffe with " + WhippedCream + Chocolate + "\nTotal Price: " + adv_Price);
            composeEmail();
        }
    }

    public void composeEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        //i.setType("message/rfc822");
        i.setData(Uri.parse("mailto"));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dasserbasyouni@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Choffe Order for " + name);
        i.putExtra(Intent.EXTRA_TEXT   , "Name: " + name + "\nQuantity: " + Q + " Choffe with " + WhippedCream + Chocolate + "\nTotal Price: " + adv_Price + "\nThank You ^_^");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AdvJustJava.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void adv_Inc(View view) {
        if(Q==100) {
            IncBtn.setEnabled(false);
            Toast toast = Toast.makeText(this, "That is the maximum order we can take", Toast.LENGTH_LONG);
            toast.show();
        }else{
        Q++;
        adv_displayQuantity(Q);
        DecBtn.setEnabled(true);}
    }

    public void adv_Dec(View view) {
        IncBtn.setEnabled(true);
        if (Q==0){
            DecBtn.setEnabled(false);
        }else{
            Q--;
            adv_displayQuantity(Q);
            if (Q==0){
                DecBtn.setEnabled(false);
            }
        }
    }

}
