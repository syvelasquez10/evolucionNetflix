// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.datepicker;

import android.content.DialogInterface;
import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface$OnDismissListener;
import android.app.DatePickerDialog$OnDateSetListener;
import android.annotation.SuppressLint;
import android.support.v4.app.DialogFragment;

@SuppressLint({ "ValidFragment" })
public class SupportDatePickerDialogFragment extends DialogFragment
{
    private DatePickerDialog$OnDateSetListener mOnDateSetListener;
    private DialogInterface$OnDismissListener mOnDismissListener;
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return DatePickerDialogFragment.createDialog(this.getArguments(), (Context)this.getActivity(), this.mOnDateSetListener);
    }
    
    @Override
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialogInterface);
        }
    }
    
    void setOnDateSetListener(final DatePickerDialog$OnDateSetListener mOnDateSetListener) {
        this.mOnDateSetListener = mOnDateSetListener;
    }
    
    void setOnDismissListener(final DialogInterface$OnDismissListener mOnDismissListener) {
        this.mOnDismissListener = mOnDismissListener;
    }
}
