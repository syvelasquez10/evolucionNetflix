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
import com.google.android.gms.common.internal.zzu;
import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import android.util.SparseArray;
import android.os.Handler;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import java.io.PrintWriter;
import java.io.FileDescriptor;

class zzm$zza implements GoogleApiClient$OnConnectionFailedListener
{
    public final int zzXZ;
    public final GoogleApiClient zzYa;
    public final GoogleApiClient$OnConnectionFailedListener zzYb;
    final /* synthetic */ zzm zzYc;
    
    public zzm$zza(final zzm zzYc, final int zzXZ, final GoogleApiClient zzYa, final GoogleApiClient$OnConnectionFailedListener zzYb) {
        this.zzYc = zzYc;
        this.zzXZ = zzXZ;
        this.zzYa = zzYa;
        this.zzYb = zzYb;
        zzYa.registerConnectionFailedListener(this);
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("GoogleApiClient #").print(this.zzXZ);
        printWriter.println(":");
        this.zzYa.dump(s + "  ", fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzYc.zzXX.post((Runnable)new zzm$zzb(this.zzYc, this.zzXZ, connectionResult));
    }
    
    public void zzmU() {
        this.zzYa.unregisterConnectionFailedListener(this);
        this.zzYa.disconnect();
    }
}
