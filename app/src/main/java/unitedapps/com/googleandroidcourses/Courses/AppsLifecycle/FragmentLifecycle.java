package unitedapps.com.googleandroidcourses.Courses.AppsLifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
   Created by DB-Project on 8/30/2017.
*/

public class FragmentLifecycle extends android.support.v4.app.Fragment{
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("Z_f", "onHiddenChanged");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Z_f", "onActivityResult");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("Z_f", "onRequestPermissionsResult");
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.i("Z_f", "onInflate c");
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        Log.i("Z_f", "onInflate a");
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.i("Z_f", "onAttachFragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Z_f", "onAttach c");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Z_f", "onAttach a");
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.i("Z_f", "onCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Z_f", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Z_f", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Z_f", "onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Z_f", "onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("Z_f", "onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Z_f", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Z_f", "onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Z_f", "onSaveInstanceState");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        Log.i("Z_f", "onMultiWindowModeChanged");
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        Log.i("Z_f", "onPictureInPictureModeChanged");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("Z_f", "onConfigurationChanged");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Z_f", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Z_f", "onStop");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i("Z_f", "onLowMemory");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Z_f", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Z_f", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Z_f", "onDetach");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.i("Z_f", "onCreateOptionsMenu");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Log.i("Z_f", "onPrepareOptionsMenu");
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        Log.i("Z_f", "onDestroyOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Z_f", "onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        Log.i("Z_f", "onOptionsMenuClosed");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.i("Z_f", "onCreateContextMenu");
    }
}
