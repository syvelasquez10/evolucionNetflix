// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.timepicker;

import com.facebook.react.bridge.ReactMethod;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.app.TimePickerDialog$OnTimeSetListener;
import android.content.DialogInterface$OnDismissListener;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.bridge.Promise;
import android.os.Bundle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class TimePickerDialogModule extends ReactContextBaseJavaModule
{
    static final String ACTION_DISMISSED = "dismissedAction";
    static final String ACTION_TIME_SET = "timeSetAction";
    static final String ARG_HOUR = "hour";
    static final String ARG_IS24HOUR = "is24Hour";
    static final String ARG_MINUTE = "minute";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    public static final String FRAGMENT_TAG = "TimePickerAndroid";
    
    public TimePickerDialogModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    private Bundle createFragmentArguments(final ReadableMap readableMap) {
        final Bundle bundle = new Bundle();
        if (readableMap.hasKey("hour") && !readableMap.isNull("hour")) {
            bundle.putInt("hour", readableMap.getInt("hour"));
        }
        if (readableMap.hasKey("minute") && !readableMap.isNull("minute")) {
            bundle.putInt("minute", readableMap.getInt("minute"));
        }
        if (readableMap.hasKey("is24Hour") && !readableMap.isNull("is24Hour")) {
            bundle.putBoolean("is24Hour", readableMap.getBoolean("is24Hour"));
        }
        return bundle;
    }
    
    @Override
    public String getName() {
        return "TimePickerAndroid";
    }
    
    @ReactMethod
    public void open(final ReadableMap readableMap, final Promise promise) {
        final Activity currentActivity = this.getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("E_NO_ACTIVITY", "Tried to open a TimePicker dialog while not attached to an Activity");
            return;
        }
        if (currentActivity instanceof FragmentActivity) {
            final FragmentManager supportFragmentManager = ((FragmentActivity)currentActivity).getSupportFragmentManager();
            final DialogFragment dialogFragment = (DialogFragment)supportFragmentManager.findFragmentByTag("TimePickerAndroid");
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            final SupportTimePickerDialogFragment supportTimePickerDialogFragment = new SupportTimePickerDialogFragment();
            if (readableMap != null) {
                supportTimePickerDialogFragment.setArguments(this.createFragmentArguments(readableMap));
            }
            final TimePickerDialogModule$TimePickerDialogListener timePickerDialogModule$TimePickerDialogListener = new TimePickerDialogModule$TimePickerDialogListener(this, promise);
            supportTimePickerDialogFragment.setOnDismissListener((DialogInterface$OnDismissListener)timePickerDialogModule$TimePickerDialogListener);
            supportTimePickerDialogFragment.setOnTimeSetListener((TimePickerDialog$OnTimeSetListener)timePickerDialogModule$TimePickerDialogListener);
            supportTimePickerDialogFragment.show(supportFragmentManager, "TimePickerAndroid");
            return;
        }
        final android.app.FragmentManager fragmentManager = currentActivity.getFragmentManager();
        final android.app.DialogFragment dialogFragment2 = (android.app.DialogFragment)fragmentManager.findFragmentByTag("TimePickerAndroid");
        if (dialogFragment2 != null) {
            dialogFragment2.dismiss();
        }
        final TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        if (readableMap != null) {
            timePickerDialogFragment.setArguments(this.createFragmentArguments(readableMap));
        }
        final TimePickerDialogModule$TimePickerDialogListener timePickerDialogModule$TimePickerDialogListener2 = new TimePickerDialogModule$TimePickerDialogListener(this, promise);
        timePickerDialogFragment.setOnDismissListener((DialogInterface$OnDismissListener)timePickerDialogModule$TimePickerDialogListener2);
        timePickerDialogFragment.setOnTimeSetListener((TimePickerDialog$OnTimeSetListener)timePickerDialogModule$TimePickerDialogListener2);
        timePickerDialogFragment.show(fragmentManager, "TimePickerAndroid");
    }
}
