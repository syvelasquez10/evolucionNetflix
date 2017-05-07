// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.RemoteException;
import android.content.Context;

@ez
public final class ck
{
    private final ct lq;
    private final Context mContext;
    private final Object mw;
    private final fi pQ;
    private final cm pR;
    private boolean pS;
    private cp pT;
    
    public ck(final Context mContext, final fi pq, final ct lq, final cm pr) {
        this.mw = new Object();
        this.pS = false;
        this.mContext = mContext;
        this.pQ = pq;
        this.lq = lq;
        this.pR = pr;
    }
    
    public cq a(final long n, final long n2) {
        gs.S("Starting mediation.");
        for (final cl cl : this.pR.qd) {
            gs.U("Trying mediation network: " + cl.pX);
            for (final String s : cl.pY) {
                Object o = this.mw;
                synchronized (o) {
                    if (this.pS) {
                        return new cq(-1);
                    }
                    this.pT = new cp(this.mContext, s, this.lq, this.pR, cl, this.pQ.tx, this.pQ.lH, this.pQ.lD);
                    // monitorexit(o)
                    o = this.pT.b(n, n2);
                    if (((cq)o).qx == 0) {
                        gs.S("Adapter succeeded.");
                        return (cq)o;
                    }
                }
                if (((cq)o).qz != null) {
                    gr.wC.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ((cq)o).qz.destroy();
                            }
                            catch (RemoteException ex) {
                                gs.d("Could not destroy mediation adapter.", (Throwable)ex);
                            }
                        }
                    });
                }
            }
        }
        return new cq(1);
    }
    
    public void cancel() {
        synchronized (this.mw) {
            this.pS = true;
            if (this.pT != null) {
                this.pT.cancel();
            }
        }
    }
}
