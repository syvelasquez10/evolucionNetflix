// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.dialog;

import android.content.Context;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.DialogInterface$OnClickListener;
import android.support.v4.app.DialogFragment;

public class SupportAlertFragment extends DialogFragment implements DialogInterface$OnClickListener
{
    private final DialogModule$AlertFragmentListener mListener;
    
    public SupportAlertFragment() {
        this.mListener = null;
    }
    
    public SupportAlertFragment(final DialogModule$AlertFragmentListener mListener, final Bundle arguments) {
        this.mListener = mListener;
        this.setArguments(arguments);
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.mListener != null) {
            this.mListener.onClick(dialogInterface, n);
        }
    }
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return AlertFragment.createDialog((Context)this.getActivity(), this.getArguments(), (DialogInterface$OnClickListener)this);
    }
    
    @Override
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mListener != null) {
            this.mListener.onDismiss(dialogInterface);
        }
    }
}
