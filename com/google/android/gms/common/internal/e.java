// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.util.Collection;
import android.os.Looper;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;

public final class e
{
    private final e$b LE;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> LF;
    final ArrayList<GoogleApiClient$ConnectionCallbacks> LG;
    private boolean LH;
    private final ArrayList<GooglePlayServicesClient$OnConnectionFailedListener> LI;
    private final Handler mHandler;
    
    public e(final Context context, final Looper looper, final e$b le) {
        this.LF = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.LG = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.LH = false;
        this.LI = new ArrayList<GooglePlayServicesClient$OnConnectionFailedListener>();
        this.LE = le;
        this.mHandler = new e$a(this, looper);
    }
    
    public void aB(final int n) {
        this.mHandler.removeMessages(1);
        synchronized (this.LF) {
            this.LH = true;
            for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.LF)) {
                if (!this.LE.gr()) {
                    break;
                }
                if (!this.LF.contains(googleApiClient$ConnectionCallbacks)) {
                    continue;
                }
                googleApiClient$ConnectionCallbacks.onConnectionSuspended(n);
            }
            this.LH = false;
        }
    }
    
    public void b(final ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.LI) {
            for (final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener : new ArrayList<GooglePlayServicesClient$OnConnectionFailedListener>(this.LI)) {
                if (!this.LE.gr()) {
                    return;
                }
                if (!this.LI.contains(googlePlayServicesClient$OnConnectionFailedListener)) {
                    continue;
                }
                googlePlayServicesClient$OnConnectionFailedListener.onConnectionFailed(connectionResult);
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
                                for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.LF)) {
                                    if (!this.LE.gr() || !this.LE.isConnected()) {
                                        break;
                                    }
                                    if (this.LG.contains(googleApiClient$ConnectionCallbacks)) {
                                        continue Label_0020_Outer;
                                    }
                                    googleApiClient$ConnectionCallbacks.onConnected(bundle);
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
    
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        n.i(googleApiClient$ConnectionCallbacks);
        synchronized (this.LF) {
            if (this.LF.contains(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " is already registered");
            }
            else {
                this.LF.add(googleApiClient$ConnectionCallbacks);
            }
            // monitorexit(this.LF)
            if (this.LE.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)googleApiClient$ConnectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        n.i(googlePlayServicesClient$OnConnectionFailedListener);
        synchronized (this.LI) {
            if (this.LI.contains(googlePlayServicesClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + googlePlayServicesClient$OnConnectionFailedListener + " is already registered");
            }
            else {
                this.LI.add(googlePlayServicesClient$OnConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        n.i(googleApiClient$ConnectionCallbacks);
        synchronized (this.LF) {
            if (this.LF != null) {
                if (!this.LF.remove(googleApiClient$ConnectionCallbacks)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " not found");
                }
                else if (this.LH) {
                    this.LG.add(googleApiClient$ConnectionCallbacks);
                }
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        n.i(googlePlayServicesClient$OnConnectionFailedListener);
        synchronized (this.LI) {
            if (this.LI != null && !this.LI.remove(googlePlayServicesClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + googlePlayServicesClient$OnConnectionFailedListener + " not found");
            }
        }
    }
}
