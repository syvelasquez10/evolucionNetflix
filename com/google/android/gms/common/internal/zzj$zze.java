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
import com.google.android.gms.common.api.GoogleApiClient$zza;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
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
    final /* synthetic */ zzj zzafK;
    private final int zzafN;
    
    public zzj$zze(final zzj zzafK, final int zzafN) {
        this.zzafK = zzafK;
        this.zzafN = zzafN;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        zzx.zzb(binder, "Expecting a valid IBinder");
        this.zzafK.zzafy = zzs$zza.zzaK(binder);
        this.zzafK.zzbF(this.zzafN);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.zzafK.mHandler.sendMessage(this.zzafK.mHandler.obtainMessage(4, this.zzafN, 1));
    }
}
