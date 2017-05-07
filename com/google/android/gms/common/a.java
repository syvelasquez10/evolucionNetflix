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
    boolean Ae;
    private final BlockingQueue<IBinder> Af;
    
    public a() {
        this.Ae = false;
        this.Af = new LinkedBlockingQueue<IBinder>();
    }
    
    public IBinder dV() throws InterruptedException {
        if (this.Ae) {
            throw new IllegalStateException();
        }
        this.Ae = true;
        return this.Af.take();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        try {
            this.Af.put(binder);
        }
        catch (InterruptedException ex) {}
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
}
