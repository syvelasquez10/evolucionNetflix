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
import com.google.android.gms.common.internal.zzx;
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

class zzq$zzc implements Runnable
{
    private final int zzaaS;
    private final ConnectionResult zzaaT;
    final /* synthetic */ zzq zzaaW;
    
    public zzq$zzc(final zzq zzaaW, final int zzaaS, final ConnectionResult zzaaT) {
        this.zzaaW = zzaaW;
        this.zzaaS = zzaaS;
        this.zzaaT = zzaaT;
    }
    
    @Override
    public void run() {
        if (this.zzaaT.hasResolution()) {
            try {
                this.zzaaT.startResolutionForResult(this.zzaaW.getActivity(), (this.zzaaW.getActivity().getSupportFragmentManager().getFragments().indexOf(this.zzaaW) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.zzaaW.zznJ();
                return;
            }
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.zzaaT.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.zzaaT.getErrorCode(), this.zzaaW.getActivity(), this.zzaaW, 2, (DialogInterface$OnCancelListener)this.zzaaW);
            return;
        }
        this.zzaaW.zza(this.zzaaS, this.zzaaT);
    }
}
