// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.RemoteException;
import android.content.Context;

public final class as
{
    private final bb ed;
    private ax fA;
    private final bz fw;
    private final Object fx;
    private final au fy;
    private boolean fz;
    private final Context mContext;
    
    public as(final Context mContext, final bz fw, final bb ed, final au fy) {
        this.fx = new Object();
        this.fz = false;
        this.mContext = mContext;
        this.fw = fw;
        this.ed = ed;
        this.fy = fy;
    }
    
    public ay a(final long n, final long n2) {
        ct.r("Starting mediation.");
        for (final at at : this.fy.fI) {
            ct.t("Trying mediation network: " + at.fD);
            for (final String s : at.fE) {
                Object o = this.fx;
                synchronized (o) {
                    if (this.fz) {
                        return new ay(-1);
                    }
                    this.fA = new ax(this.mContext, s, this.ed, this.fy, at, this.fw.hr, this.fw.em, this.fw.ej);
                    // monitorexit(o)
                    o = this.fA.b(n, n2);
                    if (((ay)o).ga == 0) {
                        ct.r("Adapter succeeded.");
                        return (ay)o;
                    }
                }
                if (((ay)o).gc != null) {
                    cs.iI.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ((ay)o).gc.destroy();
                            }
                            catch (RemoteException ex) {
                                ct.b("Could not destroy mediation adapter.", (Throwable)ex);
                            }
                        }
                    });
                }
            }
        }
        return new ay(1);
    }
    
    public void cancel() {
        synchronized (this.fx) {
            this.fz = true;
            if (this.fA != null) {
                this.fA.cancel();
            }
        }
    }
}
