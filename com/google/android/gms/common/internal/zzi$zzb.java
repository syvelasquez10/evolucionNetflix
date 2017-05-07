// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.DeadObjectException;
import java.util.Collection;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ServiceConnection;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import android.content.Context;
import com.google.android.gms.common.api.Api$Client;
import android.util.Log;
import android.os.IInterface;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzi$zzb extends Handler
{
    final /* synthetic */ zzi zzaaw;
    
    public zzi$zzb(final zzi zzaaw, final Looper looper) {
        this.zzaaw = zzaaw;
        super(looper);
    }
    
    private void zza(final Message message) {
        final zzi$zzc zzi$zzc = (zzi$zzc)message.obj;
        zzi$zzc.zznN();
        zzi$zzc.unregister();
    }
    
    private boolean zzb(final Message message) {
        return message.what == 2 || message.what == 1 || message.what == 5 || message.what == 6;
    }
    
    public void handleMessage(final Message message) {
        if (this.zzaaw.zzaat.get() != message.arg1) {
            if (this.zzb(message)) {
                this.zza(message);
            }
            return;
        }
        if ((message.what == 1 || message.what == 5 || message.what == 6) && !this.zzaaw.isConnecting()) {
            this.zza(message);
            return;
        }
        if (message.what == 3) {
            final ConnectionResult connectionResult = new ConnectionResult(message.arg2, null);
            this.zzaaw.zzaal.onReportServiceBinding(connectionResult);
            this.zzaaw.onConnectionFailed(connectionResult);
            return;
        }
        if (message.what == 4) {
            this.zzaaw.zza(4, null);
            if (this.zzaaw.zzaaq != null) {
                this.zzaaw.zzaaq.onConnectionSuspended(message.arg2);
            }
            this.zzaaw.onConnectionSuspended(message.arg2);
            this.zzaaw.zza(4, 1, null);
            return;
        }
        if (message.what == 2 && !this.zzaaw.isConnected()) {
            this.zza(message);
            return;
        }
        if (this.zzb(message)) {
            ((zzi$zzc)message.obj).zznO();
            return;
        }
        Log.wtf("GmsClient", "Don't know how to handle this message.");
    }
}
