package unitedapps.com.googleandroidcourses.Utils.CheckInternetConnection;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
    Created by Dasser on 27-Mar-17.
*/

public class CheckInternetConnection {

    public boolean checkInternetConnectionWithBtn(Activity activity, Button button){
        boolean b = true;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null){
            b = showConnectionsDialog(activity);
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }
            return b;
    }

    public boolean checkInternetConnection(Activity activity){
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        Log.i("Z_tetsssss", String.valueOf(netInfo != null && netInfo.isConnectedOrConnecting()));
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean showConnectionsDialog(final Activity activity) {
        final boolean[] b = new boolean[1];
        b[0] = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("You need a network connection in this step." +
                " Please turn or check on mobile network or Wi-Fi or check in Settings.")
                .setTitle("Unable to connect")
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                activity.startActivity(i);
                                b[0] = true;
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                b[0] = false;
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
        return b[0];
    }

}
