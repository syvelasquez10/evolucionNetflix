// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.common.ConnectionResult;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

class zzlp$zza implements GoogleApiClient$OnConnectionFailedListener
{
    public final int zzacQ;
    public final GoogleApiClient zzacR;
    public final GoogleApiClient$OnConnectionFailedListener zzacS;
    final /* synthetic */ zzlp zzacT;
    
    public zzlp$zza(final zzlp zzacT, final int zzacQ, final GoogleApiClient zzacR, final GoogleApiClient$OnConnectionFailedListener zzacS) {
        this.zzacT = zzacT;
        this.zzacQ = zzacQ;
        this.zzacR = zzacR;
        this.zzacS = zzacS;
        zzacR.registerConnectionFailedListener(this);
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("GoogleApiClient #").print(this.zzacQ);
        printWriter.println(":");
        this.zzacR.dump(s + "  ", fileDescriptor, printWriter, array);
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzacT.zzacN.post((Runnable)new zzlp$zzb(this.zzacT, this.zzacQ, connectionResult));
    }
    
    public void zzom() {
        this.zzacR.unregisterConnectionFailedListener(this);
        this.zzacR.disconnect();
    }
}
