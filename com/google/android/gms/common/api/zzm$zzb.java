// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.os.Bundle;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzu;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;

class zzm$zzb implements Runnable
{
    final /* synthetic */ zzm zzYc;
    private final int zzYd;
    private final ConnectionResult zzYe;
    
    public zzm$zzb(final zzm zzYc, final int zzYd, final ConnectionResult zzYe) {
        this.zzYc = zzYc;
        this.zzYd = zzYd;
        this.zzYe = zzYe;
    }
    
    @Override
    public void run() {
        if (!this.zzYc.mStarted || this.zzYc.zzXU) {
            return;
        }
        this.zzYc.zzXU = true;
        this.zzYc.zzXV = this.zzYd;
        this.zzYc.zzXW = this.zzYe;
        if (this.zzYe.hasResolution()) {
            try {
                this.zzYe.startResolutionForResult(this.zzYc.getActivity(), (this.zzYc.getActivity().getSupportFragmentManager().getFragments().indexOf(this.zzYc) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.zzYc.zzmT();
                return;
            }
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.zzYe.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.zzYe.getErrorCode(), this.zzYc.getActivity(), this.zzYc, 2, (DialogInterface$OnCancelListener)this.zzYc);
            return;
        }
        this.zzYc.zza(this.zzYd, this.zzYe);
    }
}
