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
    private final zzk$zza zzafP;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> zzafQ;
    final ArrayList<GoogleApiClient$ConnectionCallbacks> zzafR;
    private final ArrayList<GoogleApiClient$OnConnectionFailedListener> zzafS;
    private volatile boolean zzafT;
    private final AtomicInteger zzafU;
    private boolean zzafV;
    private final Object zzpd;
    
    public zzk(final Looper looper, final zzk$zza zzafP) {
        this.zzafQ = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzafR = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzafS = new ArrayList<GoogleApiClient$OnConnectionFailedListener>();
        this.zzafT = false;
        this.zzafU = new AtomicInteger(0);
        this.zzafV = false;
        this.zzpd = new Object();
        this.zzafP = zzafP;
        this.mHandler = new Handler(looper, (Handler$Callback)this);
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks = (GoogleApiClient$ConnectionCallbacks)message.obj;
            synchronized (this.zzpd) {
                if (this.zzafT && this.zzafP.isConnected() && this.zzafQ.contains(googleApiClient$ConnectionCallbacks)) {
                    googleApiClient$ConnectionCallbacks.onConnected(this.zzafP.zzmS());
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
        return false;
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzx.zzw(googleApiClient$ConnectionCallbacks);
        synchronized (this.zzpd) {
            if (this.zzafQ.contains(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " is already registered");
            }
            else {
                this.zzafQ.add(googleApiClient$ConnectionCallbacks);
            }
            // monitorexit(this.zzpd)
            if (this.zzafP.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)googleApiClient$ConnectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzw(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzpd) {
            if (this.zzafS.contains(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " is already registered");
            }
            else {
                this.zzafS.add(googleApiClient$OnConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzw(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzpd) {
            if (!this.zzafS.remove(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " not found");
            }
        }
    }
    
    public void zzbG(final int n) {
        boolean b = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            b = true;
        }
        zzx.zza(b, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpd) {
            this.zzafV = true;
            final ArrayList<GoogleApiClient$ConnectionCallbacks> list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzafQ);
            final int value = this.zzafU.get();
            for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                if (!this.zzafT || this.zzafU.get() != value) {
                    break;
                }
                if (!this.zzafQ.contains(googleApiClient$ConnectionCallbacks)) {
                    continue;
                }
                googleApiClient$ConnectionCallbacks.onConnectionSuspended(n);
            }
            this.zzafR.clear();
            this.zzafV = false;
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
                                synchronized (this.zzpd) {
                                    if (this.zzafV) {
                                        break Label_0206;
                                    }
                                    b3 = true;
                                    zzx.zzZ(b3);
                                    this.mHandler.removeMessages(1);
                                    this.zzafV = true;
                                    if (this.zzafR.size() == 0) {
                                        b4 = b;
                                        zzx.zzZ(b4);
                                        list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzafQ);
                                        value = this.zzafU.get();
                                        for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                                            if (!this.zzafT || !this.zzafP.isConnected() || this.zzafU.get() != value) {
                                                break;
                                            }
                                            if (this.zzafR.contains(googleApiClient$ConnectionCallbacks)) {
                                                continue Label_0042_Outer;
                                            }
                                            googleApiClient$ConnectionCallbacks.onConnected(bundle);
                                        }
                                        this.zzafR.clear();
                                        this.zzafV = false;
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
    
    public void zzi(final ConnectionResult connectionResult) {
        zzx.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzpd) {
            final ArrayList<GoogleApiClient$OnConnectionFailedListener> list = new ArrayList<GoogleApiClient$OnConnectionFailedListener>(this.zzafS);
            final int value = this.zzafU.get();
            for (final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener : list) {
                if (!this.zzafT || this.zzafU.get() != value) {
                    return;
                }
                if (!this.zzafS.contains(googleApiClient$OnConnectionFailedListener)) {
                    continue;
                }
                googleApiClient$OnConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(o)
    
    public void zzpk() {
        this.zzafT = false;
        this.zzafU.incrementAndGet();
    }
    
    public void zzpl() {
        this.zzafT = true;
    }
}
