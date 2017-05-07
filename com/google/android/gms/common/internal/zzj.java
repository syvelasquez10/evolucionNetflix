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

public final class zzj implements Handler$Callback
{
    private final Handler mHandler;
    private final zzj$zza zzaaB;
    private final ArrayList<GoogleApiClient$ConnectionCallbacks> zzaaC;
    final ArrayList<GoogleApiClient$ConnectionCallbacks> zzaaD;
    private final ArrayList<GoogleApiClient$OnConnectionFailedListener> zzaaE;
    private volatile boolean zzaaF;
    private final AtomicInteger zzaaG;
    private boolean zzaaH;
    private final Object zzqt;
    
    public zzj(final Looper looper, final zzj$zza zzaaB) {
        this.zzaaC = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzaaD = new ArrayList<GoogleApiClient$ConnectionCallbacks>();
        this.zzaaE = new ArrayList<GoogleApiClient$OnConnectionFailedListener>();
        this.zzaaF = false;
        this.zzaaG = new AtomicInteger(0);
        this.zzaaH = false;
        this.zzqt = new Object();
        this.zzaaB = zzaaB;
        this.mHandler = new Handler(looper, (Handler$Callback)this);
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks = (GoogleApiClient$ConnectionCallbacks)message.obj;
            synchronized (this.zzqt) {
                if (this.zzaaF && this.zzaaB.isConnected() && this.zzaaC.contains(googleApiClient$ConnectionCallbacks)) {
                    googleApiClient$ConnectionCallbacks.onConnected(this.zzaaB.zzlK());
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        return false;
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzu.zzu(googleApiClient$ConnectionCallbacks);
        synchronized (this.zzqt) {
            if (this.zzaaC.contains(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " is already registered");
            }
            else {
                this.zzaaC.add(googleApiClient$ConnectionCallbacks);
            }
            // monitorexit(this.zzqt)
            if (this.zzaaB.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)googleApiClient$ConnectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzu.zzu(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzqt) {
            if (this.zzaaE.contains(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " is already registered");
            }
            else {
                this.zzaaE.add(googleApiClient$OnConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks) {
        zzu.zzu(googleApiClient$ConnectionCallbacks);
        synchronized (this.zzqt) {
            if (!this.zzaaC.remove(googleApiClient$ConnectionCallbacks)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks + " not found");
            }
            else if (this.zzaaH) {
                this.zzaaD.add(googleApiClient$ConnectionCallbacks);
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzu.zzu(googleApiClient$OnConnectionFailedListener);
        synchronized (this.zzqt) {
            if (!this.zzaaE.remove(googleApiClient$OnConnectionFailedListener)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener + " not found");
            }
        }
    }
    
    public void zzbu(final int n) {
        this.mHandler.removeMessages(1);
        synchronized (this.zzqt) {
            this.zzaaH = true;
            final ArrayList<GoogleApiClient$ConnectionCallbacks> list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzaaC);
            final int value = this.zzaaG.get();
            for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                if (!this.zzaaF || this.zzaaG.get() != value) {
                    break;
                }
                if (!this.zzaaC.contains(googleApiClient$ConnectionCallbacks)) {
                    continue;
                }
                googleApiClient$ConnectionCallbacks.onConnectionSuspended(n);
            }
            this.zzaaD.clear();
            this.zzaaH = false;
        }
    }
    
    public void zzg(final Bundle bundle) {
        boolean b;
        boolean b2;
        boolean b3;
        ArrayList<GoogleApiClient$ConnectionCallbacks> list;
        int value;
        Label_0021_Outer:Label_0051_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0185:
                while (true) {
                    synchronized (this.zzqt) {
                        if (!this.zzaaH) {
                            b2 = true;
                            zzu.zzU(b2);
                            this.mHandler.removeMessages(1);
                            this.zzaaH = true;
                            if (this.zzaaD.size() == 0) {
                                b3 = b;
                                zzu.zzU(b3);
                                list = new ArrayList<GoogleApiClient$ConnectionCallbacks>(this.zzaaC);
                                value = this.zzaaG.get();
                                for (final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks : list) {
                                    if (!this.zzaaF || !this.zzaaB.isConnected() || this.zzaaG.get() != value) {
                                        break;
                                    }
                                    if (this.zzaaD.contains(googleApiClient$ConnectionCallbacks)) {
                                        continue Label_0021_Outer;
                                    }
                                    googleApiClient$ConnectionCallbacks.onConnected(bundle);
                                }
                                this.zzaaD.clear();
                                this.zzaaH = false;
                                return;
                            }
                            break Label_0185;
                        }
                    }
                    b2 = false;
                    continue Label_0051_Outer;
                }
                b3 = false;
                continue;
            }
        }
    }
    
    public void zzh(final ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.zzqt) {
            final ArrayList<GoogleApiClient$OnConnectionFailedListener> list = new ArrayList<GoogleApiClient$OnConnectionFailedListener>(this.zzaaE);
            final int value = this.zzaaG.get();
            for (final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener : list) {
                if (!this.zzaaF || this.zzaaG.get() != value) {
                    return;
                }
                if (!this.zzaaE.contains(googleApiClient$OnConnectionFailedListener)) {
                    continue;
                }
                googleApiClient$OnConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(o)
    
    public void zznR() {
        this.zzaaF = false;
        this.zzaaG.incrementAndGet();
    }
    
    public void zznS() {
        this.zzaaF = true;
    }
}
