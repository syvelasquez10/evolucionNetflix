// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

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

public final class e
{
    private final b LE;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> LF;
    final ArrayList<GoogleApiClient.ConnectionCallbacks> LG;
    private boolean LH;
    private final ArrayList<GooglePlayServicesClient.OnConnectionFailedListener> LI;
    private final Handler mHandler;
    
    public e(final Context context, final Looper looper, final b le) {
        this.LF = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.LG = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.LH = false;
        this.LI = new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>();
        this.LE = le;
        this.mHandler = new a(looper);
    }
    
    public void aB(final int n) {
        this.mHandler.removeMessages(1);
        synchronized (this.LF) {
            this.LH = true;
            for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.LF)) {
                if (!this.LE.gr()) {
                    break;
                }
                if (!this.LF.contains(connectionCallbacks)) {
                    continue;
                }
                connectionCallbacks.onConnectionSuspended(n);
            }
            this.LH = false;
        }
    }
    
    public void b(final ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.LI) {
            for (final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener : new ArrayList<GooglePlayServicesClient.OnConnectionFailedListener>(this.LI)) {
                if (!this.LE.gr()) {
                    return;
                }
                if (!this.LI.contains(onConnectionFailedListener)) {
                    continue;
                }
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(list)
    
    public void d(final Bundle bundle) {
        boolean b;
        boolean b2;
        boolean b3;
        Label_0020_Outer:Label_0049_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0165:
                while (true) {
                    synchronized (this.LF) {
                        if (!this.LH) {
                            b2 = true;
                            n.I(b2);
                            this.mHandler.removeMessages(1);
                            this.LH = true;
                            if (this.LG.size() == 0) {
                                b3 = b;
                                n.I(b3);
                                for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.LF)) {
                                    if (!this.LE.gr() || !this.LE.isConnected()) {
                                        break;
                                    }
                                    if (this.LG.contains(connectionCallbacks)) {
                                        continue Label_0020_Outer;
                                    }
                                    connectionCallbacks.onConnected(bundle);
                                }
                                this.LG.clear();
                                this.LH = false;
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
    
    protected void dM() {
        synchronized (this.LF) {
            this.d(this.LE.fD());
        }
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        n.i(connectionCallbacks);
        synchronized (this.LF) {
            return this.LF.contains(connectionCallbacks);
        }
    }
    
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        n.i(onConnectionFailedListener);
        synchronized (this.LI) {
            return this.LI.contains(onConnectionFailedListener);
        }
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        n.i(connectionCallbacks);
        synchronized (this.LF) {
            if (this.LF.contains(connectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + connectionCallbacks + " is already registered");
            }
            else {
                this.LF.add(connectionCallbacks);
            }
            // monitorexit(this.LF)
            if (this.LE.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        n.i(onConnectionFailedListener);
        synchronized (this.LI) {
            if (this.LI.contains(onConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + onConnectionFailedListener + " is already registered");
            }
            else {
                this.LI.add(onConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        n.i(connectionCallbacks);
        synchronized (this.LF) {
            if (this.LF != null) {
                if (!this.LF.remove(connectionCallbacks)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + connectionCallbacks + " not found");
                }
                else if (this.LH) {
                    this.LG.add(connectionCallbacks);
                }
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
        n.i(onConnectionFailedListener);
        synchronized (this.LI) {
            if (this.LI != null && !this.LI.remove(onConnectionFailedListener)) {
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
                synchronized (e.this.LF) {
                    if (e.this.LE.gr() && e.this.LE.isConnected() && e.this.LF.contains(message.obj)) {
                        ((GoogleApiClient.ConnectionCallbacks)message.obj).onConnected(e.this.LE.fD());
                    }
                    return;
                }
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }
    
    public interface b
    {
        Bundle fD();
        
        boolean gr();
        
        boolean isConnected();
    }
}
