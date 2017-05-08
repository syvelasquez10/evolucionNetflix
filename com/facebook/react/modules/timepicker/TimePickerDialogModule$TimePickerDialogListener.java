// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.timepicker;

import com.facebook.react.bridge.ReactMethod;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.widget.TimePicker;
import com.facebook.react.bridge.WritableNativeMap;
import android.content.DialogInterface;
import com.facebook.react.bridge.Promise;
import android.content.DialogInterface$OnDismissListener;
import android.app.TimePickerDialog$OnTimeSetListener;

class TimePickerDialogModule$TimePickerDialogListener implements TimePickerDialog$OnTimeSetListener, DialogInterface$OnDismissListener
{
    private final Promise mPromise;
    private boolean mPromiseResolved;
    final /* synthetic */ TimePickerDialogModule this$0;
    
    public TimePickerDialogModule$TimePickerDialogListener(final TimePickerDialogModule this$0, final Promise mPromise) {
        this.this$0 = this$0;
        this.mPromiseResolved = false;
        this.mPromise = mPromise;
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        if (!this.mPromiseResolved && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "dismissedAction");
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }
    
    public void onTimeSet(final TimePicker timePicker, final int n, final int n2) {
        if (!this.mPromiseResolved && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "timeSetAction");
            writableNativeMap.putInt("hour", n);
            writableNativeMap.putInt("minute", n2);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }
}
