// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.datepicker;

import com.facebook.react.bridge.ReactMethod;
import android.support.v4.app.FragmentManager;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import android.content.DialogInterface;
import com.facebook.react.bridge.WritableNativeMap;
import android.widget.DatePicker;
import com.facebook.react.bridge.Promise;
import android.content.DialogInterface$OnDismissListener;
import android.app.DatePickerDialog$OnDateSetListener;

class DatePickerDialogModule$DatePickerDialogListener implements DatePickerDialog$OnDateSetListener, DialogInterface$OnDismissListener
{
    private final Promise mPromise;
    private boolean mPromiseResolved;
    final /* synthetic */ DatePickerDialogModule this$0;
    
    public DatePickerDialogModule$DatePickerDialogListener(final DatePickerDialogModule this$0, final Promise mPromise) {
        this.this$0 = this$0;
        this.mPromiseResolved = false;
        this.mPromise = mPromise;
    }
    
    public void onDateSet(final DatePicker datePicker, final int n, final int n2, final int n3) {
        if (!this.mPromiseResolved && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "dateSetAction");
            writableNativeMap.putInt("year", n);
            writableNativeMap.putInt("month", n2);
            writableNativeMap.putInt("day", n3);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        if (!this.mPromiseResolved && this.this$0.getReactApplicationContext().hasActiveCatalystInstance()) {
            final WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("action", "dismissedAction");
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }
}
