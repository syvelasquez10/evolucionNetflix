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
    boolean HC;
    private final BlockingQueue<IBinder> HD;
    
    public a() {
        this.HC = false;
        this.HD = new LinkedBlockingQueue<IBinder>();
    }
    
    public IBinder fX() throws InterruptedException {
        if (this.HC) {
            throw new IllegalStateException();
        }
        this.HC = true;
        return this.HD.take();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.HD.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
}
