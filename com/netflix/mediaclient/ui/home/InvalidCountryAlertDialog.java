// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class InvalidCountryAlertDialog extends NetflixDialogFrag
{
    protected static InvalidCountryAlertDialog createInvalidCountryAlertDialog(final HomeActivity homeActivity) {
        final InvalidCountryAlertDialog invalidCountryAlertDialog = new InvalidCountryAlertDialog();
        invalidCountryAlertDialog.setStyle(1, 2131558712);
        return invalidCountryAlertDialog;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        final DialogInterface$OnClickListener invalidCountryDialogListener = ((HomeActivity)this.getActivity()).invalidCountryDialogListener;
        if (invalidCountryDialogListener != null) {
            invalidCountryDialogListener.onClick(dialogInterface, -1);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        alertDialog$Builder.setPositiveButton(2131492994, ((HomeActivity)this.getActivity()).invalidCountryDialogListener);
        alertDialog$Builder.setMessage((CharSequence)this.getString(2131493384));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        return (Dialog)create;
    }
}
