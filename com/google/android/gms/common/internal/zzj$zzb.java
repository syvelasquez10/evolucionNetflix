// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.RemoteException;
import android.os.DeadObjectException;
import android.os.Bundle;
import android.os.IBinder;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ServiceConnection;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import android.content.Context;
import com.google.android.gms.common.api.Api$zzb;
import android.util.Log;
import android.os.IInterface;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzj$zzb extends Handler
{
    final /* synthetic */ zzj zzafK;
    
    public zzj$zzb(final zzj zzafK, final Looper looper) {
        this.zzafK = zzafK;
        super(looper);
    }
    
    private void zza(final Message message) {
        final zzj$zzc zzj$zzc = (zzj$zzc)message.obj;
        zzj$zzc.zzpg();
        zzj$zzc.unregister();
    }
    
    private boolean zzb(final Message message) {
        return message.what == 2 || message.what == 1 || message.what == 5 || message.what == 6;
    }
    
    public void handleMessage(final Message message) {
        if (this.zzafK.zzafH.get() != message.arg1) {
            if (this.zzb(message)) {
                this.zza(message);
            }
            return;
        }
        if ((message.what == 1 || message.what == 5 || message.what == 6) && !this.zzafK.isConnecting()) {
            this.zza(message);
            return;
        }
        if (message.what == 3) {
            final ConnectionResult connectionResult = new ConnectionResult(message.arg2, null);
            this.zzafK.zzafz.zza(connectionResult);
            this.zzafK.onConnectionFailed(connectionResult);
            return;
        }
        if (message.what == 4) {
            this.zzafK.zzb(4, null);
            if (this.zzafK.zzafE != null) {
                this.zzafK.zzafE.onConnectionSuspended(message.arg2);
            }
            this.zzafK.onConnectionSuspended(message.arg2);
            this.zzafK.zza(4, 1, null);
            return;
        }
        if (message.what == 2 && !this.zzafK.isConnected()) {
            this.zza(message);
            return;
        }
        if (this.zzb(message)) {
            ((zzj$zzc)message.obj).zzph();
            return;
        }
        Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
    }
}
