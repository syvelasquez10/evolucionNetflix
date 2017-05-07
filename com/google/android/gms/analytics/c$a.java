// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;
import com.google.android.gms.internal.hc;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.hc$a;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

final class c$a implements ServiceConnection
{
    final /* synthetic */ c xK;
    
    c$a(final c xk) {
        this.xK = xk;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        z.V("service connected, binder: " + binder);
        try {
            if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(binder.getInterfaceDescriptor())) {
                z.V("bound to service");
                this.xK.xJ = hc$a.E(binder);
                this.xK.dL();
                return;
            }
        }
        catch (RemoteException ex) {}
        while (true) {
            try {
                this.xK.mContext.unbindService((ServiceConnection)this);
                this.xK.xG = null;
                this.xK.xI.a(2, null);
            }
            catch (IllegalArgumentException ex2) {
                continue;
            }
            break;
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        z.V("service disconnected: " + componentName);
        this.xK.xG = null;
        this.xK.xH.onDisconnected();
    }
}
