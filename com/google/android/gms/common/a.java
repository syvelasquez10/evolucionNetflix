// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.ComponentName;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import android.content.ServiceConnection;

public class a implements ServiceConnection
{
    private final BlockingQueue<IBinder> mA;
    boolean mz;
    
    public a() {
        this.mz = false;
        this.mA = new LinkedBlockingQueue<IBinder>();
    }
    
    public IBinder bg() throws InterruptedException {
        if (this.mz) {
            throw new IllegalStateException();
        }
        this.mz = true;
        return this.mA.take();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        try {
            this.mA.put(binder);
        }
        catch (InterruptedException ex) {}
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
}
