// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Message;
import java.util.Collection;
import android.util.Log;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import android.os.Handler;

public final class dx
{
    private final Handler mHandler;
    private final b ps;
    private ArrayList<GoogleApiClient.ConnectionCallbacks> pt;
    final ArrayList<GoogleApiClient.ConnectionCallbacks> pu;
    private boolean pv;
    private ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> pw;
    private boolean px;
    
    public dx(final Context context, final b b) {
        this(context, b, null);
    }
    
    dx(final Context context, final b ps, final Handler handler) {
        this.pu = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.pv = false;
        this.px = false;
        Handler mHandler = handler;
        if (handler == null) {
            mHandler = new a(Looper.getMainLooper());
        }
        this.pt = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.pw = new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>();
        this.ps = ps;
        this.mHandler = mHandler;
    }
    
    public void J(final int n) {
        while (true) {
            this.mHandler.removeMessages(1);
            while (true) {
                int n2;
                synchronized (this.pt) {
                    this.pv = true;
                    final ArrayList<GoogleApiClient.ConnectionCallbacks> pt = this.pt;
                    final int size = pt.size();
                    n2 = 0;
                    if (n2 >= size || !this.ps.bp()) {
                        this.pv = false;
                        return;
                    }
                    if (this.pt.contains(pt.get(n2))) {
                        pt.get(n2).onConnectionSuspended(n);
                    }
                }
                ++n2;
                continue;
            }
        }
    }
    
    public void a(final ConnectionResult connectionResult) {
        while (true) {
            this.mHandler.removeMessages(1);
            while (true) {
                int n;
                synchronized (this.pw) {
                    this.px = true;
                    final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> pw = this.pw;
                    final int size = pw.size();
                    n = 0;
                    if (n >= size) {
                        this.px = false;
                        return;
                    }
                    if (!this.ps.bp()) {
                        return;
                    }
                    if (this.pw.contains(pw.get(n))) {
                        pw.get(n).onConnectionFailed(connectionResult);
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public void b(final Bundle bundle) {
        boolean b;
        boolean b2;
        boolean b3;
        ArrayList<GoogleApiClient.ConnectionCallbacks> pt;
        int size;
        int n = 0;
        Label_0054_Outer:Label_0073_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0178:
                while (true) {
                Label_0172:
                    while (true) {
                        synchronized (this.pt) {
                            if (!this.pv) {
                                b2 = true;
                                eg.p(b2);
                                this.mHandler.removeMessages(1);
                                this.pv = true;
                                if (this.pu.size() != 0) {
                                    break Label_0172;
                                }
                                b3 = b;
                                eg.p(b3);
                                pt = this.pt;
                                size = pt.size();
                                n = 0;
                                if (n >= size || !this.ps.bp() || !this.ps.isConnected()) {
                                    this.pu.clear();
                                    this.pv = false;
                                    return;
                                }
                                this.pu.size();
                                if (!this.pu.contains(pt.get(n))) {
                                    pt.get(n).onConnected(bundle);
                                }
                                break Label_0178;
                            }
                        }
                        b2 = false;
                        continue Label_0054_Outer;
                    }
                    b3 = false;
                    continue Label_0073_Outer;
                }
                ++n;
                continue;
            }
        }
    }
    
    protected void bT() {
        synchronized (this.pt) {
            this.b(this.ps.aU());
        }
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        eg.f(connectionCallbacks);
        synchronized (this.pt) {
            return this.pt.contains(connectionCallbacks);
        }
    }
    
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        eg.f(onConnectionFailedListener);
        synchronized (this.pw) {
            return this.pw.contains(onConnectionFailedListener);
        }
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        eg.f(connectionCallbacks);
        synchronized (this.pt) {
            if (this.pt.contains(connectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + connectionCallbacks + " is already registered");
            }
            else {
                if (this.pv) {
                    this.pt = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.pt);
                }
                this.pt.add(connectionCallbacks);
            }
            // monitorexit(this.pt)
            if (this.ps.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        eg.f(onConnectionFailedListener);
        synchronized (this.pw) {
            if (this.pw.contains(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            }
            else {
                if (this.px) {
                    this.pw = new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>(this.pw);
                }
                this.pw.add(onConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        eg.f(connectionCallbacks);
        synchronized (this.pt) {
            if (this.pt != null) {
                if (this.pv) {
                    this.pt = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.pt);
                }
                if (!this.pt.remove(connectionCallbacks)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + connectionCallbacks + " not found");
                }
                else if (this.pv && !this.pu.contains(connectionCallbacks)) {
                    this.pu.add(connectionCallbacks);
                }
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        eg.f(onConnectionFailedListener);
        synchronized (this.pw) {
            if (this.pw != null) {
                if (this.px) {
                    this.pw = new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>(this.pw);
                }
                if (!this.pw.remove(onConnectionFailedListener)) {
                    Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + onConnectionFailedListener + " not found");
                }
            }
        }
    }
    
    final class a extends Handler
    {
        public a(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            if (message.what == 1) {
                synchronized (dx.this.pt) {
                    if (dx.this.ps.bp() && dx.this.ps.isConnected() && dx.this.pt.contains(message.obj)) {
                        ((GoogleApiClient.ConnectionCallbacks)message.obj).onConnected(dx.this.ps.aU());
                    }
                    return;
                }
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }
    
    public interface b
    {
        Bundle aU();
        
        boolean bp();
        
        boolean isConnected();
    }
}
