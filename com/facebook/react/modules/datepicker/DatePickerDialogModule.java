// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.datepicker;

import com.facebook.react.bridge.ReactMethod;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.app.DatePickerDialog$OnDateSetListener;
import android.content.DialogInterface$OnDismissListener;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.bridge.Promise;
import android.os.Bundle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class DatePickerDialogModule extends ReactContextBaseJavaModule
{
    static final String ACTION_DATE_SET = "dateSetAction";
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ARG_DATE = "date";
    static final String ARG_MAXDATE = "maxDate";
    static final String ARG_MINDATE = "minDate";
    static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    public static final String FRAGMENT_TAG = "DatePickerAndroid";
    
    public DatePickerDialogModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private Bundle createFragmentArguments(final ReadableMap readableMap) {
        final Bundle bundle = new Bundle();
        if (readableMap.hasKey("date") && !readableMap.isNull("date")) {
            bundle.putLong("date", (long)readableMap.getDouble("date"));
        }
        if (readableMap.hasKey("minDate") && !readableMap.isNull("minDate")) {
            bundle.putLong("minDate", (long)readableMap.getDouble("minDate"));
        }
        if (readableMap.hasKey("maxDate") && !readableMap.isNull("maxDate")) {
            bundle.putLong("maxDate", (long)readableMap.getDouble("maxDate"));
        }
        if (readableMap.hasKey("mode") && !readableMap.isNull("mode")) {
            bundle.putString("mode", readableMap.getString("mode"));
        }
        return bundle;
    }
    
    @Override
    public String getName() {
        return "DatePickerAndroid";
    }
    
    @ReactMethod
    public void open(final ReadableMap readableMap, final Promise promise) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("E_NO_ACTIVITY", "Tried to open a DatePicker dialog while not attached to an Activity");
            return;
        }
        if (currentActivity instanceof FragmentActivity) {
            final FragmentManager supportFragmentManager = ((FragmentActivity)currentActivity).getSupportFragmentManager();
            final DialogFragment dialogFragment = (DialogFragment)supportFragmentManager.findFragmentByTag("DatePickerAndroid");
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            final SupportDatePickerDialogFragment supportDatePickerDialogFragment = new SupportDatePickerDialogFragment();
            if (readableMap != null) {
                supportDatePickerDialogFragment.setArguments(this.createFragmentArguments(readableMap));
            }
            final DatePickerDialogModule$DatePickerDialogListener datePickerDialogModule$DatePickerDialogListener = new DatePickerDialogModule$DatePickerDialogListener(this, promise);
            supportDatePickerDialogFragment.setOnDismissListener((DialogInterface$OnDismissListener)datePickerDialogModule$DatePickerDialogListener);
            supportDatePickerDialogFragment.setOnDateSetListener((DatePickerDialog$OnDateSetListener)datePickerDialogModule$DatePickerDialogListener);
            supportDatePickerDialogFragment.show(supportFragmentManager, "DatePickerAndroid");
            return;
        }
        final android.app.FragmentManager fragmentManager = currentActivity.getFragmentManager();
        final android.app.DialogFragment dialogFragment2 = (android.app.DialogFragment)fragmentManager.findFragmentByTag("DatePickerAndroid");
        if (dialogFragment2 != null) {
            dialogFragment2.dismiss();
        }
        final DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        if (readableMap != null) {
            datePickerDialogFragment.setArguments(this.createFragmentArguments(readableMap));
        }
        final DatePickerDialogModule$DatePickerDialogListener datePickerDialogModule$DatePickerDialogListener2 = new DatePickerDialogModule$DatePickerDialogListener(this, promise);
        datePickerDialogFragment.setOnDismissListener((DialogInterface$OnDismissListener)datePickerDialogModule$DatePickerDialogListener2);
        datePickerDialogFragment.setOnDateSetListener((DatePickerDialog$OnDateSetListener)datePickerDialogModule$DatePickerDialogListener2);
        datePickerDialogFragment.show(fragmentManager, "DatePickerAndroid");
    }
}
