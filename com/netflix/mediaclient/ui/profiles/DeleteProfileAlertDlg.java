// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog$Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public class DeleteProfileAlertDlg extends NetflixDialogFrag
{
    protected static DeleteProfileAlertDlg createDeleteProfileDialog(final NetflixActivity netflixActivity, final String s, final String s2) {
        final DeleteProfileAlertDlg deleteProfileAlertDlg = new DeleteProfileAlertDlg();
        deleteProfileAlertDlg.setStyle(1, 2131427465);
        return deleteProfileAlertDlg;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)this.getActivity();
        if (dialogInterface$OnClickListener != null) {
            dialogInterface$OnClickListener.onClick(dialogInterface, -2);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        super.onCreate(bundle);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this.getActivity());
        final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)this.getActivity();
        alertDialog$Builder.setNegativeButton(2131231008, dialogInterface$OnClickListener);
        alertDialog$Builder.setPositiveButton(2131231394, dialogInterface$OnClickListener);
        alertDialog$Builder.setMessage(this.getString(2131231395));
        final AlertDialog create = alertDialog$Builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }
}
