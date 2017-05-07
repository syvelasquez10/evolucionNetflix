// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Message;
import android.util.Log;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.util.Collection;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;

public final class fg
{
    private final b Do;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> Dp;
    final ArrayList<GoogleApiClient.ConnectionCallbacks> Dq;
    private boolean Dr;
    private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> Ds;
    private final Handler mHandler;
    
    public fg(final Context context, final Looper looper, final b do1) {
        this.Dp = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.Dq = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.Dr = false;
        this.Ds = new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>();
        this.Do = do1;
        this.mHandler = new a(looper);
    }
    
    public void O(final int n) {
        this.mHandler.removeMessages(1);
        synchronized (this.Dp) {
            this.Dr = true;
            for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.Dp)) {
                if (!this.Do.em()) {
                    break;
                }
                if (!this.Dp.contains(connectionCallbacks)) {
                    continue;
                }
                connectionCallbacks.onConnectionSuspended(n);
            }
            this.Dr = false;
        }
    }
    
    public void a(final ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.Ds) {
            for (final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener : new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>(this.Ds)) {
                if (!this.Do.em()) {
                    return;
                }
                if (!this.Ds.contains(onConnectionFailedListener)) {
                    continue;
                }
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(list)
    
    public void b(final Bundle bundle) {
        boolean b;
        boolean b2;
        boolean b3;
        Label_0020_Outer:Label_0049_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0165:
                while (true) {
                    synchronized (this.Dp) {
                        if (!this.Dr) {
                            b2 = true;
                            fq.x(b2);
                            this.mHandler.removeMessages(1);
                            this.Dr = true;
                            if (this.Dq.size() == 0) {
                                b3 = b;
                                fq.x(b3);
                                for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.Dp)) {
                                    if (!this.Do.em() || !this.Do.isConnected()) {
                                        break;
                                    }
                                    if (this.Dq.contains(connectionCallbacks)) {
                                        continue Label_0020_Outer;
                                    }
                                    connectionCallbacks.onConnected(bundle);
                                }
                                this.Dq.clear();
                                this.Dr = false;
                                return;
                            }
                            break Label_0165;
                        }
                    }
                    b2 = false;
                    continue Label_0049_Outer;
                }
                b3 = false;
                continue;
            }
        }
    }
    
    protected void bV() {
        synchronized (this.Dp) {
            this.b(this.Do.dG());
        }
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        fq.f(connectionCallbacks);
        synchronized (this.Dp) {
            return this.Dp.contains(connectionCallbacks);
        }
    }
    
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        fq.f(onConnectionFailedListener);
        synchronized (this.Ds) {
            return this.Ds.contains(onConnectionFailedListener);
        }
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        fq.f(connectionCallbacks);
        synchronized (this.Dp) {
            if (this.Dp.contains(connectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + connectionCallbacks + " is already registered");
            }
            else {
                this.Dp.add(connectionCallbacks);
            }
            // monitorexit(this.Dp)
            if (this.Do.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        fq.f(onConnectionFailedListener);
        synchronized (this.Ds) {
            if (this.Ds.contains(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            }
            else {
                this.Ds.add(onConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        fq.f(connectionCallbacks);
        synchronized (this.Dp) {
            if (this.Dp != null) {
                if (!this.Dp.remove(connectionCallbacks)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + connectionCallbacks + " not found");
                }
                else if (this.Dr) {
                    this.Dq.add(connectionCallbacks);
                }
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        fq.f(onConnectionFailedListener);
        synchronized (this.Ds) {
            if (this.Ds != null && !this.Ds.remove(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + onConnectionFailedListener + " not found");
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
                synchronized (fg.this.Dp) {
                    if (fg.this.Do.em() && fg.this.Do.isConnected() && fg.this.Dp.contains(message.obj)) {
                        ((GoogleApiClient.ConnectionCallbacks)message.obj).onConnected(fg.this.Do.dG());
                    }
                    return;
                }
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }
    
    public interface b
    {
        Bundle dG();
        
        boolean em();
        
        boolean isConnected();
    }
}
