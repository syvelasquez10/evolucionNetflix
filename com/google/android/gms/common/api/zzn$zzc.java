// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.support.v4.content.Loader;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzu;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import android.support.v4.app.LoaderManager$LoaderCallbacks;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;

class zzn$zzc implements Runnable
{
    private final int zzYd;
    private final ConnectionResult zzYe;
    final /* synthetic */ zzn zzYh;
    
    public zzn$zzc(final zzn zzYh, final int zzYd, final ConnectionResult zzYe) {
        this.zzYh = zzYh;
        this.zzYd = zzYd;
        this.zzYe = zzYe;
    }
    
    @Override
    public void run() {
        if (this.zzYe.hasResolution()) {
            try {
                this.zzYe.startResolutionForResult(this.zzYh.getActivity(), (this.zzYh.getActivity().getSupportFragmentManager().getFragments().indexOf(this.zzYh) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.zzYh.zzmT();
                return;
            }
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.zzYe.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.zzYe.getErrorCode(), this.zzYh.getActivity(), this.zzYh, 2, (DialogInterface$OnCancelListener)this.zzYh);
            return;
        }
        this.zzYh.zza(this.zzYd, this.zzYe);
    }
}
