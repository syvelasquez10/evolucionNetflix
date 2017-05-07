// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Looper;
import android.content.ComponentName;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import android.content.ServiceConnection;

public class zza implements ServiceConnection
{
    boolean zzVD;
    private final BlockingQueue<IBinder> zzVE;
    
    public zza() {
        this.zzVD = false;
        this.zzVE = new LinkedBlockingQueue<IBinder>();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.zzVE.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
    
    public IBinder zzmf() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        }
        if (this.zzVD) {
            throw new IllegalStateException();
        }
        this.zzVD = true;
        return this.zzVE.take();
    }
}
