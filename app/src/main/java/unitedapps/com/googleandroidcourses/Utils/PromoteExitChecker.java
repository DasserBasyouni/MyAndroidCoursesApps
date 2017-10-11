package unitedapps.com.googleandroidcourses.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
    Created by Dasser on 27-Mar-17.
*/

public class PromoteExitChecker {

    private boolean resetToast = false;

    public void showExitDialog(Activity activity) {
        final int[] count = {0};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("activity.getString(R.string.dialog_title)");
        builder.setMessage("activity.getString(R.string.dialog_message)");

        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });

        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isToastShown() {
        return resetToast;
    }

    public void showExitToast(Activity activity) {
        Toast.makeText(activity, "press back again to exit", Toast.LENGTH_SHORT).show();
        changeResetToast();
    }

    private void changeResetToast() {
        resetToast = !resetToast;
    }

    public void resetResetToast() {
        resetToast = false;
    }

    public void showExitSnackbar(Activity activity) {
        final int[] count = {0};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("activity.getString(R.string.dialog_title)");
        builder.setMessage("activity.getString(R.string.dialog_message)");

        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });

        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
