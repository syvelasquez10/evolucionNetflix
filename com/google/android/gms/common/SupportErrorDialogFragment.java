// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.zzx;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;

public class SupportErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener zzaaa;
    
    public SupportErrorDialogFragment() {
        this.mDialog = null;
        this.zzaaa = null;
    }
    
    public static SupportErrorDialogFragment newInstance(Dialog mDialog, final DialogInterface$OnCancelListener zzaaa) {
        final SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        mDialog = zzx.zzb(mDialog, "Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        supportErrorDialogFragment.mDialog = mDialog;
        if (zzaaa != null) {
            supportErrorDialogFragment.zzaaa = zzaaa;
        }
        return supportErrorDialogFragment;
    }
    
    @Override
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.zzaaa != null) {
            this.zzaaa.onCancel(dialogInterface);
        }
    }
    
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.mDialog == null) {
            this.setShowsDialog(false);
        }
        return this.mDialog;
    }
    
    @Override
    public void show(final FragmentManager fragmentManager, final String s) {
        super.show(fragmentManager, s);
    }
}
