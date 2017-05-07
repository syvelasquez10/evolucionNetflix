// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;
import com.google.android.gms.internal.hc;
import android.content.ServiceConnection;
import android.content.Context;

class c implements b
{
    private Context mContext;
    private ServiceConnection xG;
    private c$b xH;
    private c$c xI;
    private hc xJ;
    
    public c(final Context mContext, final c$b xh, final c$c xi) {
        this.mContext = mContext;
        if (xh == null) {
            throw new IllegalArgumentException("onConnectedListener cannot be null");
        }
        this.xH = xh;
        if (xi == null) {
            throw new IllegalArgumentException("onConnectionFailedListener cannot be null");
        }
        this.xI = xi;
    }
    
    private hc dJ() {
        this.dK();
        return this.xJ;
    }
    
    private void dL() {
        this.dM();
    }
    
    private void dM() {
        this.xH.onConnected();
    }
    
    @Override
    public void a(final Map<String, String> map, final long n, final String s, final List<hb> list) {
        try {
            this.dJ().a(map, n, s, list);
        }
        catch (RemoteException ex) {
            z.T("sendHit failed: " + ex);
        }
    }
    
    @Override
    public void connect() {
        final Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        intent.putExtra("app_package_name", this.mContext.getPackageName());
        if (this.xG != null) {
            z.T("Calling connect() while still connected, missing disconnect().");
        }
        else {
            this.xG = (ServiceConnection)new c$a(this);
            final boolean bindService = this.mContext.bindService(intent, this.xG, 129);
            z.V("connect: bindService returned " + bindService + " for " + intent);
            if (!bindService) {
                this.xG = null;
                this.xI.a(1, null);
            }
        }
    }
    
    @Override
    public void dI() {
        try {
            this.dJ().dI();
        }
        catch (RemoteException ex) {
            z.T("clear hits failed: " + ex);
        }
    }
    
    protected void dK() {
        if (!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }
    
    @Override
    public void disconnect() {
        this.xJ = null;
        if (this.xG == null) {
            return;
        }
        while (true) {
            try {
                this.mContext.unbindService(this.xG);
                this.xG = null;
                this.xH.onDisconnected();
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
        return this.xJ != null;
    }
}
