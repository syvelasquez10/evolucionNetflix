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
    boolean zzYg;
    private final BlockingQueue<IBinder> zzYh;
    
    public zza() {
        this.zzYg = false;
        this.zzYh = new LinkedBlockingQueue<IBinder>();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.zzYh.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
    
    public IBinder zzmS() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        }
        if (this.zzYg) {
            throw new IllegalStateException();
        }
        this.zzYg = true;
        return this.zzYh.take();
    }
}
