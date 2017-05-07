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
import com.google.android.gms.common.internal.zzx;
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

class zzp$zzb implements Runnable
{
    final /* synthetic */ zzp zzaaR;
    private final int zzaaS;
    private final ConnectionResult zzaaT;
    
    public zzp$zzb(final zzp zzaaR, final int zzaaS, final ConnectionResult zzaaT) {
        this.zzaaR = zzaaR;
        this.zzaaS = zzaaS;
        this.zzaaT = zzaaT;
    }
    
    @Override
    public void run() {
        if (!this.zzaaR.mStarted || this.zzaaR.zzaaJ) {
            return;
        }
        this.zzaaR.zzaaJ = true;
        this.zzaaR.zzaaK = this.zzaaS;
        this.zzaaR.zzaaL = this.zzaaT;
        if (this.zzaaT.hasResolution()) {
            try {
                this.zzaaT.startResolutionForResult(this.zzaaR.getActivity(), (this.zzaaR.getActivity().getSupportFragmentManager().getFragments().indexOf(this.zzaaR) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.zzaaR.zznJ();
                return;
            }
        }
        if (GooglePlayServicesUtil.isUserRecoverableError(this.zzaaT.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.zzaaT.getErrorCode(), this.zzaaR.getActivity(), this.zzaaR, 2, (DialogInterface$OnCancelListener)this.zzaaR);
            return;
        }
        this.zzaaR.zza(this.zzaaS, this.zzaaT);
    }
}
