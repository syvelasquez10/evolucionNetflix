// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collection;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Handler$Callback;

public final class zzk implements Handler$Callback
{
    private final Handler mHandler;
    private final zzk$zza zzadM;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> zzadN;
    final ArrayList<GoogleApiClient$ConnectionCallbacks> zzadO;
    private final ArrayList<GoogleApiClient$OnConnectionFailedListener> zzadP;
    private volatile boolean zzadQ;
    private final AtomicInteger zzadR;
    private boolean zzadS;
    private final Object zzpc;
    
    public zzk(final Looper looper, final zzk$zza zzadM) {
        this.zzadN = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzadO = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzadP = new ArrayList<GoogleApiClient$OnConnectionFailedListener>();
        this.zzadQ = false;
        this.zzadR = new AtomicInteger(0);
        this.zzadS = false;
        this.zzpc = new Object();
        this.zzadM = zzadM;
        this.mHandler = new Handler(looper, (Handler$Callback)this);
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks = (GoogleApiClient$ConnectionCallbacks)message.obj;
            synchronized (this.zzpc) {
                if (this.zzadQ && this.zzadM.isConnected() && this.zzadN.contains(googleApiClient$ConnectionCallbacks)) {
                    googleApiClient$ConnectionCallbacks.onConnected(this.zzadM.zzmw());
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        return false;
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzx.zzv(googleApiClient$ConnectionCallbacks);
        synchronized (this.zzpc) {
            if (this.zzadN.contains(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " is already registered");
            }
            else {
                this.zzadN.add(googleApiClient$ConnectionCallbacks);
            }
            // monitorexit(this.zzpc)
            if (this.zzadM.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)googleApiClient$ConnectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzv(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzpc) {
            if (this.zzadP.contains(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " is already registered");
            }
            else {
                this.zzadP.add(googleApiClient$OnConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzx.zzv(googleApiClient$ConnectionCallbacks);
        synchronized (this.zzpc) {
            if (!this.zzadN.remove(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " not found");
            }
            else if (this.zzadS) {
                this.zzadO.add(googleApiClient$ConnectionCallbacks);
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzv(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzpc) {
            if (!this.zzadP.remove(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " not found");
            }
        }
    }
    
    public void zzbB(final int n) {
        boolean b = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            b = true;
        }
        zzx.zza(b, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpc) {
            this.zzadS = true;
            final ArrayList<GoogleApiClient$ConnectionCallbacks> list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzadN);
            final int value = this.zzadR.get();
            for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                if (!this.zzadQ || this.zzadR.get() != value) {
                    break;
                }
                if (!this.zzadN.contains(googleApiClient$ConnectionCallbacks)) {
                    continue;
                }
                googleApiClient$ConnectionCallbacks.onConnectionSuspended(n);
            }
            this.zzadO.clear();
            this.zzadS = false;
        }
    }
    
    public void zzh(final Bundle bundle) {
        final boolean b = true;
        while (true) {
            Label_0201: {
                if (Looper.myLooper() != this.mHandler.getLooper()) {
                    break Label_0201;
                }
                final boolean b2 = true;
                boolean b3;
                boolean b4;
                ArrayList<GoogleApiClient$ConnectionCallbacks> list;
                int value;
                Label_0042_Outer:Label_0072_Outer:
                while (true) {
                    zzx.zza(b2, "onConnectionSuccess must only be called on the Handler thread");
                    while (true) {
                    Label_0211:
                        while (true) {
                            Label_0206: {
                                synchronized (this.zzpc) {
                                    if (this.zzadS) {
                                        break Label_0206;
                                    }
                                    b3 = true;
                                    zzx.zzY(b3);
                                    this.mHandler.removeMessages(1);
                                    this.zzadS = true;
                                    if (this.zzadO.size() == 0) {
                                        b4 = b;
                                        zzx.zzY(b4);
                                        list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzadN);
                                        value = this.zzadR.get();
                                        for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                                            if (!this.zzadQ || !this.zzadM.isConnected() || this.zzadR.get() != value) {
                                                break;
                                            }
                                            if (this.zzadO.contains(googleApiClient$ConnectionCallbacks)) {
                                                continue Label_0042_Outer;
                                            }
                                            googleApiClient$ConnectionCallbacks.onConnected(bundle);
                                        }
                                        this.zzadO.clear();
                                        this.zzadS = false;
                                        return;
                                    }
                                    break Label_0211;
                                }
                                break;
                            }
                            b3 = false;
                            continue Label_0072_Outer;
                        }
                        b4 = false;
                        continue;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    public void zzj(final ConnectionResult connectionResult) {
        zzx.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpc) {
            final ArrayList<GoogleApiClient$OnConnectionFailedListener> list = new ArrayList<GoogleApiClient$OnConnectionFailedListener>(this.zzadP);
            final int value = this.zzadR.get();
            for (final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener : list) {
                if (!this.zzadQ || this.zzadR.get() != value) {
                    return;
                }
                if (!this.zzadP.contains(googleApiClient$OnConnectionFailedListener)) {
                    continue;
                }
                googleApiClient$OnConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(o)
    
    public void zzoI() {
        this.zzadQ = false;
        this.zzadR.incrementAndGet();
    }
    
    public void zzoJ() {
        this.zzadQ = true;
    }
}
