package com.vuziq.learningfirebase.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by bruno on 8/18/16.
 */
public class ActivityUtils {

    public static void hideKeyboard(Activity activity){

        if(activity.getCurrentFocus() != null) {
            InputMethodManager inputManager =
                    (InputMethodManager) activity.
                            getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
