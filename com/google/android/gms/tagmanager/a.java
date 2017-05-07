// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;

class a
{
    private static a anF;
    private static Object xz;
    private volatile long anB;
    private volatile long anC;
    private volatile long anD;
    private a$a anE;
    private volatile boolean mClosed;
    private final Context mContext;
    private final Thread wf;
    private volatile AdvertisingIdClient$Info xB;
    private final ju yD;
    
    static {
        a.xz = new Object();
    }
    
    private a(final Context context) {
        this(context, null, jw.hA());
    }
    
    a(final Context mContext, final a$a anE, final ju yd) {
        this.anB = 900000L;
        this.anC = 30000L;
        this.mClosed = false;
        this.anE = new a$1(this);
        this.yD = yd;
        if (mContext != null) {
            this.mContext = mContext.getApplicationContext();
        }
        else {
            this.mContext = mContext;
        }
        if (anE != null) {
            this.anE = anE;
        }
        this.wf = new Thread(new a$2(this));
    }
    
    static a V(final Context context) {
        Label_0037: {
            if (a.anF != null) {
                break Label_0037;
            }
            synchronized (a.xz) {
                if (a.anF == null) {
                    (a.anF = new a(context)).start();
                }
                return a.anF;
            }
        }
    }
    
    private void nI() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            try {
                this.xB = this.anE.nK();
                Thread.sleep(this.anB);
            }
            catch (InterruptedException ex) {
                bh.U("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }
    
    private void nJ() {
        if (this.yD.currentTimeMillis() - this.anD < this.anC) {
            return;
        }
        this.interrupt();
        this.anD = this.yD.currentTimeMillis();
    }
    
    void interrupt() {
        this.wf.interrupt();
    }
    
    public boolean isLimitAdTrackingEnabled() {
        this.nJ();
        return this.xB == null || this.xB.isLimitAdTrackingEnabled();
    }
    
    public String nH() {
        this.nJ();
        if (this.xB == null) {
            return null;
        }
        return this.xB.getId();
    }
    
    void start() {
        this.wf.start();
    }
}
