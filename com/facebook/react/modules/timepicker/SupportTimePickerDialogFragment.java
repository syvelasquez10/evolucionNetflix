// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.timepicker;

import android.content.DialogInterface;
import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;
import android.app.TimePickerDialog$OnTimeSetListener;
import android.content.DialogInterface$OnDismissListener;
import android.support.v4.app.DialogFragment;

public class SupportTimePickerDialogFragment extends DialogFragment
{
    private DialogInterface$OnDismissListener mOnDismissListener;
    private TimePickerDialog$OnTimeSetListener mOnTimeSetListener;
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return TimePickerDialogFragment.createDialog(this.getArguments(), (Context)this.getActivity(), this.mOnTimeSetListener);
    }
    
    @Override
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
