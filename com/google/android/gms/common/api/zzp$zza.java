// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import android.os.Bundle;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Intent;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.zzx;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import java.io.PrintWriter;
import java.io.FileDescriptor;

class zzp$zza implements GoogleApiClient$OnConnectionFailedListener
{
    public final int zzaaO;
    public final GoogleApiClient zzaaP;
    public final GoogleApiClient$OnConnectionFailedListener zzaaQ;
    final /* synthetic */ zzp zzaaR;
    
    public zzp$zza(final zzp zzaaR, final int zzaaO, final GoogleApiClient zzaaP, final GoogleApiClient$OnConnectionFailedListener zzaaQ) {
        this.zzaaR = zzaaR;
        this.zzaaO = zzaaO;
        this.zzaaP = zzaaP;
        this.zzaaQ = zzaaQ;
        zzaaP.registerConnectionFailedListener(this);
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("GoogleApiClient #").print(this.zzaaO);
        printWriter.println(":");
        this.zzaaP.dump(s + "  ", fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzaaR.zzaaM.post((Runnable)new zzp$zzb(this.zzaaR, this.zzaaO, connectionResult));
    }
    
    public void zznK() {
        this.zzaaP.unregisterConnectionFailedListener(this);
        this.zzaaP.disconnect();
    }
}
