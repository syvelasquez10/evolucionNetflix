// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;

class zzlp$zzb implements Runnable
{
    final /* synthetic */ zzlp zzacT;
    private final int zzacU;
    private final ConnectionResult zzacV;
    
    public zzlp$zzb(final zzlp zzacT, final int zzacU, final ConnectionResult zzacV) {
        this.zzacT = zzacT;
        this.zzacU = zzacU;
        this.zzacV = zzacV;
    }
    
    @Override
    public void run() {
        if (!this.zzacT.mStarted || this.zzacT.zzacK) {
            return;
        }
        this.zzacT.zzacK = true;
        this.zzacT.zzacL = this.zzacU;
        this.zzacT.zzacM = this.zzacV;
        if (this.zzacV.hasResolution()) {
            try {
                this.zzacV.startResolutionForResult(this.zzacT.getActivity(), (this.zzacT.getActivity().getSupportFragmentManager().getFragments().indexOf(this.zzacT) + 1 << 16) + 1);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                this.zzacT.zzok();
                return;
            }
        }
        if (zzlp.zzacJ.isUserResolvableError(this.zzacV.getErrorCode())) {
            GooglePlayServicesUtil.showErrorDialogFragment(this.zzacV.getErrorCode(), this.zzacT.getActivity(), this.zzacT, 2, (DialogInterface$OnCancelListener)this.zzacT);
            return;
        }
        if (this.zzacV.getErrorCode() == 18) {
            this.zzacT.zzacO = zzll.zza(this.zzacT.getActivity().getApplicationContext(), new zzlp$zzb$1(this, zzlp.zzacJ.zza(this.zzacT.getActivity(), (DialogInterface$OnCancelListener)this.zzacT)));
            return;
        }
        this.zzacT.zza(this.zzacU, this.zzacV);
    }
}
