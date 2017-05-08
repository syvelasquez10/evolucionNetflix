// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.dialog;

import android.content.DialogInterface;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.content.DialogInterface$OnClickListener;
import android.app.DialogFragment;

public class AlertFragment extends DialogFragment implements DialogInterface$OnClickListener
{
    private final DialogModule$AlertFragmentListener mListener;
    
    public AlertFragment() {
        this.mListener = null;
    }
    
    public AlertFragment(final DialogModule$AlertFragmentListener mListener, final Bundle arguments) {
        this.mListener = mListener;
        this.setArguments(arguments);
    }
    
    public static Dialog createDialog(final Context context, final Bundle bundle, final DialogInterface$OnClickListener dialogInterface$OnClickListener) {
        final AlertDialog$Builder setTitle = new AlertDialog$Builder(context).setTitle((CharSequence)bundle.getString("title"));
        if (bundle.containsKey("button_positive")) {
            setTitle.setPositiveButton((CharSequence)bundle.getString("button_positive"), dialogInterface$OnClickListener);
        }
        if (bundle.containsKey("button_negative")) {
            setTitle.setNegativeButton((CharSequence)bundle.getString("button_negative"), dialogInterface$OnClickListener);
        }
        if (bundle.containsKey("button_neutral")) {
            setTitle.setNeutralButton((CharSequence)bundle.getString("button_neutral"), dialogInterface$OnClickListener);
        }
        if (bundle.containsKey("message")) {
            setTitle.setMessage((CharSequence)bundle.getString("message"));
        }
        if (bundle.containsKey("items")) {
            setTitle.setItems(bundle.getCharSequenceArray("items"), dialogInterface$OnClickListener);
        }
        return (Dialog)setTitle.create();
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.mListener != null) {
            this.mListener.onClick(dialogInterface, n);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        return createDialog((Context)this.getActivity(), this.getArguments(), (DialogInterface$OnClickListener)this);
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mListener != null) {
            this.mListener.onDismiss(dialogInterface);
        }
    }
}
