// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.RemoteException;
import android.os.DeadObjectException;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.accounts.Account;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api$zzb;
import android.os.IInterface;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public final class zzj$zze implements ServiceConnection
{
    final /* synthetic */ zzj zzadH;
    private final int zzadK;
    
    public zzj$zze(final zzj zzadH, final int zzadK) {
        this.zzadH = zzadH;
        this.zzadK = zzadK;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        zzx.zzb(binder, "Expecting a valid IBinder");
        this.zzadH.zzadv = zzs$zza.zzaK(binder);
        this.zzadH.zzbA(this.zzadK);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.zzadH.mHandler.sendMessage(this.zzadH.mHandler.obtainMessage(4, this.zzadK, 1));
    }
}
