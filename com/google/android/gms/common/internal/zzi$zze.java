// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import android.os.RemoteException;
import android.os.DeadObjectException;
import java.util.Collection;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionProgressReportCallbacks;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api$Client;
import android.os.IInterface;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

public final class zzi$zze implements ServiceConnection
{
    final /* synthetic */ zzi zzaaw;
    private final int zzaaz;
    
    public zzi$zze(final zzi zzaaw, final int zzaaz) {
        this.zzaaw = zzaaw;
        this.zzaaz = zzaaz;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        zzu.zzb(binder, "Expecting a valid IBinder");
        this.zzaaw.zzaak = zzp$zza.zzaG(binder);
        this.zzaaw.zzbt(this.zzaaz);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.zzaaw.mHandler.sendMessage(this.zzaaw.mHandler.obtainMessage(4, this.zzaaz, 1));
    }
}
