// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.ef;
import java.util.List;
import java.util.Map;
import com.google.android.gms.internal.eg;
import android.content.ServiceConnection;
import android.content.Context;

class c implements com.google.android.gms.analytics.b
{
    private Context mContext;
    private ServiceConnection sj;
    private b sk;
    private c sl;
    private eg sm;
    
    public c(final Context mContext, final b sk, final c sl) {
        this.mContext = mContext;
        if (sk == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.sk = sk;
        if (sl == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.sl = sl;
    }
    
    private eg bS() {
        this.bT();
        return this.sm;
    }
    
    private void bU() {
        this.bV();
    }
    
    private void bV() {
        this.sk.onConnected();
    }
    
    @Override
    public void a(final Map<String, String> map, final long n, final String s, final List<ef> list) {
        try {
            this.bS().a(map, n, s, list);
        }
        catch (RemoteException ex) {
            aa.w("sendHit failed: " + ex);
        }
    }
    
    @Override
    public void bR() {
        try {
            this.bS().bR();
        }
        catch (RemoteException ex) {
            aa.w("clear hits failed: " + ex);
        }
    }
    
    protected void bT() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    @Override
    public void connect() {
        final Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.mContext.getPackageName());
        if (this.sj != null) {
            aa.w("Calling connect() while still connected, missing disconnect().");
        }
        else {
            this.sj = (ServiceConnection)new a();
            final boolean bindService = this.mContext.bindService(intent, this.sj, 129);
            aa.y("connect: bindService returned " + bindService + " for " + intent);
            if (!bindService) {
                this.sj = null;
                this.sl.a(1, null);
            }
        }
    }
    
    @Override
    public void disconnect() {
        this.sm = null;
        if (this.sj == null) {
            return;
        }
        while (true) {
            try {
                this.mContext.unbindService(this.sj);
                this.sj = null;
                this.sk.onDisconnected();
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            catch (IllegalStateException ex2) {
                continue;
            }
            break;
        }
    }
    
    public boolean isConnected() {
        return this.sm != null;
    }
    
    final class a implements ServiceConnection
    {
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            aa.y("service connected, binder: " + binder);
            try {
                if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(binder.getInterfaceDescriptor())) {
                    aa.y("bound to service");
                    c.this.sm = eg.a.t(binder);
                    c.this.bU();
                    return;
                }
            }
            catch (RemoteException ex) {}
            c.this.mContext.unbindService((ServiceConnection)this);
            c.this.sj = null;
            c.this.sl.a(2, null);
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            aa.y("service disconnected: " + componentName);
            c.this.sj = null;
            c.this.sk.onDisconnected();
        }
    }
    
    public interface b
    {
        void onConnected();
        
        void onDisconnected();
    }
    
    public interface c
    {
        void a(final int p0, final Intent p1);
    }
}
