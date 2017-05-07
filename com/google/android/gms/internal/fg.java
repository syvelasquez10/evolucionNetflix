// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.os.Bundle;
import android.content.Context;
import android.os.RemoteException;

@ez
public abstract class fg extends gg
{
    private final fi pQ;
    private final ff.a tu;
    
    public fg(final fi pq, final ff.a tu) {
        this.pQ = pq;
        this.tu = tu;
    }
    
    private static fk a(final fm fm, final fi fi) {
        try {
            return fm.b(fi);
        }
        catch (RemoteException ex) {
            gs.d("Could not fetch ad response from ad request service.", (Throwable)ex);
            return null;
        }
        catch (NullPointerException ex2) {
            gs.d("Could not fetch ad response from ad request service due to an Exception.", ex2);
            return null;
        }
        catch (SecurityException ex3) {
            gs.d("Could not fetch ad response from ad request service due to an Exception.", ex3);
            return null;
        }
        catch (Throwable t) {
            gb.e(t);
            return null;
        }
    }
    
    public abstract void cD();
    
    public abstract fm cE();
    
    @Override
    public final void cp() {
        try {
            final fm ce = this.cE();
            fk a;
            if (ce == null) {
                a = new fk(0);
            }
            else if ((a = a(ce, this.pQ)) == null) {
                a = new fk(0);
            }
            this.cD();
            this.tu.a(a);
        }
        finally {
            this.cD();
        }
    }
    
    @Override
    public final void onStop() {
        this.cD();
    }
    
    @ez
    public static final class a extends fg
    {
        private final Context mContext;
        
        public a(final Context mContext, final fi fi, final ff.a a) {
            super(fi, a);
            this.mContext = mContext;
        }
        
        @Override
        public void cD() {
        }
        
        @Override
        public fm cE() {
            final Bundle bd = gb.bD();
            return fr.a(this.mContext, new bm(bd.getString("gads:sdk_core_location"), bd.getString("gads:sdk_core_experiment_id"), bd.getString("gads:block_autoclicks_experiment_id")), new cj(), new fy());
        }
    }
    
    @ez
    public static final class b extends fg implements ConnectionCallbacks, OnConnectionFailedListener
    {
        private final Object mw;
        private final ff.a tu;
        private final fh tv;
        
        public b(final Context context, final fi fi, final ff.a tu) {
            super(fi, tu);
            this.mw = new Object();
            this.tu = tu;
            (this.tv = new fh(context, this, this, fi.lD.wF)).connect();
        }
        
        @Override
        public void cD() {
            synchronized (this.mw) {
                if (this.tv.isConnected() || this.tv.isConnecting()) {
                    this.tv.disconnect();
                }
            }
        }
        
        @Override
        public fm cE() {
            synchronized (this.mw) {
                try {
                    return this.tv.cF();
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
            this.tu.a(new fk(0));
        }
        
        @Override
        public void onDisconnected() {
            gs.S("Disconnected from remote ad request service.");
        }
    }
}
