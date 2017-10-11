package unitedapps.com.googleandroidcourses.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
   Created by Dasser on 18-Mar-17.
*/

public class FragmentUtils {

    private String firstReplacedFragment;

    public void firstFragmentReplacing(FragmentManager fragmentManager, int container, Fragment fragment, String tag){
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(container, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack("second frag").commit();
        firstReplacedFragment = tag;
    }

    public void replaceFragmentAndChangeActionBar(FragmentManager fragmentManager, int container, Fragment fragment, Activity activity
            , String tag, ActionBar supportActionBar, boolean displayHomeUpBtn){
        /*
              ex: .replaceFragmentAndChangeActionBar(getSupportFragmentManager(), R.id.frame_container, new CoursesList()
                                , this, "Course one Name xD", getSupportActionBar(), true);
                                               used in fragment :  ((AppCompatActivity)getActivity()).getSupportActionBar()
              */
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(container, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack("second frag").commit();
        changeActionBarProperties(supportActionBar, displayHomeUpBtn, tag);
        hideSoftKeyboard(activity);
    }

    public void goToActivity(Context applicationContext, Activity activity, Class aClass){
        Intent i = new Intent(activity, aClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        applicationContext.startActivity(i);
    }

    private void changeActionBarProperties(ActionBar actionBar, Boolean aBoolean, String tag){
        //will make the icon clickable and add the < at the left of the icon.
        actionBar.setDisplayHomeAsUpEnabled(aBoolean);

        //will just make the icon clickable, with the color at the background of the icon as a feedback of the click
        actionBar.setHomeButtonEnabled(aBoolean);

        actionBar.setTitle(tag);
    }

    public void hideSoftKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void refreshActionbarProperties(FragmentManager fragmentManager, int container, ActionBar supportActionBar){
        String tag = fragmentManager.findFragmentById(container).getTag();
        supportActionBar.setTitle(tag);

        if(firstReplacedFragment.equals(tag)){
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setHomeButtonEnabled(false);
        }
    }

    /*private  void showSnackbar(final Activity activity, Snackbar snackbar) {
        if (ScreenUtils.isTranslucentNavigationBar(activity)){
            final FrameLayout snackBarView = (FrameLayout) snackbar.getView();
            final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackBarView.getChildAt(0).getLayoutParams();
            params.setMargins(params.leftMargin,
                    params.topMargin,
                    params.rightMargin,
                    params.bottomMargin + ScreenUtils.getNavigationBarHeight(activity));

            snackBarView.getChildAt(0).setLayoutParams(params);
        }
        snackbar.show();
    }

    public static boolean isTranslucentNavigationBar(Activity activity) {
        Window w = activity.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        int flags = lp.flags;
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && (flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;

    }*/
}
