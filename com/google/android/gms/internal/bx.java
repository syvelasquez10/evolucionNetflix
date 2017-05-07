// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;
import android.os.RemoteException;

public abstract class bx extends cm
{
    private final bz fw;
    private final bw.a hn;
    
    public bx(final bz fw, final bw.a hn) {
        this.fw = fw;
        this.hn = hn;
    }
    
    private static cb a(final cd cd, final bz bz) {
        try {
            return cd.b(bz);
        }
        catch (RemoteException ex) {
            ct.b("Could not fetch ad response from ad request service.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final void ai() {
        try {
            final cd al = this.al();
            cb a;
            if (al == null) {
                a = new cb(0);
            }
            else if ((a = a(al, this.fw)) == null) {
                a = new cb(0);
            }
            this.ak();
            this.hn.a(a);
        }
        finally {
            this.ak();
        }
    }
    
    public abstract void ak();
    
    public abstract cd al();
    
    @Override
    public final void onStop() {
        this.ak();
    }
    
    public static final class a extends bx
    {
        private final Context mContext;
        
        public a(final Context mContext, final bz bz, final bw.a a) {
            super(bz, a);
            this.mContext = mContext;
        }
        
        @Override
        public void ak() {
        }
        
        @Override
        public cd al() {
            return ce.a(this.mContext, new ar());
        }
    }
    
    public static final class b extends bx implements ConnectionCallbacks, OnConnectionFailedListener
    {
        private final Object fx;
        private final bw.a hn;
        private final by ho;
        
        public b(final Context context, final bz bz, final bw.a hn) {
            super(bz, hn);
            this.fx = new Object();
            this.hn = hn;
            (this.ho = new by(context, this, this, bz.ej.iL)).connect();
        }
        
        @Override
        public void ak() {
            synchronized (this.fx) {
                if (this.ho.isConnected() || this.ho.isConnecting()) {
                    this.ho.disconnect();
                }
            }
        }
        
        @Override
        public cd al() {
            synchronized (this.fx) {
                try {
                    return this.ho.ao();
                }
                catch (IllegalStateException ex) {
                    return null;
                }
            }
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            this.start();
        }
        
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            this.hn.a(new cb(0));
        }
        
        @Override
        public void onDisconnected() {
            ct.r("Disconnected from remote ad request service.");
        }
    }
}
