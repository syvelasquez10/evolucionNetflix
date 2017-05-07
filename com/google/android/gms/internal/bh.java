// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.RemoteException;
import android.content.Context;

public final class bh
{
    private final bq ky;
    private final Object li;
    private final Context mContext;
    private final cx mQ;
    private final bj mR;
    private boolean mS;
    private bm mT;
    
    public bh(final Context mContext, final cx mq, final bq ky, final bj mr) {
        this.li = new Object();
        this.mS = false;
        this.mContext = mContext;
        this.mQ = mq;
        this.ky = ky;
        this.mR = mr;
    }
    
    public bn a(final long n, final long n2) {
        dw.v("Starting mediation.");
        for (final bi bi : this.mR.nc) {
            dw.x("Trying mediation network: " + bi.mX);
            for (final String s : bi.mY) {
                Object o = this.li;
                synchronized (o) {
                    if (this.mS) {
                        return new bn(-1);
                    }
                    this.mT = new bm(this.mContext, s, this.ky, this.mR, bi, this.mQ.pg, this.mQ.kN, this.mQ.kK);
                    // monitorexit(o)
                    o = this.mT.b(n, n2);
                    if (((bn)o).nw == 0) {
                        dw.v("Adapter succeeded.");
                        return (bn)o;
                    }
                }
                if (((bn)o).ny != null) {
                    dv.rp.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ((bn)o).ny.destroy();
                            }
                            catch (RemoteException ex) {
                                dw.c("Could not destroy mediation adapter.", (Throwable)ex);
                            }
                        }
                    });
                }
            }
        }
        return new bn(1);
    }
    
    public void cancel() {
        synchronized (this.li) {
            this.mS = true;
            if (this.mT != null) {
                this.mT.cancel();
            }
        }
    }
}
