// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.timepicker;

import android.content.DialogInterface;
import android.text.format.DateFormat;
import java.util.Calendar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.app.TimePickerDialog$OnTimeSetListener;
import android.content.DialogInterface$OnDismissListener;
import android.app.DialogFragment;

public class TimePickerDialogFragment extends DialogFragment
{
    private DialogInterface$OnDismissListener mOnDismissListener;
    private TimePickerDialog$OnTimeSetListener mOnTimeSetListener;
    
    static Dialog createDialog(final Bundle bundle, final Context context, final TimePickerDialog$OnTimeSetListener timePickerDialog$OnTimeSetListener) {
        final Calendar instance = Calendar.getInstance();
        int n = instance.get(11);
        int n2 = instance.get(12);
        boolean b = DateFormat.is24HourFormat(context);
        if (bundle != null) {
            n = bundle.getInt("hour", instance.get(11));
            n2 = bundle.getInt("minute", instance.get(12));
            b = bundle.getBoolean("is24Hour", DateFormat.is24HourFormat(context));
        }
        return (Dialog)new DismissableTimePickerDialog(context, timePickerDialog$OnTimeSetListener, n, n2, b);
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        return createDialog(this.getArguments(), (Context)this.getActivity(), this.mOnTimeSetListener);
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialogInterface);
        }
    }
    
    public void setOnDismissListener(final DialogInterface$OnDismissListener mOnDismissListener) {
        this.mOnDismissListener = mOnDismissListener;
    }
    
    public void setOnTimeSetListener(final TimePickerDialog$OnTimeSetListener mOnTimeSetListener) {
        this.mOnTimeSetListener = mOnTimeSetListener;
    }
}
