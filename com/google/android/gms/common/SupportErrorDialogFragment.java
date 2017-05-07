// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.n;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.DialogFragment;

public class SupportErrorDialogFragment extends DialogFragment
{
    private DialogInterface$OnCancelListener HG;
    private Dialog mDialog;
    
    public SupportErrorDialogFragment() {
        this.mDialog = null;
        this.HG = null;
    }
    
    public static SupportErrorDialogFragment newInstance(final Dialog dialog) {
        return newInstance(dialog, null);
    }
    
    public static SupportErrorDialogFragment newInstance(Dialog mDialog, final DialogInterface$OnCancelListener hg) {
        final SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        mDialog = n.b(mDialog, "Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        supportErrorDialogFragment.mDialog = mDialog;
        if (hg != null) {
            supportErrorDialogFragment.HG = hg;
        }
        return supportErrorDialogFragment;
    }
    
    @Override
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.HG != null) {
            this.HG.onCancel(dialogInterface);
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
