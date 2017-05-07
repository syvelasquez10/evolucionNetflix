// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.zzu;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zzVG;
    
    public ErrorDialogFragment() {
        this.mDialog = null;
        this.zzVG = null;
    }
    
    public static ErrorDialogFragment newInstance(Dialog mDialog, final DialogInterface$OnCancelListener zzVG) {
        final ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        mDialog = zzu.zzb(mDialog, "Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        errorDialogFragment.mDialog = mDialog;
        if (zzVG != null) {
            errorDialogFragment.zzVG = zzVG;
        }
        return errorDialogFragment;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.zzVG != null) {
            this.zzVG.onCancel(dialogInterface);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.mDialog == null) {
            this.setShowsDialog(false);
        }
        return this.mDialog;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        super.show(fragmentManager, s);
    }
}
