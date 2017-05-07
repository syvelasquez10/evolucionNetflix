// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;
import android.os.RemoteException;

public abstract class cv extends do
{
    private final cx mQ;
    private final cu.a pc;
    
    public cv(final cx mq, final cu.a pc) {
        this.mQ = mq;
        this.pc = pc;
    }
    
    private static cz a(final db db, final cx cx) {
        try {
            return db.b(cx);
        }
        catch (RemoteException ex) {
            dw.c("Could not fetch ad response from ad request service.", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final void aY() {
        try {
            final db bf = this.bf();
            cz a;
            if (bf == null) {
                a = new cz(0);
            }
            else if ((a = a(bf, this.mQ)) == null) {
                a = new cz(0);
            }
            this.be();
            this.pc.a(a);
        }
        finally {
            this.be();
        }
    }
    
    public abstract void be();
    
    public abstract db bf();
    
    @Override
    public final void onStop() {
        this.be();
    }
    
    public static final class a extends cv
    {
        private final Context mContext;
        
        public a(final Context mContext, final cx cx, final cu.a a) {
            super(cx, a);
            this.mContext = mContext;
        }
        
        @Override
        public void be() {
        }
        
        @Override
        public db bf() {
            return dc.a(this.mContext, new ax(), new bg());
        }
    }
    
    public static final class b extends cv implements ConnectionCallbacks, OnConnectionFailedListener
    {
        private final Object li;
        private final cu.a pc;
        private final cw pd;
        
        public b(final Context context, final cx cx, final cu.a pc) {
            super(cx, pc);
            this.li = new Object();
            this.pc = pc;
            (this.pd = new cw(context, this, this, cx.kK.rs)).connect();
        }
        
        @Override
        public void be() {
            synchronized (this.li) {
                if (this.pd.isConnected() || this.pd.isConnecting()) {
                    this.pd.disconnect();
                }
            }
        }
        
        @Override
        public db bf() {
            synchronized (this.li) {
                try {
                    return this.pd.bi();
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
            this.pc.a(new cz(0));
        }
        
        @Override
        public void onDisconnected() {
            dw.v("Disconnected from remote ad request service.");
        }
    }
}
